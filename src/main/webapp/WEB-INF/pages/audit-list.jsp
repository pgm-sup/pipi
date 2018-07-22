<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <title>用户管理-审计列表</title>

    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/static/css/layout.css" rel="stylesheet">
  </head>

  <body>

    <!-- 头部 -->
    <jsp:include page="header.jsp"/>

    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
          <jsp:include page="navibar.jsp"/>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h1 class="page-header">审计列表</h1>

          <div class="row placeholders">

            <div class="space-div"></div>
              <table class="table table-hover table-bordered perm-list">
                    <tr>
                        <td>ID</td>
                        <td>用户名</td>
                        <td>操作模块</td>
                        <td>具体操作</td>
                        <td>响应时长</td>
                        <td>用户IP</td>
                        <td>操作时间</td>
                        <td>操作结果</td>
                    </tr>
                    <c:forEach items="${logs }" var="log">
	                    <tr>
	                        <td class="logid">${log.id }</td>
	                        <td>${log.username }</td>
	                        <td>${log.module }</td>
	                        <td>${log.method }</td>
	                        <td>${log.response_data }</td>
	                        <td>${log.ip }</td>
	                        <td>${log.data }</td>
	                        <td>${log.comment }</td>
	                    </tr>
                    </c:forEach>
              </table>
          </div>          
        </div>
      </div>
    </div>
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
  </body>
</html>
