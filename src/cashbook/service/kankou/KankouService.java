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
	public KankouRegistDto registInit(Map<String, Object> formMap);

	/**
	 *
	 * @param formMap
	 * @param loginDto
	 * @throws Exception
	 */
	public void registInsUpd(Map<String, Object> formMap, LoginDto loginDto) throws Exception;
}
