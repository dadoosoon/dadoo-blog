<%@page language="java" contentType="text/html;charset=UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %> 
<%@page import="java.util.*,im.dadoo.blog.domain.*,org.apache.commons.lang3.time.*" %>

<%
  List<Article> articles = (List<Article>)request.getAttribute("mostVisitArticles");
%>
<div class="panel panel-default">
  <div class="panel-heading">
    <h3 class="panel-title">访问最多的文章</h3>
  </div>
  <div class="panel-body">
    <ul>
      <% if (articles != null) { %>
        <% for (Article article : articles) { %>
          <li><span class="badge"><%= article.getClick() %></span><%= article.getTitle() %></li>
        <% } %>
      <% } %>
    </ul>
  </div>
</div>