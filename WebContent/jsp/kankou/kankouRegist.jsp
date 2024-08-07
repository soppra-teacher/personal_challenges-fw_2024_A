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
	<title>
			観光地検索システム 観光地登録画面
	</title>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/common.css" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/kankouRegist.css" />
	<script type="text/javascript"></script>
</head>

<body>

	<bean:define id="inputBean" name="kankouRegistForm" />
	<bean:define id="viewBean" name="KANKOU_REGIST_DTO" />


	<div class="base-width text-center">

		<html:form action="/KankouRegistIns" styleClass="text-center formdesign w-400px form-center"  >
		<div class="bg"></div>
		<div class="bg bg2"></div>
		<div class="bg bg3 "></div>
			<!-- ヘッダーの名称をここで投げている -->
			<jsp:include page="/jsp/common/header.jsp">
				<jsp:param name="screenTitle" value="観光地登録画面"/>
			</jsp:include>


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
					<table class="layout-table top-10">
						<tr>
							<td class="w-20 text-right"><span class="label-title">
									都道府県 </span></td>

							<td class="w-75 text-left">
								<html:select name="inputBean" 
									property="todouhukenKey" styleClass="textbox-s margin-15">
									<html:optionsCollection name="viewBean" property="todouhuken"
										value="key" label="value" />
								</html:select>
							</td>
						</tr>

						<tr>
							<td class="w-20 text-right"><span class="label-title">
									カテゴリ </span></td>

							<td class="w-75 text-left"><html:select name="inputBean"
									property="categoryKey" styleClass="textbox-s margin-15">
									<html:optionsCollection name="viewBean" property="category"
										value="key" label="value" />
								</html:select></td>
						</tr>

						<tr>
							<td class="w-20 text-right"><span class="label-title">
									観光地名 </span></td>

							<td class="w-75 text-left"><html:text name="inputBean"
									property="kankouNm" styleClass="textbox-m margin-15" /></td>
						</tr>

						<tr>
							<td class = "w-20 text-right position-kankou" ><span class = "label-title" >
							
									説明 </span></td>
							<td class="w-75 text-left"><html:textarea name="inputBean"
									property="setsumei" styleClass="textbox-l margin-15 re-none" /></td>
						</tr>

						<tr>
							<td class="w-20 text-right position-kankou"><span class="label-title">
									レビュー </span></td>

							<td class="w-75 text-left"><html:textarea name="inputBean"
									property="review" styleClass="textbox-xl margin-15 re-none" /></td>
						</tr>

						<tr>
							<td class="w-20 text-right"><span class="label-title">
									評価値 </span></td>

							<td class="w-75 text-left"><html:select name="inputBean"
									property="hyoka" styleClass="textbox-mini margin-15">
									<html:option value="1"></html:option>
									<html:option value="2"></html:option>
									<html:option value="3"></html:option>
									<html:option value="4"></html:option>
									<html:option value="5"></html:option>
								</html:select></td>
						</tr>

						<tr>
							<td class="w-20 text-right top-10"><span class="label-title">
									写真 </span></td>
							<td class="w-75 text-left top-10">
							<html:hidden property="base64Image" value="" />
								<!-- アップしたい画像をここで指定 -->
								 <input type="file" id="profileImage" name="profileImage" accept="image/png" />
							</td>
						</tr>
						<tr>
							<td class="w-20 text-right margin-15 "><span class="label-title">
							</span></td>
							<td class="w-75 text-left">
								<img id="preview" src="<%=request.getContextPath()%>/img/sample_bgYellow.png" class="image-sample" alt="Default Image"/>
							</td>
						</tr>
							
					</table>
				</div>
				<div class="block-center layout-table">
					<html:button property="insert" styleClass="btn-blue btn-size-m padding-b-1" onclick="resetImage();">
						キャンセル
					</html:button>
				</div>
				<div class="block-center layout-table">
					<a href = "#" id = "clicklink" class = "btn-green btn-size-m" tabindex = "3" >
		              登録
		            </a>
					<jsp:include page="/jsp/common/footer.jsp" />
				</div>
		</html:form>
		
		<!-- 登録リンクの画面遷移用URL -->
		<script type="text/javascript">
		    document.getElementById('clicklink').addEventListener('click', function (event) {
		        var msg = "登録してもよろしいですか？";
		        if (!confirm(msg)) {
		        	return;
		        } else {
		        	document.forms[0].submit();
		        	
		        }
		    });
		</script>
		<script type="text/javascript">
			document.getElementById('profileImage').addEventListener('change', function(event) {
			    var file = event.target.files[0];
			    if (file) {
			        var reader = new FileReader();
			        reader.onload = function(e) {
			            document.getElementById('preview').src = e.target.result;
			            document.kankouRegistForm.base64Image.value = e.target.result;
			        }
			        reader.readAsDataURL(file);
			    }
			});

			function resetImage() {
			    // プレビュー画像を元の画像パスに戻す
			    document.getElementById('preview').src="<%=request.getContextPath()%>/img/sample_bgYellow.png";
			    // 隠しフィールドの値もリセット
			    document.kankouUpdDelForm.base64Image.value = '';
			}

		</script>
		
	</div>
</body>
</html:html>