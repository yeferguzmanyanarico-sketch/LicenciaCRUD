//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class SistemaEnvios {
    public static void main(String[] args) {
        Envio terrestre = new EnvioTerrestre("Lima", "Cusco", 100, 1100);
        Envio aereo = new EnvioAereo("Lima", "Miami", 50, 4200);
        Envio maritimo = new EnvioMaritimo("Callao", "Tokio", 200, 18000);

        System.out.println(terrestre.generarGuia());
        System.out.println(aereo.generarGuia());
        System.out.println(maritimo.generarGuia());
    }
}
