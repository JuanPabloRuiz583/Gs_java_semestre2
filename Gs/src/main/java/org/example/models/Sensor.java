package org.example.models;

public abstract class Sensor {
    protected String tipo;

    public Sensor(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public abstract double medir();
}