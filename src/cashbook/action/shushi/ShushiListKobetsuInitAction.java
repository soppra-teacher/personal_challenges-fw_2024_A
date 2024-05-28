package cashbook.action.shushi;

import static cashbook.util.Const.*;

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
 * 収支一覧（個別）画面初期表示アクションクラス
 * @author soppra
 */
public class ShushiListKobetsuInitAction extends BaseAction {
	/** Service **/
	private ShushiService shushiService;

	/**
	 * 主処理
	 */
	protected ActionForward doProcess(ActionMapping map, ActionForm form,
			HttpServletRequest request, HttpServletResponse response, LoginDto loginDto) throws Exception {

		// 戻る制御
		request.getSession().removeAttribute(SESSION_REGIST_BACK_SHUSHI_KOBETSU);

		// 初期表示取得
		ShushiListDto dto = shushiService.shushiListKobetsuInit();

		// 所得した情報をリクエスト、セッションに登録
		request.setAttribute("shushiListKobetsuForm", dto);
		request.getSession().setAttribute(SESSION_LIST_DTO_SHUSHI_KOBETSU, dto);

		return map.findForward(ACTION_FOWARD_SUCCESS);
	}

	/**
	 * Serviceのsetter
	 * @param shushiService
	 */
	public void setShushiService(ShushiService shushiService) {
		this.shushiService = shushiService;
	}
}
