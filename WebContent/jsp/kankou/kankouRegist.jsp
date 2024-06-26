<%@ page pageEncoding="Windows-31J"%>
<%@ page contentType="text/html;charset=Windows-31J"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ page import="cashbook.util.Const"%>

<!DOCTYPE html>
<html:html lang="ja">
<head>

<meta content="ja" http-equiv="Content-Language" />
<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
<title>�ό��n�o�^���</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/common.css" />
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/kankouRegist.css" />
<script language="JavaScript" type="text/javascript" charset="shift_jis" src="js/common.js"></script>

<script type="text/javascript"></script>

</head>

<body>

	<bean:define id="inputBean" name="kankouRegistForm" />
	<bean:define id="viewBean" name="KANKOU_REGIST_DTO" />
	<bean:define id="backAction" name="KANKOU_REGIST_BACK"
		type="java.lang.String" />

	<div class="base-width text-center">


		<html:form action="/KankouRegistIns" >

		<html:form action="/KankouRegistIns" styleClass="text-center formdesign w-400px form-center"  >
		<div class="bg"></div>
		<div class="bg bg2"></div>
		<div class="bg bg3 "></div>

			<!-- �w�b�_�[�̖��̂������œ����Ă��� -->
			<jsp:include page="/jsp/common/header.jsp">
				<jsp:param name="screenTitle" value="�ό��n�o�^���"/>
			</jsp:include>

			<html:hidden property="operation" value="" />

			<!-- �o�^�s���Ȃǂ̎��Ƀ��b�Z�[�W�𓊂���ꏊ -->
			<div id="contents">
				<html:messages id="msg" message="true">
					<p class="msg-info">
						�E
						<bean:write name="msg" ignore="true" filter="false" />
					</p>
				</html:messages>

				<html:messages id="msg" message="false">
					<p class="msg-err">
						�E
						<bean:write name="msg" ignore="true" filter="false" />
					</p>
				</html:messages>

				<div>
					<table class="layout-table top-10">
						<tr>
							<td class="w-20 text-right"><span class="label-title">
									�s���{�� </span></td>

							<td class="w-75 text-left"><html:select name="inputBean"
									property="todouhukenKey" styleClass="textbox-s margin-15">
									<html:optionsCollection name="viewBean" property="todouhuken"
										value="key" label="value" />
								</html:select></td>
						</tr>

						<tr>
							<td class="w-20 text-right"><span class="label-title">
									�J�e�S�� </span></td>

							<td class="w-75 text-left"><html:select name="inputBean"
									property="categoryKey" styleClass="textbox-s margin-15">
									<html:optionsCollection name="viewBean" property="category"
										value="key" label="value" />
								</html:select></td>
						</tr>

						<tr>
							<td class="w-20 text-right"><span class="label-title">
									�ό��n�� </span></td>

							<td class="w-75 text-left"><html:text name="inputBean"
									property="kankouNm" styleClass="textbox-m margin-15" /></td>
						</tr>

						<tr>
							<td class="w-20 text-right"><span class="label-title">
									���� </span></td>
							<td class="w-75 text-left"><html:textarea name="inputBean"
									property="setsumei" styleClass="textbox-l margin-15" /></td>
						</tr>

						<tr>
							<td class="w-20 text-right"><span class="label-title">
									���r���[ </span></td>

							<td class="w-75 text-left"><html:textarea name="inputBean"
									property="review" styleClass="textbox-xl margin-15" /></td>
						</tr>

						<tr>
							<td class="w-20 text-right"><span class="label-title">
									�]���l </span></td>

							<td class="w-75 text-left"><html:select name="inputBean"
									property="hyoka" styleClass="textbox-mini margin-15">
									<html:option value="1"></html:option>
									<html:option value="2"></html:option>
									<html:option value="3"></html:option>
									<html:option value="4"></html:option>
									<html:option value="5"></html:option>
								</html:select></td>
						</tr>

						<tr>
							<td class="w-20 text-right top-10"><span class="label-title">
									�ʐ^ </span></td>
							<td class="w-75 text-left top-10">
								<!-- �A�b�v�������摜�������Ŏw�� -->
								 <input type="file" id="pictures" accept=".png, .jpg, .jpeg"> 
								<!--<html:file  name="inputBean"  property="pictures" accept=".png, .jpg, .jpeg"/>   -->
							</td>
						</tr>
						<tr>
							<td class="w-20 text-right margin-15"><span class="label-title">
							</span></td>
							<td class="w-75 text-left">
								<div class="images">
									<!-- �A�b�v�����ʐ^��\������ꏊ -->
									<div id="preview" class="imt-item"></div>
								</div>
							</td>
						</tr>
					</table>
				</div>

				<div class="block-center layout-table">
					<a href = "#" id = "clicklink" class = "btn-green btn-size-m" tabindex = "3" >
		              �o�^
		            </a>
					<jsp:include page="/jsp/common/footer.jsp" />
				</div>
		</html:form>
		<!-- �I�������ʐ^��\��������X�N���v�g -->
		<script src="js/picture.js"></script>
		
		<!-- �o�^�����N�̉�ʑJ�ڗpURL -->
		<script type="text/javascript">
		    document.getElementById('clicklink').addEventListener('click', function (event) {
		        var msg = "�o�^���Ă���낵���ł����H";
		        if (!confirm(msg)) {
		        	console.log("�L�����Z��");
		        	return;
		        } else {
		        	console.log("OK");
		        	document.forms[0].submit();
		        	
		        }
		    });
		</script>
		
	</div>
</body>
</html:html>