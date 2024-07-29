package cashbook.action.kankou;

import static cashbook.util.Const.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import cashbook.action.common.BaseAction;
import cashbook.dto.common.LoginDto;
import cashbook.exception.CommonValidateException;
import cashbook.service.common.CommonServiceImpl;
import cashbook.service.kankou.KankouUpdDelService;
import cashbook.util.CommonUtil;
import cashbook.util.Const;
import cashbook.util.KankouUpdDelConst;

/**
 * 観光地更新削除・画面・更新クションクラス
 * @author soppra
 */
public class KankouUpdDelAction extends BaseAction {

	/** 観光テーブルサービス */
	private KankouUpdDelService kankouUpdDelService;

	/**
	 * テーブルサービスを設定します。
	 * @param kankouUpdDelService 観光サービス
	 */
	public void setKankouUpdDelService(KankouUpdDelService kankouUpdDelService) {
		this.kankouUpdDelService = kankouUpdDelService;
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
		String kankouId = (String) request.getSession().getAttribute(KankouUpdDelConst.KEY_KANKOU_ID);
		formMap.put(KankouUpdDelConst.KEY_KANKOU_ID, kankouId);
		//評価値を取得
		String compareHyoka = (String) request.getSession().getAttribute(KankouUpdDelConst.KEY_COMPARE_HYOKA);

		String operation = request.getParameter(ACTION_FOWARD_OPERATION);

		//登録
		if (Const.ACTION_FOWARD_INSERT.equals(operation)) {
			//評価値登録処理
			kankouUpdDelService.hyokaIns(formMap, loginDto, kankouId);

			//登録メッセージ
			request.getSession().setAttribute(SESSION_UPD_DEL_MESSAGE_KANKOU, MSG_SUCCESS_INSERT);
			//更新処理
		} else if (Const.ACTION_FOWARD_UPDATE.equals(operation)) {

			// 更新処理(評価値テーブル、観光地テーブル)
			try {
				kankouUpdDelService.update(formMap, loginDto, request, kankouId, compareHyoka);
			} catch (Exception e) {
				throw new CommonValidateException(Const.MSG_ERRORS_NO_UPD);
			}
			// 写真が新しく選ばれているかを判断する。
			if (!CommonUtil.isNull((String) formMap.get(Const.KEY_IMAGE_STRING))) {
				try {
					//写真を更新するメソッドの呼び出し。
					CommonServiceImpl commonImp = new CommonServiceImpl();
					commonImp.fileUpdIns(formMap, request);
				} catch (IOException e) {
					throw new CommonValidateException(Const.MSG_ERRORS_NO_FILE);
				}
			}

			//更新メッセージ
			request.getSession().setAttribute(SESSION_UPD_DEL_MESSAGE_KANKOU, MSG_SUCCESS_UPDATE);
			//削除処理
		} else if (Const.ACTION_FOWARD_DELETE.equals(operation)) {

			// 削除処理(評価値テーブル、観光地テーブル)
			try {
				kankouUpdDelService.delete(formMap, request);
			} catch (Exception e) {
				//削除失敗
				throw new CommonValidateException(Const.MSG_ERRORS_NO_DEL);
			}
			//写真削除処理
			Path destPath = Paths.get(request.getServletContext().getRealPath(FILE_PATH) + kankouId + Const.IMAGE_PNG);
			try {
				Files.delete(destPath);
				request.getSession().setAttribute(SESSION_LIST_MESSAGE_KANKOU, MSG_SUCCESS_DELETE);
			} catch (NoSuchFileException e) {
				request.getSession().setAttribute(SESSION_LIST_MESSAGE_KANKOU, MSG_SUCCESS_DELETE);
			} catch (IOException e) {
				request.getSession().setAttribute(SESSION_LIST_MESSAGE_KANKOU, MSG_ERRORS_NO_FILE_UPD);

			}

			return map.findForward(ACTION_FOWARD_DELETE);
		}

		// 検索条件をセッションに保持（再検索用）
		request.getSession().setAttribute(SESSION_UPD_DEL_RE_SEARCH_KANKOU, formMap);

		return map.findForward(ACTION_FOWARD_SUCCESS);
	}
}
