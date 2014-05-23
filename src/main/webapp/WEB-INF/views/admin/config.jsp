<%@page language="java" contentType="text/html;charset=UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %> 
<%@page import="java.util.*,im.dadoo.blog.domain.*,org.apache.commons.lang3.time.*" %>

<!DOCTYPE html>
<html lang="zh_cn">
<head>
  <meta name="description" content="dadoo blog">
  <title>配置管理 &nbsp; | &nbsp; Dadoo Blog</title>
  <jsp:include page="../partial/head.jsp" flush="true" />
</head>
<body>
  <jsp:include page="../partial/header.jsp" flush="true" />
  <div class="container">
    <div class="row">
      <div class="col-md-3">
        <jsp:include page="partial/leftsidebar.jsp" flush="true" />
      </div>
      <div class="col-md-9">
        <form id="config-form" action="/admin/config" method="post">
          <div class="form-group">
            <label for="title">标题</label>
            <input name="title" type="text" class="form-control" value="<%= request.getAttribute("title") %>">
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
    $("#admin-config-li").addClass("active");
  </script>
</body>
