package web.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "data")
@RequestScoped
public class ManagedBeanData {

    private Double valor1;
    private Double valor2;     
    private Double resultado;

    public ManagedBeanData() {
    }
    
    public Double getValor1() {
        return valor1;
    }

    public void setValor1(Double valor1) {
        this.valor1 = valor1;
    }

    public Double getValor2() {
        return valor2;
    }

    public void setValor2(Double valor2) {
        this.valor2 = valor2;
    }

    public Double getResultado() {
        return resultado;
    }

    public void setResultado(Double resultado) {
        this.resultado = resultado;
    }

    public String suma() {
        resultado = valor1 + valor2;
        return "result";
    }
    
    public String resta() {
        resultado = valor1 - valor2;
        return "result";
    }
    
    public String multi() {
        resultado = valor1 * valor2;
        return "result";
    }
    
    public String divi() {
        resultado = valor1 / valor2;
        return "result";
    }
    
    public String navega() {
       /*
        if(op.equals("+"))
            resultado = valor1 + valor2;
        else if(op.equals("-"))
            resultado = valor1 - valor2;
        else if(op.equals("*"))
            resultado = valor1 * valor2;
        else if(op.equals("/"))
            resultado = valor1 / valor2;
        */
        return "result";
        
    }

}
