import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class CuentaBancaria {
    private String titular;
    private String numeroCuenta;
    private Double saldo;
    private ArrayList<String> movimientos;

    //comnstructor
    public CuentaBancaria(String titular, String numeroCuenta, double saldoInicial){
        this.titular = titular;
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldoInicial > 0? saldoInicial: 0;
        this.movimientos = new ArrayList<>();
        movimientos.add(" CUENTA CREADA CON SALDO INICIAL: $"+this.saldo);
    }
    // getter de titular
    public String getTitular(){
        return titular;

    }
    // setter de titular con validacion
    public void setTitular(String titular){
        if (titular != null && !titular.trim().isEmpty()){
            this.titular = titular;
        } else {
            System.out.println(" TITULAR INVALIDO.");
        }
    }
    //getter de nuemro de cuenta
    public String getNumeroCuenta(){
        return numeroCuenta;
    }
    // getter de saldo (solo lectura, sin setter publico)
    public double getSaldo(){
        return saldo;

    }
    // metodo depositar
    public boolean depositar(double monto){
        if (monto > 0){
            saldo += monto;
            movimientos.add(" Deposito: +$ "+monto);
            return  true;
        }
        System.out.println("MONTO INVALIDO PARA DEPOSITO.");
        return false;
    }
    // metodo retirar
    public boolean retirar(double monto){
        if (monto > 0 && monto <= saldo){
            saldo -= monto;
            movimientos.add("RETIROS: -$" + monto);
            return false;

        }
        System.out.println("FONDOS INSUFICIENTES O MONTO INVALIDO ");
        return false;
    }
    //metodo transferir
    public boolean transferir(CuentaBancaria destino,double monto){
        if (this.retirar(monto)){
            destino.depositar(monto);
            movimientos.add("TRANSFERENCIA: -$ "+monto+ " a " + destino.getTitular());
            return true;

        }
        return false;
    }
    //obtener historial de movimientos
    public ArrayList<String> getMovimientos(){
        return movimientos;
    }
    // metodo main para probar
    public static void main(String[] args) {
        CuentaBancaria cuenta1 = new CuentaBancaria("ANA", "12345", 500);
        CuentaBancaria cuenta2 = new CuentaBancaria("CARLOS", "67890", 300);

        cuenta1.depositar(200);
        cuenta1.retirar(100);
        cuenta1.transferir(cuenta2, 150);

        System.out.println("SALDO DE ANA: $" + cuenta1.getSaldo());
        System.out.println("SALDO DE CARLOS: $" + cuenta2.getSaldo());

        System.out.println("MOVIMIENTOS DE ANA: " +  cuenta1.getMovimientos());
        System.out.println("MOVIMIENTOS DE CARLOS: " + cuenta2.getMovimientos());
    }
}