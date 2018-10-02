package web.managedbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "mbempleado")
@RequestScoped
public class ManagedBeanEmpleado {

    private Integer id;
    private String nombre;

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

    public void change() {
        switch (id) {
            case 1:
                nombre = "Juan Pérez";
                break;
            case 2:
                nombre = "Ana Gutiérrez";
                break;
            case 3:
                nombre = "Jorge Risco";
                break;
            case 4:
                nombre = "Katya Rodrígues";
        }
    }

}
