package dao;

import java.util.List;
import javax.faces.model.SelectItem;

public interface DaoExamen {
    
    public List<SelectItem> cbCategorias();
    
    public List<SelectItem> cbDepartamentos();
    
    public List<SelectItem> cbPeriodistas();
}
