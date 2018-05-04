 <?php  
 $db_name = "calendarinfo";  
 $mysql_user = "nikhil";  
 $mysql_pass = "tepig";  
 $server_name = "localhost";  
 $con = mysqli_connect($server_name,$mysql_user,$mysql_pass,$db_name);  
 if(!$con){
	 echo "Error connecting to database ...", .mysqli_connect_error();
 }
 else{
	 echo "<h3>Database connection successful</h3>";
 }
 ?>  