package cashbook.dao.common;

import java.util.Map;

/**
 * ログインDAOクラス
 * @author soppra
 */
public interface LoginDao {

	/**
	 * ログイン情報を取得する
	 * @param formMap フォーム項目
	 * @return ログイン情報
	 */
	public Map<String, String> findLogin(Map<String, Object> formMap);
	
	/**
	 * ユーザID・パスワードが一致しているかチェックする
	 * @param formMap フォーム項目
	 * @return ログイン情報
	 */
	public boolean checkLogin(Map<String, Object> formMap);
}
