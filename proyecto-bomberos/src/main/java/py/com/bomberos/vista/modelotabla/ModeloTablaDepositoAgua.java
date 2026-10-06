package py.com.bomberos.vista.modelotabla;

import py.com.bomberos.modelo.entidades.DepositoAgua;
import py.edu.facitec.reutilizacion.modelotabla.ModeloTablaGenerico;

public class ModeloTablaDepositoAgua extends ModeloTablaGenerico<DepositoAgua> {

    private static final long serialVersionUID = 1L;

    public ModeloTablaDepositoAgua() {
        super(new String[] {"Dirección", "Capacidad", "Estado", "Latitud", "Longitud"});
    }

    public DepositoAgua getDepositoAguaAt(int rowIndex) {
        return getEntidad(rowIndex);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
    	// TODO Auto-generated method stub
        DepositoAgua d = getEntidad(rowIndex);
        if (d == null) 
        	return null;

        return switch (columnIndex) {
            case 0 -> d.getDireccion(); 
            case 1 -> d.getCapacidad();
            case 2 -> d.getEstado();
            case 3 -> d.getLatitud();
            case 4 -> d.getLongitud();
            default -> null;
        };
    }
}