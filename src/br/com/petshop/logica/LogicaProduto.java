package br.com.petshop.logica;

import java.util.ArrayList;
import java.util.Random;

import br.com.petshop.dados.DadosEstoque;
import br.com.petshop.entidades.Estoque;
import br.com.petshop.entidades.Produto;

public class LogicaProduto {
	private Random r = new Random();
	private DadosEstoque de = new DadosEstoque();

	public boolean inserir(Produto produto) throws ClassNotFoundException {
		Estoque produtoEstoque = null;
		produtoEstoque = new Estoque(de.retorna().size(), produto, 0);

		if (de.insere(produtoEstoque)) {
			return true;
		} else
			return false;
	}

	public boolean consultaProdutoJaCadastrado(long codBarras) throws ClassNotFoundException {
		ArrayList<Estoque> listaProdutos = de.retorna();

		for (Estoque prod : listaProdutos) {
			if (prod.getProduto().getCodBarras() == codBarras)
				return true;
		}
		return false;
	}

	public int gerarCodBarrasQuatroDigitos() throws ClassNotFoundException {
		int CodBarras = r.nextInt(10000);
		/*
		 * boolean flag = false; do{ for(Estoque prod : de.retorna()){
		 * if((CodBarras == prod.getProduto().getCodBarras())){ flag = true;
		 * break; }
		 * 
		 * } }while(flag == false);
		 */
		return CodBarras;
	}
}
