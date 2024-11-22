package org.example.Daos;

import org.example.models.Carro;
import java.sql.SQLException;
import java.util.List;

public interface CarroDao {
    void create(Carro carro) throws SQLException;
    List<Carro> readAll() throws SQLException;
    void update(Carro carro) throws SQLException;
    void delete(int id) throws SQLException;
    Carro readById(int id) throws SQLException;
}