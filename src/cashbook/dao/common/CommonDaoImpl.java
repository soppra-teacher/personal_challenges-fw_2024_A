package cashbook.dao.common;

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
}
