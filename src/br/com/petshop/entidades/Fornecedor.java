package br.com.petshop.entidades;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Fornecedor implements Serializable {

	private int id;
	private String cnpj; 
	private String razaoSocial; 
	private String nomeFantasia;
	private String telefone;
	private String email;
	private Endereco endereco;

	public Fornecedor(){}

	public Fornecedor(String cnpj, String razaoSocial, String nomeFantasia, String telefone, String email){
		//setId(id);
		setCnpj(cnpj);
		setRazaoSocial(razaoSocial);
		setNomeFantasia(nomeFantasia);
		setTelefone(telefone);
		setEmail(email);
		//setEndereco(endereco);
	}

	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id = id;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getRazaoSocial() {
		return razaoSocial;
	}
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	public String getNomeFantasia() {
		return this.nomeFantasia;
	}
	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String toString(){
		return "CNPJ: " + getCnpj() + ", Razão Social: " + getRazaoSocial() + ", Nome Fantasia: " + getNomeFantasia() + endereco.toString() +", Telefone: " + getTelefone() + ", Email: " + getEmail();
	}

}