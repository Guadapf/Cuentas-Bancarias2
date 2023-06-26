package ar.edu.unlam.pb2.cuentas;

public class SaldoInsuficienteException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public SaldoInsuficienteException(String mensaje) {
		super(mensaje);
	}

}
