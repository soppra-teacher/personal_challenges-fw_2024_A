package cashbook.service.common;
import java.util.Map;

import cashbook.dto.common.LoginDto;
import cashbook.exception.CommonValidateException;

public interface LoginService {
	
	/**
	 * ログイン実行メソッド
	 * @param formMap 画面項目
	 * @return LoginDto ログインDTO
	 * @throws CommonValidateException 
	 */
	public LoginDto execute(Map<String, Object> formMap) throws CommonValidateException;

}