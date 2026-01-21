package uce.edu.web.api.matricula.application;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.matricula.infraestructure.MateriaRepository;
import uce.edu.web.api.matricula.domain.Materia;

@ApplicationScoped
@Transactional
public class MateriaService  {
    @Inject
    private MateriaRepository materiaRepository;

    public List<Materia> listarTodos() {
        return this.materiaRepository.listAll();
    }

    public Materia consultarPorId(Integer id) {
        return this.materiaRepository.findById(id.longValue());
    }

    @Transactional
    public void crear(Materia mat) {
        this.materiaRepository.persist(mat);
    }

    @Transactional
    public void actualizar(Integer id, Materia mat) {
        Materia materia = this.consultarPorId(id);
        materia.nombre = mat.nombre;
        materia.codigo = mat.codigo;
        materia.creditos = mat.creditos;
        materia.descripcion = mat.descripcion;

    }

    @Transactional
    public void actualizarParcial(Integer id, Materia mat) {
        Materia materia = this.consultarPorId(id);
        if (mat.nombre != null) {
            materia.nombre = mat.nombre;
        }
        if (mat.codigo != null) {
            materia.codigo = mat.codigo;
        }
        if (mat.creditos != null) {
            materia.creditos = mat.creditos;
        }
    }

    @Transactional
    public void eliminar(Integer id) {
        this.materiaRepository.deleteById(id.longValue());
    }

    public Materia buscarMateria(String materia) {
        return materiaRepository.find("lower(trim(nombre)) = ?1", materia.trim().toLowerCase()).firstResult();
    }

    @Transactional
    public void borrarPorNombre(String nombre) {
        this.materiaRepository.delete("nombre = ?1", nombre);
    }
}
