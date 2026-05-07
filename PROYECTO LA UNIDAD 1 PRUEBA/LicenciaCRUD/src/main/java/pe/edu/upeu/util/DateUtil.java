package pe.edu.upeu.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateUtil {
    public static final DateTimeFormatter FORMATO_VISIBLE =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static final DateTimeFormatter FORMATO_ISO =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private DateUtil() {
    }

    //formatear fecha
    public static String formatear(LocalDate fecha) {
        return (fecha != null) ? fecha.format(FORMATO_VISIBLE) : "—";
    }

    //calcular la vigencia
    public static boolean esVigente(LocalDate fechaVencimiento) {
        return fechaVencimiento != null && !fechaVencimiento.isBefore(LocalDate.now());
    }

    public static String textoEstatus(LocalDate fechaVencimiento) {
        return esVigente(fechaVencimiento) ? "✅ Vigente" : "❌ Vencida";
    }

    //dias restatntes
    public static long diasParaVencer(LocalDate fechaVencimiento) {
        if (fechaVencimiento == null) return Long.MIN_VALUE;
        return ChronoUnit.DAYS.between(LocalDate.now(), fechaVencimiento);
    }

    //una descripcion legible del vencimiento
    public static String descripcionVencimiento(LocalDate fechaVencimiento) {
        long dias = diasParaVencer(fechaVencimiento);
        if (dias == 0) return "Vence hoy";
        if (dias > 0) return "Vence en " + dias + " día(s)";
        return "Venció hace " + Math.abs(dias) + " día(s)";
    }

    //vence pronto
    public static boolean venceEn(LocalDate fechaVencimiento, int dias) {
        long restantes = diasParaVencer(fechaVencimiento);
        return restantes >= 0 && restantes <= dias;
    }
}