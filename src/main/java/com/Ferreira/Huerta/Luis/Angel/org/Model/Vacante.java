package com.Ferreira.Huerta.Luis.Angel.org.Model;

import java.time.LocalDate;

//import javax.validation.constraints.Min;

public class Vacante {
	private Integer id;
	private String nombre;
	private String descripcion;
	private Categoria categoria;
	private LocalDate fecha;
	
	private Integer destacado;
	private String imagen= "no-image.png";
	private String detalles;
	
	//@Min(value=0, message="No puede ser negaivo")
	private Double salario;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public Double getSalario() {
		return salario;
	}
	public void setSalario(Double salario) {
		this.salario = salario;
	}
	
	public Integer getDestacado() {
		return destacado;
	}
	public void setDestacado(Integer destacado) {
		this.destacado = destacado;
	}
	public String getDetalles() {
		return detalles;
	}
	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	@Override
	public String toString() {
		return "Vacante [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", categoria=" + categoria
				+ ", fecha=" + fecha + ", salario=" + salario + ", destacado=" + destacado + ", imagen=" + imagen
				+ ", detalles=" + detalles + "]";
	}
	
	
	
	
	
}
