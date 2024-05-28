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
		ソプブーのマネーノート　個人マスタメンテ
	</title>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/common.css" />
	<script type="text/javascript" charset="shift_jis" src="js/common.js" ></script>
</head>

<body onload="setDeleteButton();">

	<bean:define id="inputBean" name="kojinListForm" />
	<bean:define id="viewBean" name="KOJIN_LIST_DTO" />

	<div class="base-width text-center">

		<html:form action="/KojinListDisp" focus="kojinNm">

			<jsp:include page="/jsp/common/header.jsp">
				<jsp:param name="screenTitle" value="個人マスタメンテ"/>
			</jsp:include>

			<html:hidden property="operation" value="" />

			<div class="contents block-center">

				<html:messages id="msg" message="true">
					<p class="msg-info">
						・<bean:write name="msg" ignore="true" filter="false"/>
					</p>
				</html:messages>

				<html:messages id="msg" message="false">
					<p class="msg-err">
						・<bean:write name="msg" ignore="true" filter="false"/>
					</p>
				</html:messages>

				<table class="layout-table">
					<tr>
						<td colspan="2" class="w-50 text-left">
							<span class="label-title">
								個人名
							</span>
							<html:text name="inputBean" property="kojinNm" styleClass="input-text-s" />
						</td>
						<td colspan="2" class="w-50 text-left">
							<span class="label-title">
								個人名ｶﾅ
							</span>
							<html:text name="inputBean" property="kojinNmkana" styleClass="input-text-m" />
						</td>
					</tr>
					<tr>
						<td class="w-25 text-left">
							<span class="label-title">
								性別
							</span>
							<html:select name="inputBean" property="seibetsuKbnKey" styleClass="input-select-m">
								<html:optionsCollection name="viewBean" property="seibetsuKbn" value="key" label="value" />
							</html:select>
						</td>
						<td class="w-25 text-left">
							<span class="label-title">
								続柄
							</span>
							<html:select name="inputBean" property="zokugaraKey" styleClass="input-select-m">
								<html:optionsCollection name="viewBean" property="zokugara" value="key" label="value" />
							</html:select>
						</td>
						<td colspan="2" class="w-50 text-left">
							<span class="label-title">
								世帯主
							</span>
							<html:checkbox name='inputBean' property="setaiNusiFlg" styleClass="input-check"/>
						</td>
					</tr>
				</table>

				<table class="layout-table block-center w-100">
					<tr>
						<td class="w-25">
							<html:button property="delete" onclick="callAction(this.form, 'delete');"
								styleClass="btn btn-l">
								削除
							</html:button>
						</td>
						<td class="w-50">
							&nbsp;
						</td>
						<td class="w-25">
							<html:button property="search" onclick="callAction(this.form, 'search');"
								styleClass="btn btn-l">
								検索
							</html:button>
						</td>
					</tr>
				</table>

				<table class="l-kojin table mb-0" >
					<tr class="table-header">
						<td class="text-center l-kojin-del">削除</td>
						<td class="text-center l-kojin-kojin-id">個人ID</td>
						<td class="text-center l-kojin-setai-id">世帯ID</td>
						<td class="text-center">個人名</td>
						<td class="text-center">個人名ｶﾅ</td>
						<td class="text-center l-kojin-sex">性別</td>
						<td class="text-center l-kojin-zokugara">続柄</td>
						<td class="text-center l-kojin-setainushi">世帯主</td>
					</tr>
				</table>

				<div class="table-overflow">
					<table class="l-himoku table" >
						<logic:notEmpty name="viewBean" property="list">
							<logic:iterate id="list" name="viewBean" property="list">
								<tr>
									<td class="text-center l-kojin-del">
										<input type="checkbox" name="checkDel" value="<bean:write name="list" property="kojinId" />" />
									</td>
									<td class="l-kojin-kojin-id">
										<html:link action="/KojinRegistInit" paramId="kojinId" paramName="list" paramProperty="kojinId">
											<bean:write name="list" property="kojinId" />
										</html:link>
									</td>
									<td class="l-kojin-setai-id">
										<bean:write name="list" property="setaiId" />
									</td>
									<td class="text-left">
										<span class="p-10">
											<bean:write name="list" property="kojinNm" />
										</span>
									</td>
									<td class="text-left">
										<span class="p-10">
											<bean:write name="list" property="kojinNmkana" />
										</span>
									</td>
									<td class="l-kojin-sex">
										<bean:write name="list" property="seibetsuNm" />
									</td>
									<td class="l-kojin-zokugara">
										<bean:write name="list" property="zokugaraNm" />
									</td>
									<td class="l-kojin-setainushi">
										<bean:write name="list" property="setaiNusiNm" />
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
