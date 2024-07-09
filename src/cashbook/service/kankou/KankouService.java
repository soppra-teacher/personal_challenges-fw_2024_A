package cashbook.service.kankou;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cashbook.dto.common.LoginDto;
import cashbook.dto.kankou.KankouRegistDto;

public interface KankouService {

	/**
	 * 観光地登録画面初期表示メソッド
	 * @param formMap
	 * @return 都道府県・カテゴリコンボボックスの初期値
	 */
	public KankouRegistDto registInit(Map<String, Object> formMap);

	/**
	 * 観光地登録メソッド
	 * @param formMap
	 * @param loginDto
	 * @throws Exception
	 */
	public void registIns(Map<String, Object> formMap, LoginDto loginDto, HttpServletRequest request) throws Exception;
}
