package py.com.bomberos.vista;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;

import py.com.bomberos.controladores.IncidenteController;
import py.com.bomberos.modelo.entidades.Bombero;
import py.com.bomberos.modelo.entidades.Ciudadano;
import py.com.bomberos.modelo.entidades.Incidente;
import py.com.bomberos.modelo.entidades.TipoIncidente;
import py.edu.facitec.reutilizacion.ventana.VentanaGenerica;

public class IncidenteView extends VentanaGenerica {

    private static final long serialVersionUID = 1L;

    private JDateChooser dateChooserReporte;
    private JSpinner spinnerHoraReporte;
    private JDateChooser dateChooserInicio;
    private JSpinner spinnerHoraInicio;
    private JDateChooser dateChooserFin;
    private JSpinner spinnerHoraFin;
    private JTextField txtDescripcion;
    private JTextField txtDireccion;
    private JComboBox<String> comboEstado;
    private JComboBox<TipoIncidente> comboTipoIncidente;
    private JComboBox<Ciudadano> comboCiudadano;
    private JComboBox<Bombero> comboBombero;
    private JButton btnAgregarBombero;
    private JButton btnQuitarBombero;
    private JTable tablaBomberosAsignados;
    private DefaultTableModel modeloBomberosAsignados;
    private List<Bombero> bomberosAsignados = new ArrayList<>();

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                IncidenteView dialog = new IncidenteView();
                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                dialog.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public IncidenteController setUpController() {
        return new IncidenteController(this);
    }

