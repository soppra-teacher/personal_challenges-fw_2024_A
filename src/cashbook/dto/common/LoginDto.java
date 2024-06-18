package cashbook.dto.common;

/**
 * ログイン情報DTOクラス
 * @author soppra
 */
public class LoginDto {

	/** 個人ＩＤ */
	private String kojinId;
	/** 世帯ＩＤ */
	private String setaiId;
	/** 個人名 */
	private String kojinNm;
	/** 世帯主フラグ */
	private String setainushiFlg;

	/**
	 * 個人ＩＤを取得します。
	 * @return 個人ＩＤ
	 */
	public String getKojinId() {
	    return kojinId;
	}

	/**
	 * 個人ＩＤを設定します。
	 * @param kojinId 個人ＩＤ
	 */
	public void setKojinId(String kojinId) {
	    this.kojinId = kojinId;
	}

	/**
	 * 世帯ＩＤを取得します。
	 * @return 世帯ＩＤ
	 */
	public String getSetaiId() {
	    return setaiId;
	}

	/**
	 * 世帯ＩＤを設定します。
	 * @param setaiId 世帯ＩＤ
	 */
	public void setSetaiId(String setaiId) {
	    this.setaiId = setaiId;
	}

	/**
	 * 個人名を取得します。
	 * @return 個人名
	 */
	public String getKojinNm() {
	    return kojinNm;
	}

	/**
	 * 個人名を設定します。
	 * @param kojinNm 個人名
	 */
	public void setKojinNm(String kojinNm) {
	    this.kojinNm = kojinNm;
	}

	/**
	 * 世帯主フラグを取得します。
	 * @return 世帯主フラグ
	 */
	public String getSetainushiFlg() {
	    return setainushiFlg;
	}

	/**
	 * 世帯主フラグを設定します。
	 * @param setainushiFlg 世帯主フラグ
	 */
	public void setSetainushiFlg(String setainushiFlg) {
	    this.setainushiFlg = setainushiFlg;
	}
}
