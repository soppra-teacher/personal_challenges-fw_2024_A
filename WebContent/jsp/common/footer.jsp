<%@ page contentType="text/html; charset=Windows-31J"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<div class="contents">



	<hr />
	<table class="layout-table w-100">
		<tr>
			<td class="w-25">
				<a href = "#" id = "backMenu" class = "btn-green btn-size-m" tabindex = "3" >
					メニューへ戻る
				</a>
			</td>
			<td class="w-25">
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
	        	console.log("キャンセル");
	        	return;
	        } else {
	        	console.log("OK");
	        	window.location.href = "http://localhost:8080/Cashbook/MenuInit.do?getKey=aaa"
	        }
	    });
		//ログアウトの画面遷移処理
	    document.getElementById('logOut').addEventListener('click', function (event) {
	        var msg = "別画面に遷移しますがよろしいですか？";
	        if (!confirm(msg)) {
	        	console.log("キャンセル");
	        	return;
	        } else {
	        	console.log("OK");
	        	
	        	window.location.href = "http://localhost:8080/Cashbook/Logout.do"
	        }
	    });
	</script>

	
</div>

