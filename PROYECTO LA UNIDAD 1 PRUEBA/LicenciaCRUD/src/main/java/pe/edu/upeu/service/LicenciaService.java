package pe.edu.upeu.service;

import javafx.collections.ObservableList;
import pe.edu.upeu.dao.LicenciaDAO;
import pe.edu.upeu.dao.LicenciaDAOImpl;
import pe.edu.upeu.dao.LicenciaDAOSQLite;
import pe.edu.upeu.model.LicenciaConducir;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LicenciaService {
    private final LicenciaDAO dao;

    public LicenciaService(){
        this.dao = new LicenciaDAOSQLite();
    }

    public LicenciaService(LicenciaDAO dao) {
        this.dao = dao;
    }
    // crear
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
    //actualizar
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
    //eliminar
    public boolean eliminar(String numeroLicencia) {
        if (!dao.existeNumero(numeroLicencia)) return false;
        dao.eliminar(numeroLicencia);
        return true;
    }
    //buscar
    public LicenciaConducir buscarPorNumero(String numeroLicencia) {
        return dao.leer(numeroLicencia);
    }


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
    //listar
    public ObservableList<LicenciaConducir> listarTodas() {
        return dao.listar();
    }
    public void recalcularVigenciaGlobal() {
        dao.listar().forEach(LicenciaConducir::calcularVigencia);
    }
    //estadistica
    public long contarVigentes() {
        return dao.listar().stream().filter(LicenciaConducir::isVigente).count();
    }

    public long contarVencidas() {
        return dao.listar().stream().filter(l -> !l.isVigente()).count();
    }

    public int totalRegistros() {
        return dao.listar().size();
    }
    //valida

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
