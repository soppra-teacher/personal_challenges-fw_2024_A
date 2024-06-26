package cashbook.service.common;

import java.util.Map;

public interface UserRegistService {

	/** 
	 * <p><b>
	 * ユーザ登録画面
	 * <br>登録処理
	 * </b></p>
	 * @param formMap  画面項目
	 * @param loginDto ログイン情報DTO
	 */
	public void registIns(Map<String, Object> formMap) throws Exception;

}
