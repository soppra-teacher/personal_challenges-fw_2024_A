package cashbook.dto.setai;

import cashbook.dto.common.BaseDto;

public class SetaiRegistDto extends BaseDto {
	/** 世帯ID */
	private String setaiId;
	/** 世帯名 */
	private String setaiNm;
	/** 世帯名カナ */
	private String setaiNmKana;
	/** 郵便番号 */
	private String postNo;
	/** 住所 */
	private String address;
	/** 電話番号 */
	private String telNo;

	public String getSetaiId() {
		return setaiId;
	}
	public void setSetaiId(String setaiId) {
		this.setaiId = setaiId;
	}
	public String getSetaiNm() {
		return setaiNm;
	}
	public void setSetaiNm(String setaiNm) {
		this.setaiNm = setaiNm;
	}
	public String getSetaiNmKana() {
		return setaiNmKana;
	}
	public void setSetaiNmKana(String setaiNmKana) {
		this.setaiNmKana = setaiNmKana;
	}
	public String getPostNo() {
		return postNo;
	}
	public void setPostNo(String postNo) {
		this.postNo = postNo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTelNo() {
		return telNo;
	}
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}
}
