package web.managedbean;

import dao.DaoTutoriales;
import dao.impl.DaoTutorialesImpl;
import dto.Tutoriales;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import parainfo.convert.DeString;

@ManagedBean(name = "mbTutoriales")
@RequestScoped
public class ManagedBeanTutoriales {

    private List<Tutoriales> list;
    private Tutoriales tutoriales = new Tutoriales(); //inicializar - no null
    DaoTutoriales daoTutoriales = new DaoTutorialesImpl();
    private String message;
    private String ids;

    public ManagedBeanTutoriales() {
    }

    // gettter y setter
    public List<Tutoriales> getList() {
        list = daoTutoriales.tutorialesQry();

        if (list == null) {
            message = daoTutoriales.getMessage();
        }

        return list;
    }

    public Tutoriales getTutoriales() {
        return tutoriales;
    }

    public void setTutoriales(Tutoriales tutoriales) {
        this.tutoriales = tutoriales;
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
    public String tutorialesIns() {
        message = daoTutoriales.tutorialesIns(tutoriales);

        if (message == null) {
            return "/index?faces-redirect=true";
        } else {
            return "/tutorialesIns";
        }
    }

    public String tutorialesDel() {
        List<Integer> list_ids = DeString.ids(ids);

        if (list_ids != null) {
            message = daoTutoriales.tutorialesDel(list_ids);
        } else {
            message = "IDs Incorrectos o no envíados";
        }

        return "/index";
    }

    public String tutorialesGet() {
        tutoriales = 
                daoTutoriales.tutorialesGet(tutoriales.getIdtutorial());

        if (tutoriales != null) {
            return "/tutorialesUpd";
        } else {
            message = daoTutoriales.getMessage();
            return "/index";
        }
    }

    public String tutorialesUpd() {
        message = daoTutoriales.tutorialesUpd(tutoriales);

        if (message == null) {
            return "/index?faces-redirect=true";
        } else {
            return "/tutorialesUpd";
        }
    }
}

