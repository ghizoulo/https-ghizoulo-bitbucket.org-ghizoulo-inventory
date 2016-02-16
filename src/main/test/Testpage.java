import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import inventory.service.product.ProductManager;
import junit.framework.TestCase;

public class Testpage extends TestCase {
	 private  ProductManager service;
	 private  ClassPathXmlApplicationContext context;
	
	@BeforeClass
	public void setUp() throws Exception {
	     context = new ClassPathXmlApplicationContext("applicationContext.xml");
	     service = (ProductManager)context.getBean("simpleProductManager");
	   }
	
	@Test
	public void test_service() {
		
		 try {
			int size=service.listProducts().size();
			System.out.println("hhhh"+size);
		} catch (Exception e) {
			System.out.println("erreuuur");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
 