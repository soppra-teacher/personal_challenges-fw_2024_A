package cashbook.dao.shushi;

import java.util.List;
import java.util.Map;

import cashbook.dto.common.LoginDto;

/**
 * 収支DAOインターフェース
 * @author soppra
 *
 */
public interface ShushiDao {

	/**
	 * 収支一覧を検索する
	 * @param formMap
	 * @param loginDto
	 * @return
	 */
	public List<Map<String, String>> listSearch(Map<String, Object> formMap, LoginDto loginDto);

	/**
	 * 収支明細件数取得
	 * @param shushiId
	 * @return
	 */
	public int getShushiDtlCnt(String shushiId);

	/**
	 * 収支を削除する
	 * @param shushiId
	 */
	public void listDelete(String shushiId);

	/**
	 * 収支明細を削除する
	 * @param shushiId
	 * @param shushiDtlId
	 */
	public void listDtlDelete(String shushiId, String shushiDtlId);

	/**
	 * 収支・収支明細を検索する
	 * @param formMap
	 * @return
	 */
	public Map<String, String> registFind(Map<String, Object> formMap);

	/**
	 * 収支を登録する
	 * @param formMap
	 */
	public void registInsert(Map<String, Object> formMap);

	/**
	 * 収支明細を登録する
	 * @param formMap
	 */
	public void registDtlInsert(Map<String, Object> formMap);

	/**
	 * 収支明細を更新する
	 * @param formMap
	 */
	public void registDtlUpdate(Map<String, Object> formMap);

	/**
	 * 行ロック及び、排他チェック
	 * @param formMap
	 * @return
	 */
	public boolean lock(Map<String, Object> formMap);

	/**
	 * 費目名のコンボボックスの値を取得する
	 * @return
	 */
	public Map<String, String> getHimokuNmMap();

	/**
	 * 個人名のコンボボックスの値を取得する
	 * @param loginDto
	 * @return
	 */
	public Map<String, String> getKojinNmMap(LoginDto loginDto);

	/**
	 * 引数.個人ID,年月から収支が登録されている場合、収支明細IDのMAX+1を取得する。
	 * @param formMap
	 * @return
	 */
	public Map<String, String> getMaxShushiDtl(Map<String, Object> formMap);

	/**
	 * シーケンスから収支IDを取得する。
	 * @return
	 */
	public String getNextShushiId();
}
