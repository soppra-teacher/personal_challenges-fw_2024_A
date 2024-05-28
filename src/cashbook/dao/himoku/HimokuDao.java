package cashbook.dao.himoku;

import java.util.List;
import java.util.Map;

import cashbook.dto.common.LoginDto;

/**
 * 費目マスタDAOインターフェース
 * @author soppra
 */
public interface HimokuDao {

	/**
	 * <p><b>
	 * 費目マスタメンテ画面
	 * <br>検索処理
	 * </b></p>
	 * @param formMap フォーム項目
	 * @return List<Map<String, String>> 検索結果
	 */
	public List<Map<String, String>> searchHimokuList(Map<String, Object> formMap);

	/**
	 * <p><b>
	 * 費目マスタメンテ画面
	 * <br>削除処理(論理削除)
	 * </b></p>
	 * @param himokuCd 費目コード
	 * @param loginDto ログイン情報DTO
	 */
	public void deleteHimoku(String himokuCd, LoginDto loginDto);

	/**
	 * <p><b>
	 * 費目マスタ登録画面
	 * <br>検索処理
	 * </b></p>
	 * @param formMap フォーム項目
	 * @return Map<String, String> 検索結果
	 */
	public Map<String, String> findHimoku(Map<String, Object> formMap);

	/**
	 * <p><b>
	 * 費目マスタ登録画面
	 * <br>登録処理
	 * </b></p>
	 * @param フォーム項目
	 * @param ログイン情報DTO
	 */
	public void registHimoku(Map<String, Object> formMap, LoginDto loginDto);

	/**
	 * <p><b>
	 * 費目マスタ登録画面
	 * <br>更新処理
	 * </b></p>
	 * @param フォーム項目
	 * @param ログイン情報DTO
	 */
	public void updateHimoku(Map<String, Object> formMap, LoginDto loginDto);

	/**
	 * <p><b>
	 * 費目マスタ登録画面
	 * <br>重複チェック
	 * </b></p>
	 * @param フォーム項目
	 */
	public boolean checkOverlapHimoku(Map<String, Object> formMap);

	/**
	 * 行ロック及び、排他チェック
	 * @param formMap フォーム項目
	 * @return true：正常、false：排他エラー
	 */
	public boolean lockHimoku(Map<String, Object> formMap);
}
