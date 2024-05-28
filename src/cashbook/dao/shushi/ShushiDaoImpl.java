package cashbook.dao.shushi;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.CannotAcquireLockException;

import cashbook.dao.common.BaseDaoImpl;
import cashbook.dto.common.LoginDto;
import cashbook.util.CommonUtil;
import cashbook.util.Const;

/**
 * 収支DAOクラス
 * @author soppra
 */
public class ShushiDaoImpl extends BaseDaoImpl implements ShushiDao {

	/**
	 * 収支一覧を検索する
	 * @return 収支一覧
	 */
	public List<Map<String, String>> listSearch(Map<String, Object> formMap, LoginDto loginDto) {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT T1.SHUSHI_ID || ',' || T1.SHUSHI_DTL_ID AS SHUSHI_KEY ");
		sql.append("     , M2.KOJIN_NM AS KOJIN_NM ");
		sql.append("     , T1.HIMOKU_CD || M1.HIMOKU_NM AS HIMOKU_NM ");
		sql.append("     , M1.HIMOKU_KBN ");
		sql.append("     , T1.KINGAKU ");
		sql.append("     , TO_CHAR(T1.YMD,'YYYY/MM/DD') AS YMD ");
		sql.append("  FROM TRN_SHUSHI_DTL T1 ");
		sql.append(" INNER JOIN TRN_SHUSHI T2 ");
		sql.append("    ON T1.SHUSHI_ID = T2.SHUSHI_ID ");
		sql.append(" INNER JOIN MST_HIMOKU M1 ");
		sql.append("    ON T1.HIMOKU_CD = M1.HIMOKU_CD ");
		sql.append(" INNER JOIN MST_KOJIN M2 ");
		sql.append("    ON T2.KOJIN_ID = M2.KOJIN_ID ");
		sql.append(" WHERE T2.YM = '").append(formMap.get("yearKey")).append(formMap.get("monthKey")).append("' ");

		// 費目CD
		if (!CommonUtil.isNull(CommonUtil.getStr(formMap.get("himokuCd")))) {
			sql.append("  AND M1.HIMOKU_CD = '").append(formMap.get("himokuCd")).append("' ");
		}
		// 個人名
		if (!CommonUtil.isNull(CommonUtil.getStr(formMap.get("kojinNmKey")))) {
			sql.append("  AND T2.KOJIN_ID = '").append(formMap.get("kojinNmKey")).append("' ");
		}
		// 世帯主の場合
		if (loginDto.getSetainushiFlg().equals(Const.SETAINUSHI_ON)) {
			// 世帯ID
			sql.append("  AND M2.SETAI_ID = '").append(loginDto.getSetaiId()).append("' ");
		} else {
			// 個人ID
			sql.append("  AND T2.KOJIN_ID = '").append(loginDto.getKojinId()).append("' ");
		}
		sql.append(" ORDER BY T1.YMD ");

		return super.search(sql.toString());
	}

	/**
	 * 収支明細件数取得
	 * @return 収支明細件数
	 */
	public int getShushiDtlCnt(String shushiId) {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT COUNT(SHUSHI_ID) AS CNT ");
		sql.append("  FROM TRN_SHUSHI_DTL ");
		sql.append(" WHERE SHUSHI_ID = '").append(shushiId).append("' ");

		return Integer.parseInt(super.find(sql.toString()).get("CNT"));
	}

	/**
	 * 収支を削除する
	 */
	public void listDelete(String shushiId) {

		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM TRN_SHUSHI ");
		sql.append(" WHERE SHUSHI_ID = '").append(shushiId).append("' ");

		super.update(sql.toString());

	}

	/**
	 * 収支明細を削除する
	 */
	public void listDtlDelete(String shushiId, String shushiDtlId) {

		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM TRN_SHUSHI_DTL ");
		sql.append(" WHERE SHUSHI_ID = '").append(shushiId).append("' ");
		sql.append("   AND SHUSHI_DTL_ID = '").append(shushiDtlId).append("' ");

		super.update(sql.toString());
	}

