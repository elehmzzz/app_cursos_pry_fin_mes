<?php
header('Content-Type: application/json');
include 'config/conexion.php'; // Incluye la conexión ($pdo)

try {
    // Consulta utilizando la columna 'nombre' de la tabla cursos
    $sql = "SELECT c.id_curso, c.codigo, c.nombre, c.cupo_maximo, c.imagen, c.id_carrera, (SELECT COUNT(*) FROM inscripciones i WHERE i.id_curso = c.id_curso) AS total_inscritos FROM cursos c";
            
    $stmt = $pdo->query($sql);
    $cursos = $stmt->fetchAll();

    echo json_encode([
        "success" => true,
        "cursos" => $cursos
    ]);

} catch (PDOException $e) {
    echo json_encode([
        "success" => false, 
        "message" => "Error al obtener el estado de los cursos: " . $e->getMessage()
    ]);
}
?>