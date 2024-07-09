package cashbook.service.kankou;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cashbook.dto.common.LoginDto;
import cashbook.dto.kankou.KankouListDto;
import cashbook.dto.kankou.KankouRegistDto;
import cashbook.dto.kankou.KankouUpdDelDto;

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
	
	/**
	 * @param form     フォーム
	 * @param request  リクエスト
	 * @return 
	 */
	public KankouListDto listInit(Map<String, Object> formMap, HttpServletRequest request);
	
	/**
	 *
	 * @param form 　　フォーム
	 * @return 
	 */
	public KankouListDto listSearch(Map<String, Object> formMap);
	
	/**
	 *
	 * @param form　　 フォーム
	 * @param loginDto ログイン情報DTO
	 * @return
	 */
	public KankouUpdDelDto updDelInit(Map<String, Object> formMap, LoginDto loginDto);
	
	/**
	 *評価値テーブルのインサート処理
	 * @param formMap
	 * @param loginDto
	 * @throws Exception
	 */
	public void hyokaIns(Map<String, Object> formMap, LoginDto loginDto) throws Exception;
	
	/**
	 *評価値テーブル・観光テーブルの更新処理
	 * @param formMap
	 * @param loginDto
	 * @throws Exception
	 */
	public void update(Map<String, Object> formMap, LoginDto loginDto, HttpServletRequest request,String kankouId, String hyoka) throws Exception;
	
	public void delete(Map<String, Object> formMap, HttpServletRequest request) throws Exception;
}
