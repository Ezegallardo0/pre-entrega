# FENIXIA

FENIXIA es un emprendimiento dedicado a los muebles. Este proyecto contiene
una aplicación de consola en Java para gestionar un catálogo de productos y
registrar pedidos.

## Funcionalidades

- Agregar productos con nombre, precio y stock.
- Listar los productos disponibles.
- Buscar un producto por ID o nombre y actualizar su precio o stock.
- Eliminar productos por ID.
- Crear pedidos con uno o más productos.
- Consultar pedidos registrados durante la ejecución.
- Descontar del inventario las unidades incluidas en un pedido y avisar si no
  hay stock suficiente.

## Requisitos

- JDK 14 o posterior.

## Cómo ejecutar

Desde la carpeta del proyecto, compila los archivos Java:

```bash
javac *.java
```

Luego inicia la aplicación:

```bash
java Main
```

Seguí las opciones que aparecen en el menú para administrar el catálogo y los
pedidos.

## Datos de ejemplo

El inventario se guarda en memoria y se reinicia al cerrar la aplicación. Los
productos cargados inicialmente en `Main.java` son ejemplos de servicios de
diseño; se pueden reemplazar por los muebles que ofrece FENIXIA.

## Tecnologías

- Java
- Colecciones de Java (`ArrayList`)
- Interfaz de consola
