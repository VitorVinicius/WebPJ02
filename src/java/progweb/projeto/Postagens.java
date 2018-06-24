/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progweb.projeto;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author Vitor
 */
public class Postagens {
    private Timestamp timestamp;

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
    private  ArrayList<Postagem> postagens = new ArrayList<Postagem>();

    

    public ArrayList<Postagem> getPostagens() {
        return postagens;
    }

    public void setPostagens(ArrayList<Postagem> postagens) {
        this.postagens = postagens;
    }
    
    
}
