package br.com.fiap.infrastructure.api.rest;

import br.com.fiap.domain.model.Author;
import br.com.fiap.interfaces.AuthorController;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import br.com.fiap.domain.exceptions.EntidadeNaoLocalizada;


@Path("/authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthorRestController {

    private final AuthorController authorController;

    @Inject
    public AuthorRestController(AuthorController authorController) {
        this.authorController = authorController;
    }

    @POST
    public Response criar(Author authorInput){
        try{
            Author author = this.authorController.criar(authorInput);
            return Response.status(Response.Status.CREATED).entity(author).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") int id){
        try{
            Author author = this.authorController.buscarPorID(id);
            return Response.ok(author).build();
        } catch (EntidadeNaoLocalizada e){
            return Response.status(Response.Status.NOT_FOUND).build(); // 404 só quando realmente não existe
        } catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build(); // 500 pros demais erros
        }
    }

    @PUT
    public Response atualizar(Author authorInput) {
        try {
            Author author = this.authorController.atualizar(authorInput);
            return Response.status(Response.Status.ACCEPTED).entity(author).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (EntidadeNaoLocalizada e) {
            throw new RuntimeException(e);
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id) {
        try {
            this.authorController.deletar(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (EntidadeNaoLocalizada e) {
            throw new RuntimeException(e);
        }
    }




}
