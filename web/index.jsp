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
    
    String busca = request.getParameter("busca");


%>


<!DOCTYPE html>

<html>

  <head>
        <link rel="stylesheet" href="content/estilo.css" type="text/css"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script type="text/javascript" src="https://rawgithub.com/janl/mustache.js/master/mustache.js"></script>
        
        <title>Web2</title>
  </head>

  <body onload="carregarPosts(<%=busca!=null?"'"+busca+"'":""%>)">
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
          <form action="index.jsp">
               <p><label for="nome">Pesquisar: </label>
                <input type = "search" name = "busca" id ="busca"></p>
                <input class="botaoGetStarted" type="submit"  value = "Buscar">
              
          </form>
          <div>
              <%=  (logado ==null?"":"<a href=\"postar.jsp\"  ><span class=\"botaoAcao\">POSTAR</span></a>")%>
              <%=  (logado ==null?"":"<a href=\"Logout\"  ><span class=\"botaoAcao\">SAIR</span></a>")%>
              <%=  (logado ==null?"<a href=\"login.html\" ><span class=\"botaoAcao\">LOGIN</span></a>":"")%>
              <%=  (logado ==null?"<a href=\"cadastro.html\" ><span class=\"botaoAcao\">CADASTRO</span></a>":"")%>
              
          </div>
        </div>
      </div>
    </div>
      <hr/>
      <div id="content">
          
          
          
          
          
          
      </div>
      
      <script type="text/javascript">
          
          var currentTimestamp= "";
          var template = '<div>{{#postagens}}<div class=\"post\"><div class=\"conteudoCab\"><div class=\"img\"><img class=\"imgPost\" src=\'{{imagem}}\'\/><\/div><div class=\"tamanho\"><h1 class=\"label\">{{titulo}}<\/h1><label class=\"textoSup\">{{texto}}<\/label><div id=\"quadroCurtidasPost{{id}}\"><p>Curtidas: <label id=\"curtidasPost{{id}}\">{{curtidas}}<\/label><\/p><a class="btnCurtir" id=\"aCurtir\" onclick=\"curtir({{id}})\">Curtir<\/a><\/div><\/div><\/div><\/div>{{/postagens}}</div>';
          
          function carregarPosts(busca){
              
              $("#content").addClass("loader");
              $.get( 'API/Posts?busca='+((typeof busca === 'undefined' || busca === null)?'':busca), function( data ) {
                
                var d = new Date(); 
               currentTimestamp = d.toLocaleString(); 
                
                $("#content").removeClass("loader");
                
                
                var result = Mustache.render(template, data);

                document.getElementById('content').innerHTML = result;
                
                
                //setInterval(carregarNovosPosts, 3000);
                
                
              });
              
              
          }
          
          var carregando = false;
          function carregarNovosPosts(){
              if(carregando) return;
              carregando = true;
              $.get( "API/Posts?timestampDe="+currentTimestamp , function( data ) {
                
               
                
                //var data = {"postagens":[{"nomeUsuario":"vitor.v.gomes@live.com","titulo":"Teste","imagem":"20180315_210143.jpg","texto":"Um texto qualquer aqui","video":"mov_bbb.mp4","curtidas":0,"timestamp":"Jun 24, 2018 2:46:40 PM","id":2},{"nomeUsuario":"vitor.v.gomes@live.com","titulo":"Teste 2","imagem":"Tulips.jpg","texto":"Coala","video":"","curtidas":0,"timestamp":"Jun 24, 2018 2:46:40 PM","id":3},{"nomeUsuario":"Felipe","titulo":"nome 3","imagem":"Penguins.jpg","texto":"nome","video":"","curtidas":0,"timestamp":"Jun 24, 2018 2:46:40 PM","id":4},{"nomeUsuario":"w","titulo":"w","imagem":"509.png","texto":"w","video":"","curtidas":0,"timestamp":"Jun 24, 2018 2:46:40 PM","id":5}]};
                if(data.postagens.length>0){
                    var result = Mustache.render(template, data);
                    var cont = document.getElementById('content');
                    cont.innerHTML = result + cont.innerHTML;
                    var d = new Date(); 
                    currentTimestamp = d.toLocaleString(); 
                   
                }
                  carregando = false;
                
              });
              
              
          }
          
          
          function curtir(idPostagem){
                var actionUrl = '/PostagensAPI/Curtir';//alterar para a url da API do servidor
                
                $.post(actionUrl, {idPostagem: idPostagem}
                    , function(receivedData, textStatus, jqXHR) {

                        
                        if(textStatus == 'success'){
                                $('#quadroCurtidasPost'+idPostagem).html('<p>Curtidas: <label id=\"curtidasPost'+idPostagem+'\">'+receivedData.curtidas+'<\/label><\/p>');
                        }
                    });
                    
                     var curtidaAtual = $('#curtidasPost'+idPostagem).html();
                     $('#quadroCurtidasPost'+idPostagem).html('<p>Curtidas: <label id=\"curtidasPost'+idPostagem+'\">'+(parseInt(curtidaAtual)+1)+'<\/label><\/p>');
                     
}
              
          
      </script>
        
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

