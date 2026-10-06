package py.com.bomberos.modelo.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import py.com.bomberos.modelo.entidades.Incidente;

public class IncidenteDAO extends GenericDAO<Incidente> {

	public IncidenteDAO() {
		super(Incidente.class);
	}

	@Override
	public List<Incidente> recuperarPorFiltro(String filtro) {
		EntityManager em = getEntityManager();
		try {
			return em.createQuery(
					"SELECT i FROM Incidente i " +
							"WHERE i.descripcion LIKE :filtro OR i.direccion LIKE :filtro",
					Incidente.class)
					.setParameter("filtro", "%" + filtro + "%")
					.getResultList();
		} finally {
			em.close();
		}
	}
}
