package web.managedbean;

import java.util.Locale;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import service.Circulo;
import service.IGV;

@ManagedBean(name = "mbData")
@RequestScoped
public class ManagedBeanData {

    /**
     * Creates a new instance of ManagedBeanData
     */
    public ManagedBeanData() {
    }

    // injection
    @ManagedProperty(value = "#{sCirculo}")
    private Circulo circulo;

    public void setCirculo(Circulo circulo) {
        this.circulo = circulo;
    } // fin
    
    @ManagedProperty(value = "#{sIGV}")
    private IGV igv;
    public void setIgv(IGV igv) {
        this.igv = igv;
    }

    
    private double radio;
    private String msg;
    private String msg2;
    
    private double precio;
    private double total;
    
    private double area;


    public double getRadio() {
        return radio;
    }

    public void setRadio(double radio) {
        this.radio = radio;
    }

    public String getMsg() {
        
        //area = circulo.area(getRadio());
        
        //msg = String.format(Locale.US, "área de círculo: %1.2f", area);
        //total = precio + igv;
        total = precio + getIgv().igv(getPrecio());
        //igv
        msg = String.valueOf(getIgv().igv(getPrecio()));
        
        return msg;
    }
    //He creado otro metodo para mostrar el area del circulo
    public String getMsg2() {
        area = circulo.area(getRadio());
        msg2 = String.format(Locale.US, "área de círculo: %1.2f", area);
        return msg2;
    }
  
    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public IGV getIgv() {
        return igv;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    
    
}
