
<?php
header("Content-Type: application/json; charset=utf-8");

require_once "config/conexion.php";

try {
    $sql = "SELECT id_carrera, nombre FROM carreras";
    $consulta = $pdo->query($sql);

    $carreras = $consulta->fetchAll(PDO::FETCH_ASSOC);

    echo json_encode($carreras, JSON_UNESCAPED_UNICODE);

} catch (PDOException $e) {
    http_response_code(500);
    echo json_encode([
        "error" => "Error al consultar las carreras"
    ], JSON_UNESCAPED_UNICODE);
}
?>

