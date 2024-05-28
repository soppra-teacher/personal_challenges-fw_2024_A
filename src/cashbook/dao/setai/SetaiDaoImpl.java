package cashbook.dao.setai;

import static cashbook.util.Const.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.CannotAcquireLockException;

import cashbook.dao.common.BaseDaoImpl;
import cashbook.dto.common.LoginDto;
import cashbook.util.CommonUtil;
import cashbook.util.KojinConst;
import cashbook.util.SetaiConst;

/**
 * 世帯DAOクラス
 * @author soppra
 */
public class SetaiDaoImpl extends BaseDaoImpl implements SetaiDao {

	/**
	 * 世帯マスタ一覧を検索する
	 * @return 費目マスタ一覧
	 */
	public List<Map<String, String>> searchSetai(Map<String, Object> formMap) {

		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT  M1.setai_id ");
		sql.append("        ,M1.setai_nm ");
		sql.append("        ,M1.setai_nm_kana ");
		sql.append("        ,M1.post_no ");
		sql.append("        ,M1.address ");
		sql.append("        ,M1.tel_no ");
		sql.append("        ,M1.del_flg ");
		sql.append("        ,M1.ins_user ");
		sql.append("        ,M1.ins_date ");
		sql.append("        ,M1.upd_user ");
		sql.append("        ,M1.upd_date ");
		sql.append("        ,M1.revision ");
		sql.append("   FROM MST_SETAI M1 ");
		sql.append("  WHERE M1.DEL_FLG = '0' ");

		// 世帯名
		if (!CommonUtil.isNull(CommonUtil.getStr(formMap.get(SetaiConst.KEY_SETAI_NM)))) {
			sql.append(" AND M1.SETAI_NM LIKE '%").append(formMap.get(SetaiConst.KEY_SETAI_NM)).append("%' ");
		}
		// 世帯名カナ
		if (!CommonUtil.isNull(CommonUtil.getStr(formMap.get(SetaiConst.KEY_SETAI_NM_KANA)))) {
			sql.append(" AND M1.SETAI_NM_KANA LIKE '%").append(formMap.get(SetaiConst.KEY_SETAI_NM_KANA)).append("%' ");
		}
		// 郵便番号
		if (!CommonUtil.isNull(CommonUtil.getStr(formMap.get(SetaiConst.KEY_POST_NO)))) {
			sql.append(" AND M1.POST_NO LIKE '%").append(formMap.get(SetaiConst.KEY_POST_NO)).append("%' ");
		}
		// 住所
		if (!CommonUtil.isNull(CommonUtil.getStr(formMap.get(SetaiConst.KEY_ADDRESS)))) {
			sql.append(" AND M1.ADDRESS LIKE '%").append(formMap.get(SetaiConst.KEY_ADDRESS)).append("%' ");
		}
		// 電話番号
		if (!CommonUtil.isNull(CommonUtil.getStr(formMap.get(SetaiConst.KEY_TEL_NO)))) {
			sql.append(" AND M1.TEL_NO LIKE '%").append(formMap.get(SetaiConst.KEY_TEL_NO)).append("%' ");
		}
		sql.append(" ORDER BY M1.SETAI_ID ");

		return super.search(sql.toString());
	}

	/**
	 * 世帯マスタを削除する
	 */
	public void deleteSetai(String setaiId, LoginDto loginDto) {

		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE MST_SETAI M1 ");
		sql.append("    SET M1.DEL_FLG  = '1' ");
		sql.append("      , M1.UPD_USER = '").append(loginDto.getKojinId()).append("' ");
		sql.append("      , M1.UPD_DATE = SYSDATE ");
		sql.append("      , M1.REVISION = M1.REVISION + 1 ");
		sql.append("  WHERE M1.SETAI_ID = '").append(setaiId).append("' ");

		super.update(sql.toString());
	}

	/**
	 * 世帯マスタを検索する
	 * @return 費目マスタ
	 */
	public Map<String, String> findSetai(Map<String, Object> formMap) {

		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT M1.SETAI_ID ");
		sql.append("      , M1.SETAI_NM ");
		sql.append("      , M1.SETAI_NM_KANA ");
		sql.append("      , M1.POST_NO ");
		sql.append("      , M1.ADDRESS ");
		sql.append("      , M1.TEL_NO ");
		sql.append("      , M1.REVISION ");
		sql.append("   FROM MST_SETAI M1 ");
		sql.append("  WHERE M1.DEL_FLG = '0' ");
		sql.append("    AND M1.SETAI_ID = '").append(formMap.get(SetaiConst.KEY_SETAI_ID)).append("' ");

		return super.find(sql.toString());
	}

