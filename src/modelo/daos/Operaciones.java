package modelo.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import modelo.bins.Cuenta;
import modelo.bins.Movimiento;


public class Operaciones {
	protected EntityManagerFactory emf;
	protected EntityManager em;
	protected EntityTransaction tx;
	protected EntityTransaction tx2;
	protected String sql,sql2;
	protected Query query;
	//el formato que se va a usar para las fechas
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	//Constructor con el que nos conectamos a la base de datos
	public Operaciones() {
		super();
		emf = Persistence.createEntityManagerFactory("55_transacciones_JPA");
		em = emf.createEntityManager();
		tx = em.getTransaction();
		
	}
	
	//Le ingresamos un id de cuenta al metodo. Si existe la cuenta, devuelve true,
	//si no existe, devuelve false
	public boolean comprobarCuenta(int id)
	{
		sql = "select e from Cuenta e where e.numeroCuenta = :id";
		
		
			query = em.createQuery(sql);
			query.setParameter("id", id);
			Cuenta cuenta = null;
			try {
				cuenta = (Cuenta)query.getSingleResult();
			
			} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
			if(cuenta!=null)
			{
				return true;
			}
			else{
				return false;
			}
		
	}
	
	//este metodo devuelve un objeto de tipo Cuenta al pasarle un id de cuenta
	public Cuenta devolverCuenta(int numeroCuenta) {
		
		sql = "select e from Cuenta e where e.numeroCuenta = :numcuen";
		
			query = em.createQuery(sql);
			query.setParameter("numcuen", numeroCuenta);
			Cuenta cuenta = null;
			try {
				cuenta = (Cuenta)query.getSingleResult();

			}catch(Exception e) {
				e.printStackTrace();
			}
			return cuenta;
		
			
		
		
		
	}
	
	//metodo para el ingreso de dinero en la cuenta, con la creacion de la fila correspondiente y la actualizacion de la cuenta
	public boolean ingresar(int idcuenta, double cantidad)
	{
		
		
		Cuenta cuentaAActualizar = this.devolverCuenta(idcuenta);
		try {
			Movimiento movimiento = new Movimiento();
			movimiento.setCuenta(cuentaAActualizar);
			movimiento.setFecha(new Date());
			movimiento.setCantidad(cantidad);
			movimiento.setOperacion("ingreso");
			Cuenta cuenta = cuentaAActualizar;
			cuenta.setSaldo(cuentaAActualizar.getSaldo()+cantidad);
			
			try {
				
				tx.begin();
				em.persist(movimiento);
				tx.commit();
				
				
				em.merge(cuenta);
				

				}catch(Exception e) {
				e.printStackTrace();
				}
			
		}catch(Exception e) {
			e.printStackTrace();
			}
		return false;
		
	}
	//metodo para la extraccion de dinero en la cuenta, con la creacion de la fila correspondiente y la actualizacion de la cuenta
	public boolean extraer(int idcuenta, double cantidad)
	{
		
		
		Cuenta cuentaAActualizar = this.devolverCuenta(idcuenta);
		try {
			Movimiento movimiento = new Movimiento();
			movimiento.setCuenta(cuentaAActualizar);
			movimiento.setFecha(new Date());
			movimiento.setCantidad(cantidad);
			movimiento.setOperacion("extraccion");
			Cuenta cuenta = cuentaAActualizar;
			cuenta.setSaldo(cuentaAActualizar.getSaldo()-cantidad);
			
			try {
				
				tx.begin();
				em.persist(movimiento);
				tx.commit();
				
				
				em.merge(cuenta);
				

				}catch(Exception e) {
				e.printStackTrace();
				}
			
			
			
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	//Aqui devuelvo una lista con todas las transcacciones realizadas por el id que le paso
	public List<Movimiento> findAllOfId(int id) {
		// TODO Auto-generated method stub
		sql  = "select e from Movimiento e where e.cuenta = :id";
		
		List<Movimiento> listaADevolver = null;
		
			query = em.createQuery(sql);
			query.setParameter("id", this.devolverCuenta(id));
			
			try {
			List<Cuenta>lista = (List<Cuenta>)query.getResultList();
			listaADevolver= lista.get(0).getMovimientos();
			
			
			} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaADevolver;
	}
	
	
	
	
	
}
