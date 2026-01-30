package uce.edu.web.api.matricula.application.representation;

public class LinkDto {
    public String href;
    public String rel;

    //constructor por defecto (porque crea otro con parametros???)
    public LinkDto() {
    }
    public LinkDto(String href, String rel) {
        this.href = href;
        this.rel = rel;
    }

}
