package dao.impl;

import dao.DaoAmigos;
import dto.Amigos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import parainfo.sql.ConectaDb;

public class DaoAmigosImpl implements DaoAmigos {

    private final ConectaDb db;
    private final StringBuilder sql;
    private String message;

    public DaoAmigosImpl() {
        this.db = new ConectaDb();
        this.sql = new StringBuilder();
    }

    @Override
    public List<Amigos> amigosQry() {
        List<Amigos> list = null;
        sql.delete(0, sql.length())
                .append("SELECT ")
                .append("idamigo,")
                .append("nombre,")
                .append("correo,")
                .append("telefono,")
                .append("direccion,")
                .append("nacimiento ")
                .append("FROM amigos");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString());
                ResultSet rs = ps.executeQuery()) {

            list = new LinkedList<>();
            while (rs.next()) {
                Amigos p = new Amigos();

                p.setIdamigo(rs.getInt(1));
                p.setNombre(rs.getString(2));
                p.setCorreo(rs.getString(3));
                p.setTelefono(rs.getString(4));
                p.setDireccion(rs.getString(5));
                p.setNacimiento(rs.getDate(6));

                list.add(p);
            }

        } catch (SQLException e) {
            message = e.getMessage();
        }

        return list;
    }

    @Override
    public String amigosIns(Amigos amigos) {
        sql.delete(0, sql.length())
                .append("INSERT INTO amigos(")
                .append("nombre,")
                .append("correo,")
                .append("telefono,")
                .append("direccion,")
                .append("nacimiento")
                .append(") VALUES(?,?,?,?,?)");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString())) {

            ps.setString(1, amigos.getNombre());
            ps.setString(2, amigos.getCorreo());
            ps.setString(3, amigos.getTelefono());
            ps.setString(4, amigos.getDireccion());
            ps.setDate(5,new java.sql.Date(amigos.getNacimiento().getTime()));
            //ps.setDate(4,new java.sql.Date(amigos.getFechainicio().getTime()));
            //ps.setDate(4, amigos.getFechainicio());
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

    @Override
    public String amigosDel(List<Integer> ids) {
        sql.delete(0, sql.length())
                .append("DELETE FROM amigos WHERE idamigo =?");

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
    public Amigos amigosGet(Integer idcurso) {
        Amigos amigos = null;
        sql.delete(0, sql.length())
                .append("SELECT ")
                .append("idamigo,")
                .append("nombre,")
                .append("correo,")
                .append("telefono, ")
                .append("direccion, ")
                .append("nacimiento ")
                .append("FROM amigos ")
                .append("WHERE idamigo =?");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString())) {

            ps.setInt(1, idcurso);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    amigos = new Amigos();

                    amigos.setIdamigo(rs.getInt(1));
                    amigos.setNombre(rs.getString(2));
                    amigos.setCorreo(rs.getString(3));
                    amigos.setTelefono(rs.getString(4));
                    amigos.setDireccion(rs.getString(5));
                    amigos.setNacimiento(rs.getDate(6));

                } else {
                    throw new SQLException("ID: "
                            + idcurso + " no existe");
                }
            }

        } catch (SQLException e) {
            message = e.getMessage();
        }

        return amigos;
    }

    @Override
    public String amigosUpd(Amigos amigos) {
        sql.delete(0, sql.length())
                .append("UPDATE amigos SET ")
                .append("nombre=?,")
                .append("correo=?,")
                .append("telefono=?, ")
                .append("direccion=?, ")
                .append("nacimiento=? ")
                .append("WHERE idcurso=?");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString())) {

            ps.setString(1, amigos.getNombre());
            ps.setString(2, amigos.getCorreo());
            ps.setString(3, amigos.getTelefono());
            ps.setString(4, amigos.getDireccion());
            ps.setDate(5, new java.sql.Date(amigos.getNacimiento().getTime()));
            ps.setInt(6, amigos.getIdamigo());
            
            

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


