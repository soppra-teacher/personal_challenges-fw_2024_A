package cashbook.service.kankou;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import cashbook.dao.kankou.KankouUpdDelDao;
import cashbook.dto.common.LoginDto;
import cashbook.dto.kankou.KankouUpdDelDto;
import cashbook.util.KankouUpdDelConst;

public class KankouUpdDelServiceImpl implements KankouUpdDelService {


	/** 観光Dao */
	private KankouUpdDelDao kankouUpdDelDao;

	/** 観光Dao */
	private TransactionTemplate transactionTemplate;


	/**
	 * 観光DAOのsetter
	 * @param kankouUpdDelDao
	 */
	public void setKankouUpdDelDao(KankouUpdDelDao kankouUpdDelDao) {
		this.kankouUpdDelDao = kankouUpdDelDao;
	}

	/**
	 * TransactionTemplateのsetter
	 * @param 
	 */
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	
	/**
	 * 更新削除画面初期表示メソッド
	 * @param formMap
	 * @param loginDto ログイン情報Dto
	 * @return 検索結果
	 */
	public KankouUpdDelDto updDelInit(Map<String, Object> formMap, LoginDto loginDto) {
		KankouUpdDelDto result = new KankouUpdDelDto();

		Map<String, String> map = kankouUpdDelDao.findKankou(formMap, loginDto);
		result.setLogUserId((loginDto.getUserId()));
		result.setImagePath(map.get("FILE_NM"));
		result.setKankouNm(map.get("KANKOU_NM"));
		result.setCategoryNm(map.get("CATEGORY_NM"));
		result.setTodouhukenNm(map.get("KEN_NM"));
		result.setTihouNm(map.get("TIHOU_NM"));
		result.setSetsumei(map.get("SETSUMEI"));
		result.setReview(map.get("REVIEW"));
		result.setUserId(map.get("USER_ID"));
		//評価値が登録されていた場合は評価値をセットし、Judgeには１をセット(ボタンを更新と表示させるため)
		if ((map.get("HYOUKATI") != null)) {
			result.setHyokaJudge("1");
			result.setHyoka(map.get("HYOUKATI"));
		} else {//評価値が未登録の場合は3をセットし、Judgeには０をセット(ボタンを登録と表示させるため)
			result.setHyokaJudge("0");
			result.setHyoka("3");
		}
		return result;
	}

	/**
	 * 評価値登録メソッド
	 * @param formMap
	 * @param loginDto ログイン情報
	 * @param kankouId 観光地Id
	 * @throws Exception
	 */
	public void hyokaIns(Map<String, Object> formMap, LoginDto loginDto, String kankouId) throws Exception {
		kankouUpdDelDao.insHyoka(formMap, loginDto,kankouId);
	}

	/**
	 * 更新メソッド
	 * @param formMap
	 * @param loginDto ログイン情報
	 * @param request リクエスト
	 * @param kankouId 観光地Id
	 * @param compareHyoka
	 * @throws Exception
	 */
	public void update(Map<String, Object> formMap, LoginDto loginDto, HttpServletRequest request, String kankouId, String compareHyoka) throws Exception {
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				//元々登録されている評価値と更新が押されて場合の評価値を比較
				if (!(compareHyoka.equals(formMap.get(KankouUpdDelConst.KEY_HYOKA)))) {
					//評価値の更新
					kankouUpdDelDao.updHyoka(formMap, loginDto, kankouId);
				}
				//投稿者がどうかを判断する。
				if (formMap.get(KankouUpdDelConst.KEY_USER_ID).equals(loginDto.getUserId())) {
					//観光地テーブルの更新
					kankouUpdDelDao.updateKankou(formMap, loginDto);
				}
			}
		});
	}

	/**
	 * 削除メソッド
	 * @param formMap
	 * @param request リクエスト
	 * @throws Exception
	 */
	public void delete(Map<String, Object> formMap, HttpServletRequest request) throws Exception {
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {

			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				//評価値テーブルの削除
				kankouUpdDelDao.delHyoka(formMap);
				//観光地テーブルの削除
				kankouUpdDelDao.delKankou(formMap);
				
			}
		});
	}
}
