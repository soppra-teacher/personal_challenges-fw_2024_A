package cashbook.service.kankou;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cashbook.dto.kankou.KankouListDto;

public interface KankouListService {

	/**
	 * 検索一覧表示画面の初期表示処理
	 * @param form     フォーム
	 * @param request  リクエスト
	 * @return KankouListDto　観光検索画面Dto
	 */
	public KankouListDto listInit(Map<String, Object> formMap, HttpServletRequest request);
	
	/**
	 *検索一覧表示画面の検索処理
	 * @param form     フォーム
	 * @return KankouListDto  観光検索画面Dto
	 */
	public KankouListDto listSearch(Map<String, Object> formMap);
	
}
