package web.managedbean;

import dao.impl.DaoExamenImpl;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;

@ManagedBean(name = "data")
@RequestScoped
public class ManagedBeanData {

    private String titulo;
    private Integer idcategoria;
    private String noticia;
    private Date fecha;
    private Integer iddepartamento;
    private Integer[] idperiodista;
    //
    private List<SelectItem> cbCategorias;
    private List<SelectItem> cbDepartamentos;
    private List<SelectItem> cbPeriodistas;
    //
    private final DaoExamenImpl daoExamen;

    public ManagedBeanData() {
        this.daoExamen = new DaoExamenImpl();
    }

    public List<SelectItem> getCbCategorias() {
        return daoExamen.cbCategorias();
    }

    public List<SelectItem> getCbDepartamentos() {
        return daoExamen.cbDepartamentos();
    }

    public List<SelectItem> getCbPeriodistas() {
        return daoExamen.cbPeriodistas();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(Integer idcategoria) {
        this.idcategoria = idcategoria;
    }

    public String getNoticia() {
        return noticia;
    }

    public void setNoticia(String noticia) {
        this.noticia = noticia;
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

    public Integer[] getIdperiodista() {
        return idperiodista;
    }

    public void setIdperiodista(Integer[] idperiodista) {
        this.idperiodista = idperiodista;
    }
}
