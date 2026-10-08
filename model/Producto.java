package model;

public class Producto {
    private static int contadorId = 1;
    private int id;
    private String nombre;
    private double precio;
    private int stock;

    public Producto(String nombre, double precio, int stock) {
        this.id = contadorId++;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void reducirStock(int cantidad) throws StockInsuficienteException {
        if (cantidad > this.stock) {
            throw new StockInsuficienteException("Stock insuficiente para: " + nombre + " (Disponible: " + stock + ")");
        }
        this.stock -= cantidad;
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Producto: %-25s | Precio: $%.2f | Stock: %d", id, nombre, precio, stock);
    }
}