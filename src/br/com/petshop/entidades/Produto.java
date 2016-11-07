package br.com.petshop.entidades;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Produto implements Serializable{

	private long codBarras;
	private String nome;
	private double preco;
	private int categoria;
	private static String[] listaCategorias = {"Ração", "Perfumaria", "Veterinária", "Banho&Tosa", "Petisco", "Brinquedo", "Higiene", "Coleira", "Vestimenta", "Farmácia"};

	public Produto(){
	}

	public Produto(long codBarras, String nome, double preco, int categoria) {
		super();
		this.codBarras = codBarras;
		this.nome = nome;
		this.preco = preco;
		this.categoria = categoria;
	}

	public String[] getListaCategorias() {
		return listaCategorias;
	}

	public void setListaCategorias(String[] listaCategorias) {
		Produto.listaCategorias = listaCategorias;
	}

	public static String retornarCategoria(int categoria){
		String valor = "Categoria não cadastrada";
		for(int i = 0; i < listaCategorias.length; i++){
			if(categoria == i){
				valor = listaCategorias[i];
			}
		}
		return valor;
	}

	@Override
	public String toString(){
		return  this.codBarras + "      |     " + this.nome + "     |   " + retornarCategoria(categoria) + "  |  R$ " + this.preco  ;
	}

	public long getCodBarras() {
		return codBarras;
	}

	public void setCodBarras(long codBarras) {
		this.codBarras = codBarras;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public int getCategoria() {
		return categoria;
	}

	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}

}