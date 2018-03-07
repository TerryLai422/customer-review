package de.hybris.platform.customerreview;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.customerreview.impl.AbstractBusinessService;
import de.hybris.platform.customerreview.model.CustomerReviewModel;

public class ReviewAnalysisServiceImpl extends AbstractBusinessService implements ReviewAnalysisService {
	
	private CustomerReviewService customerReviewService;
	
	public Integer getNumberOfReviews(ProductModel paramProductModel, final Double minRating, final Double maxRating) {
		List<CustomerReviewModel> reviews = customerReviewService.getReviewsForProduct(paramProductModel);;
		
		// jdk 1.8 implementation start
		Long total = reviews.parallelStream().filter(p -> p.getRating() >= minRating && p.getRating <= maxRating).count();
		return total.intValue();
		
		// jdk 1.8 implementation end
		
		// jdk 1.7 or below implementation start
		int counter = 0;
		for (CustomerReviewModel review: reviews) {
			if (review.getRating() >= minRating && review.getRating() <= maxRating) {
				counter++;
			}
		}
		return counter;
		// jdk 1.7 or below implementation end
	}
}
