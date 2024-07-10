package cashbook.dto.kankou;

import java.util.List;
import java.util.Map;

public class KankouListDto {
	/** 観光名 */
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
	
	

	public List<KankouListDto> getList() {
		return list;
	}

	public void setList(List<KankouListDto> list) {
		this.list = list;
	}

	public String getKankouNm() {
		return kankouNm;
	}

	public void setKankouNm(String kankouNm) {
		this.kankouNm = kankouNm;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getTihouKey() {
		return tihouKey;
	}

	public void setTihouKey(String tihouKey) {
		this.tihouKey = tihouKey;
	}


	public Map<String, String> getTihou() {
		return tihou;
	}

	public void setTihou(Map<String, String> tihou) {
		this.tihou = tihou;
	}
	
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
	
	public String getKankouId() {
		return kankouId;
	}

	public void setKankouId(String kankouId) {
		this.kankouId = kankouId;
	}
	

	public String getTihouNm() {
		return tihouNm;
	}

	public void setTihouNm(String tihouNm) {
		this.tihouNm = tihouNm;
	}

	public String getTodouhukenNm() {
		return todouhukenNm;
	}

	public void setTodouhukenNm(String todouhukenNm) {
		this.todouhukenNm = todouhukenNm;
	}
	
	public String getCategoryNm() {
		return categoryNm;
	}

	public void setCategoryNm(String categoryNm) {
		this.categoryNm = categoryNm;
	}
	
	public String getHyoukati() {
		return hyoukati;
	}

	public void setHhyoukati(String hyoukati) {
		this.hyoukati = hyoukati;
	}	

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
}
