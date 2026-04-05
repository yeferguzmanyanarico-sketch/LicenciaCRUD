// PayPal
class PayPal extends MetodoPago {
    private String email;

    public PayPal(double monto, String email) {
        super(monto);
        this.email = email;
    }

    @Override
    public boolean validar() {
        return super.validar() && email.contains("@");
    }

    @Override
    public boolean procesarPago() {
        if (validar()) {
            System.out.println("Pago con PAYPAL: $" + monto + " | Cuenta: " + email);
            return super.procesarPago();
        }
        System.out.println("Email inválido.");
        return false;
    }
}
