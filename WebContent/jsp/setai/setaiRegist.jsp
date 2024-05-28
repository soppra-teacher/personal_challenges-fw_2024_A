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
		ソプブーのマネーノート　世帯マスタ登録
	</title>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/common.css" />
	<script type="text/javascript" charset="shift_jis" src="js/common.js" /></script>
	<!-- フォーカスセット -->
	<script type="text/javascript">
		window.onload = function(){
			var eleRevision = document.setaiRegistForm.revision;
			var eleSetaiId  = document.setaiRegistForm.setaiId;
			var eleSetaiNm  = document.setaiRegistForm.setaiNm;

			if (eleRevision.defaultValue == ""){
				eleSetaiId.focus();
			} else {
				eleSetaiNm.focus();
			}
		}
	</script>
</head>

<body>

	<bean:define id="inputBean" name="setaiRegistForm" />
	<bean:define id="viewBean" name="SETAI_REGIST_DTO" />
	<bean:define id="backAction" name="SETAI_REGIST_BACK" type="java.lang.String" />

	<div class="base-width text-center">

		<html:form action="/SetaiRegistDisp">

			<jsp:include page="/jsp/common/header.jsp">
				<jsp:param name="screenTitle" value="世帯マスタ登録"/>
			</jsp:include>

			<html:hidden property="operation" value="" />
			<html:hidden name="viewBean" property="revision" />

			<div id="contents">


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

				<div>
					<table class="layout-table w-100">
						<tr>
							<td class="w-25 text-right">
								<span class="label-title">
									世帯ID
								</span>
							</td>
							<td class="w-75 text-left">
								<logic:empty name="viewBean" property="revision">
									<html:text name="inputBean" property="setaiId" styleClass="input-text-s" />
								</logic:empty>
								<logic:notEmpty name="viewBean" property="revision">
									<html:text name="inputBean" property="setaiId" styleClass="input-text-s" readonly="true" />
								</logic:notEmpty>
								<label id="Label1"></label>
							</td>
						</tr>
						<tr>
							<td class="w-25 text-right">
								<span class="label-title">
									世帯名
								</span>
							</td>
							<td class="w-75 text-left">
								<html:text name="inputBean" property="setaiNm" styleClass="input-text-l" />
								<label id="Label1"></label>
							</td>
						</tr>
						<tr>
							<td class="w-25 text-right">
								<span class="label-title">
									世帯名ｶﾅ
								</span>
							</td>
							<td class="w-75 text-left">
								<html:text name="inputBean" property="setaiNmKana" styleClass="input-text-l"/>
								<label id="Label1"></label>
							</td>
						</tr>
						<tr>
							<td class="w-25 text-right">
								<span class="label-title">
									郵便番号
								</span>
							</td>
							<td class="w-75 text-left">
								<html:text name="inputBean" property="postNo" styleClass="input-text-s" />
								<label id="Label1"></label>
							</td>
						</tr>
						<tr>
							<td class="w-25 text-right">
								<span class="label-title">
									住所
								</span>
							</td>
							<td class="w-75 text-left">
								<html:text name="inputBean" property="address" styleClass="input-text-l" />
								<label id="Label1"></label>
							</td>
						</tr>
						<tr>
							<td class="w-25 text-right">
								<span class="label-title">
									電話番号
								</span>
							</td>
							<td class="w-75 text-left">
								<html:text name="inputBean" property="telNo" styleClass="input-text-s" />
								<label id="Label1"></label>
							</td>
						</tr>
					</table>
				</div>

				<div class="block-center">
					<logic:empty name="viewBean" property="revision">
						<html:button property="insert" onclick="callAction(this.form, 'insert');"
							 styleClass="btn btn-l">
							 登録
						</html:button>
					</logic:empty>
					<logic:notEmpty name="viewBean" property="revision">
						<html:button property="insert" onclick="callAction(this.form, 'update');"
							 styleClass="btn btn-l">
							 更新
						</html:button>
					</logic:notEmpty>
				</div>

				<div class="block-center">
					<html:button property="back" onclick='<%= "callAction(this.form, \'" + backAction + "\');"%>'
						styleClass="btn btn-l">
						戻る
					</html:button>
				</div>

				<jsp:include page="/jsp/common/footer.jsp" />
			</div>
		</html:form>
	</div>
</body>
</html:html>