package controller;

import model.EstadoPedido;
import model.Pedido;

import java.util.ArrayList;
import java.util.List;

public class ControladorDeEnvios {

    private final List<Pedido> historialEntregas;


    public ControladorDeEnvios() {

        this.historialEntregas =
                new ArrayList<>();
    }


    public void registrarEntregas(
            List<Pedido> pedidos) {

        for (Pedido pedido : pedidos) {

            if (pedido.getEstado()
                    == EstadoPedido.ENTREGADO
                    && !historialEntregas.contains(pedido)) {

                historialEntregas.add(pedido);
            }
        }
    }


    public void mostrarHistorialEntregas() {

        System.out.println(
                "\n======================================"
        );

        System.out.println(
                "       HISTORIAL DE ENTREGAS"
        );

        System.out.println(
                "======================================"
        );


        if (historialEntregas.isEmpty()) {

            System.out.println(
                    "No existen entregas realizadas."
            );

            return;
        }


        for (Pedido pedido : historialEntregas) {

            System.out.println(
                    pedido
            );

            pedido.verHistorial();

            System.out.println(
                    "--------------------------------------"
            );
        }
    }
}