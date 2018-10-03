package dao.impl;

import dao.DaoCursos;
import dto.Cursos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import parainfo.sql.ConectaDb;

public class DaoCursosImpl implements DaoCursos {

    private final ConectaDb db;
    private final StringBuilder sql;
    private String message;

    public DaoCursosImpl() {
        this.db = new ConectaDb();
        this.sql = new StringBuilder();
    }

    @Override
    public List<Object[]> cursosQry() {
        List<Object[]> list = null;
        sql.delete(0, sql.length())
                .append("SELECT ")
                .append("cursos.idcursos,")
                .append("profesores.nombreprofesores,")
                .append("profesores.carrera,")
                .append("cursos.nombrecursos,")
                .append("cursos.descripcion,")
                //.append("cursos.horaInicio,")
                .append("cursos.horas,")
                .append("cursos.fechainicio,")
                .append("cursos.fechafin ")
                .append("FROM cursos ")
                .append("INNER JOIN profesores ")
                .append("ON cursos.idprofesores = profesores.idprofesores ")
                .append("ORDER BY cursos.nombrecursos");

        try (Connection cn = db.getConnection();
                PreparedStatement ps
                = cn.prepareStatement(sql.toString());
                ResultSet rs = ps.executeQuery()) {

            list = new LinkedList<>();
            while (rs.next()) {
                Object[] reg = new Object[8];

                reg[0] = rs.getInt(1); //listar idFrase
                reg[1] = rs.getString(2);//listart autor
                reg[2] = rs.getString(3);//listar cursos.
                reg[3] = rs.getString(4);
                reg[4] = rs.getString(5);
                reg[5] = rs.getString(6);
                reg[6] = rs.getDate(7);
                reg[7] = rs.getDate(8);
                //reg[8] = rs.getDate(9);

                list.add(reg);
            }

        } catch (SQLException e) {
            message = e.getMessage();
        }

        return list;
    }

    @Override
    public List<Object[]> cursosCbo() {
        List<Object[]> list = null;
        // en este caso coincide con Qry pero no siempre será así
        sql.delete(0, sql.length())
                .append("SELECT ")
                .append("idprofesores,")
                .append("nombreprofesores ")
                .append("FROM profesores");

        try (Connection cn = db.getConnection();
                PreparedStatement ps
                = cn.prepareStatement(sql.toString());
                ResultSet rs = ps.executeQuery()) {

            list = new LinkedList<>();
            while (rs.next()) {
                Object[] reg = new Object[2];

                reg[0] = rs.getInt(1);
                reg[1] = rs.getString(2);

                list.add(reg);
            }

        } catch (SQLException e) {
            message = e.getMessage();
        }

        return list;
    }

    
    @Override
    public String cursosIns(Cursos cursos) {        
        //Solo inserta codigo        
        sql.delete(0, sql.length())
                .append("INSERT INTO cursos(")
                .append("nombrecursos,")
                .append("descripcion,")
                .append("fechainicio,")
                .append("fechafin,")                
                .append("tipo,")
                .append("horas,")
                .append("idprofesores")
                .append(") values(?,?,?,?,?,?,?)");

        try (
            
            Connection cn = db.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql.toString())) {
                    
            ps.setString(1,cursos.getNombrecursos());
            ps.setString(2,cursos.getDescripcion());
            //ps.setDate(3,cursos.getFechainicio());
            ps.setDate(3,new java.sql.Date(cursos.getFechainicio().getTime()));
            //ps.setDate(4,cursos.getFechafin());            
            ps.setDate(4,new java.sql.Date(cursos.getFechafin().getTime()));
            ps.setString(5,cursos.getTipo());
            ps.setString(6,cursos.getHoras());
            ps.setInt(7,cursos.getIdprofesores());
            
            int ctos = ps.executeUpdate();
            if (ctos == 0) {
                throw new SQLException("0 filas afectadas");
            }

        } catch (SQLException e) {
            message = e.getMessage();
        }

        return message;
    }

    @Override
    public String cursosDel(List<Integer> ids) {
        
        sql.delete(0, sql.length())
                .append("DELETE FROM cursos WHERE idcursos=?");

        try (Connection cn = db.getConnection();
                PreparedStatement ps
                = cn.prepareStatement(sql.toString())) {

            cn.setAutoCommit(false); // desactiva autoCommit
            boolean ok = true;
            //Para la eliminacion multiple
            for (Integer x : ids) {
                ps.setInt(1, x);

                int ctos = ps.executeUpdate();
                if (ctos == 0) {
                    ok = false;
                    message = "ID recibido no existe";
                    break;
                }
            }

            if (ok) {
                cn.commit();
            } else {
                cn.rollback();
            }

            cn.setAutoCommit(true); // activa autoCommit

        } catch (SQLException e) {
            message = e.getMessage();
        }

        return message;
    }
    //para actualizar.
    @Override
    public Cursos cursosGet(Integer idcursos) {
        Cursos cursos = null;
        
        sql.delete(0, sql.length())
                .append("SELECT ")
                .append("idcursos,")
                .append("nombrecursos,")
                .append("descripcion,")
                .append("fechainicio,")
                .append("fechafin,")
                .append("tipo,")
                .append("horas,")
                .append("idprofesores ")
                .append("FROM cursos ")
                .append("WHERE idcursos=?");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = 
                        cn.prepareStatement(sql.toString())) {

            ps.setInt(1, idcursos);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    cursos = new Cursos();

                    cursos.setIdcursos(rs.getInt(1));
                    cursos.setNombrecursos(rs.getString(2));
                    cursos.setDescripcion(rs.getString(3));
                    cursos.setFechainicio(rs.getDate(4));
                    cursos.setFechafin(rs.getDate(5));                    
                    cursos.setTipo(rs.getString(6));
                    cursos.setHoras(rs.getString(7));
                    cursos.setIdprofesores(rs.getInt(8));
                    
                } else {
                    throw new SQLException("ID: " 
                            + idcursos + " no existe");
                }
            }

        } catch (SQLException e) {
            message = e.getMessage();
        }

        return cursos;
    }
    //Para actualizar
    @Override
    public String cursosUpd(Cursos cursos) {
        
        sql.delete(0, sql.length())
                .append("UPDATE cursos SET ")
                .append("nombrecursos=?,")
                .append("descripcion=?,")
                .append("fechainicio=?,")
                .append("fechafin=?,")
                .append("tipo=?,")
                .append("horas=?,")
                .append("idprofesores=? ")                
                .append("WHERE idcursos=?");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = 
                        cn.prepareStatement(sql.toString())) {

            ps.setString(1,cursos.getNombrecursos());
            ps.setString(2,cursos.getDescripcion());
            //ps.setDate(3, cursos.getFechainicio());
            ps.setDate(3,new java.sql.Date(cursos.getFechainicio().getTime()));
            //ps.setDate(4, cursos.getFechafin());
            ps.setDate(4,new java.sql.Date(cursos.getFechafin().getTime()));
            ps.setString(5, cursos.getTipo());
            ps.setString(6, cursos.getHoras());
            ps.setInt(7, cursos.getIdprofesores());
            ps.setInt(8, cursos.getIdcursos());

            int ctos = ps.executeUpdate();
            if (ctos == 0) {
                throw new SQLException("0 filas afectadas");
            }

        } catch (SQLException ex) {
            message = ex.getMessage();
        }

        return message;
    }

    @Override
    public String getMessage() {
        return message;
    }
    
}
