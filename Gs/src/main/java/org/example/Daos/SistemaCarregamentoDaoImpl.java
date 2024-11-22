package org.example.Daos;

import org.example.configDB.DatabaseConfig;
import org.example.models.SistemaCarregamento;
import org.example.models.PlacaSolar;
import org.example.models.Carro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class SistemaCarregamentoDaoImpl implements SistemaCarregamentoDao {

    @Override
    public void create(SistemaCarregamento sistemaCarregamento) throws SQLException {
        Connection connection = new DatabaseConfig(
                "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL",
                "rm557727",
                "210605"
        ).getConnection();

        String sql = "INSERT INTO sistema_carregamento (id, placa_solar_eficiencia, nivel_bateria, capacidade_bateria, carro_id) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, sistemaCarregamento.getId());
        pstmt.setDouble(2, sistemaCarregamento.getPlacaSolar().getEficiencia());
        pstmt.setDouble(3, sistemaCarregamento.getNivelBateria());
        pstmt.setDouble(4, sistemaCarregamento.getCapacidadeBateria());
        pstmt.setInt(5, sistemaCarregamento.getCarro().getId());
        pstmt.executeUpdate();
    }

    @Override
    public List<SistemaCarregamento> readAll() throws SQLException {
        Connection connection = new DatabaseConfig(
                "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL",
                "rm557727",
                "210605"
        ).getConnection();
        List<SistemaCarregamento> result = new ArrayList<>();
        String sql = "SELECT * FROM sistema_carregamento";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            int id = rs.getInt("id");
            PlacaSolar placaSolar = new PlacaSolar(rs.getDouble("placa_solar_eficiencia"));
            double nivelBateria = rs.getDouble("nivel_bateria");
            double capacidadeBateria = rs.getDouble("capacidade_bateria");
            Carro carro = new CarroDaoImpl().readById(rs.getInt("carro_id"));
            result.add(new SistemaCarregamento(id, placaSolar, nivelBateria, capacidadeBateria, carro));
        }
        return result;
    }

    @Override
    public void update(SistemaCarregamento sistemaCarregamento) throws SQLException {
        Connection connection = new DatabaseConfig(
                "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL",
                "rm557727",
                "210605"
        ).getConnection();
        String sql = "UPDATE sistema_carregamento SET placa_solar_eficiencia = ?, nivel_bateria = ?, capacidade_bateria = ?, carro_id = ? WHERE id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setDouble(1, sistemaCarregamento.getPlacaSolar().getEficiencia());
        pstmt.setDouble(2, sistemaCarregamento.getNivelBateria());
        pstmt.setDouble(3, sistemaCarregamento.getCapacidadeBateria());
        pstmt.setInt(4, sistemaCarregamento.getCarro().getId());
        pstmt.setInt(5, sistemaCarregamento.getId());
        pstmt.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
        Connection connection = new DatabaseConfig(
                "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL",
                "rm557727",
                "210605"
        ).getConnection();
        String sql = "DELETE FROM sistema_carregamento WHERE id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
    }

    @Override
    public SistemaCarregamento readById(int id) throws SQLException {
        Connection connection = new DatabaseConfig(
                "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL",
                "rm557727",
                "210605"
        ).getConnection();
        String sql = "SELECT * FROM sistema_carregamento WHERE id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            PlacaSolar placaSolar = new PlacaSolar(rs.getDouble("placa_solar_eficiencia"));
            double nivelBateria = rs.getDouble("nivel_bateria");
            double capacidadeBateria = rs.getDouble("capacidade_bateria");
            Carro carro = new CarroDaoImpl().readById(rs.getInt("carro_id"));
            return new SistemaCarregamento(id, placaSolar, nivelBateria, capacidadeBateria, carro);
        }
        return null;
    }
}