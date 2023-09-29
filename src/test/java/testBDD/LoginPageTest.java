package testBDD;


import basesSteps.LoginPageSteps;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import models.Usuario;
import utils.YamlUtils;

public class LoginPageTest {

    private Usuario credenciales ;

    @Dado("Un usuario con credenciales validas")
    public void unUsuarioConCredencialesValidas() {
        credenciales =  new YamlUtils().getUsuarioValido("usuarioBasico");
    }

    @Cuando("el usuario inicia sesi\u00F3n")
    public void elUsuarioIniciaSesion() {
        new LoginPageSteps().iniciarSesion(credenciales.getUsuario(),credenciales.getPassword());
    }

}
