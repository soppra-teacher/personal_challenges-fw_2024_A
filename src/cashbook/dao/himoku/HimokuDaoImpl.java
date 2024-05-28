package cashbook.dao.himoku;

import static cashbook.util.Const.*;

import java.util.List;
import java.util.Map;

import org.springframework.dao.CannotAcquireLockException;

import cashbook.dao.common.BaseDaoImpl;
import cashbook.dto.common.LoginDto;
import cashbook.util.CommonUtil;
import cashbook.util.HimokuConst;

/**
 * 費目マスタDAOクラス
 * @author soppra
 */
public class HimokuDaoImpl extends BaseDaoImpl implements HimokuDao {

	/**
	 * <p><b>
	 * 費目マスタメンテ画面
	 * <br>検索処理
	 * </b></p>
	 * @param フォーム項目
	 * @return List<Map<String, String>> 検索結果
	 */
	public List<Map<String, String>> searchHimokuList(Map<String, Object> formMap) {

		// フォーム項目の入力値でSQLを組み立てる。
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT M1.himoku_cd ");
		sql.append("      ,M1.himoku_nm ");
		sql.append("      ,M1.himoku_kbn ");
		sql.append("      ,M1.del_flg ");
		sql.append("      ,M1.ins_user ");
		sql.append("      ,M1.ins_date ");
		sql.append("      ,M1.upd_user ");
		sql.append("      ,M1.upd_date ");
		sql.append("      ,M1.revision ");
		sql.append("  FROM MST_HIMOKU M1 ");
		sql.append(" WHERE M1.DEL_FLG = '0' ");

		// 費目名
		if (!CommonUtil.isNull(CommonUtil.getStr(formMap.get(HimokuConst.KEY_HIMOKU_NM)))) {
			sql.append(" AND M1.HIMOKU_NM LIKE '%").append(formMap.get(HimokuConst.KEY_HIMOKU_NM)).append("%' ");
		}
		// 費目区分
		if (!CommonUtil.isNull(CommonUtil.getStr(formMap.get(HimokuConst.KEY_HIMOKU_KBN_KEY)))) {
			sql.append(" AND M1.HIMOKU_KBN = '").append(formMap.get(HimokuConst.KEY_HIMOKU_KBN_KEY)).append("' ");
		}
		sql.append(" ORDER BY M1.HIMOKU_CD ");

		// 組み立てたSQLで検索処理を行う。
		List<Map<String, String>> result = super.search(sql.toString());

		// 処理結果を戻す
		return result;
	}

	/**
	 * <p><b>
	 * 費目マスタメンテ画面
	 * <br>削除処理(論理削除)
	 * </b></p>
	 * @param 費目コード
	 * @param ログイン情報DTO
	 */
	public void deleteHimoku(String himokuCd, LoginDto loginDto) {

		// 論理削除用のSQLを組み立てる。
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE MST_HIMOKU M1 ");
		sql.append("   SET M1.DEL_FLG   = '1' ");
		sql.append("     , M1.UPD_USER  = '").append(loginDto.getKojinId()).append("' ");
		sql.append("     , M1.UPD_DATE  = SYSDATE ");
		sql.append("     , M1.REVISION  = M1.REVISION + 1 ");
		sql.append(" WHERE M1.HIMOKU_CD = '").append(himokuCd).append("' ");

		// 組み立てたSQLで更新処理(論理削除)を行う。
		super.update(sql.toString());
	}

	/**
	 * <p><b>
	 * 費目マスタ登録画面
	 * <br>検索処理
	 * </b></p>
	 * @param フォーム項目
	 * @return Map<String, String> 検索結果
	 */
	public Map<String, String> findHimoku(Map<String, Object> formMap) {

		// 検索用のSQLを組み立てる。
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT M1.HIMOKU_CD ");
		sql.append("     , M1.HIMOKU_NM ");
		sql.append("     , M1.HIMOKU_KBN ");
		sql.append("     , M1.REVISION ");
		sql.append("  FROM MST_HIMOKU M1 ");
		sql.append(" WHERE M1.DEL_FLG   = '0' ");
		sql.append("   AND M1.HIMOKU_CD = '").append(formMap.get(HimokuConst.KEY_HIMOKU_CD)).append("' ");

		// 組み立てたSQLで検索処理を行う。
		Map<String, String> result = super.find(sql.toString());
		// 検索結果を戻す。
		return result;
	}