    public IncidenteView() {
        getPanelFormulario().setLayout(null);

        JLabel lblfechaHoraReporte = new JLabel("Horario reporte");
        lblfechaHoraReporte.setBounds(22, 30, 102, 17);
        getPanelFormulario().add(lblfechaHoraReporte);

        JLabel lblfechaHoraInicio = new JLabel("Horario inicio");
        lblfechaHoraInicio.setBounds(22, 58, 102, 17);
        getPanelFormulario().add(lblfechaHoraInicio);

        JLabel lblfechaHoraFin = new JLabel("Horario fin");
        lblfechaHoraFin.setBounds(22, 86, 82, 17);
        getPanelFormulario().add(lblfechaHoraFin);

        JLabel lblDescripcion = new JLabel("Descripción");
        lblDescripcion.setBounds(22, 120, 82, 17);
        getPanelFormulario().add(lblDescripcion);

        JLabel lblDireccion = new JLabel("Dirección");
        lblDireccion.setBounds(22, 149, 82, 17);
        getPanelFormulario().add(lblDireccion);

        JLabel lblEstado = new JLabel("Estado");
        lblEstado.setBounds(22, 177, 82, 17);
        getPanelFormulario().add(lblEstado);

        JLabel lblTipoIncidente = new JLabel("Tipo Incidente");
        lblTipoIncidente.setBounds(22, 205, 100, 17);
        getPanelFormulario().add(lblTipoIncidente);

        JLabel lblCiudadano = new JLabel("Ciudadano");
        lblCiudadano.setBounds(22, 235, 100, 17);
        getPanelFormulario().add(lblCiudadano);

        dateChooserReporte = new JDateChooser(new Date());
        dateChooserReporte.setDateFormatString("dd/MM/yyyy");
        dateChooserReporte.setBounds(136, 30, 126, 20);
        getPanelFormulario().add(dateChooserReporte);

        spinnerHoraReporte = new JSpinner(new SpinnerDateModel(new Date(), null, null, Calendar.MINUTE));
        JSpinner.DateEditor editorReporte = new JSpinner.DateEditor(spinnerHoraReporte, "HH:mm");
        spinnerHoraReporte.setEditor(editorReporte);
        spinnerHoraReporte.setBounds(284, 30, 70, 20);
        getPanelFormulario().add(spinnerHoraReporte);

        dateChooserInicio = new JDateChooser();
        dateChooserInicio.setDateFormatString("dd/MM/yyyy");
        dateChooserInicio.setBounds(136, 58, 126, 20);
        getPanelFormulario().add(dateChooserInicio);

        spinnerHoraInicio = new JSpinner(new SpinnerDateModel(new Date(), null, null, Calendar.MINUTE));
        JSpinner.DateEditor editorInicio = new JSpinner.DateEditor(spinnerHoraInicio, "HH:mm");
        spinnerHoraInicio.setEditor(editorInicio);
        spinnerHoraInicio.setBounds(284, 56, 70, 20);
        getPanelFormulario().add(spinnerHoraInicio);

        dateChooserFin = new JDateChooser();
        dateChooserFin.setDateFormatString("dd/MM/yyyy");
        dateChooserFin.setBounds(136, 86, 126, 20);
        getPanelFormulario().add(dateChooserFin);

        spinnerHoraFin = new JSpinner(new SpinnerDateModel(new Date(), null, null, Calendar.MINUTE));
        JSpinner.DateEditor editorFin = new JSpinner.DateEditor(spinnerHoraFin, "HH:mm");
        spinnerHoraFin.setEditor(editorFin);
        spinnerHoraFin.setBounds(284, 84, 70, 20);
        getPanelFormulario().add(spinnerHoraFin);

        txtDescripcion = new JTextField();
        txtDescripcion.setBounds(110, 118, 294, 21);
        getPanelFormulario().add(txtDescripcion);

        txtDireccion = new JTextField();
        txtDireccion.setBounds(110, 147, 294, 21);
        getPanelFormulario().add(txtDireccion);

        comboEstado = new JComboBox<>(
                new String[] { "En curso", "Terminado", "Cancelado" });
        comboEstado.setBounds(110, 177, 180, 21);
        getPanelFormulario().add(comboEstado);

        comboTipoIncidente = new JComboBox<>();
        comboTipoIncidente.setBounds(136, 205, 268, 21);
        getPanelFormulario().add(comboTipoIncidente);

        comboCiudadano = new JComboBox<>();
        comboCiudadano.setBounds(136, 235, 268, 21);
        getPanelFormulario().add(comboCiudadano);

        JLabel lblBombero = new JLabel("Bombero");
        lblBombero.setBounds(24, 264, 100, 17);
        getPanelFormulario().add(lblBombero);

        comboBombero = new JComboBox<>();
        comboBombero.setBounds(136, 264, 200, 21);
        getPanelFormulario().add(comboBombero);

        btnAgregarBombero = new JButton("Agregar");
        btnAgregarBombero.setBounds(340, 264, 80, 21);
        getPanelFormulario().add(btnAgregarBombero);

        modeloBomberosAsignados = new DefaultTableModel(
                new String[] { "Nombre", "Apellido" }, 0) {
            private static final long serialVersionUID = 1L;
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaBomberosAsignados = new JTable(modeloBomberosAsignados);
        JScrollPane scrollBomberos = new JScrollPane(tablaBomberosAsignados);
        scrollBomberos.setBounds(22, 297, 200, 100);
        getPanelFormulario().add(scrollBomberos);

        btnQuitarBombero = new JButton("Quitar");
        btnQuitarBombero.setBounds(340, 290, 80, 21);
        getPanelFormulario().add(btnQuitarBombero);

        btnAgregarBombero.addActionListener(e -> agregarBombero());
        btnQuitarBombero.addActionListener(e -> quitarBombero());
    }

    private void agregarBombero() {
        Object sel = comboBombero.getSelectedItem();
        if (sel instanceof Bombero) {
            Bombero b = (Bombero) sel;
            // Evitar duplicados
            if (!bomberosAsignados.contains(b)) {
                bomberosAsignados.add(b);
                modeloBomberosAsignados.addRow(new Object[] { b.getNombre(), b.getApellido() });
            }
        }
    }

    private void quitarBombero() {
        int fila = tablaBomberosAsignados.getSelectedRow();
        if (fila >= 0) {
            bomberosAsignados.remove(fila);
            modeloBomberosAsignados.removeRow(fila);
        }
    }

    public Incidente getEntidad() {
        Incidente i = new Incidente();

        Date fechaReporte = dateChooserReporte.getDate();
        Date horaReporte = (Date) spinnerHoraReporte.getValue();
        i.setFechaHoraReporte(combinarFechaHora(fechaReporte, horaReporte));

        Date fechaInicio = dateChooserInicio.getDate();
        Date horaInicio = (Date) spinnerHoraInicio.getValue();
        i.setFechaHoraInicio(combinarFechaHora(fechaInicio, horaInicio));

        Date fechaFin = dateChooserFin.getDate();
        Date horaFin = (Date) spinnerHoraFin.getValue();
        i.setFechaHoraFin(combinarFechaHora(fechaFin, horaFin));

        i.setDescripcion(txtDescripcion.getText());
        i.setDireccion(txtDireccion.getText());
        if (comboEstado.getSelectedItem() != null) {
            i.setEstado((String) comboEstado.getSelectedItem());
        } else {
            i.setEstado(null);
        }

        Object selTipo = comboTipoIncidente.getSelectedItem();
        if (selTipo instanceof TipoIncidente) {
            i.setTipoIncidente((TipoIncidente) selTipo);
        }

        Object selCiudadano = comboCiudadano.getSelectedItem();
        if (selCiudadano instanceof Ciudadano) {
            i.setCiudadano((Ciudadano) selCiudadano);
        }

        return i;
    }

    public void setEntidad(Incidente i) {
        if (i == null) {
            i = new Incidente();
        }
        if (i.getFechaHoraReporte() != null) {
            dateChooserReporte.setDate(Date.from(i.getFechaHoraReporte().atZone(ZoneId.systemDefault()).toInstant()));
            spinnerHoraReporte.setValue(Date.from(i.getFechaHoraReporte().atZone(ZoneId.systemDefault()).toInstant()));
        } else {
            dateChooserReporte.setDate(new Date());
            spinnerHoraReporte.setValue(new Date());
        }

        if (i.getFechaHoraInicio() != null) {
            dateChooserInicio.setDate(Date.from(i.getFechaHoraInicio().atZone(ZoneId.systemDefault()).toInstant()));
            spinnerHoraInicio.setValue(Date.from(i.getFechaHoraInicio().atZone(ZoneId.systemDefault()).toInstant()));
        } else {
            dateChooserInicio.setDate(null);
        }

        if (i.getFechaHoraFin() != null) {
            dateChooserFin.setDate(Date.from(i.getFechaHoraFin().atZone(ZoneId.systemDefault()).toInstant()));
            spinnerHoraFin.setValue(Date.from(i.getFechaHoraFin().atZone(ZoneId.systemDefault()).toInstant()));
        } else {
            dateChooserFin.setDate(null);
        }

        txtDescripcion.setText(i.getDescripcion() != null ? i.getDescripcion() : "");
        txtDireccion.setText(i.getDireccion() != null ? i.getDireccion() : "");
        if (i.getEstado() != null) {
            comboEstado.setSelectedItem(i.getEstado());
        } else {
            comboEstado.setSelectedIndex(-1);
        }
        comboTipoIncidente.setSelectedItem(i.getTipoIncidente());
        comboCiudadano.setSelectedItem(i.getCiudadano());
    }

    public void cargarTiposIncidente(java.util.List<TipoIncidente> lista) {
        comboTipoIncidente.removeAllItems();
        if (lista != null) {
            for (TipoIncidente t : lista) {
                comboTipoIncidente.addItem(t);
            }
        }
        comboTipoIncidente.setSelectedIndex(-1);
    }

    public void cargarCiudadanos(java.util.List<Ciudadano> lista) {
        comboCiudadano.removeAllItems();
        if (lista != null) {
            for (Ciudadano c : lista) {
                comboCiudadano.addItem(c);
            }
        }
        comboCiudadano.setSelectedIndex(-1);
    }

    public void cargarBomberos(java.util.List<Bombero> lista) {
        comboBombero.removeAllItems();
        if (lista != null) {
            for (Bombero b : lista) {
                comboBombero.addItem(b);
            }
        }
        comboBombero.setSelectedIndex(-1);
    }

    /**
     * Establece los bomberos asignados al incidente (para edición).
     */
    public void setBomberosAsignados(List<Bombero> bomberos) {
        this.bomberosAsignados.clear();
        modeloBomberosAsignados.setRowCount(0);
        if (bomberos != null) {
            for (Bombero b : bomberos) {
                this.bomberosAsignados.add(b);
                modeloBomberosAsignados.addRow(new Object[] { b.getNombre(), b.getApellido() });
            }
        }
    }

    /**
     * Devuelve la lista de bomberos asignados en la mini-tabla.
     */
    public List<Bombero> getBomberosAsignados() {
        return new ArrayList<>(bomberosAsignados);
    }

    /**
     * Limpia la mini-tabla de bomberos asignados.
     */
    public void limpiarBomberosAsignados() {
        bomberosAsignados.clear();
        modeloBomberosAsignados.setRowCount(0);
    }

    private LocalDateTime combinarFechaHora(Date fecha, Date hora) {
        if (fecha == null || hora == null)
            return null;

        Calendar calFecha = Calendar.getInstance();
        calFecha.setTime(fecha);

        Calendar calHora = Calendar.getInstance();
        calHora.setTime(hora);

        calFecha.set(Calendar.HOUR_OF_DAY, calHora.get(Calendar.HOUR_OF_DAY));
        calFecha.set(Calendar.MINUTE, calHora.get(Calendar.MINUTE));

        return calFecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    @Override
    protected String getTitulo() {
        return "Registro de Incidentes";
    }

    @Override
    protected String getTituloFormulario() {
        return "Detalle del incidente";
    }

    public JDateChooser getDateChooserReporte() {
        return dateChooserReporte;
    }

    public JSpinner getSpinnerHoraReporte() {
        return spinnerHoraReporte;
    }

    public JDateChooser getDateChooserInicio() {
        return dateChooserInicio;
    }

    public JSpinner getSpinnerHoraInicio() {
        return spinnerHoraInicio;
    }

    public JDateChooser getDateChooserFin() {
        return dateChooserFin;
    }

    public JSpinner getSpinnerHoraFin() {
        return spinnerHoraFin;
    }

    public JTextField getTxtDescripcion() {
        return txtDescripcion;
    }

    public JTextField getTxtDireccion() {
        return txtDireccion;
    }

    public JComboBox<String> getComboEstado() {
        return comboEstado;
    }

    public JComboBox<TipoIncidente> getComboTipoIncidente() {
        return comboTipoIncidente;
    }

    public JComboBox<Ciudadano> getComboCiudadano() {
        return comboCiudadano;
    }

    public JComboBox<Bombero> getComboBombero() {
        return comboBombero;
    }

    public JButton getBtnAgregarBombero() {
        return btnAgregarBombero;
    }

    public JButton getBtnQuitarBombero() {
        return btnQuitarBombero;
    }

    public JTable getTablaBomberosAsignados() {
        return tablaBomberosAsignados;
    }
}
