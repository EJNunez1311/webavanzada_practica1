package webavanzada_practica1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import webavanzada_practica1.Controladores.ClienteController;
import webavanzada_practica1.Controladores.EquipoController;

import java.io.File;

//Necesito para trabajar con archivos debo especificar esto
@ComponentScan({"webavanzada_practica1", "webavanzada_practica1.Controladores"})
@SpringBootApplication
public class WebavanzadaPractica1Application {


    public static void main(String[] args) {

        //Creo directorio para cliente y equipo
        new File(ClienteController.uploadDirectory).mkdir();

        new File(EquipoController.uploadDirectory).mkdir();

        SpringApplication.run(WebavanzadaPractica1Application.class, args);
    }

}
