package py.com.bomberos.vista.modelotabla;

import py.com.bomberos.modelo.entidades.Movil;
import py.edu.facitec.reutilizacion.modelotabla.ModeloTablaGenerico;

public class ModeloTablaMovil extends ModeloTablaGenerico<Movil> {

    private static final long serialVersionUID = 1L;

    public ModeloTablaMovil() {
        super(new String[] {"Descripción", "Capacidad Tanque", "Estado", "Tutorial Abastecimiento"});
    }

    public Movil getMovilAt(int index) {
        return getEntidad(index);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
    	// TODO Auto-generated method stub
        Movil m = getEntidad(rowIndex);
        if (m == null) 
        	return null;

        return switch (columnIndex) {
            case 0 -> m.getDescripcion();
            case 1 -> m.getCapacidadTanque() != null ? m.getCapacidadTanque() : 0.0;
            case 2 -> m.getEstado();
            case 3 -> m.getTutorialAbastecimiento();
            default -> null;
        };
    }
}