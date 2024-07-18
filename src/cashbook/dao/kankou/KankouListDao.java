package cashbook.dao.kankou;

import java.util.List;
import java.util.Map;

public interface KankouListDao {
	/**
	 * 観光地情報を一覧を検索する
	 * @return 観光地一覧
	 */
	public List<Map<String, String>> searchKankou(Map<String, Object> formMap);

}

