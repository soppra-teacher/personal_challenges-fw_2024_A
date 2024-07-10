package cashbook.dto.kankou;

/**
 * 観光地登録DTOクラス
 * @author soppra
 */
import java.util.Map;

public class KankouRegistDto{
	
	
	/** 都道府県名 （キー） */
	private String todouhukenKey;

	/** 都道府県名 （Ｍａｐ）*/
	private Map<String, String> todouhuken;
	
	/** カテゴリ名 （キー） */
	private String categoryKey;
	
	/** カテゴリ (Ｍａｐ) */
	private Map<String, String> category;
	
	/** 観光地名 */
	private String kankouNm;

	/** 説明 */
	private String setsumei;

	/** レビュー */
	private String review;
	
	/** 評名 （キー） */
	private String hyokaKey;
	
	/** 評価 (Ｍａｐ) */
	private Map<String, String> hyoka;
	
	/** 写真パス */
	private String base64Image;

	/**
	 * 都道府県名 （キー）を取得します。
	 * @return 都道府県名 （キー）
	 */
	public String getTodouhukenKey() {
	    return todouhukenKey;
	}

	/**
	 * 都道府県名 （キー）を設定します。
	 * @param todouhukenKey 都道府県名 （キー）
	 */
	public void setTodouhukenKey(String todouhukenKey) {
	    this.todouhukenKey = todouhukenKey;
	}

	/**
	 * 都道府県名 （Ｍａｐ）を取得します。
	 * @return 都道府県名 （Ｍａｐ）
	 */
	public Map<String, String> getTodouhuken() {
	    return todouhuken;
	}

	/**
	 * 都道府県名 （キー）を設定します。
	 * @param todouhuken 都道府県名 （キー）
	 */
	public void setTodouhuken(Map<String, String> todouhuken) {
	    this.todouhuken = todouhuken;
	}
	
	/**
	 * カテゴリ名 （キー）を取得します。
	 * @return カテゴリ名 （キー）
	 */
	public String getCategoryKey() {
	    return categoryKey;
	}

	/**
	 * カテゴリ名 （キー）を設定します。
	 * @param categoryKey カテゴリ名 （キー）
	 */
	public void setCategoryKey(String categoryKey) {
	    this.categoryKey = categoryKey;
	}

	/**
	 * カテゴリ (Ｍａｐ) を取得します。
	 * @return カテゴリ (Ｍａｐ) 
	 */
	public Map<String, String> getCategory() {
	    return category;
	}

	/**
	 * カテゴリ (Ｍａｐ) を設定します。
	 * @param category カテゴリ (Ｍａｐ) 
	 */
	public void setCategory(Map<String, String> category) {
	    this.category = category;
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
	 * 説明を取得します。
	 * @return 説明
	 */
	public String getSetsumei() {
		return setsumei;
	}

	/**
	 * 説明を設定します。
	 * @param setsumei 説明
	 */
	public void setSetsumei(String setsumei) {
		this.setsumei = setsumei;
	}

	/**
	 * レビューを取得します。
	 * @return レビュー
	 */
	public String getReview() {
		return review;
	}

	/**
	 * レビューを設定します。
	 * @param review レビュー
	 */
	public void setReview(String setreview) {
		this.review = setreview;
	}
	
	/**
	 * 評名 （キー）を取得します。
	 * @return 評名 （キー）
	 */
	public String getHyokaKey() {
	    return hyokaKey;
	}

	/**
	 * 評名 （キー）を設定します。
	 * @param hyokaKey 評名 （キー）
	 */
	public void setHyokaKey(String hyokaKey) {
	    this.hyokaKey = hyokaKey;
	}

	/**
	 * 評価 (Ｍａｐ)を取得します。
	 * @return 評価 (Ｍａｐ)
	 */
	public Map<String, String> getHyoka() {
	    return hyoka;
	}

	/**
	 * 評価 (Ｍａｐ)を設定します。
	 * @param hyoka 評価 (Ｍａｐ)
	 */
	public void setHyoka(Map<String, String> hyoka) {
	    this.hyoka = hyoka;
	}
	
	/**
	 * 写真パスを取得します。
	 * @return 写真パス
	 */
	public String getBase64Image() {
		return base64Image;
	}

	/**
	 * 写真パスを設定します。
	 * @param pictures 写真パス
	 */
	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}

}
