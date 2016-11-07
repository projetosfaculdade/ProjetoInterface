package br.com.petshop.dados;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import br.com.petshop.entidades.Cliente;
import br.com.petshop.utils.Utils;

public class DadosCliente {
	ArrayList <Cliente> listaCliente = new ArrayList <Cliente>();

	public boolean insere(Cliente cliente) throws ClassNotFoundException {
		Utils.criarDiretorio();
		listaCliente = retorna();
		listaCliente.add(cliente);
		return salva(listaCliente);

	}
	public boolean altera(int index, Cliente cliente) throws ClassNotFoundException {
		Utils.criarDiretorio();
		listaCliente = retorna();
		listaCliente.set(index, cliente);
		return salva(listaCliente);
	}
	public boolean exclui(int id) throws ClassNotFoundException{
		Utils.criarDiretorio();
		listaCliente = retorna();
		int index = retornaIndex(id);
		if(index != -1)
			listaCliente.remove(index);
		else
			return false;
		return salva(listaCliente);
	}
	public boolean salva(ArrayList <Cliente> listaCliente){
		Utils.criarDiretorio();
		try{
			FileOutputStream fos = new FileOutputStream(Utils.nomeDiretorioDados() + "/clientes.txt");
			ObjectOutputStream oos = new ObjectOutputStream (fos);
			oos.writeObject(listaCliente);
			oos.close();
		}catch (IOException e){
			return false;
		}
		return true;
	}
	public boolean organizaIndex() throws ClassNotFoundException{
		Utils.criarDiretorio();
		listaCliente = retorna();
		for(int i = 0; i < listaCliente.size(); i++){
			listaCliente.get(i).setId(i);
		}
		salva(listaCliente);
		return true;
	}
	public int qtdCadastrados() throws ClassNotFoundException{
		Utils.criarDiretorio();
		organizaIndex();
		listaCliente = retorna();
		return listaCliente.size();
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Cliente> retorna() throws ClassNotFoundException{
		Utils.criarDiretorio();
		try{
			FileInputStream fis = new FileInputStream(Utils.nomeDiretorioDados() + "/clientes.txt");
			ObjectInputStream ois = new ObjectInputStream (fis);
			listaCliente = (ArrayList<Cliente>) ois.readObject();
			ois.close();
		}catch (IOException e){

		}
		return listaCliente;
	}
	public int retornaIndex(int id) throws ClassNotFoundException{
		Utils.criarDiretorio();
		listaCliente = retorna();
		for(int i = 0; i < listaCliente.size(); i++){
			if(id == listaCliente.get(i).getId()){
				return i;
			}
		}
		return -1;
	}
}