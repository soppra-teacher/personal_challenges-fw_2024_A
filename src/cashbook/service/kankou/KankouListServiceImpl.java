package cashbook.service.kankou;

import static cashbook.util.Const.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cashbook.dao.common.CommonDao;
import cashbook.dao.kankou.KankouListDao;
import cashbook.dto.kankou.KankouListDto;
import cashbook.util.CommonUtil;
import cashbook.util.KankouListConst;

public class KankouListServiceImpl implements KankouListService {

	/** 共通Dao */
	private CommonDao commonDao;

	/** 観光Dao */
	private KankouListDao kankouListDao;

	/**
	 * 共通DAOのsetter
	 * @param commonDao
	 */
	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}

	/**
	 * 観光DAOのsetter
	 * @param kankouListDao
	 */
	public void setKankouListDao(KankouListDao kankouListDao) {
		this.kankouListDao = kankouListDao;
	}

	/**
	 * 検索・一覧画面初期表示メソッド
	 * @param map      アクションマッピング
	 * @param form     フォーム
	 * @param request  リクエスト
	 */
	public KankouListDto listInit(Map<String, Object> formMap, HttpServletRequest request) {

		KankouListDto result = new KankouListDto();
		// カテゴリコンボボックスの設定
		result.setCategory(commonDao.searchSelectboxCategory());
		// 地方区分コンボボックスの設定
		result.setTihou(commonDao.searchSelectboxTIhou());
		//都道府県名コンボボックスの設定
		result.setTodouhuken(commonDao.searchSelectboxTodouhuKen());

		List<Map<String, String>> list;
		Map<String, Object> serch = new HashMap<>();

		//再検索の処理
		if (ACTION_FOWARD_RESERCH.equals(request.getParameter(ACTION_FOWARD_OPERATION))) {
			
			//セッションに保存した再検索用のマップを取得する。
			 serch = CommonUtil.getSessionMap(request, ACTION_FOWARD_RESERCH);

			   if (serch == null) {
			        // 新しいマップを作成する
			        serch = new HashMap<>();
			        request.getSession().setAttribute(ACTION_FOWARD_RESERCH, serch);
			    }
			// 取得した値を保持  
			result.setKankouNm(CommonUtil.getStr(serch.get(KankouListConst.KEY_KANKOU_NM)));
			result.setUserId(CommonUtil.getStr(serch.get(KankouListConst.KEY_USER_ID)));
			result.setTihouKey(CommonUtil.getStr(serch.get(KankouListConst.KEY_TIHOU)));
			result.setTodouhukenKey(CommonUtil.getStr(serch.get(KankouListConst.KEY_TODOUHUKEN)));
			result.setCategoryKey(CommonUtil.getStr(serch.get(KankouListConst.KEY_CATEGORY_KEY)));

			//検索処理
			list = kankouListDao.searchKankou(serch);
			
		} else {
			//初期表示処理
			list = kankouListDao.searchKankou(serch);
		}

		//------------------------------------------
		// 一覧
		//------------------------------------------
		List<KankouListDto> KankouList = new ArrayList<KankouListDto>();
		// 検索処理
		Iterator<Map<String, String>> it = list.iterator();
		while (it.hasNext()) {
			Map<String, String> map = it.next();
			KankouListDto dto = new KankouListDto();
			dto.setKankouId(map.get("KANKOU_ID"));
			dto.setKankouNm(map.get("KANKOU_NM"));
			dto.setTihouNm(map.get("TIHOU_NM"));
			dto.setTodouhukenNm(map.get("KEN_NM"));
			dto.setCategoryNm(map.get("CATEGORY_NM"));
			dto.setHhyoukati(map.get("HYOUKATI"));
			dto.setUserId(map.get("USER_ID"));
			KankouList.add(dto);
		}
		result.setList(KankouList);
		return result;
	}

	/**
	 * 一覧画面検索メソッド
	 * @param formMap
	 * @return 検索結果
	 */
	public KankouListDto listSearch(Map<String, Object> formMap) {
		//------------------------------------------
		// ヘッダ
		//------------------------------------------
		KankouListDto result = new KankouListDto();
		// 入力項目を保持
		result.setKankouNm(CommonUtil.getStr(formMap.get(KankouListConst.KEY_KANKOU_NM)));
		result.setUserId(CommonUtil.getStr(formMap.get(KankouListConst.KEY_USER_ID)));
		result.setCategoryKey(CommonUtil.getStr(formMap.get(KankouListConst.KEY_CATEGORY_KEY)));
		result.setTihouKey(CommonUtil.getStr(formMap.get(KankouListConst.KEY_TIHOU)));
		result.setTodouhukenKey(CommonUtil.getStr(formMap.get(KankouListConst.KEY_TODOUHUKEN)));
		// カテゴリ名コンボボックスの設定
		result.setCategory(commonDao.searchSelectboxCategory());
		// 八地方コンボボックスの設定
		result.setTihou(commonDao.searchSelectboxTIhou());
		//都道府県名コンボボックスの設定
		result.setTodouhuken(commonDao.searchSelectboxTodouhuKen());

		//------------------------------------------
		// 一覧
		//------------------------------------------
		List<KankouListDto> KojinList = new ArrayList<KankouListDto>();
		// 検索処理
		List<Map<String, String>> list = kankouListDao.searchKankou(formMap);
		Iterator<Map<String, String>> it = list.iterator();
		while (it.hasNext()) {
			Map<String, String> map = it.next();
			KankouListDto dto = new KankouListDto();
			dto.setKankouId(map.get("KANKOU_ID"));
			dto.setKankouNm(map.get("KANKOU_NM"));
			dto.setTihouNm(map.get("TIHOU_NM"));
			dto.setTodouhukenNm(map.get("KEN_NM"));
			dto.setCategoryNm(map.get("CATEGORY_NM"));
			dto.setHhyoukati(map.get("HYOUKATI"));
			dto.setUserId(map.get("USER_ID"));
			KojinList.add(dto);
		}
		result.setList(KojinList);
		return result;
	}
}
