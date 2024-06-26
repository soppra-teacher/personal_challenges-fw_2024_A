package cashbook.service.common;

import java.util.Map;

import cashbook.dao.common.LoginDao;
import cashbook.dto.common.LoginDto;

/**
 * ログインサービス
 * @author soppra
 */
public class LoginServiceImpl implements LoginService{
	private LoginDao loginDao;
	
	/**
	 * DAOのsetter
	 * @param loginDao
	 */
	public void setLoginDao(LoginDao loginDao){
		this.loginDao = loginDao;
	}
	
	/**
	 * ログイン実行メソッド
	 * @param formMap 画面項目
	 * @return LoginDto ログインDTO
	 */
	public LoginDto execute(Map<String, Object> formMap){
		//ログイン情報の取得
		LoginDto result = new LoginDto();
		Map<String, String> map = loginDao.findLogin(formMap);
		result.setUserId(map.get("USER_ID"));
		result.setPass(map.get("PASS"));
		return result;
	}

}