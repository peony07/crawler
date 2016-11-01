<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE >
<html>
<head>
    <meta charset="utf-8">
    <title>授权页面</title>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <link rel="stylesheet" href="${ctxStatic}/app/css/Jingle.css">
    <link rel="stylesheet" href="${ctxStatic}/app/css/app.css">
    <script type="text/javascript">
         window.onload=function(){
             window.login=function(){
                 var authForm= document.getElementById("authZForm");
                 authForm.submit();
             }
         }
    </script>
</head>
<body>
<div id="aside_container">
</div>
<div id="section_container">
    <section id="login_section" class="active">
        <header>
            <h1 class="title">乐家慧登录</h1>
        </header>
        <article data-scroll="true" class="active" id="login_article">
        <form id="authZForm" name="authZForm" action="${ctx}/common/oauth2/authorize" method="post" node-type="form">
	        <div class="indented">
	            	<div>&nbsp;</div>
	            	<div class="input-group">
		                <div class="input-row">
		                    <label for="username">账号</label>
		                    <input type="text" name="username" id="username" placeholder="请填写登录账号">
		                </div>
		                <div class="input-row">
		                    <label for="password">密码</label>
		                    <input type="password" name="password" id="password" placeholder="请填写登录密码">
		                </div>
		            </div>
	            	<div class="input-group" id="validateCodeDiv" style="display:none;">
		                <div class="input-row">
		                    <label class="input-label mid" for="validateCode">验证码</label>
		                    <sys:validateCode name="validateCode" inputCssStyle="margin-bottom:0;"
		                    	imageCssStyle="padding-top:7px;"/>
		                </div>
		            </div>
	            	<div>&nbsp;</div>
	            	<input type="hidden" name="mobileLogin" value="true">
	            	 <input type="hidden" name="action"  id="action" value="login"/>
                     <input type="hidden" name="redirect_uri" value="${redirect_uri}"/>
                     <input type="hidden" name="appid" value="${app_id}"/>
                     <input type="hidden" name="scope" id="scope" value="${scope}"/>
	                <button id="btn" class="submit block" onclick="return login();" data-icon="key">登录</button>
	        </div>
	        </form>
	    </article>
    </section>
</div>
</body>
</html>