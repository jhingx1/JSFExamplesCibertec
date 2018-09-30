package web.managedbean;

import java.util.Locale;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import service.Circulo;


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

    private double radio;
    private String msg;

    public double getRadio() {
        return radio;
    }

    public void setRadio(double radio) {
        this.radio = radio;
    }

    public String getMsg() {
        double area = circulo.area(getRadio());
        msg = String.format(Locale.US, "área de círculo: %1.2f", area);
        return msg;
    }

}
