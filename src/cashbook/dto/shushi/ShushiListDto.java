package cashbook.dto.shushi;

import java.util.List;
import java.util.Map;

import cashbook.dto.common.BaseDto;
import cashbook.util.CommonUtil;

public class ShushiListDto extends BaseDto {
	/** 画面ID */
	private String gamenId;
	/** 年（キー） */
	private String yearKey;
	/** 年（Ｍａｐ） */
	private Map<String,String> yearMap;
	/** 月（キー） */
	private String monthKey;
	/** 月（Ｍａｐ） */
	private Map<String,String> monthMap;
	/** 個人名 （キー） */
	private String kojinNmKey;
	/** 個人名 （Ｍａｐ） */
	private Map<String,String> kojinNm;
	/** 費目コード */
	private String himokuCd;
	/** 費目名 （キー） */
	private String himokuNmKey;
	/** 費目名 （Ｍａｐ） */
	private Map<String,String> himokuNm;
	/** 収入合計 */
	private String shunyuTotal;
	/** 支出合計 */
	private String shishutsuTotal;
	/** 残高合計 */
	private String zandakaTotal;
	/** 収支登録一覧 */
	private List<ShushiRegistDto> list;

	/**
	 * 画面IDを取得します。
	 * @return 画面ID
	 */
	public String getGamenId() {
	    return gamenId;
	}
	/**
	 * 画面IDを設定します。
	 * @param 画面ID
	 */
	public void setGamenId(String gamenId) {
	    this.gamenId = gamenId;
	}
	/**
	 * 年（キー）を取得します。
	 * @return 年（キー）
	 */
	public String getYearKey() {
	    return yearKey;
	}
	/**
	 * 年（キー）を設定します。
	 * @param 年（キー）
	 */
	public void setYearKey(String yearKey) {
	    this.yearKey = yearKey;
	}
	/**
	 * 年（Ｍａｐ）を取得します。
	 * @return 年（Ｍａｐ）
	 */
	public Map<String,String> getYearMap() {
	    return yearMap;
	}
	/**
	 * 年（Ｍａｐ）を設定します。
	 * @param 年（Ｍａｐ）
	 */
	public void setYearMap(Map<String,String> yearMap) {
	    this.yearMap = yearMap;
	}
	/**
	 * 月（キー）を取得します。
	 * @return 月（キー）
	 */
	public String getMonthKey() {
	    return monthKey;
	}
	/**
	 * 月（キー）を設定します。
	 * @param 月（キー）
	 */
	public void setMonthKey(String monthKey) {
	    this.monthKey = monthKey;
	}
	/**
	 * 月（Ｍａｐ）を取得します。
	 * @return 月（Ｍａｐ）
	 */
	public Map<String,String> getMonthMap() {
	    return monthMap;
	}
	/**
	 * 月（Ｍａｐ）を設定します。
	 * @param 月（Ｍａｐ）
	 */
	public void setMonthMap(Map<String,String> monthMap) {
	    this.monthMap = monthMap;
	}
	/**
	 * 個人名 （キー）を取得します。
	 * @return 個人名 （キー）
	 */
	public String getKojinNmKey() {
	    return kojinNmKey;
	}
	/**
	 * 個人名 （キー）を設定します。
	 * @param kojinNmKey 個人名 （キー）
	 */
	public void setKojinNmKey(String kojinNmKey) {
	    this.kojinNmKey = kojinNmKey;
	}
	/**
	 * 個人名 （Ｍａｐ）を取得します。
	 * @return 個人名 （Ｍａｐ）
	 */
	public Map<String,String> getKojinNm() {
	    return kojinNm;
	}
	/**
	 * 個人名 （Ｍａｐ）を設定します。
	 * @param kojinNm 個人名 （Ｍａｐ）
	 */
	public void setKojinNm(Map<String,String> kojinNm) {
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
	public Map<String,String> getHimokuNm() {
	    return himokuNm;
	}
	/**
	 * 費目名 （Ｍａｐ）を設定します。
	 * @param himokuNm 費目名 （Ｍａｐ）
	 */
	public void setHimokuNm(Map<String,String> himokuNm) {
	    this.himokuNm = himokuNm;
	}
	/**
	 * 収支登録一覧を取得します。
	 * @return 収支登録一覧
	 */
	public List<ShushiRegistDto> getList() {
	    return list;
	}
	/**
	 * 収入合計を取得します。
	 * @return 収入合計
	 */
	public String getShunyuTotal() {
	    return shunyuTotal;
	}
	/**
	 * 収入合計を取得します。
	 * @return 収入合計
	 */
	public String getShunyuTotalFormat() {
	    return CommonUtil.formatComma(shunyuTotal);
	}
	/**
	 * 収入合計を設定します。
	 * @param 収入合計
	 */
	public void setShunyuTotal(String shunyuTotal) {
	    this.shunyuTotal = shunyuTotal;
	}
	/**
	 * 支出合計を取得します。
	 * @return 支出合計
	 */
	public String getShishutsuTotal() {
	    return shishutsuTotal;
	}
	/**
	 * 支出合計を取得します。
	 * @return 支出合計
	 */
	public String getShishutsuTotalFormat() {
	    return CommonUtil.formatComma(shishutsuTotal);
	}
	/**
	 * 支出合計を設定します。
	 * @param 支出合計
	 */
	public void setShishutsuTotal(String shishutsuTotal) {
	    this.shishutsuTotal = shishutsuTotal;
	}
	/**
	 * 残高合計を取得します。
	 * @return 残高合計
	 */
	public String getZandakaTotal() {
	    return zandakaTotal;
	}
	/**
	 * 残高合計を取得します。
	 * @return 残高合計
	 */
	public String getZandakaTotalFormat() {
	    return CommonUtil.formatComma(zandakaTotal);
	}
	/**
	 * 残高合計を設定します。
	 * @param 残高合計
	 */
	public void setZandakaTotal(String zandakaTotal) {
	    this.zandakaTotal = zandakaTotal;
	}
	/**
	 * 収支登録一覧を設定します。
	 * @param list 収支登録一覧
	 */
	public void setList(List<ShushiRegistDto> list) {
	    this.list = list;
	}

}
