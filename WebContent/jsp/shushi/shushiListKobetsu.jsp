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
		ソプブーのマネーノート　収支一覧（個別）
	</title>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/common.css" />
	<script language="JavaScript" type="text/javascript" charset="shift_jis" src="js/common.js" ></script>
	<script type="text/javascript">
		// 検索ボタン押下
		function pressButton(frm, mode) {
			var himokuCdPlus = document.getElementsByName('himokuNmKey').item(0).value;
			if (himokuCdPlus != "") {
				document.getElementsByName('himokuCd').item(0).value = himokuCdPlus.substring(0,2);
			} else {
				document.getElementsByName('himokuCd').item(0).value = null;
			}
			callAction(frm, mode);
		}
	</script>
</head>

<body onload="setDeleteButton();">

	<bean:define id="inputBean" name="shushiListKobetsuForm" />
	<bean:define id="viewBean" name="SHUSHI_LIST_KOBETSU_DTO" />

	<div class="base-width text-center">

		<html:form action="/ShushiListKobetsuDisp" focus="yearKey">

			<jsp:include page="/jsp/common/header.jsp">
				<jsp:param name="screenTitle" value="収支一覧（個別）"/>
			</jsp:include>

			<html:hidden property="operation" value="" />

			<div class="contents">

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

				<table class="layout-table w-100">
					<tr>
						<td class="w-50 text-left">
							<span class="label-title">
								収支年月
							</span>
							<html:select name="inputBean" property="yearKey" styleClass="input-select-m" >
								<html:optionsCollection name="viewBean" property="yearMap" value="key" label="value" />
							</html:select>
							<html:select name="inputBean" property="monthKey" styleClass="input-select-m" >
								<html:optionsCollection name="viewBean" property="monthMap" value="key" label="value" />
							</html:select>
						</td>

						<td class="w-50 text-left">
							<span class="label-title">
								費目名
							</span>
							<html:select name="inputBean" property="himokuNmKey" styleClass="input-select-xl" >
								<html:optionsCollection name="viewBean" property="himokuNm" value="key" label="value" />
							</html:select>
					        <html:hidden name="inputBean" property="himokuCd" />
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
							<html:button property="search" onclick="pressButton(this.form, 'search');"
								styleClass="btn btn-l">
								検索
							</html:button>
						</td>
					</tr>
				</table>

				<table class="l-shushi table mb-0" >
					<tr>
						<td class="blank">
							&nbsp;
						</td>
						<td class="bg-yellow l-shushi-total">
							合計
						</td>
						<td class="text-right l-shushi-shunyu">
							<span class="p-10">
								<bean:write name="viewBean" property="shunyuTotalFormat" />
							</span>
						</td>
						<td class="text-right l-shushi-shishutsu">
							<span class="p-10">
								<bean:write name="viewBean" property="shishutsuTotalFormat" />
							</span>
						</td>
						<td class="text-right l-shushi-zandaka">
							<span class="p-10">
								<logic:greaterEqual name="viewBean" property="zandakaTotal" value="0" >
									<bean:write name="viewBean" property="zandakaTotalFormat" />
								</logic:greaterEqual>
								<logic:lessThan name="viewBean" property="zandakaTotal" value="0">
									<span class="minus">
										<bean:write name="viewBean" property="zandakaTotalFormat" />
								</span>
								</logic:lessThan>
							</span>
						</td>
					</tr>
				</table>

				<table class="l-shushi table mb-0" >
					<tr class="table-header">
						<td class="l-shushi-del">削除</td>
						<td class="l-shushi-date">日付</td>
						<td colspan="2">費目名</td>
						<td class="l-shushi-shunyu">収入</td>
						<td class="l-shushi-shishutsu">支出</td>
						<td class="l-shushi-zandaka">残高</td>
					</tr>
				</table>

				<div class="table-overflow">
					<table class="l-shushi table" >
						<logic:notEmpty name="viewBean" property="list">
							<logic:iterate id="list" name="viewBean" property="list">
								<tr>
									<td class="l-shushi-del">
										<input type="checkbox" name="checkDel" value="<bean:write name="list" property="shushiKey" />" />
									</td>
									<td class="l-shushi-date">
										<bean:write name="list" property="ymd" />
									</td>
									<td colspan="2" class="text-left">
										<span class="p-10">
											<html:link action="/ShushiRegistInit" paramId="shushiKey" paramName="list" paramProperty="shushiKey">
												<bean:write name="list" property="himokuNmSei" />
											</html:link>
										</span>
									</td>
									<td class="text-right l-shushi-shunyu">
										<span class="p-10">
											<bean:write name="list" property="shunyuFormat" />
										</span>
									</td>
									<td class="text-right l-shushi-shishutsu">
										<span class="p-10">
											<bean:write name="list" property="shishutsuFormat" />
										</span>
									</td>

									<td class="text-right l-shushi-zandaka">
										<span class="p-10">
											<logic:greaterEqual name="list" property="zandaka" value="0" >
												<bean:write name="list" property="zandakaFormat" />
											</logic:greaterEqual>
											<logic:lessThan name="list" property="zandaka" value="0">
												<font color="red">
													<bean:write name="list" property="zandakaFormat" />
												</font>
											</logic:lessThan>
										</span>
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