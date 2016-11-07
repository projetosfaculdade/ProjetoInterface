package br.com.petshop.entidades;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Estoque implements Serializable{
	private int id;
	private Produto produto;
	private int qtd;


	public Estoque(int id, Produto produto, int qtd) {
		super();
		this.id = id;
		this.produto = produto;
		this.qtd = qtd;
	}

	public Estoque(){
		this.qtd=0;
	}

	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id = id;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public int getQtd() {
		return qtd;
	}
	public void setQtd(int qtd) {
		this.qtd =  qtd;
	}
	@Override
	public String toString(){
		return "|   " + this.id + "  |        " + this.produto.toString() + "   |   " + this.qtd + "   |";
	}
	public void entradaProduto(int qtdEntrada){
		this.qtd += qtdEntrada;
	}
}
