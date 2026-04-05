// Impresora
class Impresora implements Conectable {
    private boolean conectado = false;

    @Override
    public void conectar() { conectado = true; }
    @Override
    public void desconectar() { conectado = false; }
    @Override
    public boolean estaConectado() { return conectado; }
    @Override
    public String getNombreDispositivo() { return "Impresora USB"; }
}
