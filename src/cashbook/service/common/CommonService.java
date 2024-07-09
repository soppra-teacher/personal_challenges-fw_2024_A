package cashbook.service.common;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface CommonService {
	
	public void fileUpd(Map<String, Object> formMap, HttpServletRequest request) throws IOException;
}