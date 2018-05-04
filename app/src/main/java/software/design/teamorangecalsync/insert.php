 <?php  
 require "database_init.php";  
 $user_name = "nikhil";  
 $user_pass = "pokemon";
$calendar_type = "canvas"; 
$calendar_name = "canvascal1";
 $sql_query = "insert into usertable values('$user_name','$user_pass','$calendar_type','$calendar_name');";  
 
 if(mysqli_query($con,$sql_query)){
	 echo "Data Insertion success";
 }
 else{
	 	 echo "Error inserting in database ...", .mysqli_error($con);
}
 ?>  
 