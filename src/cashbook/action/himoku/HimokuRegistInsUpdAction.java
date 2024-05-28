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
 * 費目マスタ登録画面 登録・更新アクションクラス
 * @author soppra
 */
public class HimokuRegistInsUpdAction extends BaseAction {

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
	 * <p>
	 * 費目マスタ登録画面
	 * <br>登録・更新処理
	 * </p>
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

		// 登録・更新処理
		himokuService.registInsUpd(formMap, loginDto);

		// フォーム．リビジョンが未設定の場合
		if (CommonUtil.isNull(CommonUtil.getStr(formMap.get(ITEM_REVISION)))) {
			// 登録成功メッセージをセッションに設定
			request.getSession().setAttribute(SESSION_REGIST_MESSAGE_HIMOKU, MSG_SUCCESS_INSERT);

		} else {
			// 更新成功メッセージをセッションに設定
			request.getSession().setAttribute(SESSION_REGIST_MESSAGE_HIMOKU, MSG_SUCCESS_UPDATE);

		}

		// 検索条件をセッションに保持（再検索用）
		request.getSession().setAttribute(SESSION_REGIST_RE_SEARCH_HIMOKU, formMap);

		// 処理成功時の遷移先を指定する。
		return map.findForward(ACTION_FOWARD_SUCCESS);

	}
}
