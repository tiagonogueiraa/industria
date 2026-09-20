import java.text.NumberFormat;
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

            for(Funcionario f: funcionarios){

                String funcao = f.getFuncao();

                if(!funcionariosPorFuncao.containsKey(funcao)){
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
            for(Funcionario f: funcionarios){

                int mes = f.getDataNascimento().getMonthValue();

                if(mes == 10 || mes == 12 ){
                    System.out.printf("%-15s %-12s %15s%n",
                            f.getNome(),
                            f.getDataNascimento().format(data),
                            moeda.format(f.getSalario()));
                }
            }
        }



}
