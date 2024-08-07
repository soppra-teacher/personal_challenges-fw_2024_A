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
			ÏõnõVXe õEêæÊ
	</title>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/common.css" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/kankouListSearch.css" />
	<script type="text/javascript" charset="shift_jis" src="js/common.js" ></script>
</head>
<script>

</script>

<body>


	<bean:define id="inputBean" name="kankouListForm" />
	<bean:define id="viewBean" name="KANKOU_LIST_DTO" />

	<div class="formdesign text-center">

		<html:form action="/KankouListSearch">

			<html:hidden property="operation" value="" />
			
			<div class="bg"></div>
			<div class="bg bg2"></div>
			<div class="bg bg3 "></div>

			<div class="contents block-center">
			<jsp:include page="/jsp/common/header.jsp">
				<jsp:param name="screenTitle" value="õêæÊ"/>
			</jsp:include>

				<html:messages id="msg" message="true">
					<p class="msg-info">
						E<bean:write name="msg" ignore="true" filter="false"/>
					</p>
				</html:messages>

				<html:messages id="msg" message="false">
					<p class="msg-err">
						E<bean:write name="msg" ignore="true" filter="false"/>
					</p>
				</html:messages>

				<table class="layout-table">
					<tr>
						<td colspan="1" class="w-25 text-left">
							<span class="label-title p-right2-5">
								Ïõn¼
							</span>
							<html:text name="inputBean" property="kankouNm" styleClass="textbox-m-w60 p-right2-7" />
							<span class="label-title p-right7-7">
								[UID
							</span>
						</td>
						<td colspan="2" class="w-25 text-left p-right2">
							<html:text name="inputBean" property="userId" styleClass="textbox-m-w60 p-right2-w200" />
						</td>
					</tr>
					<tr>
						<td class="w-40 text-left">
							<span class="label-title">
								JeS¼
							</span>
							<html:select name="inputBean" property="categoryKey" styleClass="textbox-s-w215">
								<html:optionsCollection name="viewBean" property="category" value="key" label="value" />
							</html:select>
						
							<span class="label-title">
								ªnû¼
							</span>
							<html:select name="inputBean" property="tihouKey" styleClass="textbox-s-w185">
								<html:optionsCollection name="viewBean" property="tihou" value="key" label="value" />
							</html:select>
						</td>
						<td class="w-25 text-left">
							<span class="label-title">
								s¹§¼
							</span>
							<html:select name="inputBean" property="todouhukenKey" styleClass="textbox-s-w185" >
								<html:optionsCollection name="viewBean" property="todouhuken" value="key" label="value" />
							</html:select>
						</td>
						
					</tr>
				</table>
				
		
						<td class="w-25">
							<html:button property="search" onclick="callAction(this.form, 'search');"
								styleClass="btn-blue btn-size-m padding-b-1">
								õ
							</html:button>
							
						</td>
					</tr>
				</table>

				<table class="l-kankou table mb-0" >
					<tr class="table-header">
						<td class="text-center l-kankou-id ">ÏõnID</td>
						<td class="text-center l-kankou-nm">Ïõn¼</td>
						<td class="text-center l-tihou-nm">ªnû¼</td>
						<td class="text-center l-todouhuken-nm">s¹{§¼</td>
						<td class="text-center l-category-id">JeS¼</td>
						<td class="text-center l-hyoukati">]¿l</td>
						<td class="text-center l-user-id">[UID</td>
					</tr>
				</table>

				<div class="table-overflow">
					<table class="l-kankou table" >
						<logic:notEmpty name="viewBean" property="list">
							<logic:iterate id="list" name="viewBean" property="list">
								<tr>
									
									<td class="l-kankou-id">
										<html:link action="/KankouUpdDelInit" paramId="kankouId" paramName="list" paramProperty="kankouId">
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
				<div class="absoluteRelativeimg">
						<img src="<%=request.getContextPath()%>/img/chara_kotira.png" class="charakotira-img">
					</div>
				<jsp:include page="/jsp/common/footerList.jsp" />
			</div>
			<div class="absoluteRelativeimg">
				<img src="<%=request.getContextPath()%>/img/star.png" class="rollingstar-img">
				<img src="<%=request.getContextPath()%>/img/star.png" class="rollingstar2-img">
			</div>
		</html:form>
	</div>

</body>
</html:html>