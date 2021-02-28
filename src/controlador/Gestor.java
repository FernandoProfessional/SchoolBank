package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.org.apache.bcel.internal.generic.NEW;

import modelo.bins.Cuenta;
import modelo.daos.Operaciones;

/**
 * Servlet implementation class Gestor
 */
@WebServlet("/Gestor")
public class Gestor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Gestor() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession miSesion = request.getSession();
		//creo el objeto con el que ejecutare las operaciones
		Operaciones operacion = new Operaciones();
		
		//aqui es el switch donde manejare las diferentes opciones
		switch(request.getParameter("opcion")) {
		case "cuenta":
			if(operacion.comprobarCuenta(Integer.parseInt(request.getParameter("numeroCuenta"))))
			{
				miSesion.setAttribute("numeroCuenta", request.getParameter("numeroCuenta"));
				request.getRequestDispatcher("Opciones.jsp").forward(request, response);
			}
			else {
				request.getRequestDispatcher("Login.jsp").forward(request, response);

			}
			break;
		case "ingreso":
			
			
			operacion.ingresar(Integer.parseInt((String)miSesion.getAttribute("numeroCuenta")), Double.parseDouble(request.getParameter("cantidad")));
			request.getRequestDispatcher("Opciones.jsp").forward(request, response);
			break;
		case "extraccion":
			
			
			operacion.extraer(Integer.parseInt((String)miSesion.getAttribute("numeroCuenta")), Double.parseDouble(request.getParameter("cantidad")));
			request.getRequestDispatcher("Opciones.jsp").forward(request, response);
			break;
		case "transferencia":
			operacion.extraer(Integer.parseInt((String)miSesion.getAttribute("numeroCuenta")), Double.parseDouble(request.getParameter("cantidad")));
			operacion.ingresar(Integer.parseInt(request.getParameter("destino")), Double.parseDouble(request.getParameter("cantidad")));

			
			request.getRequestDispatcher("Opciones.jsp").forward(request, response);
			break;
		case "mostrar":
			request.setAttribute("lista", operacion.findAllOfId(Integer.parseInt((String)miSesion.getAttribute("numeroCuenta"))));
			request.setAttribute("saldo", operacion.devolverCuenta(Integer.parseInt((String)miSesion.getAttribute("numeroCuenta"))).getSaldo());
			request.getRequestDispatcher("Movimientos.jsp").forward(request, response);
			break;
		}
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
