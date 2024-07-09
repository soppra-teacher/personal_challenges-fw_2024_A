package cashbook.service.kankou;

import static cashbook.util.Const.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import cashbook.dao.common.CommonDao;
import cashbook.dao.kankou.KankouDao;
import cashbook.dto.common.LoginDto;
import cashbook.dto.kankou.KankouRegistDto;
import cashbook.exception.CommonValidateException;
import cashbook.util.CommonUtil;
import cashbook.util.KankouConst;

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
	 * @return 都道府県・カテゴリコンボボックスの初期値
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
	 * @param Map<String, Object> formMap
	 * @param LoginDto loginDto
	 * @param HttpServletRequest request
	 * @throws Exception
	 */
	public void registIns(Map<String, Object> formMap, LoginDto loginDto, HttpServletRequest request) throws Exception {

		// 観光地・評価値登録

		// 観光地情報存在チェック
		if (!kankouDao.checkOverlapKankou(formMap, loginDto)) {
			throw new CommonValidateException(MSG_ERRORS_KANKOU_DATA);
		}

		transactionTemplate.execute(new TransactionCallbackWithoutResult() {

			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				
				//テーブルロック
				kankouDao.lockKankou();
				
				//formMapに、観光IDの最大値をセット
				formMap.put(KankouConst.KEY_ID, kankouDao.getmaxKankou());
				
				// 観光地登録処理
				kankouDao.registKankou(formMap, loginDto);

				// 評価値登録処理
				kankouDao.registHyoka(formMap, loginDto);
				
				//写真登録
				
				if(!CommonUtil.isNull((String)formMap.get(KankouConst.KEY_ENCODINGIMAGE))) {
					// フォームのbase64Imageフィールドからデータを取得
					String base64Image = (String) formMap.get(KankouConst.KEY_ENCODINGIMAGE);
					// Base64データURIスキーム部分を削除
					String[] parts = base64Image.split(",");
					String imageData = parts[1];
					
					// Base64デコード
					byte[] imageBytes = Base64.getDecoder().decode(imageData);
					//MAX(観光ID) + 1.jpegの値をファイル名として設定
					String fileName =  (formMap.get(KankouConst.KEY_ID) + KankouConst.KEY_PNG);
					
					// デコードされたバイト配列をファイルとして保存
					String filePath = request.getServletContext().getRealPath("/img/") + fileName;
					
					try (FileOutputStream fos = new FileOutputStream(filePath)) {
						fos.write(imageBytes);
					} catch (FileNotFoundException e) {
						// アップロードするファイルが見つからなかった時
						e.printStackTrace();
					} catch (IOException e) {
						// その他の例外処理
						e.printStackTrace();
					}
				}
			}
		});
	}
}
