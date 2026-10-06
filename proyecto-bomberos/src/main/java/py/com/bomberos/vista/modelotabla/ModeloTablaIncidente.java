package py.com.bomberos.vista.modelotabla;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import py.com.bomberos.modelo.entidades.Incidente;
import py.edu.facitec.reutilizacion.modelotabla.ModeloTablaGenerico;

public class ModeloTablaIncidente extends ModeloTablaGenerico<Incidente> {

    private static final long serialVersionUID = 1L;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private Map<Integer, String> bomberosMap = new HashMap<>();

    public ModeloTablaIncidente() {
        super(new String[] {"Reporte", "Inicio", "Fin", "Descripción", "Dirección", "Estado", "Bombero"});
    }

    public Incidente getIncidenteAt(int index) {
        return getEntidad(index);
    }

    public void setBomberosMap(Map<Integer, String> bomberosMap) {
        this.bomberosMap = bomberosMap;
        fireTableDataChanged();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Incidente i = getEntidad(rowIndex);
        if (i == null) 
        	return null;

        return switch (columnIndex) {
            case 0 -> i.getFechaHoraReporte() != null ? i.getFechaHoraReporte().format(formatter) : "";
            case 1 -> i.getFechaHoraInicio() != null ? i.getFechaHoraInicio().format(formatter) : "";
            case 2 -> i.getFechaHoraFin() != null ? i.getFechaHoraFin().format(formatter) : "";
            case 3 -> i.getDescripcion();
            case 4 -> i.getDireccion();
            case 5 -> i.getEstado();
            case 6 -> bomberosMap.getOrDefault(i.getId(), "");
            default -> null;
        };
    }
}
