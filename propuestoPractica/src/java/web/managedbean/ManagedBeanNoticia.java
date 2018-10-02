package web.managedbean;

import dao.DaoExamen;
import dao.impl.DaoExamenImpl;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;

@ManagedBean(name = "mbnoticia")
@RequestScoped
public class ManagedBeanNoticia {
    
    private String titulo;
    private Integer idcategoria;
    private List<SelectItem> listcategorias;
    private Date fecha;
    private Integer iddepartamento;
    private List<SelectItem> listdepartemento;
    private String noticia;
    private Integer[] idperiodista; //para una lista completa
    private List<SelectItem> listperiodistas;
    
    public ManagedBeanNoticia() {        
    }

    public Integer getIdcategoria() {
        return idcategoria;
    }

    public List<SelectItem> getListcategorias() {
        DaoExamen daoExamen = new DaoExamenImpl();
        listcategorias = daoExamen.cbCategorias();
        return listcategorias;
    }  

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getIddepartamento() {
        return iddepartamento;
    }

    public void setIddepartamento(Integer iddepartamento) {
        this.iddepartamento = iddepartamento;
    }

    public List<SelectItem> getListdepartemento() {
        DaoExamen daoExamen = new DaoExamenImpl();
        listdepartemento = daoExamen.cbDepartamentos();
        return listdepartemento;
    }

    public String getNoticia() {
        return noticia;
    }

    public void setNoticia(String noticia) {
        this.noticia = noticia;
    }

    public Integer[] getIdperiodista() {
        return idperiodista;
    }

    public void setIdperiodista(Integer[] idperiodista) {
        this.idperiodista = idperiodista;
    }

    public List<SelectItem> getListperiodistas() {
        DaoExamen daoExamen = new DaoExamenImpl();
        listperiodistas = daoExamen.cbPeriodistas();
        return listperiodistas;
    }
   
}
