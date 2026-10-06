package py.com.bomberos.vista;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import py.com.bomberos.controladores.MovilController;
import py.com.bomberos.modelo.entidades.Movil;
import py.edu.facitec.reutilizacion.ventana.VentanaGenerica;

public class MovilView extends VentanaGenerica {

	private static final long serialVersionUID = 1L;
	private JTextField txtDescripcion;
	private JTextField txtCapacidad;
	private JTextField txtTutorial;
	private JComboBox<String> comboEstado;
	private Movil movilActual;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MovilView dialog = new MovilView();
					new MovilController(dialog);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MovilController setUpController() {
		return new MovilController(this);
	}

	public MovilView() {
		getPanelFormulario().setLayout(null);

		JLabel lblDescripcion = new JLabel("Descripción: ");
		lblDescripcion.setBounds(22, 30, 93, 17);
		getPanelFormulario().add(lblDescripcion);

		JLabel lblCapacidadTanque = new JLabel("Capacidad del tanque");
		lblCapacidadTanque.setBounds(22, 58, 155, 17);
		getPanelFormulario().add(lblCapacidadTanque);

		JLabel lblUnidadMedida = new JLabel("Litros");
		lblUnidadMedida.setBounds(275, 58, 46, 17);
		getPanelFormulario().add(lblUnidadMedida);

		JLabel lblEstado = new JLabel("Estado: ");
		lblEstado.setBounds(22, 86, 86, 17);
		getPanelFormulario().add(lblEstado);

		JLabel lblTutorial = new JLabel("Tutorial de Abastecimiento");
		lblTutorial.setBounds(22, 114, 179, 17);
		getPanelFormulario().add(lblTutorial);

		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(127, 30, 277, 21);
		txtDescripcion.setColumns(10);
		getPanelFormulario().add(txtDescripcion);

		txtCapacidad = new JTextField();
		txtCapacidad.setBounds(189, 55, 74, 21);
		txtCapacidad.setColumns(10);
		txtCapacidad.setHorizontalAlignment(JTextField.RIGHT);
		getPanelFormulario().add(txtCapacidad);

		comboEstado = new JComboBox<>(
				new String[] { "Disponible", "En Mantenimiento", "En Uso", "Fuera de Servicio" });
		comboEstado.setBounds(167, 82, 179, 27);
		getPanelFormulario().add(comboEstado);

		txtTutorial = new JTextField();
		txtTutorial.setBounds(213, 112, 191, 21);
		txtTutorial.setColumns(10);
		getPanelFormulario().add(txtTutorial);
	}

	public Movil getEntidad() {
		if (this.movilActual == null) {
			this.movilActual = new Movil();
		}
		movilActual.setDescripcion(txtDescripcion.getText());

		try {
			if (!txtCapacidad.getText().trim().isEmpty()) {
				movilActual.setCapacidadTanque(Double.parseDouble(txtCapacidad.getText().trim()));
			}
		} catch (NumberFormatException e) {
			movilActual.setCapacidadTanque(0.0);
		}

		movilActual.setEstado((String) comboEstado.getSelectedItem());
		movilActual.setTutorialAbastecimiento(txtTutorial.getText());
		return movilActual;
	}

	public void setEntidad(Movil m) {
		if (m == null) {
			m = new Movil();
		}
		this.movilActual = m;
		txtDescripcion.setText(m.getDescripcion() != null ? m.getDescripcion() : "");
		txtCapacidad.setText(m.getCapacidadTanque() != null ? String.valueOf(m.getCapacidadTanque()) : "");
		comboEstado.setSelectedItem(m.getEstado() != null ? m.getEstado() : "Disponible");
		txtTutorial.setText(m.getTutorialAbastecimiento() != null ? m.getTutorialAbastecimiento() : "");
	}

	@Override
	protected String getTitulo() {
		return "Registro del Movil";
	}

	@Override
	protected String getTituloFormulario() {
		return "Detalles del movil";
	}

	public JTextField getTxtDescripcion() {
		return txtDescripcion;
	}

	public JTextField getTxtCapacidad() {
		return txtCapacidad;
	}

	public JTextField getTxtTutorial() {
		return txtTutorial;
	}

	public JComboBox<String> getComboEstado() {
		return comboEstado;
	}
}
