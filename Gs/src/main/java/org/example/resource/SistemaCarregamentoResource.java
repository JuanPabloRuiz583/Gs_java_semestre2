package org.example.resource;

import org.example.Services.SistemaCarregamentoService;
import org.example.Services.SistemaCarregamentoServiceFactory;
import org.example.dtos.SistemaCarregamentoDTO;
import org.example.models.SistemaCarregamento;
import org.example.models.PlacaSolar;
import org.example.models.Carro;
import org.example.Daos.CarroDaoImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Path("/rest")
public class SistemaCarregamentoResource {
    private final SistemaCarregamentoService sistemaCarregamentoService = SistemaCarregamentoServiceFactory.create();

    @POST
    @Path("/sistemaCarregamento")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createSistemaCarregamento(SistemaCarregamentoDTO sistemaCarregamentoDTO) {
        try {
            Carro carro = new CarroDaoImpl().readById(sistemaCarregamentoDTO.getCarroId());
            if (carro == null) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Carro não encontrado").build();
            }

            SistemaCarregamento sistemaCarregamento = new SistemaCarregamento(
                    sistemaCarregamentoDTO.getId(),
                    new PlacaSolar(sistemaCarregamentoDTO.getPlacaSolarEficiencia()),
                    sistemaCarregamentoDTO.getNivelBateria(),
                    sistemaCarregamentoDTO.getCapacidadeBateria(),
                    carro
            );
            sistemaCarregamentoService.cadastrarSistemaCarregamento(sistemaCarregamento);
            return Response.status(Response.Status.CREATED).entity(sistemaCarregamentoDTO).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/sistemaCarregamento")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllSistemaCarregamento() {
        try {
            List<SistemaCarregamento> sistemaCarregamentos = sistemaCarregamentoService.listarSistemasCarregamento();
            List<SistemaCarregamentoDTO> sistemaCarregamentoDTOs = sistemaCarregamentos.stream().map(s -> new SistemaCarregamentoDTO(
                    s.getId(),
                    s.getPlacaSolar().getEficiencia(),
                    s.getNivelBateria(),
                    s.getCapacidadeBateria(),
                    s.getCarro().getId()
            )).collect(Collectors.toList());
            return Response.ok(sistemaCarregamentoDTOs).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/sistemaCarregamento/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateSistemaCarregamento(@PathParam("id") int id, SistemaCarregamentoDTO sistemaCarregamentoDTO) {
        try {
            Carro carro = new CarroDaoImpl().readById(sistemaCarregamentoDTO.getCarroId());
            if (carro == null) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Carro não encontrado").build();
            }

            SistemaCarregamento sistemaCarregamento = new SistemaCarregamento(
                    id,
                    new PlacaSolar(sistemaCarregamentoDTO.getPlacaSolarEficiencia()),
                    sistemaCarregamentoDTO.getNivelBateria(),
                    sistemaCarregamentoDTO.getCapacidadeBateria(),
                    carro
            );
            sistemaCarregamentoService.atualizarSistemaCarregamento(sistemaCarregamento);
            return Response.ok(sistemaCarregamentoDTO).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/sistemaCarregamento/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteSistemaCarregamento(@PathParam("id") int id) {
        try {
            sistemaCarregamentoService.removerSistemaCarregamento(id);
            return Response.noContent().build();
        } catch (SQLException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/sistemaCarregamento/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSistemaCarregamentoById(@PathParam("id") int id) {
        try {
            SistemaCarregamento sistemaCarregamento = sistemaCarregamentoService.buscarSistemaCarregamentoPorId(id);
            if (sistemaCarregamento == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Sistema de Carregamento não encontrado").build();
            }
            SistemaCarregamentoDTO sistemaCarregamentoDTO = new SistemaCarregamentoDTO(
                    sistemaCarregamento.getId(),
                    sistemaCarregamento.getPlacaSolar().getEficiencia(),
                    sistemaCarregamento.getNivelBateria(),
                    sistemaCarregamento.getCapacidadeBateria(),
                    sistemaCarregamento.getCarro().getId()
            );
            return Response.ok(sistemaCarregamentoDTO).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}