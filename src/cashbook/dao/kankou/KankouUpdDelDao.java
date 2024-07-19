package cashbook.dao.kankou;

import java.util.Map;

import cashbook.dto.common.LoginDto;

public interface KankouUpdDelDao {
	
	/**
	 * 観光テーブルを取得する
	 * @param formMap
	 * @param loginDto ログイン情報Dto
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
	 */
	public void delHyoka(Map<String, Object> formMap);

}

