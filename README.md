# Proyecto Spring Boot con Docker

Este proyecto es una API desarrollada en **Spring Boot**, que se ejecuta dentro de un contenedor **Docker**.

## 🚀 Requisitos previos

Antes de ejecutar el proyecto, asegúrate de tener instalado:

- [Java JDK 17+](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)
- [Docker](https://www.docker.com/get-started)

## 📦 Construcción y ejecución

### 1️⃣ Generar el archivo `.jar`

Ejecuta el siguiente comando en la raíz del proyecto para compilar y empaquetar la aplicación:

```sh
mvn clean package
```

Esto generará un archivo `.jar` dentro de la carpeta `target/`, por ejemplo:

```
target/mi-api.jar
```

### 2️⃣ Construir la imagen de Docker

Ejecuta el siguiente comando para construir la imagen Docker de la aplicación:

```sh
docker build -t mi-api .
```

### 3️⃣ Ejecutar el contenedor

Para iniciar el contenedor con la API, usa el siguiente comando:

```sh
docker run -p 8080:8080 mi-api
```

Esto ejecutará la aplicación en el puerto **8080**. Puedes acceder a la API en:

```
http://localhost:8080
```

## 📂 Configuración de la base de datos con Terraform

El proyecto incluye una carpeta llamada **terraform-mysql-rds**, donde se encuentran todos los archivos necesarios para desplegar una base de datos MySQL en **AWS** utilizando **Terraform**. 

Para desplegar la base de datos, sigue estos pasos:

1. Accede a la carpeta `terraform-mysql-rds`:
   ```sh
   cd terraform-mysql-rds
   ```
2. Inicializa Terraform:
   ```sh
   terraform init
   ```
3. Revisa el plan de infraestructura:
   ```sh
   terraform plan
   ```
4. Aplica la configuración para desplegar la base de datos en AWS:
   ```sh
   terraform apply
   ```

Esto creará una instancia de base de datos en AWS lista para conectarse con la API. Asegúrate de configurar correctamente las variables de entorno para la conexión.

---

¡Listo! Ahora puedes ejecutar tu API en Docker y desplegar la base de datos en AWS con Terraform. 🚀

