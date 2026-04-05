// clase base
public class Notificacion {
    protected  String destinatario;
    protected String mensaje;

    public  Notificacion(String destinatario, String mensaje){
        this.destinatario= destinatario;
        this.mensaje = mensaje;

    }

    public void enviar(){
        System.out.println("ENVIADO A " + destinatario);
    }

    public String formatear(){
        return "MENSAJE: " + mensaje;
    }
}
