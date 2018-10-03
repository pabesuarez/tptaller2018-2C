package ar.edu.unlam.tallerweb1.persistencia;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Ciudad;
import ar.edu.unlam.tallerweb1.modelo.Continente;
import ar.edu.unlam.tallerweb1.modelo.Pais;
import ar.edu.unlam.tallerweb1.modelo.Ubicacion;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class TpTest extends SpringTest{

    @SuppressWarnings("unchecked")
	@Test
    @Transactional @Rollback(true)
    //Buscar todos los paises de habla inglesa
    public void punto2Test(){
    	Session session = getSession();
    	Pais pais1 = new Pais();
    	pais1.setIdioma("espaniol");
    	Pais pais2 = new Pais();
    	pais2.setIdioma("ingles");
    	Pais pais3 = new Pais();
    	pais3.setIdioma("ingles");
    	session.save(pais1);
    	session.save(pais2);
    	session.save(pais3);
    	
    	List<Pais> lista = session.createCriteria(Pais.class)
    			.add(Restrictions.eq("idioma","ingles"))
    			.list();
    	
        assertThat(lista.size()).isEqualTo(2);
    }
    
    //TODO: buscar todos los paises de europa
    // Punto 3
 	@Test
 	@Transactional
 	@Rollback
 	public void buscarTodosPaisesEuropeos(){
 		
 		Pais elPais1 = new Pais();
 		elPais1.setNombrePais("Alemania");
 				
 		Pais elPais2 = new Pais();
 		elPais2.setNombrePais("EEUU");
 				
 		Pais elPais3 = new Pais();
 		elPais3.setNombrePais("Argentina");
 				
 		Pais elPais4 = new Pais();
 		elPais4.setNombrePais("Australia");
 		
 		Continente elContinente1 = new Continente();
 		elContinente1.setNombre("Europa");
 		Continente elContinente2 = new Continente();
 		elContinente2.setNombre("America");
 		Continente elContinente3 = new Continente();
 		elContinente3.setNombre("America");
 		Continente elContinente4 = new Continente();
 		elContinente4.setNombre("Oceania");
 		
 		//Asigno los continentes a los Paises
 		elPais1.setContinente(elContinente1);
 		elPais2.setContinente(elContinente2);
 		elPais3.setContinente(elContinente3);
 		elPais4.setContinente(elContinente4);
 		
 		//guardos los paises
 		getSession().save(elContinente1);
 		getSession().save(elContinente2);
 		getSession().save(elContinente3);
 		getSession().save(elContinente4);
 		getSession().save(elPais1);
 		getSession().save(elPais2);
 		getSession().save(elPais3);
 		getSession().save(elPais4);

 		
 		List<Pais> paises = getSession().createCriteria(Pais.class)
 										.createAlias("continente","continenteBuscado")
 										.add(Restrictions.eq("continenteBuscado.nombre", "Europa"))
 										.list();
 		
 		assertThat(paises.size()).isEqualTo(1);
 		
 	}
    
    //buscar todos los paises con capital al norte del tropico de cancer (latitud>23.43722)
    @SuppressWarnings("unchecked")
	@Test
    @Transactional @Rollback(true)
    public void punto4Test(){
    	Session session = getSession();
    	Ubicacion ubicacion1 = new Ubicacion();
    	ubicacion1.setLatitud(28.13);
    	ubicacion1.setLongitud(-0.33);
    	Ubicacion ubicacion2 = new Ubicacion();
    	ubicacion2.setLatitud(-27.66);
    	ubicacion2.setLongitud(0.33);
    	Ciudad ciudad1 = new Ciudad();
    	ciudad1.setUbicacion(ubicacion1);
    	Ciudad ciudad2 = new Ciudad();
    	ciudad2.setUbicacion(ubicacion2);
    	Pais pais1 = new Pais();
    	pais1.setCapital(ciudad1);
    	Pais pais2 = new Pais();
    	pais2.setCapital(ciudad2);
    	
    	session.save(ubicacion1);
    	session.save(ubicacion2);
    	session.save(ciudad1);
    	session.save(ciudad2);
    	session.save(pais1);
    	session.save(pais2);
    	
    	List<Pais> lista = session.createCriteria(Pais.class)
    			.createAlias("capital", "capitalBuscada")
    			.createAlias("capitalBuscada.ubicacion", "ubicacionBuscada")
    			.add(Restrictions.gt("ubicacionBuscada.latitud",23.43722))
    			.list();
    	
    	assertThat(lista.size()).isEqualTo(1);
    	
    }
    
    //TODO : buscar todos las ciudades del hemisferio sur (latitud<0)
    //Punto 5
  	@Test
  	@Transactional
  	@Rollback
  	public void buscarTodasCiudadesDelEsmiferioSur(){
  		
  		Ciudad ciudad1 = new Ciudad();
  		Ciudad ciudad2 = new Ciudad();
  		Ciudad ciudad3 = new Ciudad();
  		Ciudad ciudad4 = new Ciudad();
  		
  		ciudad1.setNombre("Berlin");
  		ciudad2.setNombre("Washigton");
  		ciudad3.setNombre("Buenos Aires");
  		ciudad4.setNombre("Camberra");
  		
  		Ubicacion ubicacion1 = new Ubicacion();
  		Ubicacion ubicacion2 = new Ubicacion();
  		Ubicacion ubicacion3 = new Ubicacion();
  		Ubicacion ubicacion4 = new Ubicacion();
  		
  		ubicacion1.setLatitud(30.00);
  		ubicacion2.setLatitud(28.00);
  		ubicacion3.setLatitud(-30.00);
  		ubicacion4.setLatitud(-28.00);
  				
  		ciudad1.setUbicacion(ubicacion1);
  		ciudad2.setUbicacion(ubicacion2);
  		ciudad3.setUbicacion(ubicacion3);
  		ciudad4.setUbicacion(ubicacion4);
  		
  		getSession().save(ubicacion1);
  		getSession().save(ubicacion2);
  		getSession().save(ubicacion3);
  		getSession().save(ubicacion4);
  		getSession().save(ciudad1);
  		getSession().save(ciudad2);
  		getSession().save(ciudad3);
  		getSession().save(ciudad4);
  		
  		List<Ciudad> ciudades = getSession().createCriteria(Ciudad.class)
  											.createAlias("ubicacion", "ubicacionBuscada")
  											.add(Restrictions.le("ubicacionBuscada.latitud",00.00))
  											.list();
  		
  		assertThat(ciudades.size()).isEqualTo(2);
  		
  		for (Ciudad obj : ciudades) {
  			  System.out.println(obj.getNombre());
  			};
  		
  	}
}
