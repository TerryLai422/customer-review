package de.hybris.platform.customerreview;

import de.hybris.platform.core.model.product.ProductModel;

public abstract interface ReviewAnalysisService {
	  public abstract Integer getNumberOfReviews(ProductModel paramProductModel, Double minRating, Double maxRating);
}
