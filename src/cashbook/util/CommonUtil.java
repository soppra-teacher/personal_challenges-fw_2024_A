package cashbook.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.DynaActionForm;

/**
 * 共通ユーティリティークラス
 * @author soppra
 */
public class CommonUtil {

	/**
	 * 年のコンボボックスの値を取得する
	 * @return 年コンボボックス
	 */
	public static Map<String, String> getYearMap() {
		Map<String, String> result = new LinkedHashMap<String, String>();
		for (int i = 2000; i <= 2050; i++ ) {
			result.put(String.valueOf(i), String.valueOf(i) + "年");
		}
		return result;
	}

	/**
	 * 月のコンボボックスの値を取得する
	 * @return 月コンボボックス
	 */
	public static Map<String, String> getMonthMap() {
		Map<String, String> result = new LinkedHashMap<String, String>();
		for (int i = 1; i <= 12; i++ ) {
			String month = String.format("%02d", i);
			result.put(month, month + "月");
		}
		return result;
	}

	/**
	 * NULLチェック
	 */
	public static boolean isNull(String str) {
		if (str == null || str.equals("")) {
			return true;
		}
		return false;
	}

    /**
     * 対象文字列が比較文字列パターンないの値かを判定する。
     *
     * @param str 対象文字列
     * @param patternFormat 比較文字列パターン
     * @return チェックOKならtrue、チェックNGならfalse
     */
    public static boolean isStringPatternFormat(String str, String patternFormat) {
        Pattern pattern = Pattern.compile(patternFormat);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    /**
     * 引数のオブジェクトが文字列かどうかを判定する。
     *
     * @param obj 対象オブジェクト
     * @return 文字列ならFalse
     */
	protected static boolean isString(Object obj) {
		return (obj == null) ? true : String.class.isInstance(obj);
	}

    /**
     * 引数のオブジェクトがnullならから文字を、文字列なら文字列を返す。
     *
     * @param obj 対象オブジェクト
     * @return nullなら"",文字列なら文字列
     */
	public static String getStr(Object obj) {
		return (obj == null) ? "" : obj.toString();
	}

	/**
	 * formMapからgetした削除チェックボックスの値を、List型に格納し、返却。
	 * @param formMap
	 * @return List<String>
	 */
	public static List<String> convFormMapToList(Map<String, Object> formMap){
		String[] checkDel = (String[])formMap.get(Const.ITEM_CHECKBOX_DELETE);
		List<String> list = new ArrayList<String>();
		for (String str : checkDel){
			list.add(str);
		}
		return list;
	}

	/**
	 * 文字列の数字を３桁カンマ区切りで返す
	 * @param value 数字
	 * @return カンマ区切りした数字
	 */
	public static String formatComma(String value) {
		if (isNull(value)) {
			return "";
		}
		return formatComma(Integer.parseInt(value));
	}

	/**
	 * 数値を３桁カンマ区切りで返す
	 * @param value 数値
	 * @return カンマ区切りした数字
	 */
	public static String formatComma(int value) {
		return String.format("%,d", value);
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> getFormMap(DynaActionForm dynaForm) {
		return dynaForm.getMap();
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> getSessionMap(HttpServletRequest request, String str) {
		return (Map<String, Object>)request.getSession().getAttribute(str);
	}
}

