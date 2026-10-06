package py.com.bomberos.controladores;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;

import py.com.bomberos.modelo.dao.BomberoDAO;
import py.com.bomberos.modelo.dao.IncidenteBomberoDAO;
import py.com.bomberos.modelo.dao.IncidenteDAO;
import py.com.bomberos.modelo.entidades.Bombero;
import py.com.bomberos.modelo.entidades.Incidente;
import py.com.bomberos.modelo.entidades.IncidenteBombero;
import py.com.bomberos.vista.IncidenteView;
import py.com.bomberos.vista.modelotabla.ModeloTablaIncidente;
import py.edu.facitec.reutilizacion.interfaces.AccionesABM;

public class IncidenteController implements AccionesABM {

    private final IncidenteView vista;
    private final IncidenteDAO dao;
    private final BomberoDAO bomberoDAO;
    private final IncidenteBomberoDAO incidenteBomberoDAO;
    private final ModeloTablaIncidente modeloTabla;
    private Incidente incidenteSeleccionado;

    public IncidenteController(IncidenteView vista) {
        this.vista = vista;
        this.dao = new IncidenteDAO();
        this.bomberoDAO = new BomberoDAO();
        this.incidenteBomberoDAO = new IncidenteBomberoDAO();
        this.modeloTabla = new ModeloTablaIncidente();

        inicializarEventos();
        cargarCombos();
        cargarDatosTabla();
        estadoInicial(true);
    }

