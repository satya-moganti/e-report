package com.re.ereport;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class EmployeeDataAnalysis {
	private static final String COMMA =",";

	public static void main(String[] args) {
		String csvFile = "data.csv"; // path to your CSV file
		String line;

		List<Employee> employeeList = new ArrayList<Employee>();
		int count =0;
	    ClassLoader classloader = Thread.currentThread().getContextClassLoader();
	    InputStream inputStream = classloader.getResourceAsStream("data.csv");
	    InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);

		try (BufferedReader br = new BufferedReader(streamReader)) {
			while ((line = br.readLine()) != null) {
				String[] values = line.split(COMMA);
				if(count > 0) {
					employeeList.add(addEmployee(values));
				}
				count++;

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		
	List<Employee> ceoList=	employeeList.stream().filter(e->e.getManagerId()==0).collect(Collectors.toList());
	
	
	Map<Long, List<Employee>> byManager
    = employeeList.stream()
               .collect(Collectors.groupingBy(Employee::getManagerId));
	Map<Long, List<Employee>> ceoMap = new HashMap<>();
	
    //Creating Map with CEO ID as Key and list of employees along with CEO and also adding heirarchy to find the reporting structure   
		
     for (Employee ceo : ceoList) {
    	 int heirachyIndex = 0;
         ceo.setHierachyIdex(heirachyIndex);

 	     List<Employee> employees = new ArrayList<Employee>();
         employees.add(ceo);
         ceoMap.put(ceo.getId(), employees);
	     buildHierarchy(ceo, byManager,ceoMap,ceo.getId(),(heirachyIndex));	    
	     employees = ceoMap.get(ceo.getId());
	     List<Employee> sortedEmp = employees =(List<Employee>) employees.stream().sorted(Comparator.comparing(Employee::getHierachyIdex)).collect(Collectors.toList());
	        ceoMap.put(ceo.getId(), sortedEmp);


	}
	
   
	
     for (Entry<Long, List<Employee>> entry : ceoMap.entrySet()) {
         System.out.println(" === Data Analysis for CEO : " + entry.getKey() + " ===");

         List<Employee> employees = entry.getValue();
         Map<Long, List<Employee>> managerMap =  (Map<Long, List<Employee>>) employees.stream().filter(e -> e.getManagerId() != 0)
                 .collect(Collectors.groupingBy(e -> e.getManagerId()));

         Map<Long, Employee> idMap = employees.stream().collect(Collectors.toMap(e -> e.getId(), e -> e));

         // Manager Salary Analysis
         for (Map.Entry<Long, List<Employee>> managerEntry : managerMap.entrySet()) {
             Long managerId = managerEntry.getKey();
             List<Employee> reports = managerEntry.getValue();

             double avgReportSalary = reports.stream().mapToDouble(e -> e.getSalary()).average().orElse(0);
             Employee manager = idMap.get(managerId);

             if (manager != null) {
                 if (manager.getSalary() < avgReportSalary) {
                     System.out.printf("Manager %s earns $%d LESS than average of their team ($%.2f)\n",
                             manager, manager.getSalary(), avgReportSalary);
                 } else if (manager.getSalary() > avgReportSalary) {
                     System.out.printf("Manager %s earns $%d MORE than average of their team ($%.2f)\n",
                             manager, manager.getSalary(), avgReportSalary);
                 }
             }
         }

         // Reporting line too long
         for (Employee e : employees) {
             if (e.getHierachyIdex() > 3) {
                 System.out.printf("Employee %s has a reporting line too long by %d levels\n",
                         e, e.getHierachyIdex() - 3);
             }
         }
     }
	
	
	}
		
	
	private static Employee addEmployee(String[] values) {
		if(values.length>=5) {
			return new Employee(
					Long.valueOf(values[0]),
					String.valueOf(values[1]),
					String.valueOf(values[2]),
					Long.valueOf(values[3]),
					Long.valueOf(values[4]));   
		}else {
			return new Employee(
					Long.valueOf(values[0]),
					String.valueOf(values[1]),
					String.valueOf(values[2]),
					Long.valueOf(values[3]),
					Long.valueOf(0));   
		}
	}
	
	
	public static void buildHierarchy(Employee manager, Map<Long, List<Employee>> byManager, Map<Long, List<Employee>> map, Long ceokey,
			int heirachyIndex) {
		
	    if (byManager.containsKey(manager.getId())) {
	        List<Employee> subordinates = byManager.get(manager.getId());
	        List<Employee> addOnList = map.get(ceokey);
	        map.put(ceokey,addOnList);
        	heirachyIndex  = heirachyIndex+1;
 
	        for (Employee subordinate : subordinates) {
	        	subordinate.setHierachyIdex(heirachyIndex);
	        	addOnList.add(subordinate);
	            buildHierarchy(subordinate, byManager,map,ceokey,heirachyIndex); // recurse for each subordinate
	        }
	        
	     
	        
	    }
	    
	}
	

}