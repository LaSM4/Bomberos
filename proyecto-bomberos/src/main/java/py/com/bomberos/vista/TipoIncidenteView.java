package py.com.bomberos.vista;

import java.awt.EventQueue;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import py.com.bomberos.controladores.TipoIncidenteController;
import py.com.bomberos.modelo.entidades.TipoIncidente;
import py.com.bomberos.vista.modelotabla.ModeloTablaTipoIncidente;
import py.edu.facitec.reutilizacion.ventana.VentanaGenerica;

public class TipoIncidenteView extends VentanaGenerica {

    private static final long serialVersionUID = 1L;
    private JTextField txtDescripcion;
    private TipoIncidente tipoActual;
    private ModeloTablaTipoIncidente modeloTabla;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TipoIncidenteView dialog = new TipoIncidenteView();
                    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    dialog.setUpController();
                    dialog.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public TipoIncidenteController setUpController() {
        return new TipoIncidenteController(this);
    }

    public TipoIncidenteView() {
        this.modeloTabla = new ModeloTablaTipoIncidente();
        if (getTable() != null) {
            getTable().setModel(modeloTabla);
        }

        getPanelFormulario().setLayout(null);

        JLabel lblDescripcion = new JLabel("Descripción:");
        lblDescripcion.setBounds(22, 30, 82, 17);
        getPanelFormulario().add(lblDescripcion);

        txtDescripcion = new JTextField();
        txtDescripcion.setBounds(110, 30, 294, 21);
        getPanelFormulario().add(txtDescripcion);
    }

    public ModeloTablaTipoIncidente getModeloTabla() {
        return modeloTabla;
    }

    public void setEntidad(TipoIncidente t) {
        if (t == null) {
            t = new TipoIncidente();
        }
        this.tipoActual = t;
        txtDescripcion.setText(t.getDescripcion() != null ? t.getDescripcion() : "");
    }

    public TipoIncidente getEntidad() {
        if (this.tipoActual == null) {
            this.tipoActual = new TipoIncidente();
        }
        tipoActual.setDescripcion(txtDescripcion.getText().trim());
        return tipoActual;
    }

    @Override
    protected String getTitulo() {
        return "Tipo de Incidentes";
    }

    @Override
    protected String getTituloFormulario() {
        return "Datos del Tipo de Incidente";
    }

    public JTextField getTxtDescripcion() {
        return txtDescripcion;
    }
}
