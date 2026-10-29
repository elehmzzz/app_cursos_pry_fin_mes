
<?php
header("Content-Type: application/json; charset=UTF-8");

require_once "config/conexion.php";

try {
    if (!isset($_GET["id_curso"])) {
        echo json_encode([
            "error" => "Debes proporcionar el ID del curso"
        ]);
        exit;
    }

    $id_curso = (int) $_GET["id_curso"];

    $sql = "SELECT
                c.id_curso,
                c.nombre,
                COUNT(i.id_inscripcion) AS inscritos,
                c.cupo_maximo
            FROM cursos c
            LEFT JOIN inscripciones i
                ON c.id_curso = i.id_curso
            WHERE c.id_curso = :id_curso
            GROUP BY
                c.id_curso,
                c.nombre,
                c.cupo_maximo";

    $stmt = $pdo->prepare($sql);
    $stmt->bindParam(":id_curso", $id_curso, PDO::PARAM_INT);
    $stmt->execute();

    $curso = $stmt->fetch(PDO::FETCH_ASSOC);

    if (!$curso) {
        echo json_encode([
            "error" => "No se encontró el curso"
        ]);
        exit;
    }

    $curso["id_curso"] = (int) $curso["id_curso"];
    $curso["inscritos"] = (int) $curso["inscritos"];
    $curso["cupo_maximo"] = (int) $curso["cupo_maximo"];

    echo json_encode($curso, JSON_UNESCAPED_UNICODE);

} catch (PDOException $e) {
    http_response_code(500);
    echo json_encode([
        "error" => "Error al consultar los datos del curso"
    ]);
}
?>
