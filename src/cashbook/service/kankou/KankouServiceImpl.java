package cashbook.service.kankou;

import static cashbook.util.Const.*;

import java.util.Map;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import cashbook.dao.common.CommonDao;
import cashbook.dao.kankou.KankouDao;
import cashbook.dto.common.LoginDto;
import cashbook.dto.kankou.KankouRegistDto;
import cashbook.exception.CommonValidateException;

public class KankouServiceImpl implements KankouService {

	/** 共通Dao */
	private CommonDao commonDao;

	/** 観光Dao */
	private KankouDao kankouDao;

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
	 * @param kankouDao
	 */
	public void setKankouDao(KankouDao kankouDao) {
		this.kankouDao = kankouDao;
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
	 * @return result
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
	 * @param
	 * @throws Exception
	 */
	public void registIns(Map<String, Object> formMap, LoginDto loginDto) throws Exception {

		// 観光地・評価値登録

		// 観光地情報存在チェック
		if (!kankouDao.checkOverlapKankou(formMap, loginDto)) {
			throw new CommonValidateException(MSG_ERRORS_KANKOU_DATA);
		}

		transactionTemplate.execute(new TransactionCallbackWithoutResult() {

			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				
				//テーブルロック
				
				
				// 観光地登録処理
				kankouDao.registKankou(formMap, loginDto);

				// 評価値登録処理
				kankouDao.registHyoka(formMap, loginDto);
				
				//写真登録を後に記入予定
				
			}
		});


	}
}
