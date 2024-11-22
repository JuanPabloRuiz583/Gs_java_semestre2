package org.example.resource;

import org.example.Services.CarroService;
import org.example.Services.CarroServiceFactory;
import org.example.Services.ClienteService;
import org.example.Services.ClienteServiceFactory;
import org.example.dtos.CarroDTO;
import org.example.models.Carro;
import org.example.models.Cliente;
import org.example.models.Marca;
import org.example.models.Modelo;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("/rest")
public class CarroResouce {
    private final CarroService carroService = CarroServiceFactory.create();
    private final ClienteService clienteService = ClienteServiceFactory.create();

    @POST
    @Path("/carros")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCarro(CarroDTO carroDTO) {
        try {
            // Verificar se o cliente existe
            Cliente cliente = clienteService.buscarClientePorEmail(carroDTO.getClienteEmail());
            if (cliente == null) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Cliente não encontrado").build();
            }

            // Verificar se o carro com o mesmo ID já existe
            Carro carroExistente = carroService.buscarCarroPorId(carroDTO.getId());
            if (carroExistente != null) {
                return Response.status(Response.Status.CONFLICT).entity("Carro com o mesmo ID já existe").build();
            }

            Carro carro = new Carro(
                    carroDTO.getId(),
                    Marca.valueOf(carroDTO.getMarca()),
                    Modelo.valueOf(carroDTO.getModelo()),
                    carroDTO.getCapacidadeBateria(),
                    carroDTO.getNivelBateria(),
                    carroDTO.getAutonomia(),
                    cliente
            );
            carroService.cadastrarCarro(carro);
            return Response.status(Response.Status.CREATED).entity(carroDTO).build();
        } catch (SQLException | IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/carros/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCarro(@PathParam("id") int id) {
        try {
            Carro carro = carroService.buscarCarroPorId(id);
            if (carro == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            CarroDTO carroDTO = new CarroDTO(
                    carro.getId(),
                    carro.getMarca().name(),
                    carro.getModelo().name(),
                    carro.getCapacidadeBateria(),
                    carro.getNivelBateria(),
                    carro.getAutonomia(),
                    carro.getCliente().getEmail()
            );
            return Response.ok(carroDTO).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/carros/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCarro(@PathParam("id") int id, CarroDTO carroDTO) {
        try {
            // Assuming you have additional parameters for Cliente
            Cliente cliente = new Cliente(carroDTO.getClienteEmail(), "defaultName", "defaultSurname");
            Carro carro = new Carro(
                    id,
                    Marca.valueOf(carroDTO.getMarca()),
                    Modelo.valueOf(carroDTO.getModelo()),
                    carroDTO.getCapacidadeBateria(),
                    carroDTO.getNivelBateria(),
                    carroDTO.getAutonomia(),
                    cliente
            );
            carroService.atualizarCarro(carro);
            return Response.ok(carroDTO).build();
        } catch (SQLException | IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/carros/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCarro(@PathParam("id") int id) {
        try {
            carroService.removerCarro(id);
            return Response.noContent().build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}