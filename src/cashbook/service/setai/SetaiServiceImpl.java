package cashbook.service.setai;

import static cashbook.util.Const.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cashbook.dao.setai.SetaiDao;
import cashbook.dto.common.LoginDto;
import cashbook.dto.setai.SetaiListDto;
import cashbook.dto.setai.SetaiRegistDto;
import cashbook.exception.CommonValidateException;
import cashbook.util.CommonUtil;
import cashbook.util.KojinConst;
import cashbook.util.SetaiConst;


/**
 * 世帯マスタサービス
 * @author soppra
 */
public class SetaiServiceImpl implements SetaiService{
	private SetaiDao setaiDao;

	/**
	 * 一覧画面初期表示メソッド
	 */
	public SetaiListDto listInit(){
		SetaiListDto result = new SetaiListDto();
		return result;
	}

	/**
	 * 一覧画面検索メソッド
	 */
	public SetaiListDto listSearch(Map<String, Object> formMap){
		//------------------------------------------
		// ヘッダ
		//------------------------------------------
		SetaiListDto result = new SetaiListDto();
		// 入力項目を保持
		result.setSetaiId(CommonUtil.getStr(formMap.get(SetaiConst.KEY_SETAI_ID)));
		result.setSetaiNm(CommonUtil.getStr(formMap.get(SetaiConst.KEY_SETAI_NM)));
		result.setSetaiNmKana(CommonUtil.getStr(formMap.get(SetaiConst.KEY_SETAI_NM_KANA)));
		result.setPostNo(CommonUtil.getStr(formMap.get(SetaiConst.KEY_POST_NO)));
		result.setAddress(CommonUtil.getStr(formMap.get(SetaiConst.KEY_ADDRESS)));
		result.setTelNo(CommonUtil.getStr(formMap.get(SetaiConst.KEY_TEL_NO)));

		//------------------------------------------
		// 一覧
		//------------------------------------------
		List<SetaiRegistDto> setaiList = new ArrayList<SetaiRegistDto>();
		// 検索処理
		List<Map<String, String>> list = setaiDao.searchSetai(formMap);
		Iterator<Map<String, String>> it = list.iterator();
		while (it.hasNext()) {
			Map<String, String> map = it.next();
			SetaiRegistDto dto = new SetaiRegistDto();
			dto.setSetaiId(map.get("SETAI_ID"));
			dto.setSetaiNm(map.get("SETAI_NM"));
			dto.setSetaiNmKana(map.get("SETAI_NM_KANA"));
			dto.setPostNo(map.get("POST_NO"));
			dto.setAddress(map.get("ADDRESS"));
			dto.setTelNo(map.get("TEL_NO"));
			setaiList.add(dto);
		}
		result.setList(setaiList);
		return result;
	}

	/**
	 * 削除チェックメソッド
	 * 世帯主かの判定を行う
	 */
	public boolean chkDelete(Map<String, Object> formMap, LoginDto loginDto){
		String[] checkDel = (String[])formMap.get(ITEM_CHECKBOX_DELETE);
		boolean judgeSetaiNushi = false;
		for (int i = 0; i < checkDel.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put(SetaiConst.KEY_SETAI_ID, checkDel[i]);
			map.put(KojinConst.KEY_KOJIN_ID, loginDto.getKojinId());
			judgeSetaiNushi = setaiDao.checkSetainushiFlg(map);
		}
		// 1件でも世帯主ではない情報を削除しようとした場合は、Trueを返す。
		return judgeSetaiNushi;
	}

	/**
	 * 一覧画面削除メソッド
	 */
	public boolean listDelete(Map<String, Object> formMap, LoginDto loginDto){
		List<String> list = CommonUtil.convFormMapToList(formMap);
		for(String checkDel : list){
			// 該当世帯に紐付く個人の存在チェック
			// 1件でも存在した場合は、エラーを返すようにする
			if(!isKojinExist(checkDel)){
				setaiDao.deleteSetai(checkDel, loginDto);
			} else {
				return false;
			}
		}
		return true;
	}

	/**
	 * 世帯に紐付く個人の存在チェックメソッド
	 * 削除対象世帯に個人が紐付くかのチェック
	 */
	public boolean isKojinExist(String setaiId){

		boolean isKojin = setaiDao.isKojinExist(setaiId);

		return isKojin;
	}

	/**
	 * 登録画面初期表示メソッド
	 */
	public SetaiRegistDto registInit(Map<String, Object> formMap){
		SetaiRegistDto result = new SetaiRegistDto();
		// 更新モードの場合は、対象の世帯マスタを取得する
		if (formMap != null && !CommonUtil.isNull(CommonUtil.getStr(formMap.get(SetaiConst.KEY_SETAI_ID)))) {
			Map<String, String> map = setaiDao.findSetai(formMap);
			if (map != null) {
				result.setSetaiId(map.get("SETAI_ID"));
				result.setSetaiNm(map.get("SETAI_NM"));
				result.setSetaiNmKana(map.get("SETAI_NM_KANA"));
				result.setPostNo(map.get("POST_NO"));
				result.setAddress(map.get("ADDRESS"));
				result.setTelNo(map.get("TEL_NO"));
				result.setRevision(map.get("REVISION"));
			}
		}
		return result;
	}

	/**
	 * 登録画面登録・更新メソッド
	 * @throws CommonValidateException
	 */
	public void registInsUpd(Map<String, Object> formMap, LoginDto loginDto) throws Exception {
		// 登録の場合
		if (CommonUtil.isNull(CommonUtil.getStr(formMap.get(ITEM_REVISION)))) {
			// 存在チェック
			if (!setaiDao.checkOverlapSetai(formMap)) {
				throw new CommonValidateException(MSG_ERRORS_PRIMARY_KEY);
			}
			// 登録処理
			setaiDao.registSetai(formMap, loginDto);
		// 更新の場合
		} else {
			// 排他処理
			if (!setaiDao.lockSetai(formMap)) {
				throw new CommonValidateException(MSG_ERRORS_DATA_LOCK);
			}
			// 更新処理
			setaiDao.updateSetai(formMap, loginDto);
		}
	}

	/**
	 * DAOのsetter
	 * @param setaiDao
	 */
	public void setSetaiDao(SetaiDao setaiDao){
		this.setaiDao = setaiDao;
	}
}