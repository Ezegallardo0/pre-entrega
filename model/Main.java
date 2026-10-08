package model;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Producto> inventario = new ArrayList<>();
    private static List<Pedido> pedidosRealizados = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Carga de productos iniciales para Fénixia Visual Integral
        inventario.add(new Producto("Diseño de Identidad Visual", 45000.0, 5));
        inventario.add(new Producto("Pack de Template Feed IG", 15000.0, 10));
        inventario.add(new Producto("Ilustración Personalizada", 20000.0, 3));

        int opcion;
        do {
            System.out.println("\n=================================");
            System.out.println("   FÉNIXIA - VISUAL INTEGRAL     ");
            System.out.println("=================================");
            System.out.println("1. Agregar producto/servicio");
            System.out.println("2. Listar productos");
            System.out.println("3. Buscar/Actualizar producto");
            System.out.println("4. Eliminar producto");
            System.out.println("5. Crear un pedido");
            System.out.println("6. Listar pedidos realizados");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");
            
            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcion = 0;
            }

            switch (opcion) {
                case 1 -> agregarProducto();
                case 2 -> listarProductos();
                case 3 -> buscarOActualizarProducto();
                case 4 -> eliminarProducto();
                case 5 -> crearPedido();
                case 6 -> listarPedidos();
                case 7 -> System.out.println("¡Gracias por utilizar el sistema de Fénixia Visual Integral!");
                default -> System.out.println("Opción inválida. Intente de nuevo.");
            }
        } while (opcion != 7);
    }

    private static void agregarProducto() {
        System.out.print("Nombre del servicio/producto: ");
        String nombre = scanner.nextLine();
        System.out.print("Precio: ");
        double precio = Double.parseDouble(scanner.nextLine());
        System.out.print("Cantidad en stock: ");
        int stock = Integer.parseInt(scanner.nextLine());

        inventario.add(new Producto(nombre, precio, stock));
        System.out.println("¡Producto agregado correctamente!");
    }

    private static void listarProductos() {
        if (inventario.isEmpty()) {
            System.out.println("No hay productos en el catálogo.");
            return;
        }
        System.out.println("\n--- CATÁLOGO DE FÉNIXIA ---");
        for (Producto p : inventario) {
            System.out.println(p);
        }
    }

    private static void buscarOActualizarProducto() {
        System.out.print("Ingrese ID o nombre del producto: ");
        String busqueda = scanner.nextLine();
        
        Producto encontrado = null;
        for (Producto p : inventario) {
            if (String.valueOf(p.getId()).equals(busqueda) || p.getNombre().equalsIgnoreCase(busqueda)) {
                encontrado = p;
                break;
            }
        }

        if (encontrado != null) {
            System.out.println("Producto encontrado: " + encontrado);
            System.out.print("¿Desea actualizar los datos? (s/n): ");
            if (scanner.nextLine().equalsIgnoreCase("s")) {
                System.out.print("Nuevo precio (actual " + encontrado.getPrecio() + "): ");
                encontrado.setPrecio(Double.parseDouble(scanner.nextLine()));
                System.out.print("Nuevo stock (actual " + encontrado.getStock() + "): ");
                encontrado.setStock(Integer.parseInt(scanner.nextLine()));
                System.out.println("¡Producto actualizado!");
            }
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    private static void eliminarProducto() {
        System.out.print("Ingrese ID del producto a eliminar: ");
        int id = Integer.parseInt(scanner.nextLine());
        
        boolean eliminado = inventario.removeIf(p -> p.getId() == id);
        if (eliminado) {
            System.out.println("Producto eliminado con éxito.");
        } else {
            System.out.println("No se encontró producto con ese ID.");
        }
    }

    private static void crearPedido() {
        Pedido pedido = new Pedido();
        String continuar;

        do {
            listarProductos();
            System.out.print("Ingrese ID del producto a pedir: ");
            int id = Integer.parseInt(scanner.nextLine());
            
            Producto prodSeleccionado = null;
            for (Producto p : inventario) {
                if (p.getId() == id) {
                    prodSeleccionado = p;
                    break;
                }
            }

            if (prodSeleccionado != null) {
                System.out.print("Ingrese cantidad: ");
                int cantidad = Integer.parseInt(scanner.nextLine());
                
                try {
                    pedido.agregarProducto(prodSeleccionado, cantidad);
                    System.out.println("¡Agregado al pedido!");
                } catch (StockInsuficienteException e) {
                    System.out.println("ERROR: " + e.getMessage());
                }
            } else {
                System.out.println("Producto no encontrado.");
            }

            System.out.print("¿Desea agregar otro producto al pedido? (s/n): ");
            continuar = scanner.nextLine();
        } while (continuar.equalsIgnoreCase("s"));

        pedidosRealizados.add(pedido);
        pedido.mostrarResumen();
    }

    private static void listarPedidos() {
        if (pedidosRealizados.isEmpty()) {
            System.out.println("No hay pedidos registrados.");
            return;
        }
        for (Pedido ped : pedidosRealizados) {
            ped.mostrarResumen();
        }
    }
}