package cashbook.service.kankou;

import static cashbook.util.Const.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import cashbook.dao.common.CommonDao;
import cashbook.dao.kankou.KankouDao;
import cashbook.dto.common.LoginDto;
import cashbook.dto.kankou.KankouListDto;
import cashbook.dto.kankou.KankouRegistDto;
import cashbook.dto.kankou.KankouUpdDelDto;
import cashbook.exception.CommonValidateException;
import cashbook.util.CommonUtil;
import cashbook.util.KankouConst;

public class KankouServiceImpl implements KankouService {

	/** 共通Dao */
	private CommonDao commonDao;

	/** 観光Dao */
	private KankouDao kankouDao;

	/** 観光Dao */
	private TransactionTemplate transactionTemplate;

	/**
	 * 共通DAOのsetter
	 * @param commonDao
	 */
	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}

	/**
	 * 観光DAOのsetter
	 * @param kankouDao
	 */
	public void setKankouDao(KankouDao kankouDao) {
		this.kankouDao = kankouDao;
	}

	/**
	 * TransactionTemplateのsetter
	 * @param 
	 */
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	/**
	 * 観光地登録画面初期表示メソッド
	 * @param  formMap 
	 * @return result
	 */
	public KankouRegistDto registInit(Map<String, Object> formMap) {

		KankouRegistDto result = new KankouRegistDto();

		// 都道府県名コンボボックスの設定
		result.setTodouhuken(commonDao.searchSelectboxTodouhuKen());

		// カテゴリコンボボックスの設定
		result.setCategory(commonDao.searchSelectboxCategory());

		return result;
	}

	/**
	 * 観光地登録メソッド
	 * @param
	 * @throws Exception
	 */
	public void registIns(Map<String, Object> formMap, LoginDto loginDto, HttpServletRequest request) throws Exception {

		// 観光地・評価値登録

		// 観光地情報存在チェック
		if (!kankouDao.checkOverlapKankou(formMap, loginDto)) {
			throw new CommonValidateException(MSG_ERRORS_KANKOU_DATA);
		}

		transactionTemplate.execute(new TransactionCallbackWithoutResult() {

			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {

				//テーブルロック
				kankouDao.lockKankou();
				
				//formMapに、観光IDの最大値をセット
				formMap.put(KankouConst.KEY_ID, kankouDao.getmaxKankou());
				
				// 観光地登録処理
				kankouDao.registKankou(formMap, loginDto);

				// 評価値登録処理
				kankouDao.registHyoka(formMap, loginDto);
				
			}
		});
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
		// 続柄区分コンボボックスの設定
		result.setTihou(commonDao.searchSelectboxTIhou());
		//都道府県名コンボボックスの設定
		result.setTodouhuken(commonDao.searchSelectboxTodouhuKen());

		List<Map<String, String>> list;

		//再検索の処理
		if (ACTION_FOWARD_RESERCH.equals(request.getParameter(ACTION_FOWARD_OPERATION))) {

			//セッションに保存した再検索用のマップを取得する。
			Map<String, Object> reserch = CommonUtil.getSessionMap(request, ACTION_FOWARD_RESERCH);

			if (reserch == null) {
				// 新しいマップを作成する
				reserch = new HashMap<>();
				request.getSession().setAttribute(ACTION_FOWARD_RESERCH, reserch);
			}

			// 取得した値を保持  
			result.setKankouNm(CommonUtil.getStr(reserch.get(KankouConst.KEY_KANKOU_NM)));
			result.setUserId(CommonUtil.getStr(reserch.get(KankouConst.KEY_USER_ID)));
			result.setTihouKey(CommonUtil.getStr(reserch.get(KankouConst.KEY_TIHOU)));
			result.setTodouhukenKey(CommonUtil.getStr(reserch.get(KankouConst.KEY_TODOUHUKEN)));
			result.setCategoryKey(CommonUtil.getStr(reserch.get(KankouConst.KEY_CATEGORY_KEY)));

			//検索処理
			list = kankouDao.searchKankou(reserch);

		} else {
			//初期表示処理
			formMap.put(KankouConst.KEY_USER_ID, "");
			list = kankouDao.searchKankou(formMap);
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
		result.setKankouNm(CommonUtil.getStr(formMap.get(KankouConst.KEY_KANKOU_NM)));
		result.setUserId(CommonUtil.getStr(formMap.get(KankouConst.KEY_USER_ID)));
		result.setCategoryKey(CommonUtil.getStr(formMap.get(KankouConst.KEY_CATEGORY_KEY)));
		result.setTihouKey(CommonUtil.getStr(formMap.get(KankouConst.KEY_TIHOU)));
		result.setTodouhukenKey(CommonUtil.getStr(formMap.get(KankouConst.KEY_TODOUHUKEN)));
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
		List<Map<String, String>> list = kankouDao.searchKankou(formMap);
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

	/**
	 * 更新削除画面初期表示メソッド
	 * @param formMap
	 * @param loginDto ログイン情報Dto
	 * @return 検索結果
	 */
	public KankouUpdDelDto updDelInit(Map<String, Object> formMap, LoginDto loginDto) {
		KankouUpdDelDto result = new KankouUpdDelDto();

		Map<String, String> map = kankouDao.findKankou(formMap, loginDto);
		result.setImagePath(map.get("FILE_NM"));
		result.setKankouNm(map.get("KANKOU_NM"));
		result.setCategoryNm(map.get("CATEGORY_NM"));
		result.setTodouhukenNm(map.get("KEN_NM"));
		result.setTihouNm(map.get("TIHOU_NM"));
		result.setSetsumei(map.get("SETSUMEI"));
		result.setReview(map.get("REVIEW"));
		result.setUserId(map.get("USER_ID"));
		if ((map.get("HYOUKATI") != null)) {
			result.setHyokaJudge("1");
			result.setHyoka(map.get("HYOUKATI"));
		} else {
			result.setHyokaJudge("0");
			result.setHyoka("3");
		}
		return result;
	}

	/**
	 * 評価値登録メソッド
	 * @param formMap
	 * @param loginDto ログイン情報
	 * @throws Exception
	 */
	public void hyokaIns(Map<String, Object> formMap, LoginDto loginDto, String kankouId) throws Exception {
		kankouDao.insHyoka(formMap, loginDto,kankouId);
	}

	/**
	 * 更新メソッド
	 * @param formMap
	 * @param loginDto ログイン情報
	 * @param request リクエスト
	 * @param 観光地Id
	 * @throws Exception
	 */
	public void update(Map<String, Object> formMap, LoginDto loginDto, HttpServletRequest request, String kankouId, String hyoka) throws Exception {
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				//元々登録されている評価値と更新が押されて場合の評価値を比較
				if (!(hyoka.equals(formMap.get(KankouConst.KEY_HYOKA)))) {
					kankouDao.updHyoka(formMap, loginDto, kankouId);
				}
				//投稿者がどうかを判断する。
				if (formMap.get(KankouConst.KEY_USER_ID).equals(loginDto.getUserId())) {
					kankouDao.updateKankou(formMap, loginDto);
				}
			}
		});
	}

	/**
	 * 更新メソッド
	 * @param formMap
	 * @throws Exception
	 */
	public void delete(Map<String, Object> formMap, HttpServletRequest request) throws Exception {
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {

			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				//評価値テーブルの削除
				kankouDao.delHyoka(formMap);
				//観光地テーブルの削除
				kankouDao.delKankou(formMap);
				
			}
		});
	}
}
