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
		result.setKojinId(map.get("KOJIN_ID"));
		result.setSetaiId(map.get("SETAI_ID"));
		result.setKojinNm(map.get("KOJIN_NM"));
		result.setSetainushiFlg(map.get("SETAINUSHI_FLG"));
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