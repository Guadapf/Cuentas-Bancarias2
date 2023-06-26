package ar.edu.unlam.pb2.cuentas;

public class CajaAhorro extends Cuenta{
	
	private Integer contador;

	public CajaAhorro(Integer cbu) {
		super(cbu);
		this.contador = 0;
	}


	@Override
	protected void extraer(Integer cbu, Double monto) throws Exception{
		Double valorExtra = 6.0;
		Double valorExtraccion = monto + valorExtra;
		
		if(this.cbu.equals(cbu)) {
			if(monto <= super.saldo && this.contador < 5) {
				super.saldo -= monto;
				this.contador++;
				
				Transaccion trans = new Transaccion("Extraccion", super.cbu, null, monto);
				this.transacciones.add(trans);
			}else if(valorExtraccion <= super.saldo){
				super.saldo -= valorExtraccion;
				
				Transaccion trans = new Transaccion("Extraccion", super.cbu, null, valorExtraccion);
				this.transacciones.add(trans);
			}else {
				throw new SaldoInsuficienteException("Saldo insuficiente");
			}
		}else {
			throw new CuentaInexistenteException("Cuenta inexistente");
		}
	}

}
