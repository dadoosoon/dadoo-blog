<%@page language="java" contentType="text/html;charset=UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %> 
<%@page import="java.util.*,im.dadoo.blog.domain.*,org.apache.commons.lang3.time.*,org.apache.commons.lang3.tuple.*" %>

<%
  Pair<Article, List<Tag>> pair = (Pair<Article, List<Tag>>)request.getAttribute("pair");
  Pair<Article, Article> pn = (Pair<Article, Article>)request.getAttribute("prev-next");
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
              <h6 class="panel-meta">
                <span class="glyphicon glyphicon-calendar"></span><span class="meta-content"><%= DateFormatUtils.format(pair.getLeft().getPublishDatetime(), "yyyy-MM-dd HH:mm", TimeZone.getTimeZone("GMT+8")) %></span>
                <span class="glyphicon glyphicon-eye-open"></span><span class="meta-content"><%= pair.getLeft().getClick() %>次点击</span>
                <span class="glyphicon glyphicon-comment"></span><span class="meta-content"><a href="/article/<%= pair.getLeft().getId() %>#disqus_thread"></a>条评论</span>
                <span class="glyphicon glyphicon-folder-open"></span>
                <span class="meta-content">
                  <% if (pair.getRight() != null && !pair.getRight().isEmpty()) { %>
                    <% for (Tag tag : pair.getRight()) { %>
                      &nbsp;<a href="/tag/<%= tag.getId() %>"><%= tag.getName() %></a>
                    <% } %>
                  <% } %>
                </span>
              </h6>
            </div>
            <div class="panel-body"><%= pair.getLeft().getHtml() %></div>
          </div>
          <div>
            <% if (pn != null) { %>
            <ul class="pager">
              <% if (pn.getLeft() != null) { %>
                <li class="pull-left"><a href="/article/<%= pn.getLeft().getId() %>">Prev:<%= pn.getLeft().getTitle() %></a></li>
              <% } %>
              <% if (pn.getRight() != null) { %>
                <li class="pull-right"><a href="/article/<%= pn.getRight().getId() %>">Next:<%= pn.getRight().getTitle() %></a></li>
              <% } %>
            </ul>
            <% } %>
          </div>
          <div id="disqus_thread"></div>
          <script type="text/javascript">
              /* * * CONFIGURATION VARIABLES: EDIT BEFORE PASTING INTO YOUR WEBPAGE * * */
              var disqus_shortname = 'dadoo-blog'; // required: replace example with your forum shortname

              /* * * DON'T EDIT BELOW THIS LINE * * */
              (function() {
                  var dsq = document.createElement('script'); dsq.type = 'text/javascript'; dsq.async = true;
                  dsq.src = '//' + disqus_shortname + '.disqus.com/embed.js';
                  (document.getElementsByTagName('head')[0] || document.getElementsByTagName('body')[0]).appendChild(dsq);
              })();
          </script>
          <noscript>Please enable JavaScript to view the <a href="http://disqus.com/?ref_noscript">comments powered by Disqus.</a></noscript>
          <a href="http://disqus.com" class="dsq-brlink">comments powered by <span class="logo-disqus">Disqus</span></a>
        <% } %>
      </div>
      <div class="col-md-3">
        <jsp:include page="partial/nav.jsp" flush="true" />
        <jsp:include page="partial/most-visit-articles.jsp" flush="true" />
        <jsp:include page="partial/tag-well.jsp" flush="true" />
      </div>
    </div>
  </div>
  <jsp:include page="partial/footer.jsp" flush="true" />
  <script type="text/javascript">
  /* * * CONFIGURATION VARIABLES: EDIT BEFORE PASTING INTO YOUR WEBPAGE * * */
  var disqus_shortname = 'dadoo-blog'; // required: replace example with your forum shortname

  /* * * DON'T EDIT BELOW THIS LINE * * */
  (function () {
      var s = document.createElement('script'); s.async = true;
      s.type = 'text/javascript';
      s.src = '//' + disqus_shortname + '.disqus.com/count.js';
      (document.getElementsByTagName('HEAD')[0] || document.getElementsByTagName('BODY')[0]).appendChild(s);
  }());
  </script>
</body>
