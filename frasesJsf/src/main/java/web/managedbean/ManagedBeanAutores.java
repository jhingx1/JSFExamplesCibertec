package web.managedbean;

import dao.DaoAutores;
import dao.impl.DaoAutoresImpl;
import dto.Autores;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import parainfo.convert.DeString;

@ManagedBean(name = "mbAutores")
@RequestScoped
public class ManagedBeanAutores {

    private List<Autores> list;
    private Autores autores = new Autores();
    DaoAutores daoAutores = new DaoAutoresImpl();
    private String message;
    private String ids;

    public ManagedBeanAutores() {
    }

    // gettter y setter
    public List<Autores> getList() {
        list = daoAutores.autoresQry();

        if (list == null) {
            message = daoAutores.getMessage();
        }

        return list;
    }

    public Autores getAutores() {
        return autores;
    }

    public void setAutores(Autores autores) {
        this.autores = autores;
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
    public String autoresIns() {
        message = daoAutores.autoresIns(autores);

        if (message == null) {
            return "/autoresQry?faces-redirect=true";
        } else {
            return "/autoresIns";
        }
    }

    public String autoresDel() {
        List<Integer> list_ids = DeString.ids(ids);

        if (list_ids != null) {
            message = daoAutores.autoresDel(list_ids);
        } else {
            message = "IDs Incorrectos o no envíados";
        }

        return "/autoresQry";
    }

    public String autoresGet() {
        autores = 
                daoAutores.autoresGet(autores.getIdautor());

        if (autores != null) {
            return "/autoresUpd";
        } else {
            message = daoAutores.getMessage();
            return "/autoresQry";
        }
    }

    public String autoresUpd() {
        message = daoAutores.autoresUpd(autores);

        if (message == null) {
            return "/autoresQry?faces-redirect=true";
        } else {
            return "/autoresUpd";
        }
    }
}

