jQuery(document).ready(function () {
	$("#gridDiv").show();
	jQuery("#grid").jqGrid({
	    url: "listEmpData",
	    async : false,
	    datatype: "json",
	    jsonReader: {repeatitems: false, id: "ref"},
	    colNames:['Emp Id','Employee Name','Department', 'Designation' , 'Salary','Joining Date','Edit','Delete'],
	    colModel:[
	        {name:'empId',index:'empId', width:140},
	        {name:'empName',index:'empName', width:140},
	        {name:'dept',index:'dept', width:140},
	        {name:'designation',index:'designation', width:120},
	        {name:'salary',index:'salary', width:120},
	        {name:'joiningDate',index:'joiningDate', width:120},
	        {name:'edit',search:false,index:'empId',width:55,sortable: false,formatter: editLinkH},
	        {name:'delete',search:false,index:'empId',width:60,sortable: false,formatter: deleteLinkH},
	    ],
	    rowNum:10,
	    rowList:[10,20,60],
	    height:300,
	    pager: "#pagingDiv",
	    viewrecords: true,
	    caption: ""
	});
	});

function editLinkH(cellValue, options, rowdata, action)  {
   
	var empId = rowdata.empId;
	
	return "<a href='javascript:editRecordWH(" + rowdata.empId + ")'>Edit</a>";
}

function editRecordWH(empId){
	$.ajax({
			
			type : "POST",
			encoding : "UTF-8",
			url : "getEmpDetailsById",
			datatype :'json', 
			data : {
				empId : empId,
				        },
			success : function(data) {

				$("#addEmp").show();
				$("#gridDiv").hide();
				$("#grid").hide();
				$("#pagingDiv").hide();
				 $("#saveBtn").hide();
				var posts = JSON.parse(data);
				console.log("SUCCESS: ", posts.empName);

				$("#empId").val(posts.empId);
				$("#empName").val(posts.empName);
				$("#dept").val(posts.dept);
				$("#designation").val(posts.designation);
				$("#salary").val(posts.salary);
				$("#joiningDate").val(posts.joiningDate);
			},
			error : function(e) {
				console.log("ERROR: ", e);
			
			}
		});
		
	}

function deleteLinkH(cellValue, options, rowdata, action)  {
    return "<a class='delete1' href='javascript:deleteRecordWH(" + rowdata.empId + ")'>Delete</a>";
}

function deleteRecordWH(empId){
                          
                               $.ajax({
                           		
                           		type : "POST",
                           		encoding : "UTF-8",
                           		url : "deleteEmpDataByEmpId",
                           		datatype :'json', 
                           		data : {
                           			empId : empId,
                           			        },
                           		success : function(data) {
                                         console.log("Marked for Deletion");
                                         $("#gridDiv").show();
                         				 $("#grid").show();
                                         $("#gridDiv").trigger("reloadGrid");
                                         $("#grid").trigger("reloadGrid");
                           		           },
                           		error : function(e) {
                           			console.log("ERROR: ", e);
                           		
                           		}
                           	});
                               
                                               
   
}

function deleteLinkH(cellValue, options, rowdata, action)  {
    return "<a href='javascript:deleteRecordWH(" + rowdata.empId + ")'>Delete</a>";
}

//function deleteRecordWH(empId){
//	   
//    noty({
//       text: 'Do you want to continue?',
//       layout: 'topRight',
//       buttons: [
//               {
//               	text: 'Ok', 
//               	onClick: function($noty) 
//               	     {
//                               $noty.close();
//                          
//                               $.ajax({
//                           		
//                           		type : "POST",
//                           		encoding : "UTF-8",
//                           		url : "deleteEmpDataByEmpId",
//                           		datatype :'json', 
//                           		data : {
//                           			empId : empId,
//                           			        },
//                           		success : function(data) {
//                                         console.log("Marked for Deletion");
//                                         $("#gridDiv").trigger("reloadGrid");
//                                         $("#grid").trigger("reloadGrid");
//                           		           },
//                           		error : function(e) {
//                           			console.log("ERROR: ", e);
//                           		
//                           		}
//                           	});
//                               
//               }
//               },
//               {text: 'Cancel', onClick: function($noty) {
//                   $noty.close();
//                   /*noty({ layout: 'topRight', type: 'error'});*/
//                   }
//               }
//           ]
//   })                                                    
//   
//}