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
<title>観光地登録画面</title>
<link rel="stylesheet" type="text/css"
	href="<%= request.getContextPath() %>/css/common.css" />
<script language="JavaScript" type="text/javascript" charset="shift_jis"
	src="js/common.js"></script>

<script type="text/javascript">
	
</script>

</head>

<body>

	<bean:define id="inputBean" name="kankouRegistForm" />
	<bean:define id="viewBean" name="KANKOU_REGIST_DTO" />
	<bean:define id="backAction" name="KANKOU_REGIST_BACK"
		type="java.lang.String" />

	<div class="base-width text-center">

		<html:form action="/KankouRegistDisp" focus="todouhuKen">

			<!-- ヘッダーの名称をここで投げている -->
			<jsp:include page="/jsp/common/header.jsp">
				<jsp:param name="screenTitle" value="観光地登録画面" />
			</jsp:include>

			<html:hidden property="operation" value="" />

			<!-- 登録不備などの時にメッセージを投げる場所 -->
			<div id="contents">
				<html:messages id="msg" message="true">
					<p class="msg-info">
						・
						<bean:write name="msg" ignore="true" filter="false" />
					</p>
				</html:messages>

				<html:messages id="msg" message="false">
					<p class="msg-err">
						・
						<bean:write name="msg" ignore="true" filter="false" />
					</p>
				</html:messages>

				<div>
					<table class="layout-table w-100">
						<tr>
							<td class="w-25 text-right"><span class="label-title">
									都道府県 </span></td>

							<td class="w-75 text-left"><html:select name="inputBean"
									property="todouhukenKey" styleClass="input-select-xl">
									<html:optionsCollection name="viewBean" property="todouhuken"
										value="key" label="value" />
								</html:select></td>
						</tr>

						<tr>
							<td class="w-25 text-right"><span class="label-title">
									カテゴリ </span></td>

							<td class="w-75 text-left"><html:select name="inputBean"
									property="categoryKey" styleClass="input-select-xl">
									<html:optionsCollection name="viewBean" property="category"
										value="key" label="value" />
								</html:select></td>
						</tr>

						<tr>
							<td class="w-25 text-right"><span class="label-title">
									観光地名 </span></td>

							<td class="w-75 text-left"><html:text name="inputBean"
									property="kankouNm" styleClass="input-text-l" /></td>
						</tr>

						<tr>
							<td class="w-25 text-right"><span class="label-title">
									説明 </span></td>
							<td class="w-75 text-left"><html:textarea name="inputBean"
									property="setsumei" styleClass="input-text-l2" /></td>
						</tr>

						<tr>
							<td class="w-25 text-right"><span class="label-title">
									レビュー </span></td>

							<td class="w-75 text-left"><html:textarea name="inputBean"
									property="review" styleClass="input-text-l3" /></td>
						</tr>

						<tr>
							<td class="w-25 text-right"><span class="label-title">
									評価値 </span></td>

							<td class="w-75 text-left"><html:select name="inputBean"
									property="hyoka" styleClass="input-select-m">
									<html:option value="1"></html:option>
									<html:option value="2"></html:option>
									<html:option value="3"></html:option>
									<html:option value="4"></html:option>
									<html:option value="5"></html:option>
								</html:select></td>
						</tr>

						<tr>
							<td class="w-25 text-right"><span class="label-title">
									写真 </span></td>
							<td class="w-75 text-left">
								<!-- アップしたい画像をここで指定 --> <input type="file" id="pictures"
								accept=".png, .jpg, .jpeg"> <!--<html:file  name="inputBean"  property="pictures" accept=".png, .jpg, .jpeg"/>  
				 -->
							</td>
						</tr>
						<tr>
							<td class="w-25 text-right"><span class="label-title">
							</span></td>
							<td class="w-75 text-left">
								<div class="images">
									<!-- アップした写真を表示する場所 -->
									<div id="preview" class="imt-item"></div>
								</div>
							</td>
						</tr>
					</table>
				</div>

				<div class="block-center">
					<html:button property="insert" styleClass="btn btn-l"
						onclick="callAction(this.form, 'insert');">
              登録
            </html:button>


					<jsp:include page="/jsp/common/footer.jsp" />

				</div>
		</html:form>
		<!-- 選択した写真を表示させるスクリプト -->
		<script src="js/picture.js"></script>
	</div>
</body>
</html:html>