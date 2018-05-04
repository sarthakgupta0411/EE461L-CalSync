<?php

require "calendar_database_init.php";

$user_name = $_POST["user_name"];
$calendar_name = $_POST["calendar_name"];
$assignment = $_POST["assignment"];
$duedate = $_POST["duedate"];
$duetime = $_POST["duetime"];
$description = $_POST["description"];
$apitoken = $_POST["apitoken"];

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