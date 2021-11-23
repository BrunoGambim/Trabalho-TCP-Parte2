package cosmetic.business.domain;

public class ProductCategory {
	
	private String name;

	public ProductCategory(String name) {
		this.name = name;
	}

	private String getName() {
		return name;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof ProductCategory) {
			ProductCategory productCategory = (ProductCategory) obj;
			return this.equals(productCategory.getName());
		}else {
			return false;
		}
	}
	
	private boolean equals(String name) {
		if(getName().equals(name)) {
			return true;
		}else {
			return false;
		}
	}
}
