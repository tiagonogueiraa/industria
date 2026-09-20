import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Locale;

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
        }



}
