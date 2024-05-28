package cashbook.action.setai;

import static cashbook.util.Const.ACTION_FOWARD_SUCCESS;
import static cashbook.util.Const.SESSION_LIST_DTO_SETAI;
import static cashbook.util.Const.SESSION_REGIST_BACK_SETAI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cashbook.action.common.BaseAction;
import cashbook.dto.common.LoginDto;
import cashbook.dto.setai.SetaiListDto;
import cashbook.service.setai.SetaiService;
import cashbook.util.SetaiConst;

/**
 * 世帯マスタメンテ画面初期表示アクションクラス
 * @author soppra
 */
public class SetaiListInitAction extends BaseAction {

	/** 世帯マスタサービス */
	private SetaiService setaiService;

	/**
	 * 世帯マスタサービス を設定します。
	 * @param setaiService 世帯マスタサービス
	 */
	public void setSetaiService(SetaiService setaiService) {
		this.setaiService = setaiService;
	}

	/**
	 * <p><b>
	 * 世帯マスタメンテ画面
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

		// 世帯マスタ登録画面の戻り先をセッションから削除する。
		request.getSession().removeAttribute(SESSION_REGIST_BACK_SETAI);

		// 世帯マスタメンテ初期表示情報を取得
		SetaiListDto dto = setaiService.listInit();

		// 取得した情報をリクエストに設定
		request.setAttribute(SetaiConst.FORM_SETAI_LIST, dto);
		// 取得した情報をセッションに設定
		request.getSession().setAttribute(SESSION_LIST_DTO_SETAI, dto);

		// 処理成功時の遷移先を指定する。
		return map.findForward(ACTION_FOWARD_SUCCESS);
	}
}
