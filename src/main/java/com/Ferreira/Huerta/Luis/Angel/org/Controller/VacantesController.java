package com.Ferreira.Huerta.Luis.Angel.org.Controller;

import java.beans.PropertyEditorSupport;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Ferreira.Huerta.Luis.Angel.org.Model.Vacante;
import com.Ferreira.Huerta.Luis.Angel.org.Service.IntCategorias;
import com.Ferreira.Huerta.Luis.Angel.org.Service.IntVacantes;
import com.Ferreira.Huerta.Luis.Angel.org.Utileria.Utileria;




@Controller
@RequestMapping("/vacante")
public class VacantesController {
	@Autowired
	private IntVacantes VacantesServceImp;
	
	@Autowired
	private IntCategorias CategoriaServiceImp;
	
	@ModelAttribute
	public void setGenerico(Model model) {
		model.addAttribute("categorias", CategoriaServiceImp.ObtenerTodas() );
	}
	
	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		List<Vacante> lista = VacantesServceImp.obtenerTodas();
		for(Vacante v : lista) {
			System.out.println(v);
		}
		model.addAttribute("vacantes",lista);
		model.addAttribute("total",VacantesServceImp.obtenerTodas().size());
		return "vacantes/listVacantes";
	}
	
	@GetMapping("/detalle")
	public String detalles(@RequestParam("id") int idVacante, Model model) {
		Vacante vacante = VacantesServceImp.buscarPorId(idVacante);
		model.addAttribute("vacante", vacante);
		return "vacantes/detalle";
	}
	@GetMapping("/nuevo")
	public String crear (Vacante v) {
		return "vacantes/formVacante";
	}
	@GetMapping("/consultar")
	public String consultar(@RequestParam("id")int idVacante,Model model) {
		Vacante v = VacantesServceImp.buscarPorId(idVacante);
		model.addAttribute("vacante",v);
		return "vacantes/formVacante";
	}
	@PostMapping("/guardar")
	public String guardar(@Validated Vacante v, BindingResult result,@RequestParam("archivoImagen") MultipartFile multiPart,RedirectAttributes model) {
		System.out.print(v);
		if(result.hasErrors()) {
			for(ObjectError error : result.getAllErrors()) {
				System.out.println(error.getDefaultMessage());
			}
			return "vacantes/formVacante";
		}
		if (!multiPart.isEmpty()) {

			String ruta = "c:/Empleados/src/main/resources/static/images/"; 
			String nombreImagen = Utileria.guardarArchivo(multiPart, ruta);
			if (nombreImagen != null){ 
			v.setImagen(nombreImagen);
				}
			}
		if(v.getId() == null) {
			int index = VacantesServceImp.obtenerTodas().size()-1;
			Vacante aux = VacantesServceImp.obtenerTodas().get(index);
			v.setId(aux.getId()+1);
			v.setCategoria(CategoriaServiceImp.buscarPorId(v.getCategoria().getId()));
			VacantesServceImp.guardar(v);
		}else {
			int pos = VacantesServceImp.buscarPosicion(v);
			model.addFlashAttribute("msg", "Se modifico³ el vacante");
			v.setCategoria(CategoriaServiceImp.buscarPorId(v.getCategoria().getId()));
			VacantesServceImp.modificar(pos, v);
		}

		return "redirect:/vacante/index";
	}
	@GetMapping("/eliminar")
	public String eliminar(@RequestParam("id")int idVcante) {
		VacantesServceImp.eliminar(idVcante);
		return "redirect:/vacante/index";
	}
	@GetMapping("/editar")
	public String editar(@RequestParam("id") int idCategoria , Model model) {
		Vacante vaca = VacantesServceImp.buscarPorId(idCategoria);
		model.addAttribute(vaca);
		return "vacantes/formVacante";
	}
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
      binder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
        @Override
        public void setAsText(String text) throws IllegalArgumentException{
          setValue(LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        }
        @Override
        public String getAsText() throws IllegalArgumentException {
          return DateTimeFormatter.ofPattern("yyyy-MM-dd").format((LocalDate) getValue());
        }
      });
    }
	
}
