

<?php
header("Content-Type: application/json; charset=utf-8");

require_once "conexion.php";

try {
    $sql = "SELECT * FROM cursos";
    $consulta = $pdo->query($sql);
    $cursos = $consulta->fetchAll();

    echo json_encode($cursos, JSON_UNESCAPED_UNICODE);

} catch (PDOException $e) {
    http_response_code(500);
    echo json_encode([
        "error" => "Error al consultar los cursos"
    ], JSON_UNESCAPED_UNICODE);
}
?>

