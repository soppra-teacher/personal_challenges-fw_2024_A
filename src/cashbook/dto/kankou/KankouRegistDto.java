package cashbook.dto.kankou;

import java.util.Map;

import cashbook.dto.common.BaseDto;

public class KankouRegistDto extends BaseDto {

	/** 個人ID */
	private String hiddenImagePath;
	
	/** 個人ID */
	private String kojinId;

	/** パスワード */
	private String pass;

	/** 世帯ID（キー） */
	private String setaiIdKey;

	/** 世帯ID */
	private String setaiId;

	/** 世帯名 （キー） */
	private String setaiNmKey;

	/** 世帯名 （Ｍａｐ）*/
	private Map<String, String> setaiNm;

	/** 個人名 */
	private String kojinNm;

	/** 個人名カナ */
	private String kojinNmkana;

	/** 性別区分 */
	private String seibetsuKbn;

	/** 性別名 */
	private String seibetsuNm;

	/** 続柄（キー） */
	private String zokugaraKey;

	/** 続柄（ＭＡＰ） */
	private Map<String, String> zokugara;

	/** 続柄名 */
	private String zokugaraNm;

	/** 世帯主フラグ */
	private String setaiNusiFlg;

	/** 世帯主名 */
	private String setaiNusiNm;
	////////////////////////////////////////
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
	
	private String kankouId;
	
	private String userId;
	
	private String hyokaJudge;
	
	private String logUserId;
	
	private String base64Image;
	
	
//////////////////////////////////////////////////////
	public String getKojinId() {
		return kojinId;
	}

	public void setKojinId(String kojinId) {
		this.kojinId = kojinId;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getSetaiIdKey() {
		return setaiIdKey;
	}

	public void setSetaiIdKey(String setaiIdKey) {
		this.setaiIdKey = setaiIdKey;
	}

	public String getSetaiId() {
		return setaiId;
	}

	public void setSetaiId(String setaiId) {
		this.setaiId = setaiId;
	}

	public String getSetaiNmKey() {
	    return setaiNmKey;
	}

	public void setSetaiNmKey(String setaiNmKey) {
	    this.setaiNmKey = setaiNmKey;
	}

	public Map<String, String> getSetaiNm() {
	    return setaiNm;
	}

	public void setSetaiNm(Map<String, String> setaiNm) {
	    this.setaiNm = setaiNm;
	}

	public String getKojinNm() {
		return kojinNm;
	}

	public void setKojinNm(String kojinNm) {
		this.kojinNm = kojinNm;
	}

	public String getKojinNmkana() {
		return kojinNmkana;
	}

	public void setKojinNmkana(String kojinNmkana) {
		this.kojinNmkana = kojinNmkana;
	}

	public String getSeibetsuKbn() {
		return seibetsuKbn;
	}

	public void setSeibetsuKbn(String seibetsuKbn) {
		this.seibetsuKbn = seibetsuKbn;
	}

	public String getSeibetsuNm() {
		return seibetsuNm;
	}

	public void setSeibetsuNm(String seibetsuNm) {
		this.seibetsuNm = seibetsuNm;
	}

	public Map<String, String> getZokugara() {
		return zokugara;
	}

	public void setZokugara(Map<String, String> zokugara) {
		this.zokugara = zokugara;
	}

	public String getZokugaraKey() {
		return zokugaraKey;
	}

	public void setZokugaraKey(String zokugaraKey) {
		this.zokugaraKey = zokugaraKey;
	}

	public String getZokugaraNm() {
		return zokugaraNm;
	}

	public void setZokugaraNm(String zokugaraNm) {
		this.zokugaraNm = zokugaraNm;
	}

	public String getSetaiNusiFlg() {
		return setaiNusiFlg;
	}

	public void setSetaiNusiFlg(String setaiNusiFlg) {
		this.setaiNusiFlg = setaiNusiFlg;
	}

	public String getSetaiNusiNm() {
		return setaiNusiNm;
	}

	public void setSetaiNusiNm(String setaiNusiNm) {
		this.setaiNusiNm = setaiNusiNm;
	}
	
	//画像パス**********************
	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	//********************************

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
