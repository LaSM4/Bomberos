package py.edu.facitec.reutilizacion.modelotabla;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public abstract class ModeloTablaGenerico<T> extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	protected List<T> lista = new ArrayList<>();
	protected String[] columnas;

	public ModeloTablaGenerico(String[] columnas) {
		this.columnas = columnas;
	}

	public void setLista(List<T> lista) {
		this.lista = (lista != null) ? lista : new ArrayList<>();
		fireTableDataChanged();
	}

	public List<T> getLista() {
		return lista;
	}

	public T getEntidad(int fila) {
		if (fila >= 0 && fila < lista.size()) {
			return lista.get(fila);
		}
		return null;
	}

	@Override
	public int getRowCount() {
		return lista.size();
	}

	@Override
	public int getColumnCount() {
		return columnas.length;
	}

	@Override
	public String getColumnName(int col) {
		return columnas[col];
	}
}
