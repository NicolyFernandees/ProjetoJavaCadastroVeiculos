public class Moto extends Veiculo implements OperacoesVeiculo {

    public Moto(String placa, String marca, String modelo) {
        super(placa, marca, modelo);
    }

    @Override
    public void exibirDados() {
        System.out.println("Moto - Placa: " + placa + ", Marca: " + marca + ", Modelo: " + modelo);
    }

    @Override
    public void realizarManutencao() {
        System.out.println("Realizando manutenção na moto " + placa);
    }
}
