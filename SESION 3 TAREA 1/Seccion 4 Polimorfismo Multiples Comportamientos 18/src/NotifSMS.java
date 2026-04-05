//notificacion por SMS
public class NotifSMS extends Notificacion{
    private String numTelefono;

    public NotifSMS(String destinatario, String mensaje, String numTelefono ) {
        super(destinatario, mensaje);
        this.numTelefono = numTelefono;
    }

    @Override
    public  void enviar (){
        System.out.println("SMS a " + numTelefono + " | " + formatear());
    }

    @Override
    public  String formatear(){
        return  "[SMS] " + mensaje;
    }
}
