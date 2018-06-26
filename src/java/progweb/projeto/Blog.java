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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aluno
 */
public class Blog {
    
    
     public static ArrayList<Postagem> getPostagens(Timestamp timestamp){
            ArrayList<Postagem> postagens = new ArrayList();
        try {
            
            
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
             Connection con = DriverManager.getConnection("jdbc:mysql://db4free.net/blogwebprj?useTimezone=false&serverTimezone=UTC&useSSL=false", "blogwebprj", "blogwebprj");
                    
                    String consulta = "select * from postagem where timestamp >= ? order by timestamp DESC";
                    PreparedStatement stmt = con.prepareStatement (consulta);
                    
                    stmt.setTimestamp(1,timestamp);
                    
                    ResultSet rs = stmt.executeQuery();
                    
                    while(rs.next()){
                        Postagem p = new Postagem(rs.getString("titulo"),rs.getString("imagem"),rs.getString("texto"),rs.getString("video"),rs.getString("nomeUsuario"),rs.getLong("curtidas"),rs.getTimestamp("timestamp") );
                        p.setId(rs.getInt("idpostagem"));
                        postagens.add(p);
                    }
                    
        } catch (SQLException ex) {
            Logger.getLogger(Blog.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return postagens;
    }
    
    
    
    public static ArrayList<Postagem> getPostagens(String busca){
            ArrayList<Postagem> postagens = new ArrayList();
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
             Connection con = DriverManager.getConnection("jdbc:mysql://db4free.net/blogwebprj?useTimezone=true&serverTimezone=UTC&useSSL=false", "blogwebprj", "blogwebprj");
                    
                    String consulta = (busca== null || busca.equals(""))? "select * from postagem  order by timestamp DESC":"select * from postagem where titulo like ? or texto like ?  order by timestamp DESC";
                    PreparedStatement stmt = con.prepareStatement (consulta);
                    
                    if(busca!= null && !busca.equals("")){
                        stmt.setString(1,"%"+busca+"%");
                        stmt.setString(2,"%"+busca+"%");
                    }
                    
                    ResultSet rs = stmt.executeQuery();
                    
                    while(rs.next()){
                        Postagem p = new Postagem(rs.getString("titulo"),rs.getString("imagem"),rs.getString("texto"),rs.getString("video"),rs.getString("nomeUsuario"),rs.getLong("curtidas"),rs.getTimestamp("timestamp") );
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
            
             Connection con = DriverManager.getConnection("jdbc:mysql://db4free.net/blogwebprj?useTimezone=true&serverTimezone=UTC&useSSL=false", "blogwebprj", "blogwebprj");
                    String consulta = "select * from postagem where idpostagem = ?";
                    
                    
                    
                    
                    PreparedStatement stmt = con.prepareStatement (consulta);
                    stmt.setInt(1,id);
                    
                    ResultSet rs = stmt.executeQuery();
                    
                    while(rs.next()){
                        Postagem p = new Postagem(rs.getString("titulo"),rs.getString("imagem"),rs.getString("texto"),rs.getString("video"),rs.getString("nomeUsuario"),rs.getLong("curtidas"),rs.getTimestamp("timestamp"));
                        p.setId(rs.getInt("idpostagem"));
                        postagens.add(p);
                    }
                    
        } catch (SQLException ex) {
            Logger.getLogger(Blog.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return postagens.get(0);
    }
}
    

