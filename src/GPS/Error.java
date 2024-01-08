package GPS;

public enum Error {

    AGE_OUT_OF_RANGE("Error: edad fuera del rango (debe estar entre 14 y 100 años)."),
    INSUFFICIENT_PASSWORD_LENGTH("Error: la longitud de la contraseña es insuficiente (debe tener un mínimo de 3 caracteres)."),
    DUPLICATE_USERNAME("Error: el nombre de usuario ya existe."),
    DUPLICATE_PHONE("Error: el teléfono ya existe."),
    NOT_LOGGED_USER("Error: no se encontró ningún usuario que haya iniciado sesión."),
    USER_ALREADY_LOGGED_IN("Error: el usuario ya ha iniciado sesión."),
    USER_NOT_FOUND("Error: usuario no encontrado."),
    INVALID_COMMAND("Error: comando inválido."),
    ID_PLAN_NOT_FOUND("Error: id del plan no encontrado."),
    ID_ACTIVITY_NOT_FOUND("Error: id de la actividad no encontrado"),
    INSUFFICIENT_PRIVILEGES("Error: autorización denegada."),
    NO_CORRECT_ORDER("Error: el orden de la lista no se encuentra."),
    FULL_CAPACITY("Error: la capacidad máxima está agotada."),
    MATCHING_DATE("Error: coincidente con otra fecha."),

    MATCHING_PLAN("Error: no plan found to leave"),
    ALREADY_SUBSCRIBED("Error: ya está realizada la suscripción"),

    CLOSED_PLAN("Error: el plan ha sido cerrado."),
    NULL;

    private String message;

    Error(){
    }

    Error(String message){
        this.message = message;
    }

    public void writeln() {
        if (!this.isNull()) {
            System.out.println((this.message));
        }
    }

    public boolean isNull() {
        return this == Error.NULL;
    }
}

