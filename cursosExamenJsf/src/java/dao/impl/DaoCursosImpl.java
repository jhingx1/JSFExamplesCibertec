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
    public List<Cursos> cursosQry() {
        List<Cursos> list = null;
        sql.delete(0, sql.length())
                .append("SELECT ")
                .append("idcurso,")
                .append("curso,")
                .append("fechaInicio,")
                .append("fechaFin,")
                .append("cantidadHoras,")
                .append("ambiente ")
                .append("FROM cursos");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString());
                ResultSet rs = ps.executeQuery()) {

            list = new LinkedList<>();
            while (rs.next()) {
                Cursos p = new Cursos();

                p.setIdcurso(rs.getInt(1));
                p.setCurso(rs.getString(2));
                p.setFechainicio(rs.getDate(3));
                p.setFechafin(rs.getDate(4));
                p.setCantidadHoras(rs.getInt(5));
                p.setAmbiente(rs.getString(6));
                
                list.add(p);
            }

        } catch (SQLException e) {
            message = e.getMessage();
        }

        return list;
    }

    @Override
    public String cursosIns(Cursos cursos) {
        sql.delete(0, sql.length())
                .append("INSERT INTO cursos(")
                .append("curso,")
                .append("fechaInicio,")
                .append("fechaFin,")
                .append("cantidadHoras,")
                .append("ambiente")
                .append(") VALUES(?,?,?,?,?)");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString())) {

            ps.setString(1, cursos.getCurso());
            ps.setDate(2, new java.sql.Date(cursos.getFechainicio().getTime()));
            ps.setDate(3, new java.sql.Date(cursos.getFechafin().getTime()));
            ps.setInt(4, cursos.getCantidadHoras());
            ps.setString(5, cursos.getAmbiente());
            //ps.setDate(4,new java.sql.Date(cursos.getFechainicio().getTime()));
            //ps.setDate(4, cursos.getFechainicio());
            //ps.setDate(1,  new java.sql.Date(datJourCchn.getTime()));
            

            int ctos = ps.executeUpdate();
            if (ctos == 0) {
                throw new SQLException("0 filas afectadas");
            }

        } catch (SQLException e) {
            message = e.getMessage();
        }

        return message;
    }


    public String cursosDel(List<Integer> ids) {
        sql.delete(0, sql.length())
                .append("DELETE FROM cursos WHERE idcurso=?");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = 
                        cn.prepareStatement(sql.toString())) {

            cn.setAutoCommit(false); // desactiva autoCommit
            boolean ok = true;

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


    public Cursos cursosGet(Integer idcurso) {
        Cursos cursos = null;
        sql.delete(0, sql.length())
                .append("SELECT ")
                .append("idcurso,")
                .append("curso,")
                .append("fechaInicio,")
                .append("fechaFin, ")
                .append("cantidadHoras, ")
                .append("ambiente ")
                .append("FROM cursos ")
                .append("WHERE idcurso=?");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString())) {

            ps.setInt(1, idcurso);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    cursos = new Cursos();

                    cursos.setIdcurso(rs.getInt(1));
                    cursos.setCurso(rs.getString(2));
                    cursos.setFechainicio(rs.getDate(3));
                    cursos.setFechafin(rs.getDate(4));
                    cursos.setCantidadHoras(rs.getInt(5));
                    cursos.setAmbiente(rs.getString(6));
                    
                } else {
                    throw new SQLException("ID: "
                            + idcurso + " no existe");
                }
            }

        } catch (SQLException e) {
            message = e.getMessage();
        }

        return cursos;
    }


    public String cursosUpd(Cursos cursos) {
        sql.delete(0, sql.length())
                .append("UPDATE cursos SET ")
                .append("curso=?,")
                .append("fechaInicio=?,")
                .append("fechaFin=?, ")
                .append("cantidadHoras=?, ")
                .append("ambiente=? ")
                .append("WHERE idcurso=?");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString())) {

            ps.setString(1, cursos.getCurso());
            ps.setDate(2, new java.sql.Date(cursos.getFechainicio().getTime()));
            ps.setDate(3, new java.sql.Date(cursos.getFechafin().getTime()));
            ps.setInt(4, cursos.getCantidadHoras());
            ps.setString(5, cursos.getAmbiente());
            //ps.setDate(4,new java.sql.Date(cursos.getFechainicio().getTime()));
            ps.setInt(6, cursos.getIdcurso());

            int ctos = ps.executeUpdate();
            if (ctos == 0) {
                throw new SQLException("0 filas afectadas");
            }

        } catch (SQLException ex) {
            message = ex.getMessage();
        }

        return message;
    }


    public String getMessage() {
        return message;
    }

}


