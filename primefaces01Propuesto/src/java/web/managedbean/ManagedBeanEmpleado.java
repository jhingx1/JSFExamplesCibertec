package web.managedbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "mbempleado")
@RequestScoped
public class ManagedBeanEmpleado {

    private Integer id;
    private String nombre;
    private String correo;

    public ManagedBeanEmpleado() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    

    public void change() {
        switch (id) {
            case 1:
                nombre = "Juan Pérez";
                correo = "JPeres@gmail.com";
                break;
            case 2:
                nombre = "Ana Gutiérrez";
                correo = "AGuitierrez@gmail.com";
                break;
            case 3:
                nombre = "Jorge Risco";
                correo = "JRisco@gmail.com";
                break;
            case 4:
                nombre = "Katya Rodrígues";
                correo = "KRodriguies@gmail.com";
                break;
        }
    }
}
