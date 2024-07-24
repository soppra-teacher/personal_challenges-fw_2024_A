<%@ page contentType="text/html; charset=Windows-31J"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<div class="contents">

	<hr/>
	<table class="layout-table w-100">
		<tr>
			
			<td> 
				<html:link action="/KankouListInit" styleClass="btn-green btn-size-m">
					<html:param name="operation" value="reserch" />
    					検索・一覧表示
					</html:link>
			</td>
			<td>
				<a href = "#" id = logOut class = "btn-green btn-size-m" tabindex = "3" >
					ログアウト
				</a>
			</td>
		</tr>
	</table>
	
	<script type="text/javascript">
		//メニュー画面の画面遷移処理
	    document.getElementById('backMenu').addEventListener('click', function (event) {
	        var msg = "別画面に遷移しますがよろしいですか？";
	        if (!confirm(msg)) {
	        	return;
	        } else {
	        	window.location.href = "./MenuInit.do?getKey=aaa"
	        }
	    });
		//ログアウトの画面遷移処理
	    document.getElementById('logOut').addEventListener('click', function (event) {
	        var msg = "別画面に遷移しますがよろしいですか？";
	        if (!confirm(msg)) {

	        	return;
	        } else {
	        	window.location.href = "./Logout.do"
	        }
	    });
	</script>

	
</div>

