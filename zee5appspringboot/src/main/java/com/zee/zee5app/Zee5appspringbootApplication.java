package com.zee.zee5app;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.naming.InvalidNameException;
import javax.naming.NameNotFoundException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.zee.zee5app.dto.Episodes;
import com.zee.zee5app.dto.Movies;
import com.zee.zee5app.dto.Register;
import com.zee.zee5app.dto.Series;
import com.zee.zee5app.dto.Subscriptions;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidAmountException;
import com.zee.zee5app.exception.InvalidEmailException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.exception.InvalidPasswordException;
import com.zee.zee5app.exception.LocationNotFound;
import com.zee.zee5app.service.EpisodeService;
import com.zee.zee5app.service.MovieService;
import com.zee.zee5app.service.SeriesService;
import com.zee.zee5app.service.SubscriptionService;
import com.zee.zee5app.service.UserService;

@SpringBootApplication
public class Zee5appspringbootApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationcontext = SpringApplication.run(Zee5appspringbootApplication.class, args);
		
//		DataSource dataSource = applicationcontext.getBean(DataSource.class);
//		System.out.println(dataSource != null);
		
//-----------------Register and Login Transactions-----------------------------------------------------------------		
		UserService service = applicationcontext.getBean(UserService.class);
		//Inserting at login and register
		try {
			Register register = new Register("rush001", "Rushik1", "kumar1", "rushik@gmail.com1", "12345611", new BigDecimal("1234567811"));		
			System.out.println(service.addUser(register));
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//getting user by id
		System.out.println("-------------------------------------------------------------------------------------------------------");
		try {
			Optional<Register> optional = service.getUserById("rush001");
			System.out.println(optional.get());
		} catch (InvalidNameException | IdNotFoundException | InvalidIdLengthException | InvalidEmailException
				| InvalidPasswordException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("-------------------------------------------------------------------------------------------------------");
		
		//get all user details
		System.out.println("-------------------------------------------------------------------------------------------------------");
		try {
			Optional<List<Register>> optional = service.getAllUserDetails();
			if(optional.isEmpty()) {
				System.out.println("There are no records");
			}
			else {
				optional.get().forEach(e -> System.out.println(e));
			}
		} catch (InvalidNameException | InvalidIdLengthException | InvalidEmailException
				| InvalidPasswordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("-------------------------------------------------------------------------------------------------------");
		
		//updating register
		Register register = new Register("rush001", "rushik1U", "kumar1U", "rushik@gmail.com10", "123456111", new BigDecimal("1234567810"));
		System.out.println(service.updateUser(register.getId(), register));
		
		//Deleting in Register and Login
//		try {
//			System.out.println(service.deleteUserById("rush001"));
//		} catch (IdNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//--------------------------Movies Transactions------------------------------------------
		MovieService movieservice = applicationcontext.getBean(MovieService.class);
				//inserting
				Movies movie = new Movies("mov006", "sanju", "Drama", "2019-02-12", "/location/06", "Hindhi", "Ranbir", 128, 13);
				System.out.println(movieservice.addMovie(movie));
						
				//Getting all movies
				System.out.println("-------------------------------------------------------------------------------------------------------");
					try {
						Optional<List<Movies>> optional = movieservice.getMovies();
						if(optional.isEmpty()) {
							System.out.println("There are no records");
						}
						else {
							optional.get().forEach(e -> System.out.println(e));
						}
					} catch (NameNotFoundException | LocationNotFound e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.out.println("-------------------------------------------------------------------------------------------------------");
				//getting a movie by id
					System.out.println("-------------------------------------------------------------------------------------------------------");
				try {
					Optional<Movies> optional = movieservice.getMovieById("mov0006");
					System.out.println(optional);
				} catch (NameNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (LocationNotFound e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IdNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("-------------------------------------------------------------------------------------------------------");
				
				//updating
				Movies movie2 = new Movies("mov006","sanjuU", "DramaU", "2019-02-12", "/location/06U", "Hindhi", "Ranbir", 128,  13);
				System.out.println(movieservice.modifyMovieById("mov006", movie2));
				
				//Deleting
//				try {
//					System.out.println(movieservice.deleteMovieById("mov006"));
//				} catch (IdNotFoundException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				
//------------------------------------------Subscriptions transactions--------------------------------------
		SubscriptionService subservice = applicationcontext.getBean(SubscriptionService.class);
		//Inserting
		Subscriptions subscription = new Subscriptions("sub0006", "2022-01-30", "2022-02-10", "online", "quaterly", "credit", false, 500, "rush006");
		System.out.println(subservice.addSubscription(subscription));
				
		//getting subscriptions
		System.out.println("-------------------------------------------------------------------------------------------------------");
			try {
				Optional<List<Subscriptions>> optional = subservice.getSubscriptions();
				if(optional.isEmpty()) {
					System.out.println("There are no records");
				}
				else {
					optional.get().forEach(e -> System.out.println(e));
				}
			} catch (InvalidAmountException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		System.out.println("-------------------------------------------------------------------------------------------------------");
		
		//getting  subscription by id
		System.out.println("-------------------------------------------------------------------------------------------------------");
		try {
			Optional<Subscriptions> optional = subservice.getSubscriptionById("sub0006");
			System.out.println(optional);
		}catch (InvalidAmountException | IdNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("-------------------------------------------------------------------------------------------------------");
		
		//updating
		Subscriptions subscription2 = new Subscriptions("sub0006", "2022-01-30", "2022-02-10", "online", "quaterly", "credit", false, 509, "rush006");
		System.out.println(subservice.modifySubscriptionById("sub0006", subscription2));

		//deleting
//		try {
//			System.out.println(subservice.deleteSubscriptionById("sub0006"));
//		} catch (IdNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//-------------------------------------Series transactions-----------------------------------------
		SeriesService serservice = applicationcontext.getBean(SeriesService.class);
		//Inserting data
		Series series = new Series("ser006", "seriename6", 10, "2022-01-30", "cast6", "Thriller", 13, "english");
		System.out.println(serservice.addSeries(series));
		
		//getting all Series
		System.out.println("-------------------------------------------------------------------------------------------------------");
				try {
					Optional<List<Series>> optional = serservice.getSeries();
					if(optional.isEmpty()) {
						System.out.println("There are no records");
					}
					else {
						optional.get().forEach(e -> System.out.println(e));
					}
				} catch (NameNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		System.out.println("-------------------------------------------------------------------------------------------------------");
		//getting series by id
		System.out.println("-------------------------------------------------------------------------------------------------------");
				try {
					Optional<Series> optional = serservice.getSeriesById("ser006");
					System.out.println(optional);
				}catch (IdNotFoundException | NameNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		System.out.println("-------------------------------------------------------------------------------------------------------");
		
		//updating
		Series series2 = new Series("ser006", "seriename6U", 10, "2022-01-30", "cast6U", "Thriller", 13, "english");
		System.out.println(serservice.modifySeriesById("ser006", series2));
		
		//deleting
//		try {
//			System.out.println(serservice.deleteSeriesById("ser006"));
//		} catch (IdNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//-----------------------------------Episodes transactions-------------------------------------
		EpisodeService epservice = applicationcontext.getBean(EpisodeService.class);
		//insert
		Episodes episode = new Episodes("epiId6", "ser006", "episode6", (float)45.06, "location/06");
		System.out.println(epservice.addEpisode(episode));
		
		//get all episodes
		System.out.println("-------------------------------------------------------------------------------------------------------");
		Optional<List<Episodes>> optional = epservice.getEpisodes();
		if(optional.isEmpty()) {
			System.out.println("There are no records");
		}
		else {
			optional.get().forEach(e -> System.out.println(e));
		}
		System.out.println("-------------------------------------------------------------------------------------------------------");
		
		//getting episode by ID
		System.out.println("-------------------------------------------------------------------------------------------------------");
		Optional<Episodes> optional2 = epservice.getEpisodeById("epiId6");
		System.out.println(optional2);
		System.out.println("-------------------------------------------------------------------------------------------------------");
		
		//Updating
		Episodes episode2 = new Episodes("epiId6", "ser006", "episode6U", (float)45.06, "location/06U");
		System.out.println(epservice.modifyEpisodeById("epiId6", episode2));

		//deleting
//		try {
//			System.out.println(epservice.deleteEpisodeById("epiId6"));
//		} catch (IdNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		applicationcontext.close();
	}

}
