package web.managedbean;

import dao.DaoAutores;
import dao.DaoFrases;
import dao.impl.DaoAutoresImpl;
import dao.impl.DaoFrasesImpl;
import dto.Frases;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import parainfo.convert.DeString;

@ManagedBean(name = "mbFrases")
@RequestScoped
public class ManagedBeanFrases {

    private List<Object[]> list;
    private Frases frases = new Frases();
    DaoFrases daoFrases = new DaoFrasesImpl();
    DaoAutores daoAutores = new DaoAutoresImpl();
    private String message;
    private String ids;

    public ManagedBeanFrases() {
    }

    // gettter y setter
    public List<Object[]> getList() {
        list = daoFrases.frasesQry();

        if (list == null) {
            message = daoFrases.getMessage();
        }

        return list;
    }
    
    public List<Object[]> getCombo() {
        list = daoAutores.autoresCbo();

        if (list == null) {
            message = daoFrases.getMessage();
        }

        return list;
    }

    public Frases getFrases() {
        return frases;
    }

    public void setFrases(Frases frases) {
        this.frases = frases;
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
    public String frasesIns() {
        message = daoFrases.frasesIns(frases);

        if (message == null) {
            return "/index?faces-redirect=true";
        } else {
            return "/frasesIns";
        }
    }

    public String frasesDel() {
        List<Integer> list_ids = DeString.ids(ids);

        if (list_ids != null) {
            message = daoFrases.frasesDel(list_ids);
        } else {
            message = "IDs Incorrectos o no envíados";
        }

        return "/index";
    }

    public String frasesGet() {
        frases
                = daoFrases.frasesGet(frases.getIdfrase());

        if (frases != null) {
            return "/frasesUpd";
        } else {
            message = daoFrases.getMessage();
            return "/index";
        }
    }

    public String frasesUpd() {
        message = daoFrases.frasesUpd(frases);

        if (message == null) {
            return "/index?faces-redirect=true";
        } else {
            return "/frasesUpd";
        }
    }

}

