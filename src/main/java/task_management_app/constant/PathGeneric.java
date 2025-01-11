package task_management_app.constant;

public class PathGeneric {

    // General
    public static final String PATH_ID = "/{id}";
    
    // Board Endpoints
    public static final String PATH_API_BOARDS = "/api/boards";
    public static final String PATH_CREATE_BOARD = "/create/{ownerId}";
    public static final String PATH_GET_BOARDS_BY_OWNER = "/{ownerId}";
    
    // Column Endpoints
    public static final String PATH_API_COLUMNS = "/api/columns";
    public static final String PATH_CREATE_COLUMN = "/create/{boardId}";
    public static final String PATH_GET_COLUMNS_BY_BOARD = "/{boardId}";
    
    // Task Endpoints
    public static final String PATH_API_TASKS = "/api/tasks";
    public static final String PATH_CREATE_TASK = "/create/{columnId}";
    public static final String PATH_GET_TASKS_BY_COLUMN = "/{columnId}";
}
