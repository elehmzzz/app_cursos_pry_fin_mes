
<?php
header("Content-Type: application/json; charset=UTF-8");

require_once "conexion.php";

$sql = "SELECT * FROM cursos";

$resultado = $conn->query($sql);

$cursos = array();

while ($fila = $resultado->fetch_assoc()) {
    $cursos[] = $fila;
}

echo json_encode($cursos, JSON_UNESCAPED_UNICODE);

$conn->close();
?>
