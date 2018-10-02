
package web.managedbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartSeries;

@ManagedBean(name = "lineChartBean")
@RequestScoped
public class ManagedBeanLineChartBean {

    private CartesianChartModel categoryModel;
    private CartesianChartModel linearModel;

    public ManagedBeanLineChartBean() {
        createCategoryModel();
        createLinearModel();
    }

    public CartesianChartModel getCategoryModel() {
        return categoryModel;
    }

    public CartesianChartModel getLinearModel() {
        return linearModel;
    }

    private void createCategoryModel() {
        categoryModel = new CartesianChartModel();
        ChartSeries boys = new ChartSeries();
        boys.setLabel("Boys"); //Nombre de la linea
        boys.set("2004", 120);
        boys.set("2005", 100);
        boys.set("2006", 44);
        boys.set("2007", 150);
        boys.set("2008", 25);
        ChartSeries girls = new ChartSeries();
        girls.setLabel("Girls");
        girls.set("2004", 52);
        girls.set("2005", 60);
        girls.set("2006", 110);
        girls.set("2007", 135);
        girls.set("2008", 120);
        categoryModel.addSeries(boys);//Añadiendo a la vista
        categoryModel.addSeries(girls);
    }

    private void createLinearModel() {
        //Crear las lineas cartecianas
        linearModel = new CartesianChartModel();
        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Series 1");//añadiendo el nombre de la linea.
        series1.set(1, 2);//añadiendo los valores a la linea.
        series1.set(2, 1);
        series1.set(3, 3);
        series1.set(4, 6);
        series1.set(5, 8);
        LineChartSeries series2 = new LineChartSeries();
        series2.setLabel("Series 2");
        series2.setMarkerStyle("diamond"); //Añadiendo un estilo
        series2.set(1, 6);
        series2.set(2, 3);
        series2.set(3, 2);
        series2.set(4, 7);
        series2.set(5, 9);
        linearModel.addSeries(series1); //Añadiendo al modelo
        linearModel.addSeries(series2);
    }
}
