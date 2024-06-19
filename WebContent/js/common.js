/**
 * �폜�{�^�����䃁�\�b�h
 * @returns
 */
function setDeleteButton() {
	if (window.document.forms[0].checkDel == undefined) {
		window.document.forms[0].delete.disabled = true;
	}
}

/**
 * �A�N�V�������Ăяo�����ʃ��\�b�h
 * @param form
 * @param operation
 * @returns
 */

function callAction(form, operation) {
    form.operation.value = operation;
    var msg = null;

    if (operation == "insert") {
        msg = "�o�^���Ă���낵���ł����H";
    } else if (operation == "update") {
        msg = "�X�V���Ă���낵���ł����H";
    } else if (operation == "delete") {
        msg = "�폜���Ă���낵���ł����H";
    }

    if (msg != null && !confirm(msg)) {
        return;
    }

       // inputBean.kankouNm�̒l���擾
  var kankouNmElements = document.getElementsByName("kankouNm");
    if (kankouNmElements.length > 0) {
        var kankouNm = kankouNmElements[0].value;
        form.hiddenKankouNm.value = kankouNm;
    } 
    
      // inputBean.UserID�̒l���擾
    var userIdNmElements = document.getElementsByName("userId");
    if (userIdNmElements.length >0) {
        var userId = userIdNmElements[0].value;
        form.hiddenUserId.value = userId;
    }
    
          // inputBean.categoryKey�̒l���擾
    var categoryKeyElements = document.getElementsByName("categoryKey");
    if (categoryKeyElements.length >0) {
        var categoryKey = categoryKeyElements[0].value;
        form.hiddenCategoryKey.value = categoryKey;
    }
    
    var tihouKeyElements = document.getElementsByName("tihouKey");
    if (tihouKeyElements.length > 0) {
        var tihouKey = tihouKeyElements[0].value;
        form.hiddenTihouKey.value = tihouKey;
    } 
    
    var todouhukenKeyElements = document.getElementsByName("todouhukenKey");
    if (todouhukenKeyElements.length > 0) {
        var todouhukenKey = todouhukenKeyElements[0].value;
        form.hiddenTodouhukenKey.value = todouhukenKey;
    } 

    form.submit();
}
