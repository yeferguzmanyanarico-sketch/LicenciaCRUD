package pe.edu.upeu.model;

import javafx.beans.property.*;

import java.time.LocalDate;

public class LicenciaConducir {
    private final IntegerProperty  id;
    private final StringProperty nombretitular;
    private final StringProperty numeroLicencia;
    private final StringProperty tipoLicencia;
    private final ObjectProperty<LocalDate> fechaExpedicion;
    private final ObjectProperty<LocalDate> fechaVencimiento;
    private final BooleanProperty vigente;

    //constructor VACIO (para crear una licencia nueva o en blanco)
    public LicenciaConducir(){
        this.id = new SimpleIntegerProperty(0);
        this.nombretitular = new SimpleStringProperty("");
        this.numeroLicencia = new SimpleStringProperty("");
        this.tipoLicencia = new SimpleStringProperty("");
        this.fechaExpedicion = new SimpleObjectProperty<>(LocalDate.now());
        this.fechaVencimiento = new SimpleObjectProperty<>(LocalDate.now().plusYears(5));
        this.vigente = new SimpleBooleanProperty(true);

    }

    // Constructor COMPLETO (para cargar desde la lista/ base de datos)
    public LicenciaConducir(int id, String nombreTitular, String numeroLicencia, String tipoLicencia, LocalDate fechaExpedido, LocalDate fechaVencido){
        this.id = new SimpleIntegerProperty(id);
        this.nombretitular = new SimpleStringProperty(nombreTitular);
        this.numeroLicencia = new SimpleStringProperty(numeroLicencia);
        this.tipoLicencia = new SimpleStringProperty(tipoLicencia);
        this.fechaExpedicion = new SimpleObjectProperty<>(fechaExpedido);
        this.fechaVencimiento = new SimpleObjectProperty<>(fechaVencido);
        this.vigente = new SimpleBooleanProperty(false);


    }
    // METODO CLAVE: calcula si la licencia esta vigente
    /**
     * compara la fecha de vencimiento con HOY.
     * si vence HOY o despues = VIGENTE.
     * si ya paso = vencida
     * este Metodo se llama automaticamente.
     */
    public final void calcularVigencia(){
        LocalDate hoy = LocalDate.now();
        LocalDate vencimiento = fechaVencimiento.get();
        // isBefore (hoy) = ¿ya paso? -> negamos para saber si es vigente
        vigente.set(vencimiento != null && !vencimiento.isBefore(hoy));
    }

    // texto legible del status
    public String getEstatusTexto(){
        return vigente.get() ? "Vigente" : "Vencida";
    }
    // JavaFX  Property Getter (para el binding de la TableVIew
    public IntegerProperty idProperty() {return id; }
    public StringProperty nombreTitularProperty() {
        return nombretitular;
    }
    public StringProperty numeroLicenciaProperty(){
        return numeroLicencia;
    }
    public StringProperty tipoLicenciaProperty(){
        return tipoLicencia;
    }
    public ObjectProperty<LocalDate> fechaExpedicionProperty(){
        return fechaExpedicion;
    }
    public ObjectProperty<LocalDate> fechaVencimientoProperty() {
        return fechaVencimiento;
    }
    public BooleanProperty vigenteProperty(){
        return vigente;
    }

    // GETTERS NORMALES
    public int getId(){
        return id.get();
    }
    public String getNombreTitular(){
        return nombretitular.get();
    }
    public String getNumeroLicencia(){
        return numeroLicencia.get();
    }
    public String getTipoLicencia(){
        return tipoLicencia.get();
    }
    public LocalDate getFechaExpedicion(){
        return fechaExpedicion.get();
    }
    public LocalDate getFechaVencido(){
        return fechaVencimiento.get();
    }
    public boolean isVigente(){
        return vigente.get();
    }

    //SETTERS
    public void setId(int id){
        this.id.set(id);
    }
    public void setNombretitular(String nombre){
        this.nombretitular.set(nombre);
    }
    public void setNumeroLicencia(String numero){
        this.numeroLicencia.set(numero);
    }
    public void setTipoLicencia(String tipo){
        this.tipoLicencia.set(tipo);
    }
    public void setFechaExpedicion(LocalDate fecha){
        this.fechaExpedicion.set(fecha);
    }

    //SETTERS ESPECIAL
    public void setFechaVencimiento(LocalDate fecha){
        this.fechaVencimiento.set(fecha);
        calcularVigencia();
    }

}
