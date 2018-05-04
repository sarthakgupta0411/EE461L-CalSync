<?php

require "calendar_database_init.php";

$user_name = "Nikhil";
$password = "tepig";
$calendar_type = "canvas";
$calendar_name = "canvascal1";

$sql_query = "insert into usertable values ('$user_name','$password','$calendar_type','$calendar_name');";

if(mysqli_query($con, $sql_query))
{
echo "<h3> Calendar Data insertion successful I love tepig";
}
else
{
echo "Data insertion error".mysqli_error($con);
}

?>