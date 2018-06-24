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
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

 

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

            // Registrado o driver, vamos estabelecer uma conexão  
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                try ( //Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://db4free.net/blogwebprj?useTimezone=true&serverTimezone=UTC&useSSL=false", "blogwebprj", "blogwebprj")) {
                    String consulta = " select  * "
                            + "         from    login"
                            + "         where   nomeUsuario = ? AND"
                            + "                 senha = ?";
                    //String consulta = "insert into login(nomeUsuario, nomeCompleto, senha) values (?, ?, ?)";
                    //String consulta = "insert into postagem(titulo, imagem, texto) values ('21312', 'fwe', 'erw')";
                    PreparedStatement stmt = con.prepareStatement (consulta);
                    String usuario = request.getParameter("nomeUsuario");
                    String senha = request.getParameter("senha");
                    stmt.setString(1,usuario);
                    stmt.setString(2,senha);

                    ResultSet rs = stmt.executeQuery();
                    
                    while(rs.next()){
                         out.println(rs.getString("nomeUsuario"));
                        if(request.getParameter("nomeUsuario").equals(rs.getString("nomeUsuario"))){
                            //out.println("TEM UM USUARIO COM ESSE NOME");
                            out.println("Logado com sucesso.");
                            //estabelecer sessão aqui!!
                            
                            request.getSession().setAttribute("logado", true);
                            request.getSession().setAttribute("nomeUsuario", usuario);
                            
                            
                             response.sendRedirect("index.jsp");//redireciona para a página principal
                             return;
                             //break;
                        }
                        
                    }
                    response.sendRedirect("login.html");
                    out.println("Falha no login.");
                       }

        } catch (SQLException e) {
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
