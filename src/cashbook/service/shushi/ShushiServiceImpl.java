package cashbook.service.shushi;

import static cashbook.util.Const.CD_BUNRUI_001;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cashbook.dao.common.CommonDao;
import cashbook.dao.shushi.ShushiDao;
import cashbook.dto.common.LoginDto;
import cashbook.dto.shushi.ShushiListDto;
import cashbook.dto.shushi.ShushiRegistDto;
import cashbook.exception.CommonValidateException;
import cashbook.util.CommonUtil;
import cashbook.util.Const;

/**
 * 収支サービス
 * @author soppra
 */
public class ShushiServiceImpl implements ShushiService{
	private CommonDao commonDao;
	private ShushiDao shushiDao;

	/**
	 * 収支一覧（個別）画面初期表示メソッド
	 */
	public ShushiListDto shushiListKobetsuInit(){

		// 収支一覧（個別）情報オブジェクト生成
		ShushiListDto result = new ShushiListDto();

		// 年の設定
		result.setYearMap(CommonUtil.getYearMap());
		result.setYearKey(commonDao.getYyyy());

		// 月の設定
		result.setMonthMap(CommonUtil.getMonthMap());
		result.setMonthKey(commonDao.getMm());

		// 費目名コンボボックスの設定
		result.setHimokuNm(shushiDao.getHimokuNmMap());

		return result;
	}

	/**
	 * 収支一覧（個別）画面検索メソッド
	 */
	public ShushiListDto shushiListKobetsuSearch(Map<String, Object> formMap, LoginDto loginDto) {

		// 収支一覧（個別）情報オブジェクト生成
		ShushiListDto result = new ShushiListDto();

		// 入力項目を保持
		result.setYearMap(CommonUtil.getYearMap());							// 年（Ｍａｐ）
		result.setYearKey(CommonUtil.getStr(formMap.get("yearKey")));					// 年（キー）
		result.setMonthMap(CommonUtil.getMonthMap());						// 月（Ｍａｐ）
		result.setMonthKey(CommonUtil.getStr(formMap.get("monthKey")));				// 月（キー）
		result.setHimokuNmKey(CommonUtil.getStr(formMap.get("himokuNmKey")));			// 費目名 （キー）
		result.setHimokuNm(shushiDao.getHimokuNmMap());						// 費目名コンボボックスの設定

		// 収支一覧（個別）明細情報オブジェクト生成
		List<ShushiRegistDto> shushiList = new ArrayList<ShushiRegistDto>();

		// 収支一覧情報検索処理
		List<Map<String, String>> list = shushiDao.listSearch(formMap ,loginDto);

		// 収支一覧情報検索検索結果情報を格納
		Iterator<Map<String, String>> it = list.iterator();

		// ワーク変数宣言
		int zandaka = 0;			// 残高
		int shunyuTotal = 0;		// 収入合計
		int shishutsuTotal = 0;		// 支出合計

		// 検索結果の件数、繰り返し
		while (it.hasNext()) {

			// 検索結果をマップに格納
			Map<String, String> map = it.next();

			// 収支明細情報格納オブジェクト初期化
			ShushiRegistDto dto = new ShushiRegistDto();

			dto.setShushiKey(map.get("SHUSHI_KEY") + "," + Const.DISP_ST001);		// 収支（キー）の設定
			dto.setYmd(map.get("YMD"));									// 日付の設定
			dto.setHimokuNmSei(map.get("HIMOKU_NM"));					// 費目名の設定

			// 費目区分が1(収入)の場合
			if (map.get("HIMOKU_KBN").equals("1")) {
				dto.setShunyu(map.get("KINGAKU"));				// 収入の設定

				// 残高 = 残高 + レコードの金額を設定
				dto.setZandaka(CommonUtil.getStr(
						zandaka + Integer.parseInt(map.get("KINGAKU"))));

				// 残高情報を保持
				zandaka = zandaka + Integer.parseInt(map.get("KINGAKU"));

				// 収入合計 = 収入合計 + レコードの金額を設定
				shunyuTotal = shunyuTotal + Integer.parseInt(map.get("KINGAKU"));
			} else {
				dto.setShishutsu(map.get("KINGAKU"));			// 支出

				// 残高 = 残高 + 本レコードの金額を設定
				dto.setZandaka(CommonUtil.getStr(
						zandaka - Integer.parseInt(map.get("KINGAKU"))));

				// 残高情報を保持
				zandaka = zandaka - Integer.parseInt(map.get("KINGAKU"));

				// 支出合計 = 支出合計 + レコードの金額を設定
				shishutsuTotal = shishutsuTotal + Integer.parseInt(map.get("KINGAKU"));
			}
			// 収支一覧（個別）明細情報オブジェクトに格納
			shushiList.add(dto);
		}

		// 収入合計の算術結果を格納
		result.setShunyuTotal(CommonUtil.getStr(shunyuTotal));

		// 支出合計の算術結果を格納
		result.setShishutsuTotal(CommonUtil.getStr(shishutsuTotal));

		// 残高合計の算術結果を格納
		result.setZandakaTotal(CommonUtil.getStr(zandaka));

		// 収支一覧（個別）明細情報を収支一覧（個別）情報オブジェクトに格納
		result.setList(shushiList);

		return result;
	}

