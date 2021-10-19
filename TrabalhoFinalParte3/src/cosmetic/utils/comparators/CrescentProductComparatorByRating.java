package cosmetic.utils.comparators;

import java.util.Comparator;

import cosmetic.business.domain.BusinessException;
import cosmetic.business.domain.Product;

public class CrescentProductComparatorByRating implements Comparator<Product>{
	
		@Override
		public int compare(Product o1, Product o2) {
			try {
				return o1.getEvalutionsMean().compareTo(o2.getEvalutionsMean());
			} catch (BusinessException e) {
				System.out.println(e.getMessage());
			}
			return 0;
		}
		
}
