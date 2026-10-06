package py.edu.facitec.reutilizacion.interfaces;

public interface VistaABM<T> {
	T getEntidad();
	void setEntidad(T entidad);
	void estadoInicial(boolean esInicial);
}
