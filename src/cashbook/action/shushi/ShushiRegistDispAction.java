package cashbook.action.shushi;

import static cashbook.util.Const.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

/**
 * 収支登録画面ディスパッチアクションクラス
 * @author soppra
 */
public class ShushiRegistDispAction extends DispatchAction {
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
	 * 戻る（一覧(個別)）
	 */
	public ActionForward backListKobetsu(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return mapping.findForward(ACTION_FOWARD_BACK_LIST_KOBETSU);
	}

	/**
	 * 戻る（一覧(世帯)）
	 */
	public ActionForward backListSetai(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return mapping.findForward(ACTION_FOWARD_BACK_LIST_SETAI);
	}
}
