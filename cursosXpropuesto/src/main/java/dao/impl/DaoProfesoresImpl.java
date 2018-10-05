package dao.impl;

import dao.DaoProfesores;
import dto.Profesores;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import parainfo.sql.ConectaDb;

public class DaoProfesoresImpl implements DaoProfesores {

    private final ConectaDb db;
    private final StringBuilder sql;
    private String message;

    public DaoProfesoresImpl() {
        this.db = new ConectaDb();
        this.sql = new StringBuilder();
    }

    @Override
    public List<Profesores> profesoresQry() {
        List<Profesores> list = null;
        
        sql.delete(0, sql.length())
                .append("SELECT ")
                .append("idprofesores,")
                .append("nombreprofesores,")
                .append("carrera,")
                .append("fechaingreso,")
                .append("tipocontrato ")
                .append("FROM profesores");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = 
                        cn.prepareStatement(sql.toString());
                ResultSet rs = ps.executeQuery()) {

            list = new LinkedList<>();
            while (rs.next()) {
                Profesores a = new Profesores();

                a.setIdprofesores(rs.getInt(1));
                a.setNombreprofesores(rs.getString(2));
                a.setCarrera(rs.getString(3));
                a.setFechaingreso(rs.getDate(4));
                a.setTipocontrato(rs.getString(5));
                
                list.add(a);
            }

        } catch (SQLException e) {
            message = e.getMessage();
        }

        return list;
    }

    @Override
    public String profesoresIns(Profesores profesores) {
        sql.append("INSERT INTO profesores(")
                .append("nombreprofesores,")
                .append("carrera,")
                .append("fechaingreso,")
                .append("tipocontrato")
                .append(") VALUES(?,?,?,?)");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = 
                        cn.prepareStatement(sql.toString())) {

            ps.setString(1, profesores.getNombreprofesores());
            ps.setString(2, profesores.getCarrera());
            //ps.setDate(3, profesores.getFechaingreso());
            ps.setDate(3, new java.sql.Date(profesores.getFechaingreso().getTime()));            
            ps.setString(4, profesores.getTipocontrato());

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
    public String profesoresDel(List<Integer> ids) {
        //sql.append("DELETE FROM profesores WHERE idprofesores=?");
        
        sql.delete(0, sql.length())
                .append("DELETE FROM profesores WHERE idprofesores=?");

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

    @Override
    public Profesores profesoresGet(Integer idprofesores) {
        Profesores profesores = null;
        
        sql.append("SELECT idprofesores,nombreprofesores,carrera,fechaingreso,tipocontrato FROM profesores WHERE idprofesores=?;");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = 
                        cn.prepareStatement(sql.toString())) {

            ps.setInt(1, idprofesores);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    profesores = new Profesores();

                    profesores.setIdprofesores(rs.getInt(1));
                    profesores.setNombreprofesores(rs.getString(2));
                    profesores.setCarrera(rs.getString(3));
                    profesores.setFechaingreso(rs.getDate(4));
                    profesores.setTipocontrato(rs.getString(5));

                } else {
                    throw new SQLException("ID: " 
                            + idprofesores + " no existe");
                }
            }

        } catch (SQLException e) {
            message = e.getMessage();
        }

        return profesores;
    }

    @Override
    public String profesoresUpd(Profesores profesores) {
        sql.append("UPDATE profesores SET ")
                .append("nombreprofesores=?,")
                .append("carrera=?,")
                .append("fechahoraingreso=?,")
                .append("tipocontrato=? ")
                .append("WHERE idprofesores=?");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = 
                        cn.prepareStatement(sql.toString())) {

            ps.setString(1, profesores.getNombreprofesores());
            ps.setString(2, profesores.getCarrera());
            //ps.setDate(3, profesores.getFechaingreso());
            ps.setDate(3, new java.sql.Date(profesores.getFechaingreso().getTime()));
            ps.setString(4,profesores.getTipocontrato());
            ps.setInt(5, profesores.getIdprofesores());

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

