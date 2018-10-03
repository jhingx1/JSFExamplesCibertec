package dao;

import dto.Autores;
import java.util.List;

public interface DaoAutores {

    public List<Autores> autoresQry();

    public String autoresIns(Autores autores);

    public String autoresDel(List<Integer> ids);

    public Autores autoresGet(Integer idautor);

    public String autoresUpd(Autores autores);

    public List<Object[]> autoresCbo();
    
    public String getMessage();
}

