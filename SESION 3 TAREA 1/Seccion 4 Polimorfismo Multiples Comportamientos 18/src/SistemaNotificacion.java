import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class SistemaNotificacion {
    public static void main(String[] args) {
        ArrayList < Notificacion> pendientes = new ArrayList<>();

        pendientes.add(new NotifEmail("ana@example.com", "Revisa tu bandeja", "Aviso importante"));
        pendientes.add(new NotifSMS("Carlos", "Tu código es 1234", "+51987654321"));
        pendientes.add(new NotiPush("UsuarioApp", "Tienes una nueva actualización", "🔔"));
        pendientes.add(new NotiWhatsApp("Laura", "Nos vemos a las 5pm", "+51912345678"));

        // Polimorfismo: un solo bucle envia todas las notificaciones
        for (Notificacion n : pendientes){
            n.enviar();
        }

    }
}