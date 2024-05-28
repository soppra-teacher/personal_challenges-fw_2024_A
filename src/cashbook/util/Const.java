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
	/** 遷移先 戻る(世帯一覧) */
	public static final String ACTION_FOWARD_BACK_LIST_SETAI = "backListSetai";
	/** 遷移先 戻る(個別一覧) */
	public static final String ACTION_FOWARD_BACK_LIST_KOBETSU = "backListKobetsu";

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

	/** 登録完了メッセージ */
	public static final String MSG_SUCCESS_INSERT = "messages.success.insert";
	/** 更新完了メッセージ */
	public static final String MSG_SUCCESS_UPDATE = "messages.success.update";
	/** 削除完了メッセージ */
	public static final String MSG_SUCCESS_DELETE = "messages.success.delete";

	/** 個人用エラーメッセージ1 */
	public static final String MSG_KOJIN_CONSIS_1 = "errors.kojin.seigosei1";
	/** 個人用エラーメッセージ2 */
	public static final String MSG_KOJIN_CONSIS_2 = "errors.kojin.seigosei2";

	/** 世帯マスタ削除時のエラーメッセージ */
	public static final String MSG_SETAI_ERROR = "setaiListForm.error.integrity";

	/** 世帯マスタ削除時のエラーメッセージ */
	public static final String MSG_SETAI_ERROR_2 = "setaiListForm.error.existKojin";

	/** 更新日付 */
	public static final String ITEM_REVISION = "revision";
	/** 削除対象チェックボックス */
	public static final String ITEM_CHECKBOX_DELETE = "checkDel";

	/************* セッション情報 *************/
	/** ログイン画面 DTO */
	public static final String SESSION_LOGIN_DTO = "LOGIN_DTO";

	/** 費目マスタ登録画面 DTO */
	public static final String SESSION_REGIST_DTO_HIMOKU = "HIMOKU_REGIST_DTO";
	/** 費目マスタ登録画面 戻り先 */
	public static final String SESSION_REGIST_BACK_HIMOKU = "HIMOKU_REGIST_BACK";
	/** 費目マスタ登録画面 再検索値 */
	public static final String SESSION_REGIST_RE_SEARCH_HIMOKU = "HIMOKU_REGIST_RE_SEARCH";
	/** 費目マスタ登録画面 メッセージ  */
	public static final String SESSION_REGIST_MESSAGE_HIMOKU = "HIMOKU_REGIST_MESSAGE";
	/** 費目マスタメンテ画面 DTO */
	public static final String SESSION_LIST_DTO_HIMOKU = "HIMOKU_LIST_DTO";
	/** 費目マスタメンテ画面 再検索値 */
	public static final String SESSION_LIST_RE_SEARCH_HIMOKU = "HIMOKU_LIST_RE_SEARCH";
	/** 費目マスタメンテ画面 メッセージ */
	public static final String SESSION_LIST_MESSAGE_HIMOKU = "HIMOKU_LIST_MESSAGE";

	/** 世帯マスタ登録画面 戻り先 */
	public static final String SESSION_REGIST_BACK_SETAI = "SETAI_REGIST_BACK";
	/** 世帯マスタメンテ画面 DTO */
	public static final String SESSION_LIST_DTO_SETAI = "SETAI_LIST_DTO";
	/** 世帯マスタメンテ画面 再検索値 */
	public static final String SESSION_LIST_RE_SEARCH_SETAI = "SETAI_LIST_RE_SEARCH";
	/** 世帯マスタメンテ画面 メッセージ */
	public static final String SESSION_LIST_MESSAGE_SETAI = "SETAI_LIST_MESSAGE";

	/** 世帯マスタ登録画面 DTO */
	public static final String SESSION_REGIST_DTO_SETAI = "SETAI_REGIST_DTO";
	/** 世帯マスタ登録画面 再検索値 */
	public static final String SESSION_REGIST_RE_SEARCH_SETAI = "SETAI_REGIST_RE_SEARCH";
	/** 世帯マスタ登録画面 メッセージ  */
	public static final String SESSION_REGIST_MESSAGE_SETAI = "SETAI_REGIST_MESSAGE";

	/** 個人マスタ登録画面 DTO */
	public static final String SESSION_REGIST_DTO_KOJIN = "KOJIN_REGIST_DTO";
	/** 個人マスタ登録画面 戻り先 */
	public static final String SESSION_REGIST_BACK_KOJIN = "KOJIN_REGIST_BACK";
	/** 個人マスタ登録画面 再検索値 */
	public static final String SESSION_REGIST_RE_SEARCH_KOJIN = "KOJIN_REGIST_RE_SEARCH";
	/** 個人マスタ登録画面 メッセージ */
	public static final String SESSION_REGIST_MESSAGE_KOJIN = "KOJIN_REGIST_MESSAGE";
	/** 個人マスタメンテ画面 DTO */
	public static final String SESSION_LIST_DTO_KOJIN = "KOJIN_LIST_DTO";
	/** 個人マスタメンテ画面 再検索値 */
	public static final String SESSION_LIST_RE_SEARCH_KOJIN = "KOJIN_LIST_RE_SEARCH";
	/** 個人マスタメンテ画面 メッセージ */
	public static final String SESSION_LIST_MESSAGE_KOJIN = "KOJIN_LIST_MESSAGE";

	/** 収支登録画面 戻り先 */
	public static final String SESSION_REGIST_BACK_SHUSHI = "SHUSHI_REGIST_BACK";

	/** 収支一覧(個別)画面 DTO */
	public static final String SESSION_LIST_DTO_SHUSHI_KOBETSU = "SHUSHI_LIST_KOBETSU_DTO";
	/** 収支一覧(個別)画面 戻り先 */
	public static final String SESSION_REGIST_BACK_SHUSHI_KOBETSU = "SHUSHI_LIST_KOBETSU_BACK";
	/** 収支一覧(個別)画面 メッセージ  */
	public static final String SESSION_LIST_MESSAGE_SHUSHI_KOBETSU = "SHUSHI_LIST_KOBETSU_MESSAGE";
	/** 収支一覧(個別)画面 再検索値 */
	public static final String SESSION_REGIST_RE_SEARCH_SHUSHI_KOBETSU = "SHUSHI_LIST_KOBETSU_RE_SEARCH";
	/** 収支一覧(個別)画面 メッセージ */
	public static final String SESSION_LIST_MESSAGE_SHUSHI_SETAI = "SHUSHI_LIST_SETAI_MESSAGE";


	/************* 画面ID *************/
    /** SM001:個人マスタメンテ */
	public static final String DISP_SM001 = "SM001";
    /** SM002:個人マスタ登録 */
	public static final String DISP_SM002 = "SM002";
    /** SM011:世帯マスタメンテ */
	public static final String DISP_SM011 = "SM011";
    /** SM012:世帯マスタ登録 */
	public static final String DISP_SM012 = "SM012";
    /** SM021:費目マスタメンテ */
	public static final String DISP_SM021 = "SM021";
    /** SM022:費目マスタ登録 */
	public static final String DISP_SM022 = "SM022";
	/** ST001:収支一覧(個人) */
	public static final String DISP_ST001 = "ST001";
    /** ST002:収支一覧(世帯) */
	public static final String DISP_ST002 = "ST002";
	/** ST011:収支登録 */
	public static final String DISP_ST011 = "ST011";

	/************* 区分値 *************/
    /** SETAINUSHI_ON:世帯主 */
	public static final String SETAINUSHI_ON = "1";
    /** SETAINUSHI_OFF:非世帯主 */
	public static final String SETAINUSHI_OFF = "0";
	/** SETAINUSHI_FLG_ON:世帯主 (ON/OFF) */
	public static final String SETAINUSHI_FLG_ON = "on";
	/** SETAINUSHI_FLG_OFF:世帯主 (ON/OFF) */
	public static final String SETAINUSHI_FLG_OFF = "off";

	/** 性別区分:男 */
	public static final String SEIBETSU_KBN_MAN = "1";
	/** 性別区分:女 */
	public static final String SEIBETSU_KBN_WOMAN = "2";

	/** 続柄区分:父 */
	public static final String ZOKUGARA_FATHER = "1";
	/** 続柄区分:母 */
	public static final String ZOKUGARA_MOTHER = "2";
	/** 続柄区分:長男 */
	public static final String ZOKUGARA_ELDEST_SON = "3";
	/** 続柄区分:長女 */
	public static final String ZOKUGARA_ELDEST_DAUGHTER = "4";
	/** 続柄区分:次男 */
	public static final String ZOKUGARA_SECOND_SON = "5";
	/** 続柄区分:次女 */
	public static final String ZOKUGARA_SECOND_DAUGHTER = "6";
	/** 続柄区分:三男 */
	public static final String ZOKUGARA_THIRD_SON = "7";
	/** 続柄区分:三女 */
	public static final String ZOKUGARA_THIRD_DAUGHTER = "8";

	/************* コードマスタ *************/
	/** 費目 */
	public static final String CD_BUNRUI_001 = "001";
	/** 性別 */
	public static final String CD_BUNRUI_002 = "002";
	/** 続柄 */
	public static final String CD_BUNRUI_003 = "003";
	/** 世帯主 */
	public static final String CD_BUNRUI_004 = "004";
	/** 世帯主：コード値 1 */
	public static final String CD_004_1 = "1";
}
