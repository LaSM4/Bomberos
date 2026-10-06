package py.com.bomberos.modelo.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import py.com.bomberos.modelo.entidades.Movil;

public class MovilDAO extends GenericDAO<Movil> {

	public MovilDAO() {
		super(Movil.class);
	}

	@Override
	public List<Movil> recuperarPorFiltro(String filtro) {
		EntityManager em = getEntityManager();
		try {
			return em.createQuery(
					"SELECT m FROM Movil m " +
							"WHERE m.descripcion LIKE :filtro OR m.estado LIKE :filtro",
					Movil.class)
					.setParameter("filtro", "%" + filtro + "%")
					.getResultList();
		} finally {
			em.close();
		}
	}
}