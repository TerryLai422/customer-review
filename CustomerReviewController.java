package de.hybris.platform.customerreview;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.hybris.platform.customerreview.jalo.CustomerReview;
import de.hybris.platform.customerreview.jalo.Product;
import de.hybris.platform.customerreview.jalo.User;

@Controller
public class CustomerReviewController {
	@Autowired
	@Qualifier("commentValidator")
	private CommentValidator commentValidator;

	@Autowired
	@Qualifier("ratingValidator")
	private RatingValidator ratingValidator;

	@RequestMapping(value="/createreview", method=RequestMethod.POST)
	public void createCustomerReview(User user, Product product, Double rating, String headline, String comment) {
		
		DataBinder binder = new DataBinder(comment);
		binder.setValidator(commentValidator);

		BindingResult results = binder.getBindingResult();
		commentValidator.validate(comment, results);
		if (results.hasErrors()) {
			FieldError errors = results.getFieldError("comment");
			throw new Exception(errors.getDefaultMessage());
			// or return to error Page
			// return "errorPage";
		}
		
		DataBinder binder2 = new DataBinder(rating);
		binder.setValidator(ratingValidator);

		BindingResult results2 = binder2.getBindingResult();
		ratingValidator.validate(rating, results2);
		if (results2.hasErrors()) {
			FieldError errors = results2.getFieldError("rating");
			throw new Exception(errors.getDefaultMessage());
			// or return to error Page
			// return "errorPage";
		}
		CustomerReview customerReview = CustomerReviewManager.getInstance().createCustomerReview(rating, headline,
				comment, user, product);
	}
}
