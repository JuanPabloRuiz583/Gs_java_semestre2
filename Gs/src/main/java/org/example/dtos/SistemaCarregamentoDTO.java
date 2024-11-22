package org.example.dtos;

public class SistemaCarregamentoDTO {
    private int id;
    private double placaSolarEficiencia;
    private double nivelBateria;
    private double capacidadeBateria;
    private int carroId;

    // Constructor


    public SistemaCarregamentoDTO() {
    }

    public SistemaCarregamentoDTO(int id, double placaSolarEficiencia, double nivelBateria, double capacidadeBateria, int carroId) {
        this.id = id;
        this.placaSolarEficiencia = placaSolarEficiencia;
        this.nivelBateria = nivelBateria;
        this.capacidadeBateria = capacidadeBateria;
        this.carroId = carroId;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPlacaSolarEficiencia() {
        return placaSolarEficiencia;
    }

    public void setPlacaSolarEficiencia(double placaSolarEficiencia) {
        this.placaSolarEficiencia = placaSolarEficiencia;
    }

    public double getNivelBateria() {
        return nivelBateria;
    }

    public void setNivelBateria(double nivelBateria) {
        this.nivelBateria = nivelBateria;
    }

    public double getCapacidadeBateria() {
        return capacidadeBateria;
    }

    public void setCapacidadeBateria(double capacidadeBateria) {
        this.capacidadeBateria = capacidadeBateria;
    }

    public int getCarroId() {
        return carroId;
    }

    public void setCarroId(int carroId) {
        this.carroId = carroId;
    }
}