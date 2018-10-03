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
                .append("titulo,")
                .append("horas,")
                .append("horario,fechainicio ")
                .append("FROM curso");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString());
                ResultSet rs = ps.executeQuery()) {

            list = new LinkedList<>();
            while (rs.next()) {
                Cursos p = new Cursos();

                p.setIdcurso(rs.getInt(1));
                p.setTitulo(rs.getString(2));
                p.setHoras(rs.getInt(3));
                p.setHorario(rs.getString(4));
                p.setFechainicio(rs.getDate(5));

                list.add(p);
            }

        } catch (SQLException e) {
            message = e.getMessage();
        }

        return list;
    }


    public String cursosIns(Cursos cursos) {
        sql.delete(0, sql.length())
                .append("INSERT INTO curso(")
                .append("titulo,")
                .append("horas,")
                .append("horario,")
                .append("fechainicio")
                .append(") VALUES(?,?,?,?)");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString())) {

            ps.setString(1, cursos.getTitulo());
            ps.setInt(2, cursos.getHoras());
            ps.setString(3, cursos.getHorario());
            ps.setDate(4,new java.sql.Date(cursos.getFechainicio().getTime()));
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
                .append("DELETE FROM curso WHERE idcurso=?");

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
                .append("titulo,")
                .append("horas,")
                .append("horario, ")
                .append("fechainicio ")
                .append("FROM curso ")
                .append("WHERE idcurso=?");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString())) {

            ps.setInt(1, idcurso);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    cursos = new Cursos();

                    cursos.setIdcurso(rs.getInt(1));
                    cursos.setTitulo(rs.getString(2));
                    cursos.setHoras(rs.getInt(3));
                    cursos.setHorario(rs.getString(4));
                    cursos.setFechainicio(rs.getDate(5));

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
                .append("UPDATE curso SET ")
                .append("titulo=?,")
                .append("horas=?,")
                .append("horario=?, ")
                .append("fechainicio=? ")
                .append("WHERE idcurso=?");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString())) {

            ps.setString(1, cursos.getTitulo());
            ps.setInt(2, cursos.getHoras());
            ps.setString(3, cursos.getHorario());
            ps.setDate(4,new java.sql.Date(cursos.getFechainicio().getTime()));
            //ps.setDate(4,new java.sql.Date(cursos.getFechainicio().getTime()));
            ps.setInt(5, cursos.getIdcurso());

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


