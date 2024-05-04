package com.cinema.service;

import static java.util.stream.Collectors.groupingBy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinema.ResponseWrapper;
import com.cinema.dto.HallDto;
import com.cinema.dto.OrderDto;
import com.cinema.dto.ScreenDto;
import com.cinema.dto.SeatDto;
import com.cinema.enums.SeatStatus;
import com.cinema.enums.ServiceResponseCode;
import com.cinema.exception.ServiceException;
import com.cinema.model.Order;
import com.cinema.model.Seat;
import com.cinema.repository.OrderRepository;
import com.cinema.request.OrderRequest;
import com.cinema.request.SeatRequest;
import com.cinema.security.services.UserDetailsImpl;

@Service
public class OrderServiceImpl implements OrderService {
	
	private final OrderRepository orderRepository;
	private final ScreenService screenService;
	private final SeatService seatService;
	
	@Autowired
	public OrderServiceImpl(OrderRepository orderRepository, ScreenService screenService, SeatService seatService) {
		this.orderRepository = orderRepository;
		this.screenService = screenService;
		this.seatService = seatService;
	}
	
	@Override
	public ResponseWrapper<Void> placeOrder(OrderRequest orderRequest) throws ServiceException {
		ScreenDto screen = screenService.getScreen(orderRequest.getScreenId());
		Set<Seat> seats = seatService.getSeatsByScreenId(screen.getScreenId());
		List<SeatRequest> orderSeats = orderRequest.getSeats();
		Integer userId = UserDetailsImpl.getUser().getId();
		validateSeatsAreValid(screen.getHall(), orderSeats);
		validateSeatsAreAvailable(seats, orderSeats);
		
		long orderDate = System.currentTimeMillis();
		List<Order> orders = new ArrayList<>();
		Integer currentOrderId = orderRepository.getCurrentOrderId();
		currentOrderId = currentOrderId == null ? 0 : currentOrderId;
		for (SeatRequest orderSeat : orderSeats) {
			Seat newSeat = seatService.creteSeat(new Seat(orderRequest.getScreenId(), orderSeat.getRow(), orderSeat.getCol(), SeatStatus.BOOKED));
			orders.add(new Order(userId, newSeat.getSeatId(), screen.getScreenId(), orderDate, orderSeats.size(), currentOrderId + 1));
		}
		orderRepository.saveAll(orders);
		return ResponseWrapper.success();
	}
	
	@Override
	public List<OrderDto> getOrdersByUser(Integer userId) {
		//Fetch Data
		List<Order> ordersByUserId = orderRepository.getOrdersByUserId(userId);
		Set<Integer> screenIds = ordersByUserId.stream().map(Order::getScreenId).collect(Collectors.toSet());
		Set<Integer> seatIds = ordersByUserId.stream().map(Order::getSeatId).collect(Collectors.toSet());
		Map<Integer, List<Order>> orderIdToOrders = ordersByUserId.stream().collect(groupingBy(Order::getOrderId));
		Map<Integer, ScreenDto> screenIdToScreenDto = screenService.getScreenIdToScreenDto(screenIds);
		Map<Integer, SeatDto> seatIdToSeatDto = seatService.getSeatIdToSeatDto(seatIds);
		
		
		List<OrderDto> orders = new ArrayList<>();
		// Collect response
		for (Map.Entry<Integer, List<Order>> entry : orderIdToOrders.entrySet()) {
			Integer orderId = entry.getKey();
			List<Order> innerOrders = entry.getValue();
			Order orderCandidate = innerOrders.get(0);
			List<SeatDto> orderSeats = innerOrders.stream().map(t -> seatIdToSeatDto.get(t.getSeatId())).toList();
			ScreenDto orderScreen = screenIdToScreenDto.get(orderCandidate.getScreenId());
			orders.add(new OrderDto(orderCandidate.getUserId(), orderScreen, orderSeats, orderCandidate.getDate(), orderCandidate.getQuantity(), orderId));
		}
		
		return orders;
	}
	
	private void validateSeatsAreValid(HallDto hall, List<SeatRequest> orderSeats) throws ServiceException {
		SeatRequest invalidSeatRequestOnRows = orderSeats.stream().filter(t -> t.getRow() > hall.getRows() || t.getRow() < 0).findFirst().orElse(null);
		SeatRequest invalidSeatRequestOnCols = orderSeats.stream().filter(t -> t.getCol() > hall.getCols() || t.getCol() < 0).findFirst().orElse(null);
		
		if (invalidSeatRequestOnRows != null) {
			throw new ServiceException(ServiceResponseCode.ERROR_BAD_REQUEST,
																 String.format("The request for seat %s is invalid for Hall %s (Row is out of range)", invalidSeatRequestOnRows, hall.getName()));
		}
		
		if (invalidSeatRequestOnCols != null) {
			throw new ServiceException(ServiceResponseCode.ERROR_BAD_REQUEST,
																 String.format("The request for seat %s is invalid for Hall %s (Seat number in row is out of range)", invalidSeatRequestOnCols, hall.getName()));
		}
	}
	
	private void validateSeatsAreAvailable(Set<Seat> screenSeats, List<SeatRequest> orderSeats) throws ServiceException {
		Map<String, SeatStatus> screenSeatToStatus = screenSeats.stream()
																														.collect(Collectors.toMap(t -> getSeatKey(t.getRow(), t.getSeatNumber()), Seat::getStatus));
		for (SeatRequest orderSeat : orderSeats) {
			String seatKey = getSeatKey(orderSeat.getRow(), orderSeat.getCol());
			if (screenSeatToStatus.get(seatKey) == SeatStatus.BOOKED) {
				throw new ServiceException(ServiceResponseCode.ERROR_BAD_REQUEST,
																	 String.format("Seat in row %d in column %d is already booked", orderSeat.getRow(), orderSeat.getCol()));
			}
			
		}
	}
	
	private String getSeatKey(int row, int col) {
		return row + "$" + col;
	}
}
