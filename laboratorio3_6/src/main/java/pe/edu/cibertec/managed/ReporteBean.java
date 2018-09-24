/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.cibertec.managed;

import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;


/**
 *
 * @author portatil
 */
@ManagedBean(name="reporte")
public class ReporteBean {
    
    private String tipo;
    private String titulo;
    
    public void confirmarAccion(ActionEvent ae){
        tipo=(String)ae.getComponent().getAttributes().get("tipo");
        titulo=(String)ae.getComponent().getAttributes().get("titulo");
    }
    
    public String consultar(){
       
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

   

   
}
