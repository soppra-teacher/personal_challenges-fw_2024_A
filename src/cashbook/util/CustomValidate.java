package cashbook.util;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.validator.Field;
import org.apache.commons.validator.Validator;
import org.apache.commons.validator.ValidatorAction;
import org.apache.commons.validator.util.ValidatorUtils;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.validator.Resources;

public class CustomValidate implements Serializable {

	/**
	 * デフォルトシリアルバージョン
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 半角カタカナのみチェック
	 * @param bean
	 * @param va
	 * @param field
	 * @param errors
	 * @param validator
	 * @param request
	 * @return OK:True,NG:False
	 */
	public static boolean validateHalfKanaOnly(Object bean
			                                 , ValidatorAction va
			                                 , Field field
			                                 , ActionMessages errors
			                                 , Validator validator
			                                 , HttpServletRequest request) {
		return executeCheck(bean, va, field, errors, validator, request, "^[\\uFF65-\\uFF9F\\s-]+$");
	}

	/**
	 * 郵便番号チェック
	 * @param bean
	 * @param va
	 * @param field
	 * @param errors
	 * @param validator
	 * @param request
	 * @return OK:True,NG:False
	 */
	public static boolean validatePostNo(Object bean
			                           , ValidatorAction va
			                           , Field field
			                           , ActionMessages errors
			                           , Validator validator
			                           , HttpServletRequest request) {
		return executeCheck(bean, va, field, errors, validator, request, "^\\d{3}-\\d{4}$");
	}

	/**
	 * 正規表現チェック処理
	 * @param bean
	 * @param va
	 * @param field
	 * @param errors
	 * @param validator
	 * @param request
	 * @param patternFormat
	 * @return OK:True,NG:False
	 */
	public static boolean executeCheck(Object bean
                                     , ValidatorAction va
                                     , Field field
                                     , ActionMessages errors
                                     , Validator validator
                                     , HttpServletRequest request
                                     , String patternFormat) {
		boolean bRet = true;
		String value = null;

		if (CommonUtil.isString(bean)) {
			value = (String)bean;
		} else {
			value = ValidatorUtils.getValueAsString(bean, field.getProperty());
		}

		if (value != null && !value.equals("") &&
			!CommonUtil.isStringPatternFormat(value, patternFormat)) {
			errors.add(field.getKey(), Resources.getActionMessage(validator, request, va, field));
			bRet = false;
		}

		return bRet;
	}

	/**
	 * 全角文字列チェック
	 * @param bean
	 * @param va
	 * @param field
	 * @param errors
	 * @param validator
	 * @param request
	 * @return OK:True,NG:False
	 */
	public static boolean validateZenCharOnly(Object bean
			                                 , ValidatorAction va
			                                 , Field field
			                                 , ActionMessages errors
			                                 , Validator validator
			                                 , HttpServletRequest request) {
		return executeCheck(bean, va, field, errors, validator, request, "^[^ -~｡-ﾟ]+$");
	}

}
