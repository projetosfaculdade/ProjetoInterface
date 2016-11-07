package br.com.petshop.entidades;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Vendas implements Serializable{
	private int id;
	private ArrayList<Produto> carrinho = new ArrayList<>();
	private double subTotal;
	private double valorTotal;
	
	
	public Vendas(int id, ArrayList<Produto> carrinho, double subTotal, double valorTotal) {
		super();
		this.id = id;
		this.carrinho = carrinho;
		this.subTotal = subTotal;
		this.valorTotal = valorTotal;
	}

	public Vendas(){
		this.valorTotal=0;
	}
	
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ArrayList<Produto> getCarrinho() {
		return this.carrinho;
	}
	public void setCarrinho(Produto produto) {
		this.carrinho.add(produto);
	}
	
	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

	public double getValorTotal() {
		return this.valorTotal;
	}
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public String toString(){
		return "|   "+ this.id + "   |  R$ " + this.subTotal + " |  R$ " + this.valorTotal ;
	}
}