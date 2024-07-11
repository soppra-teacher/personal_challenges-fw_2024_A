package cashbook.dto.kankou;

import java.util.List;
import java.util.Map;

/**
 * 検索・一覧画面DTOクラス
 * @author soppra
 */

public class KankouListDto {
	
	/** 観光地名 */
	private String kankouNm;
	
	/** ユーザID */
	private String userId;
	
	/** カテゴリ（キー） */
	private String categoryKey;
	
	/** カテゴリ（ＭＡＰ） */
	private Map<String, String> category;
	
	/** カテゴリ名 */
	private String categoryNm;
	
	/** 地方キー（キー） */
	private String tihouKey;
	
	/** 地方（ＭＡＰ） */
	private Map<String, String> tihou;
	
	/** 都道府県キー（キー） */
	private String todouhukenKey;
	
	/** 都道府県（ＭＡＰ） */
	private Map<String, String> todouhuken;
	
	/** 都道府県名 */
	private String todouhukenNm;
	
	/** 観光地ID */
	private String kankouId;
	
	/** 地方名 */
	private String tihouNm;
	
	/** 評価値 */
	private String hyoukati;
	
	/** 観光一覧 */
	private List<KankouListDto> list;
	
	/** 画像パス */
	private String imagePath;
	
	
	/**
	 * 観光一覧を取得します。
	 * @return 観光一覧
	 */
	public List<KankouListDto> getList() {
		return list;
	}

	/**
	 * 観光一覧を設定します。
	 * @param list 観光一覧
	 */
	public void setList(List<KankouListDto> list) {
		this.list = list;
	}

	/**
	 * 観光地名を取得します。
	 * @return 観光地名
	 */
	public String getKankouNm() {
		return kankouNm;
	}

	/**
	 * 観光地名を設定します。
	 * @param kankouNm 観光地名
	 */
	public void setKankouNm(String kankouNm) {
		this.kankouNm = kankouNm;
	}

	/**
	 * ユーザIDを取得します。
	 * @return ユーザID
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * ユーザIDを設定します。
	 * @param userId ユーザID
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * カテゴリ（キー）を取得します。
	 * @return カテゴリ（キー）
	 */
	public String getCategoryKey() {
		return categoryKey;
	}

	/**
	 * カテゴリ（キー）を設定します。
	 * @param categoryKey カテゴリ（キー）
	 */
	public void setCategoryKey(String categoryKey) {
		this.categoryKey = categoryKey;
	}

	/**
	 * カテゴリ（ＭＡＰ）を取得します。
	 * @return カテゴリ（ＭＡＰ）
	 */
	public Map<String, String> getCategory() {
		return category;
	}

	/**
	 * カテゴリ（ＭＡＰ）を設定します。
	 * @param categoryKey カテゴリ（キー）
	 */
	public void setCategory(Map<String, String> category) {
		this.category = category;
	}

	/**
	 * 地方キー（キー）を取得します。
	 * @return 地方キー（キー）
	 */
	public String getTihouKey() {
		return tihouKey;
	}

	/**
	 * 地方キー（キー）を設定します。
	 * @param tihouKey 地方キー（キー）
	 */
	public void setTihouKey(String tihouKey) {
		this.tihouKey = tihouKey;
	}

	/**
	 * 地方（ＭＡＰ）を取得します。
	 * @return 地方（ＭＡＰ）
	 */
	public Map<String, String> getTihou() {
		return tihou;
	}

	/**
	 * 地方（ＭＡＰ）を設定します。
	 * @param tihou 地方（ＭＡＰ）
	 */
	public void setTihou(Map<String, String> tihou) {
		this.tihou = tihou;
	}
	
	/**
	 * 地方（ＭＡＰ）を取得します。
	 * @return 地方（ＭＡＰ）
	 */
	public String getTodouhukenKey() {
		return todouhukenKey;
	}

	/**
	 * 地方（ＭＡＰ）を設定します。
	 * @param todouhukenKey 地方（ＭＡＰ）
	 */
	public void setTodouhukenKey(String todouhukenKey) {
		this.todouhukenKey = todouhukenKey;
	}

	/**
	 * 都道府県（ＭＡＰ）を取得します。
	 * @return 都道府県（ＭＡＰ）
	 */
	public Map<String, String> getTodouhuken() {
		return todouhuken;
	}

	/**
	 * 都道府県（ＭＡＰ）を設定します。
	 * @param todouhuken 都道府県（ＭＡＰ）
	 */
	public void setTodouhuken(Map<String, String> todouhuken) {
		this.todouhuken = todouhuken;
	}
	
	/**
	 * 観光地IDを取得します。
	 * @return 観光地ID
	 */
	public String getKankouId() {
		return kankouId;
	}

	/**
	 * 観光地IDを設定します。
	 * @param kankouId 観光地ID
	 */
	public void setKankouId(String kankouId) {
		this.kankouId = kankouId;
	}
	
	/**
	 * 地方名を取得します。
	 * @return 地方名
	 */
	public String getTihouNm() {
		return tihouNm;
	}

	/**
	 * 地方名を設定します。
	 * @param tihouNm 地方名
	 */
	public void setTihouNm(String tihouNm) {
		this.tihouNm = tihouNm;
	}

	/**
	 * 都道府県名を取得します。
	 * @return 都道府県名
	 */
	public String getTodouhukenNm() {
		return todouhukenNm;
	}

	/**
	 * 都道府県名を設定します。
	 * @param todouhukenNm 都道府県名
	 */
	public void setTodouhukenNm(String todouhukenNm) {
		this.todouhukenNm = todouhukenNm;
	}
	
	/**
	 * カテゴリ名を取得します。
	 * @return カテゴリ名
	 */
	public String getCategoryNm() {
		return categoryNm;
	}

	/**
	 * カテゴリ名を設定します。
	 * @param categoryNm カテゴリ名
	 */
	public void setCategoryNm(String categoryNm) {
		this.categoryNm = categoryNm;
	}
	
	/**
	 * 評価値を取得します。
	 * @return 評価値
	 */
	public String getHyoukati() {
		return hyoukati;
	}

	/**
	 * 評価値を設定します。
	 * @param hyoukati 評価値
	 */
	public void setHhyoukati(String hyoukati) {
		this.hyoukati = hyoukati;
	}	

	/**
	 * 画像パスを取得します。
	 * @return 画像パス
	 */
	public String getImagePath() {
		return imagePath;
	}

	/**
	 * 画像パスを設定します。
	 * @param imagePath 画像パス
	 */
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
}