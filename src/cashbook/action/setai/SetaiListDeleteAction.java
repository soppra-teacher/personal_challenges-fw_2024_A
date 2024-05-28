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
import cashbook.exception.CommonValidateException;
import cashbook.service.setai.SetaiService;
import cashbook.util.CommonUtil;

/**
 * 世帯マスタメンテ画面 削除アクションクラス
 * @author soppra
 */
public class SetaiListDeleteAction extends BaseAction {

	/** 世帯マスタサービス */
	private SetaiService setaiService;

	/**
	 * 世帯マスタサービスを設定します。
	 * @param setaiService 世帯マスタサービス
	 */
	public void setSetaiService(SetaiService setaiService) {
		this.setaiService = setaiService;
	}

	/**
	 * <p><b>
	 * 世帯マスタメンテ画面
	 * <br>削除処理
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

		// フォームの値を取得
		Map<String, Object> formMap = CommonUtil.getFormMap((DynaActionForm) form);

		// 世帯主チェック
		if (setaiService.chkDelete(formMap, loginDto)) {
			throw new CommonValidateException(MSG_SETAI_ERROR);
		}

		// 削除対象チェック
		if (checkDeleteTarget((String[]) formMap.get(ITEM_CHECKBOX_DELETE), request)) {
			return map.getInputForward();
		}

		// 削除処理
		if (!setaiService.listDelete(formMap, loginDto)) {
			throw new CommonValidateException(MSG_SETAI_ERROR_2);
		}

		// 削除完了メッセージをセッションに保持
		request.getSession().setAttribute(SESSION_LIST_MESSAGE_SETAI, MSG_SUCCESS_DELETE);

		// 処理成功時の遷移先を指定する。
		return map.findForward(ACTION_FOWARD_SUCCESS);
	}
}
