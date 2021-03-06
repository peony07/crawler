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
    <style type="text/css">
    .astyle {
	    display: inline-block;
	    padding: 8px 14px;
	    border: none;
	    cursor: pointer;
	    text-align: center;
	    text-decoration: none;
	    outline: none;
	    border-radius: 4px;
	    color: #fff;
	    background-color: #46CA5E;
	    width: 100%;
	}
    </style>
    <script type="text/javascript">
	    function login(){
	        window.location.href="ljh://user_token/on_user_token_callback";
	    }
	    function on_user_token_callback(response){
	    	//alert("用户token："+response);
		 	document.getElementById('token').value = response;
	        var authForm= document.getElementById("authZForm");
	        authForm.submit();
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
				<label class="input-label mid" for="validateCode">获得你的公开信息（昵称、头像等）</label>
				<div>&nbsp;</div>
                    <input type="hidden" name="action"  id="action" value="authorize"/>
                    <input type="hidden" name="redirect_uri" value="${redirect_uri}"/>
                    <input type="hidden" name="appid" value="${app_id}"/>
                    <input type="hidden" name="scope" id="scope" value="${scope}"/>
                    <input type="hidden" name="token" id="token" value=""/>
                	<a id="btn" class="astyle" onclick="return login();" data-icon="key">确认登录</a>
	        </div>
	        </form>
	    </article>
    </section>
</div>
</body>
</html>