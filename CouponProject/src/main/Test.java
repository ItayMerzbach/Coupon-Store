package main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import Exceptions.CouponException;
import Exceptions.CouponServerException;
import clients.AdminastorFacde;
import clients.CompanyFacade;
import clients.CustomerFacade;
import clients.javabeans.Company;
import clients.javabeans.Coupon;
import clients.javabeans.CouponType;
import clients.javabeans.Customer;

public class Test {

	public static void main(String[] args) {
		
		
		CouponSystem couponSystem =CouponSystem.getCouponSystem();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		AdminastorFacde adminastorFacde = null;
		CustomerFacade customerFacade = null;
		CompanyFacade companyFacade = null;
		
		Coupon coupon = null;
		try {                             //creating first coupon
			coupon = new Coupon(673565756, "אחלה קופון",dateFormat.parse("12/03/2015"), dateFormat.parse("12/03/2018"), 2, "RESTURANS", " קופון ממש ממש טוב", 12.54, "gfhdfjfg");
		} catch (ParseException e2) {
		}
		Coupon coupon2 = null;
		try {                              //creating second coupon
			coupon2 = new Coupon(342235234, "good coupon from jon brais", dateFormat.parse("12/04/2015"), dateFormat.parse("12/06/2018"), 2, "RESTURANS", " קופון  ממש טוב", 122.32, "gfhdfjfg");
		} catch (ParseException e2) {
		}
		                                    //creating companies and customers
		Company company = new Company(673565756, "asd", "1234", "fdgsdhf@fhhfh");
		Company company2 = new Company(342235234, "jonbrais", "4444", "fdfghgsdhf@fhhfh");
		Company company5 = new Company(275645756, "goodCompany", "1234", "dgadf");
		Customer customer = null;
		
		try {
			customer = new Customer(1234, "itay", "1234");
		} catch (CouponServerException e2) {
			System.out.println(e2.getMessage());
		}
		

		try {                             //login as admin
			adminastorFacde = (AdminastorFacde) couponSystem.login("admin", "1234", "ADMINASTOR");
		} catch (CouponException|CouponServerException e) {
			System.out.println(e.getMessage());
		} 
		
		                        //adding companies and customers to Database
		 try {
			adminastorFacde.addCompany(company);
		} catch (CouponServerException|CouponException e1) {
			System.out.println(e1.getMessage());
		}
		 try {
			adminastorFacde.addCompany(company2);
		} catch (CouponServerException|CouponException e1) {
			System.out.println(e1.getMessage());
		}
		 try {
				adminastorFacde.addCompany(company5);
			} catch (CouponServerException|CouponException e1) {
				System.out.println(e1.getMessage());
			}
		 try {
			adminastorFacde.addCustomer(customer);
		} catch (CouponServerException|CouponException e1) {
			System.out.println(e1.getMessage());
		}


		 try {
				adminastorFacde.addCustomer(customer);
			} catch (CouponServerException|CouponException e1) {
				System.out.println(e1.getMessage());
			}
		 
		
			
		 try {
			System.out.println();
			 System.out.println("try to connect to customer facade with worng password");
				customerFacade = (CustomerFacade)couponSystem.login("itay", "1111", "CUSTOMERS");
			} catch (CouponException|CouponServerException e) {
				System.out.println(e.getMessage());
			} 
				
		 try {
				System.out.println();
			 System.out.println("try to connect to customer facade with correct password");
			 customerFacade = (CustomerFacade)couponSystem.login("itay", "1234", "CUSTOMERS");
			} catch (CouponException|CouponServerException e) {
				System.out.println(e.getMessage());
			} 
			
		
			
		 try {
				System.out.println();
				System.out.println("try to connect to company facade with worng password");
			 companyFacade = (CompanyFacade) couponSystem.login("jonbrais", "1234", "COMPANY");
			} catch (CouponException|CouponServerException e) {
				System.out.println(e.getMessage());
			} 
		
		 try {
				System.out.println();
				System.out.println("try to connect to company facade with correct password");
			 companyFacade = (CompanyFacade) couponSystem.login("jonbrais", "4444", "COMPANY");
			} catch (CouponException|CouponServerException e) {
				System.out.println(e.getMessage());
			} 
			
		 try {
				System.out.println();
				System.out.println("adding 2 coupons to Database");	
			 companyFacade.addCoupon(coupon2);
			 System.out.println("succeed");	
			} catch (CouponException e) {
			System.out.println(e.getMessage());
			} catch (CouponServerException e) {
				System.out.println(e.getMessage());
			}
			
		
		 try {
				companyFacade.addCoupon(coupon);
				 System.out.println("succeed");	
			} catch (CouponException e) {
				System.out.println(e.getMessage());
			} catch (CouponServerException e) {
				System.out.println(e.getMessage());
			}
			
			
		 try {
				System.out.println();
				System.out.println("try to connect to company facade of "+company5.getCompName()+" with correct password");
			 companyFacade = (CompanyFacade) couponSystem.login(company5.getCompName(), company5.getPassword(), "COMPANY");
			} catch (CouponException|CouponServerException e) {
				System.out.println(e.getMessage());
			} 
		 List<Coupon> coupons = null;
			try {
				System.out.println();
				System.out.println("getting all coupons of: "  + company5.getIdCompany()+" from Database");	
				coupons = companyFacade.getAllCoupons();
				printCoupons(coupons);
			} catch (CouponServerException e) {
				e.printStackTrace();
			}
			
			try {
				System.out.println();
				System.out.println("get coupon by price");	
				coupons = companyFacade.getCouponsByMaxPrice(15.33);
				System.out.println("print all coupons of that company "+   company5.getIdCompany() + " where there price is lower then 15.33");
				printCoupons(coupons);
			} catch (CouponServerException e) {
				e.printStackTrace();
			}
				try {
					System.out.println();
					System.out.println("get coupons by type");	
					coupons = companyFacade.getCouponsByType(CouponType.SPORTS);
				} catch (CouponServerException e1) {
					e1.printStackTrace();
				}
				
				System.out.println();
				System.out.println("print all coupon of company"+ company5.getIdCompany() +" in sports");
				printCoupons(coupons);
				
			                                // get all coupns from DB by date ,after the given one.
			try {
				coupons = companyFacade.getCouponsAfterYourDate(dateFormat.parse("12/05/2018"));
				System.out.println("print all coupon of company: " + company5.getIdCompany() +" that end date after 12/05/2018");
				printCoupons(coupons);
				
			} catch (CouponException | CouponServerException | ParseException e) {
				System.out.println(e.getMessage());
			}
			
			try {
				System.out.println();
				coupons = companyFacade.getAllCoupons();
				System.out.println("print all coupon of company: " + company5.getIdCompany());
				printCoupons(coupons);
				
			} catch (CouponServerException  e) {
				System.out.println(e.getMessage());
			}
			
			
			System.out.println();
			System.out.println("updating a coupon");
			System.out.println();
			coupons.get(0).setTitle("update coupon");
			try {
				companyFacade.updateCoupon(coupons.get(0));
				System.out.println("succeed");
			} catch (CouponServerException | CouponException e) {
				System.out.println(e.getMessage());
			}
                                       // purchasing coupons by there array index.
			System.out.println();
			System.out.println("purchasing coupons by there array index");
			try {customerFacade.purchaseCoupon(coupons.get(1));
				System.out.println("succeed");
			} catch (CouponException | CouponServerException e) {
				System.out.println(e.getMessage());
			}
		 
				
			try {
				customerFacade.purchaseCoupon(coupons.get(2));
				System.out.println("succeed");
			} catch (CouponException | CouponServerException e) {
				System.out.println(e.getMessage());
			}
			
			try {
				customerFacade.purchaseCoupon(coupons.get(3));
				System.out.println("succeed");
			} catch (CouponException | CouponServerException e) {
				System.out.println(e.getMessage());
			}
			try {
				customerFacade.purchaseCoupon(coupons.get(0));
				System.out.println("succeed");
			} catch (CouponException | CouponServerException e) {
				System.out.println(e.getMessage());
			}try {
				customerFacade.purchaseCoupon(coupons.get(3));
				System.out.println("succeed");
			} catch (CouponException | CouponServerException e) {
				System.out.println(e.getMessage());
			}try {
				customerFacade.purchaseCoupon(coupons.get(4));
				System.out.println("succeed");
			} catch (CouponException | CouponServerException e) {
				System.out.println(e.getMessage());
			}try {
				customerFacade.purchaseCoupon(coupons.get(5));
				System.out.println("succeed");
			} catch (CouponException | CouponServerException e) {
				System.out.println(e.getMessage());
			}
			
			//try to buy coupon that not exist
			System.out.println();
			System.out.println("try to buy coupon that not exist");
			coupons.add(coupon);
			try {
				customerFacade.purchaseCoupon(coupons.get(coupons.size()-1));
				System.out.println("succeed");
			} catch (CouponException | CouponServerException e) {
				System.out.println(e.getMessage());
			}
			
			coupons = customerFacade.getAllPurchaseCoupons();
			System.out.println();
			System.out.println("get All Purchase Coupons");
			printCoupons(coupons);
			
			
			coupons = customerFacade.getAllpurchaseCouponsByPrice(200);
			System.out.println();
			System.out.println("get All purchase Coupons By Price");
			printCoupons(coupons);

			coupons = customerFacade.getAllpurchaseCouponsSortByType();
			System.out.println();
			System.out.println("get All purchase Coupons Sort By Type");
			printCoupons(coupons);
			
			// Administer facade test:
			System.out.println();
			System.out.println("Administer facade test:");
			System.out.println();
			System.out.println("update Email:");
			company2.setEmail("updateEmail");
			try {
				adminastorFacde.updateCompany(company2);
				System.out.println("succeed");
			} catch (CouponServerException | CouponException e1) {
				System.out.println(e1.getMessage());
			}
			
			// try to update company that not exist
			System.out.println();
			System.out.println("try to update company that not exist");
			try {
				adminastorFacde.updateCompany(new Company(123333, "try", "trytry", "blabla"));
				System.out.println("succeed");
			} catch (CouponServerException | CouponException e1) {
				System.out.println(e1.getMessage());
			}
			
			try {
				System.out.println();
				System.out.println("printAllCompanies: ");
				printAllCompanies(adminastorFacde);
			} catch (CouponServerException e1) {
				System.out.println(e1.getMessage());
			}
			try {
				System.out.println();
				System.out.println("printAllCustomers: ");
				printAllCustomers(adminastorFacde);
			} catch (CouponServerException | CouponException e1) {
				System.out.println(e1.getMessage());
			}
			
		
			
			System.out.println();
			System.out.println("remove Company: " + company2.getCompName());
			try {
				adminastorFacde.removeCompany(company2);
			} catch (CouponServerException | CouponException e1) {
				System.out.println(e1.getMessage());
			}
		

			//remove company that not exist in the program:
			try {
				System.out.println();
				System.out.println("remove company that not exist in the program:");
				adminastorFacde.removeCompany(new Company(123333, "try", "trytry", "blabla"));
				System.out.println("succeed");

			} catch (CouponServerException | CouponException e1) {
				System.out.println(e1.getMessage());
			}
			
			try {
				System.out.println();
				System.out.println("getCompany and printCompany");
				Company company3 = adminastorFacde.getCompany(275645756);
				printCompany(company3);
			} catch (CouponServerException | CouponException e1) {
				System.out.println(e1.getMessage());
			}
			
			try {
				System.out.println();
				System.out.println("getCustomer and printCustomer");
				Customer customer2 = adminastorFacde.getCustomer(1234);
				printCustomer(customer2);
			} catch (CouponServerException e1) {
				System.out.println(e1.getMessage());
			}
			
			try {
				System.out.println();
				System.out.println("removeCustomer " + customer.getCustName());
				adminastorFacde.removeCustomer(customer);
			} catch (CouponServerException e) {
				System.out.println(e.getMessage());}

			
			
			try {
				System.out.println();
				System.out.println("shutdown thr system");
				couponSystem.shutdown();
			} catch (CouponServerException e) {
				System.out.println(e.getMessage());
				}
			
		
	}

