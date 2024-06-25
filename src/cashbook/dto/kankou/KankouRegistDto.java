package cashbook.dto.kankou;

import java.util.Map;

public class KankouRegistDto{
	
	
	/** 都道府県名 （キー） */
	private String todouhukenKey;

	/** 都道府県名 （Ｍａｐ）*/
	private Map<String, String> todouhuken;
	
	/** カテゴリ名 （キー） */
	private String categoryKey;
	
	/** カテゴリ (Ｍａｐ)*/
	private Map<String, String> category;
	
	/** 観光地名 */
	private String kankouNm;

	/** 説明 */
	private String setsumei;

	/** レビュー */
	private String review;
	
	/** 評名 （キー） */
	private String hyokaKey;
	
	/** 評価 (Ｍａｐ)*/
	private Map<String, String> hyoka;
	
	/** 写真パス*/
	private String pictures;


	public String getTodouhukenKey() {
	    return todouhukenKey;
	}

	public void setTodouhukenKey(String todouhukenKey) {
	    this.todouhukenKey = todouhukenKey;
	}

	
	public Map<String, String> getTodouhuken() {
	    return todouhuken;
	}

	public void setTodouhuken(Map<String, String> todouhuken) {
	    this.todouhuken = todouhuken;
	}
	
	public String getCategoryKey() {
	    return categoryKey;
	}

	public void setCategoryKey(String categoryKey) {
	    this.categoryKey = categoryKey;
	}

	
	public Map<String, String> getCategory() {
	    return category;
	}

	public void setCategory(Map<String, String> category) {
	    this.category = category;
	}

	
	public String getKankouNm() {
		return kankouNm;
	}

	public void setKankouNm(String kankouNm) {
		this.kankouNm = kankouNm;
	}
	
	
	public String getSetsumei() {
		return setsumei;
	}

	public void setSetsumei(String setsumei) {
		this.setsumei = setsumei;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String setreview) {
		this.review = setreview;
	}
	
	public String getHyokaKey() {
	    return hyokaKey;
	}

	public void setHyokaKey(String hyokaKey) {
	    this.hyokaKey = hyokaKey;
	}

	
	public Map<String, String> getHyoka() {
	    return hyoka;
	}

	public void setHyoka(Map<String, String> hyoka) {
	    this.hyoka = hyoka;
	}
	
	
	public String getPictures() {
		return pictures;
	}

	public void setPictures(String pictures) {
		this.pictures = pictures;
	}

}
