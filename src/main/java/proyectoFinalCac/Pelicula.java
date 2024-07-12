package proyectoFinalCac;

import java.time.LocalDate;

public class Pelicula {
	private int id;
	private String titulo;
	private LocalDate fecha_estreno;
	private String genero;
	private int duracion;
	private String director;
	private String reparto;
	private String sinopsis;
	private String poster;


	// Constructor default 
	public Pelicula() {}

	//Constructor con parametros	
	public Pelicula(int id, String titulo, LocalDate fecha_estreno, String genero, int duracion, String director,
			String reparto, String sinopsis, String poster) {
		this.id = id;
		this.titulo = titulo;
		this.fecha_estreno = fecha_estreno;
		this.genero = genero;
		this.duracion = duracion;
		this.director = director;
		this.reparto = reparto;
		this.sinopsis = sinopsis;
		this.poster = poster;
	}
	
	// Getters y Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public LocalDate getFecha_estreno() {
		return fecha_estreno;
	}

	public void setFecha_estreno(LocalDate fecha_estreno) {
		this.fecha_estreno = fecha_estreno;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getReparto() {
		return reparto;
	}

	public void setReparto(String reparto) {
		this.reparto = reparto;
	}

	public String getSinopsis() {
		return sinopsis;
	}

	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	};
	
};
