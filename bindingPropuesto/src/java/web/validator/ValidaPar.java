package web.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("vcadena")
public class ValidaPar implements Validator{

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object o)
            throws ValidatorException {
        
        String valor = o.toString().trim();//el valor de la cadena
        if(!valor.equals("aaa")){
            throw new ValidatorException(
                    new FacesMessage("ingrese el valor correcto"));
        }
    }

}
