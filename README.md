# e-report
The Application to analyze the Employee data and Heirarchy
# Pre-requisites
1. Java 1.8 installed
2. Maven version >=3.3.3
3. Eclipse IDE  

# Requirement :
Company would like to analyze its organizationalstructure and identify potential improvements. 
 Board wants to make sure that every manager earns at least 20% more than the average salary of its direct subordinates, but no more than 50% more
than that average. Company wants to avoid too long reporting lines, therefore we would like to
identify all employees which have more than 4 managers between them and the CEO.
You are given a CSV file which contains information about all the employees. File structure looks like
this:

Each line represents an employee (CEO included). CEO has no manager specified. Number of rows
can be up to 1000.

Write a simple program which will read the file and report:
- which managers earn less than they should, and by how much
- which managers earn more than they should, and by how much
- which employees have a reporting line which is too long, and by how much
Key points:
• use only Java SE (any version), and Junit (any version) for tests.
• use maven for project structure and build
• your application should read data from a file and print out output to console. No GUIs
needed.
• code will be assessed on correctness, simplicity, readability and cleanliness
• If you have any doubts make a sensible assumption and document it
When you're ready with your assignment please send us link to an online code repository (github,
bitbucket, etc.) so we can review your code.

# Build : e-report application 
```java
 Step 1 : Download project from git repo from the following location and extract project
	    https://github.com/satya-moganti/e-report.git
 Step 2 : Build a jar using following command
            cd e-report
	    mvn  package -Dskiptests=true
 Step 3 : Run application using following command
            cd target
	    java -jar e-report.jar	
```

# Implementation 
```java
Assumptions :
hierarchy being added for employee to capture the depyh of managers from top to bottom
The hierarchy starts at index 0 (CEO) and goes deeper
Reporting line is considered too long if managers are more than > 4
A manager is any employee who has others reporting to them
Expected salary of a manager should be higher than the sum of average salaries of their direct reports
```
## Data Source
```java
data.csv file	
```
