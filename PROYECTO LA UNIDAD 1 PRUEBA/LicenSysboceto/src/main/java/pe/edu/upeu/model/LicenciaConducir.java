package pe.edu.upeu.model;

import javafx.beans.property.*;
import java.time.LocalDate;

/**
 * ═══════════════════════════════════════════════════════
 *  PAQUETE: model
 *  CLASE:   LicenciaConducir
 * ═══════════════════════════════════════════════════════
 *
 * Representa una licencia de conducir con sus atributos.
 *
 * Usa JavaFX Properties para que la TableView refleje cambios
 * automáticamente (Observable Pattern).
 *
 * La vigencia se calcula AUTOMÁTICAMENTE comparando
 * fechaVencimiento con LocalDate.now().
 */
public class LicenciaConducir {

    // ── Atributos como JavaFX Properties (para binding con TableView) ──────
    private final IntegerProperty              id;
    private final StringProperty               nombreTitular;
    private final StringProperty               numeroLicencia;
    private final StringProperty               tipoLicencia;
    private final ObjectProperty<LocalDate>    fechaExpedicion;
    private final ObjectProperty<LocalDate>    fechaVencimiento;
    private final BooleanProperty              vigente;

    // ── Constructor vacío ──────────────────────────────────────────────────
    public LicenciaConducir() {
        this.id               = new SimpleIntegerProperty(0);
        this.nombreTitular    = new SimpleStringProperty("");
        this.numeroLicencia   = new SimpleStringProperty("");
        this.tipoLicencia     = new SimpleStringProperty("B");
        this.fechaExpedicion  = new SimpleObjectProperty<>(LocalDate.now());
        this.fechaVencimiento = new SimpleObjectProperty<>(LocalDate.now().plusYears(5));
        this.vigente          = new SimpleBooleanProperty(true);
        calcularVigencia();
    }

    // ── Constructor completo ───────────────────────────────────────────────
    public LicenciaConducir(int id, String nombreTitular, String numeroLicencia,
                            String tipoLicencia, LocalDate fechaExpedicion,
                            LocalDate fechaVencimiento) {
        this.id               = new SimpleIntegerProperty(id);
        this.nombreTitular    = new SimpleStringProperty(nombreTitular);
        this.numeroLicencia   = new SimpleStringProperty(numeroLicencia);
        this.tipoLicencia     = new SimpleStringProperty(tipoLicencia);
        this.fechaExpedicion  = new SimpleObjectProperty<>(fechaExpedicion);
        this.fechaVencimiento = new SimpleObjectProperty<>(fechaVencimiento);
        this.vigente          = new SimpleBooleanProperty(false);
        calcularVigencia();
    }

    // ── Lógica de vigencia (AUTOMÁTICA) ───────────────────────────────────
    /**
     * Calcula si la licencia está vigente.
     * Vigente = fecha de vencimiento >= hoy
     */
    public final void calcularVigencia() {
        LocalDate hoy  = LocalDate.now();
        LocalDate venc = fechaVencimiento.get();
        vigente.set(venc != null && !venc.isBefore(hoy));
    }

    /** Texto legible del estatus: "Vigente" o "Vencida" */
    public String getEstatusTexto() {
        return vigente.get() ? "Vigente" : "Vencida";
    }

    // ── JavaFX Property accessors ──────────────────────────────────────────
    public IntegerProperty           idProperty()               { return id; }
    public StringProperty            nombreTitularProperty()    { return nombreTitular; }
    public StringProperty            numeroLicenciaProperty()   { return numeroLicencia; }
    public StringProperty            tipoLicenciaProperty()     { return tipoLicencia; }
    public ObjectProperty<LocalDate> fechaExpedicionProperty()  { return fechaExpedicion; }
    public ObjectProperty<LocalDate> fechaVencimientoProperty() { return fechaVencimiento; }
    public BooleanProperty           vigenteProperty()          { return vigente; }

    // ── Getters ────────────────────────────────────────────────────────────
    public int        getId()               { return id.get(); }
    public String     getNombreTitular()    { return nombreTitular.get(); }
    public String     getNumeroLicencia()   { return numeroLicencia.get(); }
    public String     getTipoLicencia()     { return tipoLicencia.get(); }
    public LocalDate  getFechaExpedicion()  { return fechaExpedicion.get(); }
    public LocalDate  getFechaVencimiento() { return fechaVencimiento.get(); }
    public boolean    isVigente()           { return vigente.get(); }

    // ── Setters ────────────────────────────────────────────────────────────
    public void setId(int id)                              { this.id.set(id); }
    public void setNombreTitular(String nombre)            { this.nombreTitular.set(nombre); }
    public void setNumeroLicencia(String numero)           { this.numeroLicencia.set(numero); }
    public void setTipoLicencia(String tipo)               { this.tipoLicencia.set(tipo); }
    public void setFechaExpedicion(LocalDate fecha)        { this.fechaExpedicion.set(fecha); }
    public void setFechaVencimiento(LocalDate fecha) {
        this.fechaVencimiento.set(fecha);
        calcularVigencia();   // Recalcula automáticamente al cambiar la fecha
    }

    @Override
    public String toString() {
        return "[" + getNumeroLicencia() + "] " + getNombreTitular()
                + " - Tipo " + getTipoLicencia() + " (" + getEstatusTexto() + ")";
    }
}


