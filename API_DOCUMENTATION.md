# Documentación de la API - Franquicias

## Descripción
Esta API permite gestionar franquicias, sucursales y productos, incluyendo operaciones de creación, actualización y eliminación de datos.

## Base URL (a partir del readme)
```
http://localhost:8080/api/v1
```

## Endpoints

### 1. Crear una nueva franquicia
**POST** `/franquicia`

#### Request Body:
```json
{
    "nombre": "pony malta"
}
```
#### Response:
```json
{
    "id": 8,
    "nombre": "pony malta"
}
```

---

### 2. Actualizar nombre de una franquicia
**PUT** `/franquicia/updateName`

#### Request Body:
```json
{
    "id": 8,
    "nombre": "ponymalta"
}
```
#### Response:
```json
{
    "id": 8,
    "nombre": "empresa ponymalta"
}
```

---

### 3. Crear una nueva sucursal
**POST** `/sucursal`

#### Request Body:
```json
{
    "nombre": "sucursal Sur 2",
    "franquicia": {
        "id": 5,
        "nombre": "cocacola"
    }
}
```
#### Response:
```json
{
    "id": 8,
    "nombre": "sucursal Sur 2",
    "franquicia": {
        "id": 5,
        "nombre": "cocacola"
    }
}
```

---

### 4. Actualizar nombre de una sucursal
**PUT** `/sucursal/updateName`

#### Request Body:
```json
{
    "id": 7,
    "nombre": "sucursal sur numero 2"
}
```
#### Response:
```json
{
    "id": 7,
    "nombre": "sucursal sur numero 2",
    "franquicia": {
        "id": 5,
        "nombre": "cocacola"
    }
}
```

---

### 5. Crear un nuevo producto
**POST** `/producto`

#### Request Body:
```json
{
    "nombre": "nuggets de pollo",
    "stock": 10000,
    "sucursal": {
        "id": 7
    }
}
```
#### Response:
```json
{
    "mensaje": "Producto creado con éxito.",
    "producto": {
        "id": 7,
        "nombre": "nuggets de pollo",
        "stock": 10000,
        "sucursal": {
            "id": 7,
            "nombre": null,
            "franquicia": null
        }
    }
}
```

---

### 6. Eliminar un producto
**DELETE** `/producto`

#### Request Body:
```json
{
    "id": 6,
    "sucursal": {
        "id": 7
    }
}
```
#### Response:
```
204 No Content
```

---

### 7. Actualizar nombre de un producto
**PUT** `/producto/updateName`

#### Request Body:
```json
{
    "id": 0,
    "nombre": "alitas nuevassss",
    "sucursal": {
        "id": 2
    }
}
```
#### Response:
```json
{
    "mensaje": "Nombre del producto actualizado con éxito."
}
```

---

### 8. Actualizar stock de un producto
**PUT** `/producto/updateStock`

#### Request Body:
```json
{
    "id": 7,
    "stock": 123000,
    "sucursal": {
        "id": 7
    }
}
```
#### Response:
```json
{
    "mensaje": "Stock del producto actualizado con éxito."
}
```

---

### 9. Obtener el producto con más stock por sucursal en una franquicia
**GET** `/producto/max-stock/{franquiciaId}`

#### Ejemplo de Request:
```
GET /producto/max-stock/5
```
#### Response:
```json
[
    {
        "id": 4,
        "nombre": "piernas de pollo",
        "stock": 1000,
        "sucursal": {
            "id": 3,
            "nombre": "sucursal general",
            "franquicia": {
                "id": 5,
                "nombre": "cocacola"
            }
        }
    },
    {
        "id": 7,
        "nombre": "nuggets de pollo",
        "stock": 123000,
        "sucursal": {
            "id": 7,
            "nombre": "sucursal sur numero 2",
            "franquicia": {
                "id": 5,
                "nombre": "cocacola"
            }
        }
    }
]
```

---

## Notas adicionales
- La API utiliza JSON como formato de intercambio de datos.
- Los errores y validaciones retornan códigos HTTP adecuados (400, 404, 500, etc.).
- Se recomienda usar Postman o cURL para probar los endpoints.

---

