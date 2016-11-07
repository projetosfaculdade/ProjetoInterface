package br.com.petshop.logica;

import java.util.ArrayList;

import br.com.petshop.dados.DadosEstoque;
import br.com.petshop.entidades.Estoque;

public class LogicaEstoque {
	private DadosEstoque de = new DadosEstoque();

	public ArrayList<Object> JTableEstoque(boolean radioEstoque, boolean categoriaComboBoxEstoque, int categoria) {
		Object[] row;
		ArrayList<Estoque> listaProdutos = null;
		ArrayList<Object> dados = new ArrayList<Object>();
		try {
			listaProdutos = de.retorna();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		for (Estoque est : listaProdutos) {
			row = new Object[6];
			row[0] = est.getId();
			row[1] = est.getProduto().getCodBarras();
			row[2] = est.getProduto().getNome();
			row[3] = est.getProduto().getPreco();
			row[4] = est.getProduto().getListaCategorias()[est.getProduto().getCategoria()];
			row[5] = est.getQtd();

			if (radioEstoque)
				dados.add(row);
			else if (categoriaComboBoxEstoque) {
				if (categoria == est.getProduto().getCategoria())
					dados.add(row);
			}
		}
		return dados;
	}

	public boolean entrada(int row, int id, int qtd) {
		if (qtd > 0) {
			Estoque produto = null;
			try {
				produto = de.retorna().get(id);
			} catch (ClassNotFoundException e2) {
				e2.printStackTrace();
			}
			produto.setQtd(produto.getQtd() + qtd);
			try {
				de.altera(id, produto);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
			return true;
		} else
			return false;

	}

	public boolean alteraQuantidade(int qtd, int id) {

		if (qtd >= 0) {
			Estoque produto = null;
			try {
				produto = de.retorna().get(id);
			} catch (ClassNotFoundException e2) {
				e2.printStackTrace();
			}
			produto.setQtd(qtd);
			try {
				de.altera(id, produto);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
			return true;
		} else
			return false;
	}
}
