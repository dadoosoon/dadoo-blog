<%@page language="java" contentType="text/html;charset=UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %> 
<%@page import="java.util.*,im.dadoo.blog.domain.*,org.apache.commons.lang3.time.*,org.apache.commons.lang3.tuple.*" %>

<%
  List<Pair<Article, List<Tag>>> pairs = (List<Pair<Article, List<Tag>>>)request.getAttribute("pairs");
  Tag curTag = (Tag)request.getAttribute("tag");
%>

<!DOCTYPE html>
<html lang="zh_cn">
<head>
  <meta name="description" content="blog">
  <% if (curTag != null) { %>
    <title><%= curTag.getName() %> &nbsp; | &nbsp; Dadoo Blog</title>
  <% } else { %>
    <title>主页 &nbsp; | &nbsp; Dadoo Blog</title>
  <% } %>
  <jsp:include page="partial/head.jsp" flush="true" />
</head>
<body>
  <jsp:include page="partial/header.jsp" flush="true" />
  <div class="container">
    <div class="row">
      <div class="col-md-9">
        <% if (curTag != null) { %>
          <h4><span class="label label-success"><%= curTag.getName() %></span></h4>
        <% } %>
        <% if (pairs != null) { %>
          <% for (Pair<Article, List<Tag>> pair : pairs) { %>
          <div id="article-<%= pair.getLeft().getId() %>" class="panel panel-default">
            <div class="panel-heading">
              <h1 class="panel-title"><a href="/article/<%= pair.getLeft().getId() %>"><%= pair.getLeft().getTitle() %></a></h1>
            </div>
            <div class="panel-body">
              <% if (pair.getLeft().getText().length() < 245) { %>
                <%= pair.getLeft().getText() %>
                <div class="read-more"><a class="btn btn-default btn-xs" href="/article/<%= pair.getLeft().getId() %>">Read More</a></div>
              <% } else { %>
                <%= pair.getLeft().getText().substring(0, 245) %>
                <div class="read-more"><a class="btn btn-default btn-xs" href="/article/<%= pair.getLeft().getId() %>">Read More</a></div>
              <% } %>
            </div>
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
        <% } %>
        <jsp:include page="partial/pagination.jsp" flush="true" />
      </div>
      <div class="col-md-3">
        <jsp:include page="partial/nav.jsp" flush="true" />
        <jsp:include page="partial/most-visit-articles.jsp" flush="true" />
        <jsp:include page="partial/tag-well.jsp" flush="true" />
        <jsp:include page="partial/link-well.jsp" flush="true" />
      </div>
    </div>
  </div>
  <jsp:include page="partial/footer.jsp" flush="true" />
</body>
