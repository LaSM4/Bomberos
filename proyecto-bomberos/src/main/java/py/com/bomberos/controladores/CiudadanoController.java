package py.com.bomberos.controladores;

import javax.swing.JOptionPane;

import py.com.bomberos.modelo.dao.CiudadanoDAO;
import py.com.bomberos.modelo.entidades.Ciudadano;
import py.com.bomberos.vista.CiudadanoView;
import py.edu.facitec.reutilizacion.interfaces.AccionesABM;

public class CiudadanoController implements AccionesABM {

    private final CiudadanoView vista;
    private final CiudadanoDAO dao;

    public CiudadanoController(CiudadanoView vista) {
        this.vista = vista;
        this.dao = new CiudadanoDAO();

        this.vista.getMiToolbar().setAcciones(this);

        estadoInicial(true);
        cargarTabla();
        setUpEvents();
    }

    private void estadoInicial(boolean esInicial) {
        vista.getTxtDocumento().setEnabled(!esInicial);
        vista.getTxtNombre().setEnabled(!esInicial);
        vista.getTxtApellido().setEnabled(!esInicial);
        vista.getTxtTelefono().setEnabled(!esInicial);
        vista.getTxtEmail().setEnabled(!esInicial);
        vista.getTxtDireccion().setEnabled(!esInicial);
        vista.getComboGenero().setEnabled(!esInicial);
        vista.getTxtProfesion().setEnabled(!esInicial);

        vista.getTable().setEnabled(esInicial);
        vista.getMiToolbar().estadoInicial(esInicial);
    }

    private void setUpEvents() {
        vista.gettBuscador().addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
                String texto = vista.gettBuscador().getText();
                if (texto.trim().isEmpty()) {
                    vista.getModeloTabla().setLista(dao.recuperarTodo());
                } else {
                    vista.getModeloTabla().setLista(dao.recuperarPorFiltro(texto));
                }
            }
        });

        vista.getTable().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int fila = vista.getTable().getSelectedRow();
                if (fila >= 0) {
                    Ciudadano ciudadano = vista.getModeloTabla().getCiudadanoAt(fila);
                    vista.setEntidad(ciudadano);
                }
            }
        });
    }

    private void cargarTabla() {
        vista.getModeloTabla().setLista(dao.recuperarTodo());
    }

    @Override
    public void nuevo() {
        estadoInicial(false);
        vista.setEntidad(new Ciudadano());
        vista.getTable().clearSelection();
    }

    @Override
    public void modificar() {
        if (vista.getEntidad().getId() == 0) {
            JOptionPane.showMessageDialog(vista, "Seleccione un ciudadano de la tabla.");
            return;
        }
        estadoInicial(false);
    }

    @Override
    public void eliminar() {
        Ciudadano c = vista.getEntidad();
        if (c.getId() == 0) {
            JOptionPane.showMessageDialog(vista, "Seleccione un ciudadano.");
            return;
        }

        int op = JOptionPane.showConfirmDialog(
                vista,
                "¿Desea eliminar este registro?",
                "Confirmación",
                JOptionPane.YES_NO_OPTION);

        if (op == JOptionPane.YES_OPTION) {
            try {
                dao.eliminar(c);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(vista, "Error al eliminar: " + e.getMessage());
                return;
            }
            cargarTabla();
            nuevo();
            estadoInicial(true);
            JOptionPane.showMessageDialog(vista, "Registro eliminado.");
        }
    }

    @Override
    public void guardar() {
        Ciudadano c = vista.getEntidad();
        if (c.getNombre() == null || c.getNombre().trim().isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Ingrese el nombre.");
            return;
        }

        if (c.getApellido() == null || c.getApellido().trim().isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Ingrese el apellido.");
            return;
        }

        try {
            dao.guardar(c);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista, "Error al guardar: " + e.getMessage());
            return;
        }

        cargarTabla();
        vista.setEntidad(new Ciudadano());
        vista.getTable().clearSelection();
        estadoInicial(true);
        JOptionPane.showMessageDialog(vista, "Registro guardado correctamente.");
    }

    @Override
    public void cancelar() {
        vista.setEntidad(new Ciudadano());
        vista.getTable().clearSelection();
        estadoInicial(true);
    }

    @Override
    public void salir() {
        vista.dispose();
    }
}