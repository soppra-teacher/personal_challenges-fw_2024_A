package cashbook.service.common;
import java.util.Map;

/**
 * ログインサービス
 * @author soppra
 */
import cashbook.dto.common.LoginDto;
public interface LoginService {
	
	/**
	 * ログイン実行メソッド
	 * @param formMap 画面項目
	 * @return LoginDto ログインDTO
	 */
	public LoginDto execute(Map<String, Object> formMap);

}