package com.Ferreira.Huerta.Luis.Angel.org.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.Ferreira.Huerta.Luis.Angel.org.Model.Categoria;
import com.Ferreira.Huerta.Luis.Angel.org.Model.Vacante;


@Service
public class VacantesServceImp implements IntVacantes {
	
	private List<Vacante> lista;
	private List<Categoria> cat;
	
	public VacantesServceImp() {
		lista = new LinkedList<Vacante>();
		DateTimeFormatter formato= DateTimeFormatter.ofPattern("dd/mm/yy");
		
		try {
			Vacante v = new Vacante();
			v.setId(1);
			v.setNombre("Desarrollador Web");
			v.setDescripcion("Desarrollo de apps mediante lenguajes de programacion");
			v.setFecha(LocalDate.of(1998, 02, 05));
			v.setSalario(5000.00);
			v.setDestacado(1);
			v.setImagen("logo1.png");
			v.setDetalles("<h2>Ofrecemos</h2>"+"<ul><li>Salario Atractvo</li>"+"<li>Oportunidad de desarrollo</li></ul>");
			
			Categoria cate = new Categoria();
			cate.setId(1);
			cate.setNombre("TICS");
			cate.setDescripcion("Relaionado con Infomratica,telecomunicacion,iot");
			
			v.setCategoria(cate);
			
			Vacante v1 = new Vacante();
			v1.setId(2);
			v1.setNombre(" Web Desarrollador");
			v1.setDescripcion("Desarrollo de apps mediante lenguajes de programacion");
			v1.setFecha(LocalDate.of(1998, 02, 05));
			v1.setSalario(5000.00);
			v1.setDestacado(0);
			v1.setImagen("logo8.png");
			v1.setDetalles("<h2>Ofrecemos</h2>"+"<ul><li>Salario Atractvo</li>"+"<li>Oportunidad de desarrollo</li></ul>");
			
			Categoria cate1 = new Categoria();
			cate1.setId(2);
			cate1.setNombre("TICS");
			cate1.setDescripcion("Relaionado con Infomratica,telecomunicacion,iot");
			
			v1.setCategoria(cate1);
			Vacante v2 = new Vacante();
			v2.setId(3);
			v2.setNombre(" Seguridad");
			v2.setDescripcion(" Relaionado con la proteccion");
			v2.setFecha(LocalDate.of(1998, 02, 05));
			v2.setSalario(5000.00);
			v2.setDestacado(1);
			v2.setImagen("logo7.png");
			v2.setDetalles("<h2>Ofrecemos</h2>"+"<ul><li>Salario Atractvo</li>"+"<li>Oportunidad de desarrollo</li></ul>");
			
			Categoria cate3 = new Categoria();
			cate3.setId(3);
			cate3.setNombre("Automotriz");
			cate3.setDescripcion("Relaionado con diseño de autos,mecanica,etc");
			
			v2.setCategoria(cate3);
			
			lista.add(v);
			lista.add(v1);
			lista.add(v2);
		}catch(DateTimeParseException ex) {
			System.out.println("Error: "+ ex.getMessage());
		}

	}
	
	@Override
	public List<Vacante> obtenerTodas() {
		return lista;
	}

	@Override
	public void guardar(Vacante vaca) {
		lista.add(vaca);
		
	}

	@Override
	public void eliminar(Integer idVacante) {
		lista.remove(buscarPorId(idVacante));
		
	}

	@Override
	public Vacante buscarPorId(Integer idVacante) {
		for(Vacante vaca:lista) {
			if(vaca.getId() == idVacante) {
				return vaca;
			}
		}
		return null;
	}
	
	@Override
	public long numVacantes() {
		return lista.size();
	}
	@Override
	public void modificar(int posicion, Vacante v) {
		lista.set(posicion, v);
		
	}
	
	@Override
	public int buscarPosicion(Vacante v) {
		int index = 0; Vacante aux = null;
		int posicion = -1;
		while(index < lista.size()) {
			aux = lista.get(index);
			if(aux.getId()==v.getId()) {
				posicion= index;
				break;
		} index++;
		
	} return posicion;
}

}
