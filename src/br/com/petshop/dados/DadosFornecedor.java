package br.com.petshop.dados;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import br.com.petshop.entidades.Fornecedor;
import br.com.petshop.utils.Utils;

public class DadosFornecedor{
	ArrayList <Fornecedor> listaFornecedores = new ArrayList <Fornecedor>();;

	public boolean insereCadastro(Fornecedor fornecedor) throws ClassNotFoundException {
		listaFornecedores = retornaCadastros();
		listaFornecedores.add(fornecedor);
		return salvaCadastro(listaFornecedores);
		
	}
	public boolean alteraCadastro(int index, Fornecedor fornecedor) throws ClassNotFoundException {
		listaFornecedores = retornaCadastros();
		listaFornecedores.set(index, fornecedor);
		return salvaCadastro(listaFornecedores);
	}

	public boolean excluiCadastro(int index) throws ClassNotFoundException{
		listaFornecedores = retornaCadastros();
		listaFornecedores.remove(index);
		return salvaCadastro(listaFornecedores);
	}
	public boolean salvaCadastro(ArrayList <Fornecedor> listaFornecedores){
		Utils.criarDiretorio();
		try{
			FileOutputStream fos = new FileOutputStream(Utils.nomeDiretorioDados() + "/fornecedores.txt");
			ObjectOutputStream oos = new ObjectOutputStream (fos);
			oos.writeObject(listaFornecedores);
			oos.close();
		}catch (IOException e){
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Fornecedor> retornaCadastros() throws ClassNotFoundException{
		Utils.criarDiretorio();
		try{
			FileInputStream fis = new FileInputStream(Utils.nomeDiretorioDados() + "/fornecedores.txt");
			ObjectInputStream ois = new ObjectInputStream (fis);
			listaFornecedores = (ArrayList<Fornecedor>) ois.readObject();
			ois.close();
		}catch (IOException e){

		}
		return listaFornecedores;
	}
	public int qtdCadastrados() throws ClassNotFoundException{
		Utils.criarDiretorio();
		organizaIndex();
		listaFornecedores = retornaCadastros();
		return listaFornecedores.size();
	}
	public boolean organizaIndex() throws ClassNotFoundException{
		Utils.criarDiretorio();
		listaFornecedores = retornaCadastros();
		for(int i = 0; i < listaFornecedores.size(); i++){
			listaFornecedores.get(i).setId(i);
		}
		salvaCadastro(listaFornecedores);
		return true;
	}
}
