//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Calculadora {
    public static double operar(double a, double b, Operacion op) {
        return op.calcular(a, b);
}
    public static void main(String[] args) {
        double x = 10, y = 5;

        System.out.println("Suma: " + operar(x, y, Operacion.SUMA));
        System.out.println("Resta: " + operar(x, y, Operacion.RESTA));
        System.out.println("Multiplicación: " + operar(x, y, Operacion.MULTIPLICACION));
        System.out.println("División: " + operar(x, y, Operacion.DIVISION));

        
    }

}