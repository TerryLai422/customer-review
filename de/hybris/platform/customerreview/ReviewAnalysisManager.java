package de.hybris.platform.customerreview;

import java.util.List;

import de.hybris.platform.customerreview.jalo.CustomerReview;
import de.hybris.platform.customerreview.jalo.ExtensionManager;
import de.hybris.platform.customerreview.jalo.Product;

public class ReviewAnalysisManager {
	public static ReviewAnalysisManager getInstance() {
		ExtensionManager extensionManager = JaloSession.getCurrentSession().getExtensionManager();
		return (CustomerReviewManager) extensionManager.getExtension("reviewanalysis");
	}
	
	public Integer getNumberOfReviews(Product item, Double minRating, Double maxRating) {
		List<CustomerReview> reviews = CustomerReviewManager.getInstance().getAllReviews(item);
		
		// jdk 1.8 implementation start
		Long total = reviews.parallelStream().filter(p -> p.getRating() >= minRating && p.getRating <= maxRating).count();
		return total.intValue();
		
		// jdk 1.8 implementation end
		
		// jdk 1.7 or below implementation start
		int counter = 0;
		for (CustomerReview review: reviews) {
			if (review.getRating() >= minRating && review.getRating() <= maxRating) {
				counter++;
			}
		}
		return counter;
		// jdk 1.7 or below implementation end
	}
}
