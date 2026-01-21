package uce.edu.web.api.matricula.interfaces;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import uce.edu.web.api.matricula.application.MateriaService;
import uce.edu.web.api.matricula.domain.Materia;

@Path("/materias")
public class MateriaResource {
    @Inject
    private MateriaService materiaService;
    @GET
    @Path("/todos")
    public Object listarTodos(){
        return this.materiaService.listarTodos();
    }
    @GET
    @Path("/consultarPorId/{id}")
    public Materia consultarPorId(@PathParam("id") Integer id){
        return this.materiaService.consultarPorId(id);
    }
    @POST
    @Path("/crear")
    public Materia guardar(Materia mat){
        this.materiaService.crear(mat);
        return mat;
    }
    @PUT
    @Path("/actualizar/{id}")
    @jakarta.ws.rs.Consumes("application/json")
    @jakarta.ws.rs.Produces("application/json")
    public Materia actualizar(@PathParam("id") Integer id, Materia mat){
        this.materiaService.actualizar(id, mat);
        return this.materiaService.consultarPorId(id);
    }
    @PATCH
    @Path("/actualizarParcial/{id}")
    @jakarta.ws.rs.Consumes("application/json")
    @jakarta.ws.rs.Produces("application/json")
    public Materia actualizarParcial(@PathParam("id") Integer id, Materia mat){
        this.materiaService.actualizarParcial(id, mat);
        return this.materiaService.consultarPorId(id);
    }
    @DELETE
    @Path("/borrar/{id}")
    @jakarta.ws.rs.Produces("application/json")
    public jakarta.ws.rs.core.Response borrar(@PathParam("id") Integer id){
        this.materiaService.eliminar(id);
        return jakarta.ws.rs.core.Response.ok().build();
    }
    @GET
    @Path("/buscarPorNombre/{nombre}")
    public Materia buscarPorNombre(@PathParam("nombre") String nombre){
        return this.materiaService.buscarMateria(nombre);
    }
    
    @DELETE
    @Path("/borrarPorNombre/{nombre}")
    @jakarta.ws.rs.Produces("application/json")
    public jakarta.ws.rs.core.Response borrarPorNombre(@PathParam("nombre") String nombre){
        this.materiaService.borrarPorNombre(nombre);
        return jakarta.ws.rs.core.Response.ok().build();
    }
}
