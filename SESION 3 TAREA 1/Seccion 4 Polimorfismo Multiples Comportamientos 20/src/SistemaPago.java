import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class SistemaPago {
    public static void main(String[] args) {
        ArrayList<MetodoPago> pagos = new ArrayList<>();

        pagos.add(new TarjetaCredito(100, "1234567812345678", "123"));
        pagos.add(new PayPal(50,"usuario@example.com"));
        pagos.add(new Transferencia(200, "9876543210"));
        pagos.add(new CriptoPago(300, "0xABCDEF123456"));

        //PILMORFISMO: un solo bucle procesa todos los pagos

        for (MetodoPago p : pagos){
            p.procesarPago();
        }

    }
}