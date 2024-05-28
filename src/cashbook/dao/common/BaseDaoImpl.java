package cashbook.dao.common;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * DAO基底クラス
 * @author soppra
 */
public class BaseDaoImpl extends JdbcDaoSupport implements BaseDao {

	/**
	 * 複数検索メソッド
	 * @param strSql SQL文List
	 * @return 検索結果を格納したList
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, String>> search(String strSql) {

		List<Map<String, String>> result = (List<Map<String, String>>) getJdbcTemplate().query(strSql,
				new ResultSetExtractor() {

					public Object extractData(ResultSet rs) throws SQLException {
						List<Map<String, String>> result = new ArrayList<Map<String, String>>();
						while (rs.next()) {
							Map<String, String> map = new HashMap<String, String>();
							ResultSetMetaData metaData = rs.getMetaData();
							for (int i = 1; i <= metaData.getColumnCount(); i++) {
								map.put(metaData.getColumnName(i), rs.getString(metaData.getColumnName(i)));

							}
							result.add(map);
						}
						return result;

					}
				});
		return result;
	}

	/**
	 * 単一検索メソッド
	 * @param strSql SQL文
	 * @return 検索結果を格納したMap
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> find(String strSql) {

		Map<String, String> result = (Map<String, String>) getJdbcTemplate().query(strSql, new ResultSetExtractor() {

			public Object extractData(ResultSet rs) throws SQLException {

				Map<String, String> map = new HashMap<String, String>();
				while (rs.next()) {
					ResultSetMetaData metaData = rs.getMetaData();
					for (int i = 1; i <= metaData.getColumnCount(); i++) {
						map.put(metaData.getColumnName(i), rs.getString(metaData.getColumnName(i)));
					}
				}

				return map;
			}
		});
		return result;
	}

	/**
	 * 更新処理メソッド
	 * @param strSql SQL文
	 * @return 更新件数
	 */
	public int update(String strSql) {
		return getJdbcTemplate().update(strSql);
	}
}