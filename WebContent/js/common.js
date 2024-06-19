/**
 * 削除ボタン制御メソッド
 * @returns
 */
function setDeleteButton() {
	if (window.document.forms[0].checkDel == undefined) {
		window.document.forms[0].delete.disabled = true;
	}
}

/**
 * アクションを呼び出す共通メソッド
 * @param form
 * @param operation
 * @returns
 */

function callAction(form, operation) {
    form.operation.value = operation;
    var msg = null;

    if (operation == "insert") {
        msg = "登録してもよろしいですか？";
    } else if (operation == "update") {
        msg = "更新してもよろしいですか？";
    } else if (operation == "delete") {
        msg = "削除してもよろしいですか？";
    }

    if (msg != null && !confirm(msg)) {
        return;
    }

       // inputBean.kankouNmの値を取得
  var kankouNmElements = document.getElementsByName("kankouNm");
    if (kankouNmElements.length > 0) {
        var kankouNm = kankouNmElements[0].value;
        form.hiddenKankouNm.value = kankouNm;
    } 
    
      // inputBean.UserIDの値を取得
    var userIdNmElements = document.getElementsByName("userId");
    if (userIdNmElements.length >0) {
        var userId = userIdNmElements[0].value;
        form.hiddenUserId.value = userId;
    }
    
          // inputBean.categoryKeyの値を取得
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
