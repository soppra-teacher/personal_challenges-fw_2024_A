package cashbook.action.kojin;

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
import cashbook.dto.kojin.KojinListDto;
import cashbook.exception.CommonValidateException;
import cashbook.service.kojin.KojinService;
import cashbook.util.CommonUtil;
import cashbook.util.KojinConst;

/**
 * 個人マスタメンテ画面検索アクションクラス
 * @author soppra
 */
public class KojinListSearchAction extends BaseAction {

	/** 個人マスタサービス */
	private KojinService kojinService;

	/**
	 * 個人マスタサービスを設定します。
	 * @param kojinService 個人マスタサービス
	 */
	public void setKojinService(KojinService kojinService) {
		this.kojinService = kojinService;
	}

	/**
	 * <p><b>
	 * 個人マスタメンテ画面
	 * <br>検索処理
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

		// フォームの値を取得
		Map<String, Object> formMap = CommonUtil.getFormMap((DynaActionForm) form);

		// リクエストから「operation」の値が"search"だったのかを判定する。
		if (!ACTION_FOWARD_SEARCH.equals(request.getParameter(ACTION_FOWARD_OPERATION))) {

			// "search"でない場合、再検索用の値をセッションから取得する。
			Map<String, Object> sessionMap = CommonUtil.getSessionMap(request, SESSION_LIST_RE_SEARCH_KOJIN);

			// 取得できた場合
			if (sessionMap != null) {
				formMap.put(KojinConst.KEY_KOJIN_ID, sessionMap.get(KojinConst.KEY_KOJIN_ID));
				formMap.put(KojinConst.KEY_KOJIN_NM, sessionMap.get(KojinConst.KEY_KOJIN_NM));
				formMap.put(KojinConst.KEY_KOJIN_NM_KANA, sessionMap.get(KojinConst.KEY_KOJIN_NM_KANA));
				formMap.put(KojinConst.KEY_SEIBETSU_KBN_KEY, sessionMap.get(KojinConst.KEY_SEIBETSU_KBN_KEY));
				formMap.put(KojinConst.KEY_ZOKUGARA, sessionMap.get(KojinConst.KEY_ZOKUGARA));
				formMap.put(KojinConst.KEY_SETAINUSI_FLG, sessionMap.get(KojinConst.KEY_SETAINUSI_FLG));
				formMap.put(ITEM_CHECKBOX_DELETE, null);
			}
		}

		// 世帯主フラグ有無チェック
		if (SETAINUSHI_FLG_ON.equals(formMap.get(KojinConst.KEY_SETAINUSI_FLG))) {
			// チェック済みの場合、パラメータを"1"に設定する。
			formMap.put(KojinConst.KEY_SETAINUSI_FLG_VALUE, SETAINUSHI_ON);

		} else {
			// 未チェック済の場合、パラメータを"0"に設定する。
			formMap.put(KojinConst.KEY_SETAINUSI_FLG_VALUE, SETAINUSHI_OFF);

		}

		// セッションからメッセージを取得する。
		String messageKey = CommonUtil.getStr(request.getSession().getAttribute(SESSION_LIST_MESSAGE_KOJIN));

		// 取得できた場合
		if (!EMPTY.equals(messageKey)) {
			// 取得したメッセージを表示する。
			ActionMessages messages = new ActionMessages();
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(messageKey));
			saveMessages(request, messages);
			// セッションからメッセージを削除する。
			request.getSession().removeAttribute(SESSION_LIST_MESSAGE_KOJIN);

		}

		// 個人マスタメンテ画面 検索処理
		KojinListDto dto = kojinService.listSearch(formMap);

		// 取得した情報をリクエストに登録
		request.setAttribute(KojinConst.FORM_KOJIN_LIST, dto);
		// 取得した情報をセッションに登録
		request.getSession().setAttribute(SESSION_LIST_DTO_KOJIN, dto);
		// 検索条件をセッションに登録（再検索用）
		request.getSession().setAttribute(SESSION_LIST_RE_SEARCH_KOJIN, formMap);

		// 検索結果が存在しない場合
		if (dto.getList().size() == 0) {
			throw new CommonValidateException(MSG_ERRORS_NO_DATA);
		}

		// 処理成功時の遷移先を指定する。
		return map.findForward(ACTION_FOWARD_SUCCESS);
	}
}
