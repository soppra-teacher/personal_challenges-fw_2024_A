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
    ソプブーのマネーノート　個人マスタ登録
  </title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/common.css" />
<script language="JavaScript" type="text/javascript" charset="shift_jis" src="js/common.js" ></script>
<script type="text/javascript">
</script>
<!-- フォーカスセット -->
<script type="text/javascript">
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
</script>
</head>

<body>

  <bean:define id="inputBean" name="kojinRegistForm" />
  <bean:define id="viewBean" name="KOJIN_REGIST_DTO" />
  <bean:define id="backAction" name="KOJIN_REGIST_BACK" type="java.lang.String" />

  <div class="base-width text-center">

    <html:form action="/KojinRegistDisp" focus="kojinId">

      <jsp:include page="/jsp/common/header.jsp">
        <jsp:param name="screenTitle" value="個人マスタ登録"/>
      </jsp:include>

      <html:hidden property="operation" value="" />
      <html:hidden name="viewBean" property="revision" />

      <div id="contents">
        <html:messages id="msg" message="true">
          <p class="msg-info">
            ・<bean:write name="msg" ignore="true" filter="false"/>
          </p>
        </html:messages>

        <html:messages id="msg" message="false">
          <p class="msg-err">
            ・<bean:write name="msg" ignore="true" filter="false"/>
          </p>
        </html:messages>

        <div>
          <table class="layout-table w-100">

            <tr>
              <td class="w-25 text-right">
                <span class="label-title">
                  個人ＩＤ
                </span>
              </td>
              <td class="w-75 text-left">
                <logic:empty name="viewBean" property="revision">
                  <html:text name="inputBean" property="kojinId" styleClass="input-text-s" />
                </logic:empty>
                <logic:notEmpty name="viewBean" property="revision">
                  <html:text name="inputBean" property="kojinId" styleClass="input-text-s" readonly="true" />
                </logic:notEmpty>
              </td>
            </tr>

            <tr>
              <td class="w-25 text-right">
                <span class="label-title">
                  パスワード
                </span>
              </td>
              <td class="w-75 text-left">
                <html:password name="inputBean" property="pass" styleClass="input-text-m" />
              </td>
            </tr>
            <tr>
              <td class="w-25 text-right">
                <span class="label-title">
                  世帯名
                </span>
              </td>
              <td class="w-75 text-left">
                <html:select name="inputBean" property="setaiNmKey" styleClass="input-select-xl" >
                  <html:optionsCollection name="viewBean" property="setaiNm" value="key" label="value" />
                </html:select>
              </td>
            </tr>

            <tr>
              <td class="w-25 text-right">
                <span class="label-title">
                  個人名
                </span>
              </td>
              <td class="w-75 text-left">
                <html:text name="inputBean" property="kojinNm" styleClass="input-text-l" />
              </td>
            </tr>

            <tr>
              <td class="w-25 text-right">
                <span class="label-title">
                  個人名ｶﾅ
                </span>
              </td>
              <td class="w-75 text-left">
                <html:text name="inputBean" property="kojinNmkana" styleClass="input-text-l" />
              </td>
            </tr>

            <tr>
              <td class="w-25 text-right">
                <span class="label-title">
                  性別
                </span>
              </td>
              <td class="w-75 text-left">
                <label>
                  <html:radio name="inputBean" property="seibetsuKbn" value ="1" styleClass="input-radio"/>
                  男
                </label>
                <label>
                  <html:radio name="inputBean" property="seibetsuKbn" value ="2" styleClass="input-radio"/>
                  女
                </label>
              </td>
            </tr>

            <tr>
              <td class="w-25 text-right">
                <span class="label-title">
                  続柄
                </span>
              </td>
              <td class="w-75 text-left">
                <html:select name="inputBean" property="zokugaraKey" styleClass="input-select-m">
                  <html:optionsCollection name="viewBean" property="zokugara" value="key" label="value" />
                </html:select>
              </td>
            </tr>

            <tr>
              <td class="w-25 text-right">
                <span class="label-title">
                  世帯主
                </span>
              </td>
              <td class="w-75 text-left">
                <html:checkbox name="inputBean" property="setaiNusiFlg" styleClass="input-check"/>
              </td>
            </tr>

          </table>
        </div>

        <div class="block-center">
          <logic:empty name="viewBean" property="revision">
            <html:button property="insert" styleClass="btn btn-l" onclick="callAction(this.form, 'insert');">
              登録
            </html:button>
          </logic:empty>
          <logic:notEmpty name="viewBean" property="revision">
            <html:button property="insert" styleClass="btn btn-l" onclick="callAction(this.form, 'update');">
              更新
            </html:button>
          </logic:notEmpty>
        </div>

        <div class="block-center">
          <html:button property="back" styleClass="btn btn-l" onclick='<%= "callAction(this.form, \'" + backAction + "\');"%>'>
            戻る
          </html:button>
        </div>

        <jsp:include page="/jsp/common/footer.jsp" />
       </div>
    </html:form>
  </div>
</body>
</html:html>