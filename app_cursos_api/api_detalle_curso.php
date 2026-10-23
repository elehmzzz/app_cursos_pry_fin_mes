
<?php
header("Content-Type: application/json; charset=utf-8");

require_once "conexion.php";

try {
    if (!isset($_GET["id"])) {
        http_response_code(400);
        echo json_encode([
            "error" => "Debes proporcionar el ID del curso"
        ], JSON_UNESCAPED_UNICODE);
        exit;
    }

    $id = filter_input(INPUT_GET, "id", FILTER_VALIDATE_INT);

    if ($id === false || $id === null || $id <= 0) {
        http_response_code(400);
        echo json_encode([
            "error" => "El ID debe ser un número entero positivo"
        ], JSON_UNESCAPED_UNICODE);
        exit;
    }

    $sql = "SELECT * FROM cursos WHERE id_curso = ?";
    $consulta = $pdo->prepare($sql);
    $consulta->execute([$id]);

    $curso = $consulta->fetch();

    if ($curso) {
        echo json_encode($curso, JSON_UNESCAPED_UNICODE);
    } else {
        http_response_code(404);
        echo json_encode([
            "error" => "No se encontró el curso"
        ], JSON_UNESCAPED_UNICODE);
    }

} catch (PDOException $e) {
    http_response_code(500);
    echo json_encode([
        "error" => "Error al consultar el curso"
    ], JSON_UNESCAPED_UNICODE);
}
?>
