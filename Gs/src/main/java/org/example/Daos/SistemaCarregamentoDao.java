package org.example.Daos;

import org.example.models.SistemaCarregamento;
import java.sql.SQLException;
import java.util.List;

public interface SistemaCarregamentoDao {
    void create(SistemaCarregamento sistemaCarregamento) throws SQLException;
    List<SistemaCarregamento> readAll() throws SQLException;
    void update(SistemaCarregamento sistemaCarregamento) throws SQLException;
    void delete(int id) throws SQLException;
    SistemaCarregamento readById(int id) throws SQLException;
}
