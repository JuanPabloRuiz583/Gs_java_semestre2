package org.example.dtos;

public class CarroDTO {
    private int id;
    private String marca;
    private String modelo;
    private double capacidadeBateria;
    private double nivelBateria;
    private double autonomia;
    private String clienteEmail;

    // Default constructor
    public CarroDTO() {
    }

    // Constructor
    public CarroDTO(int id, String marca, String modelo, double capacidadeBateria, double nivelBateria, double autonomia, String clienteEmail) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.capacidadeBateria = capacidadeBateria;
        this.nivelBateria = nivelBateria;
        this.autonomia = autonomia;
        this.clienteEmail = clienteEmail;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getCapacidadeBateria() {
        return capacidadeBateria;
    }

    public void setCapacidadeBateria(double capacidadeBateria) {
        this.capacidadeBateria = capacidadeBateria;
    }

    public double getNivelBateria() {
        return nivelBateria;
    }

    public void setNivelBateria(double nivelBateria) {
        this.nivelBateria = nivelBateria;
    }

    public double getAutonomia() {
        return autonomia;
    }

    public void setAutonomia(double autonomia) {
        this.autonomia = autonomia;
    }

    public String getClienteEmail() {
        return clienteEmail;
    }

    public void setClienteEmail(String clienteEmail) {
        this.clienteEmail = clienteEmail;
    }
}