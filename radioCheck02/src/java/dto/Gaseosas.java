package dto;

public class Gaseosas {

    private Integer idgaseosa;
    private String gaseosa;

    public Gaseosas(Integer idgaseosa, String gaseosa) {
        this.idgaseosa = idgaseosa;
        this.gaseosa = gaseosa;
    }

    public Integer getIdgaseosa() {
        return idgaseosa;
    }

    public void setIdgaseosa(Integer idgaseosa) {
        this.idgaseosa = idgaseosa;
    }

    public String getGaseosa() {
        return gaseosa;
    }

    public void setGaseosa(String gaseosa) {
        this.gaseosa = gaseosa;
    }
}
