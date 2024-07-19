package cashbook.action.kankou;

import static cashbook.util.Const.*;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;

import cashbook.action.common.BaseAction;
import cashbook.dto.common.LoginDto;
import cashbook.dto.kankou.KankouUpdDelDto;
import cashbook.service.kankou.KankouUpdDelService;
import cashbook.util.CommonUtil;
import cashbook.util.KankouUpdDelConst;

/**
 * 更新・削除画面 初期表示アクションクラス
 * @author soppra
 */
public class KankouUpdDeInitAction extends BaseAction {

	/** 観光マスタサービス */
	private KankouUpdDelService kankouUpdDelService;

	private HttpSession session;

	/**
	 * 観光マスタサービスを設定します。
	 * @param kankouUpdDelService 観光マスタサービス
	 */
	public void setKankouUpdDelService(KankouUpdDelService kankouUpdDelService) {
		this.kankouUpdDelService = kankouUpdDelService;
	}

	/**
	 * <p><b>
	 * 観光地更新・削除
	 * <br>初期表示処理
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

		// フォームの値を取得する。
		Map<String, Object> formMap = CommonUtil.getFormMap((DynaActionForm) form);
		//セッション**
		session = request.getSession();

		// 観光地IDをセッションに保存
		String kankouId = CommonUtil.getStr(formMap.get(KankouUpdDelConst.KEY_KANKOU_ID));
		session.setAttribute(KankouUpdDelConst.KEY_KANKOU_ID, kankouId);

		//評価値をセッションに保持
		String compareHyoka = CommonUtil.getStr(formMap.get(KankouUpdDelConst.KEY_HYOKA));
		session.setAttribute(KankouUpdDelConst.KEY_COMPARE_HYOKA, compareHyoka);

		// 再検索用の費目コードをセッションから取得する。
		Map<String, Object> sessionMap = CommonUtil.getSessionMap(request, SESSION_UPD_DEL_RE_SEARCH_KANKOU);

		// セッションから取得できた場合
		if (sessionMap != null) {
			// 画面に費目コードを設定する。
			formMap.put(KankouUpdDelConst.KEY_KANKOU_ID, sessionMap.get(KankouUpdDelConst.KEY_KANKOU_ID));
			// セッションに保持している費目コードを削除する。
			request.getSession().removeAttribute(SESSION_UPD_DEL_RE_SEARCH_KANKOU);
		}
		// メッセージをセッションから取得する。
		String messageKey = CommonUtil.getStr(request.getSession().getAttribute(SESSION_UPD_DEL_MESSAGE_KANKOU));

		// セッションから取得できた場合
		if (!EMPTY.equals(messageKey)) {
			ActionMessages messages = new ActionMessages();
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(messageKey));
			saveMessages(request, messages);
			request.getSession().removeAttribute(SESSION_UPD_DEL_MESSAGE_KANKOU);
		}

		// 初期表示取得
		KankouUpdDelDto dto = kankouUpdDelService.updDelInit(formMap, loginDto);

		// 取得した情報をリクエストに設定
		request.setAttribute(KankouUpdDelConst.FORM_KANKOU_UPD_DEL, dto);
		// 取得した情報をセッションに設定
		request.getSession().setAttribute(SESSION_UPD_DEL_DTO, dto);

		// 処理成功時の遷移先を指定する。
		return map.findForward(ACTION_FOWARD_SUCCESS);
	}
}
