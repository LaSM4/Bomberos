package py.com.bomberos.modelo.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;

@Entity
@Table(name = "movil")
public class Movil {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String descripcion;
	private Double capacidadTanque;
	private String estado;
	private String tutorialAbastecimiento;

	public Movil() {
	}

	public Movil(int id, String descripcion, Double capacidadTanque, String estado, String tutorialAbastecimiento) {
		this.id = id;
		this.descripcion = descripcion;
		this.capacidadTanque = capacidadTanque;
		this.estado = estado;
		this.tutorialAbastecimiento = tutorialAbastecimiento;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getCapacidadTanque() {
		return capacidadTanque;
	}

	public void setCapacidadTanque(Double capacidadTanque) {
		this.capacidadTanque = capacidadTanque;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getTutorialAbastecimiento() {
		return tutorialAbastecimiento;
	}

	public void setTutorialAbastecimiento(String tutorialAbastecimiento) {
		this.tutorialAbastecimiento = tutorialAbastecimiento;
	}

	@Override
	public String toString() {
		return descripcion;
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
		Movil other = (Movil) obj;
		return id == other.id;
	}

}
