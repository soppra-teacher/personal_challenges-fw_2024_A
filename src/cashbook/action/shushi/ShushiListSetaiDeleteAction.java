package cashbook.action.shushi;

import static cashbook.util.Const.*;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;

import cashbook.action.common.BaseAction;
import cashbook.dto.common.LoginDto;
import cashbook.service.shushi.ShushiService;

/**
 * 収支一覧（世帯）画面削除アクションクラス
 * @author soppra
 */
public class ShushiListSetaiDeleteAction extends BaseAction {

	/** Service **/
	private ShushiService shushiService;

	/**
	 * 主処理
	 */
	protected ActionForward doProcess(ActionMapping map, ActionForm form,
			HttpServletRequest request, HttpServletResponse response, LoginDto loginDto) throws Exception {

		DynaActionForm dynaForm = (DynaActionForm) form;
		@SuppressWarnings("unchecked")
		Map<String, Object> formMap = dynaForm.getMap();

		// チェック
		if (!check(formMap, request)) {
			return map.getInputForward();
		}

		// 削除
		shushiService.shushiListSetaiDelete(formMap);

		// メッセージ表示
		request.getSession().setAttribute(SESSION_LIST_MESSAGE_SHUSHI_SETAI, MSG_SUCCESS_DELETE);

		return map.findForward(ACTION_FOWARD_SUCCESS);
	}

	/**
	 * 入力チェック
	 * @return true：エラーなし、false:エラーあり
	 */
	private boolean check(Map<String, Object> formMap, HttpServletRequest request) {

		// 選択レコードが無ければエラー
		String[] checkDel = (String[]) formMap.get(ITEM_CHECKBOX_DELETE);

		if (checkDel == null || checkDel.length == 0) {
			ActionErrors errors = new ActionErrors();
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(MSG_ERRORS_NO_DELETE));
			saveErrors(request, errors);
			return false;
		}
		return true;
	}

	/**
	 * Serviceのsetter
	 * @param shushiService
	 */
	public void setShushiService(ShushiService shushiService) {
		this.shushiService = shushiService;
	}
}
