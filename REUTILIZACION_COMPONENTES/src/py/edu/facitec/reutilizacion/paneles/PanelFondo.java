package py.edu.facitec.reutilizacion.paneles;

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PanelFondo extends JPanel{
	public PanelFondo() {
	}
	
	private Image fondo;
	
	public void setFondo(String imgFondo) {
		try {
			URL url = PanelFondo.class.getResource("/img/" + imgFondo);
			if (url != null) {
				fondo = new ImageIcon(url).getImage();
				setOpaque(false);
				repaint();
			}
		} catch (Exception e) {
			// ignore or log
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (fondo != null) {
			g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
		}
	}
}
