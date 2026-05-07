package pe.edu.upeu.dao;

import javafx.collections.ObservableList;
import pe.edu.upeu.model.LicenciaConducir;

public interface LicenciaDAO {
    void crear (LicenciaConducir licencia);

    LicenciaConducir leer(String numeroLicencia);

    void actualizar(LicenciaConducir licencia);

    void eliminar(String numeroLicencia);

    ObservableList<LicenciaConducir> listar();

    boolean existeNumero(String numeroLicencia);

}
