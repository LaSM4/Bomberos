package py.edu.facitec.reutilizacion.ventana;

import javax.swing.JDialog;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public abstract class BuscadorGenerico extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField tBuscador;
	private JTable table;

	public BuscadorGenerico() {
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(this);
		setModal(true);

		setTitle(getTitulo());

		tBuscador = new JTextField();
		getContentPane().add(tBuscador, BorderLayout.NORTH);
		tBuscador.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		scrollPane.setViewportView(table);
	}

	public JTextField gettBuscador() {
		return tBuscador;
	}

	public JTable getTable() {
		return table;
	}

	protected abstract String getTitulo();

}
