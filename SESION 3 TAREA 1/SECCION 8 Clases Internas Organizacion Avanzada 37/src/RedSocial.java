//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class RedSocial {
    public static void main(String[] args) {
        Publicacion pub = new Publicacion("Ana", "Hoy fue un gran día en la universidad.");

        pub.agregarComentario("¡Qué bueno, felicidades!", "Carlos");
        pub.agregarComentario("Me alegra leer eso.", "Laura");

        pub.mostrarComentarios();
        System.out.println("Total de comentarios: " + pub.contarComentarios());

        pub.eliminarComentario(0);
        pub.mostrarComentarios();
    }
}