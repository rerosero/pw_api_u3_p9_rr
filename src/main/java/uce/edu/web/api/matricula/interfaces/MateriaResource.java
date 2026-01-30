package uce.edu.web.api.matricula.interfaces;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import uce.edu.web.api.matricula.application.MateriaService;
import uce.edu.web.api.matricula.domain.Materia;

@Path("/materias")
public class MateriaResource {
    @Inject
    private MateriaService materiaService;

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Materia> listarTodas() {
        return this.materiaService.listarTodas();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Materia consultarPorId(@PathParam("id") Integer idem) {
        return this.materiaService.consultarPorId(idem);
    }

    @POST
    @Path("")
    public Response guardar(Materia mate) {
        this.materiaService.crear(mate);
        return Response.status(Response.Status.CREATED).entity(mate).build();
    }

    @PUT
    @Path("/{id}")
    public Response actualizar(@PathParam("id") Integer id, Materia mat) {
        this.materiaService.actualizar(id, mat);
        return Response.status(209).entity(null).build();
    }

    @PATCH
    @Path("/{id}")
    public void actualizarParcial(@PathParam("id") Integer id, Materia mat) {
        this.materiaService.actualizarParcial(id, mat);
    }

    @DELETE
    @Path("/{id}")
    public void borrar(@PathParam("id") Integer id) {
        this.materiaService.eliminar(id);
    }

    @GET
    @Path("/{creditos}")
    public List<Materia> buscarPorCreditos(@PathParam("creditos") Integer creditos) {
        return this.materiaService.buscarPorCreditos(creditos);
    }

    @GET
    @Path("/{nombre}")
    public List<Materia> buscarPorNombre(@PathParam("nombre") String nombre) {
        return this.materiaService.buscarPorNombre(nombre);
    }

    @GET
    @Path("/tipo/semestre")

    public List<Materia> buscarPorTipo(@QueryParam("tipo") String tipo, @QueryParam("semestre") Integer semestre) {
        System.out.println("XXXXXXXXXX Listas por materias por tipo y semestre XXXXXXXXXX");
        return this.materiaService.buscarPorTipo(tipo, semestre);
    }
}