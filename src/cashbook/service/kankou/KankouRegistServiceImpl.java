package cashbook.service.kankou;

import static cashbook.util.Const.*;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import cashbook.dao.common.CommonDao;
import cashbook.dao.kankou.KankouRegistDao;
import cashbook.dto.common.LoginDto;
import cashbook.dto.kankou.KankouRegistDto;
import cashbook.exception.CommonValidateException;
import cashbook.util.KankouRegistConst;

public class KankouRegistServiceImpl implements KankouRegistService {

	/** 共通Dao */
	private CommonDao commonDao;

	/** 観光Dao */
	private KankouRegistDao kankouRegistDao;

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
	 * @param kankouRegistDao
	 */
	public void setkankouRegistDao(KankouRegistDao kankouRegistDao) {
		this.kankouRegistDao = kankouRegistDao;
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
	 * @return 都道府県・カテゴリコンボボックスの値
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
	 * @param formMap
	 * @param loginDto
	 * @param request
	 * @throws Exception
	 */
	public void registIns(Map<String, Object> formMap, LoginDto loginDto, HttpServletRequest request) throws Exception {
		// 観光地・評価値登録
		// 観光地情報存在チェック
		if (!kankouRegistDao.checkOverlapKankou(formMap, loginDto)) {
			throw new CommonValidateException(MSG_ERRORS_KANKOU_DATA);
		}

		transactionTemplate.execute(new TransactionCallbackWithoutResult() {

			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				//テーブルロック
				kankouRegistDao.lockKankou();
				
				//formMapに、観光IDの最大値をセット
				formMap.put(KankouRegistConst.KEY_KANKOU_ID, kankouRegistDao.getMaxKankou());
				
				// 観光地登録処理
				kankouRegistDao.registKankou(formMap, loginDto);

				// 評価値登録処理
				kankouRegistDao.registHyoka(formMap, loginDto);
				
			}
		});
	}
}
