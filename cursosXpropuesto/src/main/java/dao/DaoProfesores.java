package dao;

import dto.Profesores;
import java.util.List;

public interface DaoProfesores {

    public List<Profesores> profesoresQry();

    public String profesoresIns(Profesores profesores);

    public String profesoresDel(List<Integer> ids);

    public Profesores profesoresGet(Integer idprofesores);

    public String profesoresUpd(Profesores profesores);
    
    public String getMessage();

}

