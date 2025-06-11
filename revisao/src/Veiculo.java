public abstract class Veiculo {
    protected String placa;
    protected String marca;
    protected String modelo;

    public Veiculo(String placa, String marca, String modelo) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
    }

    // Método abstrato que será implementado nas subclasses
    public abstract void exibirDados();

    // Método concreto para exibir mensagem de cadastro
    public void mensagemCadastro() {
        System.out.println("Veículo cadastrado com sucesso");
    }

    // Getters e setters
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
}
