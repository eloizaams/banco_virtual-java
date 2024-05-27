package application.gui;

import services.ContaBancariaList;

public class BancoVirtual {

	ContaBancariaList contasBancariasList;
	
	public BancoVirtual() {
		this.contasBancariasList = criarContasBancarias();
		
	}

		
	public static void main(String[] args) {
		new BancoVirtual();
	}
	
	public ContaBancariaList criarContasBancarias() {
		return new ContaBancariaList();
	}

}
