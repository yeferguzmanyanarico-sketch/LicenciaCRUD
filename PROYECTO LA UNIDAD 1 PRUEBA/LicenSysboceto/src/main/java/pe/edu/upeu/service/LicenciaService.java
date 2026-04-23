package pe.edu.upeu.service;

import pe.edu.upeu.dao.LicenciaDAO;
import pe.edu.upeu.dao.LicenciaDAOImpl;
import pe.edu.upeu.model.LicenciaConducir;
import pe.edu.upeu.util.DateUtil;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * ═══════════════════════════════════════════════════════
 *  PAQUETE: service
 *  CLASE:   LicenciaService
 * ═══════════════════════════════════════════════════════
 *
 * Capa de SERVICIO: actúa como intermediario entre el
 * Controlador (UI) y el DAO (datos).
 *
 * Responsabilidades:
 *  - Validar los datos antes de persistirlos
 *  - Aplicar reglas de negocio (unicidad, fechas, etc.)
 *  - Calcular vigencia automáticamente
 *  - El controlador NO habla directamente con el DAO
 *
 * Flujo:  Controller → Service → DAO → ObservableList
 */
public class LicenciaService {

    private final LicenciaDAO dao;

    // ── Constructor: inyección de dependencia ──────────────────────────────
    public LicenciaService() {
        this.dao = new LicenciaDAOImpl();
    }

    /** Constructor que permite inyectar un DAO diferente (útil para pruebas) */
    public LicenciaService(LicenciaDAO dao) {
        this.dao = dao;
    }

    // ══════════════════════════════════════════════════════════════════════
    //  CREAR
    // ══════════════════════════════════════════════════════════════════════

    /**
     * Registra una nueva licencia tras validar todos sus datos.
     *
     * @param nombreTitular    nombre completo del titular
     * @param numeroLicencia   número único (ej: LIC-B-001)
     * @param tipoLicencia     "A", "B" o "C"
     * @param fechaExpedicion  fecha en que se expidió
     * @param fechaVencimiento fecha en que vence
     * @return lista de errores encontrados (vacía = éxito)
     */
    public List<String> agregar(String nombreTitular, String numeroLicencia,
                                String tipoLicencia, LocalDate fechaExpedicion,
                                LocalDate fechaVencimiento) {

        List<String> errores = validar(nombreTitular, numeroLicencia,
                tipoLicencia, fechaExpedicion, fechaVencimiento, null);

        // Regla de negocio: el número de licencia debe ser único
        if (dao.existeNumero(numeroLicencia)) {
            errores.add("El número de licencia «" + numeroLicencia + "» ya está registrado.");
        }

        if (!errores.isEmpty()) return errores;

        // Construir el objeto y guardarlo
        LicenciaConducir nueva = new LicenciaConducir(
                0,
                nombreTitular.trim(),
                numeroLicencia.trim().toUpperCase(),
                tipoLicencia,
                fechaExpedicion,
                fechaVencimiento
        );
        nueva.calcularVigencia();
        dao.crear(nueva);
        return errores;  // Lista vacía → sin errores
    }

    // ══════════════════════════════════════════════════════════════════════
    //  ACTUALIZAR
    // ══════════════════════════════════════════════════════════════════════

    /**
     * Actualiza los datos de una licencia existente.
     * El número de licencia NO cambia (es el identificador).
     *
     * @param numeroOriginal  número actual de la licencia (no cambia)
     * @param nuevoNombre     nuevo nombre del titular
     * @param nuevoTipo       nuevo tipo (A/B/C)
     * @param nuevaExpedicion nueva fecha de expedición
     * @param nuevoVencimiento nueva fecha de vencimiento
     * @return lista de errores (vacía = éxito)
     */
    public List<String> actualizar(String numeroOriginal, String nuevoNombre,
                                   String nuevoTipo, LocalDate nuevaExpedicion,
                                   LocalDate nuevoVencimiento) {

        List<String> errores = validar(nuevoNombre, numeroOriginal,
                nuevoTipo, nuevaExpedicion, nuevoVencimiento, numeroOriginal);

        if (!errores.isEmpty()) return errores;

        LicenciaConducir existente = dao.leer(numeroOriginal);
        if (existente == null) {
            errores.add("No se encontró la licencia con número: " + numeroOriginal);
            return errores;
        }

        existente.setNombreTitular(nuevoNombre.trim());
        existente.setTipoLicencia(nuevoTipo);
        existente.setFechaExpedicion(nuevaExpedicion);
        existente.setFechaVencimiento(nuevoVencimiento);  // Recalcula vigencia internamente
        dao.actualizar(existente);
        return errores;
    }

