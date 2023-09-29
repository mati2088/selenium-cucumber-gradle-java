package utils;


import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import parallel.elements.WebElementParallel;
import parallel.hooks.BaseTest;



public class WebDriverUtils {

     public WebDriver getInstance (){
       return  BaseTest.getDriver();
    }



    /**
     * M&eacute;todo que toma un n&uacute;mero de segundos y frena la ejecuci&oacute;n del programa durante esa cantidad
     * de tiempo
     * @param seconds
     */
    public static void sleep(int seconds){
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

//    /**
//     * M&eacute;todo que realiza un click derecho en el elemento asignado.
//     * @param element locator al que se quiere realizar el click derecho
//     */
//    public static void rightClick(WebElement element) {
//        locatorClickable(element);
//        Actions action = new Actions(new WebDriverUtils().getInstance());
//        action.contextClick(element).perform();
//    }

    /**
     * Presiona la tecla TAB
     */
    public static void presionarTab() {
        new Actions(BaseTest.getDriver()).sendKeys(Keys.TAB).build().perform();
        sleep(2);
    }

    public static void presionarEnter(){
        sleep(1);
        new Actions(BaseTest.getDriver()).sendKeys(Keys.ENTER).build().perform();
        sleep(1);
    }

//    /**
//     * Esperar hasta que el objeto este visible y sea clickable
//     * @param elemento locator del campo a esperar
//     */
//    public static void locatorClickable(WebElement elemento) {
//        try {
//            new WebDriverWait(BaseTest.getDriver(), ConfigHelper.getAppDefaultWait())
//                    .until(ExpectedConditions.elementToBeClickable(elemento));
//        }catch (TimeoutException e){
//            throw new RuntimeException("El elemento está deshabilitado");
//        }
//    }

    /**
     * Esperar hasta que el objeto este visible y sea clickable
     * @param elemento locator del campo a esperar
     * @param timeOut tiempo limite de espera
     */
    public static void locatorClickableWithTimeOut(WebElement elemento, int timeOut) {
        try {
            new WebDriverWait(BaseTest.getDriver(), timeOut)
                    .until(ExpectedConditions.elementToBeClickable(elemento));
        }catch (TimeoutException e){
            throw new RuntimeException("El elemento está deshabilitado");
        }
    }

//    /**
//     * Realiza click en un elemento
//     */
//    public static void clickEnElemento(WebElementParallel elemento) {
//        locatorClickable(new WebElementParallel(elemento.getElementBy()));
//        new PandoraElementParallel(elemento.getElementBy()).click();
//    }

//    /**
//     * Realiza doble click en un elemento
//     */
//    public static void dobleClickEnElemento(WebElement elemento) {
//        locatorClickable(elemento);
//        new Actions(BaseSteps.getDriver()).doubleClick(elemento).build().perform();
//    }

//    /**
//     * Selecciona una opcion de un desplegable
//     */
//    public static void seleccionarEnDesplegable(PandoraElementParallel elemento, Object opcion) {
//        locatorClickable(elemento);
//        elemento.selectOption(String.valueOf(opcion));
//        sleep(2);
//        elemento.sendKeys(Keys.TAB);
//    }

//    /**
//     * Presiona la tecla TAB en un elemento
//     */
//    public static void presionarTabEnElemento(PandoraElementParallel elemento) {
//        locatorClickable(elemento);
//        elemento.sendKeys(Keys.TAB);
//        sleep(1);
//    }

//    /**
//     * Ingresa el dato en un campo especifico y presiona la tecla TAB
//     * @param elemento locator del campo en donde se ingresara el dato
//     * @param dato valor a ingresar
//     */
//    public static void ingresarDatoYPresionarTab(PandoraElementParallel elemento, Object dato){
//        locatorClickable(elemento);
//        elemento.sendKeys(String.valueOf(dato));
//        sleep(1);
//        elemento.sendKeys(Keys.TAB);
//    }

    /**
     * Scrollea al elemento y verifica si esta totalmente visible en la ventana del navegador
     * @param element
     * @return
     */
    public static boolean estaVisible(WebElementParallel element, String nombreElemento) {
        Assertions.assertTrue(element.isDisplayed(), "Elemento no encontrado : " + nombreElemento);
        RemoteWebElement remoteWebElement = (RemoteWebElement) BaseTest.getDriver().findElement(element.getElementBy());
        Point topLeftPoints = remoteWebElement.getCoordinates().inViewPort();
        int top = topLeftPoints.getY();
        int bottom = top + remoteWebElement.getSize().getHeight();
        int left = topLeftPoints.getX();
        int right = left + remoteWebElement.getSize().getWidth();
        new Actions(BaseTest.getDriver()).sendKeys(Keys.ARROW_DOWN).perform();

        Rectangle window = BaseTest.getDriver().findElement(By.xpath("html")).getRect();
        return (top >= 0 && left >= 0 && bottom <= window.getHeight() && right <= window.getWidth());
    }

    /**
     * Espera a que el cursor del mouse no indique mas esperas (icono de carga), el link a continuacion indica los posibles estados del cursor
     * https://www.w3schools.com/cssref/tryit.php?filename=trycss_cursor
     * @param timeout
     * @return
     */
    public static void waitUntilCursorStopsLoading(int timeout){
        WebElementParallel contenido = new WebElementParallel(By.tagName("body"));
        String estadoCursor = contenido.getCssValue("cursor");
        if(estadoCursor.equals("wait")) {
            try {
                new WebDriverWait(BaseTest.getDriver(), timeout).until(x -> !x.findElement(contenido.getElementBy()).getCssValue("cursor").equals("wait"));
            } catch (TimeoutException e) {
                throw new TimeoutException("El cursor indica una espera, no se pudo continuar despues de " + timeout + " segundos");
            }
        }
    }

    /**
     * Tab en elemento interceptado por elemento web, alternativa para el tab tradicional
     * @param elemento
     */
    public static void tabEnElementoInterceptado(WebElementParallel elemento){
        new Actions(BaseTest.getDriver()).moveToElement(BaseTest.getDriver().findElement(elemento.getElementBy())).sendKeys(Keys.TAB).build().perform();
    }

    /**
     * Realiza clic a un elemento hasta que otro este visible
     * @param elementoClick
     * @param xpathElementoEsperado
     * @param intentosMaximo
     */
    public static void clickHastaQueAparezcaElemento(WebElementParallel elementoClick, String xpathElementoEsperado, int intentosMaximo){
        boolean encontrado = false;
        int intentos = 0;
        while(!encontrado && intentos < intentosMaximo){
            elementoClick.click();
            WebDriverUtils.sleep(2);
            try{
                new WebDriverWait(
                        BaseTest.getDriver()
                        , 2
                ).until(ExpectedConditions.visibilityOf(BaseTest.getDriver().findElement(By.xpath(xpathElementoEsperado))));
                encontrado = true;
            }catch(Exception ignored){}
            intentos++;
        }
    }
}
