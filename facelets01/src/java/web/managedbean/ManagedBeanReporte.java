package web.managedbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "reporte")
@RequestScoped
public class ManagedBeanReporte {

    public ManagedBeanReporte() {
    }
    private String tipo;
    private String titulo;

    public void confirmarAccion(ActionEvent ae) {
        tipo = (String) ae.getComponent().getAttributes().get("tipo");
        titulo = (String) ae.getComponent().getAttributes().get("titulo");
    }

    public String consultar() {

        //obtener clientes según tipo
        //clientes=servicio.getClientes(tipo);

        return "reporte";
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
}
