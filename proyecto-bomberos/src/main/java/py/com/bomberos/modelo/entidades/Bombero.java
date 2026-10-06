package py.com.bomberos.modelo.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import java.util.Objects;

@Entity
@Table(name = "bombero")
public class Bombero {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String documento;
	private String nombre;
	private String apellido;
	private String telefono;
	private String email;
	private String direccion;
	private String cargo;

	public Bombero() {
	}

	public Bombero(int id, String documento, String nombre, String apellido, String telefono, String email,
			String direccion, String cargo) {
		this.id = id;
		this.documento = documento;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.email = email;
		this.direccion = direccion;
		this.cargo = cargo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	@Override
	public String toString() {
		return nombre + " " + apellido;
	}

	// Se recomienda su uso por que Java compara dos registros por su memoria y no
	// por ID
	// y para evitar errores de comparacion se implementa el hashCode y equals
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bombero other = (Bombero) obj;
		return id == other.id;
	}

}
