package br.com.petshop.dados;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import br.com.petshop.entidades.Produto;
import br.com.petshop.utils.Utils;

public class DadosProduto {
	ArrayList <Produto> listaProdutos = new ArrayList <Produto>();

	public boolean insere(Produto produto) throws ClassNotFoundException {
		Utils.criarDiretorio();
		listaProdutos = retorna();
		listaProdutos.add(produto);
		return salva(listaProdutos);

	}
	public boolean altera(int index, Produto produto) throws ClassNotFoundException {
		Utils.criarDiretorio();
		listaProdutos = retorna();
		listaProdutos.set(index, produto);
		return salva(listaProdutos);
	}
	public boolean salva(ArrayList <Produto> listaProduto){
		Utils.criarDiretorio();
		try{
			FileOutputStream fos = new FileOutputStream(Utils.nomeDiretorioDados() + "/produtos.txt");
			ObjectOutputStream oos = new ObjectOutputStream (fos);
			oos.writeObject(listaProduto);
			oos.close();
		}catch (IOException e){
			return false;
		}
		return true;
	}
	@SuppressWarnings("unchecked")
	public ArrayList<Produto> retorna() throws ClassNotFoundException{
		Utils.criarDiretorio();
		try{
			FileInputStream fis = new FileInputStream(Utils.nomeDiretorioDados() + "/produtos.txt");
			ObjectInputStream ois = new ObjectInputStream (fis);
			listaProdutos = (ArrayList<Produto>) ois.readObject();
			ois.close();
		}catch (IOException e){

		}
		return listaProdutos;
	}
}
