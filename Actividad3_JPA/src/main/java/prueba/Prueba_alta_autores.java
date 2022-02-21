package prueba;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import modelo.entidad.Autor;
import modelo.entidad.Editorial;
import modelo.entidad.Libro;
import modelo.entidad.Libreria;

public class Prueba_alta_autores {

	
public static void main(String[] args) {
		
		EntityManagerFactory emf = 
				Persistence.createEntityManagerFactory("PruebaJPA_act3");
			
		EntityManager em = null;
		
		// Creamos tres objetos de tipo Autor
		Autor autor1 = new Autor(null, "Javier", "Marías", "20-09-1951");
		Autor autor2 = new Autor(null, "Julio", "Verne", "08-02-1828");
		Autor autor3 = new Autor(null, "Agatha", "Christie", "15-09-1890");
		
		// Creamos dos objetos de tipo editorial
		Editorial edi1 = new Editorial(null, "Espasa", "Calle Josefa Valcárcel, 42. Madrid");
		Editorial edi2 = new Editorial(null, "Galaxia", "Avenida Madrid, 44. Vigo");
		
		
		// Creamos los libros y les asignamos su autor y editorial
		Libro libro1 = new Libro(null, "Los dominios del lobo", 25.5, autor1, edi2, null);
		Libro libro2 = new Libro(null, "El monarca del tiempo", 15.9, autor1, edi1, null);
		Libro libro3 = new Libro(null, "Viaje al centro de la tierra", 12.7, autor2, edi2, null);
		Libro libro4 = new Libro(null, "La isla misteriosa", 30, autor2, edi2, null);
		Libro libro5 = new Libro(null, "De la tierra a la luna", 28.3, autor2, edi1, null);
		Libro libro6 = new Libro(null, "Muerte sobre el Nilo", 21, autor3, edi2, null);
		Libro libro7 = new Libro(null, "Asesinato en el Orient Expres", 17.5, autor3, edi1, null);
		Libro libro8 = new Libro(null, "Misterio en el caribe", 12, autor3, edi1, null);
		
		//Añadimos los libros asociados al autor a una lista, no nos olvidamos de cruzar las
		//referencias para hacerlo bidireccional, dado que a los libros ya les asignamos su autor al crearlos (relación Many to One)
		List<Libro> librosJavierMarías = new ArrayList<Libro>();
		librosJavierMarías.add(libro1);
		librosJavierMarías.add(libro2);
		//hacemos bidrecionalidad del autor Javier Marías y le asignamos sus lista de libros escritos
		autor1.setLibrosEscritos(librosJavierMarías);	
		
		// Creamos la lista de libros de Julio Verne y se los asignamos al autor para que sea bidireccional la relación
		List<Libro> librosJulioVerne = new ArrayList<Libro>();
		librosJulioVerne.add(libro3);
		librosJulioVerne.add(libro4);
		librosJulioVerne.add(libro5);
		autor2.setLibrosEscritos(librosJulioVerne);
		
		// Creamos la lista de libros de Julio Verne y se los asignamos al autor (bidireccional)
		List<Libro> librosAgathaChristie = new ArrayList<Libro>();
		librosAgathaChristie.add(libro6);
		librosAgathaChristie.add(libro7);
		librosAgathaChristie.add(libro8);
		autor3.setLibrosEscritos(librosAgathaChristie);
		
		// Aquí asociaremos a cada editorial una lista o colección de libros (relación Many to One)
		// Creamos una lista de libros que hayan sido publicados por cada editorial y se la asignamos a esa editorial (bidireccionalidad)
		List<Libro> librosEditorialEspasa = new ArrayList<Libro>();
		librosEditorialEspasa.add(libro2);
		librosEditorialEspasa.add(libro5);
		librosEditorialEspasa.add(libro7);
		librosEditorialEspasa.add(libro8);
		edi1.setLibrosPublicados(librosEditorialEspasa);	
		// Hacemos lo mismo con la segunda editorial:
		List<Libro> librosEditorialGalaxia = new ArrayList<Libro>();
		librosEditorialGalaxia.add(libro2);
		librosEditorialGalaxia.add(libro5);
		librosEditorialGalaxia.add(libro7);
		librosEditorialGalaxia.add(libro8);
		edi2.setLibrosPublicados(librosEditorialGalaxia);
		
		// Creamos los objetos librería
		Libreria libreria1 = new Libreria(null, "Casa del Libro", "Juan Pedro", "Calle Gran Vía, 29. Madrid", null);
		Libreria libreria2 = new Libreria(null, "Librería Bardón", "Luisa Abeledo", "Plaza de San Martín, 3. Madrid", null);
		
		// A continuación procederemos a crear las conexiones ManyToMany entre libros y librerías
		// Para ello tendremos que crear una lista para cada librería y asignarle unos libros
		// y una lista para cada libro y asignarle unas librerias (de manera que sea bidireccional)
		
		// Comenzamos creando una lista de libros para la librería Casa del Libro, que tendrá 4 libros
		List<Libro> librosCasaLibro = new ArrayList<Libro>();
		librosCasaLibro.add(libro1);
		librosCasaLibro.add(libro3);
		librosCasaLibro.add(libro4);
		librosCasaLibro.add(libro7);
		// A continuación asignamos esa lista a la librería:
		libreria1.setColeccionLibros(librosCasaLibro);
		
		// Repetimos la operación con la otra editorial Libros Bardón, que tendrá otros 4 libros
		List<Libro> librosBardon = new ArrayList<Libro>();
		librosBardon.add(libro2);
		librosBardon.add(libro3);
		librosBardon.add(libro4);
		librosBardon.add(libro7);
		// A continuación asignamos esa lista a la librería:
		libreria2.setColeccionLibros(librosBardon);
		
		// BIDIRECCIONALIDAD:
		// Haremos lo mismo a la inversa, asignando al objeto libro una lista de librerías (BIDIRECCIONALIDAD)
		
		List<Libreria> libreriasLibro1 = new ArrayList<Libreria>();
		libreriasLibro1.add(libreria1);
		libro1.setLibrerias(libreriasLibro1);
		
		List<Libreria> libreriasLibro2 = new ArrayList<Libreria>();
		libreriasLibro2.add(libreria2);
		libro2.setLibrerias(libreriasLibro2);	
		
		List<Libreria> libreriasLibro3 = new ArrayList<Libreria>();
		libreriasLibro3.add(libreria1);
		libreriasLibro3.add(libreria2);
		libro3.setLibrerias(libreriasLibro3);
		
		List<Libreria> libreriasLibro4 = new ArrayList<Libreria>();
		libreriasLibro4.add(libreria1);
		libreriasLibro4.add(libreria2);
		libro4.setLibrerias(libreriasLibro4);
		
		List<Libreria> libreriasLibro7 = new ArrayList<Libreria>();
		libreriasLibro7.add(libreria1);
		libreriasLibro7.add(libreria2);
		libro7.setLibrerias(libreriasLibro7);
		
		System.out.println("==============================================");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		
		
		//Como tenemos cascades de tipo PERSIST en los dos extremos da igual el objeto
		//que escojamos para hacer el persist. En este caso damos de alta el autor y deberían darse de alta con él los libros asociados	
		em.persist(autor1); 
		em.persist(autor2);
		em.persist(autor3);
		
		// DAMOS DE ALTA LOS AUTORES
		System.out.println(" ----- Dando de alta los autores  ----- ");

		em.getTransaction().commit(); 
		System.out.println(" ----- Autores dados de alta ----- ");
		
		// DAMOS DE ALTA LAS EDITORIALES
		em.persist(edi1); 
		em.persist(edi2);
		System.out.println(" ----- Dando de alta las editoriales  ----- ");

		em.getTransaction().commit(); 
		System.out.println(" ----- Editoriales dadas de alta ----- ");
		
		// DAMOS DE ALTA LOS LIBROS
		em.persist(libro1); 
		em.persist(libro2);
		em.persist(libro3);
		em.persist(libro4);
		em.persist(libro5);
		em.persist(libro6);
		em.persist(libro7);
		em.persist(libro8);

		System.out.println(" ----- Dando de alta los libros  ----- ");

		em.getTransaction().commit(); 
		System.out.println(" ----- Libros dados de alta ----- ");
		
		// DAMOS DE ALTA LAS LIBRERÍAS
		em.persist(libreria1); 
		em.persist(libreria2);
		System.out.println(" ----- Dando de alta las librerías  ----- ");

		em.getTransaction().commit(); 
		System.out.println(" ----- Librerías dadas de alta ----- ");
		
		/*
		em.persist(edi1); 
		em.persist(edi2);
		System.out.println(" ----- Dando de alta las editoriales  ----- ");
		
		em.getTransaction().commit(); 
		System.out.println(" ----- Editoriales dadas de alta ----- ");
		*/
		
		em.close();
		
		
		emf.close();
	}
}
