package cashbook.service.himoku;
import java.util.Map;

import cashbook.dto.common.LoginDto;
import cashbook.dto.himoku.HimokuListDto;
import cashbook.dto.himoku.HimokuRegistDto;

/**
 * 費目マスタサービス
 * @author soppra
 */
public interface HimokuService {

	/**
	 * <p><b>
	 * 費目マスタメンテ画面
	 * <br>初期表示処理
	 * </b></p>
	 * @return HimokuListDto 費目マスタメンテDTO
	 */
	public HimokuListDto listInit();

	/**
	 * <p><b>
	 * 費目マスタメンテ画面
	 * <br>検索処理
	 * </b></p>
	 * @param  formMap       画面項目
	 * @return HimokuListDto 費目マスタメンテDTO
	 */
	public HimokuListDto listSearch(Map<String, Object> formMap);

	/**
	 * <p><b>
	 * 費目マスタメンテ画面
	 * <br>削除処理
	 * </b></p>
	 * @param formMap  画面項目
	 * @param loginDto ログイン情報DTO
	 */
	public void listDelete(Map<String, Object> formMap, LoginDto loginDto);

	/**
	 * <p><b>
	 * 費目マスタ登録画面
	 * <br>検索処理
	 * </b></p>
	 * @param  formMap       画面項目
	 * @return HimokuListDto 費目マスタメンテDTO
	 */
	public HimokuRegistDto registInit(Map<String, Object> formMap);

	/**
	 * <p><b>
	 * 費目マスタ登録画面
	 * <br>登録・更新処理
	 * </b></p>
	 * @param formMap  画面項目
	 * @param loginDto ログイン情報DTO
	 */
	public void registInsUpd(Map<String, Object> formMap, LoginDto loginDto) throws Exception;

}