package entities;

import java.util.Objects;

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

	@Override
	public int hashCode() {
		return Objects.hash(cpf);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(cpf, other.cpf);
	}
	

}
