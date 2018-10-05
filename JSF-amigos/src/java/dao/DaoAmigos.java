package dao;

import dto.Amigos;
import java.util.List;

public interface DaoAmigos {

    public List<Amigos> amigosQry();

    public String amigosIns(Amigos amigos);

    public String amigosDel(List<Integer> ids);

    public Amigos amigosGet(Integer idamigo);

    public String amigosUpd(Amigos amigos);

    public String getMessage();
}
