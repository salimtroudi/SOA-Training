package webservices;

import entities.UniteEnseignement;
import metiers.UniteEnseignementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
@Path("/ue")
public class UERestApi {
    public UniteEnseignementBusiness helper=new UniteEnseignementBusiness();

    //methode => web service => rest api
    //URI
    //getAllUEs
    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll()
    {
        return Response
                .status(200)
                .entity(this.helper.getListeUE())
                .build();

    }
    //methode add
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUE(UniteEnseignement ue) {
        if (helper.addUniteEnseignement(ue)) {
            return Response.status(Response.Status.CREATED).entity("Ajout effectue avec succes").build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Echec de l'ajout").build();
    }

    @GET
    @Path("/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUE(@PathParam("code") int code) {
        UniteEnseignement ue = helper.getUEByCode(code);
        if (ue != null) {
            return Response.status(Response.Status.OK).entity(ue).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    @Path("/{code}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUE(@PathParam("code") int code, UniteEnseignement ue) {
        if (helper.updateUniteEnseignement(code, ue)) {
            return Response.status(Response.Status.OK).entity("Mise a jour effectuee avec succes").build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("UE non trouvee").build();
    }

    @DELETE
    @Path("/{code}")
    public Response deleteUE(@PathParam("code") int code) {
        if (helper.deleteUniteEnseignement(code)) {
            return Response.status(Response.Status.OK).entity("Suppression effectuee avec succes").build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("UE non trouvee").build();
    }
}
