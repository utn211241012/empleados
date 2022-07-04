package com.Ferreira.Huerta.Luis.Angel.org.Service;

import java.util.List;

import com.Ferreira.Huerta.Luis.Angel.org.Model.Vacante;


public interface IntVacantes {
	
	public List<Vacante> obtenerTodas();
	public void guardar(Vacante vaca);
	public void eliminar(Integer idVacante);
	public Vacante buscarPorId(Integer idVacante);
	public long numVacantes();
	void modificar(int posicion, Vacante v);
	int buscarPosicion(Vacante v);
}
