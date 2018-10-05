package web.managedbean;

import dao.DaoAmigos;
import dao.impl.DaoAmigosImpl;
import dto.Amigos;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import parainfo.convert.DeString;

@ManagedBean(name = "mbAmigos")
@RequestScoped
public class ManagedBeanAmigos {

    private List<Amigos> list;//lista de tipo amigos
    private Amigos amigos = new Amigos(); //inicializar - no null
    DaoAmigos daoAmigos = new DaoAmigosImpl();
    private String message;
    private String ids;

    public ManagedBeanAmigos() {
    }

    // gettter y setter
    public List<Amigos> getList() {
        list = daoAmigos.amigosQry();

        if (list == null) {
            message = daoAmigos.getMessage();
        }

        return list;
    }

    public Amigos getAmigos() {
        return amigos;
    }

    public void setAmigos(Amigos amigos) {
        this.amigos = amigos;
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
    public String amigosIns() {
       
        message = daoAmigos.amigosIns(amigos);

        if (message == null) {
            return "/index?faces-redirect=true";
        } else {
            return "/amigosIns";
        }
    }

    public String amigosDel() {
        List<Integer> list_ids = DeString.ids(ids);

        if (list_ids != null) {
            message = daoAmigos.amigosDel(list_ids);
        } else {
            message = "IDs Incorrectos o no envíados";
        }

        return "/index";
    }

    public String amigosGet() {
        amigos = daoAmigos.amigosGet(amigos.getIdamigo());

        if (amigos != null) {
            return "/amigosUpd";
        } else {
            message = daoAmigos.getMessage();
            return "/index";
        }
    }

    public String amigosUpd() {
        message = daoAmigos.amigosUpd(amigos);

        if (message == null) {
            return "/index?faces-redirect=true";
        } else {
            return "/amigosUpd";
        }
    }
}
