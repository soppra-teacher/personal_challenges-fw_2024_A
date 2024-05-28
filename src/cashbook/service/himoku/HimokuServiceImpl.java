package cashbook.service.himoku;

import static cashbook.util.Const.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cashbook.dao.common.CommonDao;
import cashbook.dao.himoku.HimokuDao;
import cashbook.dto.common.LoginDto;
import cashbook.dto.himoku.HimokuListDto;
import cashbook.dto.himoku.HimokuRegistDto;
import cashbook.exception.CommonValidateException;
import cashbook.util.CommonUtil;
import cashbook.util.Const;
import cashbook.util.HimokuConst;

/**
 * 費目マスタサービス
 * @author soppra
 */
public class HimokuServiceImpl implements HimokuService {

	/** 費目マスタDao */
	private HimokuDao himokuDao;
	/** 共通Dao */
	private CommonDao commonDao;

	/**
	 * 費目マスタDaoを設定します。
	 * @param himokuDao 費目マスタDao
	 */
	public void setHimokuDao(HimokuDao himokuDao) {
		this.himokuDao = himokuDao;
	}

	/**
	 * DAOのsetter
	 * @param commonDao
	 */
	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}

	/**
	 * <p><b>
	 * 費目マスタメンテ画面
	 * <br>初期表示処理
	 * </b></p>
	 * @return HimokuListDto 費目マスタメンテDTO
	 */
	public HimokuListDto listInit() {
		HimokuListDto result = new HimokuListDto();
		// 費目区分コンボボックスの設定
		result.setHimokuKbn(commonDao.getCode(CD_BUNRUI_001));
		return result;
	}

	/**
	 * <p><b>
	 * 費目マスタメンテ画面
	 * <br>検索処理
	 * </b></p>
	 * @param  formMap       画面項目
	 * @return HimokuListDto 費目マスタメンテDTO
	 */
	public HimokuListDto listSearch(Map<String, Object> formMap) {

		//------------------------------------------
		// ヘッダ
		//------------------------------------------
		HimokuListDto result = new HimokuListDto();

		// 入力項目を保持
		result.setHimokuNm(CommonUtil.getStr(formMap.get(HimokuConst.KEY_HIMOKU_NM)));
		result.setHimokuKbnKey(CommonUtil.getStr(formMap.get(HimokuConst.KEY_HIMOKU_KBN_KEY)));
		// 費目区分コンボボックスの設定
		result.setHimokuKbn(commonDao.getCode(CD_BUNRUI_001));

		//------------------------------------------
		// 一覧
		//------------------------------------------
		List<HimokuRegistDto> himokuList = new ArrayList<HimokuRegistDto>();

		// 検索処理
		List<Map<String, String>> list = himokuDao.searchHimokuList(formMap);
		Iterator<Map<String, String>> it = list.iterator();

		while (it.hasNext()) {
			Map<String, String> map = it.next();
			HimokuRegistDto dto = new HimokuRegistDto();
			dto.setHimokuCd(map.get("HIMOKU_CD"));
			dto.setHimokuNm(map.get("HIMOKU_NM"));
			dto.setHimokuKbnNm(commonDao.getCode(CD_BUNRUI_001).get(map.get("HIMOKU_KBN")));
			himokuList.add(dto);
		}
		result.setList(himokuList);

		return result;
	}

	/**
	 * <p><b>
	 * 費目マスタメンテ画面
	 * <br>削除処理
	 * </b></p>
	 * @param formMap  画面項目
	 * @param loginDto ログイン情報DTO
	 */
	public void listDelete(Map<String, Object> formMap, LoginDto loginDto) {

		List<String> list = CommonUtil.convFormMapToList(formMap);
		for (String checkDel : list) {
			himokuDao.deleteHimoku(checkDel, loginDto);
		}
	}

	/**
	 * <p><b>
	 * 費目マスタ登録画面
	 * <br>検索処理
	 * </b></p>
	 * @param  formMap       画面項目
	 * @return HimokuListDto 費目マスタメンテDTO
	 */
	public HimokuRegistDto registInit(Map<String, Object> formMap) {

		HimokuRegistDto result = new HimokuRegistDto();
		// 費目区分コンボボックスの設定
		result.setHimokuKbn(commonDao.getCode(CD_BUNRUI_001));

		// 更新モードの場合は、対象の費目マスタを取得する
		if (formMap != null && !CommonUtil.isNull(CommonUtil.getStr(formMap.get(HimokuConst.KEY_HIMOKU_CD)))) {
			Map<String, String> map = himokuDao.findHimoku(formMap);
			if (map != null) {
				result.setHimokuCd(map.get("HIMOKU_CD"));
				result.setHimokuNm(map.get("HIMOKU_NM"));
				result.setHimokuKbnKey(map.get("HIMOKU_KBN"));
				result.setRevision(map.get("REVISION"));
			}
		}

		return result;
	}

	/**
	 * <p><b>
	 * 費目マスタ登録画面
	 * <br>登録・更新処理
	 * </b></p>
	 * @param formMap  画面項目
	 * @param loginDto ログイン情報DTO
	 * @throws CommonValidateException
	 */
	public void registInsUpd(Map<String, Object> formMap, LoginDto loginDto) throws CommonValidateException {

		if (CommonUtil.isNull(CommonUtil.getStr(formMap.get(Const.ITEM_REVISION)))) {
			// 登録の場合
			// 存在チェック
			if (!himokuDao.checkOverlapHimoku(formMap)) {
				// キー重複エラー
				throw new CommonValidateException(Const.MSG_ERRORS_PRIMARY_KEY);
			}
			// 登録処理
			himokuDao.registHimoku(formMap, loginDto);

		} else {
			// 更新の場合
			// 排他処理
			if (!himokuDao.lockHimoku(formMap)) {
				// 排他エラー
				throw new CommonValidateException(Const.MSG_ERRORS_DATA_LOCK);
			}
			// 更新処理
			himokuDao.updateHimoku(formMap, loginDto);

		}
	}
}