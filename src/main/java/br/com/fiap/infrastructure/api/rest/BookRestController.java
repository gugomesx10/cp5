package br.com.fiap.infrastructure.api.rest;

import br.com.fiap.domain.model.Book;
import br.com.fiap.interfaces.BookController;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import br.com.fiap.domain.exceptions.EntidadeNaoLocalizada;

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookRestController {

    private final BookController bookController;

    @Inject
    public BookRestController(BookController bookController) {
        this.bookController = bookController;
    }

    @POST
    public Response criar(Book bookInput){
        try{
            Book book = this.bookController.criar(bookInput);
            return Response.status(Response.Status.CREATED).entity(book).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") int id){
        try{
            Book book = this.bookController.buscarPorID(id);
            return Response.ok(book).build();
        } catch (EntidadeNaoLocalizada e){
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @PUT
    public Response atualizar(Book bookInput) {
        try {
            Book book = this.bookController.atualizar(bookInput);
            return Response.status(Response.Status.ACCEPTED).entity(book).build();
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
            this.bookController.deletar(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (EntidadeNaoLocalizada e) {
            throw new RuntimeException(e);
        }
    }



}