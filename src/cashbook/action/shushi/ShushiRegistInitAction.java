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
import cashbook.dto.shushi.ShushiRegistDto;
import cashbook.service.shushi.ShushiService;
import cashbook.util.CommonUtil;
import cashbook.util.Const;

/**
 * 収支登録画面初期表示アクションクラス
 * @author soppra
 */
public class ShushiRegistInitAction extends BaseAction {
	/** Service **/
	private ShushiService shushiService;

	/**
	 * 主処理
	 */
	protected ActionForward doProcess(ActionMapping map, ActionForm form,
			HttpServletRequest request, HttpServletResponse response, LoginDto loginDto) throws Exception {

		// フォームの値を取得する。
		Map<String, Object> formMap = CommonUtil.getFormMap((DynaActionForm) form);

		String[] paramArray = formMap.get("shushiKey").toString().split(",");

		// 戻る制御
		String backAction = CommonUtil.getStr(request.getSession().getAttribute("SHUSHI_REGIST_BACK"));

		if (EMPTY.equals(backAction)) {

			if (paramArray[0].equals("")) {
				// メニューからの遷移の場合
				backAction = "backMenu";

			} else {
				// 一覧からの遷移の場合
				if (paramArray[2].equals(Const.DISP_ST001)) {
					backAction = "backListKobetsu";
				} else {
					backAction = "backListSetai";
				}
			}
			request.getSession().setAttribute("SHUSHI_REGIST_BACK", backAction);
		}

		if (!paramArray[0].equals("")) {
			formMap.put("shushiId", paramArray[0]);
			formMap.put("shushiDtlId", paramArray[1]);

		} else {

			// 再検索処理
			Map<String, Object> sessionMap = CommonUtil.getSessionMap(request, "SHUSHI_REGIST_RE_SEARCH");
			if (sessionMap != null) {
				formMap.put("shushiId", sessionMap.get("shushiId"));
				formMap.put("shushiDtlId", sessionMap.get("shushiDtlId"));
				request.getSession().removeAttribute("SHUSHI_REGIST_RE_SEARCH");
			}
		}

		// メッセージ表示
		String messageKey = CommonUtil.getStr(request.getSession().getAttribute("SHUSHI_REGIST_MESSAGE"));

		if (!EMPTY.equals(messageKey)) {
			ActionMessages messages = new ActionMessages();
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(messageKey));
			saveMessages(request, messages);
			request.getSession().removeAttribute("SHUSHI_REGIST_MESSAGE");

		}

		// 初期表示取得
		ShushiRegistDto dto = shushiService.registInit(formMap);

		// 所得した情報をリクエスト、セッションに登録
		request.setAttribute("shushiRegistForm", dto);
		request.getSession().setAttribute("SHUSHI_REGIST_DTO", dto);

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
