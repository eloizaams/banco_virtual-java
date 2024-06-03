package utilities;

import static mos.io.InputOutput.*;


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
	 * Formata um nome para ficar com as iniciais maiúsculas e o restante minúsculo
	 * @param nome
	 * @return String com o nome formatado
	 */
	public static String formatarReais(Double valor) {
       
        return String.format("R$ %.2f", valor);
	}
	
	/**
	 * Lê um valor double a partir de uma mensagem exibida ao usuário.
	 * Caso o separador decimal digitao seja vírgula, ele substitui por ponto. 
	 * 
	 * @param mensagem A mensagem a ser exibida para solicitar o valor.
	 * @param titulo O título da janela de entrada.
	 * @return O valor double lido ou 0.0 se a entrada for inválida.
	 */
	public static Double leDouble(String mensagem, String titulo) {
	    Double valor = null;
	    do {
	        try {
	            String input = readString(mensagem, titulo);
	            if (input == null) {
	                return null;  // Retorna null se a caixa de diálogo for fechada ou cancelada
	            }
	            valor = Double.parseDouble(input.replaceAll(",", "."));
	        } catch (NumberFormatException e) {
	            valor = null;  // Define valor como null se a entrada não for um número válido
	        }
	    } while (valor == null);

	    return valor;
	}
	
	/**
	 * Lê um valor inte a partir de uma mensagem exibida ao usuário.
	 * 
	 * @param mensagem A mensagem a ser exibida para solicitar o valor.
	 * @param titulo O título da janela de entrada.
	 * @return O valor double lido ou 0.0 se a entrada for inválida.
	 */
	public static Integer leInt(String mensagem, String titulo) {
	    Integer valor = null;
	    do {
	        try {
	            valor = readInt(mensagem, titulo);
	            if (valor == null) {
	                return null;  // Retorna null se a caixa de diálogo for fechada ou cancelada
	            }
	        } catch (NumberFormatException e) {
	            valor = null;  // Define valor como -1 se a entrada não for um número válido
	        }
	    } while (valor == null);

	    return valor;
	}
	
}//class Util
