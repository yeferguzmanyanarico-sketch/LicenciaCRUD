// notificaion por whatsaap
public class NotiWhatsApp extends  Notificacion {
    private String numero;


    public NotiWhatsApp(String destinatario, String mensaje, String numero) {
        super(destinatario, mensaje);
        this.numero = numero;

    }

    @Override
    public void enviar() {
        System.out.println("WhatsApp a " + numero + " | " + formatear());
    }

    @Override
    public String formatear() {
        return "[WhatsApp] " + mensaje;
    }
}