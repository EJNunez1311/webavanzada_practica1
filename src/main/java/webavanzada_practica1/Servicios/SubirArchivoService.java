package webavanzada_practica1.servicios;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

// almacenamiento y obtencion del nombre de la imagen
@Service
public class SubirArchivoService {

    // una funcion como argumentos un arreglo de multipart que seria en este caso
    // un arreglo de archivos, y el otro argumento es un string con la ubicacion del directorio
    //al que mandare el archivo
    public String almacenarAndDepurarImagen (MultipartFile[] files, String uploadDirectory){

        // guardando el nombre del archivo
        StringBuilder fileNames = new StringBuilder();

        // cada archivo subido y si son varios utilizaremos un foreach
            for (MultipartFile file: files) {

            // consigo y almaceno el nombre el archivo
            Path fileNamePath = Paths.get(uploadDirectory,file.getOriginalFilename());

            //  el nombagrego el nombre del archivo a la variable que definimos arriba
            fileNames.append(file.getOriginalFilename());

            // guardo los archivos o el archivo obtenido
            // El try y el catch son necesarios o sino data error el write
            try {

                Files.write(fileNamePath, file.getBytes());
            }catch (IOException e){

                e.printStackTrace();
            }
        }
        // retorno el nombre del archivo para asi mandarle esto al constructor de cliente o equipo
        // y guardar el nombre de la imagen
        return fileNames.toString();
    }
}
