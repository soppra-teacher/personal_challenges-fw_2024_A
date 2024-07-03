package cashbook.service.kankou;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
	public void registIns(Map<String, Object> formMap, LoginDto loginDto, HttpServletRequest request) throws Exception;
}
