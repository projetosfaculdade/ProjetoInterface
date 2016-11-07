package br.com.petshop.logica;

import java.util.ArrayList;

import br.com.petshop.dados.DadosCliente;
import br.com.petshop.entidades.Cliente;
import br.com.petshop.entidades.Estoque;
import br.com.petshop.entidades.Produto;

public class LogicaVendas {
	DadosCliente dc = new DadosCliente();
	public ArrayList<Object> JTableVendas(int qtdTextField, long codBarrasVendas, ArrayList<Estoque> dadosEstoque) throws ClassNotFoundException{
		Object[] row;
		ArrayList<Object> dados = new ArrayList<Object>();

		for (Estoque est : dadosEstoque) {
			row = new Object[6];
			row[0] = est.getProduto().getCodBarras();
			row[1] = est.getProduto().getNome();
			row[2] = qtdTextField;
			row[3] = est.getProduto().getListaCategorias()[est.getProduto().getCategoria()];
			row[4] = est.getProduto().getPreco();
			row[5] = qtdTextField * est.getProduto().getPreco();
			if (codBarrasVendas == est.getProduto().getCodBarras())
				dados.add(row);
		}
		return dados;
	}
	public boolean consultarCodBarras(long codBarras, ArrayList<Estoque> dadosEstoque) throws ClassNotFoundException{
		for(Estoque est : dadosEstoque){
			if(codBarras == est.getProduto().getCodBarras())
				return true;
		}
		return false;
	}
	public Cliente informacoesClienteVenda(long id) throws ClassNotFoundException{
		for(Cliente cli : dc.retorna()){
			if(cli.getId() == id)
				return cli;
		}
		return null;
	}
	public boolean consultaQuantidadeNoEstoque(long codBarras, int qtd, ArrayList<Estoque> dadosEstoque) throws ClassNotFoundException{
		for(Estoque est : dadosEstoque){
			if(codBarras == est.getProduto().getCodBarras()){
				if(est.getQtd() >= qtd)
					return true;
				return false;

			}
		}
		return false;
	}
	public Produto retornaProduto(long codBarras, ArrayList<Estoque> dadosEstoque) throws ClassNotFoundException{
		for(Estoque est : dadosEstoque){
			if(codBarras == est.getProduto().getCodBarras())
				return est.getProduto();
		}
		return null;
	}
	public int retornaIndex(long codBarras, ArrayList<Estoque> dadosEstoque){
		for(int i = 0; i < dadosEstoque.size(); i++){
			if(codBarras == dadosEstoque.get(i).getProduto().getCodBarras())
				return i;
		}
		return -1;
	}

	public int retornarIndexCategoria(String categoria) {
		Produto produto = new Produto();
		int i = -1;
		for (i = 0; i < produto.getListaCategorias().length; i++) {
			if (categoria.equals(produto.getListaCategorias()[i])) {
				return i;
			}
		}
		return i;
	}
}
