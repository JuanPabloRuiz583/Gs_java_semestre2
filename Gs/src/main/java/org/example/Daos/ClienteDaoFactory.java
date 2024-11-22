package org.example.Daos;

public class ClienteDaoFactory {

    private ClienteDaoFactory() {
    }

    public static ClienteDao create() {
        return new ClienteDaoImpl();
    }
}