    private void cargarCombos() {
        try {
            vista.cargarTiposIncidente(new py.com.bomberos.modelo.dao.TipoIncidenteDAO().recuperarTodo());
            vista.cargarCiudadanos(new py.com.bomberos.modelo.dao.CiudadanoDAO().recuperarTodo());
            vista.cargarBomberos(bomberoDAO.recuperarTodo());
        } catch (Exception e) {
            e.printStackTrace();
        }
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

        vista.getTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = vista.getTable().getSelectedRow();
                if (fila >= 0) {
                    incidenteSeleccionado = modeloTabla.getIncidenteAt(fila);
                    vista.setEntidad(incidenteSeleccionado);
                    cargarBomberosDelIncidente();
                }
            }
        });
    }

    private void cargarBomberosDelIncidente() {
        if (incidenteSeleccionado == null || incidenteSeleccionado.getId() == 0) {
            vista.limpiarBomberosAsignados();
            return;
        }
        try {
            List<IncidenteBombero> relaciones = incidenteBomberoDAO
                    .recuperarPorIncidente(incidenteSeleccionado.getId());
            List<Bombero> bomberos = new ArrayList<>();
            for (IncidenteBombero ib : relaciones) {
                bomberos.add(ib.getBombero());
            }
            vista.setBomberosAsignados(bomberos);
        } catch (Exception e) {
            vista.limpiarBomberosAsignados();
            e.printStackTrace();
        }
    }

    private void estadoInicial(boolean esInicial) {
        vista.getDateChooserReporte().setEnabled(!esInicial);
        vista.getSpinnerHoraReporte().setEnabled(!esInicial);
        vista.getDateChooserInicio().setEnabled(!esInicial);
        vista.getSpinnerHoraInicio().setEnabled(!esInicial);
        vista.getDateChooserFin().setEnabled(!esInicial);
        vista.getSpinnerHoraFin().setEnabled(!esInicial);
        vista.getTxtDescripcion().setEnabled(!esInicial);
        vista.getTxtDireccion().setEnabled(!esInicial);
        vista.getComboEstado().setEnabled(!esInicial);
        vista.getComboTipoIncidente().setEnabled(!esInicial);
        vista.getComboCiudadano().setEnabled(!esInicial);
        vista.getComboBombero().setEnabled(!esInicial);
        vista.getBtnAgregarBombero().setEnabled(!esInicial);
        vista.getBtnQuitarBombero().setEnabled(!esInicial);
        vista.getTable().setEnabled(esInicial);
        vista.getMiToolbar().estadoInicial(esInicial);
    }

    @Override
    public void nuevo() {
        estadoInicial(false);
        limpiarFormulario();
    }

    @Override
    public void guardar() {
        try {
            Incidente i = vista.getEntidad();

            if (incidenteSeleccionado != null) {
                i.setId(incidenteSeleccionado.getId());
            }

            if (i.getDescripcion() == null || i.getDescripcion().trim().isEmpty()) {
                JOptionPane.showMessageDialog(vista, "La descripción es un campo obligatorio.", "Atención",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Guardar el incidente primero
            dao.guardar(i);

            // Eliminar relaciones previas con bomberos
            if (incidenteSeleccionado != null && incidenteSeleccionado.getId() != 0) {
                incidenteBomberoDAO.eliminarPorIncidente(i.getId());
            }

            // Guardar todas las relaciones con los bomberos asignados
            List<Bombero> bomberosAsignados = vista.getBomberosAsignados();
            for (Bombero b : bomberosAsignados) {
                IncidenteBombero ib = new IncidenteBombero();
                ib.setIncidente(i);
                ib.setBombero(b);
                incidenteBomberoDAO.guardar(ib);
            }

            JOptionPane.showMessageDialog(vista, "Incidente guardado correctamente.", "Éxito",
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
    public void modificar() {
        if (incidenteSeleccionado == null) {
            JOptionPane.showMessageDialog(vista, "Seleccione un registro de la tabla.", "Atención",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        estadoInicial(false);
        JOptionPane.showMessageDialog(vista, "Modifique los datos y presione Guardar.");
    }

    @Override
    public void eliminar() {
        if (incidenteSeleccionado == null || incidenteSeleccionado.getId() == 0) {
            JOptionPane.showMessageDialog(vista, "Seleccione un registro de la tabla.", "Atención",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        int opcion = JOptionPane.showConfirmDialog(
                vista,
                "¿Desea eliminar el incidente ID " + incidenteSeleccionado.getId() + "?",
                "Confirmación",
                JOptionPane.YES_NO_OPTION);

        if (opcion == JOptionPane.YES_OPTION) {
            try {
                // Eliminar relaciones con bomberos primero
                incidenteBomberoDAO.eliminarPorIncidente(incidenteSeleccionado.getId());
                dao.eliminar(incidenteSeleccionado);
                JOptionPane.showMessageDialog(vista, "Incidente eliminado.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
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
    public void cancelar() {
        estadoInicial(true);
        limpiarFormulario();
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
            List<Incidente> resultado = dao.recuperarPorFiltro(filtro);
            modeloTabla.setLista(resultado);
            cargarBomberosEnTabla(resultado);
        }
    }

    public void cargarDatosTabla() {
        List<Incidente> lista = dao.recuperarTodo();
        modeloTabla.setLista(lista);
        cargarBomberosEnTabla(lista);
    }

    private void cargarBomberosEnTabla(List<Incidente> incidentes) {
        Map<Integer, String> bomberosMap = new HashMap<>();
        for (Incidente inc : incidentes) {
            if (inc.getId() != 0) {
                try {
                    List<IncidenteBombero> relaciones = incidenteBomberoDAO.recuperarPorIncidente(inc.getId());
                    if (!relaciones.isEmpty()) {
                        String nombres = relaciones.stream()
                                .map(ib -> ib.getBombero().toString())
                                .collect(Collectors.joining(", "));
                        bomberosMap.put(inc.getId(), nombres);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        modeloTabla.setBomberosMap(bomberosMap);
    }

    public void limpiarFormulario() {
        incidenteSeleccionado = null;
        vista.setEntidad(new Incidente());
        vista.limpiarBomberosAsignados();
        vista.getComboBombero().setSelectedIndex(-1);
        vista.getTable().clearSelection();
    }

    public ModeloTablaIncidente getModeloTabla() {
        return modeloTabla;
    }
}