package uce.edu.web.api.matricula.application;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.matricula.infraestructure.EstudianteRepository;
import uce.edu.web.api.matricula.application.representation.EstudianteRepresentation;
import uce.edu.web.api.matricula.domain.Estudiante;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Transactional
public class EstudianteService {

    @Inject
    private EstudianteRepository estudianteRepository;

    public List<EstudianteRepresentation> listarTodos() {
        List<Estudiante> lista = this.estudianteRepository.listAll();
        List<EstudianteRepresentation> listaEstuR = new ArrayList<>();
        
        for (Estudiante est : lista) {
            EstudianteRepresentation estuR = this.mapperToER(est);
            listaEstuR.add(estuR);
        }
        
        return listaEstuR;
    }

    public EstudianteRepresentation consultarPorId(Integer id) {
        Estudiante est = this.estudianteRepository.findById(id.longValue());
        return this.mapperToER(est);
    }

    @Transactional
    public void crear(EstudianteRepresentation estuR) {
        Estudiante est = this.mapperToEstudiante(estuR);
        this.estudianteRepository.persist(est);
        estuR.id = est.id;
    }

    @Transactional
    public void actualizar(Integer id, EstudianteRepresentation estuR) {
        // Obtenemos la entidad gestionada por JPA para que el Dirty Checking funcione
        Estudiante est = this.estudianteRepository.findById(id.longValue());
        
        est.apellido = estuR.apellido;
        est.nombre = estuR.nombre;
        est.fechaNacimiento = estuR.fechaNacimiento;
        est.provincia = estuR.provincia;
        est.genero = estuR.genero;
    }

    @Transactional
    public void actualizarParcial(Integer id, EstudianteRepresentation estuR) {
        Estudiante est = this.estudianteRepository.findById(id.longValue());
        
        if (estuR.nombre != null) {
            est.nombre = estuR.nombre;
        }
        if (estuR.apellido != null) {
            est.apellido = estuR.apellido;
        }
        if (estuR.fechaNacimiento != null) {
            est.fechaNacimiento = estuR.fechaNacimiento;
        }
        if (estuR.provincia != null) {
            est.provincia = estuR.provincia;
        }
        if (estuR.genero != null) {
            est.genero = estuR.genero;
        }
    }

    @Transactional
    public void eliminar(Integer id) {
        this.estudianteRepository.deleteById(id.longValue());
    }

    public List<EstudianteRepresentation> buscarPorProvincia(String provincia, String genero) {
        List<Estudiante> lista = this.estudianteRepository.find("provincia =?1 and genero =?2", provincia, genero).list();
        List<EstudianteRepresentation> listaEstuR = new ArrayList<>();
        
        for (Estudiante est : lista) {
            listaEstuR.add(this.mapperToER(est));
        }
        
        return listaEstuR;
    }

   private EstudianteRepresentation mapperToER(Estudiante est) {
      EstudianteRepresentation estuR = new EstudianteRepresentation();
      estuR.id = est.id;
      estuR.nombre = est.nombre;
      estuR.apellido = est.apellido;
      estuR.fechaNacimiento = est.fechaNacimiento;
      estuR.genero = est.genero;
      estuR.provincia = est.provincia;
      return estuR;
   }

   private Estudiante mapperToEstudiante(EstudianteRepresentation estuR) {
      Estudiante estu = new Estudiante();
      estu.id = estuR.id;
      estu.nombre = estuR.nombre;
      estu.apellido = estuR.apellido;
      estu.fechaNacimiento = estuR.fechaNacimiento;
      estu.genero = estuR.genero;
      estu.provincia = estuR.provincia;
      return estu;
   }

}