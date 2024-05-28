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
		ソプブーのマネーノート　セッションエラー
	</title>
</head>

<body bgcolor="#ffffff">
	<center>
		<h3>セッションエラー</h3>
		<pre>
			セッション情報が不正です。
			アクセスが不正か、
			セッションがタイムアウトしました。
		</pre>
		<html:link action="/Logout">ログイン画面</html:link>
		に戻る。
	</center>
</body>
</html:html>
