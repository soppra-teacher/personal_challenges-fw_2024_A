package cashbook.dao.kankou;

import java.util.List;
import java.util.Map;

import cashbook.dto.common.LoginDto;

/**
 * 個人マスタDAOインターフェース
 * @author soppra
 */
public interface KankouDao {


	/**
	 * 観光地一覧を検索する
	 * @return 観光地検索一覧
	 */
	public List<Map<String, String>> searchKankou(Map<String, Object> formMap);
	
	/**
	 * 観光テーブルを検索する
	 * @return 観光テーブル
	 */
	public Map<String, String> findKankou(Map<String, Object> formMap, LoginDto loginDto);

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
	public boolean checkOverlapKojin(Map<String, Object> formMap);

	/**
	 * 行ロック及び、排他チェック
	 * @param formMap
	 * @return
	 */
	public boolean lockKojin(Map<String, Object> formMap);
	
	/**
	 * 観光地テーブルを更新する
	 * @param formMap
	 * @param loginDto
	 */
	public void updateKankou(Map<String, Object> formMap, LoginDto loginDto);
	
	/**
	 * 評価値テーブルを更新する
	 * @param formMap
	 * @param loginDto
	 */
	public void insHyoka(Map<String, Object> formMap, LoginDto loginDto);
	
	

}
