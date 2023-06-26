package ar.edu.unlam.pb2.cuentas;

public class CuentaCorriente extends Cuenta{
	
	private final Double DESCUENTO = 0.05;
	private Double descubierto;
	private Double saldoADeber;

	public CuentaCorriente(Integer cbu, Double descubierto) {
		super(cbu);
		this.descubierto = descubierto;
		this.saldoADeber = 0.0;
	}
	
	public Double getSaldoADeber() {
		return this.saldoADeber;
	}

	@Override
	protected void extraer(Integer cbu, Double monto) throws Exception{
		Double totalDisponible = super.saldo + this.descubierto;
		
		if(this.cbu.equals(cbu)) {
			if(monto <= super.saldo) {
				super.saldo -= monto;
				
				Transaccion trans = new Transaccion("Extraccion", super.cbu, null, monto);
				this.transacciones.add(trans);
			}else if(monto <= totalDisponible) {
				Double aux = monto - super.saldo;
				this.descubierto -= aux;
				this.saldoADeber = (this.DESCUENTO * aux) + aux;
				super.saldo = 0.0 - this.saldoADeber;
				
				Transaccion trans = new Transaccion("Extraccion", super.cbu, null, monto);
				this.transacciones.add(trans);
			}else {
				throw new SaldoInsuficienteException("Saldo total insuficiente");
			}
		}else {
			throw new CuentaInexistenteException("Cuenta inexistente");
		}
	}

}
