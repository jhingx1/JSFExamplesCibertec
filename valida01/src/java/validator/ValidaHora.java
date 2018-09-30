/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validator;

import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("vhora")
public class ValidaHora implements Validator {

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object o)
            throws ValidatorException {
        String hora = o.toString().trim();
        String data[] = null;
        if (hora.length() == 0) {
            throw new ValidatorException(
                    new FacesMessage("ingrese hora!"));
        } else {
            //String erTexto = "\\d\\d:\\d\\d$";
            String erTexto = "^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$";//^([01]?[0-9]|2[0-3]):[0-5][0-9]$
            boolean ok = Pattern.matches(erTexto, hora);
            if (!ok) {
                String msg_error="";
                data = hora.split(":");
                if (data != null && data.length == 2) {
                    if (isNumeric(data[0]) && Integer.parseInt(data[0]) > 24) {
                        msg_error = "Maximo 24hrs";
                    } else if (isNumeric(data[1]) && Integer.parseInt(data[1]) >= 60) {
                        msg_error = "Maximo 59min.";
                    }
                    else
                        msg_error ="formato hh:mm es en numeros";
                }
                else
                    msg_error ="formato debe ser hh:mm";
                
                throw new ValidatorException(
                        new FacesMessage(msg_error));
            }

        }
    }
    
    public static boolean isNumeric(String cadena) {

        boolean resultado;

        try {
            Integer.parseInt(cadena);//cadena ejm:12
            resultado = true;
        } catch (NumberFormatException excepcion) {
            resultado = false;
        }

        return resultado;
    }
}
