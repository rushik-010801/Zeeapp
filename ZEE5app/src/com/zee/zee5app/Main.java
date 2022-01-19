package com.zee.zee5app;

import com.zee.zee5app.dto.Movies;
import com.zee.zee5app.dto.Register;
import com.zee.zee5app.dto.Series;
import com.zee.zee5app.dto.Subscriptions;
import com.zee.zee5app.service.MovieService;
import com.zee.zee5app.service.SeriesService;
import com.zee.zee5app.service.SubscriptionService;
import com.zee.zee5app.service.UserService;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Register register = new Register();
		
		register.setFirstName("rushik");
		register.setLastName("kumar");
		register.setEmail("rushik@gmail.com");
		register.setPassword("rushik1234");
		
		System.out.println(register);
		
		//System.out.println(register.toString());
	
		System.out.println(register.getEmail());
		
		UserService service = UserService.getInstance();
		for(int i = 0; i < 20; i++) {
			Register register2 = new Register();
			register2.setId("ab00" + i);
			register2.setFirstName("rushik" + i);
			register2.setLastName("kumar" + i);
			register2.setEmail("rushik@gmail.com" + i);
			register2.setPassword("rushik"+ i);
			System.out.println(service.addUser(register2));
		}
		
		Register register3 = service.getUserById("ab001");
		System.out.println((register3 != null));
		
		for(Register register4 : service.getUsers()) {
			if(register4 != null)
				System.out.println(register4);
		}
		
		//Subscriptions
		SubscriptionService subservice = SubscriptionService.getInstance();
		for(int i = 0; i < 20; i++) {
			Subscriptions sub = new Subscriptions();
			sub.setAutoRenewal(false);
			sub.setCountry("India");
			sub.setDateOfPurchase(i + " " + i + " " + i);
			sub.setExpiryDate((i + 10) + " " + (i + 20)+ " " + (i + 30));
			sub.setId("sub" + i);
			sub.setPaymentMode("UPI");
			sub.setStatus("active");
			subservice.addSubscription(sub);
		}
		
		for(Subscriptions subscriptions : subservice.getSubscriptions()) {
			System.out.println(subscriptions);
		}
		System.out.println(subservice.getSubscriptionById("sub2"));
		System.out.println(subservice.deleteSubscriptionById("sub2"));
		System.out.println(subservice.modifySubscriptionById("sub3", "2:34:56", "active" , "India", "UPI", false, "67:89:90"));
		
		//Movie
		MovieService movservice = MovieService.getInstance();
		String[] cast = {"cast1", "cast2"};
		for(int i = 0; i < 20; i++) {
			Movies mov = new Movies();
			mov.setId("mid" + i);
			mov.setMovieName("Movie" + i);
			mov.setCategory("cat" + i);
			mov.setReleaseDate(i + " " + i + " " + i);
			mov.setTrailer("playing");
			mov.setLanguage("lan" + i);
			mov.setCast(cast);
			mov.setLength(123);
			movservice.addMovie(mov);
		}
		
		for(Movies movie : movservice.getMovies()) {
			System.out.println(movie);
		}
		System.out.println(movservice.getMovieById("mid2"));
		System.out.println(movservice.deleteMovieById("mid2"));
		
		//Series
		SeriesService serservice = SeriesService.getInstance();
		for(int i = 0; i < 20; i++) {
			Series ser = new Series();
			ser.setId("ser" + i);
			ser.setSeriesName("seriesName" + i);
			ser.setNoofepisodes(10 + i);
			ser.setNoofSeasons(7 + i);
			ser.setTrailer("trailer");
			ser.setCast(cast);
			ser.setLengthOfEpisode(234);
			serservice.addSeries(ser);
		}
		
		for(Series ser : serservice.getSeries()) {
			System.out.println(ser);
		}
		System.out.println(serservice.getSeriesById("ser2"));
		System.out.println(serservice.deleteSeriesById("ser2"));
	}

}
