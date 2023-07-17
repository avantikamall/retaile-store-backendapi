package com.wipro.retailStore.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.retailStore.DTO.CustomerDTO;
import com.wipro.retailStore.DTO.InventoryDTO;
import com.wipro.retailStore.DTO.OrderDTO;
import com.wipro.retailStore.entity.Cart;
import com.wipro.retailStore.entity.Customer;
import com.wipro.retailStore.entity.CustomerAddress;
import com.wipro.retailStore.entity.Inventory;
import com.wipro.retailStore.entity.LineItem;
import com.wipro.retailStore.entity.Orders;
import com.wipro.retailStore.entity.Product;
import com.wipro.retailStore.mapping.CustCartMapping;
import com.wipro.retailStore.mapping.CustomerOrderDetails;
import com.wipro.retailStore.mapping.OrderCustomer;
import com.wipro.retailStore.mapping.ProductInventoryMapping;
import com.wipro.retailStore.services.CartService;
import com.wipro.retailStore.services.CustomerAddressService;
import com.wipro.retailStore.services.CustomerService;
import com.wipro.retailStore.services.InventoryService;
import com.wipro.retailStore.services.LineItemService;
import com.wipro.retailStore.services.OrderService;
import com.wipro.retailStore.services.ProductService;

@RestController
@RequestMapping("/api/shoppingservice")
public class ShippingController {

	@Autowired
	ProductService pservice;
	
	@Autowired
	InventoryService iservice;
	
	@Autowired
	CustomerService cservice;
	
	@Autowired 
	CustomerAddressService caddservcie;
	
	@Autowired
	CartService cartservice;
	
	@Autowired
	LineItemService lservice;
	
	@Autowired
	OrderService oservcie;
	
	
	
	@PostMapping("/products")
	public ResponseEntity<InventoryDTO> addInventory(@RequestBody ProductInventoryMapping pim){
		try {
			Product p = new Product();
			p.setProductName(pim.getProductName());
			p.setProductDescription(pim.getProductDescription());
			p.setProductPrice(pim.getProductPrice());
			
			Product newprod = pservice.addProduct(p);
			Inventory inv  = new Inventory();
			inv.setProductId(newprod.getProductId());
			inv.setQuantity(pim.getQuantity());
			Inventory newinv = iservice.addInventory(inv);
			
			InventoryDTO result  = new InventoryDTO("Created Successfully!", newinv, newprod);
			
			return ResponseEntity.status(201).body(result);
		}catch(Exception e){
			InventoryDTO exc = new InventoryDTO();
			exc.setMessage(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exc);
		}
	}
	
	
	@PostMapping("/customer")
	public ResponseEntity<CustCartMapping> addCustCart(@RequestBody CustomerDTO cust ){
		try {
			CustCartMapping result = new CustCartMapping();
			if(	cust.getBillingAddress().getCity().equalsIgnoreCase(cust.getShippingAddress().getCity())&& 
					cust.getBillingAddress().getDoorNo().equalsIgnoreCase(cust.getShippingAddress().getDoorNo()) &&
					cust.getBillingAddress().getLayout().equalsIgnoreCase(cust.getShippingAddress().getLayout()) &&
					cust.getBillingAddress().getStreetName().equalsIgnoreCase(cust.getShippingAddress().getStreetName()) &&
					cust.getBillingAddress().getPincode() ==cust.getShippingAddress().getPincode()
					)
			{

				CustomerAddress sa = caddservcie.addAddress(cust.getShippingAddress());
				Customer nc = new Customer(cust.getCustId(),cust.getCustName(),cust.getCustEmail(),sa.getAddressId(),sa.getAddressId());
				Customer c = cservice.addCustomer(nc);
				Cart newCart = new Cart();
				newCart.setCustId(c.getCustId());
				Cart cart = cartservice.addcart(newCart);
				result = new CustCartMapping("Customer Added Successfully", c.getCustId(), cart.getCartId());
			}
			else {
			
			CustomerAddress ba = caddservcie.addAddress(cust.getBillingAddress());
			CustomerAddress sa = caddservcie.addAddress(cust.getShippingAddress());
			Customer nc = new Customer(cust.getCustId(),cust.getCustName(),cust.getCustEmail(),ba.getAddressId(),sa.getAddressId());
			Customer c = cservice.addCustomer(nc);
			Cart newCart = new Cart();
			newCart.setCustId(c.getCustId());
			Cart cart = cartservice.addcart(newCart);
			result = new CustCartMapping("Customer Added Successfully", c.getCustId(), cart.getCartId());
			}			
			return ResponseEntity.status(HttpStatus.CREATED).body(result);
		}catch (Exception e) {
			CustCartMapping exc = new CustCartMapping() ;
			exc.setMessage(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exc);
		}
	}
	
