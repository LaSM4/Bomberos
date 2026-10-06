package py.com.bomberos.modelo.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import py.com.bomberos.modelo.entidades.Ciudadano;

public class CiudadanoDAO extends GenericDAO<Ciudadano> {
	public CiudadanoDAO() {
		super(Ciudadano.class);
	}

	@Override
	public List<Ciudadano> recuperarPorFiltro(String filtro) {
		EntityManager em = getEntityManager();
		try {
			return em.createQuery(
					"SELECT c FROM Ciudadano c " +
							"WHERE c.nombre LIKE :filtro OR c.documento LIKE :filtro",
					Ciudadano.class)
					.setParameter("filtro", "%" + filtro + "%")
					.getResultList();
		} finally {
			em.close();
		}
	}
}
