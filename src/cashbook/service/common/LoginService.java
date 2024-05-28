package cashbook.service.common;
import java.util.Map;

import cashbook.dto.common.LoginDto;
public interface LoginService {
  public LoginDto execute(Map<String, Object> formMap);
}