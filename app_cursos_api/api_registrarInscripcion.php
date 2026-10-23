<?php
header("Content-Type: application/json; charset=UTF-8");
require_once "../../config/conexion.php";

// Recibimos los parámetros de la inscripción
$id_curso = isset($_POST['id_curso']) ? intval($_POST['id_curso']) : 0;
$id_alumno = isset($_POST['id_alumno']) ? intval($_POST['id_alumno']) : 0;

// Validamos que los IDs sean correctos
if ($id_curso <= 0 || $id_alumno <= 0) {
    header("HTTP/1.1 400 Bad Request");
    echo json_encode(["error" => "El id_curso y el id_alumno son obligatorios"]);
    exit;
}

try {
    // Preparamos la inserción usando los nombres reales de las columnas de tu tabla
    $stmt = $pdo->prepare("INSERT INTO inscripciones (fecha_registro, id_curso, id_alumno) VALUES (NOW(), ?, ?)");
    $resultado = $stmt->execute([$id_curso, $id_alumno]);

    if ($resultado) {
        header("HTTP/1.1 201 Created");
        echo json_encode([
            "success" => true,
            "mensaje" => "Inscripción registrada correctamente"
        ]);
    } else {
        header("HTTP/1.1 500 Internal Server Error");
        echo json_encode(["error" => "No se pudo completar la inscripción"]);
    }

} catch (Exception $e) {
    header("HTTP/1.1 500 Internal Server Error");
    echo json_encode(["error" => "Error en el servidor: " . $e->getMessage()]);
}
?>