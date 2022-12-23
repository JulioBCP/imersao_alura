import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {

    public static void main(String[] args) throws Exception {

             // fazer uma requisicao HTTP e buscar os top 250 filmes
        // String url = "https://api.mocki.io/v2/549a5d8b/Top250Movies";
        // String url = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";
        // ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();

        // String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-06-12&end_date=2022-06-14";
        // ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa();

        String url = "http://localhost:8080/linguagens";
        ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();

        var clienteHttp = new ClienteHttp();
        String json = clienteHttp.buscaDados(url);
        // System.out.println(body);

        // exibir e manipular os dados
        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        var geradora = new GeradoraDeFigurinhas();
        // for (Map<String, String> filme : listaDeFilmes) {
        for (int i = 0; i < 3; i++) {

            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();

            String nomeArquivo = "saida/" + conteudo.getTitulo() + ".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println(conteudo.getTitulo());
            // System.out.println(urlImagem);
            // System.out.println(filme.get("imDbRating"));
            System.out.println();
        }

    }
}
