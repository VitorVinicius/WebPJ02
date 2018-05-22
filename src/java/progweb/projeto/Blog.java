/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progweb.projeto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aluno
 */
public class Blog {
    public static ArrayList<Postagem> getPostagens(){
            ArrayList<Postagem> postagens = new ArrayList();
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
             Connection con = DriverManager.getConnection("jdbc:mysql://localhost/blog", "root", "utfpr");
                    String consulta = "select * from postagem";
                    PreparedStatement stmt = con.prepareStatement (consulta);
                    
                    ResultSet rs = stmt.executeQuery();
                    
                    while(rs.next()){
                        Postagem p = new Postagem(rs.getString("titulo"),rs.getString("imagem"),rs.getString("texto"));
                        p.setId(rs.getInt("idpostagem"));
                        postagens.add(p);
                    }
                    
        } catch (SQLException ex) {
            Logger.getLogger(Blog.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return postagens;
    }
    public static Postagem getPostagem(int id){
            ArrayList<Postagem> postagens = new ArrayList();
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
             Connection con = DriverManager.getConnection("jdbc:mysql://localhost/blog", "root", "utfpr");
                    String consulta = "select * from postagem where idpostagem = ?";
                    
                    
                    
                    
                    PreparedStatement stmt = con.prepareStatement (consulta);
                    stmt.setInt(1,id);
                    
                    ResultSet rs = stmt.executeQuery();
                    
                    while(rs.next()){
                        Postagem p = new Postagem(rs.getString("titulo"),rs.getString("imagem"),rs.getString("texto"));
                        p.setId(rs.getInt("idpostagem"));
                        postagens.add(p);
                    }
                    
        } catch (SQLException ex) {
            Logger.getLogger(Blog.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return postagens.get(0);
    }
}
    

