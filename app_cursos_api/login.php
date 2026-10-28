<?php
// Ocultar advertencias HTML para evitar romper la respuesta JSON de Retrofit
ini_set('display_errors', 0);
error_reporting(0);

header("Content-Type: application/json; charset=UTF-8");
require_once "config/conexion.php"; // Incluye tu conexión PDO ($pdo)

// Decodificar el cuerpo JSON enviado por Retrofit desde Android
$jsonInput = file_get_contents("php://input");
$data = json_decode($jsonInput, true);

// Capturamos los campos correctos de acuerdo con la tabla 'usuarios'
$matricula = isset($data['matricula']) ? trim($data['matricula']) : '';
$contrasena = isset($data['contrasena']) ? trim($data['contrasena']) : '';

if (empty($matricula) || empty($contrasena)) {
    header("HTTP/1.1 400 Bad Request");
    echo json_encode([
        "exito" => false,
        "mensaje" => "Debe ingresar la matrícula y la contraseña"
    ]);
    exit;
}

try {
    // Buscamos al usuario únicamente por su matrícula para obtener su contraseña encriptada
    $stmt = $pdo->prepare("SELECT id_usuario, matricula, nombre, apellido_paterno, apellido_materno, correo_institucional, contrasena FROM usuarios WHERE matricula = ?");
    $stmt->execute([$matricula]);
    $usuario = $stmt->fetch();

    // Verificamos si el usuario existe y si la contraseña coincide con el hash seguro de la base de datos
    if ($usuario && password_verify($contrasena, $usuario['contrasena'])) {
        echo json_encode([
            "exito" => true,
            "mensaje" => "¡Bienvenido/a " . $usuario['nombre'] . "!",
            "id_usuario" => (int)$usuario['id_usuario'],
            "matricula" => $usuario['matricula'],
            "nombre" => $usuario['nombre'],
            "apellido_paterno" => $usuario['apellido_paterno'],
            "correo_institucional" => $usuario['correo_institucional']
        ]);
    } else {
        echo json_encode([
            "exito" => false,
            "mensaje" => "Matrícula o contraseña incorrectos"
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