package uce.edu.web.api.matricula.application;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.matricula.domain.Materia;
import uce.edu.web.api.matricula.infraestructure.MateriaRepository;

@ApplicationScoped
@Transactional
public class MateriaService {
    @Inject
    private MateriaRepository materiaRepository;

    public List<Materia> listarTodas() {
        return materiaRepository.listAll();
    }

    public Materia consultarPorId(Integer id) {
        return this.materiaRepository.findById(id.longValue());
    }

    @Transactional
    public void crear(Materia mate) {
        this.materiaRepository.persist(mate);
    }

    @Transactional
    public void actualizar(Integer id, Materia mat) {
        Materia mate = this.consultarPorId(id);
        mate.nombre = mat.nombre;
        mate.creditos = mat.creditos;
        mate.descripcion = mat.descripcion;
    }

    @Transactional
    public void actualizarParcial(Integer id, Materia mat) {
        Materia mate = this.consultarPorId(id);
        if (mat.nombre != null) {
            mate.nombre = mat.nombre;
        }
        if (mat.creditos != null) {
            mate.creditos = mat.creditos;
        }
        if (mat.descripcion != null) {
            mate.descripcion = mat.descripcion;
        }
    }

    @Transactional
    public void eliminar(Integer id) {
        this.materiaRepository.deleteById(id.longValue());
    }

    public List<Materia> buscarPorCreditos(Integer creditos) {
        return this.materiaRepository.buscarPorCreditos(creditos);
    }

    public List<Materia> buscarPorNombre(String nombre) {
        return this.materiaRepository.buscarPorNombre(nombre);
    }

    public List<Materia> buscarPorTipo(String tipo, Integer semestre) {
        return this.materiaRepository.find("tipo =?1 and semestre =?2", tipo, semestre).list();
    }
}