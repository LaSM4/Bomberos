package py.com.bomberos.modelo.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "deposito_agua")
public class DepositoAgua {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String latitud;
	private String longitud;
	private Double capacidad;
	private String estado;
	private String direccion;

	@ManyToOne
	@JoinColumn(name = "ciudadano_id")
	private Ciudadano ciudadano;

	@ManyToOne
	@JoinColumn(name = "tipo_deposito_id")
	private TipoDeposito tipoDeposito;

	public DepositoAgua() {
	}

	public DepositoAgua(int id, String latitud, String longitud, Double capacidad, String estado, String direccion,
			Ciudadano ciudadano, TipoDeposito tipoDeposito) {
		this.id = id;
		this.latitud = latitud;
		this.longitud = longitud;
		this.capacidad = capacidad;
		this.estado = estado;
		this.direccion = direccion;
		this.ciudadano = ciudadano;
		this.tipoDeposito = tipoDeposito;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLatitud() {
		return latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	public String getLongitud() {
		return longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	public Double getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(Double capacidad) {
		this.capacidad = capacidad;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Ciudadano getCiudadano() {
		return ciudadano;
	}

	public void setCiudadano(Ciudadano ciudadano) {
		this.ciudadano = ciudadano;
	}

	public TipoDeposito getTipoDeposito() {
		return tipoDeposito;
	}

	public void setTipoDeposito(TipoDeposito tipoDeposito) {
		this.tipoDeposito = tipoDeposito;
	}

	@Override
	public String toString() {
		return direccion + " (" + estado + ")";
	}

	// Se recomienda su uso por que Java compara dos registros por su memoria y no
	// por ID
	// y para evitar errores de comparacion se implementa el hashCode y equals
	@Override
	public int hashCode() {
		return java.util.Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DepositoAgua other = (DepositoAgua) obj;
		return id == other.id;
	}

}
