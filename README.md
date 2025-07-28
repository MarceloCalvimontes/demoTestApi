# Marcelo Calvimontes Acuña
# marcelo.calvimontes.ac@gmail.com
## Solución propuesta
```
Realice la creación de modelos, repositorios y controladores para las entidades de cliente y vehículo con sus respectivos atributos.
Creacion de los servicios solicitados (CRUD) y servicios de listado de clientes, vehículos y vehículos pertenecientes a un cliente.
Adición del atributo booleano "habilitado" a clientes y vehiculos, con el objetivo de no eliminar completamente los datos,
por ejemplo si eliminamos un cliente tambien se eliminarian todos los vehiculos relacionados con el, lo que puede no ser conveniente y un riesgoso por la perdida completa de datos.
Una mejor opción es deshabilitar al cliente y conservar los datos en caso de que sean necesarios en el futuro.

Creacion de servicios adicionales:
alternarEstadoCliente Cambia el estado del cliente de habilitado a inhabilitado o viceversa
filtrarClientesPorEstado Filtra una lista de clientes por estado, el estado debe ser enviado en el body
filtrarVehiculosPorEstadoYDocumento Filtra una lista de vehiculos por estado y número de documento de cliente, ambos datos deben ser enviados en el body
alternarEstadoVehiculo Cambia el estado del vehículo de habilitado a inhabilitado o viceversa

```

