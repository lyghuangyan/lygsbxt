package com.tom.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tom.dao.ReportMapper;
import com.tom.dao.generator.MaintainorderMapper;
import com.tom.model.generator.MaintainorderExample;
import com.tom.util.DateUtil;
import com.tom.util.TomPage;

/**
 * 报表业务层
 * @author tom
 * @since 2019-01-09 19:32:38
 */
@Service("reportService")
public class ReportService {
	
	@Autowired
	private MaintainorderMapper maintainorderMapper;
	@Autowired
	private ReportMapper reportMapper;
	
	/**
	 * 初始化需要统计的时间结点
	 * @return
	 */
	private List<Map<String, Object>> initTime() {
		List<Map<String, Object>> dataList = new ArrayList<Map<String,Object>>();
		//今天 本周 本月 本年 全部
		Map<String, Object> map = null;
		map = new HashMap<String, Object>();
		map.put("time", "今天");
		map.put("date", DateUtil.todayStart());
		dataList.add(map);
		map = new HashMap<String, Object>();
		map.put("time", "本周");
		map.put("date", DateUtil.weekStart());
		dataList.add(map);
		map = new HashMap<String, Object>();
		map.put("time", "本月");
		map.put("date", DateUtil.monthStart());
		dataList.add(map);
		map = new HashMap<String, Object>();
		map.put("time", "本年");
		map.put("date", DateUtil.yearStart());
		dataList.add(map);
		map = new HashMap<String, Object>();
		map.put("time", "全部");
		map.put("date", DateUtil.allStart());
		dataList.add(map);
		return dataList;
	}
	
	public List<Map<String, Object>> dataSummary() {
		List<Map<String, Object>> dataList = this.initTime();
		for(Map<String, Object> map : dataList) {
			//全部
			MaintainorderExample maintainorderExampleAll = new MaintainorderExample();
			maintainorderExampleAll
				.createCriteria().andIsdeleteEqualTo(0)
				.andCreatetimeGreaterThanOrEqualTo((Date)map.get("date"));
			map.put("all", maintainorderMapper.countByExample(maintainorderExampleAll));
			//待维修
			MaintainorderExample maintainorderExampleWait = new MaintainorderExample();
			maintainorderExampleWait
				.createCriteria().andIsdeleteEqualTo(0)
				.andCreatetimeGreaterThanOrEqualTo((Date)map.get("date"))
				.andStatusEqualTo("未派单");
			map.put("wait", maintainorderMapper.countByExample(maintainorderExampleWait));
			//维修中
			MaintainorderExample maintainorderExampleMaintain = new MaintainorderExample();
			maintainorderExampleMaintain
				.createCriteria().andIsdeleteEqualTo(0)
				.andCreatetimeGreaterThanOrEqualTo((Date)map.get("date"))
				.andStatusEqualTo("维修中");
			map.put("maintain", 
					maintainorderMapper.countByExample(maintainorderExampleMaintain));
			//维修完
			MaintainorderExample maintainorderExampleMaintained = new MaintainorderExample();
			maintainorderExampleMaintained
				.createCriteria().andIsdeleteEqualTo(0)
				.andCreatetimeGreaterThanOrEqualTo((Date)map.get("date"))
				.andStatusEqualTo("待评价");
			map.put("maintained", 
					maintainorderMapper.countByExample(maintainorderExampleMaintained));
			//已完成
			MaintainorderExample maintainorderExampleCompleted = new MaintainorderExample();
			maintainorderExampleCompleted
				.createCriteria().andIsdeleteEqualTo(0)
				.andCreatetimeGreaterThanOrEqualTo((Date)map.get("date"))
				.andStatusEqualTo("已完成");
			map.put("completed", 
					maintainorderMapper.countByExample(maintainorderExampleCompleted));
		}
		
		//maintainorderExample.createCriteria().andCreatetimeGreaterThanOrEqualTo(value)
		return dataList;
	}

	public List<Long> dataChart() {
		List<Long> dataList = new ArrayList<Long>();
		MaintainorderExample maintainorderExampleStart = new MaintainorderExample();
		maintainorderExampleStart.createCriteria().andIsdeleteEqualTo(0)
			.andCreatetimeBetween(DateUtil.todayStart(0), DateUtil.todayStart(8));
		dataList.add(maintainorderMapper.countByExample(maintainorderExampleStart));
		for(int hour = 8; hour <= 20; hour++) {
			MaintainorderExample maintainorderExample = new MaintainorderExample();
			maintainorderExample.createCriteria().andIsdeleteEqualTo(0)
				.andCreatetimeGreaterThanOrEqualTo(DateUtil.todayStart(hour))
				.andCreatetimeLessThan(DateUtil.todayStart(hour+1));
			dataList.add(maintainorderMapper.countByExample(maintainorderExample));
		}
		MaintainorderExample maintainorderExampleEnd = new MaintainorderExample();
		maintainorderExampleEnd.createCriteria().andIsdeleteEqualTo(0)
			.andCreatetimeGreaterThanOrEqualTo(DateUtil.todayStart(21));
		dataList.add(maintainorderMapper.countByExample(maintainorderExampleEnd));
		return dataList;
	}
	
	public List<Map<String, Object>> homeWorks(String time) {
		Date date = null;
		if("month".contentEquals(time)) {
			date = DateUtil.monthStart();
		} else {
			date = DateUtil.yearStart();
		}
		Page<Object> page = PageHelper.startPage(1,5);
		return reportMapper.reportWorksByCreatetime(date);
	}
	
	public List<Map<String, Object>> homeStars(String time) {
		Date date = null;
		if("month".contentEquals(time)) {
			date = DateUtil.monthStart();
		} else {
			date = DateUtil.yearStart();
		}
		Page<Object> page = PageHelper.startPage(1,5);
		return reportMapper.reportStarsByCreatetime(date);
	}
	
	public TomPage works(Integer pageNumber,Integer pageSize) {
        Page<Object> page = PageHelper.startPage(pageNumber,pageSize);
        reportMapper.reportWorks();
        TomPage tomPage = new TomPage(page);
        return tomPage;
    }
    
    public TomPage stars(Integer pageNumber,Integer pageSize) {
        Page<Object> page = PageHelper.startPage(pageNumber,pageSize);
        reportMapper.reportStars();
        TomPage tomPage = new TomPage(page);
        return tomPage;
    }
}
