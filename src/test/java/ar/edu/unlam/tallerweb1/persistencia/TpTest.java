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
    
}
