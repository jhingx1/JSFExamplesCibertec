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

@FacesValidator("vcelular")
public class ValidaCelular implements Validator {

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object o)
            throws ValidatorException {
        String celular = o.toString().trim();
        if (celular.length() == 0) {
            throw new ValidatorException(
                    new FacesMessage("ingrese celular!"));
        } else {
            String erTexto = "^9\\d\\d-\\d\\d\\d-\\d\\d\\d$";
            boolean ok = Pattern.matches(erTexto, celular);
            if (!ok) {
                throw new ValidatorException(
                        new FacesMessage("formato debe ser 9##-###-###"));
            }
        }
    }
}
