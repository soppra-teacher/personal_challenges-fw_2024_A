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
		�ό��n�����V�X�e���@���[�U�o�^
	</title>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/common.css" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/login.css" />
	<script type="text/javascript" charset="shift_jis" src="js/common.js" ></script>
	
</head>
<body class="bg-yellow image_chizu">

	<bean:define id="inputBean" name="userRegistForm" />
	<bean:define id="viewBean" name="USER_REGIST_DTO" />
	<bean:define id="backAction" name="USER_REGIST_BACK" type="java.lang.String" />

		<html:form action="/UserRegistIns" focus="userId" styleClass="text-center formdesign w-450px form-center" >
		<html:hidden property="operation" value="" />
		
			<div class="bg"></div>
			<div class="bg bg2"></div>
			<div class="bg bg3 "></div>
			
			<h1 class="titletext">���[�U�o�^���</h1>
			
				<html:messages id="msg" message="false">
					<p class="msg-err">
						�E<bean:write name="msg" ignore="true" filter="false"/>
					</p>
				</html:messages>
				
				<table class="layout-table">
					<tr>
						<th class="login_field">
							���[�UID
						</th>
						<td class="login_field">
							<html:text name="viewBean" property="userId" styleClass="textbox-m" tabindex="1" />
						</td>
					</tr>
					<tr>
						<th class="login_field">
							�p�X���[�h
						</th>
						<td class="login_field">
							<html:password name="viewBean" property="pass" styleClass="textbox-m" tabindex="2" />
						</td>
					</tr>
					<tr>
						<th class="login_field">
							�p�X���[�h(�m�F)
						</th>
						<td class="login_field">
							<html:password name="viewBean" property="passKakunin" styleClass="textbox-m" tabindex="2" />
							
						</td>
					</tr>
				</table>
				<table class="block-center layout-table">
				<tr>
					<td>
					<a href = "#" id="clicklink" onclick = "callAction(document.forms[0],'insertUser');" class = "btn-green btn-size-m" tabindex = "3" >
						�V�K�o�^
					</a>
					</td>
				</tr>
				<tr>
					<td>
						<html:link action="/Logout" styleClass= "btn-blue btn-size-s" > ���O�C����ʂ֖߂� </html:link>
					</td>
					
				</tr>
				</table>

		</html:form>
	</div>
	
	<script type="text/javascript">
		window.onload = function OnLinkClick(){
			var click = document.getElementById('clicklink');
			var msg;

			click.addEventListener('click', () => {
			  msg = "�V�K�o�^���Ă���낵���ł����H"
			  confirm(msg);
			});
			
			//�L�����Z���{�^�����������ꍇ�����𒆒f
			if (!confirm(msg)) {
				return;
			}
			//OK�{�^�����������ꍇ
			else{
				//�o�^���������������ɋL�ڂ���B
				msg = "�V�K�o�^���������܂����B���O�C����ʂֈڍs���܂��B"
				confirm(msg);
			}
			
		}
	</script>
</body>
</html:html>