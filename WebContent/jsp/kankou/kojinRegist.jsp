<%@ page pageEncoding="Windows-31J"%>
<%@ page contentType="text/html;charset=Windows-31J"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ page import="cashbook.util.Const"%>
<%@ page import="javax.servlet.http.HttpSession" %>

<!DOCTYPE html>
<html:html lang="ja">
<head>

	<meta content="ja" http-equiv="Content-Language" />
	<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
	<title>
		観光地検索システム・更新削除画面
	</title>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/common.css" /> 
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/kankouListUpdDel.css" />
		
<script language="JavaScript" type="text/javascript" charset="shift_jis" src="js/common.js"></script>
<script type="text/javascript">
</script>
<!-- フォーカスセット -->
<!--<script type="text/javascript">
  window.onload = function(){
    var eleRevision = document.kojinRegistForm.revision;
    var eleKojinId  = document.kojinRegistForm.kojinId;
    var elePassword = document.kojinRegistForm.pass;

    if (eleRevision.defaultValue == ""){
      eleKojinId.focus();
    } else {
      elePassword.focus();
    }
  }
//////////////////////////////////////////////////////////////////////////////////////
  window.onload = function(){
	  document.getElementById('imagePath').addEventListener('change', function() {
	    document.kojinRegistForm.hiddenImagePath.value = this.value;
	    console.log(document.kojinRegistForm.hiddenImagePath.value);
	  });
	}
/////////////////////////////////////////////////////////////////////////////////////////

</script>-->
</head>

<body>

	<bean:define id="inputBean" name="kojinRegistForm" />
	<bean:define id="viewBean" name="KOJIN_REGIST_DTO" />
	<bean:define id="backAction" name="KOJIN_REGIST_BACK"
		type="java.lang.String" />

	<div class="formdesign text-center padding-t-1">

		<html:form action="/KojinRegistDisp" focus="kojinId">

			<jsp:include page="/jsp/common/header.jsp">
				<jsp:param name="screenTitle" value="詳細表示・更新削除画面" />
			</jsp:include>

			<html:hidden property="operation" value="" />
			<html:hidden property="hiddenImagePath" value="" />
			<html:hidden name="viewBean" property="revision" />
			<html:hidden property="uploadedFilePath" value="" />
			<html:hidden name="viewBean" property="hyokaJudge" value="" />
			<html:hidden name="viewBean" property="userId" value="" />
			<html:hidden property="base64Image"  value=""/>
			<html:hidden name="inputBean" property="kankouId"  value=""/>
			
			<div class="bg"></div>
			<div class="bg bg2"></div>
			<div class="bg bg3 "></div>

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
					
						<table>
						<tr>
    <!--<td class="w-25 text-right">
        <span class="label-title">
            <!-- ラベルのタイトルをここに記載 
        </span>
    </td> -->
<td class="w-75 text-left">
    <div class="image-style-div">
        <img id="preview" src="<%=request.getContextPath()%>/img/login.png" class="image-style-img" alt="Default Image"/>
    </div>
</td>
</tr>
</table>
<%
    HttpSession httpSession = request.getSession();
    String logUserId = (String) httpSession.getAttribute("logUserId");
%>
								<logic:equal name="viewBean" property="userId" value="<%= logUserId %>">
									<input type="file" id="profileImage" name="profileImage" accept="image/*" class="btn btn-l" />
								</logic:equal>

