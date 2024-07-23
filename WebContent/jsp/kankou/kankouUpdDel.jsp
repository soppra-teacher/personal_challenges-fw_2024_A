
<%@ page pageEncoding="Windows-31J"%>
<%@ page contentType="text/html;charset=Windows-31J"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ page import="cashbook.util.Const"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="cashbook.dto.kankou.KankouUpdDelDto" %>

<!DOCTYPE html>
<html:html lang="ja">
<head>

	<meta content="ja" http-equiv="Content-Language" />
	<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
	<title>
			�ό��n�����V�X�e�� �ό��n�X�V�E�폜���
	</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/common.css" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/kankouListUpdDel.css" />
	<script type="text/javascript" charset="utf-8" src="js/common.js"></script>
	<script type="text/javascript"></script>
</head>

<body>

	<bean:define id="inputBean" name="kankouUpdDelForm" />
	<bean:define id="viewBean" name="KANKOU_UPD_DEL_DTO" />
	<%
    	KankouUpdDelDto dto = (KankouUpdDelDto) viewBean;
    	String logUserId = dto.getLogUserId();
	%>

	<div class="formdesign text-center padding-t-1">

		<html:form action="/KankouUpdDel">

			<jsp:include page="/jsp/common/header.jsp">
				<jsp:param name="screenTitle" value="�ڍו\���E�X�V�폜���" />
			</jsp:include>

			<html:hidden property="operation" value=""/>
			<html:hidden property="hiddenImagePath" value=""/>
			<html:hidden property="uploadedFilePath" value=""/>
			<html:hidden name="viewBean" property="hyokaJudge"/>
			<html:hidden name="viewBean" property="userId" />
			<html:hidden name="viewBean" property="imagePath" />
			<html:hidden property="base64Image"  value=""/>
			<html:hidden property="logUserId" value="<bean:write name='viewBean' property='logUserId'/>" />
			
			<div class="bg"></div>
			<div class="bg bg2"></div>
			<div class="bg bg3 "></div>
			

			<html:messages id="msg" message="true">
			    <p class="msg-info">
			        �E<bean:write name="msg" ignore="true" filter="false"/>
			    </p>
			</html:messages>
			
			<html:messages id="msg" message="false">
			    <p class="msg-err">
			        �E<bean:write name="msg" ignore="true" filter="false"/>
			    </p>
			</html:messages>
				<div>				
				<table>
					<tr>

					    </td>
						<td class="w-75 text-left">
						    <div class="image-style-div">
						        <img id="preview" src="<%=request.getContextPath()%>/img/kankouti/<bean:write name="viewBean" property="imagePath"/>" class="image-style-img" alt="�ʐ^���o�^����Ă��܂���"/>
						    </div>
						</td>
					</tr>
				</table>
				
				<logic:equal name="viewBean" property="userId" value="<%= logUserId %>">
					<input type="file" id="profileImage" name="profileImage" accept="image/*" class="btn btn-L" value="" />
				</logic:equal>
			
				<logic:equal name="viewBean" property="userId" value="<%= logUserId %>">
				    <html:button property="insert" styleClass="btn-blue btn-size-m padding-b-1" onclick="resetImage();">
				        �L�����Z��
				    </html:button>
				</logic:equal>
                    <table class="layout-table w-100 top-10">
						<tr>
							<td class="w-25 text-right">
							<span class="label-title">
									�ό��n�� 
							</span>
							</td>
							<td class="w-75 text-left">
							<html:text name="viewBean" property="kankouNm" styleClass="textbox-m-w90" readonly="true"/>
							</td>
						
							<td class="w-25 text-right">
							<span class="label-title">
									�J�e�S���� 
									</span>
									</td>
							<td class="w-75 text-left">
							<html:text name="viewBean" property="categoryNm" styleClass="textbox-m-w60" readonly="true"/>
							</td>
						</tr>
						<tr>
							<td class="w-25 text-right">
							<span class="label-title">
									�s���{���� 
									</span>
									</td>
							<td class="w-75 text-left">
							<html:text name="viewBean" property="todouhukenNm" styleClass="textbox-m-w60" readonly="true"/>
							</td>
						
							<td class="w-25 text-right">
							<span class="label-title">
									���n���� 
									</span>
									</td>
							<td class="w-75 text-left">
							<html:text name="viewBean" property="tihouNm" styleClass="w-75 textbox-m" readonly="true"/>
							</td>
						</tr>
						
						<tr>
							<td class="w-25 text-right position-kankou">
								<span class="label-title">
									���� 
								</span>
							</td>
							<td class="w-75 text-left">
							
							<logic:notEqual name="viewBean" property="userId" value="<%= logUserId %>">
								<html:textarea name="inputBean" property="setsumei" styleClass="textbox-l-ks margin-15 re-none" readonly="true"/>
							</logic:notEqual>
							
							<logic:equal name="viewBean" property="userId" value="<%= logUserId %>">
								<html:textarea name="inputBean" property="setsumei" styleClass="textbox-l-ks margin-15 re-none" readonly="false"/>
							</logic:equal>
							
							</td>
						</tr>
						
						<tr>
							<td class="w-25 text-right position-kankou">
							<span class="label-title">
									���r���[
									</span>
									</td>
							<td class="w-75 text-left">
							
							<logic:notEqual name="viewBean" property="userId" value="<%= logUserId %>">
								<html:textarea name="inputBean" property="review" styleClass="textbox-l-ks margin-15 re-none" readonly="true"/>
							</logic:notEqual>
							
							<logic:equal name="viewBean" property="userId" value="<%= logUserId %>">
								<html:textarea name="inputBean" property="review" styleClass="textbox-l-ks margin-15 re-none" readonly="false"/>
							</logic:equal>
							</td>
						</tr>
						 <tr>
			              <td class="w-25 text-right p-right33">
			                <span class="label-title">
			                  �]���l
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
				<div class="absoluteRelativeimg">
				    <img src="<%=request.getContextPath()%>/img/chara_sit_arm.png" class="charasitArm-img">
					<img src="<%=request.getContextPath()%>/img/chara_sit.png" class="charasit-img">
					<img src="<%=request.getContextPath()%>/img/chara_udeage.png" class="charaudeage-img">
					<img src="<%=request.getContextPath()%>/img/star.png" class="rollingstar-img">
				
				</div>
				<div class="block-center">
					<logic:equal name="viewBean" property="hyokaJudge" value="0">
    					<html:button property="insert" styleClass="btn-blue btn-size-m" onclick="callAction(this.form, 'insert');">
        					�o�^
    					</html:button>
					</logic:equal>

					<logic:equal name="viewBean" property="hyokaJudge" value="1">
    					<html:button property="insert" styleClass="btn-blue btn-size-m" onclick="callAction(this.form, 'update');">
        					�X�V
    					</html:button>
					</logic:equal>
				</div>
				<logic:equal name="viewBean" property="userId" value="<%= logUserId %>">
   					 <html:button property="insert" styleClass="btn-blue btn-size-m top-10" onclick="callAction(this.form, 'delete');">
        				�폜
    				</html:button>
				</logic:equal>


				<jsp:include page="/jsp/common/footerUpdDel.jsp" />
			</div>
		</html:form>
	</div>

<script type="text/javascript">
document.getElementById('profileImage').addEventListener('change', function(event) {
    var file = event.target.files[0];
    if (file) {
        var reader = new FileReader();
        reader.onload = function(e) {
            //�G���R�[�f�B���O���ꂽ�摜�f�[�^���B���t�B�[���h�ɐݒ�
            document.kankouUpdDelForm.base64Image.value = e.target.result;
            //�v���r���[�摜���X�V
            document.getElementById('preview').src = e.target.result;
        }
        reader.readAsDataURL(file);
    }
});

function resetImage() {
    // �v���r���[�摜�����̉摜�p�X�ɖ߂�
    document.getElementById('preview').src ="<%=request.getContextPath()%>/img/kankouti/<bean:write name="viewBean" property="imagePath"/>" ;
    // �B���t�B�[���h�̒l�����Z�b�g
    document.kankouUpdDelForm.base64Image.value = '';
}

</script>
</body>
</html:html>