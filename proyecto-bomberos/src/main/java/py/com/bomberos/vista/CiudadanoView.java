package py.com.bomberos.vista;

import java.awt.EventQueue;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import py.com.bomberos.controladores.CiudadanoController;
import py.com.bomberos.modelo.entidades.Ciudadano;
import py.com.bomberos.vista.modelotabla.ModeloTablaCiudadano;
import py.edu.facitec.reutilizacion.ventana.VentanaGenerica;

public class CiudadanoView extends VentanaGenerica {

	private static final long serialVersionUID = 1L;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtDocumento;
	private JTextField txtTelefono;
	private JTextField txtEmail;
	private JTextField txtDireccion;
	private JComboBox<String> comboGenero;
	private JTextField txtProfesion;

	private ModeloTablaCiudadano modeloTabla;
	private Ciudadano ciudadanoActual;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CiudadanoView dialog = new CiudadanoView();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setUpController();
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CiudadanoController setUpController() {
		return new CiudadanoController(this);
	}

	public CiudadanoView() {
		this.modeloTabla = new ModeloTablaCiudadano();
		if (getTable() != null) {
			getTable().setModel(modeloTabla);
		}

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
		lblDireccion.setBounds(22, 183, 82, 17);
		getPanelFormulario().add(lblDireccion);

		JLabel lblGenero = new JLabel("Genero");
		lblGenero.setBounds(22, 215, 82, 17);
		getPanelFormulario().add(lblGenero);

		JLabel lblProfesion = new JLabel("Profesion");
		lblProfesion.setBounds(22, 247, 82, 17);
		getPanelFormulario().add(lblProfesion);

		txtNombre = new JTextField();
		txtNombre.setBounds(112, 58, 294, 21);
		txtNombre.setColumns(10);
		getPanelFormulario().add(txtNombre);

		txtApellido = new JTextField();
		txtApellido.setBounds(112, 88, 294, 21);
		txtApellido.setColumns(10);
		getPanelFormulario().add(txtApellido);

		txtDocumento = new JTextField();
		txtDocumento.setBounds(112, 30, 150, 21);
		txtDocumento.setColumns(10);
		getPanelFormulario().add(txtDocumento);

		txtTelefono = new JTextField();
		txtTelefono.setBounds(112, 120, 150, 21);
		txtTelefono.setColumns(10);
		getPanelFormulario().add(txtTelefono);

		txtEmail = new JTextField();
		txtEmail.setBounds(112, 149, 294, 21);
		txtEmail.setColumns(10);
		getPanelFormulario().add(txtEmail);

		txtDireccion = new JTextField();
		txtDireccion.setBounds(112, 181, 294, 21);
		txtDireccion.setColumns(10);
		getPanelFormulario().add(txtDireccion);

		comboGenero = new JComboBox<>(
				new String[] { "Masculino", "Femenino", "Otro" });
		comboGenero.setBounds(116, 211, 120, 27);
		getPanelFormulario().add(comboGenero);

		txtProfesion = new JTextField();
		txtProfesion.setBounds(112, 245, 150, 21);
		txtProfesion.setColumns(10);
		getPanelFormulario().add(txtProfesion);
	}

	public ModeloTablaCiudadano getModeloTabla() {
		return modeloTabla;
	}

	public void setEntidad(Ciudadano c) {
		if (c == null) {
			c = new Ciudadano();
		}
		this.ciudadanoActual = c;

		txtDocumento.setText(c.getDocumento() != null ? c.getDocumento() : "");
		txtNombre.setText(c.getNombre() != null ? c.getNombre() : "");
		txtApellido.setText(c.getApellido() != null ? c.getApellido() : "");
		txtTelefono.setText(c.getTelefono() != null ? c.getTelefono() : "");
		txtEmail.setText(c.getEmail() != null ? c.getEmail() : "");
		txtDireccion.setText(c.getDireccion() != null ? c.getDireccion() : "");
		comboGenero.setSelectedItem(c.getGenero() != null ? c.getGenero() : "Masculino");
		txtProfesion.setText(c.getProfesion() != null ? c.getProfesion() : "");
	}

	public Ciudadano getEntidad() {
		if (this.ciudadanoActual == null) {
			this.ciudadanoActual = new Ciudadano();
		}

		ciudadanoActual.setDocumento(txtDocumento.getText());
		ciudadanoActual.setNombre(txtNombre.getText());
		ciudadanoActual.setApellido(txtApellido.getText());
		ciudadanoActual.setTelefono(txtTelefono.getText());
		ciudadanoActual.setEmail(txtEmail.getText());
		ciudadanoActual.setDireccion(txtDireccion.getText());
		ciudadanoActual.setGenero((String) comboGenero.getSelectedItem());
		ciudadanoActual.setProfesion(txtProfesion.getText());

		return ciudadanoActual;
	}

	@Override
	protected String getTitulo() {
		return "Registro de Ciudadano";
	}

	@Override
	protected String getTituloFormulario() {
		return "Datos del ciudadano";
	}

	public JTextField getTxtDocumento() {
		return txtDocumento;
	}

	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public JTextField getTxtApellido() {
		return txtApellido;
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

	public JComboBox<String> getComboGenero() {
		return comboGenero;
	}

	public JTextField getTxtProfesion() {
		return txtProfesion;
	}
}
