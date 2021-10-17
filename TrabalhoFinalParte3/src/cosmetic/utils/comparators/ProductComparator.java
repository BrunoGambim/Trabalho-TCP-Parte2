package cosmetic.utils.comparators;

import java.util.Comparator;

import cosmetic.business.domain.Product;

public class ProductComparator implements Comparator<Product>{

	@Override
	public int compare(Product o1, Product o2) {
		return o1.getId().compareTo(o2.getId());
	}

}
