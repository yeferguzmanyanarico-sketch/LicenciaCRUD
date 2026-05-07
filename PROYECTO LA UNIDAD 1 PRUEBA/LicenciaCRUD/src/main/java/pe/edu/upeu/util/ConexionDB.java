package pe.edu.upeu.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionDB {
    private static final String URL = "jdbc:sqlite:licencias.db" ;

    //instancia unica con el singleton
    private static ConexionDB instacia;
    private Connection conexion;

    //constructor privado de singleton
    private ConexionDB(){
        conectar();
        crearTabla();

    }
    public static ConexionDB getInstacia(){
        if (instacia == null){
            instacia = new ConexionDB();
        }
        return  instacia;

    }

    public static void getInstance() {
    }

    private void conectar (){
        try {
            Class.forName("org.sqlite.JDBC");

            conexion = DriverManager.getConnection(URL);
            System.out.println("Conexion a SQLite exitosa: licencia.db");

        } catch (ClassNotFoundException e) {
            System.err.println("❌ Driver SQLite no encontrado: " + e.getMessage());
            throw new RuntimeException("Driver SQLite no encontrado", e);

        } catch (SQLException e) {
            System.err.println("❌ Error al conectar con SQLite: " + e.getMessage());
            throw new RuntimeException("Error de conexión SQLite", e);
        }
    }
    private void crearTabla() {
        // SQL para crear la tabla
        String sql = """
                CREATE TABLE IF NOT EXISTS licencias (
                    id                INTEGER PRIMARY KEY AUTOINCREMENT,
                    nombre_titular    TEXT    NOT NULL,
                    numero_licencia   TEXT    NOT NULL UNIQUE,
                    tipo_licencia     TEXT    NOT NULL,
                    fecha_expedicion  TEXT    NOT NULL,
                    fecha_vencimiento TEXT    NOT NULL,
                    vigente           INTEGER NOT NULL DEFAULT 1
                )
                """;

        try (Statement stmt = conexion.createStatement()) {
            stmt.execute(sql);
            System.out.println("✅ Tabla 'licencias' lista en SQLite.");
        } catch (SQLException e) {
            System.err.println("❌ Error al crear tabla: " + e.getMessage());
            throw new RuntimeException("Error al crear tabla", e);
        }
    }

    // ── Obtener la conexión activa ────────────────────────────────────

    public Connection getConexion() {
        try {
            // Verificar si la conexión sigue abierta
            if (conexion == null || conexion.isClosed()) {
                System.out.println("⚠️ Reconectando a SQLite...");
                conectar();
            }
        } catch (SQLException e) {
            conectar();
        }
        return conexion;
    }

    // ── Cerrar la conexión ────────────────────────────────────────────

    public void cerrar() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println("✅ Conexión a SQLite cerrada.");
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al cerrar conexión: " + e.getMessage());
        }
    }

}
