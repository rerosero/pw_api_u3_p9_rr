package uce.edu.web.api.matricula.application.representation;

import java.time.LocalDateTime;
import java.util.List;

public class EstudianteRepresentation {
    public Integer id;
    public String nombre;
    public String apellido;
    public LocalDateTime fechaNacimiento;
    public String provincia;
    public String genero;
    //Agregar lista de hijos (la idea)
    //http://localhost:8080/......./estudiantes/1/hijos
    public List<LinkDto> links;
}
