package cashbook.service.kankou;

import static cashbook.util.Const.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cashbook.dao.common.CommonDao;
import cashbook.dao.kankou.KankouDao;
import cashbook.dao.setai.SetaiDao;
import cashbook.dto.common.LoginDto;
import cashbook.dto.kankou.KankouListDto;
import cashbook.dto.kankou.KankouRegistDto;
import cashbook.exception.CommonValidateException;
import cashbook.util.CommonUtil;
import cashbook.util.Const;
import cashbook.util.KankouConst;

/**
 *観光テーブルサービス
 * @author soppra
 */
public class KankouServiceImpl implements KankouService {

	/** 世帯マスタDao */
	private SetaiDao setaiDao;

	/** 個人マスタDao */
	private KankouDao kojinDao;

	/** 共通Dao */
	private CommonDao commonDao;

	/**
	 * 検索・一覧画面初期表示メソッド
	 */
	public KankouListDto listInit(Map<String, Object> formMap) {
		KankouListDto result = new KankouListDto();
		// カテゴリコンボボックスの設定
		result.setCategory(commonDao.getKategory());
		// 続柄区分コンボボックスの設定
		result.setTihou(commonDao.getTIhou());
		//都道府県名コンボボックスの設定
		result.setTodouhuken(commonDao.getTodouhuken());

		//------------------------------------------
		// 一覧
		//------------------------------------------
		List<KankouListDto> KojinList = new ArrayList<KankouListDto>();
		// 検索処理
		List<Map<String, String>> list = kojinDao.searchKankou(formMap);
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
	 * 一覧画面検索メソッド
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
		result.setCategory(commonDao.getKategory());
		// 続柄区分コンボボックスの設定
		result.setTihou(commonDao.getTIhou());
		//都道府県名コンボボックスの設定
		result.setTodouhuken(commonDao.getTodouhuken());

		//------------------------------------------
		// 一覧
		//------------------------------------------
		List<KankouListDto> KojinList = new ArrayList<KankouListDto>();
		// 検索処理
		List<Map<String, String>> list = kojinDao.searchKankou(formMap);
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
	 * 一覧画面削除メソッド
	 */
	public void listDelete(Map<String, Object> formMap, LoginDto loginDto) {
		List<String> list = CommonUtil.convFormMapToList(formMap);
		for (String checkDel : list) {
			kojinDao.deleteKojin(checkDel, loginDto);
		}
	}

	/**
	 * 登録画面初期表示メソッド
	 */
	public KankouRegistDto registInit(Map<String, Object> formMap, LoginDto loginDto) {
		KankouRegistDto result = new KankouRegistDto();

		// 更新モードの場合は、対象の個人マスタを取得する

		Map<String, String> map = kojinDao.findKankou(formMap, loginDto);
		result.setImagePath("FILE_NM");
		result.setKankouNm(map.get("KANKOU_NM"));
		result.setCategoryNm(map.get("CATEGORY_NM"));
		result.setTodouhukenNm(map.get("KEN_NM"));
		result.setTihouNm(map.get("TIHOU_NM"));
		result.setSetsumei(map.get("SETSUMEI"));
		result.setUserId(map.get("USER_ID"));
		if ((map.get("HYOUKATI") != null)) {
			result.setHyokaJudge("1");
			result.setHyoka(map.get("HYOUKATI"));
		} else {
			result.setHyokaJudge("0");
			result.setHyoka("3");
		}
		System.out.println("map" + map);
		return result;
	}

	/**
	 * 登録画面登録・更新メソッド
	 * @throws CommonValidateException
	 */
	public void registInsUpd(Map<String, Object> formMap, LoginDto loginDto) throws Exception {

		// 世帯主フラグ="1" 且つ 世帯主チェック
		if (SETAINUSHI_ON.equals(formMap.get(KankouConst.KEY_SETAINUSI_FLG_VALUE))
				&& formMap.get(KankouConst.KEY_SETAINUSI_FLG_VALUE) != null &&
				kojinDao.checkSetainushiFlg(formMap)) {
			throw new CommonValidateException(MSG_KOJIN_CONSIS_1);
		}

		// 性別、続柄の整合性チェック
		if (check2(formMap)) {
			throw new CommonValidateException(MSG_KOJIN_CONSIS_2);
		}

		// 登録の場合
		if (CommonUtil.isNull(CommonUtil.getStr(formMap.get(Const.ITEM_REVISION)))) {
			// 存在チェック
			if (!kojinDao.checkOverlapKojin(formMap)) {
				throw new CommonValidateException(MSG_ERRORS_PRIMARY_KEY);
			}
			// 登録処理
			kojinDao.registKojin(formMap, loginDto);
			// 更新の場合
		} else {
			// 排他処理
			if (!kojinDao.lockKojin(formMap)) {
				throw new CommonValidateException(MSG_ERRORS_DATA_LOCK);
			}
			// 更新処理
			kojinDao.updateKojin(formMap, loginDto);
		}
	}

	/**
	 * 登録画面登録・更新メソッド
	 * @throws CommonValidateException
	 */
	public void updInsDel(Map<String, Object> formMap, LoginDto loginDto, HttpServletRequest request) throws Exception {

	}
	
	/**
	 * 更新・削除画面評価値登録メソッド
	 * @throws CommonValidateException
	 */
	public void hyokaIns(Map<String, Object> formMap, LoginDto loginDto) throws Exception {
		kojinDao.insHyoka(formMap, loginDto);
	}

	/**
	 * 登録画面登録・更新メソッド
	 * @param  formMap 画面入力項目
	 * @return True:NG, False:OK
	 */
	private boolean check2(Map<String, Object> formMap) {
		boolean bRet = false;
		String category = CommonUtil.getStr(formMap.get(KankouConst.KEY_SEIBETSU_KBN));
		String zokugaraKbn = CommonUtil.getStr(formMap.get(KankouConst.KEY_ZOKUGARA));

		// 性別・続柄の相関チェック
		// 続柄がブランクの場合は、チェックを行わない
		if (!CommonUtil.isNull(zokugaraKbn)) {
			if (SEIBETSU_KBN_MAN.equals(category)) {
				if (!(ZOKUGARA_FATHER.equals(zokugaraKbn)
						|| ZOKUGARA_ELDEST_SON.equals(zokugaraKbn)
						|| ZOKUGARA_SECOND_SON.equals(zokugaraKbn)
						|| ZOKUGARA_THIRD_SON.equals(zokugaraKbn))) {
					bRet = true;
				}
			} else if (SEIBETSU_KBN_WOMAN.equals(category)) {
				if (!(ZOKUGARA_MOTHER.equals(zokugaraKbn)
						|| ZOKUGARA_ELDEST_DAUGHTER.equals(zokugaraKbn)
						|| ZOKUGARA_SECOND_DAUGHTER.equals(zokugaraKbn)
						|| ZOKUGARA_THIRD_DAUGHTER.equals(zokugaraKbn))) {
					bRet = true;
				}
			}
		}

		return bRet;
	}

	/**
	 * DAOのsetter
	 * @param kojinDao
	 */
	public void setKojinDao(KankouDao kojinDao) {
		this.kojinDao = kojinDao;
	}

	/**
	 * DAOのsetter
	 * @param setaiDao
	 */
	public void setSetaiDao(SetaiDao setaiDao) {
		this.setaiDao = setaiDao;
	}

	/**
	 * DAOのsetter
	 * @param commonDao
	 */
	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}

}