	@PutMapping("/customer/{cid}/cart")
	public ResponseEntity<Cart> putlineitem(@PathVariable("cid") long cid,@RequestBody List<LineItem> l){
		try {
		if(cartservice.searchCartbycustId(cid)!=null) {
			Cart c= cartservice.searchCartbycustId(cid);
			List<Integer> lid = new ArrayList<>();
			if(c.getLineItemId()!=null) {
				
				lid.addAll(c.getLineItemId());
			}
			for(int i=0; i<l.size(); i++) {
				
				Inventory inv = iservice.getInventoryByProductId(l.get(i).getProductId());
				if(l.get(i).getQuantity()<= inv.getQuantity()) {
					Product p = pservice.searchProduct(l.get(i).getProductId());
					LineItem newItem = new LineItem();
					newItem.setPrice(p.getProductPrice());
					newItem.setProductId(l.get(i).getProductId());
					newItem.setProductName(l.get(i).getProductName());
					newItem.setQuantity(l.get(i).getQuantity());
					
					LineItem l2 = lservice.additem(newItem);
					
					lid.add(l2.getItemId());
					
				}else {
					System.out.println("Out of Stock!!");
				}
			}
			
			Cart newcart = new Cart(c.getCartId(), cid, lid);
			Cart update = cartservice.updateCart(newcart);
			System.out.println(update.getLineItemId());
			return ResponseEntity.status(200).body(update);
			
		}else {
			return ResponseEntity.status(204).body(null);
		}
		}catch(Exception e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		
	}
	
	
	
	@PostMapping("/customer/{id}/order")
	public ResponseEntity<OrderCustomer> makeorder(@PathVariable("id") long cId){

		if(cartservice.searchCartbycustId(cId) != null) {
			Cart c = cartservice.searchCartbycustId(cId);
			System.out.println(c);
			if(c.getLineItemId()!=null) {
				
				List<Integer> l1 = c.getLineItemId();
			
			
			for(int j=0; j<l1.size(); j++) {
			   	LineItem itemqty = lservice.searchItem(l1.get(j));
			   	int productid = itemqty.getProductId();
			   	Inventory inv = iservice.getInventoryByProductId(productid);
			   	int invq = inv.getQuantity()-itemqty.getQuantity();
				Inventory nInv = new Inventory();
				nInv.setInventoryId(inv.getInventoryId());
				nInv.setProductId(inv.getProductId());
				nInv.setQuantity(invq);
				iservice.updateInventory(nInv);
			}
			
			Orders ord = new Orders();
			ord.setCustId(cId);
			ord.setLineItemId(l1);
			
			Orders order = oservcie.addOrder(ord);
			cartservice.emptyCart(c.getCartId());
			
			OrderCustomer result = new OrderCustomer("Order Created!",order.getOrderId(), order.getCustId());
			return ResponseEntity.status(200).body(result);
			}
			else {
				OrderCustomer result = new OrderCustomer();
				result.setMessage("Cart is empty!");
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
			}
		}
		else {
			OrderCustomer result = new OrderCustomer();
			result.setMessage("Cart is not tagged to the customer!!");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
		}
	}
	
	
	@GetMapping("/customer/{id}/orders")
	public ResponseEntity<CustomerOrderDetails> allOrder(@PathVariable("id") long id) {
		try {
			
		Customer cust = cservice.getCustById(id);
		Orders od = oservcie.searcOrderByCustId(id);
		List<LineItem> items = new ArrayList<>();
		if(od.getLineItemId()!=null) {
			List<Integer> itemId = od.getLineItemId();
			for(int i=0; i<itemId.size(); i++) {
				LineItem item = lservice.searchItem(itemId.get(i));
				items.add(item);
			}
		}
		
		CustomerAddress ba = caddservcie.getAddressById(cust.getBillingAddId());
		CustomerAddress sa = caddservcie.getAddressById(cust.getShippingAddId());
		
		CustomerDTO cdto = new CustomerDTO("Customer Details",id,cust.getCustName(),cust.getCustEmail(),ba,sa);
		OrderDTO odto = new OrderDTO("Orders Details",od.getOrderId(),items);
		
		CustomerOrderDetails result = new CustomerOrderDetails("Customer Details With Order", cdto,odto);
		return ResponseEntity.status(HttpStatus.OK).body(result);
		}catch(Exception e) {
			CustomerOrderDetails exc = new CustomerOrderDetails("Not Found!!",  null, null);
			return ResponseEntity.status(HttpStatus.OK).body(exc);
		}
	}
}
