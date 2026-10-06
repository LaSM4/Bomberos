package py.com.bomberos.controladores;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JOptionPane;

import py.com.bomberos.modelo.dao.MovilDAO;
import py.com.bomberos.modelo.entidades.Movil;
import py.com.bomberos.vista.MovilView;
import py.com.bomberos.vista.modelotabla.ModeloTablaMovil;
import py.edu.facitec.reutilizacion.interfaces.AccionesABM;

public class MovilController implements AccionesABM {

    private final MovilView vista;
    private final MovilDAO dao;
    private final ModeloTablaMovil modeloTabla;
    private Movil movilSeleccionado;

    public MovilController(MovilView vista) {
        this.vista = vista;
        this.dao = new MovilDAO();
        this.modeloTabla = new ModeloTablaMovil();

        inicializarEventos();
        cargarDatosTabla();
        estadoInicial(true);
    }

    private void estadoInicial(boolean esInicial) {
        vista.getTxtDescripcion().setEnabled(!esInicial);
        vista.getTxtCapacidad().setEnabled(!esInicial);
        vista.getComboEstado().setEnabled(!esInicial);
        vista.getTxtTutorial().setEnabled(!esInicial);

        vista.getTable().setEnabled(esInicial);
        vista.getMiToolbar().estadoInicial(esInicial);
    }

    private void inicializarEventos() {
        vista.getTable().setModel(modeloTabla);
        vista.getMiToolbar().setAcciones(this);

        vista.gettBuscador().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                buscar();
            }
        });

        vista.getTable().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int fila = vista.getTable().getSelectedRow();
                if (fila >= 0) {
                    movilSeleccionado = modeloTabla.getMovilAt(fila);
                    vista.setEntidad(movilSeleccionado);
                }
            }
        });
    }

    @Override
    public void nuevo() {
        movilSeleccionado = null;
        vista.setEntidad(new Movil());
        vista.getTable().clearSelection();
        estadoInicial(false);
    }

    @Override
    public void modificar() {
        if (movilSeleccionado == null) {
            JOptionPane.showMessageDialog(vista, "Seleccione un registro de la tabla para modificar.", "Atención",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        estadoInicial(false);
    }

    @Override
    public void eliminar() {
        if (movilSeleccionado == null || movilSeleccionado.getId() == 0) {
            JOptionPane.showMessageDialog(vista, "Seleccione un registro de la tabla para eliminar.", "Atención",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        int opcion = JOptionPane.showConfirmDialog(
                vista,
                "¿Desea eliminar el móvil ID " + movilSeleccionado.getId() + "?",
                "Confirmación",
                JOptionPane.YES_NO_OPTION);

        if (opcion == JOptionPane.YES_OPTION) {
            try {
                dao.eliminar(movilSeleccionado);
                JOptionPane.showMessageDialog(vista, "Móvil eliminado correctamente.", "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);
                limpiarFormulario();
                cargarDatosTabla();
                estadoInicial(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(vista, "Error al eliminar: " + e.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }

    @Override
    public void guardar() {
        try {
            Movil m = vista.getEntidad();
            if (movilSeleccionado != null) {
                m.setId(movilSeleccionado.getId());
            }

            if (m.getDescripcion() == null || m.getDescripcion().trim().isEmpty()) {
                JOptionPane.showMessageDialog(vista, "La descripción es requerida.", "Atención",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            dao.guardar(m);
            JOptionPane.showMessageDialog(vista, "Móvil guardado exitosamente.", "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);

            limpiarFormulario();
            cargarDatosTabla();
            estadoInicial(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista, "Error al guardar: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    @Override
    public void cancelar() {
        limpiarFormulario();
        estadoInicial(true);
    }

    @Override
    public void salir() {
        vista.dispose();
    }

    public void buscar() {
        String filtro = vista.gettBuscador().getText().trim();
        if (filtro.isEmpty()) {
            cargarDatosTabla();
        } else {
            List<Movil> resultado = dao.recuperarPorFiltro(filtro);
            modeloTabla.setLista(resultado);
        }
    }

    public void cargarDatosTabla() {
        List<Movil> lista = dao.recuperarTodo();
        modeloTabla.setLista(lista);
    }

    public void limpiarFormulario() {
        movilSeleccionado = null;
        vista.setEntidad(new Movil());
        vista.getTable().clearSelection();
    }

    public ModeloTablaMovil getModeloTabla() {
        return modeloTabla;
    }
}