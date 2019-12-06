package webavanzada_practica1.Servicios;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

//AAlmacenamiento y obtencion de nombre de la imagen
public class SubirArchivoService {


    //Recibe el archivo y el directorio donde se guardara
    public String almacenarAndDepurarImagen (MultipartFile[] files, String uploadDirectory){

        //Guardando el nombre del archivo
        StringBuilder fileNames = new StringBuilder();

        //Recibo cada archivo subido
        for (MultipartFile file: files) {

            // Obteniendo nombre y almacenando
            Path fileNamePath = Paths.get(uploadDirectory,file.getOriginalFilename());

            fileNames.append(file.getOriginalFilename());

            //Guardando los archivos
            try {

                Files.write(fileNamePath, file.getBytes());
            }catch (IOException e){

                e.printStackTrace();
            }
        }

        //Retornando nombre del archivo
        return fileNames.toString();
    }
}
