package web.managedbean;

import dao.DaoCursos;
import dao.impl.DaoCursosImpl;
import dto.Cursos;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import parainfo.convert.DeString;

@ManagedBean(name = "mbCursos")
@RequestScoped
public class ManagedBeanCursos {

    private List<Cursos> list;//lista de tipo cursos
    private Cursos cursos = new Cursos(); //inicializar - no null
    DaoCursos daoCursos = new DaoCursosImpl();
    private String message;
    private String ids;

    public ManagedBeanCursos() {
    }

    // gettter y setter
    public List<Cursos> getList() {
        list = daoCursos.cursosQry();

        if (list == null) {
            message = daoCursos.getMessage();
        }

        return list;
    }

    public Cursos getCursos() {
        return cursos;
    }

    public void setCursos(Cursos cursos) {
        this.cursos = cursos;
    }

    public String getMessage() {
        return message;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    // gestion
    public String cursosIns() {
       
        message = daoCursos.cursosIns(cursos);

        if (message == null) {
            return "/index?faces-redirect=true";
        } else {
            return "/cursosIns";
        }
    }

    public String cursosDel() {
        List<Integer> list_ids = DeString.ids(ids);

        if (list_ids != null) {
            message = daoCursos.cursosDel(list_ids);
        } else {
            message = "IDs Incorrectos o no envíados";
        }

        return "/index";
    }

    public String cursosGet() {
        cursos = daoCursos.cursosGet(cursos.getIdcurso());

        if (cursos != null) {
            return "/cursosUpd";
        } else {
            message = daoCursos.getMessage();
            return "/index";
        }
    }

    public String cursosUpd() {
        message = daoCursos.cursosUpd(cursos);

        if (message == null) {
            return "/index?faces-redirect=true";
        } else {
            return "/cursosUpd";
        }
    }
}
