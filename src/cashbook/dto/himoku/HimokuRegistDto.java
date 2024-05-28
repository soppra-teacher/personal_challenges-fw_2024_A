package cashbook.dto.himoku;

import java.util.Map;

import cashbook.dto.common.BaseDto;

/**
 * 費目マスタ登録画面用DTO
 * @author soppra
 */
public class HimokuRegistDto extends BaseDto {

	/** 費目ＣＤ */
	private String himokuCd;
	/** 費目名 */
	private String himokuNm;
	/** 費目区分（キー） */
	private String himokuKbnKey;
	/** 費目区分 （Ｍａｐ）*/
	private Map<String, String> himokuKbn;
	/** 費目区分名称 */
	private String himokuKbnNm;

	/**
	 * 費目ＣＤを取得します。
	 * @return 費目ＣＤ
	 */
	public String getHimokuCd() {
	    return himokuCd;
	}
	/**
	 * 費目ＣＤを設定します。
	 * @param himokuCd 費目ＣＤ
	 */
	public void setHimokuCd(String himokuCd) {
	    this.himokuCd = himokuCd;
	}
	/**
	 * 費目名を取得します。
	 * @return 費目名
	 */
	public String getHimokuNm() {
	    return himokuNm;
	}
	/**
	 * 費目名を設定します。
	 * @param himokuNm 費目名
	 */
	public void setHimokuNm(String himokuNm) {
	    this.himokuNm = himokuNm;
	}
	/**
	 * 費目区分（キー）を取得します。
	 * @return 費目区分（キー）
	 */
	public String getHimokuKbnKey() {
	    return himokuKbnKey;
	}
	/**
	 * 費目区分（キー）を設定します。
	 * @param himokuKbnKey 費目区分（キー）
	 */
	public void setHimokuKbnKey(String himokuKbnKey) {
	    this.himokuKbnKey = himokuKbnKey;
	}
	/**
	 * 費目区分 （Ｍａｐ）を取得します。
	 * @return 費目区分 （Ｍａｐ）
	 */
	public Map<String,String> getHimokuKbn() {
	    return himokuKbn;
	}
	/**
	 * 費目区分 （Ｍａｐ）を設定します。
	 * @param himokuKbn 費目区分 （Ｍａｐ）
	 */
	public void setHimokuKbn(Map<String,String> himokuKbn) {
	    this.himokuKbn = himokuKbn;
	}
	/**
	 * 費目区分名称を取得します。
	 * @return 費目区分名称
	 */
	public String getHimokuKbnNm() {
	    return himokuKbnNm;
	}
	/**
	 * 費目区分名称を設定します。
	 * @param himokuKbnNm 費目区分名称
	 */
	public void setHimokuKbnNm(String himokuKbnNm) {
	    this.himokuKbnNm = himokuKbnNm;
	}
}
