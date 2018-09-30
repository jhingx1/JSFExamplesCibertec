package service;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

//Para la insercion
@ManagedBean(name = "sCirculo")
@RequestScoped
public class Circulo {

    public double area(double r) {
        double a = Math.PI * Math.pow(r, 2);
        return a;
    }

}
