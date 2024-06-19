package cashbook.action.common;

import static cashbook.util.Const.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import cashbook.dto.common.UserRegistDto;
import cashbook.service.common.UserRegistService;
import cashbook.util.CommonUtil;
import cashbook.util.Const;
import cashbook.util.UserConst;

/**
 * ユーザ登録画面 初期表示アクションクラス
 * @author soppra
 */
public class UserRegistInitAction extends Action{
	
	/** ユーザ登録サービス */
	private UserRegistService userRegistService;

	/**
	 * ユーザ登録サービスを設定します。
	 * @param userRegistService ユーザ登録サービス
	 */
	public void setUserRegistService(UserRegistService userRegistService) {
		this.userRegistService = userRegistService;
	}
	
	/**
	 * <p><b>
	 * ユーザ登録画面
	 * <br>ユーザ登録処理
	 * </b></p>
	 * struts-configのActionに設定された場合に起動する処理です。<br>
	 *
	 * @param map      アクションマッピング
	 * @param form     フォーム
	 * @param request  リクエスト
	 * @param response レスポンス
	 * @return アクションフォワード
	 * @throws Exception すべての例外
	 */
	@Override
	public ActionForward execute(ActionMapping map, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
			/*-------------------------------------------------*
			 * １．セッションから戻り先のアクションを取得する。*
			 *-------------------------------------------------*/
			
			// 戻り先をセッションから取得する。
			String backAction = CommonUtil.getStr(request.getSession().getAttribute(SESSION_REGIST_BACK_USER));
			// セッションに戻り先を保持する。
			request.getSession().setAttribute(SESSION_REGIST_BACK_USER, backAction);
			
			/*---------------------------------------------------*
			 * ２．セッションから表示するメッセージを取得する。  *
			 *---------------------------------------------------*/
		
			// メッセージをセッションから取得する。
			String messageKey = CommonUtil.getStr(request.getSession().getAttribute(SESSION_REGIST_MESSAGE_USER));

			// セッションから取得できた場合
			if (!EMPTY.equals(messageKey)) {

				ActionMessages messages = new ActionMessages();
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(messageKey));
				saveMessages(request, messages);
				request.getSession().removeAttribute(SESSION_REGIST_MESSAGE_USER);
			}

			UserRegistDto userRegistDto = new UserRegistDto();

			// 取得した情報をリクエストに登録
			request.getSession().setAttribute(UserConst.FORM_USER_REGIST, userRegistDto);
			// 取得した情報をセッションに登録
			request.getSession().setAttribute(Const.SESSION_REGIST_DTO_USER, userRegistDto);
						
			// 処理成功時の遷移先を指定する。
			return map.findForward(Const.ACTION_FOWARD_SUCCESS);
	}
}
