// tarjeta de credito
class TarjetaCredito extends MetodoPago {
    private String numero, cvv;

    public TarjetaCredito(double monto, String numero, String cvv) {
        super(monto);
        this.numero = numero;
        this.cvv = cvv;
    }

    @Override
    public boolean validar() {
        return super.validar() && numero.length() == 16 && cvv.length() == 3;
    }

    @Override
    public boolean procesarPago() {
        if (validar()) {
            System.out.println("Pago con TARJETA: $" + monto + " | Número: ****" + numero.substring(12));
            return super.procesarPago();
        }
        System.out.println("Tarjeta inválida.");
        return false;
    }
}
