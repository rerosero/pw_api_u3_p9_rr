package uce.edu.web.api.matricula.infraestructure;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import uce.edu.web.api.matricula.domain.Hijo;

@ApplicationScoped
public class HijoRepository implements PanacheRepository<Hijo> {
    // si tengo que construir las consultas
    public List<Hijo> buscarPorIdEstudiantes(Integer idEstudiante) {
        return find("estudiante.id = ?1", idEstudiante).list();
    }
}
