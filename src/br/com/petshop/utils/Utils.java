package br.com.petshop.utils;

import java.io.File;

public class Utils {
	public static String nomePrograma = "PetShop JLR - ";
	public static final byte indexPreco = 4;
	public static final byte indexTotal = 5;
	public static String mascaraCpf(String cpf){
		String cpfAux = cpf;
		String bloco1 = cpfAux.substring(0, 3);
		String bloco2 = cpfAux.substring(3, 6);
		String bloco3 = cpfAux.substring(6, 9);
		String bloco4 = cpfAux.substring(9, 11);
		cpfAux = bloco1+"."+bloco2+"."+bloco3+"-"+bloco4;

		return cpfAux;
	}
	public static String mascaraCnpj(String cnpj){
		String cnpjAux = cnpj;
		String bloco1 = cnpjAux.substring(0, 2);
		String bloco2 = cnpjAux.substring(2, 5);
		String bloco3 = cnpjAux.substring(5, 8);
		String bloco4 = cnpjAux.substring(8, 12);
		String bloco5 = cnpjAux.substring(12, 14);
		cnpjAux = bloco1+"."+bloco2+"."+bloco3+"/"+bloco4 +"-"+ bloco5;

		return cnpjAux;
	}

	public static String mascaraTelefone(String telefone){
		String telefoneAux = telefone;
		String bloco1 = telefoneAux.substring(0, 2);
		String bloco2 = telefoneAux.substring(2, 3);
		String bloco3 = telefoneAux.substring(3, 7);
		String bloco4 = telefoneAux.substring(7, 11);
		telefoneAux = "("+bloco1+")" + " "+bloco2+" "+bloco3+"-"+bloco4;

		return telefoneAux;
	}

	public static String mascaraCEP(String cep){
		//xxxxx-xxx.
		String cepAux = cep;
		String bloco1 = cepAux.substring(0, 5);
		String bloco2 = cepAux.substring(5, 8);
		cepAux = bloco1+"-"+bloco2;

		return cepAux;
	}
	public static boolean validaCpf(String cpf){
		if (cpf.length() != 11){
			return false;
		}
		return true;
	}
	public static boolean validaCep(String cep){
		if (cep.length() != 8){
			return false;
		}
		return true;
	}
	public static boolean validaCnpj(String cnpj){
		if (cnpj.length() != 14){
			return false;
		}
		return true;
	}
	public static boolean maiorAZero(double valor){
		if(valor > 0)
			return true;
		else
			return false;
	}
	public static boolean maiorAZero(int valor){
		if(valor > 0)
			return true;
		else
			return false;
	}

	public static boolean criarDiretorio(){
		try{
			File fi= new File("dados");
			fi.mkdir();
			return true;
		}catch(Exception e){
			return false;
		}
	}

	public static String nomeDiretorioDados(){
		return "dados";
	}
}
