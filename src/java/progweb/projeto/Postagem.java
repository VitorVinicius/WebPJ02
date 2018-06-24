/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progweb.projeto;

import java.sql.Timestamp;

/**
 *
 * @author Aluno
 */
public class Postagem {

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Postagem(String titulo, String imagem, String texto, String video,String nomeUsuario, long curtidas, Timestamp timestamp) {
        this.nomeUsuario = nomeUsuario;
        this.titulo = titulo;
        this.imagem = imagem;
        this.texto = texto;
        this.video = video;
        this.curtidas = curtidas;
        this.timestamp = timestamp;
    }

    

   String nomeUsuario;

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }
    String titulo;
    String imagem;
    String texto;
    String video;
    
    long curtidas;

    public long getCurtidas() {
        return curtidas;
    }

    public void setCurtidas(long curtidas) {
        this.curtidas = curtidas;
    }
    
    Timestamp timestamp; 

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
