package controller;

import model.Pedido;

import java.util.ArrayList;

public class ControladorDeEnvios {

    private final ArrayList<Pedido> historialEntregas;


    public ControladorDeEnvios() {

        this.historialEntregas = new ArrayList<>();
    }


    public void reservarPedido(Pedido pedido) {

        pedido.reservar();
    }


    // Asignación automática.
    public void asignarRepartidor(Pedido pedido) {

        pedido.asignarRepartidor();
    }


    // Asignación manual.
    public void asignarRepartidor(
            Pedido pedido,
            String nombre) {

        pedido.asignarRepartidor(nombre);
    }


    public void mostrarTiempoEstimado(Pedido pedido) {

        System.out.println(
                "Tiempo estimado de entrega: "
                        + pedido.calcularTiempoEntrega()
                        + " minutos."
        );
    }


    public void despacharPedido(Pedido pedido) {

        pedido.despachar();

        if (pedido.estaDespachado()
                && !historialEntregas.contains(pedido)) {

            historialEntregas.add(pedido);
        }
    }


    public void cancelarPedido(Pedido pedido) {

        pedido.cancelar();
    }


    public void mostrarHistorialPedido(Pedido pedido) {

        pedido.verHistorial();
    }


    public void mostrarHistorialEntregas() {

        System.out.println("\n==================================");
        System.out.println(" HISTORIAL DE ENTREGAS REALIZADAS");
        System.out.println("==================================");

        if (historialEntregas.isEmpty()) {

            System.out.println(
                    "No existen entregas realizadas."
            );

            return;
        }

        for (Pedido pedido : historialEntregas) {

            pedido.mostrarResumen();

            System.out.println("----------------------------------");
        }
    }
}