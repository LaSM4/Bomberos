package py.com.bomberos.vista.modelotabla;

import py.com.bomberos.modelo.entidades.Ciudadano;
import py.edu.facitec.reutilizacion.modelotabla.ModeloTablaGenerico;

public class ModeloTablaCiudadano extends ModeloTablaGenerico<Ciudadano> {

    private static final long serialVersionUID = 1L;

    public ModeloTablaCiudadano() {
        super(new String[] { "Documento", "Nombre", "Apellido", "Teléfono", "Dirección"});
    }

    public Ciudadano getCiudadanoAt(int rowIndex) {
        return getEntidad(rowIndex);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
    	// TODO Auto-generated method stub
        Ciudadano c = getEntidad(rowIndex);
        if (c == null) 
        	return null;

        return switch (columnIndex) {
            case 0 -> c.getDocumento();
            case 1 -> c.getNombre();
            case 2 -> c.getApellido();
            case 3 -> c.getTelefono();
            case 4 -> c.getDireccion();
            default -> null;
        };
    }
}