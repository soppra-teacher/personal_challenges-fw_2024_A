package cashbook.action.kojin;

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
import cashbook.service.kojin.KojinService;
import cashbook.util.CommonUtil;
import cashbook.util.KojinConst;
import cashbook.util.SetaiConst;

/**
 * 個人マスタ登録画面 登録・更新アクションクラス
 * @author soppra
 */
public class KojinRegistInsUpdAction extends BaseAction {

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
	 * <p>
	 * 個人マスタ登録画面
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

		// 世帯主フラグ有無チェック
		if (SETAINUSHI_FLG_ON.equals(formMap.get(KojinConst.KEY_SETAINUSI_FLG))) {
			// チェック済みの場合、パラメータを"1"に設定する。
			formMap.put(KojinConst.KEY_SETAINUSI_FLG_VALUE, SETAINUSHI_ON);

		} else {
			// 未チェック済の場合、パラメータを"0"に設定する。
			formMap.put(KojinConst.KEY_SETAINUSI_FLG_VALUE, SETAINUSHI_OFF);

		}

		// 世帯ＩＤを設定する
		formMap.put(SetaiConst.KEY_SETAI_ID, formMap.get(SetaiConst.KEY_SETAI_NM_KEY));
		// 登録・更新
		kojinService.registInsUpd(formMap, loginDto);

		// フォーム．リビジョンが未設定の場合
		if (CommonUtil.isNull(CommonUtil.getStr(formMap.get(ITEM_REVISION)))) {
			// 登録成功メッセージをセッションに設定
			request.getSession().setAttribute(SESSION_REGIST_MESSAGE_KOJIN, MSG_SUCCESS_INSERT);

		} else {
			// 更新成功メッセージをセッションに設定
			request.getSession().setAttribute(SESSION_REGIST_MESSAGE_KOJIN, MSG_SUCCESS_UPDATE);

		}

		// 検索条件をセッションに保持（再検索用）
		request.getSession().setAttribute(SESSION_REGIST_RE_SEARCH_KOJIN, formMap);

		return map.findForward(ACTION_FOWARD_SUCCESS);
	}
}
