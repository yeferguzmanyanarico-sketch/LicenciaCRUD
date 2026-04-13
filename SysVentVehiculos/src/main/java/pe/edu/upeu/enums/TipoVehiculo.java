package pe.edu.upeu.enums;

import lombok.Getter;
import lombok.ToString;

@Getter

public enum TipoVehiculo {
    SEDAN("sedan ", "SE"),
    SUV("SUV", "SU"),
    CAMIONETA("CAMIONETA","CAN"),
    DEPORTIVO("DEPORTIVO","DE"),
    ELECTRICO("ELECTRICO", "EL");

    private final String nombre, iniciales;

    TipoVehiculo(String nombre, String iniciales){
        this.nombre = nombre;
        this.iniciales = iniciales;
    }

    //aplicando polimorfismo




}