<script type="text/javascript">
document.getElementById('profileImage').addEventListener('change', function(event) {
    var file = event.target.files[0];
    if (file) {
        var reader = new FileReader();
        reader.onload = function(e) {
            document.getElementById('preview').src = e.target.result;
            document.kojinRegistForm.operation.value = 'update';
        }
        reader.readAsDataURL(file);
    }
});
</script>

					<logic:equal name="viewBean" property="userId" value="<%= logUserId %>">
					    <html:button property="insert" styleClass="btn-blue btn-size-m padding-b-1" >
					        キャンセル
					    </html:button>
					</logic:equal>


                    <table class="layout-table w-100">
						<tr>
							<td class="w-25 text-right">
								<span class="label-title">
										観光地名 
								</span>
							</td>
							<td class="w-75 text-left">
								<html:text name="viewBean" property="kankouNm" styleClass="textbox-m-w90" readonly="true"/>
							</td>
						
							<td class="w-25 text-right">
								<span class="label-title">
										カテゴリ名 
								</span>
							</td>
							<td class="w-75 text-left">
								<html:text name="viewBean" property="categoryNm" styleClass="textbox-m-w60" readonly="true"/>
							</td>
						</tr>
						<tr>
							<td class="w-25 text-right">
								<span class="label-title">
									都道府県名 
								</span>
							</td>
							<td class="w-75 text-left">
								<html:text name="viewBean" property="todouhukenNm" styleClass="textbox-m-w60" readonly="true"/>
							</td>
							<td class="w-25 text-left">
								<span class="label-title p-right33">
										八地方名 
								</span>
							</td>
							<td class="w-75 text-left">
								<html:text name="viewBean" property="tihouNm" styleClass="w-75 textbox-m" readonly="true"/>
							</td>
						</tr>
						
						<tr>
							<td class="w-25 text-right position-kankou">
								<span class="label-title">
										説明
								</span>
							</td>
							<td class="w-75 text-left">
							
								<logic:equal name="viewBean" property="userId" value="<%= logUserId %>">
									<html:textarea name="inputBean" property="setsumei" styleClass="textbox-l-ks margin-15 re-none" readonly="false"/>
								</logic:equal>
							</td>
						</tr>
						
						<tr>
							<td class="w-25 text-right position-kankou">
								<span class="label-title">
										レビュー
								</span>
							</td>
							<td class="w-75 text-left">
							<html:textarea name="inputBean" property="review" styleClass="textbox-l-ks margin-15 re-none" readonly="true"/>
							</td>
						</tr>
						            <tr>
              <td class="w-25 text-right p-right33">
                <span class="label-title">
                  評価値
                </span>
              </td>
              <td class="w-75 text-left p-right33">
                <html:select name="inputBean" property="hyoka" styleClass="textbox-mini">
                  <html:option value="1"></html:option>
                  <html:option value="2"></html:option>
                  <html:option value="3"></html:option>
                  <html:option value="4"></html:option>
                  <html:option value="5"></html:option>
                </html:select>
              </td>
            </tr>


					</table>
				</div>

				<div class="block-center">
					<logic:equal name="viewBean" property="hyokaJudge" value="0">
    					<html:button property="insert" styleClass="btn-blue btn-size-m" onclick="callAction(this.form, 'insert');">
        					登録
    					</html:button>
					</logic:equal>

					<logic:equal name="viewBean" property="hyokaJudge" value="1">
    					<html:button property="insert" styleClass="btn-blue btn-size-m" onclick="callAction(this.form, 'update');">
        					更新
    					</html:button>
					</logic:equal>
				</div>

				<div class="block-center padding-t-1">
					<html:button property="back" styleClass="btn-blue btn-size-m"
						onclick='<%="callAction(this.form,\'" + backAction + "\');"%>'>
            				戻る
         			 </html:button>
				</div>

				<jsp:include page="/jsp/common/footer.jsp" />
			</div>
		</html:form>
	</div>

<script type="text/javascript">
document.getElementById('profileImage').addEventListener('change', function(event) {
    var file = event.target.files[0];
    if (file) {
        var reader = new FileReader();
        reader.onload = function(e) {
            // Base64エンコーディングされた画像データを隠しフィールドに設定
            document.kojinRegistForm.base64Image.value = e.target.result;
            // プレビュー画像を更新
            document.getElementById('preview').src = e.target.result;
        }
        reader.readAsDataURL(file);
    }
});
</script>

</body>
</html:html>