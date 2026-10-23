<?php
$host = "localhost";
$user="root";
$password="";
$datebase= "cursos_db";

$conn=new mysqli($host, $user,$password, $datebase);
if($conn->connect_error){
    die("Error de coneccion:" . $conn->connect_error);

}
echo"Coneccion Exitosa";
?>