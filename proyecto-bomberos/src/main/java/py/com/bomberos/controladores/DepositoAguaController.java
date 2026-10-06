package py.com.bomberos.controladores;

import java.util.List;
import javax.swing.JOptionPane;

import py.com.bomberos.modelo.dao.CiudadanoDAO;
import py.com.bomberos.modelo.dao.DepositoAguaDAO;
import py.com.bomberos.modelo.dao.TipoDepositoDAO;
import py.com.bomberos.modelo.entidades.Ciudadano;
import py.com.bomberos.modelo.entidades.DepositoAgua;
import py.com.bomberos.modelo.entidades.TipoDeposito;
import py.com.bomberos.vista.DepositoAguaView;
import py.com.bomberos.vista.modelotabla.ModeloTablaDepositoAgua;
import py.edu.facitec.reutilizacion.interfaces.AccionesABM;

public class DepositoAguaController implements AccionesABM {

    private DepositoAguaView vista;
    private DepositoAguaDAO dao;
    private ModeloTablaDepositoAgua modeloTabla;
    private DepositoAgua depositoSeleccionado;

    public DepositoAguaController(DepositoAguaView vista) {
        this.vista = vista;
        this.dao = new DepositoAguaDAO();

        modeloTabla = new ModeloTablaDepositoAgua();
        vista.getTable().setModel(modeloTabla);

        vista.getMiToolbar().setAcciones(this);

        estadoInicial(true);
        cargarCombos();
        consultar();
        setUpEvents();
    }

    private void estadoInicial(boolean esInicial) {
        vista.getTxtLatitud().setEnabled(!esInicial);
        vista.getTxtLongitud().setEnabled(!esInicial);
        vista.getTxtCapacidad().setEnabled(!esInicial);
        vista.getTxtEstado().setEnabled(!esInicial);
        vista.getTxtDireccion().setEnabled(!esInicial);
        vista.getComboCiudadano().setEnabled(!esInicial);
        vista.getComboTipoDeposito().setEnabled(!esInicial);

        vista.getTable().setEnabled(esInicial);
        vista.getMiToolbar().estadoInicial(esInicial);
    }

    private void setUpEvents() {
        vista.gettBuscador().addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
                String filtro = vista.gettBuscador().getText().trim();
                if (filtro.isEmpty()) {
                    consultar();
                } else {
                    modeloTabla.setLista(dao.recuperarPorFiltro(filtro));
                }
            }
        });

        vista.getTable().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int fila = vista.getTable().getSelectedRow();
                if (fila >= 0) {
                    depositoSeleccionado = modeloTabla.getDepositoAguaAt(fila);
                    vista.setEntidad(depositoSeleccionado);
                }
            }
        });
    }

    private void cargarCombos() {
        try {
            List<Ciudadano> ciudadanos = new CiudadanoDAO().recuperarTodo();
            vista.getComboCiudadano().removeAllItems();
            for (Ciudadano c : ciudadanos) {
                vista.getComboCiudadano().addItem(c);
            }
            vista.getComboCiudadano().setSelectedIndex(-1);

            List<TipoDeposito> tipos = new TipoDepositoDAO().recuperarTodo();
            vista.getComboTipoDeposito().removeAllItems();
            for (TipoDeposito t : tipos) {
                vista.getComboTipoDeposito().addItem(t);
            }
            vista.getComboTipoDeposito().setSelectedIndex(-1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void consultar() {
        try {
            List<DepositoAgua> lista = dao.recuperarTodo();
            modeloTabla.setLista(lista);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista, "Error al consultar: " + e.getMessage());
        }
    }

    @Override
    public void nuevo() {
        estadoInicial(false);
        limpiarFormulario();
    }

    @Override
    public void modificar() {
        if (depositoSeleccionado == null) {
            JOptionPane.showMessageDialog(vista, "Seleccione un registro de la tabla.");
            return;
        }
        estadoInicial(false);
    }

    @Override
    public void eliminar() {
        int fila = vista.getTable().getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vista, "Seleccione un registro.");
            return;
        }

        DepositoAgua deposito = modeloTabla.getDepositoAguaAt(fila);
        int opcion = JOptionPane.showConfirmDialog(
                vista,
                "¿Desea eliminar el registro?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION);

        if (opcion == JOptionPane.YES_OPTION) {
            try {
                dao.eliminar(deposito);
                JOptionPane.showMessageDialog(vista, "Registro eliminado correctamente.");
                consultar();
                limpiarFormulario();
                estadoInicial(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(vista, e.getMessage());
            }
        }
    }

    @Override
    public void guardar() {
        try {
            DepositoAgua deposito = vista.getEntidad();
            if (depositoSeleccionado != null) {
                deposito.setId(depositoSeleccionado.getId());
            }

            dao.guardar(deposito);
            JOptionPane.showMessageDialog(vista, "Registro guardado correctamente.");
            consultar();
            limpiarFormulario();
            estadoInicial(true);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vista, "La capacidad debe ser un número.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista, "Error al guardar: " + e.getMessage());
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

    private void limpiarFormulario() {
        depositoSeleccionado = null;
        vista.setEntidad(new DepositoAgua());
        vista.getTable().clearSelection();
    }
}