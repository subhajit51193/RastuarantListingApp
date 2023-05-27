package com.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.exception.RestaurantException;
import com.app.model.Restaurant;
import com.app.repository.RestaurantRepository;
import com.app.service.RestaurantService;

@SpringBootTest
@RunWith(SpringRunner.class)
class RestaurantListingAppApplicationTests {

	@Autowired
	private RestaurantService restaurantService;
	
	@MockBean
	private RestaurantRepository restaurantRepository;
	
	
	
	@Test
	void createRestaurantWithExceptionTest() {
		
		Restaurant restaurant = new Restaurant();
		restaurant.setRestaurantId(1);
		restaurant.setName("res1");
		restaurant.setPhone("9987456321");
		restaurant.setAddress("DemoAddress");
		restaurant.setRating(0);
		
		when(restaurantRepository.save(restaurant)).thenReturn(null);
		assertThrows(RestaurantException.class, 
				() ->{
					restaurantService.addRestaurant(restaurant);
				});
	}
	@Test
	void createRestaurantWithoutExceptionTest() throws RestaurantException {
		
		Restaurant restaurant = new Restaurant();
		restaurant.setRestaurantId(1);
		restaurant.setName("res1");
		restaurant.setPhone("9987456321");
		restaurant.setAddress("DemoAddress");
		restaurant.setRating(0);
		
		when(restaurantRepository.save(restaurant)).thenReturn(restaurant);
		assertEquals(restaurant, restaurantService.addRestaurant(restaurant));
	}

}
