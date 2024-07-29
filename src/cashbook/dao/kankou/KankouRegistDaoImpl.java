package cashbook.dao.kankou;

import java.util.Map;

import cashbook.dao.common.BaseDaoImpl;
import cashbook.dto.common.LoginDto;
import cashbook.util.CommonUtil;
import cashbook.util.Const;
import cashbook.util.KankouRegistConst;

public class KankouRegistDaoImpl extends BaseDaoImpl implements KankouRegistDao {

	/**
	 * 観光地情報を登録する
	 * @param Map<String, Object> formMap
	 * @param  LoginDto loginDto
	 */
	public void registKankou(Map<String, Object> formMap, LoginDto loginDto) {

		StringBuffer sql = new StringBuffer();
		sql.append(" INSERT INTO TBL_KANKOU  ( ");
		sql.append("   KANKOU_ID ");
		sql.append("   , USER_ID ");
		sql.append("   , KEN_CD ");
		sql.append("   , CATEGORY_ID ");
		sql.append("   , KANKOU_NM ");
		sql.append("   , SETSUMEI ");
		sql.append("   , REVIEW ");
		sql.append("   , FILE_NM ");
		sql.append(" ) VALUES ( ");
		sql.append("    '").append(formMap.get(KankouRegistConst.KEY_KANKOU_ID)).append("' ");
		sql.append("   , '").append(loginDto.getUserId()).append("' ");
		sql.append("   , '").append(formMap.get(KankouRegistConst.KEY_TODOUFUKEN_KEY)).append("' ");
		sql.append("   , '").append(formMap.get(KankouRegistConst.KEY_CATEGORY_KEY)).append("' ");
		sql.append("   , '").append(formMap.get(KankouRegistConst.KEY_KANKOU_NM)).append("' ");
		sql.append("   , '").append(formMap.get(KankouRegistConst.KEY_SETSUMEI)).append("' ");
		sql.append("   , '").append(formMap.get(KankouRegistConst.KEY_REVIEW)).append("' ");
		if(CommonUtil.isNull((String)formMap.get(Const.KEY_IMAGE_STRING))) {
			sql.append("   , '").append("' ");
		}else {
			//観光IDのマックス値＋1の値.jpegを写真の名前として追加 
			sql.append("   , '").append(formMap.get(KankouRegistConst.KEY_KANKOU_ID) + Const.IMAGE_PNG).append("' ");
		}
		sql.append(" ) ");

		super.update(sql.toString());
	}
	
	/**
	 * 評価値を登録する
	 * @param Map<String, Object> formMap
	 * @param LoginDto loginDto
	 */
	public void registHyoka(Map<String, Object> formMap, LoginDto loginDto) {

		StringBuffer sql = new StringBuffer();
		sql.append(" INSERT INTO TBL_HYOUKATI ( ");
		sql.append("   KANKOU_ID ");
		sql.append("   , USER_ID ");
		sql.append("   , HYOUKATI ");
		sql.append(" ) VALUES ( ");
		sql.append("    '").append(formMap.get(KankouRegistConst.KEY_KANKOU_ID)).append("' ");
		sql.append("   , '").append(loginDto.getUserId()).append("' ");
		sql.append("   , '").append(formMap.get(KankouRegistConst.KEY_HYOKA)).append("' ");
		sql.append(" ) ");
		super.update(sql.toString());
	}


	/**
	 * 重複チェック
	 * @param Map<String, Object> formMap
	 * @param LoginDto loginDto
	 * @return true：正常、false：重複エラー
	 */
	public boolean checkOverlapKankou(Map<String, Object> formMap, LoginDto loginDto) {

		Map<String, String> result;
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT COUNT(*) AS COUNT  ");
		sql.append("  FROM TBL_KANKOU ");
		sql.append(" WHERE USER_ID = '").append(loginDto.getUserId()).append("' ");
		sql.append(" AND  KEN_CD = '").append(formMap.get(KankouRegistConst.KEY_TODOUFUKEN_KEY)).append("' ");
		sql.append(" AND  KANKOU_NM = '").append(formMap.get(KankouRegistConst.KEY_KANKOU_NM)).append("' ");
		
		//SQLの結果をresultに格納する。
		result = super.find(sql.toString());
		
		if(result.get("COUNT").equals("0")) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * テーブルロック
	 */
	public void lockKankou() {

		StringBuffer sql = new StringBuffer();
		sql.append(" LOCK TABLE TBL_KANKOU ");
		sql.append("  IN EXCLUSIVE MODE ");
		sql.append("  NOWAIT ");
		super.update(sql.toString());
	}
	
	/**
	 * 観光地IDの最大値を取得
	 * @return 観光地IDの最大値 + 1 の値
	 */
	public String getMaxKankou(){
		
		Map<String, String> result;
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT COALESCE(MAX(KANKOU_ID), 0) + 1 AS MAX_KANKOUID ");
		sql.append("  FROM TBL_KANKOU ");

		//SQLの結果をresultに格納する。
		result = super.find(sql.toString());
		
		return result.get("MAX_KANKOUID");
	}
}
