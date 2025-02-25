package org.example.Daos;


import org.example.configDB.DatabaseConfig;
import org.example.models.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class ClienteDaoImpl implements ClienteDao {

    // Verifica se um email já está cadastrado no banco de dados
    private boolean isEmailRegistered(String email) throws SQLException {
        Connection connection = new DatabaseConfig(
                "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL",
                "rm557727",
                "210605"
        ).getConnection();
        String sql = "SELECT COUNT(*) FROM cliente WHERE email = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, email);
        ResultSet rs = pstmt.executeQuery();

        // Verifica se existe algum registro com o email informado
        if (rs.next()) {
            return rs.getInt(1) > 0;
        }
        return false;
    }

    @Override
    public void create( Cliente cliente) throws SQLException {
        Connection connection = new DatabaseConfig(
                "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL",
                "rm557727",
                "210605"
        ).getConnection();

        // Cliente cliente1= new Cliente("Juan","jua@email.com","123",11233,"rua a");
        if (isEmailRegistered(cliente.getEmail())) {
            throw new SQLException("O email já está cadastrado!");
        }

        String sql= "insert into cliente(nome,email,senha) values (?,?,?)";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1,cliente.getNome());
        pstmt.setString(2,cliente.getEmail());
        pstmt.setString(3,cliente.getSenha());
        pstmt.executeUpdate();
    }

    @Override
    public List<Cliente> readAll() throws SQLException {
        Connection connection = new DatabaseConfig(
                "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL",
                "rm557727",
                "210605"
        ).getConnection();
        List<Cliente> result= new ArrayList<>();
        String sql = "select *from cliente";
        Statement stmt = connection.createStatement();
        ResultSet rs= stmt.executeQuery(sql);
        while(rs.next()){
            String nome= rs.getString("nome");
            String email= rs.getString("email");
            String senha= rs.getString("senha");
            result.add(new Cliente(nome,email,senha));
        }
        return result;
    }

    @Override
    public void update(Cliente cliente) throws SQLException {
        Connection connection = new DatabaseConfig(
                "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL",
                "rm557727",
                "210605"
        ).getConnection();
        String sql="update cliente set nome=?,senha=? where email=?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1,cliente.getNome());
        pstmt.setString(2,cliente.getSenha());
        pstmt.setString(3, cliente.getEmail());
        pstmt.executeUpdate();


    }

    @Override
    public void delete(String email) throws SQLException {
        Connection connection = new DatabaseConfig(
                "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL",
                "rm557727",
                "210605"
        ).getConnection();
        String sql ="delete from cliente where email=?";
        PreparedStatement pstmt= connection.prepareStatement(sql);
        pstmt.setString(1,email);
        pstmt.executeUpdate();
    }


    // Novo método para buscar um cliente por email
    @Override
    public Cliente readByEmail(String email) throws SQLException {
        Connection connection = new DatabaseConfig(
                "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL",
                "rm557727",
                "210605"
        ).getConnection();
        String sql = "SELECT * FROM cliente WHERE email = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, email);
        ResultSet rs = pstmt.executeQuery();

        // Verifica se encontrou o cliente
        if (rs.next()) {
            String nome = rs.getString("nome");
            String senha = rs.getString("senha");


            // Retorna o cliente encontrado
            return new Cliente(nome, email, senha);
        } else {
            // Se não encontrar, retorna null
            return null;
        }
    }


}