    // ══════════════════════════════════════════════════════════════════════
    //  ELIMINAR
    // ══════════════════════════════════════════════════════════════════════

    /**
     * Elimina una licencia por su número.
     * @param numeroLicencia número de la licencia a eliminar
     * @return true si se eliminó, false si no existía
     */
    public boolean eliminar(String numeroLicencia) {
        if (!dao.existeNumero(numeroLicencia)) return false;
        dao.eliminar(numeroLicencia);
        return true;
    }

    // ══════════════════════════════════════════════════════════════════════
    //  BUSCAR
    // ══════════════════════════════════════════════════════════════════════

    /**
     * Busca una licencia por su número exacto.
     * @param numeroLicencia número a buscar
     * @return la licencia encontrada, o null
     */
    public LicenciaConducir buscarPorNumero(String numeroLicencia) {
        return dao.leer(numeroLicencia);
    }

    /**
     * Filtra licencias por nombre (búsqueda parcial, sin distinguir mayúsculas).
     * @param texto texto a buscar en el nombre del titular
     * @return lista filtrada
     */
    public List<LicenciaConducir> buscarPorNombre(String texto) {
        String busqueda = texto.toLowerCase().trim();
        List<LicenciaConducir> resultado = new ArrayList<>();
        for (LicenciaConducir l : dao.listar()) {
            if (l.getNombreTitular().toLowerCase().contains(busqueda)) {
                resultado.add(l);
            }
        }
        return resultado;
    }

    // ══════════════════════════════════════════════════════════════════════
    //  LISTAR
    // ══════════════════════════════════════════════════════════════════════

    /**
     * Retorna la ObservableList completa (para vincularla a la TableView).
     */
    public ObservableList<LicenciaConducir> listarTodas() {
        return dao.listar();
    }

    /**
     * Recalcula la vigencia de TODAS las licencias.
     * Útil para ejecutar al abrir la app (fechas pueden haber cambiado).
     */
    public void recalcularVigenciaGlobal() {
        dao.listar().forEach(LicenciaConducir::calcularVigencia);
    }

    // ══════════════════════════════════════════════════════════════════════
    //  ESTADÍSTICAS
    // ══════════════════════════════════════════════════════════════════════

    public long contarVigentes() {
        return dao.listar().stream().filter(LicenciaConducir::isVigente).count();
    }

    public long contarVencidas() {
        return dao.listar().stream().filter(l -> !l.isVigente()).count();
    }

    public int totalRegistros() {
        return dao.listar().size();
    }

    // ══════════════════════════════════════════════════════════════════════
    //  VALIDACIONES (reglas de negocio centralizadas)
    // ══════════════════════════════════════════════════════════════════════

    /**
     * Valida todos los campos de una licencia.
     * @param numeroExcluir si se está editando, excluir este número del check de unicidad
     * @return lista de mensajes de error (vacía = todo correcto)
     */
    private List<String> validar(String nombre, String numero, String tipo,
                                 LocalDate expedicion, LocalDate vencimiento,
                                 String numeroExcluir) {
        List<String> errores = new ArrayList<>();

        if (nombre == null || nombre.isBlank())
            errores.add("El nombre del titular es obligatorio.");
        else if (nombre.trim().length() < 3)
            errores.add("El nombre debe tener al menos 3 caracteres.");

        if (numero == null || numero.isBlank())
            errores.add("El número de licencia es obligatorio.");
        else if (numero.trim().length() < 4)
            errores.add("El número de licencia debe tener al menos 4 caracteres.");

        if (tipo == null || tipo.isBlank())
            errores.add("Debe seleccionar el tipo de licencia.");
        else if (!tipo.equals("A") && !tipo.equals("B") && !tipo.equals("C"))
            errores.add("El tipo debe ser A, B o C.");

        if (expedicion == null)
            errores.add("La fecha de expedición es obligatoria.");
        else if (expedicion.isAfter(LocalDate.now()))
            errores.add("La fecha de expedición no puede ser futura.");

        if (vencimiento == null)
            errores.add("La fecha de vencimiento es obligatoria.");
        else if (expedicion != null && !vencimiento.isAfter(expedicion))
            errores.add("La fecha de vencimiento debe ser posterior a la de expedición.");

        return errores;
    }
}