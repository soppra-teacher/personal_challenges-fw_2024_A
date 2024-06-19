package cashbook.service.kankou;

import static cashbook.util.Const.*;

import java.util.Map;

import cashbook.dao.common.CommonDao;
import cashbook.dao.kankou.KankouDao;
import cashbook.dto.common.LoginDto;
import cashbook.dto.kankou.KankouRegistDto;
import cashbook.exception.CommonValidateException;

public class KankouServiceImpl implements KankouService{

	/** 共通Dao */
	private CommonDao commonDao;
	
	/** 観光Dao */
	private KankouDao kankouDao;
	
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
	 * 観光地登録画面初期表示メソッド
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
	 * @throws CommonValidateException
	 */
	public void registIns(Map<String, Object> formMap, LoginDto loginDto) throws Exception {

		// 登録
			
		    // 存在チェック
			if (!kankouDao.checkOverlapKankou(formMap, loginDto)) {
				throw new CommonValidateException(MSG_ERRORS_PRIMARY_KEY);
			}
			// 登録処理
			kankouDao.registKankou(formMap, loginDto);
			
			// 評価値
			kankouDao.registHyoka(formMap, loginDto); 
	}
}
