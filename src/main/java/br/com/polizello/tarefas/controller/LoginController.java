package br.com.polizello.tarefas.controller;

import javax.servlet.http.HttpSession;


import br.com.polizello.dao.JdbcUsuarioDao;
import br.com.polizello.modelo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
	private JdbcUsuarioDao dao;
	
	@Autowired
	public LoginController(JdbcUsuarioDao dao) {
		this.dao = dao;
	}

	@RequestMapping("loginForm")
	public String loginForm(){
		return "formulario-login";
	}
	
	@RequestMapping("efetuaLogin")
	public String efetuaLogin(Usuario usuario, HttpSession session){
		Usuario usuarioLogado = dao.buscaUsuario(usuario);		
		
		if(usuarioLogado != null){
			session.setAttribute("usuarioLogado", usuarioLogado);
			return "menu";
		}
		
		return "redirect:loginForm";		
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:loginForm";
	}
	
}
