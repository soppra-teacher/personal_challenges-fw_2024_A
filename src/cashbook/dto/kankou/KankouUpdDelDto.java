package cashbook.dto.kankou;

/**
 * 詳細表示・更新削除画面DTOクラス
 * @author soppra
 */
public class KankouUpdDelDto  {

	/** イメージパス */
	private String hiddenImagePath;

	/** 画像パス */
	private String imagePath;
	
	/** 画像パス（js表示用） */
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
	
	/** ログインユーザID */
	private String logUserId;
	
	/** エンコード画像データ */
	private String base64Image;
	
	/**
	 * イメージパスを取得します。
	 * @return イメージパス
	 */
	public String getImagePath() {
		return imagePath;
	}

	/**
	 * イメージパスを設定します。
	 * @param imagePath イメージパス
	 */
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	/**
	 * 画像パスを取得します。
	 * @return 画像パス
	 */
	public String getHiddenImagePath() {
		return hiddenImagePath;
	}

	/**
	 * 画像パスを設定します。
	 * @param hiddenImagePath 画像パス
	 */
	public void setHiddenImagePath(String hiddenImagePath) {
		this.hiddenImagePath = hiddenImagePath;
	}

	/**
	 * 画像パス（js表示用）を取得します。
	 * @return 画像パス（js表示用）
	 */
	public String getProfileImage() {
		return profileImage;
	}

	/**
	 * 画像パス（js表示用）を設定します。
	 * @param profileImage 画像パス（js表示用）
	 */
	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
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
	public void setReview(String review) {
		this.review = review;
	}

	/**
	 * 評価値を取得します。
	 * @return 評価値
	 */
	public String getHyoka() {
		return hyoka;
	}

	/**
	 * 評価値を設定します。
	 * @param hyoka 評価値
	 */
	public void setHyoka(String hyoka) {
		this.hyoka = hyoka;
	}

	/**
	 * 観光IDを取得します。
	 * @return 観光ID
	 */
	public String getKankouId() {
		return kankouId;
	}

	/**
	 * 観光IDを設定します。
	 * @param kankouId 観光ID
	 */
	public void setKankouId(String kankouId) {
		this.kankouId = kankouId;
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
	 * 評価値ジャッジを取得します。
	 * @return 評価値ジャッジ
	 */
	public String getHyokaJudge() {
		return hyokaJudge;
	}

	/**
	 * 評価値ジャッジを設定します。
	 * @param hyokaJudge 評価値ジャッジ
	 */
	public void setHyokaJudge(String hyokaJudge) {
		this.hyokaJudge = hyokaJudge;
	}

	/**
	 * 評価値ジャッジを取得します。
	 * @return 評価値ジャッジ
	 */
	public String getLogUserId() {
		return logUserId;
	}

	/**
	 * ログインユーザIDを設定します。
	 * @param logUserId ログインユーザID
	 */
	public void setLogUserId(String logUserId) {
		this.logUserId = logUserId;
	}

	/**
	 * エンコード画像データを取得します。
	 * @return エンコード画像データ
	 */
	public String getBase64Image() {
		return base64Image;
	}

	/**
	 * エンコード画像データを設定します。
	 * @param base64Image エンコード画像データ
	 */
	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}

}
