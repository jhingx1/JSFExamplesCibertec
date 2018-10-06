package dao;

import dto.Cursos;
import java.util.List;

public interface DaoCursos {

    public List<Cursos> cursosQry();

    public String cursosIns(Cursos cursos);

    public String cursosDel(List<Integer> ids);

    public Cursos cursosGet(Integer idcurso);

    public String cursosUpd(Cursos cursos);

    public String getMessage();
}
