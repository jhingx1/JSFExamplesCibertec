package dao.impl;

import dao.DaoExamen;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;
import parainfo.sql.ConectaDb;

public class DaoExamenImpl implements DaoExamen {

    private final ConectaDb db;

    public DaoExamenImpl() {
        db = new ConectaDb();
    }

    @Override
    public List<SelectItem> cbCategorias() {
        return lista("SELECT idcategoria, titulo FROM categorias");
    }

    @Override
    public List<SelectItem> cbDepartamentos() {
        return lista("SELECT iddepartamento, departamento FROM departamentos");
    }

    @Override
    public List<SelectItem> cbPeriodistas() {
        return lista("SELECT idperiodista, CONCAT(apellidos,' ',nombres) FROM periodistas");
    }

    // apoyo
    private List<SelectItem> lista(String sql) {
        List<SelectItem> list = new ArrayList<>();

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new SelectItem((Integer) rs.getInt(1), rs.getString(2)));
            }

        } catch (SQLException e) {
        }

        return list;
    }
}
