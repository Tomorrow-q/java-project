
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.hyc.www.controller.constant.Methods" %>
<%@ page import="com.hyc.www.controller.constant.Pages" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>${param.title}</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet"
          href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<%-- 页面头部--%>
<div class="login-head" style="height: 100px">
    <div class="jumbotron" style="padding-bottom: 20px;padding-top:20px;margin:0px">
        <a href="${pageContext.request.contextPath}/${Pages.INDEX_JSP.toString()}"><h2 style="text-align: left">Xhotel酒店预订</h2></a>
    </div>
</div>
<c:if test="${message!=null}">
    <div class="alert alert-warning alert-dismissable" style="margin-bottom: 0">
        <button type="button" class="close" data-dismiss="alert"
                aria-hidden="true">
            &times;
        </button>
        提示：${message}
    </div>
</c:if>


<div class="login-body" style="overflow: hidden;position: absolute">
    <img src="${pageContext.request.contextPath}/bg.jpg" width="1920" height="674">
</div>

<div class="login-body">
    <div class="login-layout">
        <div class="login-box">
            <div class="panel panel-default" style="padding: 20px;font-style: inherit">
                <strong>
                    <h2 class="panel-title" style="text-align: center">用户名登陆</h2>
                </strong>
                <div class="panel-body">
                    <div class="color-input-field">
                        <form name="login" action="${pageContext.request.contextPath}/user?method=${Methods.LOGIN_DO}" method="post">
                            <c:if test="${data!=null}">
                                <input type="text" required="required" class="form-control" name="name" value="${data.users[0].name}" placeholder="请输入用户名" align="center">
                            </c:if>
                            <c:if test="${data==null}">
                                <input type="text" required="required" class="form-control" name="name" placeholder="请输入用户名" align="center">
                            </c:if>
                            <br>
                            <input type="password" required="required" class="form-control" name="password" placeholder="请输入密码"
                                   align="center">
                            <input type="checkbox" name="auto_login" value="true" style="margin-bottom: 13px">记住登陆
                            <input type="submit" class="form-control" value="登陆"
                                   style="background-color: darkorchid;color: #FFFFFF">
                        </form>
                        <br>
                        <a href="${Pages.REGIST_JSP.toString()}">立即注册</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<style type="text/css">

    .form-control {
        padding: 10px;
        height: 42px
    }


    .login-body {
        width: 100%;
        height: 800px;
    }

    .login-layout {
        position: relative;
        float: right;
        width: 80%;
        min-height: 540px;
        max-width: 450px;
        margin-right: 72px;
        margin-top: 40px;
        margin-left: 72px;
    }

    .login-box {
        position: relative;
        left: 0;
        right: 0;
        top: 50%;
        background: #fff;
        padding: 20px 20px 20px 20px;
        min-height: 300px;
        border-radius: 4px;
        -webkit-box-shadow: 0 1px 6px rgba(0, 0, 0, .1);
        box-shadow: 0 1px 6px rgba(0, 0, 0, .1);

    }


</style>
</html>
