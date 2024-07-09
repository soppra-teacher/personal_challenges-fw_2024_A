package cashbook.dao.kankou;

import java.util.List;
import java.util.Map;

import cashbook.dto.common.LoginDto;

public interface KankouDao {

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
	 * @return
	 */
	public boolean checkOverlapKankou(Map<String, Object> formMap, LoginDto loginDto);
	
	/**
	 * 観光地情報を一覧を検索する
	 * @return 観光地一覧
	 */
	public List<Map<String, String>> searchKankou(Map<String, Object> formMap);
	
	/**
	 * 観光テーブルを検索する
	 * @return 観光テーブル
	 */
	public Map<String, String> findKankou(Map<String, Object> formMap, LoginDto loginDto);
	
	/**
	 * 観光テーブルを更新する
	 * @param formMap
	 * @param loginDto ログイン情報Dto
	 */
	public void updateKankou(Map<String, Object> formMap, LoginDto loginDto);
	
	/**
	 * 観光テーブルを削除する
	 * @param formMap
	 */
	public void delKankou(Map<String, Object> formMap);
	
	/**
	 * 評価値テーブルを登録する
	 * @param formMap
	 * @param loginDto ログイン情報Dto
	 * @param kankouId 観光Id
	 */
	public void insHyoka(Map<String, Object> formMap, LoginDto loginDto, String kankouId);
	
	/**
	 * 評価値テーブルを更新する
	 * @param formMap
	 * @param loginDto ログイン情報Dto
	 * @param kankouId 観光Id
	 */
	public void updHyoka(Map<String, Object> formMap, LoginDto loginDto, String kankouId);
	
	/**
	 * 評価値テーブルを削除する
	 * @param formMap
	 * @param loginDto ログイン情報Dto
	 * @param kankouId 観光Id
	 */
	public void delHyoka(Map<String, Object> formMap);

}