	/**
	 * 収支一覧（個別）画面削除メソッド
	 */
	public void shushiListKobetsuDelete(Map<String, Object> formMap){

		// 削除情報を格納
		String[] checkDel = (String[])formMap.get("checkDel");

		// 収支明細件数取得結果
		int shushiDtlCnt = 0;

		// 削除情報の件数分、繰り返し
		for (int i = 0; i < checkDel.length; i++) {

			// 削除情報をカンマ区切りで配列収支キー（0番目:収支ID、1番目:収支明細ID）に格納
			String[] shushiKey = checkDel[i].split(",");

			if (i == 0) {
				// 収支明細件数取得
				shushiDtlCnt = shushiDao.getShushiDtlCnt(shushiKey[0]);
			}

			// 収支明細情報を削除
			shushiDao.listDtlDelete(shushiKey[0], shushiKey[1]);

			// 収支明細件数と削除チェックされた件数が同じ場合
			if (shushiDtlCnt == checkDel.length && i+1 == checkDel.length) {
				// 収支情報を削除
				shushiDao.listDelete(shushiKey[0]);
			}
		}
	}

	/**
	 * 収支一覧（世帯）画面初期表示メソッド
	 */
	public ShushiListDto shushiListSetaiInit(LoginDto loginDto){

		// 収支一覧（世帯）情報オブジェクト生成
		ShushiListDto result = new ShushiListDto();

		// 年の設定
		result.setYearMap(CommonUtil.getYearMap());
		result.setYearKey(commonDao.getYyyy());

		// 月の設定
		result.setMonthMap(CommonUtil.getMonthMap());
		result.setMonthKey(commonDao.getMm());

		// 費目名コンボボックスの設定
		result.setHimokuNm(shushiDao.getHimokuNmMap());

		// 個人名コンボボックスの設定
		result.setKojinNm(shushiDao.getKojinNmMap(loginDto));

		return result;
	}

