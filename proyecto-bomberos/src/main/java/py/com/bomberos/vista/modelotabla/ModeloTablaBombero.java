package py.com.bomberos.vista.modelotabla;

import py.com.bomberos.modelo.entidades.Bombero;
import py.edu.facitec.reutilizacion.modelotabla.ModeloTablaGenerico;

public class ModeloTablaBombero extends ModeloTablaGenerico<Bombero> {

	private static final long serialVersionUID = 1L;

	public ModeloTablaBombero() {
		super(new String[] { "Documento", "Nombre", "Apellido", "Cargo" });
	}

	public Bombero getBombero(int fila) {
		return getEntidad(fila);
	}

	@Override
	public Object getValueAt(int r, int c) {
		// TODO Auto-generated method stub
		Bombero bombero = getEntidad(r);
		if (bombero == null) 
			return null;
		
		return switch (c) {
		case 0 -> bombero.getDocumento();
		case 1 -> bombero.getNombre();
		case 2 -> bombero.getApellido();
		case 3 -> bombero.getCargo();
		default -> null;
		};
	}
}
