package cashbook.action.setai;

import static cashbook.util.Const.*;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import cashbook.action.common.BaseAction;
import cashbook.dto.common.LoginDto;
import cashbook.service.setai.SetaiService;
import cashbook.util.CommonUtil;
import cashbook.util.Const;

/**
 * 世帯マスタ登録画面登録・更新アクションクラス
 * @author soppra
 */
public class SetaiRegistInsUpdAction extends BaseAction {

	/** Service **/
	private SetaiService setaiService;

	protected ActionForward doProcess(ActionMapping map, ActionForm form,
			HttpServletRequest request, HttpServletResponse response, LoginDto loginDto) throws Exception {

		// フォームの値を取得する。
		Map<String, Object> formMap = CommonUtil.getFormMap((DynaActionForm) form);

		// 登録・更新
		setaiService.registInsUpd(formMap, loginDto);

		// メッセージ表示
		if (CommonUtil.isNull(CommonUtil.getStr(formMap.get(Const.ITEM_REVISION)))) {
			request.getSession().setAttribute(SESSION_REGIST_MESSAGE_SETAI, MSG_SUCCESS_INSERT);

		} else {
			request.getSession().setAttribute(SESSION_REGIST_MESSAGE_SETAI, MSG_SUCCESS_UPDATE);

		}

		// 検索条件をセッションに保持（再検索用）
		request.getSession().setAttribute(SESSION_REGIST_RE_SEARCH_SETAI, formMap);

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
