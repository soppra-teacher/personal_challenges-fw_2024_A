package cashbook.dto.kankou;

public class KankouUpdDelDto  {

	/** イメージパス */
	private String hiddenImagePath;

	/** 画像パス */
	private String imagePath;
	
	private String profileImage;
	/** 観光地名 */
	private String kankouNm;
	
	/** カテゴリ名 */
	private String categoryNm;
	
	/** 都道府県名 */
	private String todouhukenNm;
	
	/** 地方名 */
	private String tihouNm;
	
	/** 説明 */
	private String setsumei;
	
	/** レビュー */
	private String review;
	
	/** 評価値 */
	private String hyoka;
	
	/** 観光ID */
	private String kankouId;
	
	/** ユーザID */
	private String userId;
	
	/** 評価値ジャッジ */
	private String hyokaJudge;
	
	/** ログインユーザId */
	private String logUserId;
	
	/** エンコード画像データ */
	private String base64Image;
	
	
	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getHiddenImagePath() {
		return hiddenImagePath;
	}

	public void setHiddenImagePath(String hiddenImagePath) {
		this.hiddenImagePath = hiddenImagePath;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public String getKankouNm() {
		return kankouNm;
	}

	public void setKankouNm(String kankouNm) {
		this.kankouNm = kankouNm;
	}

	public String getCategoryNm() {
		return categoryNm;
	}

	public void setCategoryNm(String categoryNm) {
		this.categoryNm = categoryNm;
	}

	public String getTodouhukenNm() {
		return todouhukenNm;
	}

	public void setTodouhukenNm(String todouhukenNm) {
		this.todouhukenNm = todouhukenNm;
	}

	public String getTihouNm() {
		return tihouNm;
	}

	public void setTihouNm(String tihouNm) {
		this.tihouNm = tihouNm;
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

	public void setReview(String review) {
		this.review = review;
	}

	public String getHyoka() {
		return hyoka;
	}

	public void setHyoka(String hyoka) {
		this.hyoka = hyoka;
	}

	public String getKankouId() {
		return kankouId;
	}

	public void setKankouId(String kankouId) {
		this.kankouId = kankouId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getHyokaJudge() {
		return hyokaJudge;
	}

	public void setHyokaJudge(String hyokaJudge) {
		this.hyokaJudge = hyokaJudge;
	}

	public String getLogUserId() {
		return logUserId;
	}

	public void setLogUserId(String logUserId) {
		this.logUserId = logUserId;
	}

	public String getBase64Image() {
		return base64Image;
	}

	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}

}
