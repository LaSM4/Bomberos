package py.com.bomberos.modelo.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import py.com.bomberos.modelo.entidades.TipoDeposito;

public class TipoDepositoDAO extends GenericDAO<TipoDeposito> {

	public TipoDepositoDAO() {
		super(TipoDeposito.class);
	}

	@Override
	public List<TipoDeposito> recuperarPorFiltro(String filtro) {
		EntityManager em = getEntityManager();
		try {
			return em.createQuery(
					"SELECT t FROM TipoDeposito t WHERE t.descripcion LIKE :filtro",
					TipoDeposito.class)
					.setParameter("filtro", "%" + filtro + "%")
					.getResultList();
		} finally {
			em.close();
		}
	}
}
