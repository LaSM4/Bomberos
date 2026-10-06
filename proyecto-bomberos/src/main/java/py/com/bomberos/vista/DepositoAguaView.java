package py.com.bomberos.vista;

import javax.swing.*;
import py.com.bomberos.modelo.entidades.Ciudadano;
import py.com.bomberos.modelo.entidades.DepositoAgua;
import py.com.bomberos.modelo.entidades.TipoDeposito;
import py.edu.facitec.reutilizacion.ventana.VentanaGenerica;

public class DepositoAguaView extends VentanaGenerica {

    private static final long serialVersionUID = 1L;

    private JTextField txtLatitud;
    private JTextField txtLongitud;
    private JTextField txtCapacidad;
    private JTextField txtEstado;
    private JTextField txtDireccion;
    private JComboBox<Ciudadano> comboCiudadano;
    private JComboBox<TipoDeposito> comboTipoDeposito;
    private DepositoAgua depositoActual;

    public DepositoAguaView() {
        getPanelFormulario().setLayout(null);

        JLabel lblLatitud = new JLabel("Latitud:");
        lblLatitud.setBounds(22, 30, 80, 20);
        getPanelFormulario().add(lblLatitud);

        JLabel lblLongitud = new JLabel("Longitud:");
        lblLongitud.setBounds(22, 60, 80, 20);
        getPanelFormulario().add(lblLongitud);

        JLabel lblCapacidad = new JLabel("Capacidad:");
        lblCapacidad.setBounds(22, 90, 80, 20);
        getPanelFormulario().add(lblCapacidad);

        JLabel lblEstado = new JLabel("Estado:");
        lblEstado.setBounds(22, 120, 80, 20);
        getPanelFormulario().add(lblEstado);

        JLabel lblDireccion = new JLabel("Dirección:");
        lblDireccion.setBounds(22, 150, 80, 20);
        getPanelFormulario().add(lblDireccion);

        JLabel lblCiudadano = new JLabel("Ciudadano:");
        lblCiudadano.setBounds(22, 180, 80, 20);
        getPanelFormulario().add(lblCiudadano);

        JLabel lblTipoDeposito = new JLabel("Tipo Depósito:");
        lblTipoDeposito.setBounds(22, 210, 100, 20);
        getPanelFormulario().add(lblTipoDeposito);

        txtLatitud = new JTextField();
        txtLatitud.setBounds(110, 30, 200, 20);
        getPanelFormulario().add(txtLatitud);

        txtLongitud = new JTextField();
        txtLongitud.setBounds(110, 60, 200, 20);
        getPanelFormulario().add(txtLongitud);

        txtCapacidad = new JTextField();
        txtCapacidad.setBounds(110, 90, 200, 20);
        getPanelFormulario().add(txtCapacidad);

        txtEstado = new JTextField();
        txtEstado.setBounds(110, 120, 200, 20);
        getPanelFormulario().add(txtEstado);

        txtDireccion = new JTextField();
        txtDireccion.setBounds(110, 150, 200, 20);
        getPanelFormulario().add(txtDireccion);

        comboCiudadano = new JComboBox<>();
        comboCiudadano.setBounds(110, 180, 200, 20);
        getPanelFormulario().add(comboCiudadano);

        comboTipoDeposito = new JComboBox<>();
        comboTipoDeposito.setBounds(130, 210, 200, 20);
        getPanelFormulario().add(comboTipoDeposito);
    }

    public void setEntidad(DepositoAgua d) {
        if (d == null) {
            d = new DepositoAgua();
        }
        this.depositoActual = d;

        txtLatitud.setText(d.getLatitud() != null ? d.getLatitud() : "");
        txtLongitud.setText(d.getLongitud() != null ? d.getLongitud() : "");
        txtCapacidad.setText(d.getCapacidad() != null ? String.valueOf(d.getCapacidad()) : "");
        txtEstado.setText(d.getEstado() != null ? d.getEstado() : "");
        txtDireccion.setText(d.getDireccion() != null ? d.getDireccion() : "");
        comboCiudadano.setSelectedItem(d.getCiudadano());
        comboTipoDeposito.setSelectedItem(d.getTipoDeposito());
    }

    public DepositoAgua getEntidad() {
        if (this.depositoActual == null) {
            this.depositoActual = new DepositoAgua();
        }
        depositoActual.setLatitud(txtLatitud.getText().trim());
        depositoActual.setLongitud(txtLongitud.getText().trim());
        try {
            if (!txtCapacidad.getText().trim().isEmpty()) {
                depositoActual.setCapacidad(Double.parseDouble(txtCapacidad.getText().trim()));
            }
        } catch (NumberFormatException e) {
            depositoActual.setCapacidad(0.0);
        }
        depositoActual.setEstado(txtEstado.getText().trim());
        depositoActual.setDireccion(txtDireccion.getText().trim());

        Object selCiudadano = comboCiudadano.getSelectedItem();
        if (selCiudadano instanceof Ciudadano) {
            depositoActual.setCiudadano((Ciudadano) selCiudadano);
        }
        Object selTipo = comboTipoDeposito.getSelectedItem();
        if (selTipo instanceof TipoDeposito) {
            depositoActual.setTipoDeposito((TipoDeposito) selTipo);
        }
        return depositoActual;
    }

    @Override
    protected String getTitulo() {
        return "Registro de Depósitos de Agua";
    }

    @Override
    protected String getTituloFormulario() {
        return "Detalle del depósito";
    }

    public JTextField getTxtLatitud() {
        return txtLatitud;
    }

    public JTextField getTxtLongitud() {
        return txtLongitud;
    }

    public JTextField getTxtCapacidad() {
        return txtCapacidad;
    }

    public JTextField getTxtEstado() {
        return txtEstado;
    }

    public JTextField getTxtDireccion() {
        return txtDireccion;
    }

    public JComboBox<Ciudadano> getComboCiudadano() {
        return comboCiudadano;
    }

    public JComboBox<TipoDeposito> getComboTipoDeposito() {
        return comboTipoDeposito;
    }
}
