package py.com.bomberos.vista.modelotabla;

import py.com.bomberos.modelo.entidades.TipoIncidente;
import py.edu.facitec.reutilizacion.modelotabla.ModeloTablaGenerico;

public class ModeloTablaTipoIncidente extends ModeloTablaGenerico<TipoIncidente>{
	
	private static final long serialVersionUID = 1L;
	
	public ModeloTablaTipoIncidente() {
		super(new String[] {
				"ID", "Descripción"
		});
	}
	
	public TipoIncidente getTipoIncidente(int rowIndex) {
		return getEntidad(rowIndex);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		TipoIncidente tipoIncidente = getEntidad(rowIndex);
		if (tipoIncidente == null)
			return null;
		
		return switch (columnIndex) {
			case 0 -> tipoIncidente.getId();
			case 1 -> tipoIncidente.getDescripcion();
			default -> null;
		};
	}
	
	
}
