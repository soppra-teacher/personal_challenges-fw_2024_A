package cashbook.exception;

public class CommonValidateException extends Exception {

	private static final long serialVersionUID = 1L;

	private String messageKey;

	public CommonValidateException(String messageKey) {
		super("例外が発生しました");
		this.messageKey = messageKey;
	}

	public String getMessageKey() {
		return messageKey;
	}
}
