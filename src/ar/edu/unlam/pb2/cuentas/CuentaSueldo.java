package ar.edu.unlam.pb2.cuentas;

public class CuentaSueldo extends Cuenta{

	public CuentaSueldo(Integer cbu) {
		super(cbu);
	}


	@Override
	protected void extraer(Integer cbu, Double monto) throws Exception{
		if(this.cbu.equals(cbu)) {
			if(monto <= super.saldo) {
				super.saldo -= monto;
				Transaccion trans = new Transaccion("Extraccion", super.cbu, null, monto);
				this.transacciones.add(trans);
			}else {
				throw new SaldoInsuficienteException("Saldo insuficiente");
			}
		}else {
			throw new CuentaInexistenteException("Cuenta inexistente");
		}
			
	}
	
	
	

}
