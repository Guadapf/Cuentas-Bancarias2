package ar.edu.unlam.pb2.cuentas;

import java.util.Objects;

public class Transaccion {
	
	private String motivo;
	private Integer cuentaOrigen;
	private Integer cuentaDestino;
	private Double monto;
	private Integer idTransaccion = 0;
	
	
	public Transaccion(String motivo, Integer cuentaOrigen, Integer cuentaDestino, Double monto) {
		this.motivo = motivo;
		this.cuentaOrigen = cuentaOrigen;
		this.cuentaDestino = cuentaDestino;
		this.monto = monto;
		this.idTransaccion++;
	}


	public String getMotivo() {
		return motivo;
	}


	public Integer getCuentaOrigen() {
		return cuentaOrigen;
	}


	public Integer getCuentaDestino() {
		return cuentaDestino;
	}


	public Double getMonto() {
		return monto;
	}


	public Integer getIdTransferencia() {
		return idTransaccion;
	}


	@Override
	public int hashCode() {
		return Objects.hash(cuentaDestino, cuentaOrigen, idTransaccion, monto, motivo);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaccion other = (Transaccion) obj;
		return Objects.equals(cuentaDestino, other.cuentaDestino) && Objects.equals(cuentaOrigen, other.cuentaOrigen)
				&& Objects.equals(idTransaccion, other.idTransaccion) && Objects.equals(monto, other.monto)
				&& Objects.equals(motivo, other.motivo);
	}
	
	
	

}
