package ar.edu.unlam.tallerweb1.controladores;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.servicios.ServicioTP;

@Controller
public class ControladorTP {

	@Inject
	private ServicioTP servicioTP;
	
	@RequestMapping("/pasar-a-mayuscula/{cadena}")
	public ModelAndView pasarAMayuscula(@PathVariable String cadena) {
		ModelMap modelo = new ModelMap();
		modelo.put("cadena", cadena.toUpperCase());
		return new ModelAndView("mostrar", modelo);
	}
	
	// Pasar a minuscula
	@RequestMapping("/pasar-a-minuscula/{cadena}")
	public ModelAndView pasarAMinuscula(@PathVariable String cadena){
		ModelMap modelo = new ModelMap();
		modelo.put("cadena", cadena.toLowerCase());
		return new ModelAndView("mostrar", modelo);
	}
	
	
	@RequestMapping("/invertir-orden/{cadena}")
	public ModelAndView invertirOrden(@PathVariable String cadena) {
		ModelMap modelo = new ModelMap();
		modelo.put("cadena", servicioTP.invertirCadena(cadena));
		return new ModelAndView("mostrar", modelo);
	}

	//  Cantidad de caracteres
	@RequestMapping("/contar-caracteres/{cadena}")
	public ModelAndView contarCarateres(@PathVariable String cadena){
		ModelMap modelo = new ModelMap();
		modelo.put("cadena", cadena.length());
		return new ModelAndView("mostrar", modelo);
	}

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String inicio() {
		return "inicio";
	}

}
