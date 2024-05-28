package cashbook.dto.shushi;

import java.util.Map;

import cashbook.dto.common.BaseDto;
import cashbook.util.CommonUtil;

public class ShushiRegistDto extends BaseDto {

	/** 収支（キー） */
	private String shushiKey;
	/** 収支ID */
	private String shushiId;
	/** 収支明細ID */
	private String shushiDtlId;
	/** 年月 */
	private String ym;
	/** 日付 */
	private String ymd;
	/** 個人ID */
	private String kojinId;
	/** 個人名 */
	private String kojinNm;
	/** 費目コード */
	private String himokuCd;
	/** 費目名 （キー） */
	private String himokuNmKey;
	/** 費目名 （Ｍａｐ）*/
	private Map<String, String> himokuNm;
	/** 費目名称 */
	private String himokuNmSei;
	/** 費目区分（キー） */
	private String himokuKbnKey;
	/** 費目区分 （Ｍａｐ）*/
	private Map<String, String> himokuKbn;
	/** 費目区分名称 */
	private String himokuKbnNm;
	/** 金額 */
	private String kingaku;
	/** 収入 */
	private String shunyu;
	/** 支出 */
	private String shishutsu;
	/** 残高 */
	private String zandaka;

	/**
	 * 収支（キー）を取得します。
	 * @return 収支（キー）
	 */
	public String getShushiKey() {
	    return shushiKey;
	}
	/**
	 * 収支（キー）を設定します。
	 * @param shushiKey 収支（キー）
	 */
	public void setShushiKey(String shushiKey) {
	    this.shushiKey = shushiKey;
	}
	/**
	 * 収支IDを取得します。
	 * @return 収支ID
	 */
	public String getShushiId() {
	    return shushiId;
	}
	/**
	 * 収支IDを設定します。
	 * @param shushiId 収支ID
	 */
	public void setShushiId(String shushiId) {
	    this.shushiId = shushiId;
	}
	/**
	 * 収支明細IDを取得します。
	 * @return 収支明細ID
	 */
	public String getShushiDtlId() {
	    return shushiDtlId;
	}
	/**
	 * 収支明細IDを設定します。
	 * @param shushiDtlId 収支明細ID
	 */
	public void setShushiDtlId(String shushiDtlId) {
	    this.shushiDtlId = shushiDtlId;
	}
	/**
	 * 年月を取得します。
	 * @return 年月
	 */
	public String getYm() {
	    return ym;
	}
	/**
	 * 年月を設定します。
	 * @param ym 年月
	 */
	public void setYm(String ym) {
	    this.ym = ym;
	}
	/**
	 * 日付を取得します。
	 * @return 日付
	 */
	public String getYmd() {
	    return ymd;
	}
	/**
	 * 日付を設定します。
	 * @param ymd 日付
	 */
	public void setYmd(String ymd) {
	    this.ymd = ymd;
	}
	/**
	 * 個人IDを取得します。
	 * @return 個人ID
	 */
	public String getKojinId() {
	    return kojinId;
	}
	/**
	 * 個人IDを設定します。
	 * @param kojinId 個人ID
	 */
	public void setKojinId(String kojinId) {
	    this.kojinId = kojinId;
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
	 * 費目コードを取得します。
	 * @return 費目コード
	 */
	public String getHimokuCd() {
	    return himokuCd;
	}
	/**
	 * 費目コードを設定します。
	 * @param himokuCd 費目コード
	 */
	public void setHimokuCd(String himokuCd) {
	    this.himokuCd = himokuCd;
	}
	/**
	 * 費目名 （キー）を取得します。
	 * @return 費目名 （キー）
	 */
	public String getHimokuNmKey() {
	    return himokuNmKey;
	}
	/**
	 * 費目名 （キー）を設定します。
	 * @param himokuNmKey 費目名 （キー）
	 */
	public void setHimokuNmKey(String himokuNmKey) {
	    this.himokuNmKey = himokuNmKey;
	}
	/**
	 * 費目名 （Ｍａｐ）を取得します。
	 * @return 費目名 （Ｍａｐ）
	 */
	public Map<String, String> getHimokuNm() {
	    return himokuNm;
	}
	/**
	 * 費目名 （Ｍａｐ）を設定します。
	 * @param himokuNm 費目名 （Ｍａｐ）
	 */
	public void setHimokuNm(Map<String, String> himokuNm) {
	    this.himokuNm = himokuNm;
	}
	/**
	 * 費目名称を取得します。
	 * @return 費目名称
	 */
	public String getHimokuNmSei() {
	    return himokuNmSei;
	}
	/**
	 * 費目名称を設定します。
	 * @param himokuNmSei 費目名称
	 */
	public void setHimokuNmSei(String himokuNmSei) {
	    this.himokuNmSei = himokuNmSei;
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
	/**
	 * 金額を取得します。
	 * @return 金額
	 */
	public String getKingaku() {
	    return kingaku;
	}
	/**
	 * 金額を取得します。
	 * @return 金額
	 */
	public String getKingakuFormat() {
	    return CommonUtil.formatComma(kingaku);
	}
	/**
	 * 金額を設定します。
	 * @param kingaku 金額
	 */
	public void setKingaku(String kingaku) {
	    this.kingaku = kingaku;
	}
	/**
	 * 収入を取得します。
	 * @return 収入
	 */
	public String getShunyu() {
	    return shunyu;
	}
	/**
	 * 収入を取得します。
	 * @return 収入
	 */
	public String getShunyuFormat() {
	    return CommonUtil.formatComma(shunyu);
	}
	/**
	 * 収入を設定します。
	 * @param shunyu 収入
	 */
	public void setShunyu(String shunyu) {
	    this.shunyu = shunyu;
	}
	/**
	 * 支出を取得します。
	 * @return 支出
	 */
	public String getShishutsu() {
	    return shishutsu;
	}
	/**
	 * 支出を取得します。
	 * @return 支出
	 */
	public String getShishutsuFormat() {
	    return CommonUtil.formatComma(shishutsu);
	}
	/**
	 * 支出を設定します。
	 * @param shishutsu 支出
	 */
	public void setShishutsu(String shishutsu) {
	    this.shishutsu = shishutsu;
	}
	/**
	 * 残高を取得します。
	 * @return 残高
	 */
	public String getZandaka() {
	    return zandaka;
	}
	/**
	 * 残高を取得します。
	 * @return 残高
	 */
	public String getZandakaFormat() {
	    return CommonUtil.formatComma(zandaka);
	}
	/**
	 * 残高を設定します。
	 * @param zandaka 残高
	 */
	public void setZandaka(String zandaka) {
	    this.zandaka = zandaka;
	}
}
