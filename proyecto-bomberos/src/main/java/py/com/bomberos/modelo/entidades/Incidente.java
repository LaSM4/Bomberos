
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
@Table (name = "incidente")
public class Incidente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;	
	private LocalDateTime fechaHoraReporte;
	private LocalDateTime fechaHoraInicio;
	private LocalDateTime fechaHoraFin;
	private String descripcion;
	private String direccion;
	private String estado;
	
	@ManyToOne
	@JoinColumn(name = "tipo_incidente_id")
	private TipoIncidente tipoIncidente;
	
	@ManyToOne
	@JoinColumn(name = "ciudadano_id")
	private Ciudadano ciudadano;
	
	public Incidente() {}

	public Incidente(int id, LocalDateTime fechaHoraReporte, LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin,
			String descripcion, String direccion, String estado, TipoIncidente tipoIncidente, Ciudadano ciudadano) {
		this.id = id;
		this.fechaHoraReporte = fechaHoraReporte;
		this.fechaHoraInicio = fechaHoraInicio;
		this.fechaHoraFin = fechaHoraFin;
		this.descripcion = descripcion;
		this.direccion = direccion;
		this.estado = estado;
		this.tipoIncidente = tipoIncidente;
		this.ciudadano = ciudadano;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getFechaHoraReporte() {
		return fechaHoraReporte;
	}

	public void setFechaHoraReporte(LocalDateTime fechaHoraReporte) {
		this.fechaHoraReporte = fechaHoraReporte;
	}

	public LocalDateTime getFechaHoraInicio() {
		return fechaHoraInicio;
	}

	public void setFechaHoraInicio(LocalDateTime fechaHoraInicio) {
		this.fechaHoraInicio = fechaHoraInicio;
	}

	public LocalDateTime getFechaHoraFin() {
		return fechaHoraFin;
	}

	public void setFechaHoraFin(LocalDateTime fechaHoraFin) {
		this.fechaHoraFin = fechaHoraFin;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public TipoIncidente getTipoIncidente() {
		return tipoIncidente;
	}

	public void setTipoIncidente(TipoIncidente tipoIncidente) {
		this.tipoIncidente = tipoIncidente;
	}

	public Ciudadano getCiudadano() {
		return ciudadano;
	}

	public void setCiudadano(Ciudadano ciudadano) {
		this.ciudadano = ciudadano;
	}

	@Override
	public String toString() {
		return descripcion != null ? descripcion : "Incidente #" + id;
	}

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
		Incidente other = (Incidente) obj;
		return id == other.id;
	}
}
