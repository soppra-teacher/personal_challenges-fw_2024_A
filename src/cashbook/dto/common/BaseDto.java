package cashbook.dto.common;

/**
 * DTO基底クラス
 * @author soppra
 */
public class BaseDto {

	/** リビジョン */
	private String revision;

	/**
	 * リビジョンを取得します。
	 * @return リビジョン
	 */
	public String getRevision() {
	    return revision;
	}

	/**
	 * リビジョンを設定します。
	 * @param revision リビジョン
	 */
	public void setRevision(String revision) {
	    this.revision = revision;
	}
}
