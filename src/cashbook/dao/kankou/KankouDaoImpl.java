package cashbook.dao.kankou;

import java.util.Map;

import cashbook.dao.common.BaseDaoImpl;
import cashbook.dto.common.LoginDto;
import cashbook.util.CommonUtil;
import cashbook.util.KankouConst;

public class KankouDaoImpl extends BaseDaoImpl implements KankouDao {

	/**
	 * 観光地情報を登録する
	 * @param Map<String, Object> formMap
	 * @param  LoginDto loginDto
	 * @throws Exception
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
		sql.append("    '").append(formMap.get(KankouConst.KEY_ID)).append("' ");
		sql.append("   , '").append(loginDto.getUserId()).append("' ");
		sql.append("   , '").append(formMap.get(KankouConst.KEY_TODOUFUKEN_KEY)).append("' ");
		sql.append("   , '").append(formMap.get(KankouConst.KEY_CATEGORY_KEY)).append("' ");
		sql.append("   , '").append(formMap.get(KankouConst.KEY_KANKOU_NM)).append("' ");
		sql.append("   , '").append(formMap.get(KankouConst.KEY_SETSUMEI)).append("' ");
		sql.append("   , '").append(formMap.get(KankouConst.KEY_REVIEW)).append("' ");
		//写真だけ、現在は任意の値を入力
		if (!CommonUtil.isNull(CommonUtil.getStr(formMap.get(KankouConst.KEY_ENCODINGIMAGE)))) {
			//観光IDのマックス値＋1の値を写真の名前として追加 
			sql.append("   , '").append(formMap.get(KankouConst.KEY_ID) + ".jpeg").append("' ");
			sql.append(" ) ");
		}else {
			sql.append("   , 'testpicture.jpeg'");
			sql.append(" ) ");
		}
		

		super.update(sql.toString());
	}
	
	/**
	 * 評価値を登録する
	 * @param Map<String, Object> formMap
	 * @param LoginDto loginDto
	 * @throws Exception
	 */
	public void registHyoka(Map<String, Object> formMap, LoginDto loginDto) {

		StringBuffer sql = new StringBuffer();
		sql.append(" INSERT INTO TBL_HYOUKATI ( ");
		sql.append("   KANKOU_ID ");
		sql.append("   , USER_ID ");
		sql.append("   , HYOUKATI ");
		sql.append(" ) VALUES ( ");
		sql.append("    '").append(formMap.get(KankouConst.KEY_ID)).append("' ");
		sql.append("   , '").append(loginDto.getUserId()).append("' ");
		sql.append("   , '").append(formMap.get(KankouConst.KEY_HYOKA)).append("' ");
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
		sql.append(" AND  KEN_CD = '").append(formMap.get(KankouConst.KEY_TODOUFUKEN_KEY)).append("' ");
		sql.append(" AND  KANKOU_NM = '").append(formMap.get(KankouConst.KEY_KANKOU_NM)).append("' ");
		
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
	 * @throws Exception
	 */
	public void lockKankou() {

		StringBuffer sql = new StringBuffer();
		sql.append(" LOCK TABLE TBL_KANKOU ");
		sql.append("  IN EXCLUSIVE MODE ");
		sql.append("  NOWAIT ");
	}
	
	/**
	 * 観光地IDの最大値を取得
	 * @throws Exception
	 * @return 観光地テーブル
	 */
	public String maxKankou(){
		
		Map<String, String> result;
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT COALESCE((SELECT MAX(KANKOU_ID) + 1 FROM TBL_KANKOU), 1) AS MAX ");
		sql.append("  FROM TBL_KANKOU ");
		sql.append("  FETCH NEXT 1 ROWS ONLY ");

		//SQLの結果をresultに格納する。
		result = super.find(sql.toString());
		
		return result.get("MAX");
	}
}
