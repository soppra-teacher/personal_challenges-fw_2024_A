package cashbook.action.himoku;

import static cashbook.util.Const.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cashbook.action.common.BaseAction;
import cashbook.dto.common.LoginDto;
import cashbook.dto.himoku.HimokuListDto;
import cashbook.service.himoku.HimokuService;
import cashbook.util.HimokuConst;

/**
 * 費目マスタメンテ画面 初期表示アクションクラス
 * @author soppra
 */
public class HimokuListInitAction extends BaseAction {

	/** 費目マスタサービス */
	private HimokuService himokuService;

	/**
	 * 費目マスタサービス を設定します。
	 * @param himokuService 費目マスタサービス
	 */
	public void setHimokuService(HimokuService himokuService) {
		this.himokuService = himokuService;
	}

	/**
	 * <p><b>
	 * 費目マスタメンテ画面
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

		// 費目マスタ登録画面の戻り先をセッションから削除する。
		request.getSession().removeAttribute(SESSION_REGIST_BACK_HIMOKU);

		// 費目マスタメンテ初期表示情報を取得
		HimokuListDto dto = himokuService.listInit();

		// 取得した情報をリクエストに設定
		request.setAttribute(HimokuConst.FORM_HIMOKU_LIST, dto);
		// 取得した情報をセッションに設定
		request.getSession().setAttribute(SESSION_LIST_DTO_HIMOKU, dto);

		// 処理成功時の遷移先を指定する。
		return map.findForward(ACTION_FOWARD_SUCCESS);

	}
}
