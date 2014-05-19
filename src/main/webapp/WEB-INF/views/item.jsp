<%@page language="java" contentType="text/html;charset=UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %> 
<%@page import="java.util.*,im.dadoo.blog.domain.*,org.apache.commons.lang3.time.*,org.apache.commons.lang3.tuple.*" %>

<%
  Pair<Article, List<Tag>> pair = (Pair<Article, List<Tag>>)request.getAttribute("pair");
%>

<!DOCTYPE html>
<html lang="zh_cn">
<head>
  <meta name="description" content="blog">
  <jsp:include page="partial/head.jsp" flush="true">
    <jsp:param name="title" value="<%= pair.getLeft().getTitle() %>" />
  </jsp:include>
</head>
<body>
  <jsp:include page="partial/header.jsp" flush="true" />
  <div class="container">
    <div class="row">
      <div class="col-md-9">
        <% if (pair != null) { %>
          <div id="article-<%= pair.getLeft().getId() %>" class="panel panel-default">
            <div class="panel-heading">
              <h1 class="panel-title"><%= pair.getLeft().getTitle() %></h1>
            </div>
            <div class="panel-body"><%= pair.getLeft().getHtml() %></div>
            <div class="panel-footer">
              创建日期：<%= DateFormatUtils.format(pair.getLeft().getPublishDatetime(), "yyyy-MM-dd HH:mm", TimeZone.getTimeZone("GMT+8")) %>&nbsp;|&nbsp;
              浏览次数：<%= pair.getLeft().getClick() %>&nbsp;|&nbsp;
              标签：
              <% if (pair.getRight() != null && !pair.getRight().isEmpty()) { %>
                <% for (Tag tag : pair.getRight()) { %>
                  &nbsp;<a href="/tag/<%= tag.getId() %>"><%= tag.getName() %></a>
                <% } %>
              <% } %>
            </div>
          </div>
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
