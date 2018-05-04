<?php

require "calendar_database_init.php";

$user_name = "Nikhil";
$calendar_name = "canvascal1";
$assignment = "ee461l final project";
$duedate = "05/04/18";
$duetime = "11:59:00";
$description = "final project";
$apitoken = "89asdf89723";

$sql_query = "insert into canvasevent (username,calendarname,assignment,duedate,duetime,description,apitoken) values ('$user_name','$calendar_name','$assignment','$duedate','$duetime','$description','$apitoken');";

if(mysqli_query($con, $sql_query))
{
echo "<h3> Canvas event insertion successful I love tepig";
}
else
{
echo "Data insertion error".mysqli_error($con);
}

?>