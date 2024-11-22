package org.example.models;

public class SistemaCarregamento {
    private int id;
    private PlacaSolar placaSolar;
    private double nivelBateria;
    private double capacidadeBateria;
    private Carro carro;

    public SistemaCarregamento(int id, PlacaSolar placaSolar, double nivelBateria, double capacidadeBateria, Carro carro) {
        this.id = id;
        this.placaSolar = placaSolar;
        this.nivelBateria = nivelBateria;
        this.capacidadeBateria = capacidadeBateria;
        this.carro = carro;
    }

    public int getId() {
        return id;
    }

    public PlacaSolar getPlacaSolar() {
        return placaSolar;
    }

    public double getNivelBateria() {
        return nivelBateria;
    }

    public double getCapacidadeBateria() {
        return capacidadeBateria;
    }

    public Carro getCarro() {
        return carro;
    }
}