<?php
// Ocultar advertencias HTML para evitar romper la respuesta JSON de Retrofit
ini_set('display_errors', 0);
error_reporting(0);

header("Content-Type: application/json; charset=UTF-8");
require_once "config/conexion.php"; 

// Decodificar el cuerpo JSON enviado desde Retrofit (Android)
$jsonInput = file_get_contents("php://input");
$data = json_decode($jsonInput, true);

// Capturar y limpiar los datos recibidos
$matricula = isset($data['matricula']) ? trim($data['matricula']) : '';
$nombre = isset($data['nombre']) ? trim($data['nombre']) : '';
$apellido_paterno = isset($data['apellido_paterno']) ? trim($data['apellido_paterno']) : '';
$apellido_materno = isset($data['apellido_materno']) ? trim($data['apellido_materno']) : '';
$correo_institucional = isset($data['correo_institucional']) ? trim($data['correo_institucional']) : '';
$contrasena = isset($data['contrasena']) ? trim($data['contrasena']) : '';

// 1. Validar que los campos obligatorios no estén vacíos
if (empty($matricula) || empty($nombre) || empty($apellido_paterno) || empty($correo_institucional) || empty($contrasena)) {
    header("HTTP/1.1 400 Bad Request");
    echo json_encode([
        "exito" => false,
        "mensaje" => "Todos los campos obligatorios deben ser completados"
    ]);
    exit;
}

try {
    // 2. Comprobar si ya existe un usuario con la misma matrícula o correo institucional
   $stmtCheck = $pdo->prepare("SELECT id_usuario FROM usuarios WHERE matricula = ? OR correo_institucional = ?");
   $stmtCheck->execute([$matricula, $correo_institucional]);

   $usuarioExistente = $stmtCheck->fetch();

   if ($usuarioExistente) {
        header("HTTP/1.1 409 Conflict");
        echo json_encode([
            "exito" => false,
            "mensaje" => "Ya existe una cuenta registrada con esta matrícula o correo institucional"
        ]);
        exit;
    }

    // 3. Generar un hash seguro para la contraseña (NUNCA texto plano)
    $contrasenaHash = password_hash($contrasena, PASSWORD_DEFAULT);
    
    // Obtener la fecha actual del sistema para la fecha de creación
    $fechaCreacion = date('Y-m-d');

    // 4. Guardar el nuevo usuario en la base de datos
    $stmtInsert = $pdo->prepare("INSERT INTO usuarios (matricula, nombre, apellido_paterno, apellido_materno, correo_institucional, contrasena, fecha_creacion) VALUES (?, ?, ?, ?, ?, ?, ?)");
    $resultado = $stmtInsert->execute([
        $matricula, 
        $nombre, 
        $apellido_paterno, 
        $apellido_materno, 
        $correo_institucional, 
        $contrasenaHash, 
        $fechaCreacion
    ]);

    if ($resultado) {
        header("HTTP/1.1 201 Created");
        echo json_encode([
            "exito" => true,
            "mensaje" => "¡Registro de usuario exitoso!",
            "id_usuario" => (int)$pdo->lastInsertId()
        ]);
    } else {
        header("HTTP/1.1 500 Internal Server Error");
        echo json_encode([
            "exito" => false,
            "mensaje" => "No se pudo completar el registro en la base de datos"
        ]);
    }

} catch (Exception $e) {
    header("HTTP/1.1 500 Internal Server Error");
    echo json_encode([
        "exito" => false,
        "mensaje" => "Error en el servidor: " . $e->getMessage()
    ]);
}
?>