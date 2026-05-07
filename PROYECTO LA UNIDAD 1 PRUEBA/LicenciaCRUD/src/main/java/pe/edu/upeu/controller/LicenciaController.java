package pe.edu.upeu.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import pe.edu.upeu.model.LicenciaConducir;
import pe.edu.upeu.service.LicenciaService;
import pe.edu.upeu.util.DateUtil;




import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class LicenciaController implements Initializable {
    //  CAMPOS DEL FORMULARIO (conectados al FXML por fx:id)

    @FXML private TextField txtNombre;
    @FXML private TextField        txtNumero;
    @FXML private ComboBox<String> cboTipo;
    @FXML private DatePicker dpExpedicion;
    @FXML private DatePicker       dpVencimiento;
    @FXML private Label            lblEstatus;
    @FXML private Label            lblDias;
    @FXML private TextField        txtBuscar;

    //  TABLA Y COLUMNAS (conectadas al FXML por fx:id)
    @FXML private TableView<LicenciaConducir> tablaLicencias;
    @FXML private TableColumn<LicenciaConducir, Integer> colId;
    @FXML private TableColumn<LicenciaConducir, String>  colNombre;
    @FXML private TableColumn<LicenciaConducir, String>  colNumero;
    @FXML private TableColumn<LicenciaConducir, String>  colTipo;
    @FXML private TableColumn<LicenciaConducir, String>  colExpedicion;
    @FXML private TableColumn<LicenciaConducir, String>  colVencimiento;
    @FXML private TableColumn<LicenciaConducir, String>  colEstatus;
    @FXML private TableColumn<LicenciaConducir, Void>    colAcciones;

    //  ESTADÍSTICAS DEL ENCABEZADO
    @FXML private Label  lblTotal;
    @FXML private Label lblVigentes;
    @FXML private Label lblVencidas;

    //  ESTADO INTERNO DEL CONTROLADOR
    private final LicenciaService servicio = new LicenciaService();
    private String modoActual = "NUEVO";          // "NUEVO" o "EDITANDO"
    private String numeroEditando = null;          // Número de la licencia que se edita

    //  INICIALIZACIÓN
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        servicio.recalcularVigenciaGlobal();       // Actualizar vigencias al abrir
        configurarComboTipo();
        configurarColumnas();
        configurarListeners();
        vincularTabla();
        actualizarEstadisticas();
    }


    private void vincularTabla() {
        refrescarTabla();
        tablaLicencias.setPlaceholder(
                new Label("no hay licencia registrada")
        );
        /* tablaLicencias.setItems(servicio.listarTodas());
        tablaLicencias.setPlaceholder(new javafx.scene.control.Label("No hay licencias registradas."));*/
    }
    private void refrescarTabla(){
        tablaLicencias.setItems(servicio.listarTodas());
    }


    private void configurarListeners() {
        dpVencimiento.valueProperty().addListener((obs, viejo, nuevo) -> {
            if (nuevo != null) actualizarPreviewEstatus(nuevo);
        });

        // Al seleccionar una fila, cargar sus datos en el formulario
        tablaLicencias.getSelectionModel().selectedItemProperty()
                .addListener((obs, viejo, seleccionado) -> {
                    if (seleccionado != null) cargarEnFormulario(seleccionado);
                });
    }

    private void configurarColumnas() {
        colId.setStyle("-fx-alignment: CENTER;");

        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombreTitular"));

        colNumero.setCellValueFactory(new PropertyValueFactory<>("numeroLicencia"));
        colNumero.setStyle("-fx-font-family: monospace; -fx-alignment: CENTER;");

        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipoLicencia"));
        colTipo.setStyle("-fx-alignment: CENTER;");
        colTipo.setCellFactory(col -> crearCeldaBadgeTipo());

        // Fechas formateadas con DateUtil
        colExpedicion.setCellValueFactory(c ->
                new SimpleStringProperty(DateUtil.formatear(c.getValue().getFechaExpedicion())));
        colExpedicion.setStyle("-fx-alignment: CENTER;");

        colVencimiento.setCellValueFactory(c ->
                new SimpleStringProperty(DateUtil.formatear(c.getValue().getFechaVencimiento())));
        colVencimiento.setStyle("-fx-alignment: CENTER;");
        colVencimiento.setCellFactory(col -> crearCeldaFechaVencimiento());

        // Estatus calculado automáticamente
        colEstatus.setCellValueFactory(c ->
                new SimpleStringProperty(c.getValue().getEstatusTexto()));
        colEstatus.setStyle("-fx-alignment: CENTER;");
        colEstatus.setCellFactory(col -> crearCeldaEstatus());

        // Botones de acción por fila
        colAcciones.setCellFactory(col -> crearCeldaAcciones());
    }



    private void configurarComboTipo() {
        cboTipo.setItems(FXCollections.observableArrayList("A", "B", "C"));
        cboTipo.getSelectionModel().select("B");   // Valor por defecto
    }

