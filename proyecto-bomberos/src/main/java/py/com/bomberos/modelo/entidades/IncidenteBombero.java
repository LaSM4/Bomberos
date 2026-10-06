package py.com.bomberos.modelo.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Table(name = "incidente_bombero")
public class IncidenteBombero {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "incidente_id")
	private Incidente incidente;
	
	@ManyToOne
	@JoinColumn(name = "bombero_id")
	private Bombero bombero;
	
	public IncidenteBombero() {}

	public IncidenteBombero(int id, Incidente incidente, Bombero bombero) {
		this.id = id;
		this.incidente = incidente;
		this.bombero = bombero;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Incidente getIncidente() {
		return incidente;
	}

	public void setIncidente(Incidente incidente) {
		this.incidente = incidente;
	}

	public Bombero getBombero() {
		return bombero;
	}

	public void setBombero(Bombero bombero) {
		this.bombero = bombero;
	}
	
	
}
