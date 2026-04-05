
//clase abstracta base
abstract class Figura {
    protected String color;

    public Figura(String color) {
        this.color = color;
    }

    // Métodos abstractos: obligatorios en subclases
    public abstract double calcularArea();
    public abstract double calcularPerimetro();

    // Método concreto compartido
    public String descripcion() {
        return color + " | Área: " + calcularArea() + " | Perímetro: " + calcularPerimetro();
    }
}
