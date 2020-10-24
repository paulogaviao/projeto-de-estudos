package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.DaoTelefones;
import Dao.DaoUsuario;
import bean.beanTelefones;
import bean.beanValidacao;

@WebServlet("/salvarTelefones")
public class salvarTelefones extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoUsuario daousuario = new DaoUsuario();
	private DaoTelefones daotelefones = new DaoTelefones();

	public salvarTelefones() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			String user = request.getParameter("user");

			String acao = request.getParameter("acao");

			if (acao.endsWith("addfone")) {

				beanValidacao usuario = daousuario.consultar(user);

				request.getSession().setAttribute("userEscolhido", usuario);
				request.setAttribute("userEscolhido", usuario);

				request.setAttribute("telefones", daotelefones.listar(usuario.getId()));
				RequestDispatcher view = request.getRequestDispatcher("/telefones.jsp");

				request.setAttribute("msg", "salvo com sucesso");
				view.forward(request, response);
			} else if (acao.endsWith("deletefone")) {

				String foneId = request.getParameter("foneId");
				daotelefones.delete(foneId);
				beanValidacao beanvalidacao = (beanValidacao) request.getSession().getAttribute("userEscolhido");

				request.setAttribute("telefones", daotelefones.listar(beanvalidacao.getId()));
				RequestDispatcher view = request.getRequestDispatcher("/telefones.jsp");

				request.setAttribute("msg", "removido com sucesso");
				view.forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			beanValidacao beanvalidacao = (beanValidacao) request.getSession().getAttribute("userEscolhido");

			String numero = request.getParameter("numero");

			String tipo = request.getParameter("tipo");

			beanTelefones telefones = new beanTelefones();

			telefones.setNumero(numero);
			telefones.setTipo(tipo);
			telefones.setUsuario(beanvalidacao.getId());

			request.getSession().setAttribute("userEscolhido", beanvalidacao);
			request.setAttribute("userEscolhido", beanvalidacao);

			daotelefones.salvar(telefones);

			RequestDispatcher view = request.getRequestDispatcher("/telefones.jsp");
			request.setAttribute("telefones", daotelefones.listar(beanvalidacao.getId()));
			request.setAttribute("msg", "salvo com sucesso");
			view.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
