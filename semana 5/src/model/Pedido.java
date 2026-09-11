package model;

import interfaces.Cancelable;
import interfaces.Despachable;
import interfaces.Rastreable;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public abstract class Pedido implements Despachable, Cancelable, Rastreable {

    // Atributos solicitados en la actividad actual.
    private int id;
    private String direccionEntrega;
    private EstadoPedido estado;

    // Atributos conservados de las actividades anteriores.
    private final String cliente;
    private final double distanciaKm;
    private String repartidor;

    private boolean reservado;
    private boolean cancelado;

    private final List<String> historial;


    // Constructor simplificado para la simulación concurrente.
    public Pedido(int id, String direccionEntrega) {

        this(
                id,
                "Sin cliente registrado",
                direccionEntrega,
                0
        );
    }


    // Constructor conservado de la actividad anterior.
    public Pedido(
            int id,
            String cliente,
            String direccionEntrega,
            double distanciaKm) {

        this.historial = new ArrayList<>();

        setId(id);
        setDireccionEntrega(direccionEntrega);

        this.cliente = cliente;
        this.distanciaKm = distanciaKm;

        this.repartidor = "Sin asignar";
        this.estado = EstadoPedido.PENDIENTE;

        this.reservado = false;
        this.cancelado = false;

        registrarEvento(
                "Estado inicial: PENDIENTE"
        );
    }


    public int getId() {

        return id;
    }


    public final void setId(int id) {

        this.id = id;
    }


    public String getDireccionEntrega() {

        return direccionEntrega;
    }


    public final void setDireccionEntrega(
            String direccionEntrega) {

        this.direccionEntrega = direccionEntrega;
    }


    public EstadoPedido getEstado() {

        return estado;
    }


    public void setEstado(
            EstadoPedido nuevoEstado) {

        if (nuevoEstado == null) {

            throw new IllegalArgumentException(
                    "El estado no puede ser nulo."
            );
        }

        this.estado = nuevoEstado;

        registrarEvento(
                "Estado actualizado a " + nuevoEstado
        );
    }


    // Firma solicitada expresamente en la actividad.
    public void setEstado(String nuevoEstado) {

        if (nuevoEstado == null) {

            throw new IllegalArgumentException(
                    "El estado no puede ser nulo."
            );
        }

        EstadoPedido estadoConvertido =
                EstadoPedido.valueOf(
                        nuevoEstado
                                .trim()
                                .toUpperCase(Locale.ROOT)
                );

        setEstado(estadoConvertido);
    }


    public abstract void asignarRepartidor();


    public abstract void asignarRepartidor(
            String nombre);


    public abstract int calcularTiempoEntrega();


    public void reservar() {

        if (cancelado) {

            System.out.println(
                    "No se puede reservar un pedido cancelado."
            );

            return;
        }

        if (estado == EstadoPedido.ENTREGADO) {

            System.out.println(
                    "No se puede reservar un pedido ya entregado."
            );

            return;
        }

        if (reservado) {

            System.out.println(
                    "El pedido "
                            + id
                            + " ya se encuentra reservado."
            );

            return;
        }

        reservado = true;

        registrarEvento(
                "Pedido reservado"
        );

        System.out.println(
                "Pedido "
                        + id
                        + " reservado correctamente."
        );
    }


    public void mostrarResumen() {

        System.out.println(
                "ID: " + id
        );

        System.out.println(
                "Cliente: " + cliente
        );

        System.out.println(
                "Dirección de entrega: "
                        + direccionEntrega
        );

        System.out.println(
                "Distancia: "
                        + distanciaKm
                        + " km"
        );

        System.out.println(
                "Repartidor: "
                        + repartidor
        );

        System.out.println(
                "Estado: "
                        + estado
        );
    }


    public void mostrarResumen(
            boolean mostrarTiempo) {

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

        if (cancelado) {

            System.out.println(
                    "No se puede despachar un pedido cancelado."
            );

            return;
        }

        if (estado == EstadoPedido.ENTREGADO) {

            System.out.println(
                    "El pedido "
                            + id
                            + " ya fue entregado."
            );

            return;
        }

        if (estado == EstadoPedido.EN_REPARTO) {

            System.out.println(
                    "El pedido "
                            + id
                            + " ya está en reparto."
            );

            return;
        }

        if (repartidor.equals("Sin asignar")) {

            System.out.println(
                    "El pedido debe tener un repartidor asignado."
            );

            return;
        }

        setEstado("EN_REPARTO");

        System.out.println(
                "Pedido "
                        + id
                        + " enviado a reparto."
        );
    }


    @Override
    public void cancelar() {

        if (estado == EstadoPedido.EN_REPARTO) {

            System.out.println(
                    "No se puede cancelar un pedido que está en reparto."
            );

            return;
        }

        if (estado == EstadoPedido.ENTREGADO) {

            System.out.println(
                    "No se puede cancelar un pedido entregado."
            );

            return;
        }

        if (cancelado) {

            System.out.println(
                    "El pedido "
                            + id
                            + " ya se encuentra cancelado."
            );

            return;
        }

        cancelado = true;

        registrarEvento(
                "Pedido cancelado"
        );

        System.out.println(
                "Pedido "
                        + id
                        + " cancelado correctamente."
        );
    }


    @Override
    public void verHistorial() {

        System.out.println(
                "Historial del pedido #"
                        + id
                        + ":"
        );

        for (String evento : historial) {

            System.out.println(
                    "- " + evento
            );
        }
    }


    protected boolean puedeAsignarRepartidor() {

        if (cancelado) {

            System.out.println(
                    "No se puede asignar un repartidor "
                            + "a un pedido cancelado."
            );

            return false;
        }

        if (estado == EstadoPedido.ENTREGADO) {

            System.out.println(
                    "No se puede cambiar el repartidor "
                            + "de un pedido entregado."
            );

            return false;
        }

        return true;
    }


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


    protected void registrarEvento(
            String evento) {

        historial.add(evento);
    }


    protected double getDistanciaKm() {

        return distanciaKm;
    }


    @Override
    public String toString() {

        return "Pedido{" +
                "id=" + id +
                ", direccionEntrega='" +
                direccionEntrega + '\'' +
                ", estado=" + estado +
                '}';
    }
}