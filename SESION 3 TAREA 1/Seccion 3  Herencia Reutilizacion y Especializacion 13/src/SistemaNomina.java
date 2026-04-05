public class SistemaNomina {
    public static void main(String[] args) {
        EmpleadorFijo fijo = new EmpleadorFijo("ANA", "E001", 2000,300);
        EmpleadorTemporal temporal = new EmpleadorTemporal("CARLOS", "E002", 1000, 40, 20);
        Gerente gerente = new Gerente("LAURA", "E003", 3000,1000, 5);

        System.out.println("SALARIO EMPLEADO FIJO: " + fijo.calcularSalario());
        System.out.println("SALARIO EMPLEADO TEMPORAL: " + temporal.calcularSalario());
        System.out.println("SALARIO EMPLEADO GERENTE: " + gerente.calcularSalario());
    }
}
