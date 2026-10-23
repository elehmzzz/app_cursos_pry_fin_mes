<?php
header("Content-Type: application/json; charset=UTF-8");
require_once "../../config/conexion.php";

try {
    // Consultamos los registros de la tabla inscripciones
    $stmt = $pdo->query("SELECT id_inscripcion, fecha_registro, id_curso, id_alumno FROM inscripciones ORDER BY id_inscripcion DESC");
    $inscripciones = $stmt->fetchAll();

    // Aseguramos que los identificadores se devuelvan como enteros
    foreach ($inscripciones as &$ins) {
        $ins['id_inscripcion'] = (int)$ins['id_inscripcion'];
        $ins['id_curso'] = (int)$ins['id_curso'];
        $ins['id_alumno'] = (int)$ins['id_alumno'];
    }

    // Devolvemos el resultado en formato JSON
    echo json_encode($inscripciones);

} catch (Exception $e) {
    header("HTTP/1.1 500 Internal Server Error");
    echo json_encode(["error" => "Error al obtener las inscripciones: " . $e->getMessage()]);
}
?>