package cashbook.service.shushi;
import java.util.Map;

import cashbook.dto.common.LoginDto;
import cashbook.dto.shushi.ShushiListDto;
import cashbook.dto.shushi.ShushiRegistDto;

public interface ShushiService {
	  public ShushiListDto shushiListKobetsuInit();
	  public ShushiListDto shushiListKobetsuSearch(Map<String, Object> formMap, LoginDto loginDto);
	  public void shushiListKobetsuDelete(Map<String, Object> formMap);
	  public ShushiListDto shushiListSetaiInit(LoginDto loginDto);
	  public ShushiListDto shushiListSetaiSearch(Map<String, Object> formMap, LoginDto loginDto);
	  public void shushiListSetaiDelete(Map<String, Object> formMap);
  public ShushiRegistDto registInit(Map<String, Object> formMap);
  public void registInsUpd(Map<String, Object> formMap) throws Exception;
}

