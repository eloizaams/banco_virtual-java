package services;

import java.util.ArrayList;
import java.util.List;

import entites.ContaBancaria;

public class ContaBancariaList{
	
	private List<ContaBancaria> list;

	public ContaBancariaList() {
		this.list = new ArrayList<ContaBancaria>();
	}

	public final List<ContaBancaria> getList() {
		return list;
	}
	
}