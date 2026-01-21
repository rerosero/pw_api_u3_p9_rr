package uce.edu.web.api.matricula.infraestructure;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import uce.edu.web.api.matricula.domain.Estudiante;

// Con panache el crud  ya esta implementado
@ApplicationScoped
public class EstudianteRepository implements PanacheRepository<Estudiante> {

}