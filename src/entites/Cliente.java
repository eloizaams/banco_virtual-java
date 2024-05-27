package entites;

public class Cliente {
	
	private String name;
	private String cpf;
	
	public Cliente(String name, String cpf) {
		this.name = name;
		this.cpf = cpf;
	}
	
	public final String getName() {
		return name;
	}
	public final void setName(String name) {
		this.name = name;
	}
	public final String getCpf() {
		return cpf;
	}
	public final void setCpf(String cpf) {
		this.cpf = cpf;
	}
	

}
