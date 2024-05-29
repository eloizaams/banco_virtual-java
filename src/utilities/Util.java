package utilities;

import static mos.io.InputOutput.readString;


public abstract class Util {
	
	public static String leString (String mensagem, String titulo) {
		String str;
		do {
			str = readString(mensagem,titulo);
		} while (str == null);
		return str;
	}
	
	/**
	 * Formata um nome para ficar com as iniciais maiúsculas e o restante minúsculo
	 * @param nome
	 * @return String com o nome formatado
	 */
	public static String formatarNome(String nome) {
        String[] palavras = nome.split("\\s+");
        
        StringBuilder nomeFormatado = new StringBuilder();
        
        for (String palavra : palavras) {
            if (!palavra.isEmpty()) {
                String primeiraLetraMaiuscula = palavra.substring(0, 1).toUpperCase();
                String restantePalavraMinuscula = palavra.substring(1).toLowerCase();
                
              nomeFormatado.append(primeiraLetraMaiuscula).append(restantePalavraMinuscula).append(" ");
            }
        }
        return nomeFormatado.toString().trim();
	}
	
	/**
	 * Lê um valor double a partir de uma mensagem exibida ao usuário.
	 * Caso o separador decimal digitao seja vírgula, ele substitui por ponto. 
	 * 
	 * @param mensagem A mensagem a ser exibida para solicitar o valor.
	 * @param titulo O título da janela de entrada.
	 * @return O valor double lido ou 0.0 se a entrada for inválida.
	 */
	public static Double leDouble (String mensagem, String titulo) {
		String numero = readString(mensagem,titulo);
		Double valor = 0.0;
		 if (numero != null) {
	            numero = numero.replaceAll(",", ".");
	            valor = Double.parseDouble(numero);
		 }
		return (valor==null)?0:valor; 
	}
}//class Util
