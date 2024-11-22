package org.example.Services;

import org.example.Daos.CarroDao;
import org.example.Daos.CarroDaoFactory;
import org.example.models.Carro;

import java.sql.SQLException;
import java.util.List;

public class CarroService {
    private final CarroDao carroDao = CarroDaoFactory.create();

    public void cadastrarCarro(Carro carro) throws SQLException {
        if (carro.getMarca() == null || carro.getMarca().isEmpty()) {
            throw new IllegalArgumentException("Marca não pode ser vazia");
        }
        if (carro.getModelo() == null || carro.getModelo().isEmpty()) {
            throw new IllegalArgumentException("Modelo não pode ser vazio");
        }
        carroDao.create(carro);
    }

    public List<Carro> listarCarros() throws SQLException {
        return carroDao.readAll();
    }

    public Carro buscarCarroPorId(int id) throws SQLException {
        return carroDao.readById(id);
    }

    public void atualizarCarro(Carro carro) throws SQLException {
        if (carro.getMarca() == null || carro.getMarca().isEmpty()) {
            throw new IllegalArgumentException("Marca não pode ser vazia");
        }
        carroDao.update(carro);
    }

    public void removerCarro(int id) throws SQLException {
        carroDao.delete(id);
    }
}