package cashbook.dao.kankou;

import static cashbook.util.Const.*;

import java.util.List;
import java.util.Map;

import org.springframework.dao.CannotAcquireLockException;

import cashbook.dao.common.BaseDaoImpl;
import cashbook.dto.common.LoginDto;
import cashbook.util.CommonUtil;
import cashbook.util.KankouConst;
import cashbook.util.SetaiConst;

/**
 * 個人DAOクラス
 * @author soppra
 */
public class KankouDaoImpl extends BaseDaoImpl implements KankouDao {

////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 観光地情報を一覧を検索する
	 * @return 観光地一覧
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
	    sql.append("  ORDER BY H.HYOUKATI DESC ");
	    result = super.search(sql.toString());

	    return result;
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	

	/**
	 * 個人マスタを削除する
	 */
	public void deleteKojin(String kojinId, LoginDto loginDto) {

		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE MST_KOJIN M1 ");
		sql.append("   SET M1.DEL_FLG = '1' ");
		sql.append("     , M1.UPD_USER = '").append(loginDto.getKojinId()).append("' ");
		sql.append("     , M1.UPD_DATE = SYSDATE ");
		sql.append("     , M1.REVISION = M1.REVISION + 1 ");
		sql.append(" WHERE M1.KOJIN_ID = '").append(kojinId).append("' ");

		super.update(sql.toString());
	}

	/**
	 * 個人マスタを検索する
	 * @return 個人マスタ
	 */
	public Map<String, String> findKojin(Map<String, Object> formMap) {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT M1.KOJIN_ID ");
		sql.append("     , M1.SETAI_ID ");
		sql.append("     , M1.PASS ");
		sql.append("     , M1.KOJIN_NM ");
		sql.append("     , M1.KOJIN_NM_KANA ");
		sql.append("     , M1.SEIBETSU_KBN ");
		sql.append("     , M1.ZOKUGARA ");
		sql.append("     , M1.SETAINUSHI_FLG ");
		sql.append("     , M1.REVISION ");
		sql.append("  FROM MST_KOJIN M1 ");
		sql.append(" WHERE M1.DEL_FLG  = '0' ");
		sql.append("   AND M1.KOJIN_ID = '").append(formMap.get(KankouConst.KEY_KOJIN_ID)).append("' ");

		return super.find(sql.toString());
	}


	/**
	 * 個人マスタを更新する
	 */
	public void updateKojin(Map<String, Object> formMap, LoginDto loginDto) {

		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE MST_KOJIN M1 ");
		sql.append("   SET M1.SETAI_ID = '").append(formMap.get(SetaiConst.KEY_SETAI_ID)).append("' ");
		sql.append("     , M1.PASS = '").append(formMap.get(KankouConst.KEY_PASS)).append("' ");
		sql.append("     , M1.KOJIN_NM = '").append(formMap.get(KankouConst.KEY_KOJIN_NM)).append("' ");
		sql.append("     , M1.KOJIN_NM_KANA = '").append(formMap.get(KankouConst.KEY_KOJIN_NM_KANA)).append("' ");
		sql.append("     , M1.SEIBETSU_KBN = '").append(formMap.get(KankouConst.KEY_SEIBETSU_KBN)).append("' ");
		sql.append("     , M1.ZOKUGARA = '").append(formMap.get(KankouConst.KEY_ZOKUGARA)).append("' ");
		sql.append("     , M1.SETAINUSHI_FLG = '").append(formMap.get(KankouConst.KEY_SETAINUSI_FLG_VALUE)).append("' ");
		sql.append("     , M1.UPD_USER = '").append(loginDto.getKojinId()).append("' ");
		sql.append("     , M1.UPD_DATE = SYSDATE ");
		sql.append("     , M1.REVISION = M1.REVISION + 1 ");
		sql.append(" WHERE M1.KOJIN_ID = '").append(formMap.get(KankouConst.KEY_KOJIN_ID)).append("' ");

		super.update(sql.toString());
	}

	/**
	 * 重複チェック
	 * @return true：正常、false：重複エラー
	 */
	public boolean checkOverlapKojin(Map<String, Object> formMap) {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT M1.KOJIN_ID ");
		sql.append("  FROM MST_KOJIN M1 ");
		sql.append(" WHERE M1.KOJIN_ID = '").append(formMap.get(KankouConst.KEY_KOJIN_ID)).append("' ");
		sql.append("   AND ROWNUM = 1 ");

		return super.find(sql.toString()).size() == 0;
	}

	/**
	 * 行ロック及び、排他チェック
	 * @return true：正常、false：排他エラー
	 */
	public boolean lockKojin(Map<String, Object> formMap) {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT M1.KOJIN_ID ");
		sql.append("  FROM MST_KOJIN M1 ");
		sql.append(" WHERE M1.KOJIN_ID = '").append(formMap.get(KankouConst.KEY_KOJIN_ID)).append("' ");
		sql.append("   AND M1.REVISION = '").append(formMap.get(ITEM_REVISION)).append("' ");
		sql.append("   FOR UPDATE NOWAIT ");
		try {

			return super.find(sql.toString()).size() != 0;

		} catch (CannotAcquireLockException e) {
			// 対象データがロックされている場合はエラー
			return false;
		}
	}

	/**
	 * 世帯主フラグ確認
	 * @return false：正常、true：整合性エラー
	 */
	public boolean checkSetainushiFlg(Map<String, Object> formMap) {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT A.KOJIN_ID ");
		sql.append("  FROM MST_KOJIN A ");
		sql.append(" WHERE A.SETAI_ID = '").append(formMap.get(SetaiConst.KEY_SETAI_ID)).append("' ");
		sql.append("   AND A.SETAINUSHI_FLG = '1' ");
		sql.append("   AND A.KOJIN_ID != '").append(formMap.get(KankouConst.KEY_KOJIN_ID)).append("' ");

		return super.find(sql.toString()).size() != 0;
	}
}