	/**
	 * 収支・収支明細を検索する
	 * @return 収支情報
	 */
	public Map<String, String> registFind(Map<String, Object> formMap) {

		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT LPAD(T1.HIMOKU_CD, 2, '0') || M1.HIMOKU_KBN AS HIMOKU_KEY ");
		sql.append("      , M1.HIMOKU_KBN ");
		sql.append("      , T1.KINGAKU ");
		sql.append("      , T2.KOJIN_ID ");
		sql.append("      , TO_CHAR(T1.YMD, 'YYYY/MM/DD') AS YMD ");
		sql.append("      , T1.REVISION ");
		sql.append("   FROM TRN_SHUSHI_DTL T1 ");
		sql.append("  INNER JOIN TRN_SHUSHI T2 ");
		sql.append("     ON T2.SHUSHI_ID = T1.SHUSHI_ID ");
		sql.append("  INNER JOIN MST_HIMOKU M1 ");
		sql.append("     ON M1.HIMOKU_CD = T1.HIMOKU_CD ");
		sql.append("  WHERE T1.SHUSHI_ID = '").append(formMap.get("shushiId")).append("' ");
		sql.append("    AND T1.SHUSHI_DTL_ID = '").append(formMap.get("shushiDtlId")).append("' ");

		return super.find(sql.toString());
	}

	/**
	 * 収支を登録する
	 * @throws Exception
	 */
	public void registInsert(Map<String, Object> formMap) {

		StringBuffer sql = new StringBuffer();
		sql.append(" INSERT INTO TRN_SHUSHI T1 ( ");
		sql.append("     T1.SHUSHI_ID ");
		sql.append("   , T1.KOJIN_ID ");
		sql.append("   , T1.YM ");
		sql.append("   , T1.INS_USER ");
		sql.append("   , T1.INS_DATE ");
		sql.append("   , T1.UPD_USER ");
		sql.append("   , T1.UPD_DATE ");
		sql.append("   , T1.REVISION ");
		sql.append(" ) VALUES ( ");
		sql.append("     '").append(formMap.get("shushiId")).append("' ");
		sql.append("   , '").append(formMap.get("kojinId")).append("' ");
		sql.append("   , '").append(formMap.get("ym")).append("' ");
		sql.append("   , '").append(formMap.get("kojinId")).append("' ");
		sql.append("   , SYSDATE ");
		sql.append("   , '").append(formMap.get("loginKojinId")).append("' ");
		sql.append("   , SYSDATE ");
		sql.append("   , 0 ");
		sql.append(" ) ");

		super.update(sql.toString());
	}

	/**
	 * 収支明細を登録する
	 * @throws Exception
	 */
	public void registDtlInsert(Map<String, Object> formMap) {

		StringBuffer sql = new StringBuffer();
		sql.append(" INSERT INTO TRN_SHUSHI_DTL T1 ( ");
		sql.append("     T1.SHUSHI_ID ");
		sql.append("   , T1.SHUSHI_DTL_ID ");
		sql.append("   , T1.HIMOKU_CD ");
		sql.append("   , T1.KINGAKU ");
		sql.append("   , T1.YMD ");
		sql.append("   , T1.INS_USER ");
		sql.append("   , T1.INS_DATE ");
		sql.append("   , T1.UPD_USER ");
		sql.append("   , T1.UPD_DATE ");
		sql.append("   , T1.REVISION ");
		sql.append(" ) VALUES ( ");
		sql.append("     '").append(formMap.get("shushiId")).append("' ");
		sql.append("   , '").append(formMap.get("shushiDtlId")).append("' ");
		sql.append("   , '").append(formMap.get("himokuCd")).append("' ");
		sql.append("   , '").append(formMap.get("kingaku")).append("' ");
		sql.append("   , '").append(formMap.get("ymd")).append("' ");
		sql.append("   , '").append(formMap.get("kojinId")).append("' ");
		sql.append("   , SYSDATE ");
		sql.append("   , '").append(formMap.get("loginKojinId")).append("' ");
		sql.append("   , SYSDATE ");
		sql.append("   , 0 ");
		sql.append(" ) ");

		super.update(sql.toString());
	}

	/**
	 * 収支明細を更新する
	 */
	public void registDtlUpdate(Map<String, Object> formMap) {

		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE TRN_SHUSHI_DTL T1 ");
		sql.append("    SET T1.HIMOKU_CD = '").append(formMap.get("himokuCd")).append("' ");
		sql.append("      , T1.KINGAKU   = '").append(formMap.get("kingaku")).append("' ");
		sql.append("      , T1.YMD       = '").append(formMap.get("ymd")).append("' ");
		sql.append("      , T1.UPD_USER  = '").append(formMap.get("loginKojinId")).append("' ");
		sql.append("      , T1.UPD_DATE  = SYSDATE ");
		sql.append("      , T1.REVISION  = T1.REVISION + 1 ");
		sql.append("  WHERE T1.SHUSHI_ID     = '").append(formMap.get("shushiId")).append("' ");
		sql.append("    AND T1.SHUSHI_DTL_ID = '").append(formMap.get("shushiDtlId")).append("' ");

		super.update(sql.toString());
	}

