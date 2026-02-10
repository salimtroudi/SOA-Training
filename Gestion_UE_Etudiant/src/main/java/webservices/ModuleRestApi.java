package webservices;

import entities.Module;
import metiers.ModuleBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/modules")
public class ModuleRestApi {

    private ModuleBusiness helper = new ModuleBusiness();

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllModules() {
        return Response.status(Response.Status.OK)
                .entity(helper.getAllModules())
                .build();
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addModule(Module module) {
        if (helper.addModule(module)) {
            return Response.status(Response.Status.CREATED).entity("Ajout effectue avec succes").build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Echec de l'ajout (verifier l'UE associee)").build();
    }

    @GET
    @Path("/{matricule}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getModule(@PathParam("matricule") String matricule) {
        Module module = helper.getModuleByMatricule(matricule);
        if (module != null) {
            return Response.status(Response.Status.OK).entity(module).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Module non trouve").build();
    }

    @PUT
    @Path("/{matricule}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateModule(@PathParam("matricule") String matricule, Module module) {
        if (helper.updateModule(matricule, module)) {
            return Response.status(Response.Status.OK).entity("Mise a jour effectuee avec succes").build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Module non trouve").build();
    }

    @DELETE
    @Path("/{matricule}")
    public Response deleteModule(@PathParam("matricule") String matricule) {
        if (helper.deleteModule(matricule)) {
            return Response.status(Response.Status.OK).entity("Suppression effectuee avec succes").build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Module non trouve").build();
    }
}
