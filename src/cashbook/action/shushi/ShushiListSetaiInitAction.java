package cashbook.action.shushi;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cashbook.action.common.BaseAction;
import cashbook.dto.common.LoginDto;
import cashbook.dto.shushi.ShushiListDto;
import cashbook.service.shushi.ShushiService;

/**
 * 収支一覧（世帯）画面初期表示アクションクラス
 * @author soppra
 */
public class ShushiListSetaiInitAction extends BaseAction {
	/** Service **/
	private ShushiService shushiService;

	protected ActionForward doProcess(ActionMapping map, ActionForm form,
			HttpServletRequest request, HttpServletResponse response, LoginDto loginDto) throws Exception {

		// 戻る制御
		request.getSession().removeAttribute("SHUSHI_LIST_SETAI_BACK");

		// 初期表示取得
		ShushiListDto dto = shushiService.shushiListSetaiInit(loginDto);

		// 所得した情報をリクエスト、セッションに登録
		request.setAttribute("shushiListSetaiForm", dto);
		request.getSession().setAttribute("SHUSHI_LIST_SETAI_DTO", dto);

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
