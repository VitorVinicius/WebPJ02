/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progweb.projeto;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Aluno
 */
@WebServlet(name = "Editar", urlPatterns = {"/Editar"})
public class Editar extends HttpServlet {

    

    /**
     * Handles the HTTP <code>POST</code> method.
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
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/blog", "root", "utfpr")) {
                    String consulta = "update postagem set titulo = ?, imagem = ?, texto = ? where idpostagem = ?";
                    //String consulta = "insert into postagem(titulo, imagem, texto) values ('21312', 'fwe', 'erw')";
                    PreparedStatement stmt = con.prepareStatement (consulta);
                    
                    
                    stmt.setString(1,request.getParameter("titulo"));
                    stmt.setString(2,request.getParameter("imagem"));
                    stmt.setString(3,request.getParameter("texto"));
                    stmt.setString(4,request.getParameter("id"));
                    
                    int rs = stmt.executeUpdate();
                    
                    if (rs > 0){
                        response.sendRedirect("index.jsp");//redireciona para a página principal
                        out.println(request.getParameter("titulo") + " Salvo com sucesso!");
                    }
                    else
                    {
                        out.println("Erro! SQL:");
                        out.println(stmt);
                    }   }

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
    }

}
