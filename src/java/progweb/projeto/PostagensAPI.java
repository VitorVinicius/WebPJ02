/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progweb.projeto;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Aluno
 */
@WebServlet(name = "BlogAPI", urlPatterns = {"/API/Posts"})
public class PostagensAPI extends HttpServlet {

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String timestampDe = request.getParameter("timestampDe");
        //String timestampAte = request.getParameter("timestampAte");
        
        
        String busca = request.getParameter("busca");
        ArrayList<Postagem> posts = new ArrayList<Postagem>();
        Postagens postagens = new Postagens();
        postagens.setPostagens(posts);
        if(busca != null || timestampDe == null){
            posts.addAll(Blog.getPostagens(busca));
        }
        else 
            if(timestampDe!= null){
               
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date date;
            try {
                date = new Date( dateFormat.parse(timestampDe).getTime());
                
                long time = date.getTime();
                Timestamp timestamp = new Timestamp(time);
                posts.addAll(Blog.getPostagens(timestamp));
                postagens.setTimestamp(timestamp); 
                
            } catch (ParseException ex) {
                Logger.getLogger(PostagensAPI.class.getName()).log(Level.SEVERE, null, ex);
            }
                
        }
        
        //Postagem postagem =  new Postagem("titulo","imagem","texto","video","nomeUsuario",123,456);
        
        //postagens.add(postagem);
        
        //Informacoes obj = new Informacoes();
        Gson gson = new Gson();

        // converte objetos Java para JSON e retorna JSON como String
        String json = gson.toJson(postagens);
        
        
        response.setContentType("application/json");
        try (PrintWriter out = response.getWriter()) {
            out.println(json);
        }
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
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
