package py.com.bomberos.modelo.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import py.com.bomberos.modelo.entidades.IncidenteBombero;

public class IncidenteBomberoDAO extends GenericDAO<IncidenteBombero> {

	public IncidenteBomberoDAO() {
		super(IncidenteBombero.class);
	}

	public List<IncidenteBombero> recuperarPorIncidente(int incidenteId) {
		EntityManager em = getEntityManager();
		try {
			return em.createQuery(
					"SELECT ib FROM IncidenteBombero ib " +
							"JOIN FETCH ib.bombero " +
							"WHERE ib.incidente.id = :incidenteId",
					IncidenteBombero.class)
					.setParameter("incidenteId", incidenteId)
					.getResultList();
		} finally {
			em.close();
		}
	}

	public void eliminarPorIncidente(int incidenteId) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			em.createQuery("DELETE FROM IncidenteBombero ib WHERE ib.incidente.id = :incidenteId")
					.setParameter("incidenteId", incidenteId)
					.executeUpdate();
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			throw e;
		} finally {
			em.close();
		}
	}

	@Override
	public List<IncidenteBombero> recuperarPorFiltro(String filtro) {
		EntityManager em = getEntityManager();
		try {
			return em.createQuery(
					"SELECT ib FROM IncidenteBombero ib " +
							"JOIN FETCH ib.bombero b " +
							"WHERE b.nombre LIKE :filtro OR b.apellido LIKE :filtro",
					IncidenteBombero.class)
					.setParameter("filtro", "%" + filtro + "%")
					.getResultList();
		} finally {
			em.close();
		}
	}
}
