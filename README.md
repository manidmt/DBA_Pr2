# Práctica 2 de Desarrollo Basado en Agentes (DBA)

Este repositorio contiene el código de la **Práctica 2** de la asignatura **Desarrollo Basado en Agentes (DBA)**. En esta práctica, el agente debe navegar en un mapa con obstáculos y encontrar la ruta óptima hasta un objetivo usando el algoritmo de **búsqueda en anchura (BFS)**.

## Estructura del Proyecto

- **`src/Practica2_Package/`**: Contiene el código fuente de las clases principales:
  - **`Agente.java`**: Representa el agente que navega en el mapa siguiendo la ruta óptima calculada.
  - **`Coordenada.java`**: Clase para representar las coordenadas en el mapa.
  - **`Entorno.java`**: Clase que define el entorno en el que se mueve el agente.
  - **`Mapa.java`**: Gestiona la estructura del mapa, cargándolo desde un archivo y proporcionando información de accesibilidad.
  - **`Moverse.java`**: Implementa la lógica de movimiento del agente, incluyendo el algoritmo de búsqueda en anchura (BFS) para calcular la ruta óptima.

- **`Mapas/`**: Carpeta que contiene archivos `.txt` con mapas de prueba que definen entornos de navegación variados:
  - `mapWithComplexObstacle1.txt`, `mapWithHorizontalWall.txt`, etc.

- **`dist/`**: Contiene el archivo ejecutable `.jar` del proyecto:
  - **`DBA_Practica2.jar`**: Archivo ejecutable principal de la práctica.
  - **`lib/jade.jar`**: Librería JADE necesaria para ejecutar el proyecto.

- **`nbproject/`**: Archivos de configuración específicos de NetBeans, incluyendo `project.properties`, donde se debe asegurar que la ruta relativa a `jade.jar` esté configurada correctamente.

## Funcionalidades Principales

1. **Cargar y Representar el Mapa**: La clase `Mapa` carga un mapa desde un archivo `.txt`, lee sus dimensiones y posiciones de obstáculos, y proporciona métodos para verificar la accesibilidad de posiciones.
2. **Algoritmo de Búsqueda en Anchura (BFS)**: Implementado en `Moverse.java` para calcular la ruta más corta desde el inicio hasta el objetivo.
3. **Navegación del Agente**: La clase `Agente` se encarga de que el agente siga la ruta calculada, evitando obstáculos y alcanzando el objetivo.

## Requisitos

- **Java**: Asegúrate de tener instalado Java (versión 8 o superior).
- **JADE**: La librería JADE (`jade.jar`) debe estar disponible en la carpeta `dist/lib`.

## Ejecución

1. **Configurar Librerías**: Verifica que la ruta a `jade.jar` en `project.properties` esté configurada como `../../dist/lib/jade.jar` para que funcione en todos los entornos.
2. **Compilar y Ejecutar**:
   - Puedes compilar el proyecto en NetBeans o desde la terminal usando el archivo `.jar` ubicado en `dist/DBA_Practica2.jar`.
   - Ejecuta el `.jar` desde la terminal con el siguiente comando:
     ```bash
     java -jar dist/DBA_Practica2.jar
     ```

## Archivos de Configuración

- **`project.properties`**: Archivo de configuración de NetBeans en `nbproject/`, donde se configura el `classpath` y la ruta a `jade.jar` para compatibilidad entre diferentes entornos.

## Autor

**Grupo 204**