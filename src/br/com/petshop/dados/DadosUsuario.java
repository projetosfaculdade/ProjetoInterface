package br.com.petshop.dados;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import br.com.petshop.entidades.Usuario;
import br.com.petshop.utils.Utils;

public class DadosUsuario{

	ArrayList <Usuario> listaUsuarios = new ArrayList <Usuario>();

	public boolean insere(Usuario Usuario) throws ClassNotFoundException {
		Utils.criarDiretorio();
		listaUsuarios = retorna();
		listaUsuarios.add(Usuario);
		return salva(listaUsuarios);

	}
	public boolean altera(int index, Usuario Usuario) throws ClassNotFoundException {
		Utils.criarDiretorio();
		listaUsuarios = retorna();
		listaUsuarios.set(index, Usuario);
		return salva(listaUsuarios);
	}
	public boolean exclui(int id) throws ClassNotFoundException{
		Utils.criarDiretorio();
		listaUsuarios = retorna();
		int index = retornaIndex(id);
		if(index != -1)
			listaUsuarios.remove(index);
		else
			return false;
		return salva(listaUsuarios);
	}
	public boolean salva(ArrayList <Usuario> listaUsuarios){
		Utils.criarDiretorio();
		try{
			FileOutputStream fos = new FileOutputStream(Utils.nomeDiretorioDados() + "/usuarios.txt");
			ObjectOutputStream oos = new ObjectOutputStream (fos);
			oos.writeObject(listaUsuarios);
			oos.close();
		}catch (IOException e){
			return false;
		}
		return true;
	}
	public void organizaIndex() throws ClassNotFoundException{
		Utils.criarDiretorio();
		listaUsuarios = retorna();
		for(int i = 0; i < listaUsuarios.size(); i++){
			listaUsuarios.get(i).setId(i);
		}
		salva(listaUsuarios);
	}
	public int qtdCadastrados() throws ClassNotFoundException{
		Utils.criarDiretorio();
		organizaIndex();
		listaUsuarios = retorna();
		return listaUsuarios.size();
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Usuario> retorna() throws ClassNotFoundException{
		Utils.criarDiretorio();
		try{
			FileInputStream fis = new FileInputStream(Utils.nomeDiretorioDados() + "/usuarios.txt");
			ObjectInputStream ois = new ObjectInputStream (fis);
			listaUsuarios = (ArrayList<Usuario>) ois.readObject();
			ois.close();
		}catch (IOException e){

		}
		return listaUsuarios;
	}
	public int retornaIndex(int id) throws ClassNotFoundException{
		Utils.criarDiretorio();
		listaUsuarios = retorna();
		for(int i = 0; i < listaUsuarios.size(); i++){
			if(id == listaUsuarios.get(i).getId()){
				return i;
			}
		}
		return -1;
	}
}