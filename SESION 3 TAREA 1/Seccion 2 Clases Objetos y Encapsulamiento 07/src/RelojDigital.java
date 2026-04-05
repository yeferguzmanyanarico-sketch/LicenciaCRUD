//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class RelojDigital {
    private int horas, minutos, segundos;
    private int alarmaH = -1, alarmaM = -1;

    //constructor
    public RelojDigital(int horas, int minutos,int segundos){
        this.horas = (horas>= 0 && horas < 24) ? horas :0;
        this.minutos = (minutos>= 0 && minutos < 60) ? minutos: 0;
        this.segundos = (segundos>= 0 && segundos < 60) ? segundos: 0;
    }

    // avanzar un segundo
    public void avanzarSegundo (){
        segundos ++;
        if (segundos>= 60){
            segundos = 0;
            minutos++;
        }
        if (minutos >= 60){
            minutos = 0;
            horas++;
        }
        if (horas >= 24){
            horas = 0;
        }
    }
    //mostrar formato24h
    public String mostrarFormato24H(){
        return String.format("%02d:%02d:%02d", horas,minutos,segundos);

    }

    // mostrar formato de 12 horas
    public String mostraFormato12H(){
        String ampm = horas>=12 ? "PM" : "AM";
        int h = horas % 12;
        if (h == 0) h = 12 ;
        return String.format("%d : %02d : %02d %s", h, minutos, segundos, ampm );

    }

    // configurar alarma
    public void configurarAlarma(int h, int m){
        if (h >= 0 && h < 24 && m >=0 && m < 60 ){
            alarmaH = h;
            alarmaM = m;
            System.out.println("ALARMA CONFIGURADA A LAS " + h + ":" + String.format("%02d", m));
        } else {
            System.out.println("HORA INVALIDA PARA LA ALARMA. ");
        }

    }

    //verefir alarma

    public boolean verificarAlarma() {
        if (horas == alarmaH && minutos == alarmaM && segundos == 0) {
            System.out.println("¡ALARMA ACTIVADA!");
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        RelojDigital reloj = new RelojDigital(11,59,55);

        reloj.configurarAlarma(10, 0 );

        for (int i = 0; i < 10; i++) {
            reloj.avanzarSegundo();
            System.out.println("HORA 24H: " + reloj.mostrarFormato24H());
            System.out.println("HORA 12H: " + reloj.mostraFormato12H());
            reloj.verificarAlarma();
        }

    }


}