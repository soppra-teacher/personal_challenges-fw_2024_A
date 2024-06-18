package cashbook.dao.kankou;

import static cashbook.util.Const.*;

import java.util.List;
import java.util.Map;

import org.springframework.dao.CannotAcquireLockException;

import cashbook.dao.common.BaseDaoImpl;
import cashbook.dto.common.LoginDto;
import cashbook.util.CommonUtil;
import cashbook.util.KankouConst;
import cashbook.util.KojinConst;
import cashbook.util.SetaiConst;

public class KankouDaoImpl extends BaseDaoImpl implements KankouDao {

	/**
	 * 個人マスタ一覧を検索する
	 * @return 個人マスタ一覧
	 */
	public List<Map<String, String>> searchKojin(Map<String, Object> formMap) {

		List<Map<String, String>> result;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT  M1.kojin_id ");
		sql.append("       ,M1.setai_id ");
		sql.append("       ,M1.pass ");
		sql.append("       ,M1.kojin_nm ");
		sql.append("       ,M1.kojin_nm_kana ");
		sql.append("       ,M1.seibetsu_kbn ");
		sql.append("       ,M1.zokugara ");
		sql.append("       ,M1.setainushi_flg ");
		sql.append("       ,M1.del_flg ");
		sql.append("       ,M1.ins_user ");
		sql.append("       ,M1.ins_date ");
		sql.append("       ,M1.upd_user ");
		sql.append("       ,M1.upd_date ");
		sql.append("       ,M1.revision ");
		sql.append("       ,M1.kojin_nicknm ");
		sql.append("  FROM MST_KOJIN M1 ");
		sql.append(" WHERE M1.DEL_FLG = '0' ");
		// 個人名
		if (!CommonUtil.isNull(CommonUtil.getStr(formMap.get(KojinConst.KEY_KOJIN_NM)))) {
			sql.append(" AND M1.KOJIN_NM LIKE '%").append(formMap.get(KojinConst.KEY_KOJIN_NM)).append("%' ");
		}
		// 個人名ｶﾅ
		if (!CommonUtil.isNull(CommonUtil.getStr(formMap.get(KojinConst.KEY_KOJIN_NM_KANA)))) {
			sql.append(" AND M1.KOJIN_NM_KANA LIKE '%").append(formMap.get(KojinConst.KEY_KOJIN_NM_KANA)).append("%' ");
		}
		// 性別
		if (!CommonUtil.isNull(CommonUtil.getStr(formMap.get(KojinConst.KEY_SEIBETSU_KBN_KEY)))) {
			sql.append(" AND M1.SEIBETSU_KBN = '").append(formMap.get(KojinConst.KEY_SEIBETSU_KBN_KEY)).append("' ");
		}
		// 続柄
		if (!CommonUtil.isNull(CommonUtil.getStr(formMap.get(KojinConst.KEY_ZOKUGARA)))) {
			sql.append(" AND M1.ZOKUGARA = '").append(formMap.get(KojinConst.KEY_ZOKUGARA)).append("' ");
		}
		// 世帯主フラグ
		if (!CommonUtil.isNull(CommonUtil.getStr(formMap.get(KojinConst.KEY_SETAINUSI_FLG_VALUE)))) {
			if (SETAINUSHI_ON.equals(formMap.get(KojinConst.KEY_SETAINUSI_FLG_VALUE))) {
				sql.append(" AND M1.SETAINUSHI_FLG = '").append(formMap.get(KojinConst.KEY_SETAINUSI_FLG_VALUE))
						.append("' ");
			}
		}
		sql.append(" ORDER BY M1.KOJIN_ID ");
		result = super.search(sql.toString());

		return result;
	}

	/**
	 * 個人マスタを削除する
	 */
	public void deleteKojin(String kojinId, LoginDto loginDto) {

		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE MST_KOJIN M1 ");
		sql.append("   SET M1.DEL_FLG = '1' ");
		sql.append("     , M1.UPD_USER = '").append(loginDto.getKojinId()).append("' ");
		sql.append("     , M1.UPD_DATE = SYSDATE ");
		sql.append("     , M1.REVISION = M1.REVISION + 1 ");
		sql.append(" WHERE M1.KOJIN_ID = '").append(kojinId).append("' ");

		super.update(sql.toString());
	}

	/**
	 * 個人マスタを検索する
	 * @return 個人マスタ
	 */
	public Map<String, String> findKojin(Map<String, Object> formMap) {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT M1.KOJIN_ID ");
		sql.append("     , M1.SETAI_ID ");
		sql.append("     , M1.PASS ");
		sql.append("     , M1.KOJIN_NM ");
		sql.append("     , M1.KOJIN_NM_KANA ");
		sql.append("     , M1.SEIBETSU_KBN ");
		sql.append("     , M1.ZOKUGARA ");
		sql.append("     , M1.SETAINUSHI_FLG ");
		sql.append("     , M1.REVISION ");
		sql.append("  FROM MST_KOJIN M1 ");
		sql.append(" WHERE M1.DEL_FLG  = '0' ");
		sql.append("   AND M1.KOJIN_ID = '").append(formMap.get(KojinConst.KEY_KOJIN_ID)).append("' ");

		return super.find(sql.toString());
	}

