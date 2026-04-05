public class SistemaTransporte {
    public static void main(String[] args) {
        Automovil auto = new Automovil("Toyota", "Corolla", 5, 15, 4);
        Autobus bus = new Autobus("Mercedes", "Tourismo", 5, 2,0); // consumo 5 km/l, 2 pisos
        Motocicleta moto = new Motocicleta("Yamaha", "R3", 25, 300);

        System.out.println(auto.descripcion());
        System.out.println("Costo viaje auto (100 km, $5/litro): $" + auto.costoViaje(100, 5));

        System.out.println(bus.descripcion());
        System.out.println("Capacidad total de 3 buses: " + bus.capacidadTotal(3));

        System.out.println(moto.descripcion());
        System.out.println("Costo viaje moto (100 km, $5/litro): $" + moto.costoViaje(100, 5));
    }
}
