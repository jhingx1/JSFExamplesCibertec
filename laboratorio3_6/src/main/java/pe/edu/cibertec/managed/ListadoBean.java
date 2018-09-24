/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.cibertec.managed;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;


/**
 *
 * @author portatil
 */
@ManagedBean(name="listados")
@ApplicationScoped
public class ListadoBean {
    
   private List<SelectItem> generos;
   private List<SelectItem> profesiones;
   
   public ListadoBean(){
       generos=new ArrayList<SelectItem>();
       generos.add(new SelectItem("M", "Masculino"));
       generos.add(new SelectItem("F", "Femenino"));
       
       profesiones=new ArrayList<SelectItem>();
       profesiones.add(new SelectItem("001", "Arquitecto"));
       profesiones.add(new SelectItem("002", "Ingeniero"));

   }

    public List<SelectItem> getGeneros() {
        return generos;
    }

    public List<SelectItem> getProfesiones() {
        return profesiones;
    }

    public void setGeneros(List<SelectItem> generos) {
        this.generos = generos;
    }

    public void setProfesiones(List<SelectItem> profesiones) {
        this.profesiones = profesiones;
    }
   
   
   
}
