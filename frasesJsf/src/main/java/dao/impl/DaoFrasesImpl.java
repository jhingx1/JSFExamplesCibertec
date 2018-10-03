package dao.impl;

import dao.DaoFrases;
import dto.Frases;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import parainfo.sql.ConectaDb;

public class DaoFrasesImpl implements DaoFrases {

    private final ConectaDb db;
    private final StringBuilder sql;
    private String message;

    public DaoFrasesImpl() {
        this.db = new ConectaDb();
        this.sql = new StringBuilder();
    }

    @Override
    public List<Object[]> frasesQry() {
        List<Object[]> list = null;
        sql.delete(0, sql.length())
                .append("SELECT ")
                .append("frases.idfrase,")
                .append("autores.autor,")
                .append("frases.frase ")
                .append("FROM frases ")
                .append("INNER JOIN autores ")
                .append("ON frases.idautor=autores.idautor ")
                .append("ORDER BY autores.autor");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = 
                        cn.prepareStatement(sql.toString());
                ResultSet rs = ps.executeQuery()) {

            list = new LinkedList<>();
            while (rs.next()) {
                Object[] reg = new Object[3];

                reg[0] = rs.getInt(1);
                reg[1] = rs.getString(2);
                reg[2] = rs.getString(3);

                list.add(reg);
            }

        } catch (SQLException e) {
            message = e.getMessage();
        }

        return list;
    }

    @Override
    public String frasesIns(Frases frases) {
        sql.delete(0, sql.length())
                .append("INSERT INTO frases(")
                .append("idautor,")
                .append("frase")
                .append(") VALUES(?,?)");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = 
                        cn.prepareStatement(sql.toString())) {

            ps.setInt(1, frases.getIdautor());
            ps.setString(2, frases.getFrase());

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
    public String frasesDel(List<Integer> ids) {
        sql.delete(0, sql.length())
                .append("DELETE FROM frases WHERE idfrase=?");

        try (Connection cn = db.getConnection();
                PreparedStatement ps
                = cn.prepareStatement(sql.toString())) {

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
    public Frases frasesGet(Integer idfrase) {
        Frases frases = null;
        sql.delete(0, sql.length())
                .append("SELECT ")
                .append("idfrase,")
                .append("idautor,")
                .append("frase ")
                .append("FROM frases ")
                .append("WHERE idfrase=?");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = 
                        cn.prepareStatement(sql.toString())) {

            ps.setInt(1, idfrase);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    frases = new Frases();

                    frases.setIdfrase(rs.getInt(1));
                    frases.setIdautor(rs.getInt(2));
                    frases.setFrase(rs.getString(3));

                } else {
                    throw new SQLException("ID: " 
                            + idfrase + " no existe");
                }
            }

        } catch (SQLException e) {
            message = e.getMessage();
        }

        return frases;
    }

    @Override
    public String frasesUpd(Frases frases) {
        sql.delete(0, sql.length())
                .append("UPDATE frases SET ")
                .append("idautor=?,")
                .append("frase=? ")
                .append("WHERE idfrase=?");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = 
                        cn.prepareStatement(sql.toString())) {

            ps.setInt(1, frases.getIdautor());
            ps.setString(2, frases.getFrase());
            ps.setInt(3, frases.getIdfrase());

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

