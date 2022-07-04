package com.Ferreira.Huerta.Luis.Angel.org.Service;

import java.util.List;

import org.springframework.validation.annotation.Validated;

import com.Ferreira.Huerta.Luis.Angel.org.Model.Categoria;



public interface IntCategorias {
	
	public List<Categoria>ObtenerTodas();
	public void agregar (Categoria categoria);
	public Categoria buscarPorId (Integer idCategoria); 
	public void eliminar (Integer idCtargoria);
	public int totaCategorias();
	int ids();
	void modificar(int posicion, Categoria cat);
	public int buscarPosicion(@Validated Categoria categoria);
	
}
