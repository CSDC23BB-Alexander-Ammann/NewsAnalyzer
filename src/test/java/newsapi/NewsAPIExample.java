package newsapi;

import newsanalyzer.ctrl.BuildUrlException;
import newsanalyzer.ctrl.Controller;
import newsanalyzer.ctrl.NewsAnalyserException;
import newsapi.beans.Article;
import newsapi.beans.NewsReponse;
import newsapi.enums.Category;
import newsapi.enums.Country;
import newsapi.enums.Endpoint;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public class NewsAPIExample {

    public static final String APIKEY = "f7175e033d9044b19b4b44c38cc34a94";

    private Controller ctrl = new Controller();

    public static void main(String[] args) throws IOException, NewsAnalyserException, BuildUrlException {

        NewsApi newsApi = new NewsApiBuilder()
                .setApiKey(APIKEY)
                .setQ("corona")
                .setEndPoint(Endpoint.TOP_HEADLINES)
                .setSourceCountry(Country.at)
                .setSourceCategory(Category.health)
                .createNewsApi();
        try {
            NewsReponse newsResponse = newsApi.getNews();
            if (newsResponse != null) {
                List<Article> articles = newsResponse.getArticles();
                articles.stream().forEach(article -> System.out.println(article.toString()));
            }
        } catch (MalformedURLException e) {
            System.out.println("Wrong URL");
        } catch (NewsAnalyserException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("No entry found");
        } catch (BuildUrlException e) {
            e.printStackTrace();
        }


        newsApi = new NewsApiBuilder()
                .setApiKey(APIKEY)
                .setQ("corona")
                .setEndPoint(Endpoint.EVERYTHING)
                .setFrom("2020-03-20")
                .setExcludeDomains("Lifehacker.com")
                .createNewsApi();

        try {
            NewsReponse newsResponse = newsApi.getNews();
            if (newsResponse != null) {
                List<Article> articles = newsResponse.getArticles();
                articles.stream().forEach(article -> System.out.println(article.toString()));
            }
        } catch (MalformedURLException e) {
            System.out.println("Wrong URL");
        } catch (NewsAnalyserException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("No entry found");
        } catch (BuildUrlException e) {
            e.printStackTrace();
        }
    }
}
