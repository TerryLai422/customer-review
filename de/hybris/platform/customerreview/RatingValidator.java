package de.hybris.platform.customerreview;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


public class RatingValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return Double.class.equals(arg0);
	}

	@Override
	public void validate(Object object, Errors errors) {
		Double rating = (Double) object;
		if (rating < 0) {
			errors.rejectValue("rating", "message.rating.greaterthanzero");
		}
	}
}
