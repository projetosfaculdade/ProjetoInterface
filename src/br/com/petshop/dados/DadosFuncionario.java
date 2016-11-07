package br.com.petshop.dados;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import br.com.petshop.entidades.Funcionario;
import br.com.petshop.utils.Utils;

public class DadosFuncionario{
	ArrayList <Funcionario> listaFuncionarios = new ArrayList <Funcionario>();

	public boolean insere(Funcionario funcionario) throws ClassNotFoundException {

		listaFuncionarios = retorna();
		listaFuncionarios.add(funcionario);
		return salva(listaFuncionarios);
	}
	public boolean altera(int index, Funcionario funcionario) throws ClassNotFoundException {
		listaFuncionarios = retorna();
		listaFuncionarios.set(index, funcionario);
		return salva(listaFuncionarios);
	}

	public boolean exclui(int index) throws ClassNotFoundException{
		listaFuncionarios = retorna();
		listaFuncionarios.remove(index);
		return salva(listaFuncionarios);

	}
	public boolean salva(ArrayList <Funcionario> listaFuncionarios2){
		Utils.criarDiretorio();
		try{
			FileOutputStream fos = new FileOutputStream(Utils.nomeDiretorioDados() + "/funcionarios.txt");
			ObjectOutputStream oos = new ObjectOutputStream (fos);
			oos.writeObject(listaFuncionarios2);
			oos.close();
		}catch (IOException e){
			return false;
		}
		return true;
	}
	@SuppressWarnings("unchecked")
	public ArrayList <Funcionario> retorna() throws ClassNotFoundException{
		Utils.criarDiretorio();
		ArrayList <Funcionario> func = new ArrayList<>();
		try{
			FileInputStream fis = new FileInputStream(Utils.nomeDiretorioDados() + "/funcionarios.txt");
			ObjectInputStream ois = new ObjectInputStream (fis);
			func = (ArrayList<Funcionario>) ois.readObject();
			ois.close();
		}catch (IOException e){

		}
		return func;
	}
	public int qtdCadastrados() throws ClassNotFoundException{
		Utils.criarDiretorio();
		organizaIndex();
		listaFuncionarios = retorna();
		return listaFuncionarios.size();
	}
	public boolean organizaIndex() throws ClassNotFoundException{
		Utils.criarDiretorio();
		listaFuncionarios = retorna();
		for(int i = 0; i < listaFuncionarios.size(); i++){
			listaFuncionarios.get(i).setId(i);
		}
		salva(listaFuncionarios);
		return true;
	}
}
