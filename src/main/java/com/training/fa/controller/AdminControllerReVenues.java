package com.training.fa.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.training.fa.model.Order;
import com.training.fa.model.OrderDetail;
import com.training.fa.model.Revenues;
import com.training.fa.repository.OrderRepository;
import com.training.fa.service.OrderService;
import com.training.fa.service.RevenuesService;

@Controller
@RequestMapping("/admin")
public class AdminControllerReVenues {

	@Autowired
	private OrderService orderService;

	@Autowired
	private RevenuesService revenuesService;

	
	@GetMapping("/total-revenues")
	@ResponseBody
	public String totalRevenuesMonthLy(){
		 LocalDate date = LocalDate.now();
	        int day = date.getDayOfMonth();
	        int month = date.getMonthValue();
	        int year = date.getYear();
	        String startDate = "01-"+month+"-"+year;
	        String endDate = day+"-"+month+"-"+year;  
	        List<Revenues> chart_myAreaChart =  revenuesService.findByOrderDateBetween(startDate, endDate);
	        Integer sum = 0;
	        for(Revenues item:chart_myAreaChart) {
	        	sum +=item.getSales();
	        }
	       
	        	
		   return "<span> $"+sum+"</span>  <span class=\"text-success small pt-1 fw-bold\">Month</span>\r\n"
		   		+ "                  <span class=\"text-muted small pt-2 ps-1\">"+month+"</span>";
		
	}
	
    //total profit monthly//
	@GetMapping("/total-profit")
	@ResponseBody
	public String totalProfitMonthLy(){
		 LocalDate date = LocalDate.now();
	        int day = date.getDayOfMonth();
	        int month = date.getMonthValue();
	        int year = date.getYear();
	        String startDate = "01-"+month+"-"+year;
	        String endDate = day+"-"+month+"-"+year;  
	        List<Revenues> chart_myAreaChart =  revenuesService.findByOrderDateBetween(startDate, endDate);
	        Integer sum = 0;
	        for(Revenues item:chart_myAreaChart) {
	        	sum +=item.getProfit();
	        }
	       	
		   return "<span> $"+sum+"</span>  <span class=\"text-success small pt-1 fw-bold\">Month</span>\r\n"
		   		+ "                  <span class=\"text-muted small pt-2 ps-1\">"+month+"</span>";
		
	}
	
	
	
	@GetMapping("/chart_myAreaChart")
	@ResponseBody
	public List<Revenues> chart_myAreaChart(){
		 LocalDate date = LocalDate.now();
	        int day = date.getDayOfMonth();
	        int month = date.getMonthValue();
	        int year = date.getYear();
	        String startDate = "01-"+month+"-"+year;
	        String endDate = day+"-"+month+"-"+year;  
	        System.out.println("hello");
	        List<Revenues> chart_myAreaChart =  revenuesService.findByOrderDateBetween(startDate, endDate);
	        
		    return chart_myAreaChart;
		
	}
	
	@GetMapping("/today-statistics")
    public String statistics(Model model) {
        LocalDate date = LocalDate.now();
        int day = date.getDayOfMonth();
        int month = date.getMonthValue();
        int year = date.getYear();
        String startDate = "01-"+month+"-"+year;
        String endDate = day+"-"+month+"-"+year;
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("revenues", revenuesService.findByOrderDateBetween(startDate, endDate));
        model.addAttribute("body", getBody("./revenues/revenue-statistics"));
        return "/admin/index";
    }

	@GetMapping("/statistics")
	@ResponseBody
	public List<Revenues> dateChartSubmit(Model model, @RequestParam("from_date") String from_date,
			@RequestParam("to_date") String to_date) {

		List<Revenues> revenues = revenuesService.findByOrderDateBetween(from_date, to_date);

		return revenues;
	}

	
	
	@GetMapping("/day-statistics")
	public String dayStatistics(Model model){
		  LocalDate date = LocalDate.now();
	        int day = date.getDayOfMonth();
	        int day1 = date.getDayOfMonth()-1;
	        int month = date.getMonthValue();
	        int year = date.getYear();
	        String startDate = day1+"-"+month+"-"+year;
	        String endDate = day+"-"+month+"-"+year;
	        model.addAttribute("startDate", startDate);
	        model.addAttribute("endDate", endDate);
	        model.addAttribute("revenues", revenuesService.findByOrderDateBetween(startDate, endDate));
	        System.out.println(revenuesService.findByOrderDateBetween(startDate, endDate));
	        model.addAttribute("body", getBody("./revenues/revenue-statistics"));
	        return "/admin/index";
	}

	public static String getBody(String body) {
		return body + ".jsp";
	}

}
