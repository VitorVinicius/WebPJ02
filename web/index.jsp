<%-- 
    Document   : Postagens
    Created on : 18/05/2018, 16:51:42
    Author     : Aluno
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="progweb.projeto.Postagem"%>
<%@page import="progweb.projeto.Blog"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

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
          <label class="textoSup">Bem Vindo ao Blog <%=  (request.getSession().getAttribute("logado") == null ?"":request.getSession().getAttribute("nomeUsuario")+"!")%></label>
          <div>
            <input class="botaoGetStarted" type="button" value="GET STARTED" />
          </div>
        </div>
      </div>
    </div>
      <hr/>
      
      <% ArrayList<Postagem> postagens = Blog.getPostagens(); %>
        <%for ( Postagem post : postagens){ %>
        
        <div class="post">
     
      <div class="conteudoCab">
          <div class="img"><img class="imgPost" src='<%=  post.getImagem()%>'/></div>
        <div class="tamanho">
            <h1 class="label"><a href="./editar.jsp?id=<%=  post.getId()%>"><%=  post.getTitulo() %></a></h1>
          <label class="textoSup"><%=  post.getTexto()%></label>
          <div>
            <!--<input class="botaoGetStarted" type="button" value="GET STARTED" />-->
          </div>
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

