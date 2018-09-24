/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.cibertec.managed;

import javax.faces.bean.ManagedBean;
import javax.faces.component.UIInput;
import javax.faces.event.ValueChangeEvent;
import pe.edu.cibertec.bean.Cliente;

/**
 *
 * @author portatil
 */
@ManagedBean(name="clienteBean")
public class ClienteBean {
    
    private Cliente cliente=new Cliente();
    
    private String mensaje;
    
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    public String registrar(){
        
        return "resultado";
    }
    
     public void mensajeProfesion(ValueChangeEvent e){
        String valor=(String)e.getNewValue();
        if(valor.equals("001")){
            setMensaje("Tenemos los mejores cursos de Arquitectura ");
        }
        if(valor.equals("002")){
            setMensaje("Grandes Eventos esperan por ti");
        }

    }
      
   
    
}
