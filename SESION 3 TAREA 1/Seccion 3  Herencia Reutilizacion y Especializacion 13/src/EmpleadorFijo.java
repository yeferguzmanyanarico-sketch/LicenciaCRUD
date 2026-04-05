// empleado fijo con bonificacion
public class EmpleadorFijo extends Empleado {
    private double bonificacion;


    public EmpleadorFijo(String nombre, String id, double salarioBase, double bonificacion) {
        super(nombre, id, salarioBase);
        this.bonificacion = bonificacion;
    }
    @Override
    public double calcularSalario() {
        return + bonificacion;
    }
}
