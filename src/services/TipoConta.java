package services;



public enum TipoConta {
	CORRENTE ("Conta Corrente"), 
	REMUNERADA ("Conta Remunerada"), 
	POUPANCA ("Conta Poupança");
	
	
	private String tipoConta;
	

	private TipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
	}

	public String getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
	}


	@Override
	public String toString() {
		return tipoConta;
	}
	
	public static String[] criaVetorTipoConta() {
		String[] string = new String[TipoConta.values().length];
		
		for(int indice = 0; indice < TipoConta.values().length; indice++) {
			string[indice]=TipoConta.values()[indice].toString();
		}
		return string;
	}
}//enum TipoConta
