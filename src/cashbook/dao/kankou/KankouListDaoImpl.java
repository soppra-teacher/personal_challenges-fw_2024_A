package cashbook.dao.kankou;

import java.util.List;
import java.util.Map;

import cashbook.dao.common.BaseDaoImpl;
import cashbook.dto.common.LoginDto;
import cashbook.util.CommonUtil;
import cashbook.util.KankouConst;

public class KankouListDaoImpl extends BaseDaoImpl implements KankouListDao {
	/**
	 * 観光地情報を一覧を検索する
	 * @param formMap
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
	        // 観光地名 
	 		if (!CommonUtil.isNull(CommonUtil.getStr(formMap.get(KankouConst.KEY_KANKOU_NM)))) {
	 			sql.append(" AND K.KANKOU_NM LIKE '%").append(formMap.get(KankouConst.KEY_KANKOU_NM)).append("%' ");
	 		}
	 		// ユーザ名
	 		if (!CommonUtil.isNull(CommonUtil.getStr(formMap.get(KankouConst.KEY_USER_ID)))) {
	 			sql.append(" AND K.USER_ID LIKE '%").append(formMap.get(KankouConst.KEY_USER_ID)).append("%' ");
	 		}
	 		// カテゴリ名
	 		if (!CommonUtil.isNull(CommonUtil.getStr(formMap.get(KankouConst.KEY_CATEGORY_KEY)))) {
	 			sql.append(" AND K.CATEGORY_ID = '").append(formMap.get(KankouConst.KEY_CATEGORY_KEY)).append("' ");
	 		}
	 		// 都道府県名
	 		if (!CommonUtil.isNull(CommonUtil.getStr(formMap.get(KankouConst.KEY_TODOUHUKEN )))) {
	 			sql.append(" AND K.KEN_CD = '").append(formMap.get(KankouConst.KEY_TODOUHUKEN )).append("' ");
	 		}
	 		// 地方名
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
	 * @param loginDto ログイン情報Dto
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
	
}