	/**
	 * 観光地情報を登録する
	 * @throws Exception
	 */
	public void registKankou(Map<String, Object> formMap, LoginDto loginDto) {

		StringBuffer sql = new StringBuffer();
		sql.append(" INSERT INTO TBL_KANKOU  ( ");
		sql.append("   USER_ID ");
		sql.append("   , KEN_CD ");
		sql.append("   , CATEGORY_ID ");
		sql.append("   , KANKOU_NM ");
		sql.append("   , SETSUMEI ");
		sql.append("   , REVIEW ");
		sql.append("   , FILE_NM ");
		sql.append(" ) VALUES ( ");
		sql.append("    '").append(loginDto.getKojinId()).append("' ");
		sql.append("   , '").append(formMap.get(KankouConst.KEY_TODOUFUKEN_KEY)).append("' ");
		sql.append("   , '").append(formMap.get(KankouConst.KEY_CATEGORY_KEY)).append("' ");
		sql.append("   , '").append(formMap.get(KankouConst.KEY_KANKOU_NM)).append("' ");
		System.out.println("観光名所" + formMap.get(KankouConst.KEY_KANKOU_NM));
		sql.append("   , '").append(formMap.get(KankouConst.KEY_SETSUMEI)).append("' ");
		sql.append("   , '").append(formMap.get(KankouConst.KEY_review)).append("' ");
		//写真だけ、現在は任意の値を入力
		//sql.append("   , '").append(formMap.get(KankouConst.KEY_PICTURES)).append("' ");
		sql.append("   , 'testpicture.jpeg'");
		sql.append(" ) ");

		super.update(sql.toString());
	}
	
	/**
	 * 評価値を登録する
	 * @throws Exception
	 */
	public void registHyoka(Map<String, Object> formMap, LoginDto loginDto) {

		StringBuffer sql = new StringBuffer();
		sql.append(" INSERT INTO TBL_HYOUKATI ( ");
		sql.append("   KANKOU_ID ");
		sql.append("   , USER_ID ");
		sql.append("   , HYOUKATI ");
		sql.append(" ) VALUES ( ");
		sql.append("   (SELECT MAX(KANKOU_ID) ");
		sql.append("    FROM TBL_KANKOU) ");
		sql.append("   , '").append(loginDto.getKojinId()).append("' ");
		sql.append("   , '").append(formMap.get(KankouConst.KEY_HYOKA)).append("' ");
		sql.append(" ) ");
		super.update(sql.toString());
	}

	/**
	 * 個人マスタを更新する
	 */
	public void updateKojin(Map<String, Object> formMap, LoginDto loginDto) {

		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE MST_KOJIN M1 ");
		sql.append("   SET M1.SETAI_ID = '").append(formMap.get(SetaiConst.KEY_SETAI_ID)).append("' ");
		sql.append("     , M1.PASS = '").append(formMap.get(KojinConst.KEY_PASS)).append("' ");
		sql.append("     , M1.KOJIN_NM = '").append(formMap.get(KojinConst.KEY_KOJIN_NM)).append("' ");
		sql.append("     , M1.KOJIN_NM_KANA = '").append(formMap.get(KojinConst.KEY_KOJIN_NM_KANA)).append("' ");
		sql.append("     , M1.SEIBETSU_KBN = '").append(formMap.get(KojinConst.KEY_SEIBETSU_KBN)).append("' ");
		sql.append("     , M1.ZOKUGARA = '").append(formMap.get(KojinConst.KEY_ZOKUGARA)).append("' ");
		sql.append("     , M1.SETAINUSHI_FLG = '").append(formMap.get(KojinConst.KEY_SETAINUSI_FLG_VALUE)).append("' ");
		sql.append("     , M1.UPD_USER = '").append(loginDto.getKojinId()).append("' ");
		sql.append("     , M1.UPD_DATE = SYSDATE ");
		sql.append("     , M1.REVISION = M1.REVISION + 1 ");
		sql.append(" WHERE M1.KOJIN_ID = '").append(formMap.get(KojinConst.KEY_KOJIN_ID)).append("' ");

		super.update(sql.toString());
	}

	/**
	 * 重複チェック
	 * @return true：正常、false：重複エラー
	 */
	public boolean checkOverlapKankou(Map<String, Object> formMap, LoginDto loginDto) {

		Map<String, String> result;
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT COUNT(*) AS COUNT  ");
		sql.append("  FROM TBL_KANKOU ");
		//個人IDの部分だけ、ログイン画面が作成され次第変更する
		sql.append(" WHERE USER_ID = '").append(loginDto.getKojinId()).append("' ");
		System.out.println(loginDto.getKojinId());
		System.out.println(formMap.get(KankouConst.KEY_TODOUFUKEN_KEY));
		System.out.println(formMap.get(KankouConst.KEY_KANKOU_NM));
		sql.append(" AND  KEN_CD = '").append(formMap.get(KankouConst.KEY_TODOUFUKEN_KEY)).append("' ");
		sql.append(" AND  KANKOU_NM = '").append(formMap.get(KankouConst.KEY_KANKOU_NM)).append("' ");
		System.out.println(super.find(sql.toString()).size());
		
		//SQLの結果をresultに格納する。
		result = super.find(sql.toString());
		System.out.println(result.get("COUNT"));
		
		
		if(result.get("COUNT").equals("0")) {
			return true;
		}else {
			return false;
		}
		//return super.find(sql.toString()).size() == 0;
	}

	/**
	 * 行ロック及び、排他チェック
	 * @return true：正常、false：排他エラー
	 */
	public boolean lockKojin(Map<String, Object> formMap) {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT M1.KOJIN_ID ");
		sql.append("  FROM MST_KOJIN M1 ");
		sql.append(" WHERE M1.KOJIN_ID = '").append(formMap.get(KojinConst.KEY_KOJIN_ID)).append("' ");
		sql.append("   AND M1.REVISION = '").append(formMap.get(ITEM_REVISION)).append("' ");
		sql.append("   FOR UPDATE NOWAIT ");
		try {

			return super.find(sql.toString()).size() != 0;

		} catch (CannotAcquireLockException e) {
			// 対象データがロックされている場合はエラー
			return false;
		}
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
}
