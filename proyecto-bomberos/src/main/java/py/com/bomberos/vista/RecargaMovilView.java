package py.com.bomberos.vista;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.*;
import com.toedter.calendar.JDateChooser;

import py.com.bomberos.modelo.entidades.Bombero;
import py.com.bomberos.modelo.entidades.DepositoAgua;
import py.com.bomberos.modelo.entidades.Movil;
import py.com.bomberos.modelo.entidades.RecargaMovil;
import py.edu.facitec.reutilizacion.ventana.VentanaGenerica;

public class RecargaMovilView extends VentanaGenerica {

    private static final long serialVersionUID = 1L;

    private JDateChooser dateChooserFecha;
    private JSpinner spinnerHora;
    private JTextField txtDescripcion;
    private JTextField txtCantidadLitros;
    private JComboBox<Movil> comboMovil;
    private JComboBox<Bombero> comboBombero;
    private JComboBox<DepositoAgua> comboDepositoAgua;

    public RecargaMovilView() {
        getPanelFormulario().setLayout(null);

        JLabel lblFechaHora = new JLabel("Fecha y hora");
        lblFechaHora.setBounds(22, 30, 100, 20);
        getPanelFormulario().add(lblFechaHora);

        JLabel lblDescripcion = new JLabel("Descripción");
        lblDescripcion.setBounds(22, 60, 100, 20);
        getPanelFormulario().add(lblDescripcion);

        JLabel lblCantidadLitros = new JLabel("Cantidad (L)");
        lblCantidadLitros.setBounds(22, 90, 100, 20);
        getPanelFormulario().add(lblCantidadLitros);

        JLabel lblMovil = new JLabel("Móvil");
        lblMovil.setBounds(22, 120, 100, 20);
        getPanelFormulario().add(lblMovil);

        JLabel lblBombero = new JLabel("Bombero");
        lblBombero.setBounds(22, 150, 100, 20);
        getPanelFormulario().add(lblBombero);

        JLabel lblDeposito = new JLabel("Depósito de agua");
        lblDeposito.setBounds(22, 180, 120, 20);
        getPanelFormulario().add(lblDeposito);

        dateChooserFecha = new JDateChooser();
        dateChooserFecha.setDateFormatString("dd/MM/yyyy");
        dateChooserFecha.setBounds(130, 30, 100, 20);
        getPanelFormulario().add(dateChooserFecha);

        spinnerHora = new JSpinner(new SpinnerDateModel(new Date(), null, null, Calendar.MINUTE));
        JSpinner.DateEditor editorHora = new JSpinner.DateEditor(spinnerHora, "HH:mm");
        spinnerHora.setEditor(editorHora);
        spinnerHora.setBounds(240, 30, 70, 20);
        getPanelFormulario().add(spinnerHora);

        txtDescripcion = new JTextField();
        txtDescripcion.setBounds(130, 60, 250, 20);
        getPanelFormulario().add(txtDescripcion);

        txtCantidadLitros = new JTextField();
        txtCantidadLitros.setBounds(130, 90, 100, 20);
        getPanelFormulario().add(txtCantidadLitros);

        comboMovil = new JComboBox<>();
        comboMovil.setBounds(130, 120, 250, 20);
        getPanelFormulario().add(comboMovil);

        comboBombero = new JComboBox<>();
        comboBombero.setBounds(130, 150, 250, 20);
        getPanelFormulario().add(comboBombero);

        comboDepositoAgua = new JComboBox<>();
        comboDepositoAgua.setBounds(150, 180, 230, 20);
        getPanelFormulario().add(comboDepositoAgua);
    }

