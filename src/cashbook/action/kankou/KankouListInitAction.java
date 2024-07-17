package cashbook.action.kankou;

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
import cashbook.dto.kankou.KankouListDto;
import cashbook.service.kankou.KankouListService;
import cashbook.util.CommonUtil;
import cashbook.util.KankouListConst;


/**
 * 観光地検索・一覧表示画面 初期表示アクションクラス
 * @author soppra
 */
public class KankouListInitAction extends BaseAction {

	/** 観光サービス */
	private KankouListService kankouListService;

	/**
	 * 観光サービスを設定します。
	 * @param kankouListService 観光テーブルサービス
	 */
	public void setKankouListService(KankouListService kankouListService) {
		this.kankouListService = kankouListService;
	}

	/**
	 * <p><b>
	 * 観光地検索画面
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
		
		Map<String, Object> formMap = CommonUtil.getFormMap((DynaActionForm) form);
		
		// 観光地検索初期表示情報を取得
		KankouListDto dto = kankouListService.listInit(formMap, request);

		// 取得した情報をリクエストに設定
		request.setAttribute(KankouListConst.FORM_KANKOU_LIST, dto);
		// 取得した情報をセッションに設定
		request.getSession().setAttribute(SESSION_LIST_DTO_KANKOU, dto);
		// 処理成功時の遷移先を指定する。
		return map.findForward(ACTION_FOWARD_SUCCESS);
	}
}
