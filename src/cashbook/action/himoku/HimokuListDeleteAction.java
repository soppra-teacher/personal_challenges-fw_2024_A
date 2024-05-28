package cashbook.action.himoku;

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
import cashbook.service.himoku.HimokuService;
import cashbook.util.CommonUtil;

/**
 * 費目マスタメンテ画面 削除アクションクラス
 * @author soppra
 */
public class HimokuListDeleteAction extends BaseAction {

	/** 費目マスタサービス */
	private HimokuService himokuService;

	/**
	 * 費目マスタサービスを設定します。
	 * @param himokuService 費目マスタサービス
	 */
	public void setHimokuService(HimokuService himokuService) {
		this.himokuService = himokuService;
	}

	/**
	 * <p><b>
	 * 費目マスタメンテ画面
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

		// 削除対象チェック
		if (checkDeleteTarget((String[]) formMap.get(ITEM_CHECKBOX_DELETE), request)) {
			return map.getInputForward();
		}

		// 削除処理
		himokuService.listDelete(formMap, loginDto);

		// 削除完了メッセージをセッションに保持
		request.getSession().setAttribute(SESSION_LIST_MESSAGE_HIMOKU, MSG_SUCCESS_DELETE);

		// 処理成功時の遷移先を指定する。
		return map.findForward(ACTION_FOWARD_SUCCESS);
	}
}
