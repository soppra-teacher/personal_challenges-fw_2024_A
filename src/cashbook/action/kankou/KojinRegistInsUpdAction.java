package cashbook.action.kankou;

import static cashbook.util.Const.*;

import java.io.FileOutputStream;
import java.util.Base64;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import cashbook.action.common.BaseAction;
import cashbook.dto.common.LoginDto;
import cashbook.service.kankou.KankouService;
import cashbook.util.CommonUtil;
import cashbook.util.Const;
import cashbook.util.KankouConst;

/**
 * 観光地更新削除・画面・更新クションクラス
 * @author soppra
 */
public class KojinRegistInsUpdAction extends BaseAction {

	/** 観光テーブルサービス */
	private KankouService kojinService;

	/**
	 * テーブルサービスを設定します。
	 * @param kankouService 観光サービス
	 */
	public void setKojinService(KankouService kojinService) {
		this.kojinService = kojinService;
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
		System.out.println(formMap);
		// フォームのbase64Imageフィールドからデータを取得
		String base64Image = (String) formMap.get(KankouConst.KEY_Image_String);
		// Base64データURIスキーム部分を削除
		String[] parts = base64Image.split(",");
		String imageData = parts[1];

		// Base64デコード
		byte[] imageBytes = Base64.getDecoder().decode(imageData);
		// ファイル名を設定
		String fileName = "1.png";

        // デコードされたバイト配列をファイルとして保存
		String filePath = request.getServletContext().getRealPath("/img/") + fileName;
		try (FileOutputStream fos = new FileOutputStream(filePath)) {
		    fos.write(imageBytes);
		}
		
		System.out.println();
		
		//更新処理
		if(Const.ACTION_FOWARD_INSERT.equals(request.getParameter(ACTION_FOWARD_OPERATION))) {
			//登録処理
			//kojinService.hyokaIns();
			
		}
		if(Const.ACTION_FOWARD_UPDATE.equals(request.getParameter(ACTION_FOWARD_OPERATION))) {
			// 更新処理
		}
		if(Const.ACTION_FOWARD_DELETE.equals(request.getParameter(ACTION_FOWARD_OPERATION))) {
			// 削除処理
		}

		// 更新・削除処理
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
