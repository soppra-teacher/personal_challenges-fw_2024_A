package cashbook.dao.setai;

import java.util.List;
import java.util.Map;

import cashbook.dto.common.LoginDto;

/**
 * 世帯マスタDAOインターフェース
 * @author soppra
 *
 */
public interface SetaiDao {

	/**
	 * 世帯マスタ一覧を検索する
	 * @param formMap
	 * @return
	 */
	public List<Map<String, String>> searchSetai(Map<String, Object> formMap);

	/**
	 * 世帯マスタを削除する
	 * @param himokuCd
	 * @param loginDto
	 */
	public void deleteSetai(String himokuCd, LoginDto loginDto);

	/**
	 * 世帯マスタを検索する
	 * @param formMap
	 * @return
	 */
	public Map<String, String> findSetai(Map<String, Object> formMap);

	/**
	 * 世帯マスタを登録する
	 * @param formMap
	 * @param loginDto
	 */
	public void registSetai(Map<String, Object> formMap, LoginDto loginDto);

	/**
	 * 世帯マスタを更新する
	 * @param formMap
	 * @param loginDto
	 */
	public void updateSetai(Map<String, Object> formMap, LoginDto loginDto);

	/**
	 * 重複チェック
	 * @param formMap
	 * @return
	 */
	public boolean checkOverlapSetai(Map<String, Object> formMap);

	/**
	 * 行ロック及び、排他チェック
	 * @param formMap
	 * @return
	 */
	public boolean lockSetai(Map<String, Object> formMap);

	/**
	 * 世帯主フラグ確認
	 * @param formMap
	 * @return
	 */
	public boolean checkSetainushiFlg(Map<String, Object> formMap);

	/**
	 * 個人存在チェック
	 * @param setaiId
	 * @return
	 */
	public boolean isKojinExist(String setaiId);

	/**
	 * セレクトボックス用世帯マスタ取得
	 * @return 世帯マスタ
	 */
	public Map<String, String> searchSelectboxSetai();

}
