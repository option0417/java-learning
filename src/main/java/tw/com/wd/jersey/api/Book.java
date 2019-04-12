package tw.com.wd.jersey.api;


import tw.wd.api.rest.service.BookType;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

@Path("api/v1/book")
@Produces("application/json;charset=utf-8")
public interface Book {
    @GET
    @Path("/")
    Response getBooks(@Context HttpHeaders headers, @QueryParam("book_type") BookType bookType);

    @GET
    @Path("/{book_id}")
    Response getBook(@Context HttpHeaders headers, @PathParam("book_id") String bookID);

    @POST
    @Path("/")
    Response createBook(@Context HttpHeaders headers, String body);

    @PUT
    @Path("/{book_id}")
    Response updateBook(@Context HttpHeaders headers, @PathParam("book_id") String bookId, String body);

    @DELETE
    @Path("/{book_id}")
    Response deleteBook(@Context HttpHeaders headers, @PathParam("book_id") String bookID);
}