	/**
	 * 行ロック及び、排他チェック
	 * @return true：正常、false：排他エラー
	 */
	public boolean lock(Map<String, Object> formMap) {

		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT T1.SHUSHI_ID ");
		sql.append("   FROM TRN_SHUSHI_DTL T1 ");
		sql.append("  WHERE T1.SHUSHI_ID     = '").append(formMap.get("shushiId")).append("' ");
		sql.append("    AND T1.SHUSHI_DTL_ID = '").append(formMap.get("shushiDtlId")).append("' ");
		sql.append("    AND T1.REVISION      = '").append(formMap.get("revision")).append("' ");
		sql.append("    FOR UPDATE NOWAIT ");

		try {
			return super.find(sql.toString()).size() != 0;
		} catch (CannotAcquireLockException e) {
			// 対象データがロックされている場合はエラー
			return false;
		}
	}

	/**
	 * 費目名のコンボボックスの値を取得する
	 * @return 費目名コンボボックス
	 */
	public Map<String, String> getHimokuNmMap() {

		List<Map<String, String>> result;
		Map<String, String> ret = new LinkedHashMap<String, String>();
		ret.put("", "");
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT LPAD(M.HIMOKU_CD, 2, '0') || M.HIMOKU_KBN  AS CODE ");
		sql.append("      , M.HIMOKU_CD || ':' || M.HIMOKU_NM AS NAME");
		sql.append("   FROM MST_HIMOKU M ");
		sql.append("  WHERE M.DEL_FLG = '0' ");
		sql.append("  ORDER BY M.HIMOKU_CD ");

		result = super.search(sql.toString());

		for (Map<String, String> map : result) {
			ret.put(map.get("CODE"), map.get("NAME"));
		}

		return ret;
	}

	/**
	 * 個人名のコンボボックスの値を取得する
	 * @return 個人名コンボボックス
	 */
	public Map<String, String> getKojinNmMap(LoginDto loginDto) {

		List<Map<String, String>> result;
		Map<String, String> ret = new LinkedHashMap<String, String>();
		ret.put("", "");

		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT M.KOJIN_ID AS KOJIN_ID ");
		sql.append("      , M.KOJIN_NM AS KOJIN_NM ");
		sql.append("   FROM MST_KOJIN M ");
		sql.append("  WHERE M.DEL_FLG = '0' ");
		sql.append("    AND M.SETAI_ID = '").append(loginDto.getSetaiId()).append("' ");
		sql.append("  ORDER BY M.KOJIN_ID ");

		result = super.search(sql.toString());

		for (Map<String, String> map : result) {
			ret.put(map.get("KOJIN_ID"), map.get("KOJIN_NM"));
		}

		return ret;
	}

	/**
	 * 引数.個人ID,年月から収支が登録されている場合、収支明細IDのMAX+1を取得する。
	 * @return 収支ID, 収支明細ID
	 */
	public Map<String, String> getMaxShushiDtl(Map<String, Object> formMap) {

		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT T1.SHUSHI_ID AS NOWSHUSHIID ");
		sql.append("      , LPAD(TO_NUMBER(max(T2.SHUSHI_DTL_ID)) + 1, 8, '0') AS NEXTSHUSHIDTLID ");
		sql.append("   FROM TRN_SHUSHI T1 ");
		sql.append("  INNER JOIN TRN_SHUSHI_DTL T2 ");
		sql.append("     ON T2.SHUSHI_ID = T1.SHUSHI_ID ");
		sql.append("  WHERE T1.KOJIN_ID  = '").append(formMap.get("kojinId")).append("' ");
		sql.append("    AND T1.YM        = '").append(formMap.get("ym")).append("' ");
		sql.append("  GROUP BY T1.SHUSHI_ID ");

		return super.find(sql.toString());
	}

	/**
	 * シーケンスから収支IDを取得する。
	 * @return 収支ID
	 */
	public String getNextShushiId() {

		Map<String, String> result;
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT LPAD(TO_NUMBER(SHUSHI_ID_SEQ.NEXTVAL) + 1, 8, '0') AS SHUSHIID ");
		sql.append("   FROM DUAL ");
		result = super.find(sql.toString());

		return result.get("SHUSHIID");
	}
}
