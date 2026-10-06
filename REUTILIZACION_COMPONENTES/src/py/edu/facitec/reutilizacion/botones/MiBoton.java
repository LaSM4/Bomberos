package py.edu.facitec.reutilizacion.botones;

import java.awt.Color;
import java.awt.Dimension;
import java.net.URL;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class MiBoton extends JButton {
	private ImageIcon icono;

	public MiBoton() {
		setForeground(Color.WHITE);
		setBackground(new Color(0, 102, 0));

		super.setText("TEXTO");

		setMaximumSize(new Dimension(100, 80));
		setPreferredSize(new Dimension(100, 80));

		setHorizontalTextPosition(CENTER);
		setVerticalTextPosition(BOTTOM);
	}

	@Override
	public void setText(String text) {
		if (icono == null && text != null) {
			setNombreIcono(text.toLowerCase());
		}
		super.setText(text);
	}

	private static final Logger logger = Logger.getLogger(MiBoton.class.getName());

	public void setNombreIcono(String nombreIcono) {
		try {
			URL url = MiBoton.class.getResource("/img/" + nombreIcono + ".png");
			if (url != null) {
				icono = new ImageIcon(url);
				this.setIcon(icono);
			}
		} catch (Exception e) {
			logger.fine("Icono no encontrado: " + nombreIcono);
		}
	}

}