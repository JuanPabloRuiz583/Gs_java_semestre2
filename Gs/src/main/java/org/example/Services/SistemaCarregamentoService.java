package org.example.Services;

import org.example.Daos.SistemaCarregamentoDao;
import org.example.Daos.SistemaCarregamentoDaoFactory;
import org.example.models.SistemaCarregamento;

import java.sql.SQLException;
import java.util.List;

public class SistemaCarregamentoService {
    private final SistemaCarregamentoDao sistemaCarregamentoDao = SistemaCarregamentoDaoFactory.create();

    public void cadastrarSistemaCarregamento(SistemaCarregamento sistemaCarregamento) throws SQLException {
        if (sistemaCarregamento.getPlacaSolar() == null) {
            throw new IllegalArgumentException("Placa Solar não pode ser vazia");
        }
        if (sistemaCarregamento.getCarro() == null) {
            throw new IllegalArgumentException("Carro não pode ser vazio");
        }
        sistemaCarregamentoDao.create(sistemaCarregamento);
    }

    public List<SistemaCarregamento> listarSistemasCarregamento() throws SQLException {
        return sistemaCarregamentoDao.readAll();
    }

    public SistemaCarregamento buscarSistemaCarregamentoPorId(int id) throws SQLException {
        return sistemaCarregamentoDao.readById(id);
    }

    public void atualizarSistemaCarregamento(SistemaCarregamento sistemaCarregamento) throws SQLException {
        if (sistemaCarregamento.getPlacaSolar() == null) {
            throw new IllegalArgumentException("Placa Solar não pode ser vazia");
        }
        sistemaCarregamentoDao.update(sistemaCarregamento);
    }

    public void removerSistemaCarregamento(int id) throws SQLException {
        sistemaCarregamentoDao.delete(id);
    }
}
