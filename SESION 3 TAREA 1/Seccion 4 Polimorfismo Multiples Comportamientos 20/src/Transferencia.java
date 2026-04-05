//Transferencia bancaria

class Transferencia extends MetodoPago {
    private String cuentaOrigen;

    public Transferencia(double monto, String cuentaOrigen) {
        super(monto);
        this.cuentaOrigen = cuentaOrigen;
    }

    @Override
    public boolean validar() {
        return super.validar() && cuentaOrigen.length() >= 10;
    }

    @Override
    public boolean procesarPago() {
        if (validar()) {
            System.out.println("Pago por TRANSFERENCIA: $" + monto + " | Cuenta: " + cuentaOrigen);
            return super.procesarPago();
        }
        System.out.println("Cuenta inválida.");
        return false;
    }
}
