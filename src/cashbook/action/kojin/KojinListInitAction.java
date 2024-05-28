package cashbook.action.kojin;

import static cashbook.util.Const.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cashbook.action.common.BaseAction;
import cashbook.dto.common.LoginDto;
import cashbook.dto.kojin.KojinListDto;
import cashbook.service.kojin.KojinService;
import cashbook.util.KojinConst;

/**
 * 個人マスタメンテ画面 初期表示アクションクラス
 * @author soppra
 */
public class KojinListInitAction extends BaseAction {

	/** 個人マスタサービス */
	private KojinService kojinService;

	/**
	 * 個人マスタサービスを設定します。
	 * @param kojinService 個人マスタサービス
	 */
	public void setKojinService(KojinService kojinService) {
		this.kojinService = kojinService;
	}

	/**
	 * <p><b>
	 * 個人マスタメンテ画面
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

		// 個人マスタ登録画面の戻り先をセッションから削除する。
		request.getSession().removeAttribute(SESSION_REGIST_BACK_KOJIN);

		// 個人マスタメンテ初期表示情報を取得
		KojinListDto dto = kojinService.listInit();

		// 取得した情報をリクエストに設定
		request.setAttribute(KojinConst.FORM_KOJIN_LIST, dto);
		// 取得した情報をセッションに設定
		request.getSession().setAttribute(SESSION_LIST_DTO_KOJIN, dto);

		// 処理成功時の遷移先を指定する。
		return map.findForward(ACTION_FOWARD_SUCCESS);
	}
}
