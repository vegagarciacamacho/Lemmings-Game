# 🧩 Proyecto Lemmings

Este proyecto implementa una versión del clásico juego **Lemmings**, estructurado en distintos paquetes que separan la lógica del juego, el control, las excepciones, la vista y las utilidades.  

---

## 🗂️ Estructura del proyecto

### 🧠 `control/`
Contiene las clases encargadas de **gestionar el flujo del juego y los comandos del usuario**.

#### 📁 `commands/`
Implementa el patrón *Command*, donde cada acción del jugador se modela como una clase independiente:
- **Command.java** → Clase abstracta base para todos los comandos.
- **CommandGenerator.java** → Crea instancias de comandos según la entrada del usuario.
- **ExitCommand.java** → Cierra el juego.
- **HelpCommand.java** → Muestra ayuda sobre los comandos disponibles.
- **LoadCommand.java** → Carga una partida guardada.
- **NoParamsCommand.java** → Base para comandos sin parámetros.
- **ResetCommand.java** → Reinicia el estado del juego.
- **SaveCommand.java** → Guarda la partida actual.
- **SetRoleCommand.java** → Cambia el rol de un lemming.
- **UpdateCommand.java** → Avanza el juego un turno.

#### ⚠️ `exceptions/`
Define excepciones personalizadas que controlan errores específicos:
- **CommandException.java** → Base de todas las excepciones de comandos.
- **CommandExecuteException.java** → Error al ejecutar un comando.
- **CommandParseException.java** → Error al interpretar un comando.
- **GameLoadException.java** → Fallo al cargar una partida.
- **GameModelException.java** → Error general en el modelo.
- **GameParseException.java** → Error al analizar la configuración del juego.
- **ObjectParseException.java** → Error al interpretar un objeto del juego.
- **OffBoardException.java** → Movimiento fuera del tablero.
- **RoleParseException.java** → Error al interpretar un rol de lemming.

#### ⚙️ `Controller.java`
Clase principal del paquete `control`.  
Coordina los comandos, el modelo y la vista para mantener la interacción del usuario con el juego.

---

### 🎮 `logic/`
Contiene la **lógica del juego**, los objetos, roles, y las clases que representan su estado y comportamiento.

#### 📁 `gameobjects/`
Clases que representan los objetos físicos del mundo del juego:
- **ExitDoor.java** → Puerta de salida de los lemmings.  
- **GameItem.java** → Interfaz base para todos los elementos del juego.  
- **GameObject.java** → Clase base para objetos del mundo.  
- **GameObjectFactory.java** → Creador de objetos del juego.  
- **Lemming.java** → Clase que representa a un lemming.  
- **MetalWall.java** → Pared indestructible.  
- **Wall.java** → Pared normal, puede ser cavada o destruida.  

#### 📁 `lemmingRoles/`
Define los **roles o comportamientos** de los lemmings:
- **AbstractRole.java** → Clase base de todos los roles.  
- **DownCaverRole.java** → Lemming que cava hacia abajo.  
- **LemmingRole.java** → Interfaz común de roles.  
- **LemmingRoleFactory.java** → Generador de instancias de roles.  
- **ParachuterRole.java** → Lemming que cae lentamente.  
- **WalkerRole.java** → Lemming que camina en línea recta.  

#### Clases dentro de `logic/`
- **Direction.java** → Enum que define direcciones posibles (izquierda, derecha, abajo...).  
- **FileGameConfiguration.java** → Configura el juego a partir de un archivo.  
- **Game.java** → Núcleo principal de la lógica del juego.  
- **GameConfiguration.java** → Define la configuración del nivel actual.  
- **GameModel.java** → Representa el modelo de datos del juego.  
- **GameObjectContainer.java** → Contenedor de objetos activos.  
- **GameStatus.java** → Estado del juego (en curso, victoria, derrota...).  
- **GameWorld.java** → Representa el tablero o mundo del juego.  
- **Position.java** → Representa coordenadas y posiciones.  

---

### 🧩 `util/`
Clases de utilidad y funciones auxiliares.
- **MyStringUtils.java** → Métodos estáticos para manejo de cadenas.

---

### 🎨 `view/`
Encargado de la **interfaz por consola**.
- **ConsoleColorsAnsiCodes.java** → Códigos ANSI para mostrar colores.  
- **ConsoleColorsView.java** → Añade colores a la vista de consola.  
- **ConsoleView.java** → Vista básica en consola.  
- **GameView.java** → Representación visual del estado del juego.  
- **Messages.java** → Contiene textos y mensajes usados en la interfaz.  

---

### 🚀 Archivos raíz
- **Main.java** → Punto de entrada del programa.  
- **Tests.java** → Pruebas y validaciones del sistema.  
- **module-info.java** → Declaración del módulo Java.  
- **alumnos.txt** → Información de los autores del proyecto.  

---

## 👨‍💻 Autores
- Ignacio Ramírez Suárez
- Vega García Camacho
---

## ⚙️ Ejecución

Compila y ejecuta el proyecto desde la raíz con:

```bash
javac -d bin src/tp1/**/*.java
java -cp bin tp1.Main
