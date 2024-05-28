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
 * 収支一覧(個別)画面検索アクションクラス
 * @author soppra
 */
public class ShushiListKobetsuSearchAction extends BaseAction {
	/** Service **/
	private ShushiService shushiService;

	/**
	 * 主処理
	 */
	protected ActionForward doProcess(ActionMapping map, ActionForm form,
			HttpServletRequest request, HttpServletResponse response, LoginDto loginDto) throws Exception {

		// フォームの値を取得
		Map<String, Object> formMap = CommonUtil.getFormMap((DynaActionForm) form);

		// 再検索処理の場合
		if (!ACTION_FOWARD_SEARCH.equals(request.getParameter(ACTION_FOWARD_OPERATION))) {

			@SuppressWarnings("unchecked")
			Map<String, Object> sessionMap = (Map<String, Object>) request.getSession()
					.getAttribute("SHUSHI_LIST_KOBETSU_RE_SEARCH");

			if (sessionMap != null) {
				formMap.put("yearKey", sessionMap.get("yearKey"));
				formMap.put("monthKey", sessionMap.get("monthKey"));
				formMap.put("himokuNmKey", sessionMap.get("himokuNmKey"));
				formMap.put("himokuCd", sessionMap.get("himokuCd"));
				formMap.put("checkDel", null);

			}
		}

		// メッセージ表示
		String messageKey = CommonUtil.getStr(request.getSession().getAttribute(SESSION_LIST_MESSAGE_SHUSHI_KOBETSU));

		if (!EMPTY.equals(messageKey)) {
			ActionMessages messages = new ActionMessages();
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(messageKey));
			saveMessages(request, messages);
			request.getSession().removeAttribute(SESSION_LIST_MESSAGE_SHUSHI_KOBETSU);

		}

		// 検索
		ShushiListDto dto = shushiService.shushiListKobetsuSearch(formMap, loginDto);

		// 所得した情報をリクエスト、セッションに登録
		request.setAttribute("shushiListKobetsuForm", dto);
		request.getSession().setAttribute(SESSION_LIST_DTO_SHUSHI_KOBETSU, dto);

		// 検索条件をセッションに登録（再検索用）
		request.getSession().setAttribute(SESSION_REGIST_RE_SEARCH_SHUSHI_KOBETSU, formMap);

		// 検索結果が存在しない場合
		if (dto.getList().size() == 0) {
			throw new CommonValidateException(MSG_ERRORS_NO_DATA);
		}

		return map.findForward("success");
	}

	/**
	 * Serviceのsetter
	 * @param shushiService
	 */
	public void setShushiService(ShushiService shushiService) {
		this.shushiService = shushiService;
	}
}
