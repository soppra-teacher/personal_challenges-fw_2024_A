<%@ page pageEncoding="Windows-31J" %>
<%@ page contentType="text/html;charset=Windows-31J" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ page import="cashbook.util.Const"%>

<!DOCTYPE html>
<html:html>
<head>


	<meta content="ja" http-equiv="Content-Language" />
	<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
	<title>
		観光地検索システム　ユーザ登録
	</title>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/common.css" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/login.css" />
	<script type="text/javascript" charset="shift_jis" src="js/common.js" ></script>
</head>
<body class="image_chizu">

	<bean:define id="inputBean" name="userRegistForm" />
	<bean:define id="viewBean" name="USER_REGIST_DTO" />
	<bean:define id="backAction" name="USER_REGIST_BACK" type="java.lang.String" />

		<html:form action="/UserRegistIns" focus="userId" styleClass="text-center formdesign w-450px form-center" >
		<html:hidden property="operation" value="" />
		<html:hidden name="viewBean" property="hidden" />
		
			<div class="bg"></div>
			<div class="bg bg2"></div>
			<div class="bg bg3 "></div>
			
			<h1 class="titletext">ユーザ登録画面</h1>
			
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
							<html:text name="viewBean" property="userId" styleClass="textbox-m" tabindex="1" />
						</td>
					</tr>
					<tr>
						<th class="login_field">
							パスワード
						</th>
						<td class="login_field">
							<html:password name="viewBean" property="pass" styleClass="textbox-m" tabindex="2" />
						</td>
					</tr>
					<tr>
						<th class="login_field">
							パスワード(確認)
						</th>
						<td class="login_field">
							<html:password name="viewBean" property="passKakunin" styleClass="textbox-m" tabindex="2" />
							
						</td>
					</tr>
				</table>
				<table class="block-center layout-table">
				<tr>
					<td>
						<a href = "#" id="clicklink" class = "btn-green btn-size-m" tabindex = "3" >
						新規登録
						</a>
					</td>
				</tr>
				<tr>
					<td>
						<html:link action="/Logout" styleClass= "btn-blue btn-size-s" > ログイン画面へ戻る </html:link>
					</td>
					
				</tr>
				</table>

		</html:form>
	</div>
	

	<script type="text/javascript">
    document.getElementById('clicklink').addEventListener('click', function (event) {
        var msg = "新規登録してもよろしいですか？";
        if (!confirm(msg)) {
        	console.log("キャンセル");
        	return;
        } else {
        	document.forms[0].submit(); 
        }
    });
	</script>
	
</body>

</html:html>
<script>
	window.addEventListener("load",(event) => {
		var hidden = document.userRegistForm.hidden;
	     if(hidden.defaultValue == "1"){
		    //hidden値1：OK
	    	alert("登録処理が完了しました。ログイン画面へ移行します。");
			document.forms[0].submit(); 
			window.location.href = "http://localhost:8080/Cashbook/"
	    }
	    else {
	    	return;
		    
	    }
	    
	});
</script>