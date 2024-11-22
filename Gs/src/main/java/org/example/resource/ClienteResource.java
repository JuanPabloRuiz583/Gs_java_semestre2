package org.example.resource;

import org.example.Services.ClienteService;
import org.example.Services.ClienteServiceFactory;
import org.example.dtos.ClienteDTO;
import org.example.models.Cliente;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("/rest")
public class ClienteResource {
    private final ClienteService clienteService = ClienteServiceFactory.create();

    @POST
    @Path("/clientes")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCliente(ClienteDTO clienteDTO) {
        try {
            Cliente cliente = new Cliente(clienteDTO.getNome(), clienteDTO.getEmail(), clienteDTO.getSenha());
            clienteService.cadastrarCliente(cliente);
            return Response.status(Response.Status.CREATED).entity(clienteDTO).build();
        } catch (SQLException | IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/clientes/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCliente(@PathParam("email") String email) {
        try {
            Cliente cliente = clienteService.buscarClientePorEmail(email);
            if (cliente == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            ClienteDTO clienteDTO = new ClienteDTO();
            clienteDTO.setNome(cliente.getNome());
            clienteDTO.setEmail(cliente.getEmail());
            clienteDTO.setSenha(cliente.getSenha());
            return Response.ok(clienteDTO).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }



    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response verificarLogin(ClienteDTO clienteDTO) {
        try {
            Cliente clienteEmail = clienteService.buscarClientePorEmail(clienteDTO.getEmail());
            if (clienteEmail != null && clienteEmail.getSenha().equals(clienteDTO.getSenha())) {
                return Response.ok(clienteDTO).build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED).entity("Email ou senha incorretos").build();
            }
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }



    @PUT
    @Path("/clientes/{email}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCliente(@PathParam("email") String email, ClienteDTO clienteDTO) {
        try {
            Cliente cliente = new Cliente(clienteDTO.getNome(), email, clienteDTO.getSenha());
            clienteService.atualizarCliente(cliente);
            return Response.ok(clienteDTO).build();
        } catch (SQLException | IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/clientes/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCliente(@PathParam("email") String email) {
        try {
            clienteService.removerCliente(email);
            return Response.noContent().build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}