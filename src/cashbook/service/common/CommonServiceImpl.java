package cashbook.service.common;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cashbook.util.Const;
import cashbook.util.KankouRegistConst;

public class CommonServiceImpl implements CommonService {

	/**
	 * エンコードされた画像データをデコードして保存する。
	 * @param formMap
	 * @param request
	 * @throws IOException
	 */
	public void fileUpdIns(Map<String, Object> formMap, HttpServletRequest request) throws IOException {
		// フォームのbase64Imageフィールドからデータを取得
		String base64Image = (String) formMap.get(Const.KEY_IMAGE_STRING);
		// Base64データURIスキーム部分を削除
		String[] parts = base64Image.split(",");
		String imageData = parts[1];
		// Base64デコード
		byte[] imageBytes = Base64.getDecoder().decode(imageData);
		// ファイル名を設定
		String fileName = formMap.get(KankouRegistConst.KEY_KANKOU_ID) + Const.IMAGE_PNG;
		// デコードされたバイト配列をファイルとして保存
		String filePath = request.getServletContext().getRealPath(Const.FILE_PATH) + fileName;
		FileOutputStream fos = new FileOutputStream(filePath);
		fos.write(imageBytes);
		fos.close();
	}

}