package com.Ferreira.Huerta.Luis.Angel.org.Controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Ferreira.Huerta.Luis.Angel.org.Model.Categoria;
import com.Ferreira.Huerta.Luis.Angel.org.Service.IntCategorias;



@RequestMapping("/categoria")
@Controller
public class CategoriaController{
	
		@Autowired
		private IntCategorias CategoriaService;
		
		@GetMapping("/index")
		public String mostrarIndex(Model model){
			List<Categoria> lista = CategoriaService.ObtenerTodas();
			for(Categoria c : lista) {
				System.out.println(c);
			}
			model.addAttribute("categorias",lista);
			return "categorias/listCategorias";
		}
		@PostMapping("/guardar")
		public String guardar(
				@Validated
				Categoria categoria, 
				BindingResult result,
				RedirectAttributes model) {
			if(result.hasErrors()) {
				System.out.println("Error");
				return "categorias/formCategoria";
			}else {
			if ( categoria.getId() == null) {
				int index = CategoriaService.ObtenerTodas().size()-1;
				Categoria aux = CategoriaService.ObtenerTodas().get(index);
				categoria.setId(aux.getId()+1);
				model.addFlashAttribute("msg", "Se guardo la categoria");
				CategoriaService.agregar(categoria);
			}else {
				int posicion = CategoriaService.buscarPosicion(categoria);
				model.addFlashAttribute("msg", "Se modificoÂ³ la categoria");
				CategoriaService.modificar(posicion, categoria);
			}
		
			return "redirect:/categoria/index";
		}
		}
		@GetMapping("/eliminar")
		public String eliminar(@RequestParam("id")int idCategoria) {
			CategoriaService.eliminar(idCategoria);
			return "redirect:/categoria/index";
		}
		
		@GetMapping("/editar")
		public String editar(@RequestParam("id") int idCategoria , Model model) {
			Categoria categoria = CategoriaService.buscarPorId(idCategoria);
			model.addAttribute(categoria);
			return "categorias/editCategoria";
		}
		
		@GetMapping("/consultar")
		public String consultar(@RequestParam("id")int idCategoria,Model model) {
			Categoria categoria = CategoriaService.buscarPorId(idCategoria);
			model.addAttribute("categoria",categoria);
			return "categorias/formCategoria";
		}
		@GetMapping("/crear")
		public String nuevaCategoria(Categoria c) {
			return "categorias/formCategoria";
		}
}