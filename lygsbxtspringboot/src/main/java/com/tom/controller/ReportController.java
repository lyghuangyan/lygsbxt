package com.tom.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.tom.service.ReportService;
import com.tom.util.ApiResponse;
import com.tom.util.TomPage;

@RestController
@RequestMapping("/report")
public class ReportController extends BaseController {
	
	@Autowired
	private ReportService reportService;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/dataSummary")
	public ApiResponse dataSummary() {
		List<Map<String, Object>> dataList = reportService.dataSummary();
		ApiResponse result = new ApiResponse();
		result.setCode("200");
		result.setData(dataList);
		return result;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/dataChart")
	public ApiResponse dataChart() {
		List<Long> dataList = reportService.dataChart();
		ApiResponse result = new ApiResponse();
		result.setCode("200");
		result.setData(dataList);
		return result;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/homeWorks")
	public ApiResponse homeWorks(
			@RequestParam(value = "time", required = false, defaultValue = "year")
			String time) {
		List<Map<String, Object>> dataList = reportService.homeWorks(time);
		ApiResponse result = new ApiResponse();
		result.setCode("200");
		result.setData(dataList);
		return result;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/homeStars")
	public ApiResponse homeStars(
			@RequestParam(value = "time", required = false, defaultValue = "year")
			String time) {
		List<Map<String, Object>> dataList = reportService.homeStars(time);
		ApiResponse result = new ApiResponse();
		result.setCode("200");
		result.setData(dataList);
		return result;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping("/works")
    public ApiResponse works(@RequestParam(value = "page", required = false, defaultValue = "1")
    Integer pageNumber,
    @RequestParam(value = "size", required = false, defaultValue = "10")
    Integer pageSize) {
	    TomPage tomPage = reportService.works(pageNumber, pageSize);
        ApiResponse result = new ApiResponse();
        result.setCode("200");
        result.setData(tomPage);
        return result;
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping("/stars")
    public ApiResponse stars(
            @RequestParam(value = "page", required = false, defaultValue = "1")
            Integer pageNumber,
            @RequestParam(value = "size", required = false, defaultValue = "10")
            Integer pageSize) {
        TomPage tomPage = reportService.stars(pageNumber, pageSize);
        ApiResponse result = new ApiResponse();
        result.setCode("200");
        result.setData(tomPage);
        return result;
    }

}
