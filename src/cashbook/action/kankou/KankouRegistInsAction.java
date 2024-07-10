package cashbook.action.kankou;

import static cashbook.util.Const.*;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.TransactionRolledbackException;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import cashbook.action.common.BaseAction;
import cashbook.dto.common.LoginDto;
import cashbook.exception.CommonValidateException;
import cashbook.service.common.CommonServiceImpl;
import cashbook.service.kankou.KankouService;
import cashbook.util.CommonUtil;
import cashbook.util.KankouConst;

public class KankouRegistInsAction extends BaseAction{
	
	/** 観光地登録サービス */
	private KankouService kankouService;

	
	/**
	 * 観光地登録サービスを設定します。
	 * @param kankouService 観光地登録サービス
	 */
	public void setKankouService(KankouService kankouService) {
		this.kankouService = kankouService;
	}

	/**
	 * <p>
	 * 観光地登録画面
	 * <br>登録
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
		
		try {
			// 登録処理
			kankouService.registIns(formMap, loginDto, request);
		//ロールバック専用のTransactionRolledbackException
		}catch (TransactionRolledbackException e) {
			//トランザクションでエラーが発生した場合のエラーメッセージと処理内容
			throw new CommonValidateException(MSG_ERRORS_KANKOU_DATA_ID);
			
		}catch(Exception e){
			throw new CommonValidateException(MSG_ERRORS_KANKOU_DATA);
		}
		
		// 写真処理
		if (!CommonUtil.isNull((String) formMap.get(KankouConst.KEY_IMAGE_STRING))) {
			try {
				CommonServiceImpl commonImp = new CommonServiceImpl();
				commonImp.fileUpd(formMap, request);
			} catch (IOException e) {
					//写真の例外処理が発生した時
					throw new CommonValidateException(MSG_ERRORS_IMAGE_EXEPTION);
			}
		}

		// 登録成功メッセージをセッションに設定
		request.getSession().setAttribute(SESSION_REGIST_MESSAGE_KANKOU, MSG_SUCCESS_INSERT);

		// 検索条件をセッションに保持（再検索用）
		request.getSession().setAttribute(SESSION_REGIST_RE_SEARCH_KANKOU, formMap);

		return map.findForward(ACTION_FOWARD_SUCCESS);
	}
}
