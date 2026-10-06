package py.com.bomberos.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import py.com.bomberos.controladores.BomberoController;
import py.com.bomberos.controladores.CiudadanoController;
import py.com.bomberos.controladores.DepositoAguaController;
import py.com.bomberos.controladores.IncidenteController;
import py.com.bomberos.controladores.MovilController;
import py.com.bomberos.controladores.RecargaMovilController;
import py.com.bomberos.controladores.TipoIncidenteController;
import py.edu.facitec.reutilizacion.paneles.PanelFondo;

public class MenuPrincipalView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private BomberoView bomberoView;
	private CiudadanoView ciudadanoView;
	private IncidenteView incidenteView;
	private MovilView movilView;
	private DepositoAguaView depositoAguaView;
	private RecargaMovilView recargaMovilView;
	private TipoIncidenteView tipoIncidenteView;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				MenuPrincipalView frame = new MenuPrincipalView();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MenuPrincipalView() {
		setTitle("Sistema de Gestión de Bomberos - Menú Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 650);
		setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		// Panel de Contenido Principal (con fondo de imagen)
		PanelFondo panelFondo = new PanelFondo();
		panelFondo.setFondo("bombero_fondo.png");
		contentPane = panelFondo;
		contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
		contentPane.setLayout(new BorderLayout(15, 15));
		setContentPane(contentPane);

		// Encabezado Superior
		JLabel lblTitulo = new JLabel("SISTEMA DE GESTIÓN DE CUERPO DE BOMBEROS");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblTitulo.setForeground(new Color(180, 0, 0));
		contentPane.add(lblTitulo, BorderLayout.NORTH);

		JPanel panelMenuIzquierdo = new JPanel();
		panelMenuIzquierdo.setOpaque(false);
		panelMenuIzquierdo.setPreferredSize(new Dimension(240, 0));

		panelMenuIzquierdo.setLayout(new GridLayout(8, 1, 0, 10));

		JButton btnBomberos = crearBotonMenu("Bomberos", new Color(231, 76, 60));
		btnBomberos.addActionListener(e -> abrirBombero());
		panelMenuIzquierdo.add(btnBomberos);

		JButton btnCiudadanos = crearBotonMenu("Ciudadanos", new Color(39, 174, 96));
		btnCiudadanos.addActionListener(e -> abrirCiudadano());
		panelMenuIzquierdo.add(btnCiudadanos);

		JButton btnIncidentes = crearBotonMenu("Incidentes", new Color(142, 68, 173));
		btnIncidentes.addActionListener(e -> abrirIncidente());
		panelMenuIzquierdo.add(btnIncidentes);

		JButton btnMoviles = crearBotonMenu("Móviles", new Color(52, 152, 219));
		btnMoviles.addActionListener(e -> abrirMovil());
		panelMenuIzquierdo.add(btnMoviles);

		JButton btnDepositos = crearBotonMenu("Depósitos de Agua", new Color(243, 156, 18));
		btnDepositos.addActionListener(e -> abrirDepositoAgua());
		panelMenuIzquierdo.add(btnDepositos);

		JButton btnRecargas = crearBotonMenu("Recargas de Móviles", new Color(26, 188, 156));
		btnRecargas.addActionListener(e -> abrirRecargaMovil());
		panelMenuIzquierdo.add(btnRecargas);

		JButton btnTipos = crearBotonMenu("Tipos de Incidente", new Color(52, 73, 94));
		btnTipos.addActionListener(e -> abrirTipoIncidente());
		panelMenuIzquierdo.add(btnTipos);

		JButton btnSalir = crearBotonMenu("Salir del Sistema", new Color(192, 57, 43));
		btnSalir.addActionListener(e -> salirAplicacion());
		panelMenuIzquierdo.add(btnSalir);

		contentPane.add(panelMenuIzquierdo, BorderLayout.WEST);

		// Pie de página
		JLabel lblFooter = new JLabel("POWERED BY Lucas Saavedra - Hiago Diaz");
		lblFooter.setHorizontalAlignment(SwingConstants.CENTER);
		lblFooter.setFont(new Font("Segoe UI", Font.ITALIC, 12));
		contentPane.add(lblFooter, BorderLayout.SOUTH);

	}

	private JButton crearBotonMenu(String texto) {
		return crearBotonMenu(texto, new Color(180, 0, 0));
	}

	private JButton crearBotonMenu(String texto, Color color) {
		JButton btn = new JButton(texto);
		btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btn.setForeground(Color.WHITE);
		btn.setBackground(color);
		btn.setOpaque(true);
		btn.setContentAreaFilled(true);
		btn.setBorderPainted(false);
		btn.setFocusPainted(false);
		return btn;
	}

	private void salirAplicacion() {
		int confirm = JOptionPane.showConfirmDialog(this, "¿Desea salir de la aplicación?", "Confirmar Salida",
				JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	// Métodos de apertura de ventanas ABM
	private void abrirBombero() {
		if (bomberoView == null || !bomberoView.isDisplayable()) {
			bomberoView = new BomberoView();
			new BomberoController(bomberoView);
			bomberoView.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosed(WindowEvent e) {
					bomberoView = null;
				}
			});
		}
		bomberoView.setVisible(true);
	}

	private void abrirCiudadano() {
		if (ciudadanoView == null || !ciudadanoView.isDisplayable()) {
			ciudadanoView = new CiudadanoView();
			new CiudadanoController(ciudadanoView);
			ciudadanoView.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosed(WindowEvent e) {
					ciudadanoView = null;
				}
			});
		}
		ciudadanoView.setVisible(true);
	}

	private void abrirIncidente() {
		if (incidenteView == null || !incidenteView.isDisplayable()) {
			incidenteView = new IncidenteView();
			new IncidenteController(incidenteView);
			incidenteView.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosed(WindowEvent e) {
					incidenteView = null;
				}
			});
		}
		incidenteView.setVisible(true);
	}

	private void abrirMovil() {
		if (movilView == null || !movilView.isDisplayable()) {
			movilView = new MovilView();
			new MovilController(movilView);
			movilView.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosed(WindowEvent e) {
					movilView = null;
				}
			});
		}
		movilView.setVisible(true);
	}

	private void abrirDepositoAgua() {
		if (depositoAguaView == null || !depositoAguaView.isDisplayable()) {
			depositoAguaView = new DepositoAguaView();
			new DepositoAguaController(depositoAguaView);
			depositoAguaView.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosed(WindowEvent e) {
					depositoAguaView = null;
				}
			});
		}
		depositoAguaView.setVisible(true);
	}

	private void abrirRecargaMovil() {
		if (recargaMovilView == null || !recargaMovilView.isDisplayable()) {
			recargaMovilView = new RecargaMovilView();
			new RecargaMovilController(recargaMovilView);
			recargaMovilView.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosed(WindowEvent e) {
					recargaMovilView = null;
				}
			});
		}
		recargaMovilView.setVisible(true);
	}

	private void abrirTipoIncidente() {
		if (tipoIncidenteView == null || !tipoIncidenteView.isDisplayable()) {
			tipoIncidenteView = new TipoIncidenteView();
			new TipoIncidenteController(tipoIncidenteView);
			tipoIncidenteView.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosed(WindowEvent e) {
					tipoIncidenteView = null;
				}
			});
		}
		tipoIncidenteView.setVisible(true);
	}
}
