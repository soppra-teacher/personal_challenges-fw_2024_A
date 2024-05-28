package cashbook.action.setai;

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
import cashbook.dto.setai.SetaiListDto;
import cashbook.exception.CommonValidateException;
import cashbook.service.setai.SetaiService;
import cashbook.util.CommonUtil;
import cashbook.util.SetaiConst;

/**
 * 世帯マスタメンテ画面検索アクションクラス
 * @author soppra
 */
public class SetaiListSearchAction extends BaseAction {

	/** 世帯マスタサービス */
	private SetaiService setaiService;

	/**
	 * 世帯マスタサービスを設定します。
	 * @param setaiService 世帯マスタサービス
	 */
	public void setSetaiService(SetaiService setaiService) {
		this.setaiService = setaiService;
	}

	/**
	 * <p><b>
	 * 世帯マスタメンテ画面
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
			Map<String, Object> sessionMap = CommonUtil.getSessionMap(request, SESSION_LIST_RE_SEARCH_SETAI);

			// 取得できた場合
			if (sessionMap != null) {
				formMap.put(SetaiConst.KEY_SETAI_ID, sessionMap.get(SetaiConst.KEY_SETAI_ID));
				formMap.put(SetaiConst.KEY_SETAI_NM, sessionMap.get(SetaiConst.KEY_SETAI_NM));
				formMap.put(SetaiConst.KEY_SETAI_NM_KANA, sessionMap.get(SetaiConst.KEY_SETAI_NM_KANA));
				formMap.put(SetaiConst.KEY_ADDRESS, sessionMap.get(SetaiConst.KEY_ADDRESS));
				formMap.put(SetaiConst.KEY_POST_NO, sessionMap.get(SetaiConst.KEY_POST_NO));
				formMap.put(SetaiConst.KEY_TEL_NO, sessionMap.get(SetaiConst.KEY_TEL_NO));
				formMap.put(ITEM_CHECKBOX_DELETE, null);
			}
		}

		// セッションからメッセージを取得する。
		String messageKey = CommonUtil.getStr(request.getSession().getAttribute(SESSION_LIST_MESSAGE_SETAI));

		// 取得できた場合
		if (!EMPTY.equals(messageKey)) {
			// 取得したメッセージを表示する。
			ActionMessages messages = new ActionMessages();
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(messageKey));
			saveMessages(request, messages);
			// セッションからメッセージを削除する。
			request.getSession().removeAttribute(SESSION_LIST_MESSAGE_SETAI);

		}

		// 世帯マスタメンテ画面 検索処理
		SetaiListDto dto = setaiService.listSearch(formMap);

		// 取得した情報をリクエストに登録
		request.setAttribute(SetaiConst.FORM_SETAI_LIST, dto);
		// 取得した情報をセッションに登録
		request.getSession().setAttribute(SESSION_LIST_DTO_SETAI, dto);

		// 検索条件をセッションに登録（再検索用）
		request.getSession().setAttribute(SESSION_LIST_RE_SEARCH_SETAI, formMap);

		// 検索結果が存在しない場合
		if (dto.getList().size() == 0) {
			throw new CommonValidateException(MSG_ERRORS_NO_DATA);
		}

		// 処理成功時の遷移先を指定する。
		return map.findForward(ACTION_FOWARD_SUCCESS);
	}
}
