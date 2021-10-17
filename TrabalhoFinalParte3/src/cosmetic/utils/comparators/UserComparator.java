package cosmetic.utils.comparators;

import java.util.Comparator;

import cosmetic.business.domain.User;

public class UserComparator implements Comparator<User>{

	@Override
	public int compare(User o1, User o2) {
		if(o1.getNumberOfEvaluations() > o2.getNumberOfEvaluations()) {
			return 1;
		}else if(o1.getId() > o2.getId() && o1.getNumberOfEvaluations() == o2.getNumberOfEvaluations()) {
			return 1;
		}else {
			return -1;
		}
	}

}
