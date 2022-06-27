# Diccionario de datos

## Tabla: Category

Representa la colección de categorías a las que puede pertencer una película.

| Campo | Tipo    | ¿Nulleable? | ¿PK/FK? | Descripción                                                         |
| ----- | ------- | ----------- | ------- | ------------------------------------------------------------------- |
| code  | varchar | X           | PK      | Código único que representa a una categoría. Sigue el formato CT_## |
| name  | varchar | X           | --      | Cadena de texto que representa el nombre de categoría               |

## Tabla: Movie

Representa la colección de películas que el cine posee para proyectar en sus salas

| Campo       | Tipo    | ¿Nulleable? | ¿PK/FK? | Descripción                                                              |
| ----------- | ------- | ----------- | ------- | ------------------------------------------------------------------------ |
| code        | varchar | X           | PK      | Código único que representa a una película. Sigue el formato XXXXXXXXX-X |
| title       | varchar | X           | --      | Título de la película                                                    |
| length      | int     | X           | --      | Duración en minutos                                                      |
| id_category | varchar | ✓           | FK      | Categoría de la película. Es el id de la tabla Category                  |

## Tabla: Schedule

Representa la cartelera del cine, donde se almacena la colección de horarios en los que se presentan las películas

| Campo     | Tipo      | ¿Nulleable? | ¿PK/FK? | Descripción                                                                           |
| --------- | --------- | ----------- | ------- | ------------------------------------------------------------------------------------- |
| id        | serial    | X           | PK      | Correlativo auto generado que representa a un horario de función para una película    |
| timestamp | timestamp | X           | --      | Fecha y hora de la función                                                            |
| price     | numeric   | X           | --      | Precio de la función                                                                  |
| capacity  | int       | X           |         | Capacidad máxima de personas. Debe de validar al momneto de ralizar una nueva reserva |
| id_movie  | varchar   | X           | FK      | película a proyectar. Es el id de la tabla Movie                                      |

## Tabla: Booking

Representa la reserva de puestos para una función de una película e específico.

| Campo       | Tipo      | ¿Nulleable? | ¿PK/FK? | Descripción                                                                        |
| ----------- | --------- | ----------- | ------- | ---------------------------------------------------------------------------------- |
| id          | serial    | X           | PK      | Correlativo auto generado que representa a una reserva para una función específica |
| timestamp   | timestamp | X           | --      | Fecha y hora de la reserva                                                         |
| id_user     | int       | X           | FK      | Usuario que realizó la reserva. Es el id de la tabla User                          |
| id_schedule | int       | X           | FK      | Función que se ha reservado. Es el id de la tabla Schedule                         |

## Tabla: User

Representa al usuario que utiliza la aplicación.

| Campo    | Tipo    | ¿Nulleable? | ¿PK/FK? | Descripción                                                                        |
| -------- | ------- | ----------- | ------- | ---------------------------------------------------------------------------------- |
| id       | serial  | X           | PK      | Correlativo auto generado que representa a una reserva para una función específica |
| username | varchar | X           | --      | Nombre de usuario. Debe de ser único                                               |
| email    | varchar | X           | --      | Correo del usuario. Debe de ser único                                              |
| password | varchar | X           | --      | Constraseña del usuario. Debe de estar encriptada                                  |

