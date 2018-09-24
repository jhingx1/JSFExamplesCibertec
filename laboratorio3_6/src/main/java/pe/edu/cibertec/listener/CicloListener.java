/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.cibertec.listener;

import java.util.logging.Logger;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

/**
 *
 * @author portatil
 */
public class CicloListener implements PhaseListener{
    
    private final static Logger LOGGER=Logger.getLogger(CicloListener.class.getName());
    
    @Override
    public void afterPhase(PhaseEvent event) {
       LOGGER.info("TERMINO LA FASE ".concat(event.getPhaseId().toString()));
    }

    @Override
    public void beforePhase(PhaseEvent event) {
       LOGGER.info("ANTES DE INICIAR LA FASE ".concat(event.getPhaseId().toString()));
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RENDER_RESPONSE;
    }
    
}
