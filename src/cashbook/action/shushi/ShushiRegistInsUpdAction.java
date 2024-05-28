package cashbook.action.shushi;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import cashbook.action.common.BaseAction;
import cashbook.dto.common.LoginDto;
import cashbook.service.shushi.ShushiService;
import cashbook.util.CommonUtil;
import cashbook.util.Const;

/**
 * 収支登録画面登録・更新アクションクラス
 * @author soppra
 */
public class ShushiRegistInsUpdAction extends BaseAction {

	/** Service **/
	private ShushiService shushiService;

	/**
	 * 主処理
	 */
	protected ActionForward doProcess(ActionMapping map, ActionForm form,
			HttpServletRequest request, HttpServletResponse response, LoginDto loginDto) throws Exception {

		Map<String, Object> formMap = CommonUtil.getFormMap((DynaActionForm) form);

		// 入力フォーム.日付の年月をYYYYMM形式で設定
		formMap.put("ym", CommonUtil.getStr(formMap.get("ymd")).substring(0, 4)
				+ CommonUtil.getStr(formMap.get("ymd")).substring(5, 7));
		// ログインDTO.個人IDを設定
		formMap.put("loginKojinId", loginDto.getKojinId());

		if (CommonUtil.isNull(CommonUtil.getStr(formMap.get("kojinId")))) {
			formMap.put("kojinId", loginDto.getKojinId());
		}

		// 登録・更新
		shushiService.registInsUpd(formMap);

		// メッセージ表示
		if (CommonUtil.isNull(CommonUtil.getStr(formMap.get(Const.ITEM_REVISION)))) {
			request.getSession().setAttribute("SHUSHI_REGIST_MESSAGE", "messages.success.insert");

		} else {
			request.getSession().setAttribute("SHUSHI_REGIST_MESSAGE", "messages.success.update");

		}

		// 検索条件をセッションに保持（再検索用）
		request.getSession().setAttribute("SHUSHI_REGIST_RE_SEARCH", formMap);

		return map.findForward("success");
	}

	/**
	 * Serviceのsetter
	 * @param himokuService
	 */
	public void setShushiService(ShushiService shushiService) {
		this.shushiService = shushiService;
	}
}
