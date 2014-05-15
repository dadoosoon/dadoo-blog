<%@page language="java" contentType="text/html;charset=UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %> 
<%@page import="java.util.*,im.dadoo.blog.domain.*,org.apache.commons.lang3.time.*" %>

<%
  List<Article> articles = (List<Article>)request.getAttribute("articles");
%>

<!DOCTYPE html>
<html lang="zh_cn">
<head>
  <meta name="description" content="blog">
  <jsp:include page="partial/head.jsp" flush="true">
    <jsp:param name="title" value="1" />
  </jsp:include>
</head>
<body>
  <jsp:include page="partial/header.jsp" flush="true" />
  <div class="container">
    <div class="row">
      <div class="col-md-9">
        <% if (articles != null) { %>
          <% for (Article article : articles) { %>
          <div id="article-<%= article.getId() %>" class="panel panel-default">
            <div class="panel-heading">
              <h1 class="panel-title"><a href="/article/<%= article.getId() %>"><%= article.getTitle() %></a></h1>
            </div>
            <div class="panel-body">
              <% if (article.getText().length() < 245) { %>
                <%= article.getText() %>
              <% } else { %>
                <%= article.getText().substring(0, 245) %><a href="/article/<%= article.getId() %>">...更多内容</a>
              <% } %>
            </div>
            <div class="panel-footer">
              创建日期：<%= DateFormatUtils.format(article.getPublishDatetime(), "yyyy-MM-dd HH:mm", TimeZone.getTimeZone("GMT+8")) %>&nbsp;|&nbsp;
              浏览次数：<%= article.getClick() %>&nbsp;|&nbsp;
              标签：##标签
            </div>
          </div>
          <% } %>
        <% } %>
      </div>
      <div class="col-md-3">
        <jsp:include page="partial/nav.jsp" flush="true" />
        <jsp:include page="partial/most-visit-articles.jsp" flush="true" />
      </div>
    </div>
  </div>
  <jsp:include page="partial/footer.jsp" flush="true" />
</body>
