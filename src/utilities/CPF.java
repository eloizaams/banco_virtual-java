package utilities;

import static mos.io.InputOutput.readString;

public abstract class CPF {
		
		/* O CPF é composto por 11 dígitos, sendo os dois últimos os dígitos verificadores. 
		 * 
		* Primeiro dígito verificador (10º dígito):
		* 	Somar o produto dos 9 primeiros dígitos do CPF pelos pesos de 10 a 2.
		* 	O peso do primeiro dígito é 10, do segundo é 9, e assim por diante, até o peso do nono dígito, que é 2.
		* 	Calcular o resto da divisão dessa soma por 11.
		* 	Se o resto for menor que 2, o dígito verificador é 0. Caso contrário, é 11 menos o resto.
		* 
		* Segundo dígito verificador (11º dígito):
		* 	Seguir os mesmos passos do primeiro dígito verificador, porém considerando os 10 primeiros dígitos do CPF, 
		* 		incluindo o primeiro dígito verificador recém-calculado.
		* 	O peso do primeiro dígito é 11, do segundo é 10, e assim por diante, até o peso do décimo dígito, que é 2.
		*/

	public static boolean isValidCPF (String cpf) {
		// Remove caracteres não numéricos
		limpaStringCpf(cpf); 
        
        //Verifica se o cpf tem 11 dígitos e se eles não iguais
        if(cpf==null || cpf.length()!=11 || verificaNumerosIguais(cpf))
        	return false;
        
        //Cria um vetor com os digitos do CPF
        int[] digitosCPF = new int[11];
        
        for(int posicao = 0; posicao<11;posicao++) {
        	digitosCPF[posicao]=Character.getNumericValue(cpf.charAt(posicao));
        }
        
                
     // Calcula o primeiro dígito verificador
        int primeiroDigito = 0;
        for (int posicao = 0; posicao < 9; posicao++) {
            primeiroDigito += digitosCPF[posicao] * (10 - posicao);
        }
        primeiroDigito = (primeiroDigito * 10) % 11;
        if (primeiroDigito != digitosCPF[9]) {
            return false;
        }
            
        // Calcula o segundo dígito verificador
        int segundoDigito = 0;
        for (int posicao = 0; posicao < 10; posicao++) {
            segundoDigito += digitosCPF[posicao] * (11 - posicao);
        }
        segundoDigito = (segundoDigito * 10) % 11;
        if (segundoDigito != digitosCPF[10]) {
            return false;
        }
        return true;
	}
	
	public static String limpaStringCpf (String cpf) {
		// Remove caracteres não numéricos
		if (cpf !=null) {
			return cpf.replaceAll(Constantes.STRING_NAO_NUMEROS, Constantes.STRING_VAZIA);
		}
		return Constantes.STRING_VAZIA;
	}

	// Verifica se todos os dígitos são iguais
	public static boolean verificaNumerosIguais(String cpf) {
		char primeiroDigito = cpf.charAt(0);
		boolean todosNumerosIguais = true;
		for (int i = 1; i < cpf.length(); i++) {
			if (cpf.charAt(i) != primeiroDigito) {
				todosNumerosIguais = false;
				break;
			}
		}
		return todosNumerosIguais;
	}
	
	public static String leCpf(String mensagem, String titulo) {
		String cpf;
		do {
			cpf = readString(mensagem, titulo);
		}while (cpf != null && !CPF.isValidCPF(cpf));
		return cpf;
	}
	
	public static String toStringCPF(String cpf) {
		cpf = limpaStringCpf(cpf);
		return (isValidCPF(cpf))? String.format(Constantes.TO_STRING_VALIDA_CPF,cpf.substring(0, 3),
				cpf.substring(3, 6), cpf.substring(6, 9), cpf.substring(9)):"";
	}
	
}//class validaCPF
