package cashbook.dao.kankou;

import java.util.Map;

import cashbook.dto.common.LoginDto;

public interface KankouRegistDao {

	/**
	 * 観光地情報を登録する
	 * @param formMap
	 * @param loginDto
	 */
	public void registKankou(Map<String, Object> formMap, LoginDto loginDto);
	
	/**
	 * 評価値を登録する
	 * @param formMap
	 * @param loginDto
	 */
	public void registHyoka(Map<String, Object> formMap, LoginDto loginDto);

	/**
	 * 重複チェック
	 * @param formMap
	 * @param loginDto
	 * @return true：重複あり、false：重複なし
	 */
	public boolean checkOverlapKankou(Map<String, Object> formMap, LoginDto loginDto);
	
	/**
	 * テーブルロック及び、排他チェック
	 */
	public void lockKankou();
	
	/**
	 * 観光地IDのMAX値を取得
	 * @return 観光地テーブルの観光地IDの最大値 + 1
	 */
	public String getMaxKankou();
	
	

}

