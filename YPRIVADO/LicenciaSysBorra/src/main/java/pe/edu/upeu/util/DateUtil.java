package pe.edu.upeu.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**UTILIDAD: herraminetas para mejorar fechas
 *
 * todos los metodos estatiticos no necesitan crear un objeto
 * Uso: DateUtil.formatear(fecha)   DateUtil.esVigente(fecha)
 */

public final class DateUtil {

    //formato para mostrar en la pantalla: 25/12/2024
    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    //constructor privado nadie puede crear  instancias
    private DateUtil(){}

    /**
     * convierte una fecha en texto. ejemplo: LocalDate.of(2024,12,25) en ("25/12/2024")
     */
    public static String formatear(LocalDate fecha){
        return (fecha != null) ? fecha.format(FORMATO) : "-";
    }

    // TRUE = vigente (vence hoy o en futuro,) false = vencida (ya paso)
    public static boolean esVigente(LocalDate fechaVencimiento){
        return fechaVencimiento != null && !fechaVencimiento.isBefore(LocalDate.now());
    }
    /**
     * Texto del estatus con ícono.
     * Retorna: "✅ Vigente"  o  "❌ Vencida"
     */
    public static String textoEstatus(LocalDate fechaVencimiento) {
        return esVigente(fechaVencimiento) ? "✅ Vigente" : "❌ Vencida";
    }

}