	/**
	 * 収支一覧（世帯）画面検索メソッド
	 */
	public ShushiListDto shushiListSetaiSearch(Map<String, Object> formMap, LoginDto loginDto) {

		// 収支一覧（世帯）情報オブジェクト生成
		ShushiListDto result = new ShushiListDto();

		// 入力項目を保持
		result.setYearMap(CommonUtil.getYearMap());							// 年（Ｍａｐ）
		result.setYearKey(CommonUtil.getStr(formMap.get("yearKey")));					// 年（キー）
		result.setMonthMap(CommonUtil.getMonthMap());						// 月（Ｍａｐ）
		result.setMonthKey(CommonUtil.getStr(formMap.get("monthKey")));				// 月（キー）
		result.setHimokuNmKey(CommonUtil.getStr(formMap.get("himokuNmKey")));			// 費目名 （キー）
		result.setHimokuNm(shushiDao.getHimokuNmMap());						// 費目名コンボボックスの設定
		result.setKojinNmKey(CommonUtil.getStr(formMap.get("kojinNmKey")));			// 個人名 （キー）
		result.setKojinNm(shushiDao.getKojinNmMap(loginDto));				// 個人名コンボボックスの設定

		// 収支一覧（世帯）明細情報オブジェクト生成
		List<ShushiRegistDto> shushiList = new ArrayList<ShushiRegistDto>();

		// 収支一覧情報検索処理
		List<Map<String, String>> list = shushiDao.listSearch(formMap ,loginDto);

		// 収支一覧情報検索検索結果情報を格納
		Iterator<Map<String, String>> it = list.iterator();

		// ワーク変数宣言
		int zandaka = 0;			// 残高
		int shunyuTotal = 0;		// 収入合計
		int shishutsuTotal = 0;		// 支出合計

		// 検索結果の件数、繰り返し
		while (it.hasNext()) {

			// 検索結果をマップに格納
			Map<String, String> map = it.next();

			// 収支明細情報格納オブジェクト初期化
			ShushiRegistDto dto = new ShushiRegistDto();

			dto.setShushiKey(map.get("SHUSHI_KEY") + "," + Const.DISP_ST002);		// 収支（キー）の設定
			dto.setYmd(map.get("YMD"));							// 日付の設定
			dto.setHimokuNmSei(map.get("HIMOKU_NM"));			// 費目名の設定
			dto.setKojinNm(map.get("KOJIN_NM"));				// 個人名の設定

			// 費目区分が1(収入)の場合
			if (map.get("HIMOKU_KBN").equals("1")) {
				dto.setShunyu(map.get("KINGAKU"));				// 収入の設定

				// 残高 = 残高 + レコードの金額を設定
				dto.setZandaka(CommonUtil.getStr(
						zandaka + Integer.parseInt(map.get("KINGAKU"))));

				// 残高情報を保持
				zandaka = zandaka + Integer.parseInt(map.get("KINGAKU"));

				// 収入合計 = 収入合計 + レコードの金額を設定
				shunyuTotal = shunyuTotal + Integer.parseInt(map.get("KINGAKU"));
			} else {
				dto.setShishutsu(map.get("KINGAKU"));			// 支出

				// 残高 = 残高 + 本レコードの金額を設定
				dto.setZandaka(CommonUtil.getStr(
						zandaka - Integer.parseInt(map.get("KINGAKU"))));

				// 残高情報を保持
				zandaka = zandaka - Integer.parseInt(map.get("KINGAKU"));

				// 支出合計 = 支出合計 + レコードの金額を設定
				shishutsuTotal = shishutsuTotal + Integer.parseInt(map.get("KINGAKU"));
			}
			// 収支一覧（世帯）明細情報オブジェクトに格納
			shushiList.add(dto);
		}

		// 収入合計の算術結果を格納
		result.setShunyuTotal(CommonUtil.getStr(shunyuTotal));

		// 支出合計の算術結果を格納
		result.setShishutsuTotal(CommonUtil.getStr(shishutsuTotal));

		// 残高合計の算術結果を格納
		result.setZandakaTotal(CommonUtil.getStr(zandaka));

		// 収支一覧（世帯）明細情報を収支一覧（世帯）情報オブジェクトに格納
		result.setList(shushiList);

		return result;
	}

