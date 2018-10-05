package web.managedbean;

import dao.DaoProfesores;
import dao.impl.DaoProfesoresImpl;
import dto.Profesores;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import parainfo.convert.DeString;

@ManagedBean(name = "mbProfesores")
@RequestScoped
public class ManagedBeanProfesores {

    private List<Profesores> list;
    private Profesores profesores = new Profesores();
    DaoProfesores daoProfesores = new DaoProfesoresImpl();
    private String message;
    private String ids;

    public ManagedBeanProfesores() {
    }

    // gettter y setter
    public List<Profesores> getList() {
        list = daoProfesores.profesoresQry();

        if (list == null) {
            message = daoProfesores.getMessage();
        }

        return list;
    }

    public Profesores getProfesores() {
        return profesores;
    }

    public void setProfesores(Profesores profesores) {
        this.profesores = profesores;
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
    public String profesoresIns() {
        message = daoProfesores.profesoresIns(profesores);

        if (message == null) {
            return "/autoresQry?faces-redirect=true";
        } else {
            return "/autoresIns";
        }
    }

    public String profesoresDel() {
        List<Integer> list_ids = DeString.ids(ids);

        if (list_ids != null) {
            message = daoProfesores.profesoresDel(list_ids);
        } else {
            message = "IDs Incorrectos o no envíados";
        }

        return "/autoresQry";
    }

    public String profesoresGet() {
        profesores = 
                daoProfesores.profesoresGet(profesores.getIdprofesores());

        if (profesores != null) {
            return "/profesoresUpd";
        } else {
            message = daoProfesores.getMessage();
            return "/profesoresQry";
        }
    }

    public String profesoresUpd() {
        message = daoProfesores.profesoresUpd(profesores);

        if (message == null) {
            return "/profesoresQry?faces-redirect=true";
        } else {
            return "/profesoresUpd";
        }
    }
}

