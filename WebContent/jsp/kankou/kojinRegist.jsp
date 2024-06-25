
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
<title>�\�v�u�[�̃}�l�[�m�[�g �l�}�X�^�o�^</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/common.css" />
<script language="JavaScript" type="text/javascript" charset="shift_jis"
	src="js/common.js"></script>
<script type="text/javascript">
</script>
<!-- �t�H�[�J�X�Z�b�g -->
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

	<div class="base-width text-center">

		<html:form action="/KojinRegistDisp" focus="kojinId">

			<jsp:include page="/jsp/common/header.jsp">
				<jsp:param name="screenTitle" value="�ڍו\���E�X�V�폜���" />
			</jsp:include>

			<html:hidden property="operation" value="" />
			<html:hidden property="hiddenImagePath" value="" />
			<html:hidden name="viewBean" property="revision" />
			<html:hidden property="uploadedFilePath" value="" />
			<html:hidden name="viewBean" property="hyokaJudge" value="" />
			<html:hidden name="viewBean" property="userId" value="" />
			<html:hidden property="base64Image"  value=""/>
			<html:hidden name="inputBean" property="kankouId"  value=""/>
			


			<div id="contents">
				<html:messages id="msg" message="true">
					<p class="msg-info">
						�E
						<bean:write name="msg" ignore="true" filter="false" />
					</p>
				</html:messages>

				<html:messages id="msg" message="false">
					<p class="msg-err">
						�E
						<bean:write name="msg" ignore="true" filter="false" />
					</p>
				</html:messages>

				<div>
					
						<table>
						<tr>
    <td class="w-25 text-right">
        <span class="label-title">
            <!-- ���x���̃^�C�g���������ɋL�� -->
        </span>
    </td>
<td class="w-75 text-left">
    <div style="width: 1000px; height: 400px; border: 1px solid #000; display: flex; align-items: center; justify-content: center; overflow: hidden;">
        <img id="preview" src="<%=request.getContextPath()%>/img/login.png" style="max-width: 100%; max-height: 100%;" alt="Default Image"/>
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
    <html:button property="insert" styleClass="btn btn-l" >
        �L�����Z��
    </html:button>
</logic:equal>



                    <table class="layout-table w-100">
						<tr>
							<td class="w-25 text-right">
							<span class="label-title">
									�ό��n�� 
							</span>
							</td>
							<td class="w-75 text-left">
							<html:text name="viewBean" property="kankouNm" styleClass="input-text-l" readonly="true"/>
							</td>
						</tr>
						<tr>
							<td class="w-25 text-right">
							<span class="label-title">
									�J�e�S���� 
									</span>
									</td>
							<td class="w-75 text-left">
							<html:text name="viewBean" property="categoryNm" styleClass="input-text-m" readonly="true"/>
							</td>
						</tr>
						<tr>
							<td class="w-25 text-right">
							<span class="label-title">
									�s���{���� 
									</span>
									</td>
							<td class="w-75 text-left">
							<html:text name="viewBean" property="todouhukenNm" styleClass="input-text-m" readonly="true"/>
							</td>
						</tr>
						</tr>
						<tr>
							<td class="w-25 text-right">
							<span class="label-title">
									���n���� 
									</span>
									</td>
							<td class="w-75 text-left">
							<html:text name="viewBean" property="tihouNm" styleClass="input-text-m" readonly="true"/>
							</td>
						</tr>
						
						<tr>
							<td class="w-25 text-right">
							<span class="label-title">
									���� 
									</span>
									</td>
							<td class="w-75 text-left">
							<html:text name="inputBean" property="setsumei" styleClass="input-text-m" readonly="true"/>
							<logic:equal name="viewBean" property="userId" value="<%= logUserId %>">
									<html:text name="inputBean" property="setsumei" styleClass="input-text-m" readonly="false"/>
								</logic:equal>
							</td>
						</tr>
						
						<tr>
							<td class="w-25 text-right">
							<span class="label-title">
									���r���[
									</span>
									</td>
							<td class="w-75 text-left">
							<html:text name="inputBean" property="review" styleClass="input-text-m" readonly="true"/>
							</td>
						</tr>
						            <tr>
              <td class="w-25 text-right">
                <span class="label-title">
                  �]���l
                </span>
              </td>
              <td class="w-75 text-left">
                <html:select name="inputBean" property="hyoka" styleClass="input-select-m">
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
    					<html:button property="insert" styleClass="btn btn-l" onclick="callAction(this.form, 'insert');">
        					�o�^
    					</html:button>
					</logic:equal>

					<logic:equal name="viewBean" property="hyokaJudge" value="1">
    					<html:button property="insert" styleClass="btn btn-l" onclick="callAction(this.form, 'update');">
        					�X�V
    					</html:button>
					</logic:equal>
				</div>

				<div class="block-center">
					<html:button property="back" styleClass="btn btn-l"
						onclick='<%="callAction(this.form,\'" + backAction + "\');"%>'>
            �߂�
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
            // Base64�G���R�[�f�B���O���ꂽ�摜�f�[�^���B���t�B�[���h�ɐݒ�
            document.kojinRegistForm.base64Image.value = e.target.result;
            // �v���r���[�摜���X�V
            document.getElementById('preview').src = e.target.result;
        }
        reader.readAsDataURL(file);
    }
});
</script>

</body>
</html:html>