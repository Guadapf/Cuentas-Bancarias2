package ar.edu.unlam.pb2.cuentas;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

public class CuentasTest {

	@Test
	public void queSePuedaDepositarEnUnaCuentaSueldo() throws Exception {
		Cuenta c = new CuentaSueldo(1234);
		
		c.depositar(1234, 2000.0);
		Double valorEsperado = 2000.0;
		Double valorObtenido = c.getSaldo();
		
		assertEquals(valorEsperado, valorObtenido, 0.01);
	}
	
	@Test
	public void queSePuedaDepositarEnCajaDeAhorro() throws Exception {
		Cuenta c = new CajaAhorro(1234);
		
		c.depositar(1234, 2000.0);
		
		Double valorEsperado = 2000.0;
		Double valorObtenido = c.getSaldo();
		
		assertEquals(valorEsperado, valorObtenido, 0.01);
	}
	
	@Test
	public void queAlHacerMasDeCincoExtraccionesSeDescuenteElRecargo() throws Exception {
		Cuenta c = new CajaAhorro(1234);
		
		c.depositar(1234, 2000.0);
		
		c.extraer(1234, 50.0);
		c.extraer(1234, 50.0);
		c.extraer(1234, 50.0);
		c.extraer(1234, 50.0);
		c.extraer(1234, 50.0);
		c.extraer(1234, 50.0);
		
		Double valorEsperado = 1694.0;
		Double valorObtenido = c.getSaldo();
		
		assertEquals(valorEsperado, valorObtenido, 0.01);
	}
	
	@Test
	public void queAlHacerUnaExtraccionMayorAlSaldoSeUtiliceElDescubierto() throws Exception {
		Cuenta c = new CuentaCorriente(1234, 150.0);
		
		c.depositar(1234, 100.0);
		c.extraer(1234, 200.0);
		
		Double valorEsperado = 105.0;
		Double valorObtenido = ((CuentaCorriente) c).getSaldoADeber();
		
		assertEquals(valorEsperado, valorObtenido, 0.01);
	}
	
	@Test
	public void queSePuedaRealizarUnaTransferencia() throws Exception {
		Cuenta cuentaOrigen = new CuentaSueldo(1235);
		Cuenta cuentaDestino = new CajaAhorro(3214);
		
		cuentaOrigen.depositar(1235, 400.0);
		cuentaOrigen.transferencia(cuentaDestino, 200.0);
		
		assertEquals(200.0, cuentaOrigen.getSaldo(), 0.01);
		assertEquals(200.0, cuentaDestino.getSaldo(), 0.01);
	}
	
	@Test (expected = SaldoInsuficienteException.class)
	public void queAlIntentarRetirarMasDineroQueElDisponibleLanceUnaExcepcion() throws Exception{
		Cuenta c = new CuentaSueldo(1235);
		
		c.extraer(1235, 100.0);
	}
	
	@Test (expected = CuentaInexistenteException.class)
	public void queAlIngresarUnaClaveIncorrectaLanceUnaExcepcion() throws Exception{
		Cuenta c = new CuentaSueldo(1235);
		
		c.depositar(1234, 200.0);
	}
	
	@Test
	public void queLasTransaccionesSeAgreguenCorrectamente() throws Exception {
		Cuenta c = new CuentaSueldo(1235);
		Cuenta cuentaDestino = new CuentaCorriente(9874, 250.0);
		LinkedList<Transaccion> trans = new LinkedList<Transaccion>();
		Transaccion t1 = new Transaccion("Deposito", c.getCbu(), null, 1000.0);
		Transaccion t2 = new Transaccion("Transferencia", c.getCbu(), cuentaDestino.getCbu(), 500.0);
		
		c.depositar(1235, 1000.0);
		c.transferencia(cuentaDestino, 500.0);
		
		trans.add(t1);
		trans.add(t2);
		
		assertEquals(trans, c.transacciones);
	}

}
