package dao.impl;

import dao.DaoAutores;
import dto.Autores;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import parainfo.sql.ConectaDb;

public class DaoAutoresImpl implements DaoAutores {

    private final ConectaDb db;
    private final StringBuilder sql;
    private String message;

    public DaoAutoresImpl() {
        this.db = new ConectaDb();
        this.sql = new StringBuilder();
    }

    @Override
    public List<Autores> autoresQry() {
        List<Autores> list = null;
        sql.delete(0, sql.length())
                .append("SELECT ")
                .append("idautor,")
                .append("autor ")
                .append("FROM autores");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = 
                        cn.prepareStatement(sql.toString());
                ResultSet rs = ps.executeQuery()) {

            list = new LinkedList<>();
            while (rs.next()) {
                Autores a = new Autores();

                a.setIdautor(rs.getInt(1));
                a.setAutor(rs.getString(2));

                list.add(a);
            }

        } catch (SQLException e) {
            message = e.getMessage();
        }

        return list;
    }

    @Override
    public String autoresIns(Autores autores) {
        sql.delete(0, sql.length())
                .append("INSERT INTO autores(")
                .append("autor")
                .append(") VALUES(?)");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = 
                        cn.prepareStatement(sql.toString())) {

            ps.setString(1, autores.getAutor());

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
    public String autoresDel(List<Integer> ids) {
        sql.delete(0, sql.length())
                .append("DELETE FROM autores WHERE idautor=?");

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
    public Autores autoresGet(Integer idautor) {
        Autores autores = null;
        sql.delete(0, sql.length())
                .append("SELECT ")
                .append("idautor,")
                .append("autor ")
                .append("FROM autores ")
                .append("WHERE idautor=?");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = 
                        cn.prepareStatement(sql.toString())) {

            ps.setInt(1, idautor);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    autores = new Autores();

                    autores.setIdautor(rs.getInt(1));
                    autores.setAutor(rs.getString(2));

                } else {
                    throw new SQLException("ID: " 
                            + idautor + " no existe");
                }
            }

        } catch (SQLException e) {
            message = e.getMessage();
        }

        return autores;
    }

    @Override
    public String autoresUpd(Autores autores) {
        sql.delete(0, sql.length())
                .append("UPDATE autores SET ")
                .append("autor=? ")
                .append("WHERE idautor=?");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = 
                        cn.prepareStatement(sql.toString())) {

            ps.setString(1, autores.getAutor());
            ps.setInt(2, autores.getIdautor());

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
    public List<Object[]> autoresCbo() {
        List<Object[]> list = null;
        // en este caso coincide con Qry pero no siempre será así        
        sql.delete(0, sql.length())
                .append("SELECT ")
                .append("idautor,")
                .append("autor ")
                .append("FROM autores");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = 
                        cn.prepareStatement(sql.toString());
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
    public String getMessage() {
        return message;
    }

}

