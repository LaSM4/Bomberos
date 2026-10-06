package py.com.bomberos.modelo.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import py.com.bomberos.modelo.entidades.RecargaMovil;

public class RecargaMovilDAO extends GenericDAO<RecargaMovil> {

	public RecargaMovilDAO() {
		super(RecargaMovil.class);
	}

	@Override
	public List<RecargaMovil> recuperarPorFiltro(String filtro) {
		EntityManager em = getEntityManager();
		try {
			return em.createQuery(
					"SELECT r FROM RecargaMovil r " +
							"WHERE r.descripcion LIKE :filtro",
					RecargaMovil.class)
					.setParameter("filtro", "%" + filtro + "%")
					.getResultList();
		} finally {
			em.close();
		}
	}
}