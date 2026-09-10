package model;

import interfaces.Cancelable;
import interfaces.Despachable;
import interfaces.Rastreable;

import java.util.ArrayList;

public abstract class Pedido implements Despachable, Cancelable, Rastreable {

    private final int codigo;
    private final String cliente;
    private final String direccion;
    private final double distanciaKm;

    private String repartidor;
    private String estado;

    private final ArrayList<String> historial;

    public Pedido(int codigo, String cliente, String direccion, double distanciaKm) {

        this.codigo = codigo;
        this.cliente = cliente;
        this.direccion = direccion;
        this.distanciaKm = distanciaKm;

        this.repartidor = "Sin asignar";
        this.estado = "Creado";

        this.historial = new ArrayList<>();
    }


    // Asignación automática.
    public abstract void asignarRepartidor();


    // Asignación manual.
    // Sobrecarga de la operación anterior y comportamiento
    // especializado por cada tipo de pedido.
    public abstract void asignarRepartidor(String nombre);


    // Cada tipo de pedido calcula su propio tiempo.
    public abstract int calcularTiempoEntrega();


    public void reservar() {

        if (estado.equals("Creado")) {

            estado = "Reservado";

            registrarEvento("Pedido reservado");

            System.out.println(
                    "Pedido " + codigo + " reservado correctamente."
            );

        } else {

            System.out.println(
                    "El pedido " + codigo
                            + " no se encuentra disponible para reserva."
            );
        }
    }


    public void mostrarResumen() {

        System.out.println("Código: " + codigo);
        System.out.println("Cliente: " + cliente);
        System.out.println("Dirección: " + direccion);
        System.out.println("Distancia: " + distanciaKm + " km");
        System.out.println("Repartidor: " + repartidor);
        System.out.println("Estado: " + estado);
    }


    // Segunda sobrecarga funcional.
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


    @Override
    public void despachar() {

        switch (estado) {

            case "Cancelado":
                System.out.println(
                        "No se puede despachar un pedido cancelado."
                );
                break;

            case "Creado":
                System.out.println(
                        "El pedido debe ser reservado antes de ser despachado."
                );
                break;

            case "Despachado":
                System.out.println(
                        "El pedido " + codigo + " ya fue despachado."
                );
                break;

            default:

                if (repartidor.equals("Sin asignar")) {

                    System.out.println(
                            "El pedido debe tener un repartidor antes de ser despachado."
                    );

                    return;
                }

                estado = "Despachado";

                registrarEvento("Pedido despachado");

                System.out.println(
                        "Pedido " + codigo
                                + " despachado correctamente."
                );
                break;
        }
    }


    @Override
    public void cancelar() {

        switch (estado) {

            case "Despachado":
                System.out.println(
                        "No se puede cancelar un pedido que ya fue despachado."
                );
                break;

            case "Cancelado":
                System.out.println(
                        "El pedido " + codigo
                                + " ya se encuentra cancelado."
                );
                break;

            default:
                estado = "Cancelado";

                registrarEvento("Pedido cancelado");

                System.out.println(
                        "Pedido " + codigo
                                + " cancelado correctamente."
                );
                break;
        }
    }


    @Override
    public void verHistorial() {

        System.out.println(
                "\nHistorial del pedido " + codigo + ":"
        );

        if (historial.isEmpty()) {

            System.out.println(
                    "- No existen movimientos registrados."
            );

            return;
        }

        for (String evento : historial) {

            System.out.println("- " + evento);
        }
    }


    // Comprueba si el pedido está preparado para recibir un repartidor.
    protected boolean puedeAsignarRepartidor() {

        switch (estado) {

            case "Creado" -> {
                System.out.println(
                        "El pedido debe ser reservado antes de asignar un repartidor."
                );
                return false;
            }

            case "Cancelado" -> {
                System.out.println(
                        "No se puede asignar un repartidor a un pedido cancelado."
                );
                return false;
            }

            case "Despachado" -> {
                System.out.println(
                        "No se puede cambiar el repartidor de un pedido despachado."
                );
                return false;
            }

            default -> {
                return true;
            }
        }
    }


    // Función común utilizada por las subclases
    // para registrar una asignación.
    protected void registrarAsignacion(
            String nombreRepartidor,
            String tipoAsignacion) {

        this.repartidor = nombreRepartidor;

        registrarEvento(
                "Repartidor asignado "
                        + tipoAsignacion
                        + ": "
                        + nombreRepartidor
        );

        System.out.println(
                "Repartidor asignado "
                        + tipoAsignacion
                        + ": "
                        + nombreRepartidor
        );
    }


    protected void registrarEvento(String evento) {

        historial.add(evento);
    }


    protected double getDistanciaKm() {

        return distanciaKm;
    }


    public boolean estaDespachado() {

        return estado.equals("Despachado");
    }
}