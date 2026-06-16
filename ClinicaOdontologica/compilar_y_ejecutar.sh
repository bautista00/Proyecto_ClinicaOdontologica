#!/bin/bash
# ============================================================
# Clinica Odontologica - Script de compilacion y ejecucion
# ============================================================

SRC_DIR="src"
OUT_DIR="out/classes"
JAR_FILE="out/ClinicaOdontologica.jar"
MANIFEST="out/MANIFEST.MF"

echo "=== Clinica Odontologica ==="
echo ""

# 1. Crear directorio de salida
mkdir -p "$OUT_DIR"
mkdir -p out

# 2. Compilar
echo "[1/3] Compilando fuentes..."
find "$SRC_DIR" -name "*.java" > /tmp/sources_clinica.txt
if javac -d "$OUT_DIR" @/tmp/sources_clinica.txt; then
    echo "      Compilacion exitosa."
else
    echo "      ERROR en compilacion. Verifica que Java JDK este instalado."
    exit 1
fi

# 3. Empaquetar en JAR
echo "[2/3] Generando JAR..."
printf 'Main-Class: Main\n' > "$MANIFEST"
jar cfm "$JAR_FILE" "$MANIFEST" -C "$OUT_DIR" .
echo "      JAR generado: $JAR_FILE"

# 4. Ejecutar
echo "[3/3] Iniciando aplicacion..."
echo ""
java -jar "$JAR_FILE"
