package com.newt.controllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.newt.ProductWareHouseMicroServiceApp;
import com.newt.controller.ProductsController;
import com.newt.entity.Products;
import com.newt.service.ProductsService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = ProductWareHouseMicroServiceApp.class)
public class ControllerTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(ControllerTest.class);
	private MockMvc mvc;

	@Autowired
	private WebApplicationContext wac;

	@Autowired
	ProductsService productService;
	@Autowired
	ProductsController productController;

	@InjectMocks
	ProductsController productControllerMock;

	@Mock
	ProductsService productServiceMock;

	@Before
	public void setUp() {
		LOGGER.debug("------------Inside the Setup method ---------------");
		MockitoAnnotations.initMocks(this);
		this.mvc=MockMvcBuilders.webAppContextSetup(this.wac).build();
		productController.setProductsService(productService);
	}
	
	@Test
	public void testGetAllProducts() throws Exception {
		Products first = new Products(1,"Car",456677.5,2);
		Products second = new Products(2,"Bike",2345.5,3);
		List<Products>  productList=Arrays.asList(first,second);
		URI url=new URI("/products/allProducts");
		MvcResult result=this.mvc.perform(get(url).contentType(MediaType.APPLICATION_JSON)
				.content(convertObjectToJsonBytes(productList)))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			    .andReturn();
		LOGGER.info( "Above getResponce");
		LOGGER.info( result.getResponse().getContentAsString());
	}
	
	@Test
	public void testGetProductsById() throws Exception  {

		String uri=new String ("/products/{productId}");
		Products product = new Products(1,"Car",456677.5,2);
		LOGGER.info("Inside the test case method ");
		
			MvcResult result=this.mvc.perform(get(uri, 1).contentType(MediaType.APPLICATION_JSON)
					.content(convertObjectToJsonBytes(product)))
					.andExpect(status().isOk())
					.andReturn();
			LOGGER.info( result.getResponse().getContentAsString());
		

	}
	
	@Test
	public void testAddProducts() throws Exception{
		Products product= new Products(2, "Car", 34566.4, 2);
		URI url=new URI("/products/add");
		LOGGER.info("===========Entering into testAddProduct Method=====================");
		
		MvcResult result1=this.mvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).content(convertObjectToJsonBytes(product)))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andReturn();
		LOGGER.info( result1.getResponse().getContentAsString());
		LOGGER.info("===========Leaving into testAddProduct Method=====================");

	}
	
	@Test
	public void testUpdateProducts() throws Exception{
		Products product= new Products(2, "Bus", 34566.4, 2);
		URI url=new URI("/products/update");
		LOGGER.info("===========Entering into testUpdateProduct Method=====================");
		
		MvcResult result2=this.mvc.perform(put(url).contentType(MediaType.APPLICATION_JSON).content(convertObjectToJsonBytes(product)))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andReturn();
		LOGGER.info( result2.getResponse().getContentAsString());
		LOGGER.info("===========Leaving into testUpdateProduct Method=====================");
	}
	
	@Test
	@SqlGroup(@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:Before.sql"))
	public void testDeleteProduct() throws Exception{
		Products product= new Products(4, "Car", 34566.4, 2);
		String url=new String("/products/Delete/{productId}");
		LOGGER.info("===========Entering into testDeleteProduct Method=====================");
		
		MvcResult result3=this.mvc.perform(delete(url,product.getProductId()).contentType(MediaType.APPLICATION_JSON).content(convertObjectToJsonBytes(product)))
				.andExpect(status().isOk())/*.andExpect(content().contentType(MediaType.APPLICATION_JSON))*/
				.andReturn();
		LOGGER.info( result3.getResponse().getContentAsString());
		LOGGER.info("===========Leaving into testDeleteProduct Method=====================");
	}
	
	@Test
	public void testGetProductsByName() throws Exception  {

		String uri=new String ("/products/ByName/{productName}");
		Products product = new Products(1,"Car",456677.5,2);
		LOGGER.info("Inside the testGetProductsByName method ");
		
			MvcResult result=this.mvc.perform(get(uri, "Bus").contentType(MediaType.APPLICATION_JSON)
					.content(convertObjectToJsonBytes(product)))
					.andExpect(status().isOk())
					.andReturn();
			LOGGER.info( result.getResponse().getContentAsString());
		

	}
	
	public static byte[] convertObjectToJsonBytes(Object object) throws JsonProcessingException {
		LOGGER.info("Inside the Convert JSON method ");

		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		return mapper.writeValueAsBytes(object);
	}
}




