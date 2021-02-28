package modelo.daos;

import java.util.List;

import modelo.bins.Cuenta;
import modelo.bins.Movimiento;

public interface InterOperaciones {
	public boolean comprobarCuenta(int id);
	public Cuenta devolverCuenta(int numeroCuenta);
	public boolean ingresar(int idcuenta, double cantidad);
	public boolean extraer(int idcuenta, double cantidad);
	public List<Movimiento> findAllOfId(int id);
}
