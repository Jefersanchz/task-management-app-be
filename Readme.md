
# Instrucciones para Ejecutar el Contenedor Docker

Este proyecto está contenerizado para facilitar la ejecución en un entorno aislado. A continuación se detallan los pasos para construir y ejecutar el contenedor Docker, y para probar los endpoints en Swagger.

## Prerrequisitos
Asegúrate de tener Docker y Docker Compose instalados en tu máquina. Si estás utilizando Docker Desktop, ya viene con Docker Compose integrado.

## Paso 1: Construcción de la Imagen Docker
En la raíz del proyecto, abre una terminal y ejecuta el siguiente comando para construir la imagen Docker:

```bash
docker build -t taskmanagement .
```

Este comando compilará el código y creará la imagen Docker llamada `taskmanagement`, que contiene todas las dependencias necesarias.

## Paso 2: Ejecución del Contenedor con Docker Compose
Para ejecutar el contenedor, usa Docker Compose con el siguiente comando:

```bash
docker-compose up --build
```

Este comando:

- Construye la imagen si aún no existe o si ha habido cambios.
- Ejecuta el contenedor en el puerto `9000`.

La aplicación estará accesible en `http://localhost:9000`.

## Paso 3: Probar los Endpoints en Swagger
La aplicación utiliza Springdoc OpenAPI para generar documentación automática de la API en Swagger. Una vez que la aplicación esté ejecutándose, sigue estos pasos para acceder y probar los endpoints:

1. Abre un navegador y ve a `http://localhost:9000/swagger-ui.html`.
2. En la interfaz de Swagger, encontrarás la documentación de todos los endpoints disponibles.
3. Haz clic en **Try it out** junto a cada endpoint para probarlo directamente desde la interfaz de Swagger.

### Endpoints Disponibles


#### Users (Usuarios)

- `POST /api/users/register`: Crea un usuario.
- `POST /api/users/login`: Iniciar sesión.
- `POST /api/products`: Crea un nuevo producto.

#### Boards (Tableros)

- `GET /api/boards/{ownerId}` Obtiene los tableros del usuario por ID.
- `POST /api/boards/create/{ownerId}`: Crea un tablero por su ID de usuario.
- `PUT /api/boards/update/{boardId}`: Actualiza un tablero por ID y usuario.
- `DELETE /api/boards/deleteByOwner/{ownerId}`: Elimina todos los tableros por ID usuario.
- `DELETE /api/boards/delete/{boardId}/{ownerId}`Elimina un tablero en especifico por ID de tablero y ID del usuario

#### Columns (Columnas)

- `POST /api/columns/create/{boardId}` Crea columnas asociadasa a un tablero ID.
- `GET /api/columns/{boardId}`: Trae las columnas asociadas a un tablero ID.

#### Task (Tareas)

- `PUT /api/tasks/update/{taskId}` Obtiene los tableros del usuario por ID.
- `POST /api/tasks/create/{columnId}`: Crea una tarea con su columna.
- `GET /api/tasks/{columnId}`: Obtiene tareas por columna.
- `GET /api/tasks/all`: Obtiene todas las tareas 
- `DELETE /api/tasks/delete/{taskId}`: Elimina las tareas por ID especifico.

## Paso 4: Detener el Contenedor
Para detener y eliminar el contenedor una vez que hayas terminado de probar el backend, ejecuta:

```bash
docker-compose down
```

Este comando apagará y eliminará el contenedor.
