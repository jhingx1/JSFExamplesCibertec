package web.managedbean;

import dao.DaoAutores;
import dao.DaoCursos;
import dao.impl.DaoAutoresImpl;
import dao.impl.DaoCursosImpl;
import dto.Cursos;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import parainfo.convert.DeString;

@ManagedBean(name = "mbCursos")
@RequestScoped
public class ManagedBeanCursos {

    private List<Object[]> list;
    private Cursos cursos = new Cursos();
    DaoCursos daoCursos = new DaoCursosImpl();
    DaoAutores daoAutores = new DaoAutoresImpl();
    private String message;
    private String ids;

    public ManagedBeanCursos() {
    }

    // gettter y setter
    public List<Object[]> getList() {
        list = daoCursos.cursosQry();

        if (list == null) {
            //message = daoCursos.getMessage();
        }

        return list;
    }
    
    public List<Object[]> getCombo() {
        list = daoCursos.cursosCbo();

        if (list == null) {
            //message = daoCursos.getMessage();
        }

        return list;
    }

    public Cursos getCursos() {
        return cursos;
    }

    public void setCursos(Cursos cursos) {
        this.cursos = cursos;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getMessage() {
        return message;
    }

    // gestion
    public String cursosIns() {
        message = daoCursos.cursosIns(cursos);

        if (message == null) {
            return "/index?faces-redirect=true";
        } else {
            return "/frasesIns";
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
        cursos
                = daoCursos.cursosGet(cursos.getIdcursos());

        if (cursos != null) {
            return "/frasesUpd";
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

