package cashbook.action.kankou;

import static cashbook.util.Const.*;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import cashbook.action.common.BaseAction;
import cashbook.dto.common.LoginDto;
import cashbook.dto.kankou.KankouUpdDelDto;
import cashbook.service.kankou.KankouService;
import cashbook.util.CommonUtil;
import cashbook.util.KankouConst;

/**
 * 更新・削除画面 初期表示アクションクラス
 * @author soppra
 */
public class KankouUpdDeInitAction extends BaseAction {

	/** 観光マスタサービス */
	private KankouService kankouService;

	private HttpSession session;

	/**
	 * 観光マスタサービスを設定します。
	 * @param kankouService 観光マスタサービス
	 */
	public void setKankouService(KankouService kankouService) {
		this.kankouService = kankouService;
	}

	/**
	 * <p><b>
	 * 観光地更新・削除
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

		// フォームの値を取得する。
		Map<String, Object> formMap = CommonUtil.getFormMap((DynaActionForm) form);

		//セッション**
		session = request.getSession();

		// ユーザーIDをセッションに保存
		String logUserId = loginDto.getUserId();
		session.setAttribute(KankouConst.KEY_LOG_USER_ID, logUserId);

		// 観光地IDをセッションに保存
		String kankouId = CommonUtil.getStr(formMap.get(KankouConst.KEY_KANKOU_ID));
		session.setAttribute(KankouConst.KEY_KANKOU_ID, kankouId);

		//評価値をセッションに保持
		String compareHyoka = CommonUtil.getStr(formMap.get(KankouConst.KEY_HYOKA));
		session.setAttribute(KankouConst.KEY_COMPARE_HYOKA, compareHyoka);

		// 戻り先をセッションから取得する。
		String backAction = CommonUtil.getStr(request.getSession().getAttribute(SESSION_UPD_DEL_BACK));

		// セッションに戻り先を保持する。
		request.getSession().setAttribute(SESSION_UPD_DEL_BACK, backAction);
		

		// 初期表示取得
		KankouUpdDelDto dto = kankouService.updDelInit(formMap, loginDto);

		// 取得した情報をリクエストに設定
		request.setAttribute(KankouConst.FORM_KANKOU_UPD_DEL, dto);
		// 取得した情報をセッションに設定
		request.getSession().setAttribute(SESSION_UPD_DEL_DTO, dto);

		// 処理成功時の遷移先を指定する。
		return map.findForward(ACTION_FOWARD_SUCCESS);
	}
}
