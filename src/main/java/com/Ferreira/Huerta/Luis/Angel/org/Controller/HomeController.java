package com.Ferreira.Huerta.Luis.Angel.org.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.Ferreira.Huerta.Luis.Angel.org.Model.Categoria;
import com.Ferreira.Huerta.Luis.Angel.org.Model.Vacante;
import com.Ferreira.Huerta.Luis.Angel.org.Service.IntCategorias;
import com.Ferreira.Huerta.Luis.Angel.org.Service.IntVacantes;

@Controller
public class HomeController {
	
	@Autowired
	private IntVacantes VacantesServceImp;
	@Autowired
	private IntCategorias CategoriasServceImp;
	
	@GetMapping("/")
	public String mostrarHome(Model model) {
		List<Vacante> lista = VacantesServceImp.obtenerTodas();
		List<Categoria> catego = CategoriasServceImp.ObtenerTodas();
		model.addAttribute("vacantes", lista);
		model.addAttribute("categorias", catego);
		return "home";
	}
}