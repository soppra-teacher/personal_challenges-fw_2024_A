package cashbook.service.kankou;

import static cashbook.util.Const.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cashbook.dao.common.CommonDao;
import cashbook.dao.kankou.KankouDao;
import cashbook.dao.kojin.KojinDao;
import cashbook.dao.setai.SetaiDao;
import cashbook.dto.common.LoginDto;
import cashbook.dto.kankou.KankouRegistDto;
import cashbook.dto.kojin.KojinListDto;
import cashbook.dto.kojin.KojinRegistDto;
import cashbook.exception.CommonValidateException;
import cashbook.util.CommonUtil;
import cashbook.util.Const;
import cashbook.util.KojinConst;

public class KankouServiceImpl implements KankouService{

	/** 世帯マスタDao */
	private SetaiDao setaiDao;

	/** 個人マスタDao */
	private KojinDao kojinDao;

	/** 共通Dao */
	private CommonDao commonDao;
	
	/** 観光Dao */
	private KankouDao kankouDao;

	/**
	 * 一覧画面初期表示メソッド
	 */
	public KojinListDto listInit() {
		KojinListDto result = new KojinListDto();
		// 性別コンボボックスの設定
		result.setSeibetsuKbn(commonDao.getCode(CD_BUNRUI_002));
		// 続柄区分コンボボックスの設定
		result.setzokugara(commonDao.getCode(CD_BUNRUI_003));
		return result;
	}

	/**
	 * 一覧画面検索メソッド
	 */
	public KojinListDto listSearch(Map<String, Object> formMap) {
		//------------------------------------------
		// ヘッダ
		//------------------------------------------
		KojinListDto result = new KojinListDto();
		// 入力項目を保持
		result.setKojinNm(CommonUtil.getStr(formMap.get(KojinConst.KEY_KOJIN_NM)));
		result.setKojinNmkana(CommonUtil.getStr(formMap.get(KojinConst.KEY_KOJIN_NM_KANA)));
		result.setSeibetsuKbnKey(CommonUtil.getStr(formMap.get(KojinConst.KEY_SEIBETSU_KBN_KEY)));
		result.setZokugaraKey(CommonUtil.getStr(formMap.get(KojinConst.KEY_ZOKUGARA)));
		result.setSetaiNusiFlg(CommonUtil.getStr(formMap.get(KojinConst.KEY_SETAINUSI_FLG)));
		// 性別区分コンボボックスの設定
		result.setSeibetsuKbn(commonDao.getCode(CD_BUNRUI_002));
		// 続柄区分コンボボックスの設定
		result.setzokugara(commonDao.getCode(CD_BUNRUI_003));

		//------------------------------------------
		// 一覧
		//------------------------------------------
		List<KojinRegistDto> KojinList = new ArrayList<KojinRegistDto>();
		// 検索処理
		List<Map<String, String>> list = kojinDao.searchKojin(formMap);
		Iterator<Map<String, String>> it = list.iterator();
		while (it.hasNext()) {
			Map<String, String> map = it.next();
			KojinRegistDto dto = new KojinRegistDto();
			dto.setKojinId(map.get("KOJIN_ID"));
			dto.setSetaiId(map.get("SETAI_ID"));
			dto.setKojinNm(map.get("KOJIN_NM"));
			dto.setKojinNmkana(map.get("KOJIN_NM_KANA"));
			dto.setSeibetsuNm(commonDao.getCode(CD_BUNRUI_002).get(map.get("SEIBETSU_KBN")));
			dto.setZokugaraNm(commonDao.getCode(CD_BUNRUI_003).get(map.get("ZOKUGARA")));
			dto.setSetaiNusiFlg(map.get("SETAINUSHI_FLG"));
			if (SETAINUSHI_ON.equals(map.get("SETAINUSHI_FLG"))) {
				dto.setSetaiNusiNm(commonDao.getCodeName(CD_BUNRUI_004, CD_004_1));
			}
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
	public KankouRegistDto registInit(Map<String, Object> formMap) {

		KankouRegistDto result = new KankouRegistDto();

		// 都道府県名コンボボックスの設定
		result.setTodouhuken(commonDao.searchSelectboxTodouhuKen());
		
		// カテゴリコンボボックスの設定
		result.setCategory(commonDao.searchSelectboxCategory());
		
		return result;
	}

	/**
	 * 登録画面登録・更新メソッド
	 * @throws CommonValidateException
	 */
	public void registIns(Map<String, Object> formMap, LoginDto loginDto) throws Exception {

		// 登録の場合
		if (CommonUtil.isNull(CommonUtil.getStr(formMap.get(Const.ITEM_REVISION)))) {
			// 存在チェック
			if (kankouDao.checkOverlapKankou(formMap)) {
				throw new CommonValidateException(MSG_ERRORS_PRIMARY_KEY);
			}
			// 登録処理
			kankouDao.registKankou(formMap, loginDto);
			
			// 評価値
			kankouDao.registHyoka(formMap, loginDto);
		} 
	}

	/**
	 * 登録画面登録・更新メソッド
	 * @param  formMap 画面入力項目
	 * @return True:NG, False:OK
	 */
	private boolean check2(Map<String, Object> formMap) {
		boolean bRet = false;
		String seibetsuKbn = CommonUtil.getStr(formMap.get(KojinConst.KEY_SEIBETSU_KBN));
		String zokugaraKbn = CommonUtil.getStr(formMap.get(KojinConst.KEY_ZOKUGARA));

		// 性別・続柄の相関チェック
		// 続柄がブランクの場合は、チェックを行わない
		if (!CommonUtil.isNull(zokugaraKbn)) {
			if (SEIBETSU_KBN_MAN.equals(seibetsuKbn)) {
				if (!(ZOKUGARA_FATHER.equals(zokugaraKbn)
						|| ZOKUGARA_ELDEST_SON.equals(zokugaraKbn)
						|| ZOKUGARA_SECOND_SON.equals(zokugaraKbn)
						|| ZOKUGARA_THIRD_SON.equals(zokugaraKbn))) {
					bRet = true;
				}
			} else if (SEIBETSU_KBN_WOMAN.equals(seibetsuKbn)) {
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
	public void setKojinDao(KojinDao kojinDao) {
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
	
	/**
	 * DAOのsetter
	 * @param kankouDao
	 */
	public void setKankouDao(KankouDao kankouDao) {
		this.kankouDao = kankouDao;
	}

}
