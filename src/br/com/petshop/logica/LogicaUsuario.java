package br.com.petshop.logica;

import java.util.ArrayList;

import br.com.petshop.dados.DadosUsuario;
import br.com.petshop.entidades.Usuario;

public class LogicaUsuario {
	private Usuario usuario;
	private DadosUsuario du = new DadosUsuario();

	public ArrayList<Object> JTableUsuario(boolean mostrarSenhaCkbxUsu) {
		Object[] row;
		ArrayList<Usuario> usuarios = null;
		ArrayList<Object> dados = new ArrayList<Object>();
		try {
			usuarios = du.retorna();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		for (Usuario usu : usuarios) {
			row = new Object[4];
			row[0] = usu.getId();
			row[1] = usu.getLogin();
			if (mostrarSenhaCkbxUsu)
				row[2] = usu.getSenha();
			else
				row[2] = "******";
			row[3] = (usu.getGrupo() == 0) ? "Administrador" : "Normal";

			dados.add(row);
		}
		return dados;
	}

	public boolean consultaLogin(String login) throws ClassNotFoundException {
		for (Usuario usu : du.retorna()) {
			if (login.equals(usu.getLogin())) {
				return true;
			}
		}
		return false;
	}

	public boolean inserir(String login, char[] senha, int grupo) throws ClassNotFoundException {
		Usuario usuario = new Usuario(du.qtdCadastrados(), login, senha, grupo);

		if (du.insere(usuario))
			return true;
		else
			return false;
	}

	public boolean excluir(int id) throws ClassNotFoundException {
		if (du.exclui(id)) {
			du.organizaIndex();
			return true;
		} else
			return false;
	}

	public boolean alterar(int id, String login, char[] senha, int grupo) throws ClassNotFoundException {
		if (login != null && senha != null && grupo != -1) {
			usuario = new Usuario(id, login, senha, grupo);

			if (du.altera(id, usuario))
				return true;
		}
		return false;
	}

	public boolean autenticaUsuario(String login, char[] senhaAux) throws ClassNotFoundException {
		ArrayList<Usuario> listaUsuarios = du.retorna();
		String senha = String.valueOf(senhaAux);
		for (Usuario usuario : listaUsuarios) {
			if (usuario.getLogin().equals(login) && usuario.getSenha().equals(senha)) {
				return true;
			}
		}
		return false;
	}

	public String cadastraUsuarioAdmin() throws ClassNotFoundException {
		ArrayList<Usuario> listaUsuarios = du.retorna();

		if (listaUsuarios.size() == 0) {
			char[] senha = { '1', '2', '3', '4' };
			Usuario usuario = new Usuario(0, "admin", senha, 0);

			du.insere(usuario);
			return "Login: " + usuario.getLogin() + ", Senha: " + usuario.getSenha();
		}
		return null;
	}

	public int retornaID(String login, char[] senhaAux) throws ClassNotFoundException {
		ArrayList<Usuario> listaUsuarios = du.retorna();
		int index = 0;
		String senha = String.valueOf(senhaAux);
		for (int i = 0; i < listaUsuarios.size(); i++) {
			if (listaUsuarios.get(i).getLogin().equals(login) && listaUsuarios.get(i).getSenha().equals(senha)) {
				index = i;
				break;
			}
		}
		return index;
	}
}
