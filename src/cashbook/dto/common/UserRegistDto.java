package cashbook.dto.common;

/**
 * ユーザ登録情報DTOクラス
 * @author soppra
 */
public class UserRegistDto{
	
	/** ユーザＩＤ */
	private String userId;
	/** パスワード */
	private String pass;
	/** パスワード(確認) */
	private String passKakunin;
	/** 登録判定 */
	private String hidden;
	
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
	
	/**
	 * 登録判定を取得します。
	 * @return 登録判定
	 */
	public String getHidden() {
	    return hidden;
	}
	/**
	 * 登録判定を設定します。
	 * @param hidden 登録判定
	 */
	public void setHidden(String hidden) {
	    this.hidden = hidden;
	}
}