package com.EmpInfo.Service;

import java.util.HashMap;
import java.util.List;

import com.EmpInfo.Beans.EmpMasterBean;

public interface EmpMasterService {

	public String InsertEmpData(String empName,String dept,String designation,String salary,String joiningDate) throws Exception;
	
	public List<EmpMasterBean> listEmpData() throws Exception;
	
	public HashMap<String, Object> getEmpDetailsByEmpId(Integer empId) throws Exception;
	
	public String updateEmpData(Integer empId,String empName,String dept,String designation,Integer salary,String joiningDate) throws Exception;
	
	public String deleteEmpData(Integer empId) throws Exception;
}
