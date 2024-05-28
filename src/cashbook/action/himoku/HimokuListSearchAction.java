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
import cashbook.dto.himoku.HimokuListDto;
import cashbook.exception.CommonValidateException;
import cashbook.service.himoku.HimokuService;
import cashbook.util.CommonUtil;
import cashbook.util.HimokuConst;

/**
 * 費目マスタメンテ画面 検索アクションクラス
 * @author soppra
 */
public class HimokuListSearchAction extends BaseAction {

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
	 * 費目マスタメンテ画面
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
			Map<String, Object> sessionMap = CommonUtil.getSessionMap(request, SESSION_LIST_RE_SEARCH_HIMOKU);

			// 取得できた場合
			if (sessionMap != null) {
				formMap.put(HimokuConst.KEY_HIMOKU_NM, sessionMap.get(HimokuConst.KEY_HIMOKU_NM));
				formMap.put(HimokuConst.KEY_HIMOKU_KBN_KEY, sessionMap.get(HimokuConst.KEY_HIMOKU_KBN_KEY));
				formMap.put(ITEM_CHECKBOX_DELETE, null);
			}
		}

		// セッションからメッセージを取得する。
		String messageKey = CommonUtil.getStr(request.getSession().getAttribute(SESSION_LIST_MESSAGE_HIMOKU));

		// 取得できた場合
		if (!EMPTY.equals(messageKey)) {

			// 取得したメッセージを表示する。
			ActionMessages messages = new ActionMessages();
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(messageKey));
			saveMessages(request, messages);
			// セッションからメッセージを削除する。
			request.getSession().removeAttribute(SESSION_LIST_MESSAGE_HIMOKU);
		}

		// 費目マスタメンテ画面 検索処理
		HimokuListDto dto = himokuService.listSearch(formMap);

		// 取得した情報をリクエストに登録
		request.setAttribute(HimokuConst.FORM_HIMOKU_LIST, dto);
		// 取得した情報をセッションに登録
		request.getSession().setAttribute(SESSION_LIST_DTO_HIMOKU, dto);
		// 検索条件をセッションに登録（再検索用）
		request.getSession().setAttribute(SESSION_LIST_RE_SEARCH_HIMOKU, formMap);

		// 検索結果が存在しない場合
		if (dto.getList().size() == 0) {
			throw new CommonValidateException(MSG_ERRORS_NO_DATA);
		}

		// 処理成功時の遷移先を指定する。
		return map.findForward(ACTION_FOWARD_SUCCESS);

	}
}
