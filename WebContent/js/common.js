
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

    form.submit();
}