@FXML
    public void onAgregar(ActionEvent event) {
    List<String> errores = servicio.agregar(
            txtNombre.getText(),
            txtNumero.getText(),
            cboTipo.getValue(),
            dpExpedicion.getValue(),
            dpVencimiento.getValue()
    );

    if (!errores.isEmpty()) {
        mostrarError("Errores al agregar", String.join("\n", errores));
        return;
    }

    mostrarExito("Licencia registrada correctamente.");
    limpiarFormulario();
    actualizarEstadisticas();
    refrescarTabla();

    }
@FXML
    public void onEditar(ActionEvent event) {
        if (!"EDITANDO".equals(modoActual)) {
            mostrarAviso("Selecciona una licencia de la tabla para editar.");
            return;
        }

        List<String> errores = servicio.actualizar(
                numeroEditando,
                txtNombre.getText(),
                cboTipo.getValue(),
                dpExpedicion.getValue(),
                dpVencimiento.getValue()
        );

        if (!errores.isEmpty()) {
            mostrarError("Errores al actualizar", String.join("\n", errores));
            return;
        }

        mostrarExito("Licencia actualizada correctamente.");
        limpiarFormulario();
        actualizarEstadisticas();
        refrescarTabla();
    }


    /** Botón "Eliminar" — elimina la licencia seleccionada */
    @FXML
    private void onEliminar() {
        LicenciaConducir seleccionada =
                tablaLicencias.getSelectionModel().getSelectedItem();

        if (seleccionada == null) {
            mostrarAviso("Selecciona una licencia de la tabla para eliminar.");
            return;
        }

        Optional<ButtonType> respuesta = mostrarConfirmacion(
                "¿Eliminar licencia?",
                "Se eliminará:\n  Titular: " + seleccionada.getNombreTitular()
                        + "\n  Número: "  + seleccionada.getNumeroLicencia()
                        + "\n\nEsta acción no se puede deshacer."
        );

        if (respuesta.isPresent() && respuesta.get() == ButtonType.OK) {
            servicio.eliminar(seleccionada.getNumeroLicencia());
            mostrarExito("Licencia eliminada.");
            limpiarFormulario();
            actualizarEstadisticas();
            refrescarTabla();
        }
    }

    /** Botón "Buscar" — filtra por texto en el campo de búsqueda */
    @FXML
    private void onBuscar() {
        String texto = txtBuscar.getText().trim();

        if (texto.isEmpty()) {
            // Si está vacío, mostrar todas
            tablaLicencias.setItems(servicio.listarTodas());
            return;
        }

        // Buscar por número exacto primero
        LicenciaConducir porNumero = servicio.buscarPorNumero(texto);
        if (porNumero != null) {
            ObservableList<LicenciaConducir> resultado =
                    FXCollections.observableArrayList(porNumero);
            tablaLicencias.setItems(resultado);
            return;
        }

        // Sino, buscar por nombre (parcial)
        List<LicenciaConducir> porNombre = servicio.buscarPorNombre(texto);
        tablaLicencias.setItems(FXCollections.observableArrayList(porNombre));

        if (porNombre.isEmpty()) {
            mostrarAviso("No se encontraron licencias con: «" + texto + "»");
        }
    }

    /** Botón "Ver todas" — restaura la lista completa */
    @FXML
    private void onVerTodas() {
        txtBuscar.clear();
        tablaLicencias.setItems(servicio.listarTodas());
        tablaLicencias.getSelectionModel().clearSelection();
    }

    /** Botón "Limpiar" — limpia el formulario y vuelve a modo NUEVO */
    @FXML
    private void onLimpiar() {
        limpiarFormulario();
    }
    //  CARGA DEL FORMULARIO AL SELECCIONAR FILA
    private void cargarEnFormulario(LicenciaConducir licencia) {
        txtNombre.setText(licencia.getNombreTitular());
        txtNumero.setText(licencia.getNumeroLicencia());
        txtNumero.setEditable(false);   // No se puede cambiar el número en edición
        txtNumero.setStyle("-fx-background-color: #f5f5f5; -fx-border-radius: 6;");
        cboTipo.setValue(licencia.getTipoLicencia());
        dpExpedicion.setValue(licencia.getFechaExpedicion());
        dpVencimiento.setValue(licencia.getFechaVencimiento());
        actualizarPreviewEstatus(licencia.getFechaVencimiento());

        modoActual       = "EDITANDO";
        numeroEditando   = licencia.getNumeroLicencia();

    }
    //  HELPERS DE UI
    private void limpiarFormulario() {
        txtNombre.clear();
        txtNumero.clear();
        txtNumero.setEditable(true);
        txtNumero.setStyle("");
        cboTipo.getSelectionModel().select("B");
        dpExpedicion.setValue(null);
        dpVencimiento.setValue(null);
        lblEstatus.setText("— Sin fecha seleccionada —");
        lblEstatus.setTextFill(Color.GRAY);
        if (lblDias != null) lblDias.setText("");
        txtBuscar.clear();
        tablaLicencias.getSelectionModel().clearSelection();
        modoActual     = "NUEVO";
        numeroEditando = null;
    }

    private void actualizarPreviewEstatus(LocalDate fechaVencimiento) {
        boolean vigente = DateUtil.esVigente(fechaVencimiento);
        lblEstatus.setText(DateUtil.textoEstatus(fechaVencimiento));
        lblEstatus.setTextFill(vigente ? Color.web("#27ae60") : Color.web("#e74c3c"));
        if (lblDias != null)
            lblDias.setText(DateUtil.descripcionVencimiento(fechaVencimiento));
    }

    private void actualizarEstadisticas() {
        if (lblTotal    != null) lblTotal.setText(String.valueOf(servicio.totalRegistros()));
        if (lblVigentes != null) lblVigentes.setText(String.valueOf(servicio.contarVigentes()));
        if (lblVencidas != null) lblVencidas.setText(String.valueOf(servicio.contarVencidas()));
    }

    // ═════════════════════════════════════════════════════
    //  CELDAS PERSONALIZADAS DE LA TABLA
    // ═════════════════════════════════════════════════════

    /** Celda con badge de color para el tipo de licencia */
    private TableCell<LicenciaConducir, String> crearCeldaBadgeTipo() {
        return new TableCell<>() {
            @Override
            protected void updateItem(String tipo, boolean vacia) {
                super.updateItem(tipo, vacia);
                if (vacia || tipo == null) { setGraphic(null); return; }
                javafx.scene.control.Label badge = new javafx.scene.control.Label(tipo);
                badge.setAlignment(Pos.CENTER);
                badge.setMinWidth(30);
                String color = switch (tipo) {
                    case "A" -> "#e65100";
                    case "B" -> "#0d47a1";
                    case "C" -> "#4a148c";
                    default  -> "#616161";
                };
                badge.setStyle("-fx-background-color: " + color + "22;"
                        + "-fx-border-color: " + color + ";"
                        + "-fx-border-radius: 10; -fx-background-radius: 10;"
                        + "-fx-padding: 2 8 2 8; -fx-font-weight: bold;"
                        + "-fx-text-fill: " + color + ";");
                setGraphic(badge);
                setAlignment(Pos.CENTER);
            }
        };
    }

    /** Celda con color para la fecha de vencimiento */
    private TableCell<LicenciaConducir, String> crearCeldaFechaVencimiento() {
        return new TableCell<>() {
            @Override
            protected void updateItem(String fecha, boolean vacia) {
                super.updateItem(fecha, vacia);
                if (vacia || fecha == null) { setText(null); return; }
                setText(fecha);
                LicenciaConducir lic = getTableView().getItems().get(getIndex());
                if (lic != null && !lic.isVigente())
                    setStyle("-fx-text-fill: #c62828; -fx-font-weight: bold;");
                else if (lic != null && DateUtil.venceEn(lic.getFechaVencimiento(), 30))
                    setStyle("-fx-text-fill: #e65100; -fx-font-style: italic;");
                else
                    setStyle("-fx-text-fill: #2e7d32;");
            }
        };
    }

    /** Celda con indicador visual de estatus (vigente/vencida) */
    private TableCell<LicenciaConducir, String> crearCeldaEstatus() {
        return new TableCell<>() {
            @Override
            protected void updateItem(String estatus, boolean vacia) {
                super.updateItem(estatus, vacia);
                if (vacia || estatus == null) { setGraphic(null); return; }

                LicenciaConducir lic = getTableView().getItems().get(getIndex());
                boolean vigente = (lic != null) && lic.isVigente();

                javafx.scene.control.Label chip = new javafx.scene.control.Label(vigente ? "✅ Vigente" : "❌ Vencida");
                chip.setStyle(vigente
                        ? "-fx-background-color: #e8f5e9; -fx-text-fill: #1b5e20;"
                        + "-fx-background-radius: 10; -fx-padding: 3 10;"
                        + "-fx-font-weight: bold; -fx-font-size: 11;"
                        : "-fx-background-color: #ffebee; -fx-text-fill: #b71c1c;"
                        + "-fx-background-radius: 10; -fx-padding: 3 10;"
                        + "-fx-font-weight: bold; -fx-font-size: 11;");

                setGraphic(chip);
                setAlignment(Pos.CENTER);
            }
        };
    }

    /** Celda con botones Editar / Eliminar por cada fila */
    private TableCell<LicenciaConducir, Void> crearCeldaAcciones() {
        return new TableCell<>() {
            private final Button btnEditar   = new Button("✏️");
            private final Button btnEliminar = new Button("🗑️");
            private final HBox contenedor  = new HBox(6, btnEditar, btnEliminar);

            {
                // Estilo de botones miniatura
                String estiloBase = "-fx-background-radius: 6; -fx-cursor: hand;"
                        + "-fx-font-size: 13; -fx-padding: 3 8;";
                btnEditar.setStyle(estiloBase + "-fx-background-color: #e3f2fd;");
                btnEliminar.setStyle(estiloBase + "-fx-background-color: #fce4ec;");
                contenedor.setAlignment(Pos.CENTER);

                btnEditar.setOnAction(e -> {
                    LicenciaConducir lic = getTableView().getItems().get(getIndex());
                    tablaLicencias.getSelectionModel().select(lic);
                    cargarEnFormulario(lic);
                });

                btnEliminar.setOnAction(e -> {
                    LicenciaConducir lic = getTableView().getItems().get(getIndex());
                    tablaLicencias.getSelectionModel().select(lic);
                    onEliminar();
                });
            }

            @Override
            protected void updateItem(Void item, boolean vacia) {
                super.updateItem(item, vacia);
                setGraphic(vacia ? null : contenedor);
            }
        };
    }

    // ═════════════════════════════════════════════════════
    //  DIÁLOGOS / MENSAJES
    // ═════════════════════════════════════════════════════

    private void mostrarExito(String mensaje) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("✅ Operación exitosa");
        a.setHeaderText(null);
        a.setContentText(mensaje);
        a.showAndWait();
    }

    private void mostrarError(String titulo, String mensaje) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setTitle("❌ " + titulo);
        a.setHeaderText("Por favor corrija los siguientes errores:");
        a.setContentText(mensaje);
        a.showAndWait();
    }

    private void mostrarAviso(String mensaje) {
        Alert a = new Alert(Alert.AlertType.WARNING);
        a.setTitle("⚠️ Aviso");
        a.setHeaderText(null);
        a.setContentText(mensaje);
        a.showAndWait();
    }

    private Optional<ButtonType> mostrarConfirmacion(String titulo, String mensaje) {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle(titulo);
        a.setHeaderText(null);
        a.setContentText(mensaje);
        return a.showAndWait();
    }
}
