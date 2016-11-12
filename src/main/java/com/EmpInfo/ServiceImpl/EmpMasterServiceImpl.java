package com.EmpInfo.ServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EmpInfo.Beans.EmpMasterBean;
import com.EmpInfo.DAO.EmpMasterDAO;
import com.EmpInfo.Service.EmpMasterService;

@Service
public class EmpMasterServiceImpl implements EmpMasterService {

	@Autowired
	EmpMasterDAO empMasterDao;

	public String InsertEmpData(String empName, String dept, String designation, String salary, String joiningDate)
			throws Exception {
		String s = "";
		try {

			s = empMasterDao.InsertEmpDataDAO(empName, dept, designation, salary, joiningDate);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}

	public List<EmpMasterBean> listEmpData() throws Exception {

		List<EmpMasterBean> empMasterArrayList = new ArrayList<EmpMasterBean>();

		try {
			empMasterArrayList = empMasterDao.getListEmpDataDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return empMasterArrayList;
	}
	
	public HashMap<String, Object> getEmpDetailsByEmpId(Integer empId) throws Exception{
		HashMap<String, Object> empData = new HashMap<String, Object>();
		try {
			empData = empMasterDao.getEmpDetailsByEmpIdDAO(empId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return empData;
	}
	
	
	public String updateEmpData(Integer empId,String empName,String dept,String designation,Integer salary,String joiningDate) throws Exception{
		String s = "";
		try {

			s = empMasterDao.updateEmpDataDAO(empId,empName,dept,designation,salary,joiningDate);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}
	
	public String deleteEmpData(Integer empId) throws Exception{
		String s = "";
		try {

			s = empMasterDao.deleteEmpDataDAO(empId);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}
}
