//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class SistemaFacturas {
    public static void main(String[] args) {
        Factura factura = new Factura("Ana", 250.0);

        // Vista como Pagable
        Pagable p = factura;
        p.procesarPago();

        // Vista como Imprimible
        Imprimible i = factura;
        i.imprimir();

        // Vista como Exportable
        Exportable e = factura;
        System.out.println(e.exportar(e.getFormato()));
    }
}