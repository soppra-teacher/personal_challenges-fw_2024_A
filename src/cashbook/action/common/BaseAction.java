package cashbook.action.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import cashbook.dto.common.LoginDto;
import cashbook.exception.CommonValidateException;
import cashbook.util.Const;

/**
 * アクション基底クラス
 * @author soppra
 */
public abstract class BaseAction extends Action {

	/**
	 * 各機能毎のメイン処理
	 * @param map アクションマッピング
	 * @param form フォーム
	 * @param request リクエスト
	 * @param response レスポンス
	 * @param loginDto ログイン情報DTO
	 * @return アクションフォワード
	 * @throws Exception すべての例外
	 */
	protected abstract ActionForward doProcess(ActionMapping map, ActionForm form,
			HttpServletRequest request, HttpServletResponse response, LoginDto loginDto) throws Exception;

	/**
	 * <p><b>
	 * 主処理
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

		// ログイン情報取得
		LoginDto loginDto = (LoginDto) request.getSession().getAttribute("LOGIN_DTO");

		// ログイン情報が取得できない場合はセッションエラーページへ遷移
		if (loginDto == null) {
			return map.findForward("sessionError");
		}

		try {
			return doProcess(map, form, request, response, loginDto);

		} catch (CommonValidateException e) {
			ActionErrors errors = new ActionErrors();
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(e.getMessageKey()));
			saveErrors(request, errors);
			return map.getInputForward();

		}
	}

	/**
	 * 共通機能
	 * <br>削除対象チェックボックス処理対象判定
	 *
	 * @param checkDel 削除対象チェックボックス
	 * @param request リクエスト
	 * @return True:エラーあり、False:エラーなし
	 */
	protected boolean checkDeleteTarget(String[] checkDel, HttpServletRequest request) {

		// 選択レコードが無ければエラー
		if (checkDel == null || checkDel.length == 0) {

			ActionErrors errors = new ActionErrors();
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(Const.MSG_ERRORS_NO_DELETE));
			saveErrors(request, errors);
			return true;

		}
		return false;
	}

}
