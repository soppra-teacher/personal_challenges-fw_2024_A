package cashbook.action.kankou;

import static cashbook.util.Const.*;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import cashbook.action.common.BaseAction;
import cashbook.dto.common.LoginDto;
import cashbook.dto.kankou.KankouRegistDto;
import cashbook.service.kankou.KankouService;
import cashbook.util.CommonUtil;
import cashbook.util.KankouConst;

/**
 * 個人マスタ登録画面 初期表示アクションクラス
 * @author soppra
 */
public class KojinRegistInitAction extends BaseAction {

	/** 個人マスタサービス */
	private KankouService kojinService;

	/**
	 * 個人マスタサービスを設定します。
	 * @param kojinService 個人マスタサービス
	 */
	public void setKojinService(KankouService kojinService) {
		this.kojinService = kojinService;
	}

	/**
	 * <p><b>
	 * 個人マスタ登録画面
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
		
		 // ユーザーIDをセッションに保存
	    String logUserId = loginDto.getUserId();
        HttpSession session = request.getSession();
        session.setAttribute("logUserId", logUserId);

		// 戻り先をセッションから取得する。
		String backAction = CommonUtil.getStr(request.getSession().getAttribute(SESSION_REGIST_BACK_KOJIN));

//		// セッションから取得できない場合
		if (EMPTY.equals(backAction)) {

			// 個人IDがフォームに設定されていない場合
			if (CommonUtil.isNull(CommonUtil.getStr(formMap.get(KankouConst.KEY_KOJIN_ID)))) {
				// メニューからの遷移と判定
				backAction = ACTION_FOWARD_BACK_MENU;

			} else {
				// 個人マスタメンテからの遷移の場合
				backAction = ACTION_FOWARD_BACK_LIST;

			}
			// セッションに戻り先を保持する。
			request.getSession().setAttribute(SESSION_REGIST_BACK_KOJIN, backAction);
		}

		// 再検索用の個人IDをセッションから取得する。
//		Map<String, Object> sessionMap = CommonUtil.getSessionMap(request, SESSION_REGIST_RE_SEARCH_KOJIN);
//
//		// セッションから取得できた場合
//		if (sessionMap != null) {
//			// 画面に個人IDを設定する。
//			formMap.put(KankouConst.KEY_KOJIN_ID, sessionMap.get(KankouConst.KEY_KOJIN_ID));
//			// セッションに保持している個人IDを削除する。
//			request.getSession().removeAttribute(SESSION_REGIST_RE_SEARCH_KOJIN);
//
//		}
//
//		// メッセージをセッションから取得する。
//		String messageKey = CommonUtil.getStr(request.getSession().getAttribute(SESSION_REGIST_MESSAGE_KOJIN));
//
//		// セッションから取得できた場合
//		if (!EMPTY.equals(messageKey)) {
//			ActionMessages messages = new ActionMessages();
//			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(messageKey));
//			saveMessages(request, messages);
//			request.getSession().removeAttribute(SESSION_REGIST_MESSAGE_KOJIN);
//
//		}
		

		// 初期表示取得
		KankouRegistDto dto = kojinService.registInit(formMap, loginDto);

		// 取得した情報をリクエストに設定
		request.setAttribute(KankouConst.FORM_KOJIN_REGIST, dto);
		// 取得した情報をセッションに設定
		request.getSession().setAttribute(SESSION_REGIST_DTO_KOJIN, dto);

		// 処理成功時の遷移先を指定する。
		return map.findForward(ACTION_FOWARD_SUCCESS);
	}
}
