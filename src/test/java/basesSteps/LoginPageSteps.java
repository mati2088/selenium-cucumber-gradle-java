package basesSteps;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import pages.LoginPage;
import utils.WebDriverUtils;

public class LoginPageSteps {

    LoginPage pagina;
    private static final String PAGE_NAME = "Inicios de sesi\u00F3n recientes: ";

    public LoginPageSteps(){
        pagina = new LoginPage();
        validarTitulo();
    }

    @Step(PAGE_NAME + "Se valida el t\u00EDtulo de la p\u00E1gina")
    private LoginPageSteps validarTitulo(){
        Assertions.assertTrue(pagina.getTitulo().isDisplayed(),
                PAGE_NAME + "El t\u00EDtulo de la p\u00E1gina no se encuentra visible");
        return this;
    }


    @Step(PAGE_NAME + "Hacer clic en el bot\u00F3n iniciar sesi\u00F3n")
    public LoginPageSteps clickBotonIniciarSesion() {
        Assertions.assertTrue(pagina.getBotonIniciarSesion().isDisplayed(), "El bot\u00F3n Iniciar Sesi\u00F3n no se encuentra visible");
        pagina.getBotonIniciarSesion().click();
        return this;
    }

    @Step(PAGE_NAME + "Se ingresa nombre del usuario")
    public LoginPageSteps ingresarUsuario(String usuario) {
        Assertions.assertTrue(pagina.getCampoUsuario().isDisplayed(), "El campo usuario no se encuentra visible");
        pagina.getCampoUsuario().sendKeys(usuario);
        return this;
    }

    @Step(PAGE_NAME + "Se ingresa el password")
    public LoginPageSteps ingresarPassword(String password) {
        Assertions.assertTrue(pagina.getCampoPassword().isDisplayed(), "El campo password no se encuentra visible");
        pagina.getCampoPassword().click();
        WebDriverUtils.sleep(3);
        pagina.getCampoPassword().sendKeys(password);
        return this;
    }

    @Step(PAGE_NAME + "el usuario inicia sesi\u00F3n")
    public void iniciarSesion(String usuario,String password) {
        ingresarUsuario(usuario);
        WebDriverUtils.sleep(3);
        ingresarPassword(password);
        WebDriverUtils.sleep(3);
        clickBotonIniciarSesion();
    }
}