	private static void printCustomer(Customer customer) {
		System.out.println("**********************");
		System.out.print(customer.getIdCustomer()+ ": ");
		System.out.println("customer name: : "+ customer.getCustName());
		System.out.println("**********************");
		System.out.println();		
	}

	private static void printCompany(Company company) {
		System.out.println("**********************");
		System.out.print(company.getIdCompany()+": ");
		System.out.print("company name: " + company.getCompName());
		System.out.println(". email: " + company.getEmail());
		System.out.println("**********************");
		System.out.println();			
	}

	private static void printAllCustomers(AdminastorFacde adminastorFacde) throws CouponServerException, CouponException {
		for(Customer customer  : adminastorFacde.getAllCustomers()) {
			printCustomer(customer);
	
		}		
	}

	private static void printAllCompanies(AdminastorFacde adminastorFacde) throws CouponServerException {
		for(Company company : adminastorFacde.getAllCompanies()) {
			printCompany(company);
		}
	}

	private static void printCoupons(List<Coupon> coupons) {
		
		for (Coupon coupon3 : coupons) {
			System.out.println("**********************");
			System.out.println("id: " + coupon3.getIdCoupon());
			System.out.println("title: "+ coupon3.getTitle());
			System.out.println("price: " + coupon3.getPrice());
			System.out.println("type: " + coupon3.getCouponType().toString());
			System.out.println("IdCompany: " + coupon3.getIdCompany());
			System.out.println("**********************");
			System.out.println();

		}		
	}
	

}
