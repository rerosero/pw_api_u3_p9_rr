package uce.edu.web.api.matricula.interfaces;

import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.matricula.application.EstudianteService;
import uce.edu.web.api.matricula.application.HijoService;
import uce.edu.web.api.matricula.application.representation.EstudianteRepresentation;
import uce.edu.web.api.matricula.application.representation.HijoRepresentation;
import uce.edu.web.api.matricula.application.representation.LinkDto;


@Path("/estudiantes")
public class EstudianteResource {
    @Inject
    private EstudianteService estudianteService;
    @Inject
    private HijoService hijoService;

    @Context
    private UriInfo uriInfo;

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({ "admin", "user" })
    public List<EstudianteRepresentation> listarTodos() {
        System.out.println("XXXXXXXXXXX  Listar todos los estudiantes XXXXXXXXXXX ");
        List<EstudianteRepresentation> lista = new ArrayList<>();
        for(EstudianteRepresentation er: this.estudianteService.listarTodos()){
            lista.add(this.construirLinks(er));
        }
        return lista;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public EstudianteRepresentation consultarPorId(@PathParam("id") Integer id) {
        return this.construirLinks(this.estudianteService.consultarPorId(id));
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public Response guardar(EstudianteRepresentation estu) {
        this.estudianteService.crear(estu);
        return Response.status(Response.Status.CREATED).entity(estu).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    // consume un objeto estudiantes y produce un objeto response
    public Response actualizar(@PathParam("id") Integer id, EstudianteRepresentation est) {
        this.estudianteService.actualizar(id, est);
        est.id = id;
        return Response.ok(this.construirLinks(est)).build();
    }

    @PATCH
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public void actualizarParcial(@PathParam("id") Integer id, EstudianteRepresentation est) {
        this.estudianteService.actualizarParcial(id, est);
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("admin")
    public void borrar(@PathParam("id") Integer id) {
        this.estudianteService.eliminar(id);
    }

    @GET
    @Path("/provincia/genero")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public List<EstudianteRepresentation> buscarPorProvincia(@QueryParam("provincia") String provincia,
            @QueryParam("genero") String genero) {
        System.out.println("XXXXXXXXXXX Listar por provincia  y genero XXXXXXXXX");
        List<EstudianteRepresentation> lista = new ArrayList<>();
        for (EstudianteRepresentation er : this.estudianteService.buscarPorProvincia(provincia, genero)) {
            lista.add(this.construirLinks(er));
        }
        return lista;
    }

    @GET
    @Path("/{id}/hijos")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public List<HijoRepresentation> buscarPorIdEstudiantes(@PathParam("id") Integer id) {
        return this.hijoService.buscarPorIdEstudiante(id);
    }

    private EstudianteRepresentation construirLinks(EstudianteRepresentation er) {
        String self = this.uriInfo.getBaseUriBuilder().path(EstudianteResource.class).path(String.valueOf(er.id))
                .build().toString();

                String hijos = this.uriInfo.getBaseUriBuilder().path(EstudianteResource.class)
                .path(String.valueOf(er.id)).path("hijos").build().toString();

        er.links = List.of(new LinkDto(self, "self"), new LinkDto(hijos, "hijos"));
        return er;
    }
}