    public RecargaMovil getEntidad() throws IllegalArgumentException {
        RecargaMovil rm = new RecargaMovil();

        Date fecha = dateChooserFecha.getDate();
        if (fecha == null) {
            throw new IllegalArgumentException("Debe seleccionar una fecha válida");
        }

        Date hora = (Date) spinnerHora.getValue();
        if (hora == null) {
            throw new IllegalArgumentException("Debe seleccionar una hora válida");
        }

        rm.setFechaHora(combinarFechaHora(fecha, hora));

        String descripcion = txtDescripcion.getText().trim();
        if (descripcion.isEmpty()) {
            throw new IllegalArgumentException("La descripción es obligatoria");
        }
        rm.setDescripcion(descripcion);

        String cantidadTexto = txtCantidadLitros.getText().trim();
        if (cantidadTexto.isEmpty()) {
            throw new IllegalArgumentException("Debe ingresar la cantidad de litros");
        }

        try {
            Double cantidad = Double.parseDouble(cantidadTexto);
            if (cantidad <= 0) {
                throw new IllegalArgumentException("La cantidad de litros debe ser mayor a 0");
            }
            rm.setCantidadLitros(cantidad);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("La cantidad de litros debe ser un número válido");
        }

        if (comboMovil.getItemCount() == 0) {
            throw new IllegalArgumentException("No hay móviles registrados. Primero debe registrar un móvil.");
        }
        Object selectedMovil = comboMovil.getSelectedItem();
        if (selectedMovil == null) {
            throw new IllegalArgumentException("Debe seleccionar un móvil de la lista");
        }
        rm.setMovil((Movil) selectedMovil);

        Object selectedBombero = comboBombero.getSelectedItem();
        if (selectedBombero == null) {
            throw new IllegalArgumentException("Debe seleccionar un bombero");
        }
        rm.setBombero((Bombero) selectedBombero);

        Object selectedDeposito = comboDepositoAgua.getSelectedItem();
        if (selectedDeposito == null) {
            throw new IllegalArgumentException("Debe seleccionar un depósito de agua");
        }
        rm.setDepositoAgua((DepositoAgua) selectedDeposito);

        return rm;
    }

    public void setEntidad(RecargaMovil rm) {
        if (rm == null) {
            rm = new RecargaMovil();
        }

        if (rm.getFechaHora() != null) {
            Date fechaHora = Date.from(rm.getFechaHora().atZone(ZoneId.systemDefault()).toInstant());
            dateChooserFecha.setDate(fechaHora);
            spinnerHora.setValue(fechaHora);
        } else {
            dateChooserFecha.setDate(new Date());
            spinnerHora.setValue(new Date());
        }

        txtDescripcion.setText(rm.getDescripcion() != null ? rm.getDescripcion() : "");
        txtCantidadLitros.setText(rm.getCantidadLitros() != null ? String.valueOf(rm.getCantidadLitros()) : "");

        comboMovil.setSelectedItem(rm.getMovil());
        comboBombero.setSelectedItem(rm.getBombero());
        comboDepositoAgua.setSelectedItem(rm.getDepositoAgua());
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

    public void cargarMoviles(List<Movil> lista) {
        comboMovil.removeAllItems();
        if (lista != null && !lista.isEmpty()) {
            for (Movil m : lista) {
                comboMovil.addItem(m);
            }
        }
        comboMovil.setSelectedIndex(-1);
    }

    public void cargarBomberos(List<Bombero> lista) {
        comboBombero.removeAllItems();
        if (lista != null && !lista.isEmpty()) {
            for (Bombero b : lista) {
                comboBombero.addItem(b);
            }
        }
        comboBombero.setSelectedIndex(-1);
    }

    public void cargarDepositos(List<DepositoAgua> lista) {
        comboDepositoAgua.removeAllItems();
        if (lista != null && !lista.isEmpty()) {
            for (DepositoAgua d : lista) {
                comboDepositoAgua.addItem(d);
            }
        }
        comboDepositoAgua.setSelectedIndex(-1);
    }

    @Override
    protected String getTitulo() {
        return "Registro de Recargas de Móviles";
    }

    @Override
    protected String getTituloFormulario() {
        return "Detalle de la recarga";
    }

    public JDateChooser getDateChooserFecha() {
        return dateChooserFecha;
    }

    public JSpinner getSpinnerHora() {
        return spinnerHora;
    }

    public JTextField getTxtDescripcion() {
        return txtDescripcion;
    }

    public JTextField getTxtCantidadLitros() {
        return txtCantidadLitros;
    }

    public JComboBox<Movil> getComboMovil() {
        return comboMovil;
    }

    public JComboBox<Bombero> getComboBombero() {
        return comboBombero;
    }

    public JComboBox<DepositoAgua> getComboDepositoAgua() {
        return comboDepositoAgua;
    }
}