package web.managedbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.model.chart.PieChartModel;

@ManagedBean(name = "chartBean")
@RequestScoped
public class ManagedBeanChartBean {

    private PieChartModel pieModel;//Atributo de tipo pieModel

    public ManagedBeanChartBean() {
        createPieModel();
    }

    public PieChartModel getPieModel() {
        return pieModel;
    }

    private void createPieModel() {
        pieModel = new PieChartModel();
        //Añadiendo al modelo
        pieModel.set("Brand 1", 540);
        pieModel.set("Brand 2", 325);
        pieModel.set("Brand 3", 702);
        pieModel.set("Brand 4", 421);
    }
}
