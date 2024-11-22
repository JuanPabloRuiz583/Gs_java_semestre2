package org.example.models;

public enum Modelo {
    //  CROSSOVER , SUV , PICAPES , ESPORTIVOS , SEDA , MINIVAN , PERUA , HATCH

    SEDAN("Sedan"),
    SUV("SUV"),
    HATCHBACK("Hatchback"),
    CROSSOVER("Crossover"),
    PICKUP("Pickup"),
    SPORT("Sport"),
    MINIVAN("Minivan"),
    WAGON("Wagon"),
    CONVERTIBLE("Convertible"),
    COUPE("Coupe");

    private final String descricao;

    Modelo(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public boolean isEmpty() {
        return descricao == null || descricao.trim().isEmpty();
    }
}
