package cashbook.dto.kankou;

import java.util.List;
import java.util.Map;

import cashbook.dto.common.BaseDto;

public class KankouListDto extends BaseDto {
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
	
//	/** 観光一覧 */
	private List<KankouListDto> list;
	/** 画像パス */
	private String imagePath;
	
	

	/** 個人ID */
	private String kojinId;

	/** 世帯主フラグ */
	private String setaiNusiFlg;

//	/** 個人マスタ一覧 */
//	private List<KojinRegistDto> list;

	public String getKojinId() {
		return kojinId;
	}

	public void setKojinId(String kojinId) {
		this.kojinId = kojinId;
	}

	public String getSetaiNusiFlg() {
		return setaiNusiFlg;
	}

	public void setSetaiNusiFlg(String setaiNusiFlg) {
		this.setaiNusiFlg = setaiNusiFlg;
	}

	public List<KankouListDto> getList() {
		return list;
	}

	public void setList(List<KankouListDto> list) {
		this.list = list;
	}
//***************************観光地検索システム*****************************************
	//観光地名**********************
	public String getKankouNm() {
		return kankouNm;
	}

	public void setKankouNm(String kankouNm) {
		this.kankouNm = kankouNm;
	}
	//********************************

	//ユーザID**********************
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	//********************************

	//カテゴリ名**********************
	public String getCategoryKey() {
		return categoryKey;
	}

	public void setCategoryKey(String categoryKey) {
		this.categoryKey = categoryKey;
	}

	//********************************
	//カテゴリ(MAP)**********************
	public Map<String, String> getCategory() {
		return category;
	}

	public void setCategory(Map<String, String> category) {
		this.category = category;
	}

	//********************************
	//地方キー**********************
	public String getTihouKey() {
		return tihouKey;
	}

	public void setTihouKey(String tihouKey) {
		this.tihouKey = tihouKey;
	}

	//********************************
	//地方(MAP)**********************
	public Map<String, String> getTihou() {
		return tihou;
	}

	public void setTihou(Map<String, String> tihou) {
		this.tihou = tihou;
	}
	//********************************
	//都道府県キー**********************
	public String getTodouhukenKey() {
		return todouhukenKey;
	}

	public void setTodouhukenKey(String todouhukenKey) {
		this.todouhukenKey = todouhukenKey;
	}

	//********************************
	//都道府県(MAP)**********************
	public Map<String, String> getTodouhuken() {
		return todouhuken;
	}

	public void setTodouhuken(Map<String, String> todouhuken) {
		this.todouhuken = todouhuken;
	}
	//********************************
	//観光地ID**********************
	public String getKankouId() {
		return kankouId;
	}

	public void setKankouId(String kankouId) {
		this.kankouId = kankouId;
	}
	//********************************
	//地方名**********************
	public String getTihouNm() {
		return tihouNm;
	}

	public void setTihouNm(String tihouNm) {
		this.tihouNm = tihouNm;
	}
	//********************************	
	//都道府県名**********************
	public String getTodouhukenNm() {
		return todouhukenNm;
	}

	public void setTodouhukenNm(String todouhukenNm) {
		this.todouhukenNm = todouhukenNm;
	}
	//********************************	
	//カテゴリ名**********************
	public String getCategoryNm() {
		return categoryNm;
	}

	public void setCategoryNm(String categoryNm) {
		this.categoryNm = categoryNm;
	}
	//********************************	
	//評価値**********************
	public String getHyoukati() {
		return hyoukati;
	}

	public void setHhyoukati(String hyoukati) {
		this.hyoukati = hyoukati;
	}
	//********************************	
	
	//画像パス**********************
	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	//********************************	
}
