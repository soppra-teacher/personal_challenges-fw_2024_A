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
	 * ログイン実行メソッド
	 * @param loginService
	 */
	public LoginDto execute(Map<String, Object> formMap){
		LoginDto result = new LoginDto();
		Map<String, String> map = loginDao.find(formMap);
		result.setUserId(map.get("USER_ID"));
		result.setPass(map.get("PASS"));
		return result;
	}

	/**
	 * DAOのsetter
	 * @param loginDao
	 */
	public void setLoginDao(LoginDao loginDao){
		this.loginDao = loginDao;
	}

}