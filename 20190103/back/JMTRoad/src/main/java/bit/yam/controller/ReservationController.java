package bit.yam.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bit.yam.config.Reservation;
//import bit.yam.config.Reservation_haha;
import bit.yam.mapper.ReservationService;
import bit.yam.mapper.ReserveMapper;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/reserve")
public class ReservationController {

	@Autowired
	private ReservationService reservationService;

	@Autowired
	private ReserveMapper reservemapper;

	@PostMapping("/save")
	public void saveUser(@RequestBody Reservation user) {

		System.out.println("찍혀주세요 아멘" + user);
		reservemapper.saveReserve(user);

	}

	@GetMapping
	public List<Reservation> listUser() {
		return reservationService.findAll();
	}

	@GetMapping("/today/{ownerNo}")
	public List<Reservation> getOne(@PathVariable int ownerNo) {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

		Date time = new Date();

		System.out.println(time);

		String today = format1.format(time);

		System.out.println("today" + today);

		return reservemapper.ReserveList(ownerNo, today);
	}

	@GetMapping("/todaycount/{ownerNo}")
	public int countreservation(@PathVariable int ownerNo) {

		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

		Date time = new Date();
		String today = format1.format(time);

		System.out.println("fdsa");
		return reservemapper.countreservation(ownerNo,today);
	}
	
	@GetMapping("/request/{ownerNo}")
	public List<Reservation> requestreservation(@PathVariable int ownerNo){
		return reservemapper.requestreservation(ownerNo);
	}
	
	@GetMapping("/requestcount/{ownerNo}")
	public int countrequestreservation(@PathVariable int ownerNo) {
		
		return reservemapper.countrequestreservation(ownerNo);
	}
	
	@GetMapping("/accept/{reservationNO}")
	public void acceptreservation(@PathVariable int reservationNO) {
		reservemapper.acceptreservation(reservationNO);
	}
	
	@GetMapping("/reject/{reservationNO}")
	public void rejectreservation(@PathVariable int reservationNO) {
		reservemapper.rejectreservation(reservationNO);
	}

}
