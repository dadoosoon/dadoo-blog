<%@page language="java" contentType="text/html;charset=UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %> 
<%@page import="java.util.*,im.dadoo.blog.domain.*,org.apache.commons.lang3.time.*" %>

<%
  List<Article> articles = (List<Article>)request.getAttribute("articles");
%>

<!DOCTYPE html>
<html lang="zh_cn">
<head>
  <meta name="description" content="dadoo blog">
  <jsp:include page="../partial/head.jsp" flush="true">
    <jsp:param name="title" value="文章管理" />
  </jsp:include>
</head>
<body>
  <jsp:include page="../partial/header.jsp" flush="true" />
  <div class="container">
    <div class="row">
      <div class="col-md-3">
        <jsp:include page="partial/leftsidebar.jsp" flush="true" />
      </div>
      <div class="col-md-9">
        <a class="btn btn-primary pull-right" href="/admin/article/add">新文章</a>
        <table class="table table-hover">
          <thead>
            <tr>
              <th>名称</th>
              <th>创建时间</th>
              <th>标签</th>
              <th>浏览次数</th>
              <th>操作</th>
            </tr>
          </thead>
          <tbody>
            <% if (articles != null) { %>
              <% for (Article article : articles) { %>
              <tr>
                <td><a href="/article/<%= article.getId() %>"><%= article.getTitle() %></a></td>
                <td><%= DateFormatUtils.format(article.getPublishDatetime(), "yyyy-MM-dd HH:mm", TimeZone.getTimeZone("GMT+8")) %></td>
                <td>##标签</td>
                <td><%= article.getClick() %></td>
                <td>
                  <a href="/admin/article/<%= article.getId() %>/update">修改</a>
                  <a href="/admin/article/<%= article.getId() %>/delete">删除</a>
                </td>
              </tr>
              <% } %>
            <% } %>
          </tbody>
        </table>
      </div>
    </div>
  </div>
  <jsp:include page="../partial/footer.jsp" flush="true" />
  <script>
    $("#admin-article-li").addClass("active");
  </script>
</body>
