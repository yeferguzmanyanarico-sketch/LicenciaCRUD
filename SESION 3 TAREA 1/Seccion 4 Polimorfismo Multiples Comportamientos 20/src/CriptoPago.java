//Criptomonedas
class CriptoPago extends MetodoPago {
    private String wallet;

    public CriptoPago(double monto, String wallet) {
        super(monto);
        this.wallet = wallet;

    }

    @Override
    public boolean validar() {
        return super.validar() && wallet.startsWith("0x") && wallet.length() > 10;
    }

    @Override
    public boolean procesarPago() {
        if (validar()) {
            System.out.println("Pago con CRIPTO: $" + monto + " | Wallet: " + wallet);
            return super.procesarPago();
        }
        System.out.println("Wallet inválida.");
        return false;
    }
}
