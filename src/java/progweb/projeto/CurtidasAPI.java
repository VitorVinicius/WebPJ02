/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progweb.projeto;

import com.google.gson.Gson;
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
@WebServlet(name = "CurtidasAPI", urlPatterns = {"/PostagensAPI/Curtir"})
public class CurtidasAPI extends HttpServlet {

    
    class RespostaCurtida{
        private long curtidas;

        public long getCurtidas() {
            return curtidas;
        }

        public void setCurtidas(long curtidas) {
            this.curtidas = curtidas;
        }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    

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
        
        
        try //A captura de exceções SQLException em Java é obrigatória para usarmos JDBC.   
        {

            // Registrado o driver, vamos estabelecer uma conexão  
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                try ( //Class.forName("com.mysql.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://db4free.net/blogwebprj?useTimezone=true&serverTimezone=UTC&useSSL=false", "blogwebprj", "blogwebprj")) 
                {
                    String consulta = "update postagem set curtidas = curtidas +1 where idpostagem = ?";
                    //String consulta = "insert into postagem(titulo, imagem, texto) values ('21312', 'fwe', 'erw')";
                    PreparedStatement stmt = con.prepareStatement (consulta);
                    
                    
                    int idPostagem = Integer.parseInt(request.getParameter("idPostagem"));
                    
                    
                    stmt.setLong(1,idPostagem);
                    
                    
                    response.setContentType("application/json");
                    
                    
                    int rs = stmt.executeUpdate();
                    
                    
                    try (PrintWriter out = response.getWriter()) {
                        
                        Postagem postagem = Blog.getPostagem(idPostagem);
                        
                        
                        RespostaCurtida resposta = new RespostaCurtida();
                        resposta.curtidas = postagem.getCurtidas();
                        
                        
                       
                         //Informacoes obj = new Informacoes();
                        Gson gson = new Gson();
                    
                        // converte objetos Java para JSON e retorna JSON como String
                        String json = gson.toJson(resposta);
                        
                        out.println(json);
                    
                    
                    }
                }

        } catch (SQLException e) {
            // se houve algum erro, uma exceção é gerada para informar o erro   
            e.printStackTrace(); //vejamos que erro foi gerado e quem o gerou 
            
        }
            
            
        }
        
        
        
        
        
    

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
