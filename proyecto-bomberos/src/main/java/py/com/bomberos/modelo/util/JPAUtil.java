package py.com.bomberos.modelo.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {
	
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("bomberosPU");
	
	public static EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
	
	public static void cerrar() {
		emf.close();
	}
}
