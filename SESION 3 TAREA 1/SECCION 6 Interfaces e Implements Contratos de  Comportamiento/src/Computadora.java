import java.util.ArrayList;

// Clase Computadora que gestiona periféricos
class Computadora {
    private ArrayList<Conectable> perifericos = new ArrayList<>();

    public void agregarDispositivo(Conectable dispositivo) {
        perifericos.add(dispositivo);
        dispositivo.conectar();
        System.out.println(dispositivo.getNombreDispositivo() + " conectado.");
    }

    public void mostrarEstadoDispositivos() {
        for (Conectable d : perifericos) {
            System.out.println(d.getNombreDispositivo() + " | Conectado: " + d.estaConectado());
        }
    }

    public void desconectarTodos() {
        for (Conectable d : perifericos) {
            d.desconectar();
            System.out.println(d.getNombreDispositivo() + " desconectado.");
        }
    }
}
