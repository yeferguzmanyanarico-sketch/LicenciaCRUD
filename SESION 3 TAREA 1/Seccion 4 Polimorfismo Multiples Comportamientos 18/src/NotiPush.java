// notificacion push
public class NotiPush extends Notificacion{
    private String icono;


    public NotiPush(String destinatario, String mensaje, String icono) {
        super(destinatario, mensaje);
        this.icono = icono;

    }
     @Override
    public void enviar(){
         System.out.println("PUSH a " + destinatario + " | Icono: " + icono + " | " +formatear());
     }

     @Override
    public String formatear (){
        return  "[PUSH] " + mensaje;
     }
}
