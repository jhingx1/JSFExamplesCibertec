package web.managedbean;

import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "mbcalendar")
@RequestScoped
public class ManagedBeanCalendar {

    //Fechas del sistema
    private Date date1 = new Date(System.currentTimeMillis());
    private Date date2 = new Date(System.currentTimeMillis());
    private Date date3 = new Date(System.currentTimeMillis());

    public ManagedBeanCalendar() {
    }

    public Date getDate1() {
        return date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    public Date getDate2() {
        return date2;
    }

    public void setDate2(Date date2) {
        this.date2 = date2;
    }

    public Date getDate3() {
        return date3;
    }

    public void setDate3(Date date3) {
        this.date3 = date3;
    }

    public String navega() {
        return "result";
    }
}
