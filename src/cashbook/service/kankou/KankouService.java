package cashbook.service.kankou;

import java.util.Map;

import cashbook.dto.common.LoginDto;
import cashbook.dto.kankou.KankouRegistDto;

public interface KankouService {

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
