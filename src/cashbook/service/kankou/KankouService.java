package cashbook.service.kankou;

import java.util.Map;

import cashbook.dto.common.LoginDto;
import cashbook.dto.kankou.KankouListDto;
import cashbook.dto.kankou.KankouRegistDto;

/**
 * 個人サービスインターフェース
 * @author soppra
 *
 */
public interface KankouService {

	/**
	 *
	 * @return
	 */
	public KankouListDto listInit(Map<String, Object> formMap);

	/**
	 *
	 * @param formMap
	 * @return
	 */
	public KankouListDto listSearch(Map<String, Object> formMap);

	/**
	 *
	 * @param formMap
	 * @param loginDto
	 */
	public void listDelete(Map<String, Object> formMap, LoginDto loginDto);

	/**
	 *
	 * @param formMap
	 * @return
	 */
	public KankouRegistDto registInit(Map<String, Object> formMap, LoginDto loginDto);

	/**
	 *
	 * @param formMap
	 * @param loginDto
	 * @throws Exception
	 */
	public void registInsUpd(Map<String, Object> formMap, LoginDto loginDto) throws Exception;
	
	
	/**
	 *観光地テーブルのアップデート処理
	 * @param formMap
	 * @param loginDto
	 * @throws Exception
	 */
	public void updInsDel(Map<String, Object> formMap, LoginDto loginDto) throws Exception;
	
	/**
	 *評価値テーブルのインサート処理
	 * @param formMap
	 * @param loginDto
	 * @throws Exception
	 */
	public void hyokaIns(Map<String, Object> formMap, LoginDto loginDto, String kankouId) throws Exception;
	
	/**
	 *評価値テーブル・観光テーブルの更新処理
	 * @param formMap
	 * @param loginDto
	 * @throws Exception
	 */
	public void update(Map<String, Object> formMap, LoginDto loginDto, String kankouId, String hyoka) throws Exception;
	
	/**
	 *評価値の登録・更新・削除後の再表示用
	 * @param formMap
	 */
	public KankouRegistDto valueSet(Map<String, Object> formMap);
}
