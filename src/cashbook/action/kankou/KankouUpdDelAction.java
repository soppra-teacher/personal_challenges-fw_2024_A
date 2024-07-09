package cashbook.action.kankou;

import static cashbook.util.Const.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.TransactionRolledbackException;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;

import cashbook.action.common.BaseAction;
import cashbook.dto.common.LoginDto;
import cashbook.dto.kankou.KankouUpdDelDto;
import cashbook.service.kankou.KankouService;
import cashbook.util.CommonUtil;
import cashbook.util.Const;
import cashbook.util.KankouConst;

/**
 * 観光地更新削除・画面・更新クションクラス
 * @author soppra
 */
public class KankouUpdDelAction extends BaseAction {

	/** 観光テーブルサービス */
	private KankouService kankouService;

	/**
	 * テーブルサービスを設定します。
	 * @param kankouService 観光サービス
	 */
	public void setKankouService(KankouService kankouService) {
		this.kankouService = kankouService;
	}

	/**
	 * <p>
	 * 観光地詳細表示・更新・削除画面
	 * <br>詳細表示・更新・削除処理
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

		//観光IDのIDを取得
		String kankouId = (String) request.getSession().getAttribute("kankouId");
		formMap.put(KankouConst.KEY_KANKOU_ID, kankouId);
		//評価値を取得
		String compareHyoka = (String) request.getSession().getAttribute(KankouConst.KEY_COMPARE_HYOKA);

		String operation = request.getParameter(ACTION_FOWARD_OPERATION);

		//メッセージ表示
		String messageKey = "";
		//登録
		if (Const.ACTION_FOWARD_INSERT.equals(operation)) {
			//評価値登録処理
			kankouService.hyokaIns(formMap, loginDto);
			
			//登録メッセージ
			messageKey = MSG_SUCCESS_INSERT;
		//更新処理
		} else if (Const.ACTION_FOWARD_UPDATE.equals(operation)) {

			// 更新処理(評価値テーブル、観光地テーブル)
			try {
				kankouService.update(formMap, loginDto, request, kankouId, compareHyoka);
			} catch (Exception e) {
				ActionErrors errors = new ActionErrors();
				errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(Const.MSG_ERRORS_NO_UPD));
				saveErrors(request, errors);
				return map.getInputForward();
			}
			// 写真更新処理
			if (!CommonUtil.isNull((String) formMap.get(KankouConst.KEY_IMAGE_STRING))) {
				try {
					CommonUtil.fileUpd(formMap, request);
				} catch (IOException e) {
					ActionErrors errors = new ActionErrors();
					errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(Const.MSG_ERRORS_NO_FILE));
					saveErrors(request, errors);
					return map.getInputForward();
				}
			}

			//セッションの評価値の値を更新する。
			String hyoka = CommonUtil.getStr(formMap.get(KankouConst.KEY_HYOKA));
			request.getSession().setAttribute(KankouConst.KEY_COMPARE_HYOKA, hyoka);

			//更新メッセージ
			messageKey = MSG_SUCCESS_UPDATE;
		//削除処理
		} else if (Const.ACTION_FOWARD_DELETE.equals(operation)) {

			// 削除処理(評価値テーブル、観光地テーブル)
			try {
				kankouService.delete(formMap, request);
			} catch (TransactionRolledbackException e) {
				//削除失敗
				ActionErrors errors = new ActionErrors();
				errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(Const.MSG_ERRORS_NO_DEL));
				saveErrors(request, errors);
				return map.getInputForward();
			}
			//写真削除処理
			Path destPath = Paths.get(request.getServletContext().getRealPath("/img/kankouti/") + kankouId + ".png");
			try {
				Files.delete(destPath);
			} catch (IOException e) {
				ActionErrors errors = new ActionErrors();
				errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(Const.MSG_ERRORS_NO_FILE_UPD));
				saveErrors(request, errors);
				return map.getInputForward();
			}

			return map.findForward(ACTION_FOWARD_DELETE);
		}

		//メッセージの表示
		if (!CommonUtil.isNull(messageKey)) {
			ActionMessages messages = new ActionMessages();
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(messageKey));
			saveMessages(request, messages);
		}
		//再検索処理
		KankouUpdDelDto dto = kankouService.updDelInit(formMap, loginDto);
		// 取得した情報をリクエストに設定
		request.setAttribute(KankouConst.FORM_KANKOU_UPD_DEL, dto);
		// 取得した情報をセッションに設定
		request.getSession().setAttribute(SESSION_UPD_DEL_DTO, dto);

		return map.findForward(ACTION_FOWARD_SUCCESS);
	}
}
