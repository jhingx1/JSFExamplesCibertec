package service;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "sIGV")
@RequestScoped
public class IGV {

    public double igv(double r) {
        double a = r*18/100;
        return a;
    }

}