	/**
	 * 収支一覧（世帯）画面削除メソッド
	 */
	public void shushiListSetaiDelete(Map<String, Object> formMap){

		// 削除情報を格納
		String[] checkDel = (String[])formMap.get("checkDel");

		// 収支明細件数取得結果
		int shushiDtlCnt = 0;

		// 削除情報の件数分、繰り返し
		for (int i = 0; i < checkDel.length; i++) {

			// 削除情報をカンマ区切りで配列収支キー（0番目:収支ID、1番目:収支明細ID）に格納
			String[] shushiKey = checkDel[i].split(",");

			if (i == 0) {
				// 収支明細件数取得
				shushiDtlCnt = shushiDao.getShushiDtlCnt(shushiKey[0]);
			}

			// 収支明細情報を削除
			shushiDao.listDtlDelete(shushiKey[0], shushiKey[1]);

			// 収支明細件数と削除チェックされた件数が同じ場合
			if (shushiDtlCnt == checkDel.length && i+1 == checkDel.length) {
				// 収支情報を削除
				shushiDao.listDelete(shushiKey[0]);
			}
		}
	}

	/**
	 * 登録画面初期表示メソッド
	 */
	public ShushiRegistDto registInit(Map<String, Object> formMap){
		ShushiRegistDto result = new ShushiRegistDto();
		// 費目名コンボボックスの設定
		result.setHimokuNm(shushiDao.getHimokuNmMap());
		// 費目区分コンボボックスの設定
		result.setHimokuKbn(commonDao.getCode(CD_BUNRUI_001));
		// 更新モードの場合は、対象の収支情報を取得する
		if (formMap != null && !CommonUtil.isNull(CommonUtil.getStr(formMap.get("shushiId")))) {
			Map<String, String> map = shushiDao.registFind(formMap);
			if (map != null) {
				result.setShushiId(formMap.get("shushiId").toString());
				result.setShushiDtlId(formMap.get("shushiDtlId").toString());
				result.setKojinId(map.get("KOJIN_ID"));
				result.setHimokuNmKey(map.get("HIMOKU_KEY"));
				result.setHimokuKbnKey(map.get("HIMOKU_KBN"));
				result.setKingaku(map.get("KINGAKU"));
				result.setYmd(map.get("YMD"));
				result.setRevision(map.get("REVISION"));
			}
		}
		return result;
	}

	/**
	 * 登録画面登録・更新メソッド
	 * @throws CommonValidateException
	 */
	public void registInsUpd(Map<String, Object> formMap) throws Exception {

		// 登録の場合
		if (CommonUtil.isNull(CommonUtil.getStr(formMap.get(Const.ITEM_REVISION)))) {
			// 個人ID,年月(日付の年月)で収支明細ID+1の値を取得する。
			Map<String, String> nextInfo = shushiDao.getMaxShushiDtl(formMap);
			if (nextInfo.size() > 0) {
				// 取得できた場合
				formMap.put("shushiId", nextInfo.get("NOWSHUSHIID"));
				formMap.put("shushiDtlId", nextInfo.get("NEXTSHUSHIDTLID"));
			} else {
				// 取得できない場合、シーケンスから収支IDを取得する。
				formMap.put("shushiId", shushiDao.getNextShushiId());
				formMap.put("shushiDtlId", "00000001");
				// 収支テーブル登録処理
				shushiDao.registInsert(formMap);
			}
			// 収支明細テーブル登録処理
			shushiDao.registDtlInsert(formMap);

		// 更新の場合
		} else {
			// 排他処理
			if (!shushiDao.lock(formMap)) {
				throw new CommonValidateException("errors.lock");
			}
			// 更新処理
			shushiDao.registDtlUpdate(formMap);
		}
	}

	/**
	 * DAOのsetter
	 * @param shushiDao
	 */
	public void setShushiDao(ShushiDao shushiDao){
		this.shushiDao = shushiDao;
	}
	/**
	 * DAOのsetter
	 * @param commonDao
	 */
	public void setCommonDao(CommonDao commonDao){
		this.commonDao = commonDao;
	}
}