package cashbook.dao.common;

import java.util.Map;

/**
 * ユーザ登録DAOインターフェース
 * @author soppra 
 */
public interface UserRegistDao{
	
	/**
	 * <p><b>
	 * ユーザ登録画面
	 * <br>登録処理
	 * </b></p>
	 * @param formMap フォーム項目
	 */
	public void registUser(Map<String, Object> formMap);
	
	/**
	 * <p><b>
	 * ユーザ登録画面
	 * <br>重複チェック
	 * </b></p>
	 * @param formMap フォーム項目
	 * @return true : 重複なし, false : 重複あり
	 */
	public boolean checkOverlapUserRegist(Map<String, Object> formMap);
}
