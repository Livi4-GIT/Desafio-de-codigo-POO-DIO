import java.time.LocalDate;

public class App {
    public static void main(String[] args) {
        Curso curso1 = new Curso();
        curso1.setTitulo("Curso Java");
        curso1.setDescricao("Descrição do curso Java");
        curso1.setCargaHoraria(8);

        Curso curso2 = new Curso();
        curso2.setTitulo("Curso JavaScript");
        curso2.setDescricao("Descrição do curso JavaScript");
        curso2.setCargaHoraria(4);

        Conteudo conteudo = new Curso();
        conteudo.setTitulo("Curso de Python");
        conteudo.setDescricao("Descrição do curso de Python");

        Mentoria mentoria = new Mentoria();
        mentoria.setTitulo("Mentoria de Java");
        mentoria.setDescricao("Descrição da mentoria de Java");
        mentoria.setData(LocalDate.now());


        System.out.println(curso1);
        System.out.println(curso2);
        System.out.println(conteudo);
        System.out.println(mentoria);
    }
}
