
<%@ page contentType="text/html; charset=Windows-31J"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<div class="contents">

	<hr />

	<table class="layout-table w-100">

		<tr>

			<td class="w-25">
				<a href = "#" id = "registKankou" class = "btn-green btn-size-m" tabindex = "3" >
					登録画面へ
				</a>
			</td>

			<td><a href="./KankouListInit.do?operation=reserch"
				id="searchList" class="btn-green btn-size-m"> 検索・一覧表示 </a></td>

			<td class="w-25"><a href="#" id="logOut"
				class="btn-green btn-size-m" tabindex="3"> ログアウト </a></td>

		</tr>

	</table>

	<script type="text/javascript">
	//メニュー画面の画面遷移処理
    document.getElementById('registKankou').addEventListener('click', function (event) {
        var msg = "別画面に遷移しますがよろしいですか？";
        if (!confirm(msg)) {
        	return;
        } else {
        	window.location.href = "./KankouRegistInit.do"
        }
    });

		// ログアウトの画面遷移処理

		document
				.getElementById('logOut')
				.addEventListener(
						'click',
						function(event) {

							var msg = "別画面に遷移しますがよろしいですか？";

							if (!confirm(msg)) {

								event.preventDefault();

							} else {

								window.location.href = "./Logout.do";

							}

						});

		// 検索・一覧表示リンクの画面遷移処理

		document
				.getElementById('searchList')
				.addEventListener(
						'click',
						function(event) {

							var msg = "検索・一覧表示画面に遷移しますがよろしいですか？";

							if (!confirm(msg)) {

								event.preventDefault();

							} else {

								window.location.href = "./KankouListInit.do?operation=reserch";

							}

						});
	</script>

</div>
