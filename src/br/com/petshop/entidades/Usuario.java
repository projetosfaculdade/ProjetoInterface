package br.com.petshop.entidades;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Usuario implements Serializable{
	private int id;
	private String login;
	private String senha;
	private int grupo; //adm, normal
	@SuppressWarnings("unused")
	private String grupos[] = {"adm", "normal"};
	private boolean status;
	
	public Usuario(){}
	
	public Usuario(int id, String login, char[] senhaAux, int grupo){
		this.id = id;
		this.login = login;
		String senha = String.valueOf(senhaAux);
		this.senha = senha;
		this.grupo = grupo;
		this.status = true;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public int getGrupo() {
		return grupo;
	}
	public void setGrupo(int grupo) {
		this.grupo = grupo;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String toString(){
		return "Login: " + getLogin() + " | Status: " + parseStatusToString();
	}
	public String parseStatusToString(){
		if (this.getStatus() == true){
			return "ATIVO";
		}
		return "INATIVO";
	}
	
}