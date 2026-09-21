import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Principal {

    public static void main(String[] args) {

        System.out.println("Bem vindo ao sistema de funcionários!");

        DateTimeFormatter data = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Criar lista de funcionários

        List<Funcionario> funcionarios = new ArrayList<>();

        //Adiciona funcionários na lista

        funcionarios.add(new Funcionario("Maria", LocalDate.parse("18/10/2000", data), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.parse("12/05/1990", data), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.parse("02/05/1961", data), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.parse("14/10/1988", data), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.parse("05/01/1995", data), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.parse("19/11/1999", data), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.parse("31/03/1993", data), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.parse("08/07/1994", data), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.parse("24/05/2003", data), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.parse("02/09/1996", data), new BigDecimal("2799.93"), "Gerente"));

        //REMOVER JOÃO QUE ESTÁ NA POSIÇÃO 1
        funcionarios.remove(1);

        //IMPRIMIR USUÁRIOS
        // exemplos printf para organizar os dados como uma tabela
        // "%-10s" com menos: esquerda (nome, função)
        //"%15s"   sem menos: direita (salário)

        System.out.println("Funcionários cadastrados");
        System.out.printf("%-15s %-15s %15s %-15s%n", "Nome", "Nascimento", "Salário", "Função");
        System.out.println("-".repeat(55));

        for (Funcionario f : funcionarios) {

            NumberFormat moeda = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("pt-BR"));
            moeda.format(f.getSalario());

            System.out.printf("%-15s %-15s %15s %-15s%n",
                    f.getNome(),
                    f.getDataNascimento().format(data),
                    moeda.format(f.getSalario()),
                    f.getFuncao());
        }

        // 3.4 - Funcionários recebem 10% de aumento
        System.out.println("-".repeat(55));
        System.out.println("Funcionários com novo salário, aumento 10%");
        for (Funcionario f : funcionarios) {

            BigDecimal salarioAtual = f.getSalario();

            BigDecimal aumento = salarioAtual.multiply(new BigDecimal("0.10"));

            BigDecimal salarioNovo = salarioAtual.add(aumento);

            f.setSalario(salarioNovo);
        }

        //Imprime funcionários com aumento
        System.out.printf("%-15s %-15s %15s %-15s%n", "Nome", "Nascimento", "Salário", "Função");
        System.out.println("-".repeat(55));

        for (Funcionario f : funcionarios) {
            NumberFormat moeda = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("pt-BR"));
            moeda.format(f.getSalario());

            System.out.printf("%-15s %-15s %15s %-15s%n",
                    f.getNome(),
                    f.getDataNascimento().format(data),
                    moeda.format(f.getSalario()),
                    f.getFuncao());
        }


        // 3.5 Agrupar funcionários em um map, sendo a chave a função e o valor a lista de funcionários

        Map<String, List<Funcionario>> funcionariosPorFuncao = new HashMap<>();

        for (Funcionario f : funcionarios) {

            String funcao = f.getFuncao();

            if (!funcionariosPorFuncao.containsKey(funcao)) {
                funcionariosPorFuncao.put(funcao, new ArrayList<>());
            }

            funcionariosPorFuncao.get(funcao).add(f);
        }

        NumberFormat moeda = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("pt-BR"));

        System.out.println("-".repeat(44));
        System.out.println("Funcionários por função");
        System.out.printf("%-15s %-12s %15s%n", "Nome", "Nascimento", "Salário");
        System.out.println("-".repeat(44));

        for (String funcao : funcionariosPorFuncao.keySet()) {
            System.out.println(funcao);

            for (Funcionario f : funcionariosPorFuncao.get(funcao)) {
                System.out.printf("%-15s %-12s %15s%n",
                        f.getNome(),
                        f.getDataNascimento().format(data),
                        moeda.format(f.getSalario()));
            }
        }

        //Aniversariantes de outubro e dezembro (10, 12)
        System.out.println("-".repeat(44));
        System.out.println("Aniversariantes de outubro e dezembro (10, 12)");
        System.out.println("-".repeat(44));
        for (Funcionario f : funcionarios) {

            int mes = f.getDataNascimento().getMonthValue();

            if (mes == 10 || mes == 12) {
                System.out.printf("%-15s %-12s %15s%n",
                        f.getNome(),
                        f.getDataNascimento().format(data),
                        moeda.format(f.getSalario()));
            }
        }

        //Funcionário mais velho

        System.out.println("-".repeat(44));
        System.out.println("Ordenação dos mais velhos > mais novos");
        System.out.println("-".repeat(44));

        Funcionario maisVelho = null;
        int maiorIdade = 0;

        for (Funcionario f : funcionarios) {

            int idade = Period.between(f.getDataNascimento(), LocalDate.now()).getYears();

            if (idade > maiorIdade) {
                maiorIdade = idade;
                maisVelho = f;
            }
        }
        System.out.println("Funcionário mais velho");
        System.out.println("Nome: " + maisVelho.getNome() + " | Idade: " + maiorIdade + " anos");

        // Ordenar por ordem alfabética
        List<Funcionario> ordenados = new ArrayList<>();

        for (Funcionario f : funcionarios) {
            int posicao = 0;

            //Enquanto o nome da lista for menor que o do f, passa para o próximo. Quando achar um maior (ou a lista acabar), pega essa posição e insere.
            while (posicao < ordenados.size() && ordenados.get(posicao).getNome().compareTo(f.getNome()) < 0) {
                posicao++;
            }

            ordenados.add(posicao, f);
        }
        System.out.println("-".repeat(44));
        System.out.println("Funcionários em ordem alfabética");
        System.out.printf("%-15s %-12s %15s %-15s%n", "Nome", "Nascimento", "Salário", "Função");
        System.out.println("-".repeat(59));

        for (Funcionario f : ordenados) {
            System.out.printf("%-15s %-12s %15s %-15s%n",
                    f.getNome(),
                    f.getDataNascimento().format(data),
                    moeda.format(f.getSalario()),
                    f.getFuncao());
        }

        //Imprimir total dos salários dos funcionários

        System.out.println("-".repeat(44));
        System.out.println("Total salário funcionários");
        System.out.println("-".repeat(44));

        BigDecimal total = BigDecimal.ZERO;

        for(Funcionario f: funcionarios){
            BigDecimal salario = f.getSalario();

            total = total.add(salario);

        }
        System.out.println("Total dos salários: " + moeda.format(total));

        // Lista de funcionários agrupado  salário / salário mínimo

        BigDecimal salarioMinimo = new BigDecimal("1212.00");

        // formatador só de número (sem o "R$"), com vírgula e 2 casas
        NumberFormat numero = NumberFormat.getNumberInstance(Locale.forLanguageTag("pt-BR"));
        numero.setMinimumFractionDigits(2); // define 2 casas mínimo
        numero.setMaximumFractionDigits(2);

        System.out.println("-".repeat(44));
        System.out.println("Salários mínimos por funcionário (mínimo: " + moeda.format(salarioMinimo) + ")");
        System.out.printf("%-15s %15s %12s%n", "Nome", "Salário", "Qtd. mínimos");
        System.out.println("-".repeat(44));

        for (Funcionario f : funcionarios) {

            BigDecimal quantidade = f.getSalario().divide(salarioMinimo, 2, RoundingMode.HALF_UP);

            System.out.printf("%-15s %15s %12s%n",
                    f.getNome(),
                    moeda.format(f.getSalario()),
                    numero.format(quantidade));
        }

    }

}