	/**
	 * 世帯マスタを登録する
	 * @throws Exception
	 */
	public void registSetai(Map<String, Object> formMap, LoginDto loginDto) {

		StringBuffer sql = new StringBuffer();
		sql.append(" INSERT INTO MST_SETAI M1 ( ");
		sql.append("     M1.SETAI_ID ");
		sql.append("   , M1.SETAI_NM ");
		sql.append("   , M1.SETAI_NM_KANA ");
		sql.append("   , M1.POST_NO ");
		sql.append("   , M1.ADDRESS ");
		sql.append("   , M1.TEL_NO ");
		sql.append("   , M1.DEL_FLG ");
		sql.append("   , M1.INS_USER ");
		sql.append("   , M1.INS_DATE ");
		sql.append("   , M1.UPD_USER ");
		sql.append("   , M1.UPD_DATE ");
		sql.append("   , M1.REVISION ");
		sql.append(" ) VALUES ( ");
		sql.append("     '").append(formMap.get(SetaiConst.KEY_SETAI_ID)).append("' ");
		sql.append("   , '").append(formMap.get(SetaiConst.KEY_SETAI_NM)).append("' ");
		sql.append("   , '").append(formMap.get(SetaiConst.KEY_SETAI_NM_KANA)).append("' ");
		sql.append("   , '").append(formMap.get(SetaiConst.KEY_POST_NO)).append("' ");
		sql.append("   , '").append(formMap.get(SetaiConst.KEY_ADDRESS)).append("' ");
		sql.append("   , '").append(formMap.get(SetaiConst.KEY_TEL_NO)).append("' ");
		sql.append("   , '0' ");
		sql.append("   , '").append(loginDto.getKojinId()).append("' ");
		sql.append("   , SYSDATE ");
		sql.append("   , '").append(loginDto.getKojinId()).append("' ");
		sql.append("   , SYSDATE ");
		sql.append("   , 0 ");
		sql.append(" ) ");

		super.update(sql.toString());
	}

	/**
	 * 世帯マスタを更新する
	 */
	public void updateSetai(Map<String, Object> formMap, LoginDto loginDto) {

		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE MST_SETAI M1 ");
		sql.append("    SET M1.SETAI_NM      = '").append(formMap.get(SetaiConst.KEY_SETAI_NM)).append("' ");
		sql.append("      , M1.SETAI_NM_KANA = '").append(formMap.get(SetaiConst.KEY_SETAI_NM_KANA)).append("' ");
		sql.append("      , M1.POST_NO       = '").append(formMap.get(SetaiConst.KEY_POST_NO)).append("' ");
		sql.append("      , M1.ADDRESS       = '").append(formMap.get(SetaiConst.KEY_ADDRESS)).append("' ");
		sql.append("      , M1.TEL_NO        = '").append(formMap.get(SetaiConst.KEY_TEL_NO)).append("' ");
		sql.append("      , M1.UPD_USER      = '").append(loginDto.getKojinId()).append("' ");
		sql.append("      , M1.UPD_DATE      = SYSDATE ");
		sql.append("      , M1.REVISION      = M1.REVISION + 1 ");
		sql.append("  WHERE M1.SETAI_ID      = '").append(formMap.get(SetaiConst.KEY_SETAI_ID)).append("' ");

		super.update(sql.toString());
	}

	/**
	 * 重複チェック
	 * @return true：正常、false：重複エラー
	 */
	public boolean checkOverlapSetai(Map<String, Object> formMap) {

		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT M1.SETAI_ID ");
		sql.append("   FROM MST_SETAI M1 ");
		sql.append("  WHERE M1.SETAI_ID = '").append(formMap.get(SetaiConst.KEY_SETAI_ID)).append("' ");
		sql.append("    AND ROWNUM = 1 ");

		return super.find(sql.toString()).size() == 0;
	}

	/**
	 * 世帯主フラグ確認
	 * @return false：正常、true：整合性エラー
	 */
	public boolean checkSetainushiFlg(Map<String, Object> formMap) {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT A.KOJIN_ID ");
		sql.append("  FROM MST_KOJIN A ");
		sql.append(" WHERE A.SETAI_ID = '").append(formMap.get(SetaiConst.KEY_SETAI_ID)).append("' ");
		sql.append("   AND A.SETAINUSHI_FLG = '1' ");
		sql.append("   AND A.KOJIN_ID != '").append(formMap.get(KojinConst.KEY_KOJIN_ID)).append("' ");

		return super.find(sql.toString()).size() != 0;
	}

	/**
	 * 個人存在チェック
	 * @return true：存在する、false：存在しない
	 */
	public boolean isKojinExist(String setaiId) {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT A.KOJIN_ID ");
		sql.append("  FROM MST_KOJIN A ");
		sql.append(" WHERE A.SETAI_ID = '").append(setaiId).append("' ");
		sql.append(" AND   A.SETAINUSHI_FLG = '0' ");

		return super.find(sql.toString()).size() != 0;
	}

	/**
	 * 行ロック及び、排他チェック
	 * @return true：正常、false：排他エラー
	 */
	public boolean lockSetai(Map<String, Object> formMap) {

		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT M1.SETAI_ID ");
		sql.append("   FROM MST_SETAI M1 ");
		sql.append("  WHERE M1.SETAI_ID = '").append(formMap.get(SetaiConst.KEY_SETAI_ID)).append("' ");
		sql.append("    AND M1.REVISION = '").append(formMap.get(ITEM_REVISION)).append("' ");
		sql.append("    FOR UPDATE NOWAIT ");

		try {
			return super.find(sql.toString()).size() != 0;
		} catch (CannotAcquireLockException e) {
			// 対象データがロックされている場合はエラー
			return false;
		}
	}

	/**
	 * セレクトボックス用世帯マスタ取得
	 * @return 世帯マスタ
	 */
	public Map<String, String> searchSelectboxSetai() {

		List<Map<String, String>> result;
		Map<String, String> ret = new LinkedHashMap<String, String>();
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT M.SETAI_ID AS ID ");
		sql.append("      , M.SETAI_ID || ':' || M.SETAI_NM AS NAME");
		sql.append("   FROM MST_SETAI M ");
		sql.append("  WHERE M.DEL_FLG = '0' ");
		sql.append("  ORDER BY M.SETAI_ID ");
		result = super.search(sql.toString());
		for (Map<String, String> map : result) {
			if (ret.size() == 0) {
				ret.put("", "");
			}
			ret.put(map.get("ID"), map.get("NAME"));
		}

		return ret;
	}
}
