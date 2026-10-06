package py.com.bomberos.vista.modelotabla;

import java.time.format.DateTimeFormatter;
import py.com.bomberos.modelo.entidades.RecargaMovil;
import py.edu.facitec.reutilizacion.modelotabla.ModeloTablaGenerico;

public class ModeloTablaRecargaMovil extends ModeloTablaGenerico<RecargaMovil> {

    private static final long serialVersionUID = 1L;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public ModeloTablaRecargaMovil() {
        super(new String[] {"Fecha/Hora", "Litros", "Móvil", "Bombero", "Depósito Agua", "Descripción"});
    }

    public RecargaMovil getRecargaMovilAt(int index) {
        return getEntidad(index);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
    	// TODO Auto-generated method stub
        RecargaMovil rm = getEntidad(rowIndex);
        if (rm == null) 
        	return null;

        return switch (columnIndex) {
            case 0 -> rm.getFechaHora() != null ? rm.getFechaHora().format(formatter) : "";
            case 1 -> rm.getCantidadLitros() != null ? rm.getCantidadLitros() : 0.0;
            case 2 -> rm.getMovil() != null ? rm.getMovil().getDescripcion() : "";
            case 3 -> rm.getBombero() != null ? rm.getBombero().toString() : "";
            case 4 -> rm.getDepositoAgua() != null ? rm.getDepositoAgua().toString() : "";
            case 5 -> rm.getDescripcion();
            default -> null;
        };
    }
}
