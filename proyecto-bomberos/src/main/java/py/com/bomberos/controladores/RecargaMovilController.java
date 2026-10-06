package py.com.bomberos.controladores;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JOptionPane;

import py.com.bomberos.modelo.dao.BomberoDAO;
import py.com.bomberos.modelo.dao.DepositoAguaDAO;
import py.com.bomberos.modelo.dao.MovilDAO;
import py.com.bomberos.modelo.dao.RecargaMovilDAO;
import py.com.bomberos.modelo.entidades.RecargaMovil;
import py.com.bomberos.vista.RecargaMovilView;
import py.com.bomberos.vista.modelotabla.ModeloTablaRecargaMovil;
import py.edu.facitec.reutilizacion.interfaces.AccionesABM;

public class RecargaMovilController implements AccionesABM {

    private final RecargaMovilView vista;
    private final RecargaMovilDAO dao;
    private final ModeloTablaRecargaMovil modeloTabla;
    private RecargaMovil recargaSeleccionada;

    public RecargaMovilController(RecargaMovilView vista) {
        this.vista = vista;
        this.dao = new RecargaMovilDAO();
        this.modeloTabla = new ModeloTablaRecargaMovil();

        inicializarEventos();
        cargarCombos();
        cargarDatosTabla();
        estadoInicial(true);
    }

    private void inicializarEventos() {
        vista.getTable().setModel(modeloTabla);

        if (vista.getMiToolbar() != null) {
            vista.getMiToolbar().setAcciones(this);
        }

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
                    recargaSeleccionada = modeloTabla.getRecargaMovilAt(fila);
                    vista.setEntidad(recargaSeleccionada);
                }
            }
        });
    }

    private void cargarCombos() {
        try {
            vista.cargarMoviles(new MovilDAO().recuperarTodo());
            vista.cargarBomberos(new BomberoDAO().recuperarTodo());
            vista.cargarDepositos(new DepositoAguaDAO().recuperarTodo());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void estadoInicial(boolean esInicial) {
        vista.getDateChooserFecha().setEnabled(!esInicial);
        vista.getSpinnerHora().setEnabled(!esInicial);
        vista.getTxtDescripcion().setEnabled(!esInicial);
        vista.getTxtCantidadLitros().setEnabled(!esInicial);
        vista.getComboMovil().setEnabled(!esInicial);
        vista.getComboBombero().setEnabled(!esInicial);
        vista.getComboDepositoAgua().setEnabled(!esInicial);
        vista.getTable().setEnabled(esInicial);
        vista.getMiToolbar().estadoInicial(esInicial);
    }

    @Override
    public void nuevo() {
        estadoInicial(false);
        limpiarFormulario();
    }

    @Override
    public void modificar() {
        if (recargaSeleccionada == null) {
            JOptionPane.showMessageDialog(vista, "Seleccione un registro de la tabla.", "Atención",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        estadoInicial(false);
        JOptionPane.showMessageDialog(vista, "Modifique los datos y presione Guardar.");
    }

    @Override
    public void guardar() {
        try {
            RecargaMovil rm = vista.getEntidad();

            if (recargaSeleccionada != null) {
                rm.setId(recargaSeleccionada.getId());
            }

            if (rm.getCantidadLitros() == null || rm.getCantidadLitros() <= 0) {
                JOptionPane.showMessageDialog(vista, "Ingrese una cantidad válida de litros.", "Atención",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            dao.guardar(rm);
            JOptionPane.showMessageDialog(vista, "Recarga de móvil guardada correctamente.", "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);

            estadoInicial(true);
            limpiarFormulario();
            cargarDatosTabla();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista, "Error al guardar: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar() {
        if (recargaSeleccionada == null || recargaSeleccionada.getId() == 0) {
            JOptionPane.showMessageDialog(vista, "Seleccione un registro para eliminar.", "Atención",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        int opcion = JOptionPane.showConfirmDialog(
                vista,
                "¿Desea eliminar la recarga ID " + recargaSeleccionada.getId() + "?",
                "Confirmación",
                JOptionPane.YES_NO_OPTION);

        if (opcion == JOptionPane.YES_OPTION) {
            try {
                dao.eliminar(recargaSeleccionada);
                JOptionPane.showMessageDialog(vista, "Recarga eliminada.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
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

    public void buscar() {
        String filtro = vista.gettBuscador().getText().trim();
        if (filtro.isEmpty()) {
            cargarDatosTabla();
        } else {
            List<RecargaMovil> resultado = dao.recuperarPorFiltro(filtro);
            modeloTabla.setLista(resultado);
        }
    }

    public void cargarDatosTabla() {
        List<RecargaMovil> lista = dao.recuperarTodo();
        modeloTabla.setLista(lista);
    }

    public void limpiarFormulario() {
        recargaSeleccionada = null;
        vista.setEntidad(new RecargaMovil());
        vista.getTable().clearSelection();
    }

    public ModeloTablaRecargaMovil getModeloTabla() {
        return modeloTabla;
    }

    @Override
    public void cancelar() {
        estadoInicial(true);
        limpiarFormulario();
    }

    @Override
    public void salir() {
        vista.dispose();
    }
}
