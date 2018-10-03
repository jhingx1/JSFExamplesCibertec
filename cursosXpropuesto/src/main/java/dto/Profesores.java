package dto;

import java.sql.Date;
import java.sql.Timestamp;

public class Profesores {

    private Integer idprofesores;
    private String nombreprofesores;
    private String carrera;
    private Date fechaingreso;
    private String tipocontrato;

    public Profesores() {
    }

    public Integer getIdprofesores() {
        return idprofesores;
    }

    public void setIdprofesores(Integer idprofesores) {
        this.idprofesores = idprofesores;
    }

    public String getNombreprofesores() {
        return nombreprofesores;
    }

    public void setNombreprofesores(String nombreprofesores) {
        this.nombreprofesores = nombreprofesores;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public Date getFechaingreso() {
        return fechaingreso;
    }

    public void setFechaingreso(Date fechaingreso) {
        this.fechaingreso = fechaingreso;
    }

    public String getTipocontrato() {
        return tipocontrato;
    }

    public void setTipocontrato(String tipocontrato) {
        this.tipocontrato = tipocontrato;
    }

    
    
}
