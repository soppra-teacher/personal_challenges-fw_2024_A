package cashbook.dto.common;

/**
 * ログイン情報DTOクラス
 * @author soppra
 */
public class UserRegistDto{
	
	/** ユーザＩＤ */
	private String userId;
	/** パスワード */
	private String pass;
	/** パスワード(確認) */
	private String passKakunin;

	
	/**
	 * ユーザＩＤを取得します。
	 * @return ユーザＩＤ
	 */
	public String getUserId() {
	    return userId;
	}
	/**
	 * ユーザＩＤを設定します。
	 * @param userId ユーザＩＤ
	 */
	public void setUserId(String userId) {
	    this.userId = userId;
	}
	/**
	 * パスワードを取得します。
	 * @return パスワード
	 */
	public String getPass() {
	    return pass;
	}
	/**
	 * パスワードを設定します。
	 * @param pass パスワード
	 */
	public void setPass(String pass) {
	    this.pass = pass;
	}
	
	/**
	 * パスワード(確認)を取得します。
	 * @return パスワード
	 */
	public String getPassKakunin() {
	    return passKakunin;
	}
	/**
	 * パスワード(確認)を設定します。
	 * @param passKakunin パスワード(確認)
	 */
	public void setPassKakunin(String passKakunin) {
	    this.passKakunin = passKakunin;
	}
}