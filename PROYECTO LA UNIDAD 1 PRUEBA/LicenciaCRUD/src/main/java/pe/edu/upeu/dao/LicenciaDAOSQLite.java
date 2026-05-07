package pe.edu.upeu.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pe.edu.upeu.model.LicenciaConducir;
import pe.edu.upeu.util.ConexionDB;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LicenciaDAOSQLite implements LicenciaDAO{

    private Connection getConexion(){
        return ConexionDB.getInstacia().getConexion();
    }
    //CREATE.. GUARDAR NUEVA LICENCIA EN SQLITE

    @Override
    public void crear(LicenciaConducir licencia) {
        String sql = """
                INSERT INTO licencias 
                (nombre_titular, numero_licencia, tipo_licencia,
                fecha_expedicion, fecha_vencimiento, vigente)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (PreparedStatement ps = getConexion().prepareStatement(
                sql, Statement.RETURN_GENERATED_KEYS)) {

            // Calcular vigencia antes de guardar
            licencia.calcularVigencia();

            // Asignar valores a cada ?
            ps.setString(1, licencia.getNombreTitular());
            ps.setString(2, licencia.getNumeroLicencia().toUpperCase());
            ps.setString(3, licencia.getTipoLicencia());
            ps.setString(4, licencia.getFechaExpedicion().toString());  // yyyy-MM-dd
            ps.setString(5, licencia.getFechaVencimiento().toString()); // yyyy-MM-dd
            ps.setInt(6, licencia.isVigente() ? 1 : 0); // 1=vigente, 0=vencida

            ps.executeUpdate(); // Ejecutar el INSERT

            // Obtener el ID que SQLite asignó automáticamente
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    licencia.setId(keys.getInt(1));
                    System.out.println("✅ Licencia guardada con ID: " + licencia.getId());
                }
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al guardar licencia: " + e.getMessage());
            throw new RuntimeException("Error al crear licencia en BD", e);
        }

    }
    // READ — Buscar licencia por número

    @Override
    public LicenciaConducir leer(String numeroLicencia) {
        String sql = "SELECT * FROM licencias WHERE numero_licencia = ?";

        try (PreparedStatement ps = getConexion().prepareStatement(sql)) {
            ps.setString(1, numeroLicencia);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Convertir la fila del ResultSet a un objeto Java
                    return mapearLicencia(rs);
                }
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al buscar licencia: " + e.getMessage());
        }
        return null;
    }

    //  UPDATE — Actualizar licencia existente

    @Override
    public void actualizar(LicenciaConducir licencia) {
        String sql = """
                UPDATE licencias
                SET nombre_titular    = ?,
                    tipo_licencia     = ?,
                    fecha_expedicion  = ?,
                    fecha_vencimiento = ?,
                    vigente           = ?
                WHERE numero_licencia = ?
                """;

        try (PreparedStatement ps = getConexion().prepareStatement(sql)) {
            licencia.calcularVigencia(); // Recalcular antes de guardar

            ps.setString(1, licencia.getNombreTitular());
            ps.setString(2, licencia.getTipoLicencia());
            ps.setString(3, licencia.getFechaExpedicion().toString());
            ps.setString(4, licencia.getFechaVencimiento().toString());
            ps.setInt(5, licencia.isVigente() ? 1 : 0);
            ps.setString(6, licencia.getNumeroLicencia());

            int filasAfectadas = ps.executeUpdate();
            System.out.println("✅ Licencia actualizada. Filas afectadas: " + filasAfectadas);

        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar: " + e.getMessage());
            throw new RuntimeException("Error al actualizar licencia en BD", e);
        }

    }

    //  DELETE — Eliminar licencia

    @Override
    public void eliminar(String numeroLicencia) {
        String sql = "DELETE FROM licencias WHERE numero_licencia = ?";

        try (PreparedStatement ps = getConexion().prepareStatement(sql)) {
            ps.setString(1, numeroLicencia);
            int filasAfectadas = ps.executeUpdate();
            System.out.println("✅ Licencia eliminada. Filas: " + filasAfectadas);

        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar: " + e.getMessage());
            throw new RuntimeException("Error al eliminar licencia en BD", e);
        }

    }


    //  LIST — Obtener todas las licencias
    @Override
    public ObservableList<LicenciaConducir> listar() {
        ObservableList<LicenciaConducir> lista =
                FXCollections.observableArrayList();

        String sql = "SELECT * FROM licencias ORDER BY nombre_titular ASC";

        try (Statement stmt = getConexion().createStatement();
             ResultSet rs   = stmt.executeQuery(sql)) {

            // Recorrer todas las filas y convertir a objetos Java
            while (rs.next()) {
                LicenciaConducir lic = mapearLicencia(rs);
                lic.calcularVigencia(); // Recalcular con fecha de hoy
                lista.add(lic);
            }

            System.out.println("✅ Se cargaron " + lista.size() + " licencias de SQLite.");

        } catch (SQLException e) {
            System.err.println("❌ Error al listar: " + e.getMessage());
        }
        return lista;
    }

    //  EXISTE — Verificar unicidad del número
    @Override
    public boolean existeNumero(String numeroLicencia) {
        String sql = "SELECT COUNT(*) FROM licencias WHERE numero_licencia = ?";

        try (PreparedStatement ps = getConexion().prepareStatement(sql)) {
            ps.setString(1, numeroLicencia);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0; // true si COUNT > 0
                }
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al verificar número: " + e.getMessage());
        }
        return false;
    }
    private LicenciaConducir mapearLicencia(ResultSet rs) throws  SQLException{

        return new LicenciaConducir(
                rs.getInt("id"),
                rs.getString("nombre_titular"),
                rs.getString("numero_licencia"),
                rs.getString("tipo_licencia"),
                LocalDate.parse(rs.getString("fecha_expedicion")),   // "2024-01-15" → LocalDate
                LocalDate.parse(rs.getString("fecha_vencimiento"))   // "2029-01-15" → LocalDate
        );
    }
}
