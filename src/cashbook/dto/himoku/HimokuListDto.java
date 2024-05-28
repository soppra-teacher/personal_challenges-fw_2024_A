package cashbook.dto.himoku;

import java.util.List;
import java.util.Map;

import cashbook.dto.common.BaseDto;

/**
 * 費目マスタメンテ画面用DTO
 * @author soppra
 */
public class HimokuListDto extends BaseDto {

	/** 費目名 */
	private String himokuNm;
	/** 費目区分（キー） */
	private String himokuKbnKey;
	/** 費目区分（Ｍａｐ） */
	private Map<String, String> himokuKbn;
	/** 費目マスタ一覧 */
	private List<HimokuRegistDto> list;

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
	 * 費目区分（Ｍａｐ）を取得します。
	 * @return 費目区分（Ｍａｐ）
	 */
	public Map<String,String> getHimokuKbn() {
	    return himokuKbn;
	}
	/**
	 * 費目区分（Ｍａｐ）を設定します。
	 * @param himokuKbn 費目区分（Ｍａｐ）
	 */
	public void setHimokuKbn(Map<String,String> himokuKbn) {
	    this.himokuKbn = himokuKbn;
	}
	/**
	 * 費目マスタ一覧を取得します。
	 * @return 費目マスタ一覧
	 */
	public List<HimokuRegistDto> getList() {
	    return list;
	}
	/**
	 * 費目マスタ一覧を設定します。
	 * @param list 費目マスタ一覧
	 */
	public void setList(List<HimokuRegistDto> list) {
	    this.list = list;
	}
}
