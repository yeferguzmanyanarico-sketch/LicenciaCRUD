package pe.edu.upeu.dao;

import pe.edu.upeu.model.LicenciaConducir;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;

/**
 * ═══════════════════════════════════════════════════════
 *  PAQUETE: dao
 *  TIPO:    Implementación
 *  CLASE:   LicenciaDAOImpl
 * ═══════════════════════════════════════════════════════
 *
 * Implementa LicenciaDAO usando una lista en MEMORIA.
 *
 * Usa ObservableList<LicenciaConducir> para que la TableView
 * de JavaFX detecte cambios y se refresque automáticamente.
 *
 * Incluye datos de ejemplo para poder probar la app al iniciar.
 */
public class LicenciaDAOImpl implements LicenciaDAO {

    // ── Lista observable: sincronizada automáticamente con la UI ──────────
    private final ObservableList<LicenciaConducir> lista =
            FXCollections.observableArrayList();

    private int contadorId = 1;   // Contador para asignar IDs auto-incrementales

    // ── Constructor: carga datos de ejemplo ───────────────────────────────
    public LicenciaDAOImpl() {
        cargarDatosEjemplo();
    }

    // ── CREATE ─────────────────────────────────────────────────────────────
    @Override
    public void crear(LicenciaConducir licencia) {
        licencia.setId(contadorId++);
        licencia.calcularVigencia();   // Calcular vigencia antes de guardar
        lista.add(licencia);
    }

    // ── READ ───────────────────────────────────────────────────────────────
    @Override
    public LicenciaConducir leer(String numeroLicencia) {
        return lista.stream()
                .filter(l -> l.getNumeroLicencia().equalsIgnoreCase(numeroLicencia))
                .findFirst()
                .orElse(null);
    }

    // ── UPDATE ─────────────────────────────────────────────────────────────
    @Override
    public void actualizar(LicenciaConducir licenciaActualizada) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNumeroLicencia()
                    .equalsIgnoreCase(licenciaActualizada.getNumeroLicencia())) {
                licenciaActualizada.calcularVigencia();
                lista.set(i, licenciaActualizada);  // set() notifica a la TableView
                return;
            }
        }
    }

    // ── DELETE ─────────────────────────────────────────────────────────────
    @Override
    public void eliminar(String numeroLicencia) {
        lista.removeIf(l ->
                l.getNumeroLicencia().equalsIgnoreCase(numeroLicencia));
    }

    // ── LIST ───────────────────────────────────────────────────────────────
    @Override
    public ObservableList<LicenciaConducir> listar() {
        return lista;
    }

    // ── EXISTE ─────────────────────────────────────────────────────────────
    @Override
    public boolean existeNumero(String numeroLicencia) {
        return lista.stream()
                .anyMatch(l -> l.getNumeroLicencia().equalsIgnoreCase(numeroLicencia));
    }

    // ── Datos de ejemplo ───────────────────────────────────────────────────
    /**
     * Inserta registros de demostración para que la tabla no aparezca vacía.
     * Incluye licencias vigentes, vencidas y próximas a vencer.
     */
    private void cargarDatosEjemplo() {
        crear(new LicenciaConducir(0, "Ana María Torres",   "LIC-A-001", "A",
                LocalDate.of(2021, 3, 10), LocalDate.now().plusYears(2)));

        crear(new LicenciaConducir(0, "Carlos Mendoza",     "LIC-B-002", "B",
                LocalDate.of(2020, 7, 15), LocalDate.now().plusMonths(6)));

        crear(new LicenciaConducir(0, "Elena Vargas",       "LIC-C-003", "C",
                LocalDate.of(2019, 1,  5), LocalDate.now().plusDays(20)));

        crear(new LicenciaConducir(0, "Roberto Silva",      "LIC-B-004", "B",
                LocalDate.of(2018, 4, 20), LocalDate.of(2023, 4, 20)));  // Vencida

        crear(new LicenciaConducir(0, "Sofía Ramírez",      "LIC-A-005", "A",
                LocalDate.of(2022, 9,  1), LocalDate.now().plusYears(3)));

        crear(new LicenciaConducir(0, "Diego Fernández",    "LIC-C-006", "C",
                LocalDate.of(2017, 11, 30), LocalDate.of(2022, 11, 30))); // Vencida
    }
}
