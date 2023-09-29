package pages;

import org.openqa.selenium.By;
import parallel.elements.WebElementParallel;

public class LoginPage {

    private String titulo = "//input[@type='text']";
    private String campoPassword = "//div[@id='passContainer']";
    private String CampoUsuario = "//input[@type='text']";

    private String botonIniciarSesion = "//button[contains(text(),'Iniciar sesión')]";

    public WebElementParallel getTitulo(){return new WebElementParallel(By.xpath(titulo));}

    public WebElementParallel getCampoPassword(){return new WebElementParallel(By.xpath(campoPassword));}

    public WebElementParallel getBotonIniciarSesion(){return new WebElementParallel(By.xpath(botonIniciarSesion));}

    public WebElementParallel getCampoUsuario(){return new WebElementParallel(By.xpath(CampoUsuario));}



}
