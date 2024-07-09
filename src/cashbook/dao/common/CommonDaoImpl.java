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
	 * セレクトボックス用都道府県マスタ取得
	 * @return カテゴリマスタ
	 */
	public Map<String, String> searchSelectboxTodouhuKen() {

		List<Map<String, String>> result;
		Map<String, String> ret = new LinkedHashMap<String, String>();
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT KEN_CD ");
		sql.append("      , KEN_CD || ':' || KEN_NM AS TODOUHUKENNM");
		sql.append("   FROM MST_TODOUHUKEN ");
		sql.append("   ORDER BY  KEN_CD ASC ");
		result = super.search(sql.toString());
		for (Map<String, String> map : result) {
			if (ret.size() == 0) {
				ret.put("", "");
			}
			ret.put(map.get("KEN_CD"), map.get("TODOUHUKENNM"));
		}

		return ret;
	}

	/**
	 * セレクトボックス用カテゴリマスタ取得
	 * @return カテゴリマスタ
	 */
	public Map<String, String> searchSelectboxCategory() {

		List<Map<String, String>> result;
		Map<String, String> ret = new LinkedHashMap<String, String>();
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT CATEGORY_ID  ");
		sql.append("      , CATEGORY_ID || ':' || CATEGORY_NM AS NM ");
		sql.append("   FROM TBL_CATEGORY ");
		sql.append("   ORDER BY CATEGORY_ID ASC ");
		result = super.search(sql.toString());
		for (Map<String, String> map : result) {
			if (ret.size() == 0) {
				ret.put("", "");
			}
			ret.put(map.get("CATEGORY_ID"), map.get("NM"));
		}

		return ret;
	}

	/**
	 * 地方テーブルから地方コード、地方名を取得する。
	 *
	 * @param クラスコード
	 */
	public Map<String, String> searchSelectboxTIhou() {

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
}
