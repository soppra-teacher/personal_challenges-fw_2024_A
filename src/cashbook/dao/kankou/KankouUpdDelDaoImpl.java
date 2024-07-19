package cashbook.dao.kankou;

import java.util.Map;

import cashbook.dao.common.BaseDaoImpl;
import cashbook.dto.common.LoginDto;
import cashbook.util.CommonUtil;
import cashbook.util.Const;
import cashbook.util.KankouUpdDelConst;

public class KankouUpdDelDaoImpl extends BaseDaoImpl implements KankouUpdDelDao {
	
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
		sql.append("  WHERE K.KANKOU_ID = '").append(formMap.get(KankouUpdDelConst.KEY_KANKOU_ID)).append("' ");
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
		sql.append(" SET SETSUMEI = '").append(formMap.get(KankouUpdDelConst.KEY_SETUMEI)).append("' ");
		sql.append("    ,REVIEW = '").append(formMap.get(KankouUpdDelConst.KEY_REVIEW)).append("' ");
		if (!CommonUtil.isNull(CommonUtil.getStr(formMap.get(Const.KEY_IMAGE_STRING)))) {
			sql.append("    ,FILE_NM = '").append(formMap.get(KankouUpdDelConst.KEY_KANKOU_ID)).append(Const.IMAGE_PNG + "'");
		}
		sql.append("WHERE KANKOU_ID = '").append(formMap.get(KankouUpdDelConst.KEY_KANKOU_ID)).append("' ");
		super.update(sql.toString());
	}
	
	/**
	 * 観光テーブルを削除する
	 * @param formMap
	 */
	public void delKankou(Map<String, Object> formMap) {
		StringBuffer sql = new StringBuffer();
		sql.append(" DELETE TBL_KANKOU ");
		sql.append(" WHERE KANKOU_ID ='").append(formMap.get(KankouUpdDelConst.KEY_KANKOU_ID)).append("' ");
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
		sql.append("     ,'").append(formMap.get(KankouUpdDelConst.KEY_HYOKA)).append("' )");
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
		sql.append("  SET HYOUKATI = '").append(formMap.get(KankouUpdDelConst.KEY_HYOKA)).append("'");
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
		sql.append(" WHERE KANKOU_ID ='").append(formMap.get(KankouUpdDelConst.KEY_KANKOU_ID)).append("' ");
		super.update(sql.toString());
	}
}
