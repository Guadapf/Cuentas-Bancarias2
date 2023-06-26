package ar.edu.unlam.pb2.cuentas;

public class CuentaInexistenteException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public CuentaInexistenteException(String mensaje) {
		super(mensaje);
	}

}
