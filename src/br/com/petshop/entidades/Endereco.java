package br.com.petshop.entidades;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Endereco implements Serializable{
	private String cep;
	private String logradouro;
	private int numero;
	private String bairro;
	private String municipio;
	private short estado;
	private String[] listaEstados = {"AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RO", "RS", "RR", "SC", "SE", "SP", "TO" };
	private String pais;
	private String complemento;
	
	public Endereco(){
		
	}
	public Endereco(String cep, String logradouro, int numero, String bairro, String municipio, short estado, String pais, String complemento){
		setCep(cep);
		setLogradouro(logradouro);
		setNumero(numero);
		setBairro(bairro);
		setMunicipio(municipio);
		setEstado(estado);
		setPais(pais);
		setComplemento(complemento);
	}


	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getLogradouro(){
		return this.logradouro;
	}
	public void setLogradouro(String logradouro){
		this.logradouro = logradouro;
	}
	
	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getBairro(){
		return this.bairro;
	}
	public void setBairro(String bairro){
		this.bairro = bairro;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public short getEstado() {
		return estado;
	}
	
	public void setEstado(short estado) {
		this.estado = estado;
	}
	
	public String[] getListaEstados() {
		return listaEstados;
	}
	
	public void setListaEstados(String[] listaEstados) {
		this.listaEstados = listaEstados;
	}


	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}


	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String toString(){
		return ", CEP :"+ getCep();
		
	}
}
