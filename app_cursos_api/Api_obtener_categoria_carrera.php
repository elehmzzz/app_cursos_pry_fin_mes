
<?php
header("Content-Type: application/json; charset=utf-8");

require_once "config/conexion.php";

try {
    if (!isset($_GET["id_carrera"])) {
        http_response_code(400);
        echo json_encode([
            "error" => "Debes proporcionar el ID de la carrera..."
        ], JSON_UNESCAPED_UNICODE);
        exit;
    }

    $idCarrera = filter_input(
        INPUT_GET,
        "id_carrera",
        FILTER_VALIDATE_INT
    );

    if ($idCarrera === false || $idCarrera === null || $idCarrera <= 0) {
        http_response_code(400);
        echo json_encode([
            "error" => "El ID debe ser un número entero positivo"
        ], JSON_UNESCAPED_UNICODE);
        exit;
    }

    $sql = "SELECT * FROM cursos WHERE id_carrera = ?";
    $consulta = $pdo->prepare($sql);
    $consulta->execute([$idCarrera]);

    $cursos = $consulta->fetchAll(PDO::FETCH_ASSOC);

    echo json_encode($cursos, JSON_UNESCAPED_UNICODE);

} catch (PDOException $e) {
    http_response_code(500);
    echo json_encode([
        "error" => "Error al consultar los cursos"
    ], JSON_UNESCAPED_UNICODE);
}
?>
