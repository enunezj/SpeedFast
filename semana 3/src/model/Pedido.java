package model;

import interfaces.Cancelable;
import interfaces.Despachable;
import interfaces.Rastreable;

import java.util.ArrayList;

// Clase abstracta que contiene los atributos y comportamientos
// comunes para todos los tipos de pedidos.
public abstract class Pedido implements Despachable, Cancelable, Rastreable {

    private final int codigo;
    private final String cliente;
    private final String direccion;
    private final double distanciaKm;
    private String repartidor;
    private String estado;

    private final ArrayList<String> historial;

    // Constructor
    public Pedido(int codigo, String cliente, String direccion, double distanciaKm) {

        this.codigo = codigo;
        this.cliente = cliente;
        this.direccion = direccion;
        this.distanciaKm = distanciaKm;

        this.repartidor = "Sin asignar";
        this.estado = "Creado";

        this.historial = new ArrayList<>();
    }


    // Método abstracto.
    // Cada tipo de pedido tendrá una asignación automática diferente.
    public abstract void asignarRepartidor();


    // Método abstracto.
    // Cada tipo de pedido calculará su tiempo de entrega
    // utilizando una lógica propia.
    public abstract int calcularTiempoEntrega();


    // Método para reservar un pedido.
    public void reservar() {

        if (estado.equals("Creado")) {

            estado = "Reservado";

            registrarEvento("Pedido reservado");

            System.out.println(
                    "Pedido " + codigo + " reservado correctamente."
            );

        } else {

            System.out.println(
                    "El pedido " + codigo + " no se encuentra disponible para reserva."
            );
        }
    }


    // SOBRECARGA:
    // Permite asignar manualmente un repartidor indicando su nombre.
    public void asignarRepartidor(String nombre) {

        if (estado.equals("Cancelado")) {

            System.out.println(
                    "No se puede asignar un repartidor a un pedido cancelado."
            );

            return;
        }

        this.repartidor = nombre;

        registrarEvento(
                "Repartidor asignado manualmente: " + nombre
        );

        System.out.println(
                "Repartidor asignado manualmente: " + nombre
        );
    }


    // Método implementado en la clase abstracta.
    // Muestra la información general del pedido.
    public void mostrarResumen() {

        System.out.println("Código: " + codigo);
        System.out.println("Cliente: " + cliente);
        System.out.println("Dirección: " + direccion);
        System.out.println("Distancia: " + distanciaKm + " km");
        System.out.println("Repartidor: " + repartidor);
        System.out.println("Estado: " + estado);
    }


    // SOBRECARGA:
    // Permite mostrar el resumen y, opcionalmente,
    // el tiempo estimado de entrega.
    public void mostrarResumen(boolean mostrarTiempo) {

        mostrarResumen();

        if (mostrarTiempo) {

            System.out.println(
                    "Tiempo estimado: "
                            + calcularTiempoEntrega()
                            + " minutos"
            );
        }
    }


    // Implementación de la interfaz Despachable.
    @Override
    public void despachar() {

        if (estado.equals("Cancelado")) {

            System.out.println(
                    "No se puede despachar un pedido cancelado."
            );

            return;
        }

        if (estado.equals("Creado")) {

            System.out.println(
                    "El pedido debe ser reservado antes de ser despachado."
            );

            return;
        }

        if (estado.equals("Despachado")) {

            System.out.println(
                    "El pedido " + codigo + " ya fue despachado."
            );

            return;
        }

        estado = "Despachado";

        registrarEvento("Pedido despachado");

        System.out.println(
                "Pedido " + codigo + " despachado correctamente."
        );
    }


    // Implementación de la interfaz Cancelable.
    @Override
    public void cancelar() {

        if (estado.equals("Despachado")) {

            System.out.println(
                    "No se puede cancelar un pedido que ya fue despachado."
            );

            return;
        }

        if (estado.equals("Cancelado")) {

            System.out.println(
                    "El pedido " + codigo + " ya se encuentra cancelado."
            );

            return;
        }

        estado = "Cancelado";

        registrarEvento("Pedido cancelado");

        System.out.println(
                "Pedido " + codigo + " cancelado correctamente."
        );
    }


    // Implementación de la interfaz Rastreable.
    @Override
    public void verHistorial() {

        System.out.println(
                "\nHistorial del pedido " + codigo + ":"
        );

        if (historial.isEmpty()) {

            System.out.println("- No existen movimientos registrados.");

            return;
        }

        for (String evento : historial) {

            System.out.println("- " + evento);
        }
    }


    // Método protegido que permite a las clases hijas
    // agregar eventos al historial.
    protected void registrarEvento(String evento) {

        historial.add(evento);
    }


    // Getter protegido utilizado por las clases hijas
    // para calcular el tiempo de entrega.
    protected double getDistanciaKm() {

        return distanciaKm;
    }


    // Setter protegido utilizado por las clases hijas
    // para realizar la asignación automática.
    protected void setRepartidor(String repartidor) {

        this.repartidor = repartidor;
    }



}