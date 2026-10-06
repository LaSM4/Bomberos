package py.com.bomberos.vista;

import java.awt.EventQueue;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import py.com.bomberos.controladores.BomberoController;
import py.com.bomberos.modelo.entidades.Bombero;
import py.edu.facitec.reutilizacion.ventana.VentanaGenerica;

public class BomberoView extends VentanaGenerica {

	private static final long serialVersionUID = 1L;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtDocumento;
	private JTextField txtTelefono;
	private JTextField txtEmail;
	private JTextField txtDireccion;
	private JTextField txtCargo;
	private Bombero bomberoActual;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BomberoView dialog = new BomberoView();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setUpController();
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public BomberoController setUpController() {
		return new BomberoController(this);
	}

	public BomberoView() {
		getPanelFormulario().setLayout(null);

		JLabel lblDocumento = new JLabel("Documento");
		lblDocumento.setBounds(22, 30, 82, 17);
		getPanelFormulario().add(lblDocumento);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(22, 58, 82, 17);
		getPanelFormulario().add(lblNombre);

		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(22, 86, 82, 17);
		getPanelFormulario().add(lblApellido);

		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(22, 120, 82, 17);
		getPanelFormulario().add(lblTelefono);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(22, 149, 82, 17);
		getPanelFormulario().add(lblEmail);

		JLabel lblDireccion = new JLabel("Direccion");
		lblDireccion.setBounds(22, 177, 82, 17);
		getPanelFormulario().add(lblDireccion);

		JLabel lblCargo = new JLabel("Cargo");
		lblCargo.setBounds(22, 205, 82, 17);
		getPanelFormulario().add(lblCargo);

		txtNombre = new JTextField();
		txtNombre.setBounds(110, 58, 294, 21);
		txtNombre.setColumns(10);
		getPanelFormulario().add(txtNombre);

		txtApellido = new JTextField();
		txtApellido.setBounds(110, 86, 294, 21);
		txtApellido.setColumns(10);
		getPanelFormulario().add(txtApellido);

		txtDocumento = new JTextField();
		txtDocumento.setBounds(110, 30, 150, 21);
		txtDocumento.setColumns(10);
		getPanelFormulario().add(txtDocumento);

		txtTelefono = new JTextField();
		txtTelefono.setBounds(110, 118, 150, 21);
		txtTelefono.setColumns(10);
		getPanelFormulario().add(txtTelefono);

		txtEmail = new JTextField();
		txtEmail.setBounds(110, 147, 294, 21);
		txtEmail.setColumns(10);
		getPanelFormulario().add(txtEmail);

		txtDireccion = new JTextField();
		txtDireccion.setBounds(110, 177, 294, 21);
		txtDireccion.setColumns(10);
		getPanelFormulario().add(txtDireccion);

		txtCargo = new JTextField();
		txtCargo.setBounds(110, 205, 150, 21);
		txtCargo.setColumns(10);
		getPanelFormulario().add(txtCargo);
	}

	public void setEntidad(Bombero b) {
		if (b == null) {
			b = new Bombero();
		}
		this.bomberoActual = b;

		txtDocumento.setText(b.getDocumento() != null ? b.getDocumento() : "");
		txtNombre.setText(b.getNombre() != null ? b.getNombre() : "");
		txtApellido.setText(b.getApellido() != null ? b.getApellido() : "");
		txtTelefono.setText(b.getTelefono() != null ? b.getTelefono() : "");
		txtEmail.setText(b.getEmail() != null ? b.getEmail() : "");
		txtDireccion.setText(b.getDireccion() != null ? b.getDireccion() : "");
		txtCargo.setText(b.getCargo() != null ? b.getCargo() : "");
	}

	public Bombero getEntidad() {
		if (this.bomberoActual == null) {
			this.bomberoActual = new Bombero();
		}
		bomberoActual.setDocumento(txtDocumento.getText().trim());
		bomberoActual.setNombre(txtNombre.getText().trim());
		bomberoActual.setApellido(txtApellido.getText().trim());
		bomberoActual.setTelefono(txtTelefono.getText().trim());
		bomberoActual.setEmail(txtEmail.getText().trim());
		bomberoActual.setDireccion(txtDireccion.getText().trim());
		bomberoActual.setCargo(txtCargo.getText().trim());
		return bomberoActual;
	}

	@Override
	protected String getTitulo() {
		return "Registro de Bomberos";
	}

	@Override
	protected String getTituloFormulario() {
		return "Datos del bombero";
	}

	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public JTextField getTxtApellido() {
		return txtApellido;
	}

	public JTextField getTxtDocumento() {
		return txtDocumento;
	}

	public JTextField getTxtTelefono() {
		return txtTelefono;
	}

	public JTextField getTxtEmail() {
		return txtEmail;
	}

	public JTextField getTxtDireccion() {
		return txtDireccion;
	}

	public JTextField getTxtCargo() {
		return txtCargo;
	}
}
