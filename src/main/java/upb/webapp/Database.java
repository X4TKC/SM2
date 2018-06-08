package upb.webapp;

import com.sun.jersey.spi.resource.Singleton;
import upb.entity.Productos;
import upb.entity.Usuario;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

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
	public int delete(String correo) {
		// Create an EntityManager
		System.out.println("eliminar Usuario: " + correo);
		EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction transaction = null;

		try {
			transaction = manager.getTransaction();
			transaction.begin();

			Productos usuarioborr = manager.find(Productos.class, correo);
			// System.out.println(usuarioborr.toString());

			if (usuarioborr != null) {
				System.out.println("sargse");
				manager.remove(usuarioborr);
				transaction.commit();
			}
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		} finally {
			manager.close();
			return 0;
		}
	}

	public void modificar(String correo, Usuario usuario) {
		// Create an EntityManager

		EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction transaction = null;

		try {
			transaction = manager.getTransaction();
			transaction.begin();
			Usuario u = manager.find(Usuario.class, correo);
			u.setNombre(usuario.getNombre());
			u.setFactura(usuario.getFactura());
			u.setContrasena(usuario.getContrasena());
			u.setCelular(usuario.getCelular());
			u.setNit(usuario.getNit());
			manager.persist(u);

			// envia transaccion
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		} finally {
			manager.close();
		}
	}


	public static Usuario auth(String correo, String contrasena) {
		List<Usuario> list = null;
		EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction transaction = null;

		try {
			// Get a transaction
			transaction = manager.getTransaction();
			transaction.begin();
			// Get usuarios
			list = manager.createQuery("SELECT s FROM " + Usuario.class.getName() + " s WHERE correo = '"
					+ contrasena + "'", Usuario.class)
					.getResultList();
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();

		} finally {
			manager.close();
		}
		Usuario usuario = null;
		try{
			usuario = list.get(0);
		}catch (Exception e){
			return null;
		}
		if (usuario.getContrasena().equals(contrasena)) {
			return usuario;
		} else {
			return null;
		}
	}

	// public static void main(String[] args) {
	//  Database a = new Database();
	// Create two Students
	// a.create("Jaime", "jaime@hotmail.com", "45454548", "Paz", 78784887, 1012544452);
	// Alice will get an id 1
	//a.create(2, "Libro2", "test1"); // Bob will get an id 2
	//  a.create(3, "Libro3", "test3"); // Charlie will get an id 3
//
//        // Update the age of Bob using the id
//        a.update(2, "Bob", "abc");
//
//        // Delete the Alice from database
//        a.delete(1);
//
//        // Print all the Students
//        List<Libro> libros = a.readAll();
//        if (libros != null) {
//            for (Libro stu : libros) {
//                System.out.println(stu);
//            }
//        }
//
//        // NEVER FORGET TO CLOSE THE ENTITY_MANAGER_FACTORY
	//  ENTITY_MANAGER_FACTORY.close();
//    }
	// }

	/**
	 * Create a new Producto.
	 * @param IDProductos
	 * @param nombre_pro
	 * @param Descripcion
	 * @param precio
	 */
	public Productos createP(String IDProductos, String nombre_pro,String Descripcion, String precio){
		// Create an EntityManager
		//System.out.println("Creando Producto : " + IDProductos+ " nombre_pro : "+nombre_pro + " Descripcion : "+Descripcion + " precio : "+precio);
		EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction transaction = null;
		Productos pro=null;
		pro = new Productos();

		pro.setIDProductos(IDProductos);
		pro.setNombre_pro(nombre_pro);
		pro.setDescripcion(Descripcion);
		pro.setPrecio(precio);

		try {
			// empieza transaccon
			transaction = manager.getTransaction();
			transaction.begin();
			// crea objeto

			// guarda  persistentemente
			manager.persist(pro);
			// envia transaccion
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		} finally {
			manager.close();

		}
		return pro;
	}
	public void deleteP(String IDProductos) {
		// Create an EntityManager
		System.out.println("eliminar producto: " + IDProductos);
		EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction transaction = null;

		try {
			transaction = manager.getTransaction();
			transaction.begin();

			Productos productosborr = manager.find(Productos.class, IDProductos);
			if (productosborr != null) {
				manager.remove(productosborr);
				transaction.commit();
			}
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		} finally {
			manager.close();
		}
	}

	public void modificarP(String IDProductos, Productos productos) {
		// Create an EntityManager

		EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction transaction = null;

		try {
			transaction = manager.getTransaction();
			transaction.begin();
			Productos p = manager.find(Productos.class, IDProductos);
			p.setDescripcion(productos.getDescripcion());
			p.setNombre_pro(productos.getNombre_pro());
			p.setPrecio(productos.getPrecio());
			manager.persist(p);

			// envia transaccion
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		} finally {
			manager.close();
		}
	}

	public static void main(String[] args) {
		Database a = new Database();
		//a.create("pedroasd", "hola123@asd.com","holapedro","asdaspedro123",70145789,123123);
		//a.createP("P123","aasd","asdasd","asdasdbs");
		a.deleteP("P123");
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