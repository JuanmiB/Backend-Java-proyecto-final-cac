package proyectoFinalCac;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import java.io.IOException;

/**
 * Servlet implementation class PeliculaServlet
 */
@WebServlet("/peliculas/*")
public class PeliculaServlet extends HttpServlet {
    private ServicePelicula servicePelicula;
    private ObjectMapper objectMapper;
    
    
    @Override
    public void init() throws ServletException {
        servicePelicula = new ServicePelicula();
        objectMapper = new ObjectMapper();
        
     // Registrar el módulo JavaTimeModule
        objectMapper.registerModule(new JavaTimeModule());
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        
        try {
            if (pathInfo == null || pathInfo.equals("/")) {
                List<Pelicula> peliculas = servicePelicula.getAllPeliculas();
                String json = objectMapper.writeValueAsString(peliculas);
                resp.setContentType("application/json");
                resp.getWriter().write(json);
        
            } else {
                String[] pathParts = pathInfo.split("/");
                
                if (pathParts.length > 1) {
                    try {
                        int id = Integer.parseInt(pathParts[1]);
                        Pelicula pelicula = servicePelicula.getById(id);
                        if (pelicula != null) {
                            String json = objectMapper.writeValueAsString(pelicula);
                            resp.setContentType("application/json");
                            resp.getWriter().write(json);
                        } else {
                            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Pelicula no encontrada");
                        }
                    } catch (NumberFormatException e) {
                        resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido");
                    }
                } else {
                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Ruta inválida");
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();  // Imprimir la excepción en la salida del servidor
            throw new ServletException("Error al obtener los datos de la base de datos: " + e.getMessage(), e);
        } catch (Exception e) {
            e.printStackTrace();  // Imprimir la excepción en la salida del servidor
            throw new ServletException("Error en el procesamiento de la solicitud: " + e.getMessage(), e);
        }
    }
    
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	try {
    			Pelicula pelicula = objectMapper.readValue(req.getReader(), Pelicula.class);
    			servicePelicula.addPelicula(pelicula);
    			resp.setStatus(HttpServletResponse.SC_CREATED);
        } catch (JsonParseException | JsonMappingException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Error en el formato de los datos de la película: " + e.getMessage());
        } catch (SQLException e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al interactuar con la base de datos: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Clase no encontrada: " + e.getMessage());
        } catch (IOException e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error de entrada/salida: " + e.getMessage());
        }
  
    		}

    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	try {
    		Pelicula pelicula = objectMapper.readValue(req.getReader(), Pelicula.class);
    		servicePelicula.updatePelicula(pelicula);
    		resp.setStatus(HttpServletResponse.SC_CREATED);
    	} catch(SQLException | ClassNotFoundException e) {
    		throw new ServletException(e);
    	}
    }
    
    protected void doDelete (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
    	String pathInfo = req.getPathInfo();
    	try {
    		if (pathInfo != null && pathInfo.split("/").length > 1) {
    			int id = Integer.parseInt(pathInfo.split("/")[1]);
    			servicePelicula.deletePelicula(id);
    			resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
    		}
    	} catch(SQLException | ClassNotFoundException e) {
    		throw new ServletException(e);
    	}
    
    }
}
