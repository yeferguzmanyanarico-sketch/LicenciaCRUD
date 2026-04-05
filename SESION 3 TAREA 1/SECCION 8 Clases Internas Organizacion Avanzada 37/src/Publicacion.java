import java.time.LocalDateTime;
import java.util.ArrayList;

class Publicacion {
    private String autor;
    private String contenido;
    private ArrayList<Comentario> comentarios = new ArrayList<>();

    public Publicacion(String autor, String contenido) {
        this.autor = autor;
        this.contenido = contenido;
    }

    // Clase interna: Comentario
    class Comentario {
        private String texto;
        private String autorComentario;
        private LocalDateTime fecha;

        public Comentario(String texto, String autorComentario) {
            this.texto = texto;
            this.autorComentario = autorComentario;
            this.fecha = LocalDateTime.now();
        }

        public void notificarAutor() {
            // Accede directamente al autor de la publicación
            System.out.println("Notificar a " + Publicacion.this.autor + ": nuevo comentario de " + autorComentario);
        }

        @Override
        public String toString() {
            return autorComentario + " (" + fecha + "): " + texto;
        }
    }

    // Métodos de la publicación
    public void agregarComentario(String texto, String autorComentario) {
        Comentario c = new Comentario(texto, autorComentario);
        comentarios.add(c);
        c.notificarAutor();
    }

    public void eliminarComentario(int index) {
        if (index >= 0 && index < comentarios.size()) {
            comentarios.remove(index);
            System.out.println("Comentario eliminado.");
        } else {
            System.out.println("Índice inválido.");
        }
    }

    public int contarComentarios() {
        return comentarios.size();
    }

    public void mostrarComentarios() {
        System.out.println("Comentarios en la publicación de " + autor + ":");
        for (Comentario c : comentarios) {
            System.out.println(c);
        }
    }
}
