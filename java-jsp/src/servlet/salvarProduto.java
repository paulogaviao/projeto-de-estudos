package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.DaoProduto;
import bean.beanProduto;

@WebServlet("/salvarProduto")
public class salvarProduto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoProduto daoproduto = new DaoProduto();

	public salvarProduto() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String acao = request.getParameter("acao");

		String produto = request.getParameter("produto");

		try {
			if (acao.equalsIgnoreCase("delete")) {
				daoproduto.delete(produto);

				// carrega os usuarios e retona para mesma pagina
				RequestDispatcher view = request.getRequestDispatcher("/produtos.jsp");
				request.setAttribute("produtos", daoproduto.listar());
				view.forward(request, response);
			} else if (acao.equalsIgnoreCase("editar")) {
				daoproduto.consultar(produto);

				RequestDispatcher view = request.getRequestDispatcher("/produtos.jsp");
				request.setAttribute("produto", daoproduto.consultar(produto));
				view.forward(request, response);
			} else if (acao.equalsIgnoreCase("listartodos")) {
				RequestDispatcher view = request.getRequestDispatcher("/produtos.jsp");
				request.setAttribute("produtos", daoproduto.listar());
				view.forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");

		if (acao != null && acao.equalsIgnoreCase("reset")) {
			RequestDispatcher view = request.getRequestDispatcher("/produtos.jsp");

			try {
				request.setAttribute("produtos", daoproduto.listar());
			} catch (Exception e) {
				e.printStackTrace();
			}
			view.forward(request, response);
		} else {

			String id = request.getParameter("id");

			String nome = request.getParameter("nome");

			String quantidade = request.getParameter("quantidade");

			String valor = request.getParameter("valor");

			try {

				boolean podeInserir = true;

				if (nome == null || nome.isEmpty()) {
					request.setAttribute("msg", "Deve-se inserir o Nome do produto para progredir no cadastro");
					podeInserir = false;
				} else if (quantidade == null || quantidade.isEmpty()) {
					request.setAttribute("msg", "Deve-se inserir a Quantidade do produto para progredir no cadastro");
					podeInserir = false;
				} else if (valor == null || valor.isEmpty()) {
					request.setAttribute("msg", "Deve-se inserir o Valor do produto para progredir no cadastro");
					podeInserir = false;
				}else /* esta condição valida login e senha do cadastro */
				if (id == null || id.isEmpty() && !daoproduto.validarNome(nome)) {
					request.setAttribute("msg", "Não foi possivel cadastrar..produto já existe no sistema");
					podeInserir = false;

				}

				beanProduto produto = new beanProduto();

				produto.setNome(nome);
				produto.setId(!id.isEmpty() ? Long.parseLong(id) : null);

				if (quantidade != null && !quantidade.isEmpty()) {
					produto.setQuantidade(Double.parseDouble(quantidade));
				}
				if (valor != null && !valor.isEmpty()) {
					produto.setValor(Double.parseDouble(valor));
				} 
				/* se os campos estiveram de acordo sera salvo nesta condição */
				if (id == null || id.isEmpty() && daoproduto.validarNome(nome) && podeInserir) {

					daoproduto.salvar(produto);

				} else if (id != null && !id.trim().isEmpty() && podeInserir && nome != null && !nome.trim().isEmpty()) {
					if (daoproduto.validarNomeUpdate(nome, id)) {
						daoproduto.atualizar(produto);

					} else {
						request.setAttribute("msg", "Não foi possivel atualizar..Produto já existe no sistema");
						podeInserir = false;
					}
				}

				if (!podeInserir) {
					request.setAttribute("produto", produto);
				}

				RequestDispatcher view = request.getRequestDispatcher("/produtos.jsp");
				request.setAttribute("produtos", daoproduto.listar());
				view.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
