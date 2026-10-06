package py.com.bomberos.modelo.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import py.com.bomberos.modelo.entidades.Bombero;

public class BomberoDAO extends GenericDAO<Bombero> {
	public BomberoDAO() {
		super(Bombero.class);
	}

	@Override
	public List<Bombero> recuperarPorFiltro(String filtro) {
		EntityManager em = getEntityManager();
		try {
			return em.createQuery(
					"SELECT b FROM Bombero b " +
							"WHERE b.nombre LIKE :filtro OR b.documento LIKE :filtro",
					Bombero.class)
					.setParameter("filtro", "%" + filtro + "%")
					.getResultList();
		} finally {
			em.close();
		}
	}
}
