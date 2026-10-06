package py.edu.facitec.reutilizacion.ventana;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import py.edu.facitec.reutilizacion.toolbar.MiToolbar;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;

public abstract class VentanaGenerica extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JTextField tBuscador;
	private MiToolbar miToolbar;
	private JPanel panelFormulario;

	/**
	 * Create the dialog.
	 */
	public VentanaGenerica() {
		setTitle(getTitulo());
		// DEFINE EL TAMAÑO DE LA VENTANA
		setBounds(100, 100, 1024, 720);
		// CENTRAR LA VENTANA
		setLocationRelativeTo(this);
		// EVITA QUE LA VENTANA PIERDA EL FOCO
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		panelFormulario = new JPanel();
		panelFormulario.setBackground(Color.LIGHT_GRAY);
		panelFormulario.setBorder(
				new TitledBorder(null, getTituloFormulario(), TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelFormulario.setBounds(12, 10, 487, 516);
		contentPanel.add(panelFormulario);
		panelFormulario.setLayout(null);

		miToolbar = new MiToolbar();
		miToolbar.setBounds(10, 572, 613, 82);
		contentPanel.add(miToolbar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(531, 9, 473, 492);
		contentPanel.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JLabel lblNewLabel = new JLabel("Buscar");
		lblNewLabel.setBounds(531, 513, 45, 13);
		contentPanel.add(lblNewLabel);

		tBuscador = new JTextField();
		tBuscador.setBounds(582, 509, 407, 19);
		contentPanel.add(tBuscador);
		tBuscador.setColumns(10);

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}

	// METODOS ABSTRACTOS PARA DEFINIR TITULOS
	protected abstract String getTitulo();

	protected abstract String getTituloFormulario();

	public JTable getTable() {
		return table;
	}

	public JTextField gettBuscador() {
		return tBuscador;
	}

	public MiToolbar getMiToolbar() {
		return miToolbar;
	}

	public JPanel getPanelFormulario() {
		return panelFormulario;
	}
}
