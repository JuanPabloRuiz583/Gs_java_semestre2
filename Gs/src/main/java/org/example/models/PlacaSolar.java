package org.example.models;

public class PlacaSolar {
    private double eficiencia;

    public PlacaSolar(double eficiencia) {
        this.eficiencia = eficiencia;
    }

    public double getEficiencia() {
        return eficiencia;
    }

    public void setEficiencia(double eficiencia) {
        this.eficiencia = eficiencia;
    }

    public double gerarEnergia(double horasDeSol) {
        return horasDeSol * eficiencia;
    }
}
