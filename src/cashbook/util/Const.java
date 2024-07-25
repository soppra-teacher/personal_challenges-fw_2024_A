package cashbook.util;

/**
 * 共通定数クラス
 * @author soppra
 */
public class Const {

	/** 空文字 */
	public static final String EMPTY = "";

	/** 遷移先 成功 */
	public static final String ACTION_FOWARD_SUCCESS = "success";
	/** 遷移先 失敗 */
	public static final String ACTION_FOWARD_ERROR = "error";
	/** 遷移先 検索 */
	public static final String ACTION_FOWARD_SEARCH = "search";
	/** 遷移先 削除 */
	public static final String ACTION_FOWARD_DELETE = "delete";
	/** 遷移先 登録 */
	public static final String ACTION_FOWARD_INSERT = "insert";
	/** 遷移先 更新 */
	public static final String ACTION_FOWARD_UPDATE = "update";
	/** 遷移先 戻る(メニュー) */
	public static final String ACTION_FOWARD_BACK_MENU = "backMenu";
	/** 遷移先 戻る(一覧) */
	public static final String ACTION_FOWARD_BACK_LIST = "backList";
	/** 遷移先 オペレーション */
	public static final String ACTION_FOWARD_OPERATION = "operation";
	/** 遷移先 再検索 */
	public static final String ACTION_FOWARD_RESERCH = "reserch";

	/** ログイン失敗メッセージ */
	public static final String MSG_ERRORS_LOGIN_ERROR = "loginForm.error.login";
	/** 検索結果0件メッセージ */
	public static final String MSG_ERRORS_NO_DATA = "errors.noData";
	/** 削除対象なしメッセージ */
	public static final String MSG_ERRORS_NO_DELETE = "errors.checkDel";
	/** キー重複データ存在ありメッセージ */
	public static final String MSG_ERRORS_PRIMARY_KEY = "errors.overLap";
	/** 排他エラーメッセージ */
	public static final String MSG_ERRORS_DATA_LOCK = "errors.lock";
	/** パスワード不一致メッセージ */
	public static final String MSG_ERRORS_PASS_FUITTI = "userRegistForm.error.userpass";
	/** 更新失敗メッセージ */
	public static final String MSG_ERRORS_NO_UPD = "errors.overlap.upd";
	/** 削除失敗メッセージ */
	public static final String MSG_ERRORS_NO_DEL = "errors.over.lap.delete";
	/** 画像削除メッセージ */
	public static final String MSG_ERRORS_NO_FILE = "errors.overlap.fileUpd";
	/** 画像更新失敗メッセージ */
	public static final String MSG_ERRORS_NO_FILE_UPD = "errors.overlap.fileDel";

	/** 登録完了メッセージ */
	public static final String MSG_SUCCESS_INSERT = "messages.success.insert";
	/** 更新完了メッセージ */
	public static final String MSG_SUCCESS_UPDATE = "messages.success.update";
	/** 削除完了メッセージ */
	public static final String MSG_SUCCESS_DELETE = "messages.success.delete";

	/** ユーザ登録完了メッセージ */
	public static final String MSG_SUCCESS_INSERT_USER = "userRegistForm.messages.success.insert.user";
	
	/** 削除対象チェックボックス */
	public static final String ITEM_CHECKBOX_DELETE = "checkDel";

	
	/************* セッション情報 *************/
	/** ログイン画面 DTO */
	public static final String SESSION_LOGIN_DTO = "LOGIN_DTO";
	/** ユーザ登録画面 DTO */
	public static final String SESSION_REGIST_DTO_USER = "USER_REGIST_DTO";
	/** ユーザ登録画面 戻り先 */
	public static final String SESSION_REGIST_BACK_USER = "USER_REGIST_BACK";
	/** ユーザ登録画面 メッセージ  */
	public static final String SESSION_REGIST_MESSAGE_USER = "USER_REGIST_MESSAGE";
	/** ユーザ登録画面 セッション保持  */
	public static final String SESSION_REGIST_SESSION_FORM_USER = "REGIST_SESSION_FORM_USER";
	/** ユーザ登録画面 登録成功判定（OK）  */
	public static final String SESSION_REGIST_HIDDEN_OK = "1";
	/** ユーザ登録画面 登録成功判定（NG）  */
	public static final String SESSION_REGIST_HIDDEN_NG = "0";
	
	/** 観光地登録画面 DTO */
	public static final String SESSION_REGIST_DTO_KANKOU = "KANKOU_REGIST_DTO";
	/** 観光地登録画面 戻り先 */
	public static final String SESSION_REGIST_BACK_KANKOU = "KANKOU_REGIST_BACK";
	/** 観光地登録画面 再検索値 */
	public static final String SESSION_REGIST_RE_SEARCH_KANKOU = "KANKOU_REGIST_RE_SEARCH";
	/** 観光地登録画面 メッセージ */
	public static final String SESSION_REGIST_MESSAGE_KANKOU = "KANKOU_REGIST_MESSAGE";
	/** 観光地登録画面 DTO */
	public static final String SESSION_LIST_DTO_KANKOU = "KANKOU_LIST_DTO";
	/** 観光地登録画面 再検索値 */
	public static final String SESSION_LIST_RE_SEARCH_KANKOU = "KANKOU_LIST_RE_SEARCH";
	/** 観光地登録画面 メッセージ */
	public static final String SESSION_LIST_MESSAGE_KANKOU = "KANKOU_LIST_MESSAGE";
	/** 重複データ存在ありメッセージ */
	public static final String MSG_ERRORS_KANKOU_DATA = "errors.overlap.kankou";
	/** トランザクションエラーメッセージ */
	public static final String MSG_ERRORS_KANKOU_DATA_REGIST = "errors.kankouRegist";
	/** 写真処理の失敗メッセージ */
	public static final String MSG_ERRORS_IMAGE_EXEPTION = "errors.image.kankou";
	
	/** 観光地更新削除画面 戻り先 変更 */
	public static final String SESSION_UPD_DEL_BACK = "KANKOU_UPD_DEL_BACK";
	
	/** 更新削除画面 DTO */
	public static final String SESSION_UPD_DEL_DTO = "KANKOU_UPD_DEL_DTO";
	/** 観光地更新削除画面 再検索値 */
	public static final String SESSION_UPD_DEL_RE_SEARCH_KANKOU = "SESSION_UPD_DEL_RE_SEARCH_KANKOU";
	/** 観光地登録画面 メッセージ */
	public static final String SESSION_UPD_DEL_MESSAGE_KANKOU = "KANKOU_UPD_DEL_MESSAGE";
	/** 観光地更新削除画面 戻り先 変更 */
	public static final String SESSION_REGIST_BACK_KOJIN = "KANKOU_UPD_DEL_BACK";
	
	/************* 写真処理 *************/
	/**  getRealPath  */
	public static final String FILE_PATH = "/img/kankouti/";
	/** キー値：エンコードファイルデータ */
	public static final String KEY_IMAGE_STRING = "base64Image";
	/** キー値：jpegキー */
	public static final String IMAGE_PNG = ".png";
}
