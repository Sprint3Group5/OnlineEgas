package com.egas.dealer.controller;


import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.RequestBody;
import com.egas.dealer.Controller.DealerController;
import com.egas.dealer.entity.CustomerNewConnection;
import com.egas.dealer.entity.Dealer;
import com.egas.dealer.repository.DealerRepository;
import com.egas.dealer.service.IDealerServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;






@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class DealerControllerTest {

	private static final ObjectMapper om = new ObjectMapper();
	
	@Autowired
	 private MockMvc mockMvc;
	 
	@MockBean
	private IDealerServiceImpl dealerService;

	 @MockBean
	 private DealerRepository dealerRepository;
	 
	 @Test
	    public void viewCustConections_OK() throws Exception {
		 DealerController d=new DealerController();
		 d.pancardNumberLogin="ABCDE1234J";
		 List<CustomerNewConnection> customers = Arrays.asList(
				new CustomerNewConnection("ABHII7856P","Abhi","Patil","Maharashtra","Thane","Chakki Naka, Thane","8990876789"),
				new CustomerNewConnection("SHAIL7856Y","Shaily","Pandey","Maharashtra","Thane","parsikh Naka, Thane","7990871234"));
		        
		 		when(dealerService.getAllCustomerConnections()).thenReturn(customers);
		        
		        mockMvc.perform(MockMvcRequestBuilders.get("/dealers/viewCustomerConnections"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(hasSize(2)))
                .andExpect(jsonPath("$[0].custPancard").value("ABHII7856P"))
                .andExpect(jsonPath("$[0].custFirstName").value("Abhi"))
                .andExpect(jsonPath("$[0].custLastName").value("Patil"))
                .andExpect(jsonPath("$[0].custState").value("Maharashtra"))
                .andExpect(jsonPath("$[0].custCity").value("Thane"))
                .andExpect(jsonPath("$[0].custAddress").value("Chakki Naka, Thane"))
                .andExpect(jsonPath("$[0].custContact").value("8990876789"))
                .andExpect(jsonPath("$[1].custPancard").value("SHAIL7856Y"))
                .andExpect(jsonPath("$[1].custFirstName").value("Shaily"))
                .andExpect(jsonPath("$[1].custLastName").value("Pandey"))
                .andExpect(jsonPath("$[1].custState").value("Maharashtra"))
                .andExpect(jsonPath("$[1].custCity").value("Thane"))
                .andExpect(jsonPath("$[1].custAddress").value("parsikh Naka, Thane"))
                .andExpect(jsonPath("$[1].custContact").value("7990871234")); 
        verify(dealerService, times(1)).getAllCustomerConnections();
	 }
	 
	/* @Test
	 public void register_OK() throws Exception {
		// dealerService.addDealer(dealer);
		Dealer dealer=new Dealer("ABCDE1234J","Shivani","Manchewar","Female","shivani@gmail.com","7806173900","shiva","shiva","Thane","Maharashtra");
		//when(dealerService.addDealer(dealer)).thenReturn(dealer);
		when(dealerRepository.save(any(Dealer.class))).thenReturn(newDealer); 
		 
	 }	 */
/*	 @Test
	    public void save_book_OK() throws Exception {

	        Book newBook = new Book(1L, "Spring Boot Guide", "mkyong", new BigDecimal("2.99"));
	        when(mockRepository.save(any(Book.class))).thenReturn(newBook);

	        mockMvc.perform(post("/books")
	                .content(om.writeValueAsString(newBook))
	                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
	                //.andDo(print())
	                .andExpect(status().isCreated())
	                .andExpect(jsonPath("$.id", is(1)))
	                .andExpect(jsonPath("$.name", is("Spring Boot Guide")))
	                .andExpect(jsonPath("$.author", is("mkyong")))
	                .andExpect(jsonPath("$.price", is(2.99)));

	        verify(mockRepository, times(1)).save(any(Book.class));

	    } 
	 */	 
	 
/*	  @Test
	    public void patch_bookAuthor_OK() throws Exception {

	        when(mockRepository.save(any(Book.class))).thenReturn(new Book());
	        String patchInJson = "{\"author\":\"ultraman\"}";

	        mockMvc.perform(patch("/books/1")
	                .content(patchInJson)
	                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
	                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
	                .andExpect(status().isOk());

	        verify(mockRepository, times(1)).findById(1L);
	        verify(mockRepository, times(1)).save(any(Book.class));

	    }*/
/*	  @Test
	    public void update_ConectionStatus_OK() throws Exception {

		Map<String, String> updateData = new HashMap<String, String>();
		updateData.put("custPancard", "ABHII7856P");
		updateData.put("custConnectionStatus", "Approved");
		CustomerConnection customer= new CustomerConnection("ABHII7856P","Abhi","Patil","Maharashtra","Thane","Chakki Naka, Thane","8990876789");
		customer.setCustConnectionStatus(updateData.get("custConnectionStatus"));
		when(dealerService.saveConnectionStatus(updateData)).thenReturn(customer); 
		//String patchInJson = "{\"custPancard\":\"ABHII7856P\",\"custConnectionStatus\":\"Approved\"}";
		*
		 * HashMap<String, Object> updates = new HashMap<>(); updates.put("address",
		 * "5th avenue");
		 * 
		 * mockMvc.perform(patch("/heavyresource/1")
		 * .contentType(MediaType.APPLICATION_JSON_VALUE)
		 * .content(objectMapper.writeValueAsString(updates))
		 * ).andExpect(status().isOk());
		 *
		System.out.println(customer);
	        mockMvc.perform(MockMvcRequestBuilders.patch("/dealers/changeConnectionStatus")
	        		.contentType(MediaType.APPLICATION_JSON_VALUE)
	        		  .content(om.writeValueAsString(updateData))
	        		//.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
	                //.andExpect(content().contentType(MediaType.APPLICATION_JSON))
	                ).andExpect(status().isOk())
	               // .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
	               // .andExpect(content().contentType(MediaType.APPLICATION_JSON))
	               // .andExpect(status().is4xxClientError())
	             //   .andExpect(jsonPath("$.custId").value(1))
	                .andExpect(jsonPath("$.custPancard").value("ABHII7856P"))
	                .andExpect(jsonPath("$.custFirstName").value("Abhi"))
	                .andExpect(jsonPath("$.custLastName").value("Patil"))
	                .andExpect(jsonPath("$.custState").value("Maharashtra"))
	                .andExpect(jsonPath("$.custCity").value("Thane"))
	                .andExpect(jsonPath("$.custAddress").value("Chakki Naka, Thane"))
	                .andExpect(jsonPath("$.custContact").value("8990876789"))
	                .andExpect(jsonPath("$.custConnectionStatus").value("Approved"));
	              .andExpect(jsonPath("$.id", is(1)))
	                .andExpect(jsonPath("$.name", is("ABC")))
	                .andExpect(jsonPath("$.author", is("mkyong")))
	                .andExpect(jsonPath("$.price", is(19.99)));

	      //  verify(dealerService, times(1)).saveConnectionStatus(updateData);
	    }
*/
	@Test
    public void viewDealers_OK() throws Exception {

    List<Dealer> dealers = Arrays.asList(
    		new Dealer("ABCDE1234J","Shivani","Manchewar","Female","shivani@gmail.com","7806173900","shiva","shiva","Thane","Maharashtra"),
      		new Dealer("PQRS4567T","Gayatri","Manchewar","Female","gayatri@gmail.com","8806137600","gayatri","gayatri","Mulund","Maharashtra"));
        when(dealerService.getAllDealers()).thenReturn(dealers);
        mockMvc.perform(MockMvcRequestBuilders.get("/dealers/viewDealers"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(hasSize(2)))
                .andExpect(jsonPath("$[0].pancardNumber").value("ABCDE1234J"))
                .andExpect(jsonPath("$[0].firstName").value("Shivani"))
                .andExpect(jsonPath("$[0].lastName").value("Manchewar"))
                .andExpect(jsonPath("$[0].gender").value("Female"))
                .andExpect(jsonPath("$[0].email").value("shivani@gmail.com"))
                .andExpect(jsonPath("$[0].contactNumber").value("7806173900"))
                .andExpect(jsonPath("$[0].password").value("shiva"))
                .andExpect(jsonPath("$[0].confirmPassword").value("shiva"))
                .andExpect(jsonPath("$[0].city").value("Thane"))
                .andExpect(jsonPath("$[0].state").value("Maharashtra"))
                .andExpect(jsonPath("$[0].dealerStatus").value("Pending"))
                .andExpect(jsonPath("$[1].pancardNumber").value("PQRS4567T"))
                .andExpect(jsonPath("$[1].firstName").value("Gayatri"))
                .andExpect(jsonPath("$[1].lastName").value("Manchewar"))
                .andExpect(jsonPath("$[1].gender").value("Female"))
                .andExpect(jsonPath("$[1].email").value("gayatri@gmail.com"))
                .andExpect(jsonPath("$[1].contactNumber").value("8806137600"))
                .andExpect(jsonPath("$[1].password").value("gayatri"))
                .andExpect(jsonPath("$[1].confirmPassword").value("gayatri"))
                .andExpect(jsonPath("$[1].city").value("Mulund"))
                .andExpect(jsonPath("$[1].state").value("Maharashtra"))
                .andExpect(jsonPath("$[1].dealerStatus").value("Pending")); 
        verify(dealerService, times(1)).getAllDealers();
    }
	

}