	/**
	 * <p><b>
	 * 費目マスタ登録画面
	 * <br>登録処理
	 * </b></p>
	 * @param フォーム項目
	 * @param ログイン情報DTO
	 */
	public void registHimoku(Map<String, Object> formMap, LoginDto loginDto) {

		// 登録用のSQLを組み立てる。
		StringBuffer sql = new StringBuffer();
		sql.append(" INSERT INTO MST_HIMOKU M1 ( ");
		sql.append("     M1.HIMOKU_CD ");
		sql.append("   , M1.HIMOKU_NM ");
		sql.append("   , M1.HIMOKU_KBN ");
		sql.append("   , M1.DEL_FLG ");
		sql.append("   , M1.INS_USER ");
		sql.append("   , M1.INS_DATE ");
		sql.append("   , M1.UPD_USER ");
		sql.append("   , M1.UPD_DATE ");
		sql.append("   , M1.REVISION ");
		sql.append(" ) VALUES ( ");
		sql.append("     '").append(formMap.get(HimokuConst.KEY_HIMOKU_CD)).append("' ");
		sql.append("   , '").append(formMap.get(HimokuConst.KEY_HIMOKU_NM)).append("' ");
		sql.append("   , '").append(formMap.get(HimokuConst.KEY_HIMOKU_KBN_KEY)).append("' ");
		sql.append("   , '0' ");
		sql.append("   , '").append(loginDto.getKojinId()).append("' ");
		sql.append("   , SYSDATE ");
		sql.append("   , '").append(loginDto.getKojinId()).append("' ");
		sql.append("   , SYSDATE ");
		sql.append("   , 0 ");
		sql.append(" ) ");

		// 組み立てたSQLで登録処理を行う。
		super.update(sql.toString());
	}

	/**
	 * <p><b>
	 * 費目マスタ登録画面
	 * <br>更新処理
	 * </b></p>
	 * @param フォーム項目
	 * @param ログイン情報DTO
	 */
	public void updateHimoku(Map<String, Object> formMap, LoginDto loginDto) {

		// 更新用のSQLを組み立てる。
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE MST_HIMOKU M1 ");
		sql.append("   SET M1.HIMOKU_NM  = '").append(formMap.get(HimokuConst.KEY_HIMOKU_NM)).append("' ");
		sql.append("     , M1.HIMOKU_KBN = '").append(formMap.get(HimokuConst.KEY_HIMOKU_KBN_KEY)).append("' ");
		sql.append("     , M1.UPD_USER   = '").append(loginDto.getKojinId()).append("' ");
		sql.append("     , M1.UPD_DATE   = SYSDATE ");
		sql.append("     , M1.REVISION   = M1.REVISION + 1 ");
		sql.append(" WHERE M1.HIMOKU_CD  = '").append(formMap.get(HimokuConst.KEY_HIMOKU_CD)).append("' ");

		// 組み立てたSQLで更新処理を行う。
		super.update(sql.toString());
	}

	/**
	 * <p><b>
	 * 費目マスタ登録画面
	 * <br>重複チェック
	 * </b></p>
	 * @param フォーム項目
	 */
	public boolean checkOverlapHimoku(Map<String, Object> formMap) {

		// 検索用のSQLを組み立てる。
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT M1.HIMOKU_CD ");
		sql.append("  FROM MST_HIMOKU M1 ");
		sql.append(" WHERE M1.HIMOKU_CD = '").append(formMap.get(HimokuConst.KEY_HIMOKU_CD)).append("' ");
		sql.append("   AND ROWNUM = 1 ");

		// 組み立てたSQLで検索処理を行う。
		return super.find(sql.toString()).size() == 0;
	}

	/**
	 * 行ロック及び、排他チェック
	 * @param formMap フォーム項目
	 * @return true：正常、false：排他エラー
	 */
	public boolean lockHimoku(Map<String, Object> formMap) {

		// 検索用のSQLを組み立てる。
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT M1.HIMOKU_CD ");
		sql.append("  FROM MST_HIMOKU M1 ");
		sql.append(" WHERE M1.HIMOKU_CD = '").append(formMap.get(HimokuConst.KEY_HIMOKU_CD)).append("' ");
		sql.append("   AND M1.REVISION  = '").append(formMap.get(ITEM_REVISION)).append("' ");
		sql.append("   FOR UPDATE NOWAIT ");
		try {

			// 組み立てたSQLで検索処理を行う。
			return super.find(sql.toString()).size() != 0;

		} catch (CannotAcquireLockException e) {
			// 対象データがロックされている場合はエラー
			return false;
		}
	}
}
