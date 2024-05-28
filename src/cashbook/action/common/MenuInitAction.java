package cashbook.action.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cashbook.dto.common.LoginDto;
import cashbook.util.Const;

/**
 * メニュー画面アクションクラス
 * @author soppra
 */
public class MenuInitAction extends BaseAction {

	/**
	 * <p><b>
	 * メニュー画面
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

		// 各登録画面の戻り先をセッションから削除する。
		request.getSession().removeAttribute(Const.SESSION_REGIST_BACK_HIMOKU); // 費目マスタ登録画面 戻り先
		request.getSession().removeAttribute(Const.SESSION_REGIST_BACK_SETAI); // 世帯マスタ登録画面 戻り先
		request.getSession().removeAttribute(Const.SESSION_REGIST_BACK_KOJIN); // 個人マスタ登録画面 戻り先
		request.getSession().removeAttribute(Const.SESSION_REGIST_BACK_SHUSHI); // 収支登録画面 戻り先

		// 処理成功時の遷移先を指定する。
		return map.findForward(Const.ACTION_FOWARD_SUCCESS);
	}
}
