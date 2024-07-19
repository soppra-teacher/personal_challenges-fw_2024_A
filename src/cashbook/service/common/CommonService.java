package cashbook.service.common;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface CommonService {
	/**
	 * エンコードされた画像データをデコードして保存する
	 * @param formMap     フォーム
	 * @param request  リクエスト
	 * @throw IOException
	 */
	public void fileUpdIns(Map<String, Object> formMap, HttpServletRequest request) throws IOException;
	
}