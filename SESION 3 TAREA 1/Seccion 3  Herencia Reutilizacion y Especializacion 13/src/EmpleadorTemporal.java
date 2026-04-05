// Empleado temporal con horas trabajadas y tarifa por hora
public class EmpleadorTemporal extends Empleado{
    private int horasTrabajadas;
    private double tarifahora;

    public EmpleadorTemporal(String nombre, String id, double salarioBase, int horasTrabajadas, double tarifahora) {
        super(nombre, id, salarioBase);
        this.horasTrabajadas = horasTrabajadas;
        this.tarifahora = tarifahora;
    }


    @Override
    public double calcularSalario() {
        return + (horasTrabajadas * tarifahora);
    }
}
