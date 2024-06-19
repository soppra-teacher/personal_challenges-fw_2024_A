<%@ page pageEncoding="Windows-31J" %>
<%@ page contentType="text/html;charset=Windows-31J" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ page import="cashbook.util.Const"%>

<!DOCTYPE html>
<html:html lang="ja">
<head>

	<meta content="ja" http-equiv="Content-Language" />
	<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
	<title>
		�ό��n�����V�X�e���E�����ꗗ���
	</title>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/common.css" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/KankouList.css" />
	<script type="text/javascript" charset="shift_jis" src="js/common.js" ></script>
</head>
<script>

</script>

<body onload="setDeleteButton();">

	<bean:define id="inputBean" name="kankouListForm" />
	<bean:define id="viewBean" name="KANKOU_LIST_DTO" />

	<div class="kankouList-width text-center">

		<html:form action="/KojinListDisp" focus="kojinNm">

			<html:hidden property="operation" value="" />
			<html:hidden property="hiddenKankouNm" value="" />
			<html:hidden property="hiddenUserId" value="" />
			<html:hidden property="hiddenTihouKey" value="" />
			<html:hidden property="hiddenCategoryKey" value="" />
			<html:hidden property="hiddenTodouhukenKey" value="" />
			
	

			<div class="contents block-center test-a">
			<jsp:include page="/jsp/common/header.jsp">
				<jsp:param name="screenTitle" value="�����ꗗ���"/>
			</jsp:include>

				<html:messages id="msg" message="true">
					<p class="msg-info">
						�E<bean:write name="msg" ignore="true" filter="false"/>
					</p>
				</html:messages>

				<html:messages id="msg" message="false">
					<p class="msg-err">
						�E<bean:write name="msg" ignore="true" filter="false"/>
					</p>
				</html:messages>

				<table class="layout-table">
					<tr>
						<td colspan="1" class="w-50 text-left">
							<span class="label-title">
								�ό��n��
							</span>
							<html:text name="inputBean" property="kankouNm" styleClass="input-text-s" />
						</td>
						<td colspan="2" class="w-50 text-left">
							<span class="label-title">
								���[�UID
							</span>
							<html:text name="inputBean" property="userId" styleClass="input-text-m" />
						</td>
					</tr>
					<tr>
						<td class="w-25 text-left">
							<span class="label-title">
								�J�e�S����
							</span>
							<html:select name="inputBean" property="categoryKey" styleClass="input-select-xl">
								<html:optionsCollection name="viewBean" property="category" value="key" label="value" />
							</html:select>
						</td>
						<td class="w-25 text-left">
							<span class="label-title">
								���n����
							</span>
							<html:select name="inputBean" property="tihouKey" styleClass="input-select-xl">
								<html:optionsCollection name="viewBean" property="tihou" value="key" label="value" />
							</html:select>
						</td>
						<td class="w-25 text-left">
							<span class="label-title">
								�s������
							</span>
							<html:select name="inputBean" property="todouhukenKey" styleClass="input-select-xl" >
								<html:optionsCollection name="viewBean" property="todouhuken" value="key" label="value" />
							</html:select>
						</td>
						
					</tr>
				</table>
				
		
						
						<td class="w-25">
							<html:button property="search" onclick="callAction(this.form, 'search');"
								styleClass="btn btn-l">
								����
							</html:button>
							
						</td>
					</tr>
				</table>

				<table class="l-kankou table mb-0" >
					<tr class="table-header">
						<td class="text-center l-kankou-id ">�ό��nID</td>
						<td class="text-center l-kankou-nm">�ό��n��</td>
						<td class="text-center l-tihou-nm">���n����</td>
						<td class="text-center l-todouhuken-nm">�s���{����</td>
						<td class="text-center l-category-id">�J�e�S����</td>
						<td class="text-center l-hyoukati">�]���l</td>
						<td class="text-center l-user-id">���[�UID</td>
					</tr>
				</table>

				<div class="table-overflow">
					<table class="l-kankou table" >
						<logic:notEmpty name="viewBean" property="list">
							<logic:iterate id="list" name="viewBean" property="list">
								<tr>
									<td class="l-kankou-id">
										<html:link action="/KojinRegistInit" paramId="kojinId" paramName="list" paramProperty="kankouId">
											<bean:write name="list" property="kankouId" />
										</html:link>
									</td>
									<td class="l-kankou-nm">
										<span class="p-10">
											<bean:write name="list" property="kankouNm" />
										</span>
									</td>
									<td class="l-tihou-nm">
										<bean:write name="list" property="tihouNm" />
									</td>
									<td class="l-todouhuken-nm">
										<span class="p-10">
											<bean:write name="list" property="todouhukenNm" />
										</span>
									</td>
									<td class="l-category-id">
										<span class="p-10">
											<bean:write name="list" property="categoryNm" />
										</span>
									</td>
									<td class="l-hyoukati">
										<bean:write name="list" property="hyoukati" />
									</td>
									<td class="l-user-id">
										<bean:write name="list" property="userId" />
									</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
					</table>
				</div>

				<jsp:include page="/jsp/common/footer.jsp" />
			</div>
		</html:form>
	</div>

</body>
</html:html>
