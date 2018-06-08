package upb.webapp;

import com.sun.jersey.spi.resource.Singleton;

import upb.entity.Usuario;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

@Singleton
public class Database {
	// Create an EntityManagerFactory when you start the application.
	private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
			.createEntityManagerFactory("chas");


	public Database() {
	}

	public void closeDataBase() {
		ENTITY_MANAGER_FACTORY.close();
	}

	/**
	 * Create a new Usuario.
	 *
	 * @param nombre
	 * @param correo
	 * @param contrasena
	 * @param factura
	 * @param celular
	 * @param nit
	 */


	public Usuario create(String nombre, String correo, String contrasena, String factura, int celular, int nit) {
		// Create an EntityManager
		// System.out.println("Creando Usuario : " + nombre + " correo : " + correo + " contrasena : " + contrasena + " factura : " + factura + " celular : " + celular + " nit : " + nit);
		EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction transaction = null;
		Usuario stu = new Usuario();

		stu.setNombre(nombre);
		stu.setCorreo(correo);
		stu.setContrasena(contrasena);
		stu.setFactura(factura);
		stu.setCelular(celular);
		stu.setNit(nit);
		try {

			transaction = manager.getTransaction();
			transaction.begin();

			manager.persist(stu);

			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		} finally {
			manager.close();
		}
		return stu;
	}

	public static void main(String[] args) {
		Database a = new Database();
		a.create("pedroasd", "hola123@asd.com","holapedro","asdaspedro123",70145789,123123);
		// Create two Students
		//a.createP("P0167", "Chicle", "Sabor fruttilla", "1bs");
		// Alice will get an id 1
		// a.create("nombre", "correoppgfc@gmail.com", "ghghgj", "hghg", 23353225,77783173); //a.create(2, "Libro2", "test1"); // Bob will get an id 2
		//a.delete("pruebita45@gmail.com");

		//  a.create(3, "Libro3", "test3"); // Charlie will get an id
//
//        // Update the age of Bob using the id
//        a.update(2, "Bob", "abc");
//
//        // Delete the Alice from database
//        a.delete(1);
//
//        // Print all the Students
//        List<Usuario> libros = a.readAll();
//        if (libros != null) {
//            for (Usuario stu : libros) {
//                System.out.println(stu);
//            }
//        }
//
//        // NEVER FORGET TO CLOSE THE ENTITY_MANAGER_FACTORY
		ENTITY_MANAGER_FACTORY.close();
//    }
	}

}