package com.EmpInfo.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.EmpInfo.Beans.EmpMasterBean;
import com.EmpInfo.Service.EmpMasterService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
public class EmpMasterController {

	@Autowired
	EmpMasterService empMasterService;

	@RequestMapping(value = "/saveEmpData", method = RequestMethod.POST)
	public @ResponseBody String saveEmpData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
			String empName = request.getParameter("empName");
			String dept = request.getParameter("dept");
			String designation = request.getParameter("designation");
			String salary = request.getParameter("salary");
			String joiningDate = request.getParameter("joiningDate");

			String status = empMasterService.InsertEmpData(empName, dept, designation, salary, joiningDate);
			
		return status;
	}

	@RequestMapping(value = "/listEmpData", method = RequestMethod.GET)
	public @ResponseBody String getEmpDataList(HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {
		response.setContentType("application/json");
		String json = "";
		List<EmpMasterBean> empMasterArrayList = new ArrayList<EmpMasterBean>();
		try {
			empMasterArrayList = empMasterService.listEmpData();
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			json = gson.toJson(empMasterArrayList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
	@RequestMapping(value="/getEmpDetailsById",method=RequestMethod.POST)
	public @ResponseBody String getEmpDetailsById(HttpSession session,HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setContentType("application/json");
		
		String json;
		Integer empId= Integer.parseInt(nvl(request.getParameter("empId")));
        Gson gson = new GsonBuilder().setPrettyPrinting().create();	   
        HashMap<String, Object> file=empMasterService.getEmpDetailsByEmpId(empId);
	    
	    json = gson.toJson(file);
		return json;
		
	}
	
	
	@RequestMapping(value="/updateEmpData",method=RequestMethod.POST)
	public @ResponseBody String updateEmpData(HttpSession session,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		Integer empId = Integer.parseInt(nvl(request.getParameter("empId")));
		String empName=nvl(request.getParameter("empName"));
		String dept=nvl(request.getParameter("dept"));
		String designation=nvl(request.getParameter("designation"));
		Integer salary=Integer.parseInt(nvl(request.getParameter("salary")));
	    String joiningDate=nvl(request.getParameter("joiningDate"));
		
		String status=empMasterService.updateEmpData(empId,empName,dept,designation,salary,joiningDate);
	
		return status;
		
	}
	
	@RequestMapping(value="/deleteEmpDataByEmpId",method=RequestMethod.POST)
	public @ResponseBody String deleteEmpDataByEmpId(HttpSession session,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		Integer empId = Integer.parseInt(nvl(request.getParameter("empId")));
			
		String status=empMasterService.deleteEmpData(empId);

		return status;
		
	}
	public static String nvl(String str) {
        return (str == null) ? "" : str.trim();
    }
}
