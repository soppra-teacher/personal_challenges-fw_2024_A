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
		ソプブーのマネーノート　メニュー
	</title>

	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/common.css" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/menu.css" />
	<script type="text/javascript"></script>
</head>

<body>

	<div class="base-width text-center">

		<bean:define id="loginInfo" name="LOGIN_DTO" />

		<jsp:include page="/jsp/common/header.jsp">
			<jsp:param name="screenTitle" value="メニュー" />
		</jsp:include>

		<div class="contents block-center">

			<table class="layout-table block-center">
				<tr>
					<td colspan="3">
						<img src="img/sopbu.png" alt="そぷぶー" />
					</td>
				</tr>

				<tr>
					<td colspan="3">
						<span class="company-logo">SOPPRA</span>
					</td>
				</tr>

				<tr>
					<td rowspan="5" class="plr-20">
						<h1>
							経営理念
						</h1>
						<h2>
							感謝が人を創り
						</h2>
						<h2>
							感謝が幸福を呼ぶ
						</h2>
						<h3>
							Ⅰ　全てに感謝する経営
						</h3>
						<h3>
							Ⅱ　優れた人格を形成する経営
						</h3>
						<h3>
							Ⅲ　人々を幸福にする経営
						</h3>
					</td>
					<td class="plr-20">
						<p>
							<html:link action="/KojinRegistInit">個人マスタ登録</html:link>
						</p>
					</td>
					<td class="plr-20">
						<p>
							<html:link action="/KojinListInit">個人マスタメンテ</html:link>
						</p>
					</td>
				</tr>

				<tr>
					<td>
						<p>
							<html:link action="/SetaiRegistInit">世帯マスタ登録</html:link>
						</p>
					</td>
					<td>
						<p>
							<html:link action="/SetaiListInit">世帯マスタメンテ</html:link>
						</p>
					</td>
				</tr>

				<tr>
					<td>
						<p>
							<html:link action="/HimokuRegistInit">費目マスタ登録</html:link>
						</p>
					</td>
					<td>
						<p>
							<html:link action="/HimokuListInit">費目マスタメンテ</html:link>
						</p>
					</td>
				</tr>

				<tr>
					<td>
						<p>
							<html:link action="/ShushiRegistInit">収支登録</html:link>
						</p>
					</td>
					<td>
						<p>
							&nbsp;
						</p>
					</td>
				</tr>

				<tr>
					<td>
						<p>
							<html:link action="/ShushiListKobetsuInit">収支一覧（個別）</html:link>
						</p>
					</td>
					<td>
						<p>
							<logic:equal name="loginInfo" property="setainushiFlg" value="<%=Const.SETAINUSHI_ON%>">
								<html:link action="/ShushiListSetaiInit">収支一覧（世帯）</html:link>
							</logic:equal>
							<logic:notEqual name="loginInfo" property="setainushiFlg" value="<%=Const.SETAINUSHI_ON%>">
								&nbsp;
							</logic:notEqual>
						</p>
					</td>
				</tr>

			</table>


			<jsp:include page="/jsp/common/footer.jsp" />
		</div>

	</div>
</body>
</html:html>
