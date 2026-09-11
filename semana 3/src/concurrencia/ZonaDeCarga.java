package concurrencia;

import model.EstadoPedido;
import model.Pedido;

import java.util.ArrayList;
import java.util.List;

public class ZonaDeCarga {

    private final List<Pedido> pedidosPendientes;


    public ZonaDeCarga() {

        this.pedidosPendientes =
                new ArrayList<>();

        System.out.println(
                "[Zona de Carga] Inicializada correctamente."
        );
    }


    public synchronized void agregarPedido(
            Pedido p) {

        if (p == null) {

            return;
        }

        if (p.getEstado()
                != EstadoPedido.PENDIENTE) {

            System.out.println(
                    "[Zona de Carga] Rechazado: El pedido #"
                            + p.getId()
                            + " no está PENDIENTE."
            );

            return;
        }

        pedidosPendientes.add(p);

        System.out.println(
                "[Zona de Carga] Pedido #"
                        + p.getId()
                        + " recepcionado. Destino: "
                        + p.getDireccionEntrega()
        );
    }


    public synchronized Pedido retirarPedido() {

        for (int i = 0;
             i < pedidosPendientes.size();
             i++) {

            Pedido pedido =
                    pedidosPendientes.get(i);

            if (pedido.getEstado()
                    == EstadoPedido.PENDIENTE) {

                /*
                 * La eliminación ocurre mientras
                 * el recurso compartido sigue protegido.
                 * Otro hilo no puede retirar
                 * el mismo pedido.
                 */
                pedidosPendientes.remove(i);

                return pedido;
            }
        }

        return null;
    }


    public synchronized boolean estaVacia() {

        return pedidosPendientes.isEmpty();
    }
}