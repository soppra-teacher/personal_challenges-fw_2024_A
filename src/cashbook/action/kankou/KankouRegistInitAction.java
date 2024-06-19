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
import cashbook.service.kankou.KankouService;
import cashbook.service.kojin.KojinService;
import cashbook.util.CommonUtil;
import cashbook.util.KankouConst;
import cashbook.util.KojinConst;

public class KankouRegistInitAction extends BaseAction{
	/** 個人マスタサービス */
	private KojinService kojinService;
	
	/** 観光地登録サービス */
	private KankouService kankouService;

	/**
	 * 個人マスタサービスを設定します。
	 * 後に消去予定
	 * @param kojinService 個人マスタサービス
	 */
	public void setKojinService(KojinService kojinService) {
		this.kojinService = kojinService;
	}
	
	public void setKankouService(KankouService kankouService) {
		this.kankouService = kankouService;
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

		// 戻り先をセッションから取得する。
		String backAction = CommonUtil.getStr(request.getSession().getAttribute(SESSION_REGIST_BACK_KANKOU));

		// セッションから取得できない場合
		if (EMPTY.equals(backAction)) {

			// 個人IDがフォームに設定されていない場合
			//後に、ユーザIDを設定するようにしておく
			if (CommonUtil.isNull(CommonUtil.getStr(formMap.get(KojinConst.KEY_KOJIN_ID)))) {
				// メニューからの遷移と判定
				backAction = ACTION_FOWARD_BACK_MENU;

			} else {
				// 観光地検索・一覧からの遷移の場合
				backAction = ACTION_FOWARD_BACK_LIST;

			}
			// セッションに戻り先を保持する。
			request.getSession().setAttribute(SESSION_REGIST_BACK_KANKOU, backAction);
		}

		// 再検索用の個人IDをセッションから取得する。
		Map<String, Object> sessionMap = CommonUtil.getSessionMap(request, SESSION_REGIST_RE_SEARCH_KANKOU);

		// セッションから取得できた場合
		if (sessionMap != null) {
			// 画面に個人IDを設定する。
			//後に、ユーザIDに変更しておく。
			formMap.put(KojinConst.KEY_KOJIN_ID, sessionMap.get(KojinConst.KEY_KOJIN_ID));
			// セッションに保持している個人IDを削除する。
			request.getSession().removeAttribute(SESSION_REGIST_RE_SEARCH_KANKOU);

		}

		// メッセージをセッションから取得する。
		//ユーザIDに変更後に、KANKOUへ変更する
		String messageKey = CommonUtil.getStr(request.getSession().getAttribute(SESSION_REGIST_MESSAGE_KOJIN));

		// セッションから取得できた場合
		if (!EMPTY.equals(messageKey)) {
			ActionMessages messages = new ActionMessages();
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(messageKey));
			saveMessages(request, messages);
			request.getSession().removeAttribute(SESSION_REGIST_MESSAGE_KOJIN);

		}

		// 初期表示取得
		KankouRegistDto dto =  kankouService.registInit(formMap);
		
		// 取得した情報をリクエストに設定
		request.setAttribute(KankouConst.FORM_KANKOU_REGIST, dto);
		// 取得した情報をセッションに設定
		request.getSession().setAttribute(SESSION_REGIST_DTO_KANKOU, dto);

		// 処理成功時の遷移先を指定する。
		return map.findForward(ACTION_FOWARD_SUCCESS);
	}
}
