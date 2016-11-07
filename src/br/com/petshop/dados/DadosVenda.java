package br.com.petshop.dados;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import br.com.petshop.entidades.Vendas;
import br.com.petshop.utils.Utils;

public class DadosVenda {
	ArrayList <Vendas> listaVendas = new ArrayList <Vendas>();
	public boolean insere(Vendas venda) throws ClassNotFoundException {
		Utils.criarDiretorio();
		listaVendas = retorna();
		listaVendas.add(venda);
		return salva(listaVendas);

	}
	public boolean altera(int index, Vendas estoque) throws ClassNotFoundException {
		Utils.criarDiretorio();
		listaVendas = retorna();
		listaVendas.set(index, estoque);
		return salva(listaVendas);
	}
	public boolean salva(ArrayList <Vendas> listaEstoque){
		Utils.criarDiretorio();
		try{
			FileOutputStream fos = new FileOutputStream(Utils.nomeDiretorioDados() + "/vendas.txt");
			ObjectOutputStream oos = new ObjectOutputStream (fos);
			oos.writeObject(listaEstoque);
			oos.close();
		}catch (IOException e){
			return false;
		}
		return true;
	}
	@SuppressWarnings("unchecked")
	public ArrayList<Vendas> retorna() throws ClassNotFoundException{
		Utils.criarDiretorio();
		try{
			FileInputStream fis = new FileInputStream(Utils.nomeDiretorioDados() + "/vendas.txt");
			ObjectInputStream ois = new ObjectInputStream (fis);
			listaVendas = (ArrayList<Vendas>) ois.readObject();
			ois.close();
		}catch (IOException e){

		}
		return listaVendas;
	}	
}