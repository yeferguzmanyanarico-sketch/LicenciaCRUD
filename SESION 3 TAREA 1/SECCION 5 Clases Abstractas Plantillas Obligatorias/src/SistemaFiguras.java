//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class SistemaFiguras {
    public static void main(String[] args) {

        Figura c = new Circulo("Rojo", 5);
        Figura r = new Rectangulo("Azul", 4, 6);
        Figura t = new Triangulo("Verde", 3, 4, 3, 4, 5);

        System.out.println(c.descripcion());
        System.out.println(r.descripcion());
        System.out.println(t.descripcion());

        // Figura f = new Figura("Negro"); // ERROR: no se puede instanciar abstracta
    }



}