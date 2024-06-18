package cashbook.service.kankou;

import java.util.Map;

import cashbook.dto.common.LoginDto;
import cashbook.dto.kankou.KankouRegistDto;
import cashbook.dto.kojin.KojinListDto;

public interface KankouService {

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
	public KankouRegistDto registInit(Map<String, Object> formMap);

	/**
	 *
	 * @param formMap
	 * @param loginDto
	 * @throws Exception
	 */
	public void registIns(Map<String, Object> formMap, LoginDto loginDto) throws Exception;
}
