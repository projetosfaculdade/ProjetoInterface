package br.com.petshop.dados;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import br.com.petshop.entidades.Estoque;
import br.com.petshop.utils.Utils;

public class DadosEstoque {

	ArrayList <Estoque> listaProdutos = new ArrayList <Estoque>();
	public boolean insere(Estoque estoque) throws ClassNotFoundException {
		Utils.criarDiretorio();
		listaProdutos = retorna();
		listaProdutos.add(estoque);
		return salva(listaProdutos);

	}
	public boolean altera(int index, Estoque estoque) throws ClassNotFoundException {
		Utils.criarDiretorio();
		listaProdutos = retorna();
		listaProdutos.set(index, estoque);
		return salva(listaProdutos);
	}
	public boolean salva(ArrayList <Estoque> listaEstoque){
		Utils.criarDiretorio();
		try{
			FileOutputStream fos = new FileOutputStream(Utils.nomeDiretorioDados() + "/estoque.txt");
			ObjectOutputStream oos = new ObjectOutputStream (fos);
			oos.writeObject(listaEstoque);
			oos.close();
		}catch (IOException e){
			return false;
		}
		return true;
	}
	@SuppressWarnings("unchecked")
	public ArrayList<Estoque> retorna() throws ClassNotFoundException{
		Utils.criarDiretorio();
		try{
			FileInputStream fis = new FileInputStream(Utils.nomeDiretorioDados() + "/estoque.txt");
			ObjectInputStream ois = new ObjectInputStream (fis);
			listaProdutos = (ArrayList<Estoque>) ois.readObject();
			ois.close();
		}catch (IOException e){

		}
		return listaProdutos;
	}	
}
