package cashbook.action.kankou;

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
import cashbook.dto.kankou.KankouRegistDto;
import cashbook.service.kankou.KankouRegistService;
import cashbook.util.CommonUtil;
import cashbook.util.KankouRegistConst;
import cashbook.util.UserConst;

public class KankouRegistInitAction extends BaseAction{
	
	/** 観光地登録サービス */
	private KankouRegistService kankouRegistService;
	
	/**
	 * 観光地登録サービスを設定します。
	 * @param kankouRegistService 個人マスタサービス
	 */
	public void setKankouRegistService(KankouRegistService kankouRegistService) {
		this.kankouRegistService = kankouRegistService;
	}
	
	/**
	 * <p><b>
	 * 観光地登録画面
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



		// 再検索用の個人IDをセッションから取得する。
		Map<String, Object> sessionMap = CommonUtil.getSessionMap(request, SESSION_REGIST_RE_SEARCH_KANKOU);

		// セッションから取得できた場合
		if (sessionMap != null) {
			// 画面にユーザIDを設定する。
			formMap.put(UserConst.KEY_USER_ID, sessionMap.get(UserConst.KEY_USER_ID));
			// セッションに保持しているユーザIDを削除する。
			request.getSession().removeAttribute(SESSION_REGIST_RE_SEARCH_KANKOU);
		}

		// メッセージをセッションから取得する。
		String messageKey = CommonUtil.getStr(request.getSession().getAttribute(SESSION_REGIST_MESSAGE_KANKOU));

		// セッションから取得できた場合
		if (!EMPTY.equals(messageKey)) {
			ActionMessages messages = new ActionMessages();
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(messageKey));
			saveMessages(request, messages);
			request.getSession().removeAttribute(SESSION_REGIST_MESSAGE_KANKOU);

		}

		// 初期表示取得
		KankouRegistDto dto =  kankouRegistService.registInit(formMap);
		
		// 取得した情報をリクエストに設定
		request.setAttribute(KankouRegistConst.FORM_KANKOU_REGIST, dto);
		// 取得した情報をセッションに設定
		request.getSession().setAttribute(SESSION_REGIST_DTO_KANKOU, dto);

		// 処理成功時の遷移先を指定する。
		return map.findForward(ACTION_FOWARD_SUCCESS);
	}
}
