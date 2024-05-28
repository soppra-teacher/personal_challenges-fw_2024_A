package cashbook.action.himoku;

import static cashbook.util.Const.*;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;

import cashbook.action.common.BaseAction;
import cashbook.dto.common.LoginDto;
import cashbook.dto.himoku.HimokuRegistDto;
import cashbook.service.himoku.HimokuService;
import cashbook.util.CommonUtil;
import cashbook.util.HimokuConst;

/**
 * 費目マスタ登録画面 初期表示アクションクラス
 * @author soppra
 */
public class HimokuRegistInitAction extends BaseAction {

	/** 費目マスタサービス */
	private HimokuService himokuService;

	/**
	 * 費目マスタサービスを設定します。
	 * @param himokuService 費目マスタサービス
	 */
	public void setHimokuService(HimokuService himokuService) {
		this.himokuService = himokuService;
	}

	/**
	 * <p><b>
	 * 費目マスタ登録画面
	 * <br>初期表示処理
	 * </b></p>
	 *
	 * @param map      アクションマッピング
	 * @param form     フォーム
	 * @param request  リクエスト
	 * @param response レスポンス
	 * @param loginDto ログイン情報DTO
	 * @return アクションフォワード
	 * @throws Exception すべての例外
	 */
	protected ActionForward doProcess(ActionMapping map, ActionForm form,
			HttpServletRequest request, HttpServletResponse response, LoginDto loginDto) throws Exception {

		// フォームの値を取得する。
		Map<String, Object> formMap = CommonUtil.getFormMap((DynaActionForm) form);

		/*-------------------------------------------------*
		 * １．セッションから戻り先のアクションを取得する。*
		 *-------------------------------------------------*/
		// 戻り先をセッションから取得する。
		String backAction = CommonUtil.getStr(request.getSession().getAttribute(SESSION_REGIST_BACK_HIMOKU));
		// セッションから取得できない場合
		//if (backAction == null) {
		if (EMPTY.equals(backAction)) {

			// 費目コードがフォームに設定されていない場合
			if (CommonUtil.isNull(CommonUtil.getStr(formMap.get(HimokuConst.KEY_HIMOKU_CD)))) {
				// メニューからの遷移と判定
				backAction = ACTION_FOWARD_BACK_MENU;

			} else {
				// 費目マスタメンテからの遷移の場合
				backAction = ACTION_FOWARD_BACK_LIST;

			}
			// セッションに戻り先を保持する。
			request.getSession().setAttribute(SESSION_REGIST_BACK_HIMOKU, backAction);
		}

		/*---------------------------------------------------*
		 * ２．セッションから再検索用の費目コードを取得する。*
		 *---------------------------------------------------*/
		// 再検索用の費目コードをセッションから取得する。
		Map<String, Object> sessionMap = CommonUtil.getSessionMap(request, SESSION_REGIST_RE_SEARCH_HIMOKU);

		// セッションから取得できた場合
		if (sessionMap != null) {

			// 画面に費目コードを設定する。
			formMap.put(HimokuConst.KEY_HIMOKU_CD, sessionMap.get(HimokuConst.KEY_HIMOKU_CD));
			// セッションに保持している費目コードを削除する。
			request.getSession().removeAttribute(SESSION_REGIST_RE_SEARCH_HIMOKU);
		}

		/*---------------------------------------------------*
		 * ３．セッションから表示するメッセージを取得する。  *
		 *---------------------------------------------------*/
		// メッセージをセッションから取得する。
		String messageKey = CommonUtil.getStr(request.getSession().getAttribute(SESSION_REGIST_MESSAGE_HIMOKU));

		// セッションから取得できた場合
		if (!EMPTY.equals(messageKey)) {

			ActionMessages messages = new ActionMessages();
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(messageKey));
			saveMessages(request, messages);
			request.getSession().removeAttribute(SESSION_REGIST_MESSAGE_HIMOKU);
		}

		// 初期表示取得
		HimokuRegistDto dto = himokuService.registInit(formMap);

		// 取得した情報をリクエストに設定
		request.setAttribute(HimokuConst.FORM_HIMOKU_REGIST, dto);
		// 取得した情報をセッションに設定
		request.getSession().setAttribute(SESSION_REGIST_DTO_HIMOKU, dto);

		// 処理成功時の遷移先を指定する。
		return map.findForward(ACTION_FOWARD_SUCCESS);
	}
}
