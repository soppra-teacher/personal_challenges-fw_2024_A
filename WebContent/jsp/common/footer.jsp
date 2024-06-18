<%@ page contentType="text/html; charset=Windows-31J"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<div class="contents">


	<hr />
	<table class="layout-table w-100">
		<tr>
			
			<td >
				<html:link action="/MenuInit"  styleClass="btn-green btn-size-m" >
					メニューへ戻る
				</html:link>
			</td>
			<td>
				<html:link action="/Logout" styleClass="btn-green btn-size-m" >
					ログアウト
				</html:link>
			</td>
			<td >
				<html:link action="/KojinRegistInit" styleClass="btn-green btn-size-m">
					登録画面へ
				</html:link>
			</td>
			
		</tr>
	</table>
</div>

