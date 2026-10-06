package py.com.bomberos.controladores;

import java.util.List;
import javax.swing.JOptionPane;

import py.com.bomberos.modelo.dao.BomberoDAO;
import py.com.bomberos.modelo.entidades.Bombero;
import py.com.bomberos.vista.BomberoView;
import py.com.bomberos.vista.modelotabla.ModeloTablaBombero;
import py.edu.facitec.reutilizacion.interfaces.AccionesABM;

public class BomberoController implements AccionesABM {

    private BomberoView vista;
    private Bombero bombero;
    private ModeloTablaBombero mtBombero;
    private List<Bombero> lista;
    private BomberoDAO dao;

    public BomberoController(BomberoView vista) {
        this.vista = vista;
        this.vista.getMiToolbar().setAcciones(this);

        mtBombero = new ModeloTablaBombero();
        this.vista.getTable().setModel(mtBombero);

        dao = new BomberoDAO();

        estadoInicial(true);
        recuperarTodo();
        setUpEvents();
    }

    private void recuperarTodo() {
        lista = dao.recuperarTodo();
        mtBombero.setLista(lista);
    }

    private void setUpEvents() {
        vista.getTable().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                cargarCampos(vista.getTable().getSelectedRow());
            }
        });

        vista.gettBuscador().addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
                buscar();
            }
        });
    }

    private void buscar() {
        String filtro = vista.gettBuscador().getText();
        if (filtro == null || filtro.trim().isEmpty()) {
            recuperarTodo();
        } else {
            lista = dao.recuperarPorFiltro(filtro);
            mtBombero.setLista(lista);
        }
    }

    private void limpiarCampos() {
        vista.setEntidad(new Bombero());
        vista.getTable().clearSelection();
        bombero = null;
    }

    private void cargarCampos(int posicion) {
        if (posicion < 0 || posicion >= lista.size()) {
            return;
        }
        bombero = lista.get(posicion);
        vista.setEntidad(bombero);
    }

    private void estadoInicial(boolean esInicial) {
        vista.getTxtDocumento().setEnabled(!esInicial);
        vista.getTxtNombre().setEnabled(!esInicial);
        vista.getTxtApellido().setEnabled(!esInicial);
        vista.getTxtTelefono().setEnabled(!esInicial);
        vista.getTxtEmail().setEnabled(!esInicial);
        vista.getTxtDireccion().setEnabled(!esInicial);
        vista.getTxtCargo().setEnabled(!esInicial);

        vista.getTable().setEnabled(esInicial);
        vista.getMiToolbar().estadoInicial(esInicial);
    }

    @Override
    public void nuevo() {
        estadoInicial(false);
        limpiarCampos();
    }

    @Override
    public void modificar() {
        if (vista.getTable().getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(vista, "Seleccione un bombero de la tabla.");
            return;
        }
        estadoInicial(false);
    }

    @Override
    public void eliminar() {
        int posicion = vista.getTable().getSelectedRow();
        if (posicion < 0) {
            JOptionPane.showMessageDialog(vista, "Seleccione un registro.");
            return;
        }

        bombero = lista.get(posicion);
        int respuesta = JOptionPane.showConfirmDialog(
                vista,
                "¿Desea eliminar este bombero?",
                "Confirmación",
                JOptionPane.YES_NO_OPTION);

        if (respuesta == JOptionPane.YES_OPTION) {
            try {
                dao.eliminar(bombero);
                recuperarTodo();
                limpiarCampos();
                JOptionPane.showMessageDialog(vista, "Registro eliminado correctamente.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(vista, e.getMessage());
            }
        }
    }

    @Override
    public void guardar() {
        Bombero b = vista.getEntidad();
        if (bombero != null) {
            b.setId(bombero.getId());
        }

        if (b.getDocumento() == null || b.getDocumento().trim().isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Ingrese el documento.");
            return;
        }

        if (b.getNombre() == null || b.getNombre().trim().isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Ingrese el nombre.");
            return;
        }

        if (b.getApellido() == null || b.getApellido().trim().isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Ingrese el apellido.");
            return;
        }

        try {
            dao.guardar(b);
            recuperarTodo();
            limpiarCampos();
            estadoInicial(true);
            JOptionPane.showMessageDialog(vista, "Registro guardado correctamente.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista, e.getMessage());
        }
    }

    @Override
    public void cancelar() {
        estadoInicial(true);
        limpiarCampos();
    }

    @Override
    public void salir() {
        vista.dispose();
    }
}