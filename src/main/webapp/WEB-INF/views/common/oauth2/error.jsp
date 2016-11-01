<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>网页授权</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
</head>
<style type="text/css">
	body{background-color: #e1e0de;}
</style>
<body>
	<div style="margin-top: 80px;">
		<div style="text-align: center;">
			<img src="${ctxStatic}/img/icon_lejia.png">
		</div>
		<div style="text-align: center;width: 100%;line-height: 80px;">	
			${errorMsg}
		</div>
	</div>	
</body>
<html>