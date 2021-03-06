package br.com.petshop.entidades;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Animal implements Serializable{

	private int Id;
	private String nomeAnimal;
	private String racaAnimal;
	private String especieAnimal;
	private char sexoAnimal;
	private int idadeAnimal;


	public Animal(int id, String nomeAnimal, String racaAnimal, String especieAnimal, char sexoAnimal, int idadeAnimal) {
		super();
		Id = id;
		this.nomeAnimal = nomeAnimal;
		this.racaAnimal = racaAnimal;
		this.especieAnimal = especieAnimal;
		this.sexoAnimal = sexoAnimal;
		this.idadeAnimal = idadeAnimal;
	}


	public String toString(Animal ani){
		return "\t-> ID: " + ani.getId() + ", Nome: " + ani.getNomeAnimal() + ", Ra�a: " + ani.getRacaAnimal() + ", Especie: " + ani.getEspecieAnimal() + ", Sexo: " + ani.getSexoAnimal() + ", Idade: " + ani.getIdadeAnimal();
	}

	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getNomeAnimal() {
		return nomeAnimal;
	}
	public void setNomeAnimal(String nomeAnimal) {
		this.nomeAnimal = nomeAnimal;
	}
	public String getRacaAnimal() {
		return racaAnimal;
	}
	public void setRacaAnimal(String racaAnimal) {
		this.racaAnimal = racaAnimal;
	}
	public String getEspecieAnimal() {
		return especieAnimal;
	}
	public void setEspecieAnimal(String especieAnimal) {
		this.especieAnimal = especieAnimal;
	}
	public char getSexoAnimal() {
		return sexoAnimal;
	}
	public void setSexoAnimal(char sexoAnimal) {
		this.sexoAnimal = sexoAnimal;
	}
	public int getIdadeAnimal() {
		return idadeAnimal;
	}
	public void setIdadeAnimal(int idadeAnimal) {
		this.idadeAnimal = idadeAnimal;
	}


}
