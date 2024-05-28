package cashbook.service.kojin;

import java.util.Map;

import cashbook.dto.common.LoginDto;
import cashbook.dto.kojin.KojinListDto;
import cashbook.dto.kojin.KojinRegistDto;

/**
 * 個人サービスインターフェース
 * @author soppra
 *
 */
public interface KojinService {

	/**
	 *
	 * @return
	 */
	public KojinListDto listInit();

	/**
	 *
	 * @param formMap
	 * @return
	 */
	public KojinListDto listSearch(Map<String, Object> formMap);

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
	public KojinRegistDto registInit(Map<String, Object> formMap);

	/**
	 *
	 * @param formMap
	 * @param loginDto
	 * @throws Exception
	 */
	public void registInsUpd(Map<String, Object> formMap, LoginDto loginDto) throws Exception;
}
