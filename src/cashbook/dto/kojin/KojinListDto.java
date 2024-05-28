package cashbook.dto.kojin;

import java.util.List;
import java.util.Map;

import cashbook.dto.common.BaseDto;

public class KojinListDto extends BaseDto {

	/** 個人ID */
	private String kojinId;

	/** 個人名 */
	private String kojinNm;

	/** 個人名カナ */
	private String kojinNmkana;

	/** 性別区分（キー） */
	private String seibetsuKbnKey;

	/** 性別区分（ＭＡＰ） */
	private Map<String, String> seibetsuKbn;

	/** 続柄（キー） */
	private String zokugaraKey;

	/** 続柄（ＭＡＰ） */
	private Map<String, String> zokugara;

	/** 世帯主フラグ */
	private String setaiNusiFlg;

	/** 個人マスタ一覧 */
	private List<KojinRegistDto> list;

	public String getKojinId() {
		return kojinId;
	}

	public void setKojinId(String kojinId) {
		this.kojinId = kojinId;
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

	public String getSeibetsuKbnKey() {
		return seibetsuKbnKey;
	}

	public void setSeibetsuKbnKey(String seibetsuKbnKey) {
		this.seibetsuKbnKey = seibetsuKbnKey;
	}

	public Map<String, String> getSeibetsuKbn() {
		return seibetsuKbn;
	}

	public void setSeibetsuKbn(Map<String, String> seibetsuKbn) {
		this.seibetsuKbn = seibetsuKbn;
	}

	public String getZokugaraKey() {
		return zokugaraKey;
	}

	public void setZokugaraKey(String zokugaraKey) {
		this.zokugaraKey = zokugaraKey;
	}


	public Map<String, String> getzokugara() {
		return zokugara;
	}
	public void setzokugara(Map<String, String> zokugara) {
		this.zokugara = zokugara;
	}

	public String getSetaiNusiFlg() {
		return setaiNusiFlg;
	}

	public void setSetaiNusiFlg(String setaiNusiFlg) {
		this.setaiNusiFlg = setaiNusiFlg;
	}

	public List<KojinRegistDto> getList() {
		return list;
	}
	public void setList(List<KojinRegistDto> list) {
		this.list = list;
	}


}
