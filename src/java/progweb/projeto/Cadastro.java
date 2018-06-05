/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progweb.projeto;


import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Aluno
 */
@WebServlet(name = "Cadastro", urlPatterns = {"/Cadastro"})
public class Cadastro extends HttpServlet {

 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Resultado</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Resultado</h1>");
         
            
            String method = request.getMethod().toLowerCase() ;
            if (method.equals("post")){
            try //A captura de exceções SQLException em Java é obrigatória para usarmos JDBC.   
        {

                    Boolean continuar =true;
                    String nomeUsuario = request.getParameter("nomeUsuario");
                    if(nomeUsuario == null || nomeUsuario.isEmpty()){
                        out.println("O nome do usuário é obrigatório.<br/>");
                        continuar = false;
                    }
                    
                    String nomeCompleto = request.getParameter("nomeCompleto");
                    if(nomeCompleto == null || nomeCompleto.isEmpty()){
                        out.println("O nome completo do usuário é obrigatório.<br/>");
                        continuar = false;
                    }
                    String senha = request.getParameter("senha");
                    if(senha == null || senha.isEmpty()){
                        out.println("A senha é obrigatória.<br/>");
                        continuar = false;
                    }
                    String endereco = request.getParameter("endereco");
                    if(endereco == null || endereco.isEmpty()){
                        out.println("O endereço é obrigatório.<br/>");
                        continuar = false;
                    }
                
            // Registrado o driver, vamos estabelecer uma conexão  
            
            if(continuar){
            
                 DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                try ( //Class.forName("com.mysql.jdbc.Driver");
                     Connection con = DriverManager.getConnection("jdbc:mysql://db4free.net/blogwebprj?useTimezone=true&serverTimezone=UTC", "blogwebprj", "blogwebprj")) {
                    
                    
                    
                   
                    String consulta = "insert into login(nomeUsuario, nomeCompleto, senha, endereco) values (?, ?, ?,?)";
                    //String consulta = "insert into postagem(titulo, imagem, texto) values ('21312', 'fwe', 'erw')";
                    PreparedStatement stmt = con.prepareStatement (consulta);

                    stmt.setString(1,nomeUsuario);
                    stmt.setString(2,nomeCompleto);
                    stmt.setString(3,senha);
                    stmt.setString(4,endereco);
                    

                    int rs = stmt.executeUpdate();


                    if (rs > 0){
                        out.println(request.getParameter("nomeCompleto") + " Inserido com sucesso!");
                        response.sendRedirect("index.jsp");//redireciona para a página principal
                             
                    }
                    else
                    {
                        out.println("Erro!");
                    }   }
            
            }
           

        } 
            catch(java.sql.SQLIntegrityConstraintViolationException ex){
                out.println("Já existe um nome de usuário '"+request.getParameter("nomeUsuario")+"' cadastrado ");
            }
            catch (SQLException e) {
            // se houve algum erro, uma exceção é gerada para informar o erro   
            e.printStackTrace(); //vejamos que erro foi gerado e quem o gerou 
            
            out.println(e.getMessage());
        }          
            }
            out.println("</body>");
            out.println("</html>");
        }
    }
  
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
