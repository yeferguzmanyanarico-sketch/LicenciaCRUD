// Gerente con bono gerencial y empleados a cargo

public class Gerente extends Empleado{
    private  double bonoGerencial;
    private  int empeladosACargo;


    public Gerente(String nombre,String id, double salarioBase, double bonoGerencial, int empleadosACargo) {
        super(nombre, id, salarioBase);
        this.bonoGerencial = bonoGerencial;
        this.empeladosACargo = empleadosACargo;
    }

    @Override
    public double calcularSalario() {
        return + bonoGerencial + (empeladosACargo * 50);
        // Ejemplo: bono adicional por cada empleado a cargo
    }
}
