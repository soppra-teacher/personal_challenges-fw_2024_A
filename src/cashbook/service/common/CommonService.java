package cashbook.service.common;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface CommonService {
	
	public void fileUpdIns(Map<String, Object> formMap, HttpServletRequest request) throws IOException;
	
}