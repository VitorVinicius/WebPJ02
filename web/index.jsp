<%-- 
    Document   : Postagens
    Created on : 18/05/2018, 16:51:42
    Author     : Aluno
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="progweb.projeto.Postagem"%>
<%@page import="progweb.projeto.Blog"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<% 
    Object logado =  request.getSession().getAttribute("logado");
    Object nomeUsuario =  request.getSession().getAttribute("nomeUsuario");
    
    


%>


<!DOCTYPE html>

<html>

  <head>
        <link rel="stylesheet" href="content/estilo.css" type="text/css"/>
        <title>Web2</title>
  </head>

  <body>
      <div class="cabecalho">
      <div class="cabecalhoHeader">
			<div class="headerMenuButton">
			</div>
		</div>
    
      <div class="conteudoCab">
        <div class="img"></div>
        <div class="tamanho">
          <h1 class="label">Reflex</h1>
          <label class="textoSup">Bem Vindo ao Blog <%=  (logado ==null?"":request.getSession().getAttribute("nomeUsuario")+"!")%></label>
          <form action="/index.jsp">
               <p><label for="nome">Pesquisar: </label>
                <input type = "search" name = "busca" id ="busca"></p>
                <input class="botaoGetStarted" type="submit"  value = "Buscar">
              
          </form>
          <div>
              <%=  (logado ==null?"":"<a href=\"/postar.jsp\"  ><span class=\"botaoAcao\">POSTAR</span></a>")%>
              <%=  (logado ==null?"":"<a href=\"/Logout\"  ><span class=\"botaoAcao\">SAIR</span></a>")%>
              <%=  (logado ==null?"<a href=\"/login.html\" ><span class=\"botaoAcao\">LOGIN</span></a>":"")%>
              <%=  (logado ==null?"<a href=\"/cadastro.html\" ><span class=\"botaoAcao\">CADASTRO</span></a>":"")%>
              
          </div>
        </div>
      </div>
    </div>
      <hr/>
      
      <% ArrayList<Postagem> postagens = Blog.getPostagens(request.getParameter("busca")); %>
        <%for ( Postagem post : postagens){ %>
        
        <div class="post">
     
      <div class="conteudoCab">
          <div class="img"><img class="imgPost" src='<%=  post.getImagem()%>'/></div>
        <div class="tamanho">
            <h1 class="label">
                <%if ( nomeUsuario!= null && nomeUsuario.toString().equals(post.getNomeUsuario())){ %>
                
                     <a href="./editar.jsp?id=<%=  post.getId()%>"><%=  post.getTitulo() %></a>
                
                   <%}else{%>
                     <%=  post.getTitulo() %>
                   <%}%>
            </h1>
          <label class="textoSup"><%=  post.getTexto()%></label>
          
          <%if ( post.getVideo() != null && !post.getVideo().isEmpty()){ %>
          <div>
            <video width="400" controls>
            <source src="<%=  post.getVideo()%>" >
            Your browser does not support HTML5 video.
          </video>
          </div>
            <%}%>
        </div>
      </div>
    </div>
        
      <%}%>
      
      
  <hr/>
    <div class="conteudo">
      <div class="textoInf">
        <div class="linha1"></div>
        <h2 class="callout1">Ipsum malesuada</h2>
        <div class="callout2">Vivamus quis pulvinar libero, non porttitor ipsum.
        Aliquam posuere, metus et vehicula mollis.</div>
      </div>

      <table style="width: 100%;">
        <tr>
          <td style="text-align: center; vertical-align: middle;">
            <input type="button" class="botaobottom1">
            <div class="espacoLinha">
              <div class="linha2"></div>
            </div>

            <input type="button" class="botaobottom2">
            <div class="espacoLinha">
              <div class="linha2"></div>
            </div>

            <input type="button" class="botaobottom3">
            <div class="espacoLinha">
              <div class="linha2"></div>
            </div>

            <input type="button" class="botaobottom4">
            <div class="espacoLinha">
              <div class="linha2"></div>
            </div>
            <input type="button" class="botaobottom5">
          </td>
        </tr>
      </table>

      <div class="callout3">Vivamus quis pulvinar libero, non porttitor ipsum. Aliquam sodales, felis sit amet pretium tincidunt, mi magna tristique nibh, a vulputate massa metus ornare risus.
      Sed metus dolor, consequat id condimentum et, aliquam et lacus. 
      In tempus elit felis, at imperdiet ligula dictum vitae. Morbi condimentum sapien metus. Morbi auctor justo sed bibendum sagittis. Cras suscipit varius tincidunt. 
        <div>
          <input class="botaoDetails" type="button" value="DETAILS" />
        </div>
      </div>
    </div>
  </body>

</html>

