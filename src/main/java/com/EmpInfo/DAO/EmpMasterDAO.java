
package com.EmpInfo.DAO;

import java.util.HashMap;
import java.util.List;

import com.EmpInfo.Beans.EmpMasterBean;

public interface EmpMasterDAO {

	public String InsertEmpDataDAO(String empName,String dept,String designation,String salary,String joiningDate) throws Exception;

	public List<EmpMasterBean> getListEmpDataDAO() throws Exception;
	
	public HashMap<String, Object> getEmpDetailsByEmpIdDAO(Integer empId) throws Exception;
	
	public String updateEmpDataDAO(Integer empId,String empName,String dept,String designation,Integer salary,String joiningDate) throws Exception;
	
	public String deleteEmpDataDAO(Integer empId) throws Exception;

}
