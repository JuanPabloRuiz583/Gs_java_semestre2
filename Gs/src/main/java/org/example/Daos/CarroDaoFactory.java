package org.example.Daos;

public class CarroDaoFactory {
    private CarroDaoFactory() {
    }

    public static CarroDao create() {
        return new CarroDaoImpl();
    }
}
