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
import cashbook.service.kankou.KankouService;
import cashbook.util.CommonUtil;
import cashbook.util.KankouConst;

/**
 * 観光地検索画面アクションクラス
 * @author soppra
 */
public class KankouListSearchAction extends BaseAction {

	/** 観光地検索サービス */
	private KankouService kankouService;

	/**
	 * 観光サービスを設定します。
	 * @param kankouService 観光テーブルサービス
	 */
	public void setKojinService(KankouService kankouService) {
		this.kankouService = kankouService;
	}

	/**
	 * <p><b>
	 * 観光地検索一覧表示画面
	 * <br>検索処理
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
		System.out.println(formMap);
		System.out.println(request.getParameter(ACTION_FOWARD_OPERATION));
		System.out.println(request.getParameter("hiddenKankouNm"));
		System.out.println(request.getParameter("hiddenUserId"));
		System.out.println(request.getParameter("hiddenCategoryKey"));

		// 個人マスタメンテ画面 検索処理
		KankouListDto dto = kankouService.listSearch(formMap);

		// 取得した情報をリクエストに登録
		request.setAttribute(KankouConst.FORM_KANKOU_LIST, dto);
		// 取得した情報をセッションに登録
		request.getSession().setAttribute(SESSION_LIST_DTO_KANKOU, dto);
		// 検索条件をセッションに登録（再検索用）
		//request.getSession().setAttribute(SESSION_LIST_RE_SEARCH_KOJIN, formMap);

		// 処理成功時の遷移先を指定する。
		return map.findForward(ACTION_FOWARD_SUCCESS);
	}
}
