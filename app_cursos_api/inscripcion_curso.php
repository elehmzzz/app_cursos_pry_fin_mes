<?php
header('Content-Type: application/json');
include 'config/conexion.php'; // Incluye la conexión ($pdo)

// Recibir datos por POST
$id_usuario = isset($_POST['id_usuario']) ? $_POST['id_usuario'] : '';
$id_curso = isset($_POST['id_curso']) ? $_POST['id_curso'] : '';

if (empty($id_usuario) || empty($id_curso)) {
    echo json_encode(["success" => false, "message" => "Faltan datos obligatorios (id_usuario o id_curso)."]);
    exit();
}

try {
    // 1. Comprobar que el curso existe y obtener su cupo máximo
    $stmt = $pdo->prepare("SELECT cupo_maximo FROM cursos WHERE id_curso = ?");
    $stmt->execute([$id_curso]);
    $curso = $stmt->fetch();

    if (!$curso) {
        echo json_encode(["success" => false, "message" => "El curso no existe."]);
        exit();
    }
    $cupo_maximo = $curso['cupo_maximo'];

    // 2. Contar cuántos alumnos ya están inscritos en este curso
    $stmt_insc = $pdo->prepare("SELECT COUNT(*) as total FROM inscripciones WHERE id_curso = ?");
    $stmt_insc->execute([$id_curso]);
    $total_inscritos = $stmt_insc->fetch()['total'];

    if ($total_inscritos >= $cupo_maximo) {
        echo json_encode(["success" => false, "message" => "Cupo lleno. No hay lugares disponibles."]);
        exit();
    }

    // 3. Verificar si el usuario ya está inscrito en el curso
    $stmt_ver = $pdo->prepare("SELECT * FROM inscripciones WHERE id_curso = ? AND id_usuario = ?");
    $stmt_ver->execute([$id_curso, $id_usuario]);
    
    if ($stmt_ver->rowCount() > 0) {
        echo json_encode(["success" => false, "message" => "El usuario ya se encuentra inscrito en este curso."]);
        exit();
    }

    // 4. Registrar la inscripción con la fecha actual
    $fecha_inscripcion = date('Y-m-d');
    $stmt_insert = $pdo->prepare("INSERT INTO inscripciones (fecha_inscripcion, id_curso, id_usuario) VALUES (?, ?, ?)");
    
    if ($stmt_insert->execute([$fecha_inscripcion, $id_curso, $id_usuario])) {
        echo json_encode(["success" => true, "message" => "Inscripción realizada con éxito."]);
    } else {
        echo json_encode(["success" => false, "message" => "Error al registrar en la base de datos."]);
    }

} catch (PDOException $e) {
    echo json_encode(["success" => false, "message" => "Error en la BD: " . $e->getMessage()]);
}
?>