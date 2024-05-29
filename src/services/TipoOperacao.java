package services;

public enum TipoOperacao {
	SALDO ("Saldo"),
	DEPOSITO ("Depósito"),
	TRANSFERENCIA ("Transferência"),
	SAQUE ("Saque");
	
	private String operacao;

	private TipoOperacao(String operacao) {
		this.operacao = operacao;
	}

	public String getOperacao() {
		return operacao;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}

	@Override
	public String toString() {
		return operacao;
	}

}
