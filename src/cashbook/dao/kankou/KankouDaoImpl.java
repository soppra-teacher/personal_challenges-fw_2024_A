package cashbook.dao.kankou;

import java.util.List;
import java.util.Map;

import cashbook.dao.common.BaseDaoImpl;
import cashbook.dto.common.LoginDto;
import cashbook.util.CommonUtil;
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
		sql.append("   , '").append(formMap.get(KankouConst.KEY_SETSUMEI)).append("' ");
		sql.append("   , '").append(formMap.get(KankouConst.KEY_REVIEW)).append("' ");
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
	 * 観光地情報を一覧を検索する
	 * @param formMpa
	 * @return 検索結果
	 */
	public List<Map<String, String>> searchKankou(Map<String, Object> formMap) {
	    List<Map<String, String>> result;
	    StringBuffer sql = new StringBuffer();
	    sql.append("SELECT  K.KANKOU_ID ");
	    sql.append("       ,K.KANKOU_NM ");
	    sql.append("       ,TI.TIHOU_NM ");
	    sql.append("       ,T.KEN_NM ");
	    sql.append("       ,C.CATEGORY_NM ");
	    sql.append("       ,H.HYOUKATI ");
	    sql.append("       ,K.USER_ID ");
	    sql.append("  FROM TBL_KANKOU K ");
	    sql.append("  INNER JOIN MST_TODOUHUKEN T ");
	    sql.append("  ON K.KEN_CD = T.KEN_CD ");
	    sql.append("  INNER JOIN MST_TIHOU TI ");
	    sql.append("  ON T.TIHOU_CD = TI.TIHOU_CD ");
	    sql.append("  INNER JOIN TBL_CATEGORY C  ");
	    sql.append("  ON K.CATEGORY_ID = C.CATEGORY_ID  ");
	    sql.append("  INNER JOIN (SELECT ROUND(AVG(HYOUKATI), 1) AS HYOUKATI, KANKOU_ID FROM TBL_HYOUKATI GROUP BY KANKOU_ID) H ");
	    sql.append("  ON K.KANKOU_ID = H.KANKOU_ID ");
	    sql.append("  INNER JOIN MST_USER U ");
	    sql.append("  ON K.USER_ID = U.USER_ID ");
	        //観光地名 
	 		if (!CommonUtil.isNull(CommonUtil.getStr(formMap.get(KankouConst.KEY_KANKOU_NM)))) {
	 			sql.append(" AND K.KANKOU_NM LIKE '%").append(formMap.get(KankouConst.KEY_KANKOU_NM)).append("%' ");
	 		}
	 		// ユーザ名
	 		if (!CommonUtil.isNull(CommonUtil.getStr(formMap.get(KankouConst.KEY_USER_ID)))) {
	 			sql.append(" AND K.USER_ID LIKE '%").append(formMap.get(KankouConst.KEY_USER_ID)).append("%' ");
	 		}
	 		//カテゴリ名
	 		if (!CommonUtil.isNull(CommonUtil.getStr(formMap.get(KankouConst.KEY_CATEGORY_KEY)))) {
	 			sql.append(" AND K.CATEGORY_ID = '").append(formMap.get(KankouConst.KEY_CATEGORY_KEY)).append("' ");
	 		}
	 		//都道府県名
	 		if (!CommonUtil.isNull(CommonUtil.getStr(formMap.get(KankouConst.KEY_TODOUHUKEN )))) {
	 			sql.append(" AND K.KEN_CD = '").append(formMap.get(KankouConst.KEY_TODOUHUKEN )).append("' ");
	 		}
	 		//地方名
	 		if (!CommonUtil.isNull(CommonUtil.getStr(formMap.get(KankouConst.KEY_TIHOU )))) {
	 			sql.append(" AND TI.TIHOU_CD = '").append(formMap.get(KankouConst.KEY_TIHOU )).append("' ");
	 		}
	    sql.append("  ORDER BY H.HYOUKATI DESC, ");
	    sql.append("  K.KEN_CD ASC");
	    result = super.search(sql.toString());
	    return result;
	}
	
	/**
	 * 観光テーブルを検索する
	 * @param formMap
	 * @param 
	 * @return 観光テーブル
	 */
	public Map<String, String> findKankou(Map<String, Object> formMap, LoginDto loginDto) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT K.FILE_NM ");
		sql.append("     , K.KANKOU_NM ");
		sql.append("     , C.CATEGORY_NM ");
		sql.append("     , T.KEN_NM ");
		sql.append("     , TI.TIHOU_NM ");
		sql.append("     , K.SETSUMEI ");
		sql.append("     , K.REVIEW ");
		sql.append("     , H.HYOUKATI ");
		sql.append("     , K.USER_ID ");
		sql.append("  FROM TBL_KANKOU K ");
		sql.append("  INNER JOIN MST_TODOUHUKEN T ");
		sql.append("  ON K.KEN_CD = T.KEN_CD ");
		sql.append("  INNER JOIN MST_TIHOU TI ");
		sql.append("  ON T.TIHOU_CD = TI.TIHOU_CD ");
		sql.append("  INNER JOIN TBL_CATEGORY C ");
		sql.append("  ON K.CATEGORY_ID = C.CATEGORY_ID ");
		sql.append("  LEFT JOIN TBL_HYOUKATI H ");
		sql.append("  ON K.KANKOU_ID = H.KANKOU_ID AND H.USER_ID = '").append(loginDto.getUserId()).append("' ");
		sql.append("  INNER JOIN MST_USER U ");
		sql.append("  ON K.USER_ID = U.USER_ID ");
		sql.append("  WHERE K.KANKOU_ID = '").append(formMap.get(KankouConst.KEY_KANKOU_ID)).append("' ");
		return super.find(sql.toString());
	}
	
	/**
	 * 観光テーブルを更新する
	 * @param formMap
	 * @param loginDto ログイン情報Dto
	 */
	public void updateKankou(Map<String, Object> formMap, LoginDto loginDto) {

		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE TBL_KANKOU");
		sql.append(" SET SETSUMEI = '").append(formMap.get(KankouConst.KEY_SETUMEI)).append("' ");
		sql.append("    ,REVIEW = '").append(formMap.get(KankouConst.KEY_REVIEW)).append("' ");
		if (!CommonUtil.isNull(CommonUtil.getStr(formMap.get(KankouConst.KEY_IMAGE_STRING)))) {
			sql.append("    ,FILE_NM = '").append(formMap.get(KankouConst.KEY_KANKOU_ID)).append(".png'");
		}
		sql.append("WHERE KANKOU_ID = '").append(formMap.get(KankouConst.KEY_KANKOU_ID)).append("' ");
		super.update(sql.toString());
	}
	
	/**
	 * 観光テーブルを削除する
	 * @param formMap
	 */
	public void delKankou(Map<String, Object> formMap) {
		StringBuffer sql = new StringBuffer();
		sql.append(" DELETE TBL_KANKOU ");
		sql.append(" WHERE KANKOU_ID ='").append(formMap.get(KankouConst.KEY_KANKOU_ID)).append("' ");
	}
	
	
	/**
	 * 評価値テーブルを登録する
	 * @param formMap
	 * @param loginDto ログイン情報Dto
	 * @param kankouId 観光Id
	 */
	public void insHyoka(Map<String, Object> formMap, LoginDto loginDto, String kankouId) {

		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO TBL_HYOUKATI ");
		sql.append("  (KANKOU_ID,");
		sql.append("   USER_ID,");
		sql.append("   HYOUKATI) ");
		sql.append("   VALUES ('").append(kankouId).append("'");
		sql.append("     ,'").append(loginDto.getUserId()).append("' ");
		sql.append("     ,'").append(formMap.get(KankouConst.KEY_HYOKA)).append("' )");
		super.update(sql.toString());
	}
	
	/**
	 * 評価値テーブルを更新する
	 * @param formMap
	 * @param loginDto ログイン情報Dto
	 * @param kankouId 観光Id
	 */
	public void updHyoka(Map<String, Object> formMap, LoginDto loginDto, String kankouId){
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE TBL_HYOUKATI");
		sql.append("  SET HYOUKATI = '").append(formMap.get(KankouConst.KEY_HYOKA)).append("'");
		sql.append("  WHERE KANKOU_ID = '").append(kankouId).append("'");
		sql.append("  AND USER_ID = '").append(loginDto.getUserId()).append("' ");
		super.update(sql.toString());
	}
	
	/**
	 * 評価値テーブルを削除する
	 * @param formMap
	 * @param loginDto ログイン情報Dto
	 * @param kankouId 観光Id
	 */
	public void delHyoka(Map<String, Object> formMap){

		StringBuffer sql = new StringBuffer();
		sql.append(" DELETE TBL_HYOUKATI ");
		sql.append(" WHERE KANKOU_ID ='").append(formMap.get(KankouConst.KEY_KANKOU_ID)).append("' ");
		super.update(sql.toString());
	}
}
