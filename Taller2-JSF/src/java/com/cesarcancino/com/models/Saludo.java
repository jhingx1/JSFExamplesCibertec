package com.cesarcancino.com.models;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class Saludo {

    
    public Saludo() {
    }
    
    private String texto="Hola desde el Managed Beans";

    public String getTexto() {
        return texto;
    }
    public void setTexto(String texto) {
        this.texto = texto;
    }

}
