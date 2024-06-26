package cashbook.action.common;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

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
			
			// 再検索用のhiddenをセッションから取得する。
			Map<String, Object> sessionMap = CommonUtil.getSessionMap(request, Const.SESSION_REGIST_SESSION_FORM_USER);
			

			/*---------------------------------------------------*
			 * ２．セッションから表示するメッセージを取得する。  *
			 *---------------------------------------------------*/
			
			UserRegistDto userRegistDto = new UserRegistDto();
			
			// セッションから取得できた場合
			if (sessionMap != null) {
				// 画面にHiddenを設定する。
				userRegistDto.setHidden(CommonUtil.getStr(sessionMap.get(UserConst.KEY_USER_HIDDEN)));
				// セッションに保持しているHiddenを削除する。
				request.getSession().removeAttribute(Const.SESSION_REGIST_SESSION_FORM_USER);
			}
			// 取得した情報をセッションに登録
			request.getSession().setAttribute(Const.SESSION_REGIST_DTO_USER, userRegistDto);
			
			// 処理成功時の遷移先を指定する。
			return map.findForward(Const.ACTION_FOWARD_SUCCESS);
	}
}
