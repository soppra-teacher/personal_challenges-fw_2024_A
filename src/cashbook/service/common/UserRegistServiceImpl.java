package cashbook.service.common;

import java.util.Map;

import cashbook.dao.common.UserRegistDao;
import cashbook.dto.common.UserRegistDto;
import cashbook.exception.CommonValidateException;
import cashbook.util.CommonUtil;
import cashbook.util.Const;
import cashbook.util.UserConst;

/**
 * ユーザ登録サービス
 * @author soppra
 */
public class UserRegistServiceImpl implements UserRegistService{
	
	/** ユーザ登録Dao */
	private UserRegistDao userRegistDao;
	
	/**
	 * ユーザ登録Daoを設定します。
	 * @param userRegistDao ユーザ登録Dao
	 */
	public void setUserRegistDao(UserRegistDao userRegistDao) {
		this.userRegistDao = userRegistDao;
	}
	
	/**
	 * <p><b>
	 * ユーザ登録画面
	 * <br>登録処理
	 * </b></p>
	 * @param formMap  画面項目
	 * @param loginDto ログイン情報DTO
	 * @throws CommonValidateException
	 */
	public void registIns(Map<String, Object> formMap) throws CommonValidateException {
			UserRegistDto result = new UserRegistDto();
			// 入力項目を保持
			result.setHidden(CommonUtil.getStr(formMap.get(UserConst.KEY_USER_HIDDEN)));
			
				// 存在チェック
			if (!userRegistDao.checkOverlapUserRegist(formMap)) {
				// キー重複エラー
				throw new CommonValidateException(Const.MSG_ERRORS_PRIMARY_KEY);
			}
			// パスワード一致チェック
			if (!checkPass(formMap)) {
				// パスワード不一致エラー
				throw new CommonValidateException(Const.MSG_ERRORS_PASS_FUITTI);
			}
			
			// 登録処理
			userRegistDao.registUser(formMap);

	}
	
	/**
	 * ユーザ登録画面登録メソッド
	 * @param  formMap 画面入力項目
	 * @return True:OK, False:NG
	 */
	private boolean checkPass(Map<String, Object> formMap) {
		boolean bRet = false;
		String pass = CommonUtil.getStr(formMap.get(UserConst.KEY_USER_PASS));
		String passKakunin = CommonUtil.getStr(formMap.get(UserConst.KEY_USER_PASS_KAKUNIN));

		if(!(pass.equals(passKakunin))) {
			return bRet;
		}
		return bRet = true;
	}
	
	
	
}
