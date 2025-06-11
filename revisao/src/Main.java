import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VeiculoService service = new VeiculoService();
        int opcao = 0;

        do {
            System.out.println("\n--- Sistema de Cadastro de Veículos ---");
            System.out.println("1 - Cadastrar veículo");
            System.out.println("2 - Listar veículos");
            System.out.println("3 - Atualizar veículo");
            System.out.println("4 - Remover veículo");
            System.out.println("5 - Realizar manutenção");
            System.out.println("6 - Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
                    case 1:
                        System.out.print("Digite o tipo (Carro/Moto): ");
                        String tipo = scanner.nextLine().trim();
                        System.out.print("Digite a placa: ");
                        String placa = scanner.nextLine().trim();
                        System.out.print("Digite a marca: ");
                        String marca = scanner.nextLine().trim();
                        System.out.print("Digite o modelo: ");
                        String modelo = scanner.nextLine().trim();

                        Veiculo v;
                        if (tipo.equalsIgnoreCase("Carro")) {
                            v = new Carro(placa, marca, modelo);
                        } else if (tipo.equalsIgnoreCase("Moto")) {
                            v = new Moto(placa, marca, modelo);
                        } else {
                            System.out.println("Tipo inválido.");
                            break;
                        }
                        service.cadastrarVeiculo(v);
                        break;
                    case 2:
                        service.listarVeiculos();
                        break;
                    case 3:
                        System.out.print("Digite a placa do veículo a atualizar: ");
                        String placaAtualizar = scanner.nextLine().trim();
                        System.out.print("Digite a nova marca: ");
                        String novaMarca = scanner.nextLine().trim();
                        System.out.print("Digite o novo modelo: ");
                        String novoModelo = scanner.nextLine().trim();
                        service.atualizarVeiculo(placaAtualizar, novaMarca, novoModelo);
                        break;
                    case 4:
                        System.out.print("Digite a placa do veículo a remover: ");
                        String placaRemover = scanner.nextLine().trim();
                        service.removerVeiculo(placaRemover);
                        break;
                    case 5:
                        System.out.print("Digite a placa do veículo para manutenção: ");
                        String placaManutencao = scanner.nextLine().trim();
                        service.realizarManutencao(placaManutencao);
                        break;
                    case 6:
                        System.out.println("Saindo do sistema...");
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida, digite um número.");
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } while (opcao != 6);

        scanner.close();
    }
}
