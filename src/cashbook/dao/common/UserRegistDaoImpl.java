package cashbook.dao.common;

import java.util.Map;

import cashbook.util.UserConst;

public class UserRegistDaoImpl extends BaseDaoImpl implements UserRegistDao{
	
	/**
	 * <p><b>
	 * ユーザ登録画面
	 * <br>登録処理
	 * </b></p>
	 * @param フォーム項目
	 * @param ログイン情報DTO
	 */
	public void registUser(Map<String, Object> formMap) {

		// 登録用のSQLを組み立てる。
		StringBuffer sql = new StringBuffer();
		sql.append(" INSERT INTO MST_USER M1 ( ");
		sql.append("     M1.USER_ID ");
		sql.append("   , M1.PASS ");
		sql.append(" ) VALUES ( ");
		sql.append("     '").append(formMap.get(UserConst.KEY_USER_ID)).append("' ");
		sql.append("   , '").append(formMap.get(UserConst.KEY_USER_PASS)).append("' ");
		sql.append(" ) ");

		// 組み立てたSQLで登録処理を行う。
		super.update(sql.toString());
	}
	/**
	 * <p><b>
	 * ユーザ登録画面
	 * <br>重複チェック
	 * </b></p>
	 * @param フォーム項目
	 */
	
	public boolean checkOverlapUserRegist(Map<String, Object> formMap) {

		// 検索用のSQLを組み立てる。
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT M1.USER_ID ");
		sql.append("  FROM MST_USER M1 ");
		sql.append(" WHERE M1.USER_ID = '").append(formMap.get(UserConst.KEY_USER_ID)).append("' ");
		sql.append("   AND ROWNUM = 1 ");

		// 組み立てたSQLで検索処理を行う。
		return super.find(sql.toString()).size() == 0;
	}
	
	
}
