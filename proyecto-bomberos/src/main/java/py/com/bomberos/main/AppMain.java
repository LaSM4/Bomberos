package py.com.bomberos.main;

import javax.swing.SwingUtilities;
import py.com.bomberos.vista.MenuPrincipalView;

public class AppMain {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			MenuPrincipalView menuPrincipal = new MenuPrincipalView();
			menuPrincipal.setVisible(true);
		});
	}
}
