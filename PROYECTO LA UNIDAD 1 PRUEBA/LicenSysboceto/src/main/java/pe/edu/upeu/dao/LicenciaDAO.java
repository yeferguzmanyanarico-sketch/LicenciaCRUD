package pe.edu.upeu.dao;

import pe.edu.upeu.model.LicenciaConducir;
import javafx.collections.ObservableList;

/**
 * ═══════════════════════════════════════════════════════
 *  PAQUETE: dao
 *  TIPO:    Interfaz (contrato)
 *  CLASE:   LicenciaDAO
 * ═══════════════════════════════════════════════════════
 *
 * Define las operaciones CRUD que debe implementar
 * cualquier fuente de datos (memoria, base de datos, API...).
 *
 * Al programar contra esta interfaz y no contra la implementación,
 * podemos cambiar el origen de datos sin tocar el resto del código.
 */
public interface LicenciaDAO {

    /**
     * Agrega una nueva licencia al almacenamiento.
     * @param licencia objeto con los datos a guardar
     */
    void crear(LicenciaConducir licencia);


    /**
     * Busca una licencia por su número único.
     * @param numeroLicencia identificador único de la licencia
     * @return la licencia encontrada, o null si no existe
     */
    LicenciaConducir leer(String numeroLicencia);

    /**
     * Actualiza los datos de una licencia existente.
     * @param licencia objeto con los datos actualizados (mismo número)
     */
    void actualizar(LicenciaConducir licencia);

    /**
     * Elimina una licencia del almacenamiento.
     * @param numeroLicencia identificador de la licencia a eliminar
     */
    void eliminar(String numeroLicencia);

    /**
     * Retorna la lista observable con todas las licencias.
     * Observable = la TableView se actualiza automáticamente.
     * @return ObservableList con todas las licencias
     */
    ObservableList<LicenciaConducir> listar();

    /**
     * Verifica si ya existe una licencia con ese número.
     * @param numeroLicencia número a verificar
     * @return true si ya existe
     */
    boolean existeNumero(String numeroLicencia);
}
