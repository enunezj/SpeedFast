package model;

// Representa un pedido de comida.
public class PedidoComida extends Pedido {

    public PedidoComida(
            int codigo,
            String cliente,
            String direccion,
            double distanciaKm) {

        super(codigo, cliente, direccion, distanciaKm);
    }


    // SOBRESCRITURA:
    // Los pedidos de comida se asignan automáticamente
    // a un repartidor en moto.
    @Override
    public void asignarRepartidor() {

        String nombre = "Carlos - Moto";

        setRepartidor(nombre);

        registrarEvento(
                "Repartidor automático asignado: " + nombre
        );

        System.out.println(
                "Repartidor automático asignado: " + nombre
        );
    }


    // SOBRESCRITURA:
    // Regla propia para calcular el tiempo del pedido de comida.
    @Override
    public int calcularTiempoEntrega() {

        return 20 + (int) (getDistanciaKm() * 3);
    }
}