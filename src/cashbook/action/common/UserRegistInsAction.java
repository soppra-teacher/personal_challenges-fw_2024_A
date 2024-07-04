package cashbook.action.common;

import static cashbook.util.Const.*;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;

import cashbook.exception.CommonValidateException;
import cashbook.service.common.UserRegistService;
import cashbook.util.CommonUtil;
import cashbook.util.UserConst;

/**
 * ユーザ登録画面 登録アクションクラス
 * @author soppra
 */
public class UserRegistInsAction extends Action{
	
	/**  ユーザ登録サービス */
	private UserRegistService userRegistService;

	/**
	 *  ユーザ登録サービスを設定します。
	 * @param userService 個人マスタサービス
	 */
	public void setUserRegistService(UserRegistService userRegistService) {
		this.userRegistService = userRegistService;
	}
	
	/**
	 * <p><b>
	 * ユーザ登録画面
	 * <br>登録処理
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
	public ActionForward execute(ActionMapping map, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			// フォームの値を取得する。
			Map<String, Object> formMap = CommonUtil.getFormMap((DynaActionForm) form);
		
			// 登録
			userRegistService.registIns(formMap);
			// hiddenパラメータを"1"に設定する。
			formMap.put(UserConst.KEY_USER_HIDDEN, SESSION_REGIST_HIDDEN_OK);
						
			

			// 登録成功メッセージをセッションに設定
			request.getSession().setAttribute(SESSION_REGIST_MESSAGE_USER, MSG_SUCCESS_INSERT_USER );
			// 取得した情報をセッションに設定
			request.getSession().setAttribute(SESSION_REGIST_SESSION_FORM_USER, formMap);
			
			return map.findForward(ACTION_FOWARD_SUCCESS);

		} catch (CommonValidateException e) {
			ActionErrors errors = new ActionErrors();
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(e.getMessageKey()));
			saveErrors(request, errors);
			
			Map<String, Object> formMap = CommonUtil.getFormMap((DynaActionForm) form);
			// hiddenパラメータを"0"に設定する。
			formMap.put(UserConst.KEY_USER_HIDDEN, SESSION_REGIST_HIDDEN_NG);
			
			return map.getInputForward();

		}
		
	}
}