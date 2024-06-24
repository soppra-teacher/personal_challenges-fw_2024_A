package cashbook.dao.common;

import java.util.Map;

/**
 * 共通DAOクラス
 * @author soppra
 */
public interface CommonDao {
	
	/**
	 * セレクトボックス用都道府県マスタ取得
	 * @return 都道府県マスタ
	 */
	public Map<String, String> searchSelectboxTodouhuKen();
	
	/**
	 * セレクトボックス用カテゴリマスタ取得
	 * @return カテゴリマスタ
	 */
	public Map<String, String> searchSelectboxCategory();
}
