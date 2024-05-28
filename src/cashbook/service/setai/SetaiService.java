package cashbook.service.setai;
import java.util.Map;

import cashbook.dto.common.LoginDto;
import cashbook.dto.setai.SetaiListDto;
import cashbook.dto.setai.SetaiRegistDto;

public interface SetaiService {
  public SetaiListDto listInit();
  public SetaiListDto listSearch(Map<String, Object> formMap);
  public SetaiRegistDto registInit(Map<String, Object> formMap);
  public boolean chkDelete(Map<String, Object> formMap, LoginDto loginDto);
  public boolean listDelete(Map<String, Object> formMap, LoginDto loginDto);
  public void registInsUpd(Map<String, Object> formMap, LoginDto loginDto) throws Exception;
}