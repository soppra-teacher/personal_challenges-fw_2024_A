package cashbook.action.shushi;

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
import cashbook.dto.shushi.ShushiListDto;
import cashbook.exception.CommonValidateException;
import cashbook.service.shushi.ShushiService;
import cashbook.util.CommonUtil;

/**
 * 収支一覧(世帯)画面検索アクションクラス
 * @author soppra
 */
public class ShushiListSetaiSearchAction extends BaseAction {
	/** 収支サービス */
	private ShushiService shushiService;

	/**
	 * Serviceのsetter
	 * @param shushiService
	 */
	public void setShushiService(ShushiService shushiService) {
		this.shushiService = shushiService;
	}

	protected ActionForward doProcess(ActionMapping map, ActionForm form,
			HttpServletRequest request, HttpServletResponse response, LoginDto loginDto) throws Exception {

		// フォームの値を取得
		Map<String, Object> formMap = CommonUtil.getFormMap((DynaActionForm) form);

		// 再検索処理の場合
		if (!"search".equals(request.getParameter("operation"))) {

			@SuppressWarnings("unchecked")
			Map<String, Object> sessionMap = (Map<String, Object>) request.getSession()
					.getAttribute("SHUSHI_LIST_SETAI_RE_SEARCH");

			if (sessionMap != null) {

				formMap.put("yearKey", sessionMap.get("yearKey"));
				formMap.put("monthKey", sessionMap.get("monthKey"));
				formMap.put("kojinNmKey", sessionMap.get("kojinNmKey"));
				formMap.put("himokuNmKey", sessionMap.get("himokuNmKey"));
				formMap.put("himokuCd", sessionMap.get("himokuCd"));
				formMap.put("checkDel", null);
			}
		}

		// メッセージ表示
		String messageKey = CommonUtil.getStr(request.getSession().getAttribute("SHUSHI_LIST_SETAI_MESSAGE"));

		if (!EMPTY.equals(messageKey)) {

			ActionMessages messages = new ActionMessages();
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(messageKey));
			saveMessages(request, messages);
			request.getSession().removeAttribute("SHUSHI_LIST_SETAI_MESSAGE");
		}

		// 検索
		ShushiListDto dto = shushiService.shushiListSetaiSearch(formMap, loginDto);

		// 所得した情報をリクエスト、セッションに登録
		request.setAttribute("shushiListSetaiForm", dto);
		request.getSession().setAttribute("SHUSHI_LIST_SETAI_DTO", dto);

		// 検索条件をセッションに登録（再検索用）
		request.getSession().setAttribute("SHUSHI_LIST_SETAI_RE_SEARCH", formMap);

		// 検索結果が存在しない場合
		if (dto.getList().size() == 0) {
			throw new CommonValidateException("errors.noData");
		}
		return map.findForward("success");
	}
}
