package org.example.models;

public class SensorNivelBateria extends Sensor {
    private SistemaCarregamento sistemaCarregamento;

    public SensorNivelBateria(SistemaCarregamento sistemaCarregamento) {
        super("Nível de Bateria");
        this.sistemaCarregamento = sistemaCarregamento;
    }

    @Override
    public double medir() {
        return sistemaCarregamento.getNivelBateria();
    }
}