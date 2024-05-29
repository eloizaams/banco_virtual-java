package entities;

import java.time.LocalDate;
import services.TipoOperacao;
import java.time.LocalTime;

public class OperacaoBancaria {

	private LocalDate date;
	private LocalTime time;
	private TipoOperacao type;
	private double amount;
	
	public OperacaoBancaria(TipoOperacao type, double amount) {
		this.type = type;
		this.amount = amount;
		this.date = LocalDate.now();
		this.time = LocalTime.now();
	}

	public final LocalDate getDate() {
		return date;
	}

	public final LocalTime getTime() {
		return time;
	}

	public final TipoOperacao getType() {
		return type;
	}

	public final double getAmount() {
		return amount;
	}
	
	
	
	
	
}//class OperacaoBancaria
