<?php
$connection = mysql_connect("fdb6.biz.nf","1435818_xec","PcK6HHCDb8vbpHYH");
mysql_select_db("1435818_xec") or die( "Unable to select database");


$jsonData = array();
$userApps = array();
$app = array();

$result = mysql_query("select * from DisTab");
$count = mysql_num_rows($result);
echo $count;


  mysql_close();
?>