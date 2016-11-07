package br.com.petshop.dados;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import br.com.petshop.entidades.Animal;
import br.com.petshop.utils.Utils;

public class DadosAnimal {
	ArrayList <Animal> listaAnimais = new ArrayList <Animal>();

	public boolean insere(Animal animal){
		Utils.criarDiretorio();
		listaAnimais = retorna();
		listaAnimais.add(animal);
		return salva(listaAnimais);

	}
	public boolean altera(int index, Animal animal) {
		Utils.criarDiretorio();
		listaAnimais = retorna();
		listaAnimais.set(index, animal);
		return salva(listaAnimais);
	}
	public boolean exclui(int id){
		Utils.criarDiretorio();
		listaAnimais = retorna();
		int index = retornaIndex(id);
		if(index != -1)
			listaAnimais.remove(index);
		else
			return false;
		return salva(listaAnimais);
	}
	public boolean salva(ArrayList <Animal> listaAnimais){
		Utils.criarDiretorio();
		try{
			FileOutputStream fos = new FileOutputStream(Utils.nomeDiretorioDados() + "/animais.txt");
			ObjectOutputStream oos = new ObjectOutputStream (fos);
			oos.writeObject(listaAnimais);
			oos.close();
		}catch (IOException e){
			return false;
		}
		return true;
	}
	public void organizaIndex(){
		Utils.criarDiretorio();
		listaAnimais = retorna();
		for(int i = 0; i < listaAnimais.size(); i++){
			listaAnimais.get(i).setId(i);
		}
		salva(listaAnimais);
	}
	public int qtdCadastrados(){
		Utils.criarDiretorio();
		organizaIndex();
		listaAnimais = retorna();
		return listaAnimais.size();
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Animal> retorna(){
		Utils.criarDiretorio();
		try{
			FileInputStream fis = new FileInputStream(Utils.nomeDiretorioDados() + "/animais.txt");
			ObjectInputStream ois = new ObjectInputStream (fis);
			listaAnimais = (ArrayList<Animal>) ois.readObject();
			ois.close();
		}catch (IOException e){

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return listaAnimais;
	}
	public int retornaIndex(int id){
		Utils.criarDiretorio();
		listaAnimais = retorna();
		for(int i = 0; i < listaAnimais.size(); i++){
			if(id == listaAnimais.get(i).getId()){
				return i;
			}
		}
		return -1;
	}
}
