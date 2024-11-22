package org.example.models;

public class Microcontrolador {
    private SensorEnergiaGerada sensorEnergiaGerada;
    private SensorNivelBateria sensorNivelBateria;

    public Microcontrolador(SensorEnergiaGerada sensorEnergiaGerada, SensorNivelBateria sensorNivelBateria) {
        this.sensorEnergiaGerada = sensorEnergiaGerada;
        this.sensorNivelBateria = sensorNivelBateria;
    }

    public void monitorar() {
        double energiaGerada = sensorEnergiaGerada.medir();
        double nivelBateria = sensorNivelBateria.medir();

        System.out.println("Energia Gerada: " + energiaGerada + " kWh");
        System.out.println("Nível de Bateria: " + nivelBateria + " kWh");
    }
}