/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progweb.projeto;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;
/**
 *
 * @author Aluno
 */
@WebServlet(name = "Postar", urlPatterns = {"/Postar"})
@MultipartConfig
public class Postar extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        
        
        
        response.setContentType("text/html;charset=UTF-8");
        
        // Create path components to save the file
        final String path =  "D:\\home\\site\\wwwroot\\bin\\apache-tomcat-8.5.24\\webapps\\PRJ03";//"c:\\arquivos";//
        
        final Part videoFilePart = request.getPart("video");
        final String videoFileName = getFileName(videoFilePart);
        
        final Part imageFilePart = request.getPart("imagem");
        final String imageFileName = getFileName(imageFilePart);
        
        InputStream filecontent = null;
         OutputStream imageOutFile = null;
         OutputStream videoOutFile = null;
        try (PrintWriter out = response.getWriter()) {
            
            Object logado =  request.getSession().getAttribute("logado");
   
            if(logado == null){
                response.sendRedirect("login.html");
               out.println("Faça login primeiro");
               return;
            }
            
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Resultado</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Resultado</h1>");
         
            
            Boolean continuar =true;
                    String titulo = request.getParameter("titulo");
                    if(titulo == null || titulo.isEmpty()){
                        out.println("O titulo é obrigatório.<br/>");
                        continuar = false;
                    }
                    
                    String imagem = request.getParameter("imagem") + "";
                    if((imagem ).isEmpty()){
                        out.println("A imagem é obrigatória.<br/>");
                        continuar = false;
                    }
                    
                    String texto = request.getParameter("texto");
                    if(texto == null || texto.isEmpty()){
                        out.println("O texto é obrigatório.<br/>");
                        continuar = false;
                    }
                   
                
            
            
            String method = request.getMethod().toLowerCase() ;
            if (method.equals("post") && continuar){
            try //A captura de exceções SQLException em Java é obrigatória para usarmos JDBC.   
        {

            // Registrado o driver, vamos estabelecer uma conexão  
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                try ( //Class.forName("com.mysql.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://db4free.net/blogwebprj?useTimezone=false&serverTimezone=UTC&useSSL=false", "blogwebprj", "blogwebprj")) {
                    String consulta = "insert into postagem(titulo, imagem, texto,video,nomeUsuario, timestamp) values (?, ?, ?, ?,?,?)";
                    //String consulta = "insert into postagem(titulo, imagem, texto) values ('21312', 'fwe', 'erw')";
                    PreparedStatement stmt = con.prepareStatement (consulta);
                    
                    
                    stmt.setString(1,request.getParameter("titulo"));
                    stmt.setString(2,imageFileName);
                    stmt.setString(3,request.getParameter("texto"));
                    stmt.setString(4,videoFileName);
                    stmt.setString(5,request.getSession().getAttribute("nomeUsuario").toString());
                    
                    
                    long time = new Date().getTime();
                    Timestamp timestamp = new Timestamp(time);
                    stmt.setTimestamp(6, timestamp);
                    
                    imageOutFile = new FileOutputStream(new File(path + File.separator
                                + imageFileName));
                        filecontent = imageFilePart.getInputStream();

                        int read = 0;
                        byte[] bytes = new byte[1024];

                        while ((read = filecontent.read(bytes)) != -1) {
                            imageOutFile.write(bytes, 0, read);
                        }
                    
                        if(videoFileName!=null && videoFileName.isEmpty() == false){
                                videoOutFile = new FileOutputStream(new File(path + File.separator
                                    + videoFileName));
                            filecontent = videoFilePart.getInputStream();

                            read = 0;
                            bytes = new byte[1024];

                            while ((read = filecontent.read(bytes)) != -1) {
                                videoOutFile.write(bytes, 0, read);
                            }
                        }
                    
                    int rs = stmt.executeUpdate();
                    
                    if (rs > 0){
                        response.sendRedirect("index.jsp");//redireciona para a página principal
                        out.println(request.getParameter("titulo") + " Inserido com sucesso!");
                        
                    }
                    else
                    {
                        out.println("Erro!");
                    }   }
                
                
                

        } catch (SQLException e) {
            // se houve algum erro, uma exceção é gerada para informar o erro   
            e.printStackTrace(); //vejamos que erro foi gerado e quem o gerou 
            
            out.println(e.getMessage());
        }
            catch (FileNotFoundException fne) {
        out.println("You either did not specify a file to upload or are "
                + "trying to upload a file to a protected or nonexistent "
                + "location.");
        out.println("<br/> ERROR: " + fne.getMessage());

        //LOGGER.log(Level.SEVERE, "Problems during file upload. Error: {0}", 
          //      new Object[]{fne.getMessage()});
    } finally {
        if (imageOutFile != null) {
            imageOutFile.close();
        }
        if (filecontent != null) {
            filecontent.close();
        }
        if (out != null) {
            out.close();
        }
    }
            
            }
            
            
            
            out.println("</body>");
            out.println("</html>");
        }
        
        
        
        
        
    }
  private String getFileName(final Part part) {
    final String partHeader = part.getHeader("content-disposition");
    //LOGGER.log(Level.INFO, "Part Header = {0}", partHeader);
    for (String content : part.getHeader("content-disposition").split(";")) {
        if (content.trim().startsWith("filename")) {
            return content.substring(
                    content.indexOf('=') + 1).trim().replace("\"", "");
        }
    }
    return null;
}
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
