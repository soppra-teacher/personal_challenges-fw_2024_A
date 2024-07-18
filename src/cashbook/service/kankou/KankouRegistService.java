package cashbook.service.kankou;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cashbook.dto.common.LoginDto;
import cashbook.dto.kankou.KankouRegistDto;

public interface KankouRegistService {

	/**
	 * 都道府県・カテゴリコンボボックスの初期表示処理
	 * @param formMap
	 * @return  都道府県・カテゴリコンボボックスの値
	 */
	public KankouRegistDto registInit(Map<String, Object> formMap);

	/**
	 * 観光地・評価値の登録処理
	 * @param formMap
	 * @param loginDto
	 * @throws Exception
	 */
	public void registIns(Map<String, Object> formMap, LoginDto loginDto, HttpServletRequest reques) throws Exception;
}
