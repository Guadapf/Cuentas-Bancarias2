package ar.edu.unlam.pb2.cuentas;

import java.util.LinkedList;

public abstract class Cuenta {
	
	protected Integer cbu;
	protected Double saldo;
	protected LinkedList<Transaccion> transacciones = new LinkedList<Transaccion>();
	
	public Cuenta(Integer cbu) {
		this.cbu = cbu;
		this.saldo = 0.0;
	}
	
	protected Integer getCbu(){
		return this.cbu;
	}
	protected Double getSaldo() {
		return this.saldo;
	}
	
	protected LinkedList<Transaccion> getTransacciones(){
		return this.transacciones;
	}
	
	protected void depositar(Integer cbu, Double monto) throws Exception {
		if(this.cbu.equals(cbu)) {
			this.saldo += monto;
			
			Transaccion trans = new Transaccion("Deposito", this.cbu, null, monto);
			this.transacciones.add(trans);
		}else {
			throw new CuentaInexistenteException("Cuenta inexistente");
		}
	}
	
	protected void transferencia(Cuenta cuentaDestino, Double monto) throws Exception {
		if(monto <= this.saldo) {
			this.saldo -= monto;
			cuentaDestino.depositar(cuentaDestino.getCbu(), monto);
			
			Transaccion trans = new Transaccion("Transferencia", this.cbu, cuentaDestino.getCbu(), monto);
			this.transacciones.add(trans);
		}
	}
	
	
	protected abstract void extraer(Integer cbu, Double monto) throws Exception;

}
