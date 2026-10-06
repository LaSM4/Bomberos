package py.com.bomberos.controladores;

import javax.swing.JOptionPane;
import py.com.bomberos.modelo.dao.TipoIncidenteDAO;
import py.com.bomberos.modelo.entidades.TipoIncidente;
import py.com.bomberos.vista.TipoIncidenteView;
import py.edu.facitec.reutilizacion.interfaces.AccionesABM;

public class TipoIncidenteController implements AccionesABM {

    private final TipoIncidenteView vista;
    private final TipoIncidenteDAO dao;
    private TipoIncidente entidad;

    public TipoIncidenteController(TipoIncidenteView vista) {
        this.vista = vista;
        this.dao = new TipoIncidenteDAO();
        if (this.vista.getMiToolbar() != null) {
            this.vista.getMiToolbar().setAcciones(this);
        }
        inicializarEventos();
        cargarTabla();
        estadoInicial(true);
    }

    private void inicializarEventos() {
        if (vista.getTable() != null) {
            vista.getTable().getSelectionModel().addListSelectionListener(e -> {
                if (!e.getValueIsAdjusting()) {
                    int fila = vista.getTable().getSelectedRow();
                    if (fila >= 0 && vista.getModeloTabla() != null) {
                        entidad = vista.getModeloTabla().getTipoIncidente(fila);
                        vista.setEntidad(entidad);
                    }
                }
            });
        }

        if (vista.gettBuscador() != null) {
            vista.gettBuscador().addKeyListener(new java.awt.event.KeyAdapter() {
                @Override
                public void keyReleased(java.awt.event.KeyEvent e) {
                    String texto = vista.gettBuscador().getText().trim();
                    if (texto.isEmpty()) {
                        cargarTabla();
                    } else if (vista.getModeloTabla() != null) {
                        vista.getModeloTabla().setLista(dao.recuperarPorFiltro(texto));
                    }
                }
            });
        }
    }

    private void cargarTabla() {
        if (vista.getModeloTabla() != null) {
            vista.getModeloTabla().setLista(dao.recuperarTodo());
        }
    }

    private void estadoInicial(boolean esInicial) {
        vista.getTxtDescripcion().setEnabled(!esInicial);
        if (vista.getTable() != null) {
            vista.getTable().setEnabled(esInicial);
        }
        if (vista.getMiToolbar() != null) {
            vista.getMiToolbar().estadoInicial(esInicial);
        }
    }

    @Override
    public void nuevo() {
        entidad = new TipoIncidente();
        vista.setEntidad(entidad);
        if (vista.getTable() != null) {
            vista.getTable().clearSelection();
        }
        estadoInicial(false);
    }

    @Override
    public void modificar() {
        if (entidad == null || entidad.getId() == 0) {
            JOptionPane.showMessageDialog(vista, "No hay ningún registro seleccionado.");
            return;
        }
        estadoInicial(false);
    }

    @Override
    public void eliminar() {
        if (entidad == null || entidad.getId() == 0) {
            JOptionPane.showMessageDialog(vista, "No hay ningún registro seleccionado.");
            return;
        }
        int op = JOptionPane.showConfirmDialog(vista, "¿Desea eliminar este tipo de incidente?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (op == JOptionPane.YES_OPTION) {
            try {
                dao.eliminar(entidad);
                JOptionPane.showMessageDialog(vista, "Eliminado con éxito.");
                cargarTabla();
                nuevo();
                estadoInicial(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(vista, "Error al eliminar: " + e.getMessage());
            }
        }
    }

    @Override
    public void guardar() {
        TipoIncidente t = vista.getEntidad();
        if (t.getDescripcion() == null || t.getDescripcion().trim().isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Ingrese la descripción.");
            return;
        }
        try {
            dao.guardar(t);
            JOptionPane.showMessageDialog(vista, "Guardado con éxito");
            cargarTabla();
            nuevo();
            estadoInicial(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista, "Error al guardar: " + e.getMessage());
        }
    }

    @Override
    public void cancelar() {
        entidad = new TipoIncidente();
        vista.setEntidad(entidad);
        if (vista.getTable() != null) {
            vista.getTable().clearSelection();
        }
        estadoInicial(true);
    }

    @Override
    public void salir() {
        vista.dispose();
    }
}
