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
	var selected = new Boolean(false);
	if (operation == "insert") {
		msg = "�o�^���Ă���낵���ł����H";
	} 
	if (msg != null && !confirm(msg)) {
		return;
	}
	form.submit();
}
