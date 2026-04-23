package pe.edu.upeu.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * ═══════════════════════════════════════════════════════
 *  PAQUETE: util
 *  CLASE:   DateUtil
 * ═══════════════════════════════════════════════════════
 *
 * Clase utilitaria con métodos estáticos para:
 *  - Formatear fechas en formato legible (dd/MM/yyyy)
 *  - Calcular vigencia de una fecha
 *  - Calcular días restantes hasta el vencimiento
 *
 * No se instancia: todos sus métodos son estáticos.
 */
public final class DateUtil {

    /** Formato estándar para mostrar fechas en la UI */
    public static final DateTimeFormatter FORMATO_VISIBLE =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /** Formato ISO para parsear desde cadena (yyyy-MM-dd) */
    public static final DateTimeFormatter FORMATO_ISO =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /** Constructor privado: clase utilitaria, no instanciable */
    private DateUtil() {}

    // ── Formatear fecha → texto ────────────────────────────────────────────

    /**
     * Convierte un LocalDate al formato dd/MM/yyyy.
     * @param fecha fecha a formatear
     * @return texto formateado, o "—" si la fecha es null
     */
    public static String formatear(LocalDate fecha) {
        return (fecha != null) ? fecha.format(FORMATO_VISIBLE) : "—";
    }

    // ── Calcular vigencia ─────────────────────────────────────────────────

    /**
     * Determina si una fecha de vencimiento sigue vigente.
     * @param fechaVencimiento fecha a evaluar
     * @return true si la fecha no ha pasado (inclusive hoy)
     */
    public static boolean esVigente(LocalDate fechaVencimiento) {
        return fechaVencimiento != null && !fechaVencimiento.isBefore(LocalDate.now());
    }

    /**
     * Texto de estatus basado en la fecha de vencimiento.
     * @param fechaVencimiento fecha a evaluar
     * @return "✅ Vigente" o "❌ Vencida"
     */
    public static String textoEstatus(LocalDate fechaVencimiento) {
        return esVigente(fechaVencimiento) ? "✅ Vigente" : "❌ Vencida";
    }

    // ── Días restantes ────────────────────────────────────────────────────

    /**
     * Calcula cuántos días faltan hasta el vencimiento.
     * @param fechaVencimiento fecha de vencimiento
     * @return días positivos si vigente, negativos si vencida, 0 si es hoy
     */
    public static long diasParaVencer(LocalDate fechaVencimiento) {
        if (fechaVencimiento == null) return Long.MIN_VALUE;
        return ChronoUnit.DAYS.between(LocalDate.now(), fechaVencimiento);
    }

    /**
     * Texto descriptivo de cuánto falta para vencer o hace cuánto venció.
     * @param fechaVencimiento fecha a evaluar
     * @return ejemplo: "Vence en 45 días" / "Venció hace 30 días" / "Vence hoy"
     */
    public static String descripcionVencimiento(LocalDate fechaVencimiento) {
        long dias = diasParaVencer(fechaVencimiento);
        if (dias == 0)  return "Vence hoy";
        if (dias > 0)   return "Vence en " + dias + " día(s)";
        return "Venció hace " + Math.abs(dias) + " día(s)";
    }

    /**
     * Indica si la licencia vence en los próximos N días.
     * Útil para mostrar alertas de renovación.
     * @param fechaVencimiento fecha a evaluar
     * @param dias             número de días hacia adelante
     * @return true si vence dentro del rango
     */
    public static boolean venceEn(LocalDate fechaVencimiento, int dias) {
        long restantes = diasParaVencer(fechaVencimiento);
        return restantes >= 0 && restantes <= dias;
    }
}
