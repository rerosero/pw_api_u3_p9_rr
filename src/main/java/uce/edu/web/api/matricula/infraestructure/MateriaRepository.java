package uce.edu.web.api.matricula.infraestructure;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import uce.edu.web.api.matricula.domain.Materia;
import java.util.List;

@ApplicationScoped
public class MateriaRepository implements PanacheRepository<Materia> {

    public List<Materia> buscarPorCreditos(Integer creditos){
        return list("creditos", creditos);
    }
    public List<Materia> buscarPorNombre(String nombre){
        return list("lower(nombre) like lower(?1)", "%" + nombre + "%");
    }
} 
