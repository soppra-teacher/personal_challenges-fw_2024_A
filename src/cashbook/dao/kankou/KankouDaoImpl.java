package cashbook.dao.kankou;

import java.util.Map;

import cashbook.dao.common.BaseDaoImpl;
import cashbook.dto.common.LoginDto;
import cashbook.util.KankouConst;

public class KankouDaoImpl extends BaseDaoImpl implements KankouDao {

	/**
	 * 観光地情報を登録する
	 * @throws Exception
	 */
	public void registKankou(Map<String, Object> formMap, LoginDto loginDto) {

		StringBuffer sql = new StringBuffer();
		sql.append(" INSERT INTO TBL_KANKOU  ( ");
		sql.append("   USER_ID ");
		sql.append("   , KEN_CD ");
		sql.append("   , CATEGORY_ID ");
		sql.append("   , KANKOU_NM ");
		sql.append("   , SETSUMEI ");
		sql.append("   , REVIEW ");
		sql.append("   , FILE_NM ");
		sql.append(" ) VALUES ( ");
		sql.append("    '").append(loginDto.getUserId()).append("' ");
		sql.append("   , '").append(formMap.get(KankouConst.KEY_TODOUFUKEN_KEY)).append("' ");
		sql.append("   , '").append(formMap.get(KankouConst.KEY_CATEGORY_KEY)).append("' ");
		sql.append("   , '").append(formMap.get(KankouConst.KEY_KANKOU_NM)).append("' ");
		System.out.println("観光名所" + formMap.get(KankouConst.KEY_KANKOU_NM));
		sql.append("   , '").append(formMap.get(KankouConst.KEY_SETSUMEI)).append("' ");
		sql.append("   , '").append(formMap.get(KankouConst.KEY_review)).append("' ");
		//写真だけ、現在は任意の値を入力
		//sql.append("   , '").append(formMap.get(KankouConst.KEY_PICTURES)).append("' ");
		sql.append("   , 'testpicture.jpeg'");
		sql.append(" ) ");

		super.update(sql.toString());
	}
	
	/**
	 * 評価値を登録する
	 * @throws Exception
	 */
	public void registHyoka(Map<String, Object> formMap, LoginDto loginDto) {

		StringBuffer sql = new StringBuffer();
		sql.append(" INSERT INTO TBL_HYOUKATI ( ");
		sql.append("   KANKOU_ID ");
		sql.append("   , USER_ID ");
		sql.append("   , HYOUKATI ");
		sql.append(" ) VALUES ( ");
		sql.append("   (SELECT MAX(KANKOU_ID) ");
		sql.append("    FROM TBL_KANKOU) ");
		sql.append("   , '").append(loginDto.getUserId()).append("' ");
		sql.append("   , '").append(formMap.get(KankouConst.KEY_HYOKA)).append("' ");
		sql.append(" ) ");
		super.update(sql.toString());
	}


	/**
	 * 重複チェック
	 * @return true：正常、false：重複エラー
	 */
	public boolean checkOverlapKankou(Map<String, Object> formMap, LoginDto loginDto) {

		Map<String, String> result;
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT COUNT(*) AS COUNT  ");
		sql.append("  FROM TBL_KANKOU ");
		//個人IDの部分だけ、ログイン画面が作成され次第変更する
		sql.append(" WHERE USER_ID = '").append(loginDto.getUserId()).append("' ");
		System.out.println(loginDto.getUserId());
		System.out.println(formMap.get(KankouConst.KEY_TODOUFUKEN_KEY));
		System.out.println(formMap.get(KankouConst.KEY_KANKOU_NM));
		sql.append(" AND  KEN_CD = '").append(formMap.get(KankouConst.KEY_TODOUFUKEN_KEY)).append("' ");
		sql.append(" AND  KANKOU_NM = '").append(formMap.get(KankouConst.KEY_KANKOU_NM)).append("' ");
		System.out.println(super.find(sql.toString()).size());
		
		//SQLの結果をresultに格納する。
		result = super.find(sql.toString());
		System.out.println(result.get("COUNT"));
		
		
		if(result.get("COUNT").equals("0")) {
			return true;
		}else {
			return false;
		}
		//return super.find(sql.toString()).size() == 0;
	}

}
