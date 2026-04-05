//notificacion por Email
public class NotifEmail extends  Notificacion{
    private String asunto;

    public NotifEmail(String destinatario, String mensaje, String asunto) {
        super(destinatario, mensaje);
        this.asunto = asunto;
    }
    @Override
    public void enviar(){
        System.out.println("EMAIL a " + destinatario + "| asunto: "+ asunto + "|" + formatear());
    }

    @Override
    public String formatear(){
        return "[EMAIL] " + mensaje;
    }
}
