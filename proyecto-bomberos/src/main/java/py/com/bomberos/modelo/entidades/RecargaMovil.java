package py.com.bomberos.modelo.entidades;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "recarga_movil")
public class RecargaMovil {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private LocalDateTime fechaHora;
	private String descripcion;
	private Double cantidadLitros;

	@ManyToOne
	@JoinColumn(name = "movil_id")
	private Movil movil;

	@ManyToOne
	@JoinColumn(name = "bombero_id")
	private Bombero bombero;

	@ManyToOne
	@JoinColumn(name = "deposito_agua_id")
	private DepositoAgua depositoAgua;

	public RecargaMovil() {
	}

	public RecargaMovil(int id, LocalDateTime fechaHora, String descripcion, Double cantidadLitros, Movil movil,
			Bombero bombero, DepositoAgua depositoAgua) {
		this.id = id;
		this.fechaHora = fechaHora;
		this.descripcion = descripcion;
		this.cantidadLitros = cantidadLitros;
		this.movil = movil;
		this.bombero = bombero;
		this.depositoAgua = depositoAgua;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(LocalDateTime fechaHora) {
		this.fechaHora = fechaHora;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getCantidadLitros() {
		return cantidadLitros;
	}

	public void setCantidadLitros(Double cantidadLitros) {
		this.cantidadLitros = cantidadLitros;
	}

	public Movil getMovil() {
		return movil;
	}

	public void setMovil(Movil movil) {
		this.movil = movil;
	}

	public Bombero getBombero() {
		return bombero;
	}

	public void setBombero(Bombero bombero) {
		this.bombero = bombero;
	}

	public DepositoAgua getDepositoAgua() {
		return depositoAgua;
	}

	public void setDepositoAgua(DepositoAgua depositoAgua) {
		this.depositoAgua = depositoAgua;
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
		RecargaMovil other = (RecargaMovil) obj;
		return id == other.id;
	}

}
