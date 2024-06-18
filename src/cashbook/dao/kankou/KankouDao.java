package cashbook.dao.kankou;

import java.util.List;
import java.util.Map;

import cashbook.dto.common.LoginDto;

public interface KankouDao {
	/**
	 * 個人マスタ一覧を検索する
	 * @param formMap フォーム項目
	 * @return 個人マスタ一覧
	 */
	public List<Map<String, String>> searchKojin(Map<String, Object> formMap);

	/**
	 * 個人マスタを削除する
	 * @param kojinId
	 * @param loginDto
	 */
	public void deleteKojin(String kojinId, LoginDto loginDto);

	/**
	 * 個人マスタを検索する
	 * @param formMap
	 * @return
	 */
	public Map<String, String> findKojin(Map<String, Object> formMap);

	/**
	 * 世帯主フラグ確認
	 * @param formMap
	 * @return
	 */
	public boolean checkSetainushiFlg(Map<String, Object> formMap);

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
	 * 個人マスタを更新する
	 * @param formMap
	 * @param loginDto
	 */
	public void updateKojin(Map<String, Object> formMap, LoginDto loginDto);

	/**
	 * 重複チェック
	 * @param formMap
	 * @return
	 */
	public boolean checkOverlapKankou(Map<String, Object> formMap);

	/**
	 * 行ロック及び、排他チェック
	 * @param formMap
	 * @return
	 */
	public boolean lockKojin(Map<String, Object> formMap);
	
	

}

