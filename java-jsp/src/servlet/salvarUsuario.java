package servlet;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.print.DocFlavor.INPUT_STREAM;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.tomcat.util.codec.binary.Base64;

import Dao.DaoUsuario;
import bean.beanValidacao;
import javax.servlet.annotation.MultipartConfig;;

@WebServlet("/salvarUsuario")
@MultipartConfig

public class salvarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// tela onde recebe os dados da pagina e executa as açoes requisitadas

	private DaoUsuario daoUsuario = new DaoUsuario();

	public salvarUsuario() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");

		String user = request.getParameter("user");

		try {
			if (acao.equalsIgnoreCase("delete")) {
				daoUsuario.delete(user);

				// carrega os usuarios e retona para mesma pagina
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuario", daoUsuario.listar());
				view.forward(request, response);
			} else if (acao.equalsIgnoreCase("editar")) {
				daoUsuario.consultar(user);

				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("user", daoUsuario.consultar(user));
				view.forward(request, response);
			} else if (acao.equalsIgnoreCase("listartodos")) {
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuario", daoUsuario.listar());
				view.forward(request, response);
			} else if (acao.equalsIgnoreCase("download")) {
				beanValidacao usuario = daoUsuario.consultar(user);
				if (usuario != null) {
					byte[] fileBytes = null;
					String contentType = "";
					/*
					 * converte o arquivo base 64 do banco de dados que esta em string para byte[]
					 */
					String tipo = request.getParameter("tipo");
					if (tipo.equalsIgnoreCase("imagem")) {
						contentType = usuario.getContentType();
						fileBytes = new Base64().decodeBase64(usuario.getFotoBase64());
					} else if (tipo.equalsIgnoreCase("curriculo")) {
						contentType = usuario.getContentTypeCurriculo();
						fileBytes = new Base64().decodeBase64(usuario.getCurriculoBase64());
					}

					response.setHeader("Content-Disposition",
							"attachment;filename=arquivo." + contentType.split("\\/")[1]);

					/* coloca os bytes em um objeto para fazer o processo */

					InputStream is = new ByteArrayInputStream(fileBytes);

					/* inicio da resposta para o navegador */
					int read = 0;

					byte[] bytes = new byte[1024];
					OutputStream os = response.getOutputStream();

					while ((read = is.read(bytes)) != -1) {
						os.write(bytes, 0, read);
					}
					os.flush();
					os.close();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String acao = request.getParameter("acao");

		if (acao != null && acao.equalsIgnoreCase("reset")) {
			RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");

			try {
				request.setAttribute("usuario", daoUsuario.listar());
			} catch (Exception e) {
				e.printStackTrace();
			}
			view.forward(request, response);
		} else {

			String id = request.getParameter("id");

			String login = request.getParameter("login");

			String senha = request.getParameter("senha");

			String nome = request.getParameter("nome");

			String fone = request.getParameter("fone");

			String cep = request.getParameter("cep");

			String rua = request.getParameter("rua");

			String bairro = request.getParameter("bairro");

			String cidade = request.getParameter("cidade");

			String estado = request.getParameter("estado");

			String ibge = request.getParameter("ibge");

			beanValidacao usuario = new beanValidacao();

			usuario.setId(!id.isEmpty() ? Long.parseLong(id) : 0);
			usuario.setLogin(login);
			usuario.setSenha(senha);
			usuario.setNome(nome);
			usuario.setFone(fone);
			usuario.setCep(cep);
			usuario.setRua(rua);
			usuario.setBairro(bairro);
			usuario.setCidade(cidade);
			usuario.setEstado(estado);
			usuario.setIbge(ibge);

			try {

				/* inicio do upload de foto */
				if (ServletFileUpload.isMultipartContent(request)) {

					Part imagemFoto = request.getPart("foto");
					if (imagemFoto != null && imagemFoto.getInputStream().available() > 0) {
						imagemFoto.getInputStream();

						String fotoBase64 = new Base64()
								.encodeBase64String(converteStremParabyte(imagemFoto.getInputStream()));
						usuario.setFotoBase64(fotoBase64);
						usuario.setContentType(imagemFoto.getContentType());
					}else {
						usuario.setFotoBase64(request.getParameter("fotoTemp"));
						usuario.setContentType(request.getParameter("contentTypeTemp"));
					}
					Part curriculoPdf = request.getPart("curriculo");
					if (curriculoPdf != null && curriculoPdf.getInputStream().available() > 0) {
						curriculoPdf.getInputStream();

						String curriculoBase64 = new Base64()
								.encodeBase64String(converteStremParabyte(curriculoPdf.getInputStream()));
						usuario.setCurriculoBase64(curriculoBase64);
						usuario.setContentTypeCurriculo(curriculoPdf.getContentType());
					}else {
						usuario.setCurriculoBase64(request.getParameter("fotoTempPdf"));
						usuario.setContentTypeCurriculo(request.getParameter("contentTypeTempPdf"));
					}
				}

				/* fim do upload de foto */

				boolean podeInserir = true;

				if (login == null || login.isEmpty()) {
					request.setAttribute("msg", "Deve-se informar o Login para prosseguir..");
					podeInserir = false;
				} else if (senha == null || senha.isEmpty()) {
					request.setAttribute("msg", "Deve-se informar o Senha para prosseguir..");
					podeInserir = false;
				} else if (nome == null || nome.isEmpty()) {
					request.setAttribute("msg", "Deve-se informar o Nome para prosseguir..");
					podeInserir = false;
				} else if (fone == null || fone.isEmpty()) {
					request.setAttribute("msg", "Deve-se informar o Telefone para prosseguir..");
					podeInserir = false;
				} else

				if (id == null || id.isEmpty()
						&& !daoUsuario.validarLogin(login)) {/* esta condição valida login e senha do cadastro */
					request.setAttribute("msg", "Não foi possivel cadastrar..Login já existe no sistema");
					podeInserir = false;

				} else if (id == null || id.isEmpty() && daoUsuario.validarLogin(login) && podeInserir) {
					if (id == null || id.isEmpty() && daoUsuario.validarSenha(senha) && podeInserir) {
						daoUsuario.salvar(usuario);

					} else {
						request.setAttribute("msg", "Não foi possivel cadastrar..Senha já existe no sistema");
						podeInserir = false;
					}
				} else if (id != null || !id.isEmpty() && podeInserir) {
					if (daoUsuario.validarLoginUpdate(login, id)) {
						daoUsuario.atualizar(usuario);

					} else {
						request.setAttribute("msg", "Não foi possivel atualizar..Login já existe no sistema");
						podeInserir = false;
					}
				}

				if (!podeInserir) {
					request.setAttribute("user", usuario);
				}

				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuario", daoUsuario.listar());
				view.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	private byte[] converteStremParabyte(InputStream imagemFoto) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int reads = imagemFoto.read();
		while (reads != -1) {
			baos.write(reads);
			reads = imagemFoto.read();
		}
		return baos.toByteArray();
	}
}
