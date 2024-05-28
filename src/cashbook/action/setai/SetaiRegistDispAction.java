package cashbook.action.setai;

import static cashbook.util.Const.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

/**
 * 世帯マスタ登録画面ディスパッチアクションクラス
 * @author soppra
 */
public class SetaiRegistDispAction extends DispatchAction {
	/**
	 * 登録
	 */
	public ActionForward insert(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return mapping.findForward(ACTION_FOWARD_INSERT);
	}

	/**
	 * 更新
	 */
	public ActionForward update(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return mapping.findForward(ACTION_FOWARD_UPDATE);
	}

	/**
	 * 戻る（メニュー）
	 */
	public ActionForward backMenu(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return mapping.findForward(ACTION_FOWARD_BACK_MENU);
	}

	/**
	 * 戻る（一覧）
	 */
	public ActionForward backList(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return mapping.findForward(ACTION_FOWARD_BACK_LIST);
	}
}
