package cosmetic.utils.comparators;

import java.util.Comparator;

import cosmetic.business.domain.BusinessException;
import cosmetic.business.domain.Product;

public class DecrescentProductComparatorByRating implements Comparator<Product>{
	@Override
	public int compare(Product o1, Product o2) {
		try {
			return -1*o1.getEvalutionsMean().compareTo(o2.getEvalutionsMean());
		} catch (BusinessException e) {
			System.out.println(e.getMessage());
		}
		return 0;
	}
}
