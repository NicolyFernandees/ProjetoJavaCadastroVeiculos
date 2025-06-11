import java.io.*;
import java.util.ArrayList;

public class VeiculoService {
    private ArrayList<Veiculo> veiculos;
    private final String arquivo = "veiculos.txt";

    public VeiculoService() {
        veiculos = new ArrayList<>();
        carregarDoArquivo();
    }

    public void cadastrarVeiculo(Veiculo v) throws Exception {
        if (existePlaca(v.getPlaca())) {
            throw new Exception("Placa já cadastrada!");
        }
        veiculos.add(v);
        salvarNoArquivo();
        v.mensagemCadastro();
    }

    public void listarVeiculos() {
        if (veiculos.isEmpty()) {
            System.out.println("Nenhum veículo cadastrado.");
        } else {
            for (Veiculo v : veiculos) {
                v.exibirDados();
            }
        }
    }

    public void atualizarVeiculo(String placa, String novaMarca, String novoModelo) throws Exception {
        Veiculo v = buscarPorPlaca(placa);
        if (v == null) {
            throw new Exception("Veículo não encontrado.");
        }
        v.setMarca(novaMarca);
        v.setModelo(novoModelo);
        salvarNoArquivo();
        System.out.println("Veículo atualizado com sucesso.");
    }

    public void removerVeiculo(String placa) throws Exception {
        Veiculo v = buscarPorPlaca(placa);
        if (v == null) {
            throw new Exception("Veículo não encontrado.");
        }
        veiculos.remove(v);
        salvarNoArquivo();
        System.out.println("Veículo removido com sucesso.");
    }

    public void realizarManutencao(String placa) throws Exception {
        Veiculo v = buscarPorPlaca(placa);
        if (v == null) {
            throw new Exception("Veículo não encontrado.");
        }
        if (v instanceof OperacoesVeiculo) {
            ((OperacoesVeiculo) v).realizarManutencao();
        }
    }

    private boolean existePlaca(String placa) {
        return veiculos.stream().anyMatch(v -> v.getPlaca().equalsIgnoreCase(placa));
    }

    private Veiculo buscarPorPlaca(String placa) {
        return veiculos.stream()
                .filter(v -> v.getPlaca().equalsIgnoreCase(placa))
                .findFirst()
                .orElse(null);
    }

    private void salvarNoArquivo() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo))) {
            for (Veiculo v : veiculos) {
                String tipo = (v instanceof Carro) ? "Carro" : "Moto";
                bw.write(tipo + ";" + v.getPlaca() + ";" + v.getMarca() + ";" + v.getModelo());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar no arquivo: " + e.getMessage());
        }
    }

    private void carregarDoArquivo() {
        File file = new File(arquivo);
        if (!file.exists()) {
            return; // Arquivo ainda não existe
        }
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length == 4) {
                    String tipo = partes[0];
                    String placa = partes[1];
                    String marca = partes[2];
                    String modelo = partes[3];
                    if (tipo.equalsIgnoreCase("Carro")) {
                        veiculos.add(new Carro(placa, marca, modelo));
                    } else if (tipo.equalsIgnoreCase("Moto")) {
                        veiculos.add(new Moto(placa, marca, modelo));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar arquivo: " + e.getMessage());
        }
    }
}
