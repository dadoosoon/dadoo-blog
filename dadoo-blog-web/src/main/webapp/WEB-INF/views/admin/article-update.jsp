<%@page language="java" contentType="text/html;charset=UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %> 
<%@page import="java.util.*,im.dadoo.blog.domain.*,org.apache.commons.lang3.time.*,org.apache.commons.lang3.tuple.*" %>

<%
  Pair<Article, List<Tag>> pair = (Pair<Article, List<Tag>>)request.getAttribute("article-tags-pair");
  List<Tag> tags = (List<Tag>)request.getAttribute("tags");
%>
<!DOCTYPE html>
<html lang="zh_cn">
<head>
  <meta name="description" content="dadoo blog">
  <jsp:include page="../partial/head.jsp" flush="true">
    <jsp:param name="title" value="修改文章" />
  </jsp:include>
  <script src="http://cdn.bootcss.com/ckeditor/4.3.2/ckeditor.js"></script>
</head>
<body>
  <jsp:include page="../partial/header.jsp" flush="true" />
  <div class="container">
    <div class="row">
      <div class="col-md-3">
        <jsp:include page="partial/leftsidebar.jsp" flush="true" />
      </div>
      <div class="col-md-9">
        <form id="update-article-form" enctype="multipart/form-data" action="/admin/article/<%= pair.getLeft().getId() %>/update" method="post">
          <div class="form-group">
            <label for="title">标题</label>
            <input name="title" type="text" class="form-control" value="<%= pair.getLeft().getTitle() %>">
          </div>
          <div class="form-group">
            <label for="tagIds">标签</label>
            <select id="tagIds" name="tagIds" multiple class="form-control">
              <% if (tags != null) { %>
                <% for (Tag tag : tags) { %>
                  <% Boolean flag = false; %>
                  <% for (Tag t : pair.getRight()) { %>
                    <% if (t.getId().equals(tag.getId())) { %>
                      <option selected="selected" value="<%= tag.getId() %>"><%= tag.getName() %></option>
                      <% flag = true; %>
                    <% } %>
                  <% } %>
                  <% if (!flag) { %>
                    <option value="<%= tag.getId() %>"><%= tag.getName() %></option>
                  <% } %>
                <% } %>
              <% } %>
            </select>
          </div>
          <div class="form-group">
            <label for="html">内容</label>
            <textarea id="html" name="html" class="form-control" rows="15"><%= pair.getLeft().getHtml() %></textarea>
          </div>
          <div class="form-group">
            <button type="submit" class="btn btn-default">保存</button>
          </div>
        </form>
      </div>
    </div>
  </div>
  <jsp:include page="../partial/footer.jsp" flush="true" />
  <script>
    $("#admin-article-li").addClass("active");
    CKEDITOR.replace("html",{
      uiColor:"#9AB8F3",
      filebrowserUploadUrl: "/api/upload"
    });
  </script>
</body>
