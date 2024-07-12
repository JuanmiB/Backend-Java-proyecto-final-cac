package proyectoFinalCac;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
public class ServicePelicula {
	
	private Conexion conexion;
	
	public ServicePelicula()
	{
		this.conexion = new Conexion();
	}
	
	// OBTENER TODAS LAS PELICULAS
	public List<Pelicula> getAllPeliculas() throws SQLException, ClassNotFoundException
	{
		List<Pelicula> peliculas = new ArrayList<>();
		Connection con = conexion.getConnection();
		String sql = "SELECT * FROM peliculas";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs =ps.executeQuery();
		
		while(rs.next()) {
			LocalDate fechaEstreno = rs.getDate("fecha_estreno").toLocalDate();
			Pelicula pelicula = new Pelicula(
					rs.getInt("id"),
					rs.getString("titulo"),
					fechaEstreno,
					rs.getString("genero"),
					rs.getInt("duracion"),
					rs.getString("director"),
					rs.getString("reparto"),
					rs.getString("sinopsis"),
					rs.getString("poster")
					);
			peliculas.add(pelicula);
		}
		rs.close();
		ps.close();
		
		return peliculas;
	}
	
	// OBTENER PELIUCLA POR ID
	public Pelicula getById(int id) throws SQLException, ClassNotFoundException {
		Pelicula pelicula = null;
				Connection con = conexion.getConnection();
				String sql = "SELECT * FROM peliculas WHERE id=?";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, id);
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) 
				{
					LocalDate fechaEstreno = rs.getDate("fecha_estreno").toLocalDate();
							pelicula = new Pelicula(
							rs.getInt("id"),
							rs.getString("titulo"),
							fechaEstreno,
							rs.getString("genero"),
							rs.getInt("duracion"),
							rs.getString("director"),
							rs.getString("reparto"),
							rs.getString("sinopsis"),
							rs.getString("poster")
							);
				}
				rs.close();
				ps.close();
				return pelicula;
	}
	
	//INSERTAR PELICULA
	public void addPelicula (Pelicula pelicula) throws SQLException, ClassNotFoundException
	{
		Connection con = conexion.getConnection();
		String sql = "INSERT INTO "
				+ "peliculas (titulo, fecha_estreno, genero, duracion, director, reparto, sinopsis, poster)"
				+ "VALUES (?,?,?,?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
	    ps.setString(1, pelicula.getTitulo());
	    ps.setDate(2, Date.valueOf(pelicula.getFecha_estreno())); 
	    ps.setString(3, pelicula.getGenero());
	    ps.setInt(4, pelicula.getDuracion());
	    ps.setString(5, pelicula.getDirector());
	    ps.setString(6, pelicula.getReparto());
	    ps.setString(7, pelicula.getSinopsis());
	    ps.setString(8, pelicula.getPoster());
	    
	    ps.executeUpdate(); 
	    
	    ps.close(); 
	    con.close(); 
	}
	
	// ELIMINAR PELICULA
	public void deletePelicula(int id) throws SQLException, ClassNotFoundException {
	    Connection con = conexion.getConnection();
	    String sql = "DELETE FROM peliculas WHERE id = ?";
	    PreparedStatement ps = con.prepareStatement(sql);
	    
	    ps.setInt(1, id); // Asigna el ID de la película a eliminar
	    
	    ps.executeUpdate(); 
	    
	    ps.close(); 
	    con.close(); 
	}
	
	public void updatePelicula (Pelicula pelicula) throws SQLException, ClassNotFoundException
	{
		Connection con = conexion.getConnection();
		String sql = "UPDATE peliculas SET titulo = ?, fecha_estreno = ?, genero = ?, duracion = ?, director = ?, reparto = ?, sinopsis = ?, poster = ? WHERE id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, pelicula.getTitulo());
	    ps.setDate(2, Date.valueOf(pelicula.getFecha_estreno())); 
	    ps.setString(3, pelicula.getGenero());
	    ps.setInt(4, pelicula.getDuracion());
	    ps.setString(5, pelicula.getDirector());
	    ps.setString(6, pelicula.getReparto());
	    ps.setString(7, pelicula.getSinopsis());
	    ps.setString(8, pelicula.getPoster());
	    ps.setInt(9, pelicula.getId());
	    ps.executeUpdate();
	    
	    ps.close();
	    con.close();
	}
	
	
	
	
}
