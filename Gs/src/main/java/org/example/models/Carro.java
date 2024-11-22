package org.example.models;

public class Carro {
    private int id;
    private Marca marca;
    private Modelo modelo;
    private double capacidadeBateria;
    private double nivelBateria;
    private double autonomia;
    private Cliente cliente;

    public Carro(int id, Marca marca, Modelo modelo, double capacidadeBateria, double nivelBateria, double autonomia, Cliente cliente) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.capacidadeBateria = capacidadeBateria;
        this.nivelBateria = nivelBateria;
        this.autonomia = autonomia;
        this.cliente = cliente;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}