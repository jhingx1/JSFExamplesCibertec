package beans;
import clases.Persona;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class personaBean {
    
    private Persona persona = new Persona();
    private static List<Persona> lstPersonas=new ArrayList();

    public personaBean() {
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public List<Persona> getLstPersonas() {
        return lstPersonas;
    }

    public void setLstPersonas(List<Persona> lstPersonas) {
        personaBean.lstPersonas = lstPersonas;
    }
    
    //Agregar elemento
    public void agregarPersona(){
        personaBean.lstPersonas.add(this.persona);
    }
    
    public void eliminarPersona(Persona per){
        personaBean.lstPersonas.remove(per);
    }
    
}
