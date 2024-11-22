package org.example.models;

public class SensorEnergiaGerada extends Sensor {
    private PlacaSolar placaSolar;
    private double horasDeSol;

    public SensorEnergiaGerada(PlacaSolar placaSolar) {
        super("Energia Gerada");
        this.placaSolar = placaSolar;
    }

    public void setHorasDeSol(double horasDeSol) {
        this.horasDeSol = horasDeSol;
    }

    @Override
    public double medir() {
        return placaSolar.gerarEnergia(horasDeSol);
    }
}
