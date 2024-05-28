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
import cashbook.dto.setai.SetaiRegistDto;
import cashbook.service.setai.SetaiService;
import cashbook.util.CommonUtil;
import cashbook.util.SetaiConst;

/**
 * 世帯マスタ登録画面初期表示アクションクラス
 * @author soppra
 */
public class SetaiRegistInitAction extends BaseAction {
	/** Service **/
	private SetaiService setaiService;

	protected ActionForward doProcess(ActionMapping map, ActionForm form,
			HttpServletRequest request, HttpServletResponse response, LoginDto loginDto) throws Exception {

		Map<String, Object> formMap = CommonUtil.getFormMap((DynaActionForm) form);

		// 戻る制御
		String backAction = CommonUtil.getStr(request.getSession().getAttribute(SESSION_REGIST_BACK_SETAI));

		if (EMPTY.equals(backAction)) {

			if (CommonUtil.isNull(CommonUtil.getStr(formMap.get(SetaiConst.KEY_SETAI_ID)))) {
				// メニューからの遷移の場合
				backAction = ACTION_FOWARD_BACK_MENU;

			} else {
				// 一覧からの遷移の場合
				backAction = ACTION_FOWARD_BACK_LIST;

			}
			request.getSession().setAttribute(SESSION_REGIST_BACK_SETAI, backAction);
		}

		// 再検索処理
		Map<String, Object> sessionMap = CommonUtil.getSessionMap(request, SESSION_REGIST_RE_SEARCH_SETAI);
		if (sessionMap != null) {
			formMap.put(SetaiConst.KEY_SETAI_ID, sessionMap.get(SetaiConst.KEY_SETAI_ID));
			request.getSession().removeAttribute(SESSION_REGIST_RE_SEARCH_SETAI);
		}

		// メッセージ表示
		String messageKey = CommonUtil.getStr(request.getSession().getAttribute(SESSION_REGIST_MESSAGE_SETAI));

		if (!EMPTY.equals(messageKey)) {
			ActionMessages messages = new ActionMessages();
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(messageKey));
			saveMessages(request, messages);
			request.getSession().removeAttribute(SESSION_REGIST_MESSAGE_SETAI);

		}

		// 初期表示取得
		SetaiRegistDto dto = setaiService.registInit(formMap);

		// 所得した情報をリクエスト、セッションに登録
		request.setAttribute(SetaiConst.FORM_SETAI_REGIST, dto);
		request.getSession().setAttribute(SESSION_REGIST_DTO_SETAI, dto);

		return map.findForward(ACTION_FOWARD_SUCCESS);
	}

	/**
	 * Serviceのsetter
	 * @param setaiService
	 */
	public void setSetaiService(SetaiService setaiService) {
		this.setaiService = setaiService;
	}
}
