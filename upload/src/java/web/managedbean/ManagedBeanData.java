package web.managedbean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.faces.bean.ManagedBean;
import org.primefaces.event.FileUploadEvent;

@ManagedBean(name = "mbData")
public class ManagedBeanData {

    public void upload(FileUploadEvent event) {
        try {
            copyFile(event.getFile().getFileName(), event.getFile().getInputstream());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void copyFile(String fileName, InputStream in) {
        //almacenar en una ruta temporal.
        try (OutputStream out = new FileOutputStream(new File("c:/temp/" + fileName))) {
            int read;
            byte[] bytes = new byte[1024];//validar para que solo sea de un mega 1024KB
            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            in.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
