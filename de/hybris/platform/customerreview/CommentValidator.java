package de.hybris.platform.customerreview;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import de.hybris.platform.customerreview.impl.Required;

public class CommentValidator implements Validator {

	private CurseWord curseWord;

	public CurseWord getCurseWord() {
		return curseWord;
	}

	@Required
	public void setCurseWord(CurseWord curseWord) {
		this.curseWord = curseWord;
	}
	
	@Override
	public boolean supports(Class<?> arg0) {
		return String.class.equals(arg0);
	}

	@Override
	public void validate(Object object, Errors errors) {
		String comment = (String) object;
		for (String word: curseWord.getList()) {
			if (comment.contains(word)) {
				errors.rejectValue("comment", "message.comment.curse");
				break;
			}
		}
	}
}
