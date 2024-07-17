package cashbook.service.kankou;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cashbook.dto.common.LoginDto;
import cashbook.dto.kankou.KankouUpdDelDto;

public interface KankouUpdDelService {
	
	/**
	 *更新削除画面の初期表示処理
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
	public void hyokaIns(Map<String, Object> formMap, LoginDto loginDto,String kankouId) throws Exception;
	
	/**
	 *評価値テーブル・観光テーブルの更新処理
	 * @param formMap
	 * @param loginDto
	 * @throws Exception
	 */
	public void update(Map<String, Object> formMap, LoginDto loginDto, HttpServletRequest request,String kankouId, String hyoka) throws Exception;
	
	/**
	 *評価値テーブル・観光テーブルの削除処理
	 * @param formMap
	 * @throws Exception
	 */
	public void delete(Map<String, Object> formMap, HttpServletRequest request) throws Exception;
}
