package task_management_app.constant;

public class PathGeneric {

    // General
    public static final String PATH_ID = "/{id}";

    // Board Endpoints
    public static final String PATH_API_BOARDS = "/api/boards";
    public static final String PATH_CREATE_BOARD = "/create/{ownerId}";
    public static final String PATH_GET_BOARDS_BY_OWNER = "/{ownerId}";
    public static final String PATH_UPDATE_BOARD = "/update/{boardId}";
    public static final String PATH_DELETE_BOARD = "/deleteByOwner/{ownerId}";
    public static final String PATH_DELETE_SPECIFIC_BOARD = "/delete/{boardId}/{ownerId}";

    // Column Endpoints
    public static final String PATH_API_COLUMNS = "/api/columns";
    public static final String PATH_CREATE_COLUMN = "/create/{boardId}";
    public static final String PATH_GET_COLUMNS_BY_BOARD = "/{boardId}";

    // Task Endpoints
    public static final String PATH_API_TASKS = "/api/tasks";
    public static final String PATH_CREATE_TASK = "/create/{columnId}";
    public static final String PATH_GET_TASKS_BY_COLUMN = "/{columnId}";
    // Ruta para obtener todas las tareas
    public static final String PATH_GET_ALL_TASKS = "/all";
    // Ruta para actualizar una tarea específica
    public static final String PATH_UPDATE_TASK = "/update/{taskId}";
    public static final String PATH_DELETE_TASK = "/delete/{taskId}";
    

}
