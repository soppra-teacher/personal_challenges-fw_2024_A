package cashbook.dao.common;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 共通DAOクラス
 * @author soppra
 */
public class CommonDaoImpl extends BaseDaoImpl implements CommonDao {

	/**
	 * システム年(YYYY)を取得する
	 * @return システム年(YYYY)
	 */
	public String getYyyy() {

		Map<String, String> result;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT TO_CHAR(SYSDATE,'YYYY') AS YYYY ");
		sql.append("  FROM DUAL ");
		result = super.find(sql.toString());

		return result.get("YYYY");
	}

	/**
	 * システム月(MM)を取得する
	 * @return システム月(MM)
	 */
	public String getMm() {

		Map<String, String> result;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT TO_CHAR(SYSDATE, 'MM') AS MM ");
		sql.append("  FROM DUAL ");
		result = super.find(sql.toString());

		return result.get("MM");
	}

	/**
	 * システム日(DD)を取得する
	 * @return システム日(DD)
	 */
	public String getDd() {

		Map<String, String> result;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT TO_CHAR(SYSDATE, 'DD') AS DD ");
		sql.append("  FROM DUAL ");
		result = super.find(sql.toString());

		return result.get("DD");
	}

	/**
	 * システム年月日(YYYY/MM/DD)を取得する
	 * @return システム年月日(YYYY/MM/DD)
	 */
	public String getYyyyMmDd() {

		Map<String, String> result;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT TO_CHAR(SYSDATE, 'YYYY/MM/DD') AS YYYMMDD ");
		sql.append("  FROM DUAL ");
		result = super.find(sql.toString());

		return result.get("YYYMMDD");
	}
//****************************************************************************************
	/**
	 * カテゴリテーブルからカテゴリID、カテゴリ名を取得する。
	 *
	 * @param クラスコード
	 */
	public Map<String, String> getKategory() {

		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
		Map<String, String> result = new LinkedHashMap<String, String>();

		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT  CATEGORY_ID");
		sql.append("        ,CATEGORY_ID || ':' || CATEGORY_NM AS CATEGORY ");
		sql.append(" FROM    TBL_CATEGORY ");
		
		mapList = super.search(String.valueOf(sql));
		if (mapList.size() == 0) {
			return null;

		} else {
			// 1つ目に空白をセット
			result.put("", "");

			// リストの内容分回し、形式を変えて呼び元へ返す
			for (Map<String, String> map : mapList) {
				result.put(map.get("CATEGORY_ID"), map.get("CATEGORY"));
			}
			return result;
		}
	}
//**************************************************************************************************

	/**
	 * 地方テーブルから地方コード、地方名を取得する。
	 *
	 * @param クラスコード
	 */
	public Map<String, String> getTIhou() {

		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
		Map<String, String> result = new LinkedHashMap<String, String>();

		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT  TIHOU_CD");
		sql.append("        ,TIHOU_CD || ':' || TIHOU_NM AS TIHOU ");
		sql.append(" FROM    MST_TIHOU ");
		
		mapList = super.search(String.valueOf(sql));
		if (mapList.size() == 0) {
			return null;

		} else {
			// 1つ目に空白をセット
			result.put("", "");

			// リストの内容分回し、形式を変えて呼び元へ返す
			for (Map<String, String> map : mapList) {
				result.put(map.get("TIHOU_CD"), map.get("TIHOU"));
			}
			return result;
		}
	}
//**************************************************************************************************
	/**
	 * 都道府県マスタから都道県コード、都道府県名を取得する。
	 *
	 * @param クラスコード
	 */
	public Map<String, String> getTodouhuken() {

		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
		Map<String, String> result = new LinkedHashMap<String, String>();

		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT  KEN_CD");
		sql.append("        , KEN_CD || ':' || KEN_NM AS TODOUHUKEN");
		sql.append(" FROM    MST_TODOUHUKEN ");
		
		mapList = super.search(String.valueOf(sql));
		if (mapList.size() == 0) {
			return null;

		} else {
			// 1つ目に空白をセット
			result.put("", "");

			// リストの内容分回し、形式を変えて呼び元へ返す
			for (Map<String, String> map : mapList) {
				result.put(map.get("KEN_CD"), map.get("TODOUHUKEN"));
			}
			return result;
		}
	}
//**************************************************************************************************

	/**
	 * コードマスタより、コード名称を文字列型で取得する
	 *
	 * @param classCd クラスコード
	 * @param cd コード
	 */
	public String getCodeName(String classCd, String cd) {

		Map<String, String> resultMap = new LinkedHashMap<String, String>();
		String result = "";

		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT  mc.cd_nm ");
		sql.append(" FROM    mst_code mc ");
		sql.append(" WHERE   mc.class_code = '").append(classCd).append("' ");
		sql.append(" AND     mc.cd = '").append(cd).append("' ");

		resultMap = super.find(String.valueOf(sql));

		if ("".equals(resultMap.get("CD_NM"))) {
			return null;
		} else {
			result = resultMap.get("CD_NM");
		}

		return result;
	}
}
