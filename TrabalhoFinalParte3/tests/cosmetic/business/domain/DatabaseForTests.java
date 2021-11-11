package cosmetic.business.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import cosmetic.database.Database;

public class DatabaseForTests implements Database{
	private Map<Long,User> users;
	private Map<String,EvaluationCommittee> evaluationCommittees; 
	private Map<Long,Product> products;
	
	public DatabaseForTests() throws BusinessException {
		this.users = new HashMap<>();
		this.evaluationCommittees = new HashMap<>();
		this.products = new HashMap<>();
		initData();
		
	}
	
	public Collection<Product> getAllProducts(){
		return this.products.values();
	}
	
	public Product getProductById(Long id) {
		return this.products.get(id);
	}
	
	public User getUserById(Long id) {
		return this.users.get(id);
	}
	
	public EvaluationCommittee getEvaluationCommitteeByName(String name){
		return evaluationCommittees.get(name);
	}

	private void initData() throws BusinessException {
		State RS = new State("RS");
		State SP = new State("SP");
		State CE = new State("CE");
		
		ProductCategory BBCream = new ProductCategory("BB Cream");
		ProductCategory CCCream = new ProductCategory("CC Cream");
		ProductCategory DDCream = new ProductCategory("DD Cream");
		ProductCategory FoundationSPF = new ProductCategory("Foundation+SPF");
		ProductCategory OilFree = new ProductCategory("Oil Free Matte SPF");
		ProductCategory PowderSunscreen = new ProductCategory("Powder Sunscreen");
		
		User user1 = new User(1L,"João",RS);
		user1.addProductCategory(BBCream);
		user1.addProductCategory(CCCream);
		user1.addProductCategory(DDCream);
		
		User user2 = new User(2L,"Ana",SP);
		user2.addProductCategory(FoundationSPF);
		user2.addProductCategory(DDCream);
		user2.addProductCategory(CCCream);
		
		User user3 = new User(3L,"Manoela",RS);
		user3.addProductCategory(BBCream);
		user3.addProductCategory(OilFree);
		
		User user4 = new User(4L,"Joana",CE);
		user4.addProductCategory(BBCream);
		user4.addProductCategory(CCCream);
		user4.addProductCategory(FoundationSPF);
		user4.addProductCategory(PowderSunscreen);
		
		User user5 = new User(5L,"Miguel",RS);
		user5.addProductCategory(FoundationSPF);
		user5.addProductCategory(DDCream);
		user5.addProductCategory(OilFree);
		
		User user6 = new User(6L,"Beatriz",CE);
		user6.addProductCategory(CCCream);
		user6.addProductCategory(OilFree);
		user6.addProductCategory(PowderSunscreen);
		
		User user7 = new User(7L,"Suzana",RS);
		user7.addProductCategory(PowderSunscreen);
		user7.addProductCategory(DDCream);
		user7.addProductCategory(CCCream);
		
		User user8 = new User(8L,"Natasha",CE);
		user8.addProductCategory(DDCream);
		user8.addProductCategory(CCCream);
		user8.addProductCategory(BBCream);
		
		User user9 = new User(9L,"Pedro",SP);
		user9.addProductCategory(PowderSunscreen);
		user9.addProductCategory(FoundationSPF);
		
		User user10 = new User(10L,"Carla",SP);
		user10.addProductCategory(CCCream);
		
		users.put(user1.getId(), user1);
		users.put(user2.getId(), user2);
		users.put(user3.getId(), user3);
		users.put(user4.getId(), user4);
		users.put(user5.getId(), user5);
		users.put(user6.getId(), user6);
		users.put(user7.getId(), user7);
		users.put(user8.getId(), user8);
		users.put(user9.getId(), user9);
		users.put(user10.getId(), user10);
		
		EvaluationCommittee committee1 = new EvaluationCommittee("SPF A");
		committee1.addMember(user1);
		committee1.addMember(user2);
		committee1.addMember(user3);
		committee1.addMember(user4);
		committee1.addMember(user5);
		committee1.addMember(user6);
		committee1.addMember(user7);
		
		EvaluationCommittee committee2 = new EvaluationCommittee("SPF B");
		committee2.addMember(user1);
		committee2.addMember(user2);
		committee2.addMember(user3);
		committee2.addMember(user4);
		committee2.addMember(user5);
		committee2.addMember(user6);
		committee2.addMember(user7);
		
		EvaluationCommittee committee3 = new EvaluationCommittee("SPF C");
		committee3.addMember(user4);
		committee3.addMember(user5);
		committee3.addMember(user6);
		committee3.addMember(user7);
		committee3.addMember(user8);
		committee3.addMember(user9);
		committee3.addMember(user10);
		
		evaluationCommittees.put(committee1.getName(), committee1);
		evaluationCommittees.put(committee2.getName(), committee2);
		evaluationCommittees.put(committee3.getName(), committee3);
		
		Product product1 = new Product(1L,"L'oreal DD Cream",user1,committee3,DDCream);
		Product product2 = new Product(2L,"Avon CC Cream",user6,committee2,CCCream);
		Product product3 = new Product(3L,"Revolution Powder Sunscreen",user7,committee2,PowderSunscreen);
		Product product4 = new Product(4L,"Maybelline BB Cream",user8,committee2,BBCream);
		Product product5 = new Product(5L,"Revlon Foundation+SPF20",user9,committee2,FoundationSPF);
		Product product6 = new Product(6L,"Nivea Matte Face SPF",user10,committee2,OilFree);
		Product product7 = new Product(7L,"La Roche CC Cream",user6,committee1,CCCream);
		Product product8 = new Product(8L,"Yves Rocher Powder+SPF15",user7,committee1,PowderSunscreen);
		Product product9 = new Product(9L,"Nivea BB Cream",user8,committee1,BBCream);
		Product product10 = new Product(10L,"Base O Boticário SPF20",user9,committee1,FoundationSPF);
		Product product11 = new Product(11L,"Natura SPF20 Rosto Matte",user10,committee1,OilFree);
		
		products.put(product1.getId(), product1);
		products.put(product2.getId(), product2);
		products.put(product3.getId(), product3);
		products.put(product4.getId(), product4);
		products.put(product5.getId(), product5);
		products.put(product6.getId(), product6);
		products.put(product7.getId(), product7);
		products.put(product8.getId(), product8);
		products.put(product9.getId(), product9);
		products.put(product10.getId(), product10);
		products.put(product11.getId(), product11);
		
		Evaluation evaluation1 = new Evaluation(product1,user8,2F);
		Evaluation evaluation2 = new Evaluation(product1,user10);
		Evaluation evaluation3 = new Evaluation(product2,user7,2F);
		Evaluation evaluation4 = new Evaluation(product2,user2,3F);
		Evaluation evaluation5 = new Evaluation(product3,user4,-1F);
		Evaluation evaluation6 = new Evaluation(product3,user6,1F);
		Evaluation evaluation7 = new Evaluation(product4,user1,1F);
		Evaluation evaluation8 = new Evaluation(product4,user3,0F);
		Evaluation evaluation9 = new Evaluation(product5,user4,-3F);
		Evaluation evaluation10 = new Evaluation(product5,user5,-3F);
		Evaluation evaluation11 = new Evaluation(product6,user3,-1F);
		Evaluation evaluation12 = new Evaluation(product6,user6,0F);
		
	}
}
