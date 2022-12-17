package com.masai.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.RouteException;
import com.masai.exception.UserException;
import com.masai.model.Bus;
import com.masai.model.Route;
import com.masai.repository.RouteDao;
import com.masai.repository.SessionDao;

@Service
public class RouteServiceImpl implements RouteService {
	
	
	@Autowired
	private RouteDao rDao;
	
	@Autowired
	private SessionDao sDao;

	@Override
	public Route addRoute(Route route, String key) throws RouteException, UserException {
		
		
		Route newRoute = rDao.findByRouteFromAndRouteTo(route.getRouteFrom(), route.getRouteTo());
		
		if (newRoute != null) {
			throw new RouteException("Route from : " + route.getRouteFrom() + " to " + route.getRouteTo() + " already exists" );
		}
		
		List<Bus> busList = new ArrayList<>();
		route.setBus(busList);
	
		return  rDao.save(route);
	}

	@Override
	public Route updateRoute(Route route, String key) throws RouteException, UserException {
		
		Optional<Route> opt = rDao.findById(route.getRouteId());
		
		if(opt.isPresent()) {
			   
			 Route existingRoute = opt.get();
			 
			 if (!existingRoute.getBus().isEmpty())
				 throw new RouteException("Cannot update Route ! Already buses are Scheduled for this route");
			 
			 if (route.getDistance() != null) existingRoute.setDistance(route.getDistance());
			 if (route.getRouteFrom() != null) existingRoute.setRouteFrom(route.getRouteFrom());
			 if (route.getRouteTo() != null) existingRoute.setRouteTo(route.getRouteTo());
			 
			Route saved =  rDao.save(existingRoute);
			
			return saved;
			   
		  }
		  else {
			 throw new RouteException("No route exist to update please save the Route first");
		  }
	}

	@Override
	public Route deleteRoute(Integer routeId, String key) throws RouteException, UserException {
		
		Optional<Route> opt =	rDao.findById(routeId);
		
		if(opt.isPresent()) {
			
			Route rot = opt.get();
			
			if (!rot.getBus().isEmpty())
				 throw new RouteException("Cannot delete Route ! Already buses are Scheduled for this route");
			
			rDao.delete(rot);
			
			return rot;
			
		}
		else {
			throw new RouteException("No route found on this "+routeId+" id");
		}
	}

	@Override
	public Route viewRoute(Integer routeId, String key) throws RouteException, UserException {
		
		Optional<Route> opt =rDao.findById(routeId);
		
	     if(opt.isPresent()) {
	    	 
	    	 return opt.get();
	     }
	     else {
	    	 throw new RouteException("No route found on this "+routeId+" id");
	     }
	}

	@Override
	public List<Route> viewAllRoute(String key) throws RouteException, UserException {
		
		List<Route> routeList = rDao.findAll();
		
		if(routeList.size()!=0) {
			
			return routeList;
		}else {
			throw new RouteException("Route list is empty");
		}
	}

}
