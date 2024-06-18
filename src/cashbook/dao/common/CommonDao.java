package cashbook.dao.common;

import java.util.Map;

/**
 * 共通DAOクラス
 * @author soppra
 */
public interface CommonDao {

	/**
	 * システム年(YYYY)を取得する
	 * @return システム年(YYYY)
	 */
	public String getYyyy();

	/**
	 * システム月(MM)を取得する
	 * @return システム月(MM)
	 */
	public String getMm();

	/**
	 * システム日(DD)を取得する
	 * @return システム日(DD)
	 */
	public String getDd();

	/**
	 * システム年月日(YYYY/MM/DD)を取得する
	 * @return システム年月日(YYYY/MM/DD)
	 */
	public String getYyyyMmDd();

	/**
	 * コードマスタより、コード名称を文字列型で取得する
	 */
	public String getCodeName(String classCd ,String cd);
	
//*************************************観光地検索システム****************************************************************	
	/**
	 * カテゴリテーブルより、カテゴリID、カテゴリ名称をリスト型で取得する
	 */
	public Map<String ,String> getKategory();
	
	/**
	 * 地方マスタより、地方コード、地方名称をリスト型で取得する
	 */
	public Map<String ,String> getTIhou();
//	
//	/**
//	 * 都道府県マスタより、都道府県コード、都道府県名称をリスト型で取得する
//	 */
	public Map<String ,String> getTodouhuken();

}
