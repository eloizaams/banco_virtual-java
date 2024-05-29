package services;

/**
 * Enumeração que representa as opções do menu principal do sistema bancário virtual.
 */
public enum Menu {
	ABRIR_CONTA ("Abrir conta"),
	DEPOSITO ("Depósito"),
	SALDO ("Saldo"),
	TRANSFERENCIA ("Transferência"),
	SAQUE ("Saque"),
	RELATORIO ("Relatório");
	
	private String funcao;

	/**
     * Construtor da enumeração Menu.
     * @param funcao A função associada à opção do menu.
     */
	private Menu(String funcao) {
		this.funcao = funcao;
	}
	
	/**
     * Obtém a função associada à opção do menu.
     * @return A função associada à opção do menu.
     */
	public String getFuncao() {
		return funcao;
	}

	/**
     * Define a função associada à opção do menu.
     * @param funcao A função associada à opção do menu.
     */
	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	/**
     * Retorna a representação em String da opção do menu.
     * @return A representação em String da opção do menu.
     */
	@Override
	public String toString() {
		return funcao;
	}
	
	/**
     * Cria um vetor de Strings com todas as opções do menu.
     * @return Um vetor de Strings com todas as opções do menu.
     */
	public static String[] criaVetorMenu() {
		String[] string = new String[Menu.values().length];
		for(int indice=0;indice< Menu.values().length;indice++) {
			string[indice]=Menu.values()[indice].toString();
		}
		return string;
	}
}// enum Menu
