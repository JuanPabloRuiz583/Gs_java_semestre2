package org.example.Daos;

import org.example.configDB.DatabaseConfig;
import org.example.models.Carro;
import org.example.models.Cliente;
import org.example.models.Marca;
import org.example.models.Modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarroDaoImpl implements CarroDao {

    @Override
    public void create(Carro carro) throws SQLException {
        Connection connection = new DatabaseConfig(
                "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL",
                "rm557727",
                "210605"
        ).getConnection();

        String sql = "INSERT INTO carro (id,marca, modelo, capacidade_bateria, nivel_bateria, autonomia, cliente_email) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, carro.getId());
        pstmt.setString(2, carro.getMarca().toString());
        pstmt.setString(3, carro.getModelo().toString());
        pstmt.setDouble(4, carro.getCapacidadeBateria());
        pstmt.setDouble(5, carro.getNivelBateria());
        pstmt.setDouble(6, carro.getAutonomia());
        pstmt.setString(7, carro.getCliente().getEmail());
        pstmt.executeUpdate();
    }

    @Override
    public List<Carro> readAll() throws SQLException {
        Connection connection = new DatabaseConfig(
                "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL",
                "rm557727",
                "210605"
        ).getConnection();
        List<Carro> result = new ArrayList<>();
        String sql = "SELECT * FROM carro";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            int id = rs.getInt("id");
            Marca marca = Marca.valueOf(rs.getString("marca"));
            Modelo modelo = Modelo.valueOf(rs.getString("modelo"));
            double capacidadeBateria = rs.getDouble("capacidade_bateria");
            double nivelBateria = rs.getDouble("nivel_bateria");
            double autonomia = rs.getDouble("autonomia");
            Cliente cliente = new ClienteDaoImpl().readByEmail(rs.getString("cliente_email"));
            result.add(new Carro(id, marca, modelo, capacidadeBateria, nivelBateria, autonomia, cliente));
        }
        return result;
    }

    @Override
    public void update(Carro carro) throws SQLException {
        Connection connection = new DatabaseConfig(
                "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL",
                "rm557727",
                "210605"
        ).getConnection();
        String sql = "UPDATE carro SET marca = ?, modelo = ?, capacidade_bateria = ?, nivel_bateria = ?, autonomia = ?, cliente_email = ? WHERE id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, carro.getMarca().toString());
        pstmt.setString(2, carro.getModelo().toString());
        pstmt.setDouble(3, carro.getCapacidadeBateria());
        pstmt.setDouble(4, carro.getNivelBateria());
        pstmt.setDouble(5, carro.getAutonomia());
        pstmt.setString(6, carro.getCliente().getEmail());
        pstmt.setInt(7, carro.getId());
        pstmt.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
        Connection connection = new DatabaseConfig(
                "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL",
                "rm557727",
                "210605"
        ).getConnection();
        String sql = "DELETE FROM carro WHERE id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
    }

    @Override
    public Carro readById(int id) throws SQLException {
        Connection connection = new DatabaseConfig(
                "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL",
                "rm557727",
                "210605"
        ).getConnection();
        String sql = "SELECT * FROM carro WHERE id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            Marca marca = Marca.valueOf(rs.getString("marca"));
            Modelo modelo = Modelo.valueOf(rs.getString("modelo"));
            double capacidadeBateria = rs.getDouble("capacidade_bateria");
            double nivelBateria = rs.getDouble("nivel_bateria");
            double autonomia = rs.getDouble("autonomia");
            Cliente cliente = new ClienteDaoImpl().readByEmail(rs.getString("cliente_email"));
            return new Carro(id, marca, modelo, capacidadeBateria, nivelBateria, autonomia, cliente);
        }
        return null;
    }
}