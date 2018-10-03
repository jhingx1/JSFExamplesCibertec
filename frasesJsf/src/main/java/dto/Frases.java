package dto;

public class Frases {

    private Integer idfrase;
    private Integer idautor;
    private String frase;

    public Frases() {
    }

    public String getFrase() {
        return frase;
    }

    public void setFrase(String frase) {
        this.frase = frase;
    }

    public Integer getIdautor() {
        return idautor;
    }

    public void setIdautor(Integer idautor) {
        this.idautor = idautor;
    }

    public Integer getIdfrase() {
        return idfrase;
    }

    public void setIdfrase(Integer idfrase) {
        this.idfrase = idfrase;
    }
}

