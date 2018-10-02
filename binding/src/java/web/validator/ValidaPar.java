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
        //Capturando el valor que se pasa desde la vista.
        String valor = o.toString().trim();
        if (valor.length() == 0) {
            throw new ValidatorException(
                    new FacesMessage("ingrese entero par"));
        } else {
            try {
                //Uso de Integer.valueOf(valor), para pasar de String a su forma de entero
                //ademas esto se puede usar como un validador de que se esta enviado un
                //entero.
                Integer x = Integer.valueOf(valor);//pasando a entero
                if ((x % 2) == 1) {
                    throw new ValidatorException(
                            new FacesMessage("debe ser par"));
                }
            } catch (NumberFormatException e) {
                //Aquie se ve como se captura si se ejecuta un error
                //significa que no se esta pasando un numero.
                throw new ValidatorException(
                        new FacesMessage("valor entero"));
            }
        }
        
    }

}
