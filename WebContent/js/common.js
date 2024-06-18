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
//function callAction(form, operation) {
//	form.operation.value = operation;
//	var msg = null;
//	var selected = new Boolean(false);
//	if (operation == "insert") {
//		msg = "登録してもよろしいですか？";
//	} else if (operation == "update") {
//		msg = "更新してもよろしいですか？";
//	} else if (operation == "delete") {
//		msg = "削除してもよろしいですか？";
//	}
//	if (msg != null && !confirm(msg)) {
//		return;
//	}
//	
//	var kankouNm = document.getElementsByName("inputBean.kankouNm")[0].value;
//	console.log(kankouNm);
//	form.KANKOUnm.value = kankouNm;
//	form.submit();
//}


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
    
//    var kankouNmElements = document.getElementsByName("userId");
//    if (kankouNmElements.length > 0) {
//        var kankouNm = kankouNmElements[0].value;
//        form.hiddenUserId.value = userId;
//    }
//    
//        var kankouNmElements = document.getElementsByName("tihouKey");
//    if (kankouNmElements.length > 0) {
//        var kankouNm = kankouNmElements[0].value;
//        form.hiddenTihouKey.value = tihouKey;
//    }  

    form.submit();
}
