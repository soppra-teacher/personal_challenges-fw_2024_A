<%@ page pageEncoding="Windows-31J" %>
<%@ page contentType="text/html;charset=Windows-31J" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<!DOCTYPE html>
<html:html>
<head>

	<meta content="ja" http-equiv="Content-Language" />
	<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
	<title>
		観光地検索システム　ログイン
	</title>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/common.css" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/login.css" />
	<script type="text/javascript" charset="shift_jis" src="js/common.js" ></script>

</head>
<body onload="document.forms[0].elements[0].focus();" class="bg-yellow">

		<html:form action="/Login?getKey=aaa" focus="userId" styleClass="text-center formdesign w-400px form-center" >

			<div class="bg"></div>
			<div class="bg bg2"></div>
			<div class="bg bg3 "></div>
			
			<h1 class="titletext">観光地検索システム</h1>
			
				<html:messages id="msg" message="false">
					<p class="msg-err">
						・<bean:write name="msg" ignore="true" filter="false"/>
					</p>
				</html:messages>
				
				<table class="layout-table">
					<tr>
						<th class="login_field">
							ユーザID
						</th>
						<td class="login_field">
							<html:text property="userId" styleClass="textbox-m" tabindex="1" />
						</td>
					</tr>
					<tr>
						<th class="login_field">
							パスワード
						</th>
						<td class="login_field">
							<html:password property="pass" styleClass="textbox-m" tabindex="2" />
						</td>
					</tr>
				</table>
				<table class="block-center layout-table">
				<tr>
					<td>
						<a href = "#" onclick = "document.forms[0].submit();" class = "btn-green btn-size-m" tabindex = "3" > ログイン </a>
					</td>
				</tr>
				<tr>
					<td>
						<html:link action="/UserRegistInit" styleClass= "btn-green btn-size-m">新規登録</html:link>
					</td>
				</tr>
				</table>

		</html:form>
	</div>
</body>
</html:html>