package web.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("vpar")//se coloca en el index.xhtml
public class ValidaPar implements Validator{

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object o)
            throws ValidatorException {
        String valor = o.toString().trim();
        if (valor.length() == 0) {
            throw new ValidatorException(
                    new FacesMessage("ingrese entero par"));
        } else {
            try {
                Integer x = Integer.valueOf(valor);
                if ((x % 2) == 1) {
                    throw new ValidatorException(
                            new FacesMessage("debe ser par"));
                }
            } catch (NumberFormatException e) {
                throw new ValidatorException(
                        new FacesMessage("valor entero"));
            }
        }
    }

}
