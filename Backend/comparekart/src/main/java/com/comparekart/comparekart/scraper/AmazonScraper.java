package com.comparekart.comparekart.scraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Component
public class AmazonScraper {
    public static void main(String[] args) throws IOException {
        for(int i=0;i<5;i++){
            String producttext = "laptop ";
            String encodedProduct = URLEncoder.encode(producttext, "UTF-8");
            String url = "https://www.amazon.in/s?k=" + encodedProduct + "&page=" + Integer.toString(i);

            Document doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
                    .timeout(10000)
                    .get();

            Elements productContainers = doc.select("div.sg-col-inner");
            List<Product> products = new ArrayList<>();

            for(Element container : productContainers){
                Element titleElement = container.selectFirst(".a-size-medium.a-spacing-none.a-color-base.a-text-normal");
                String title = titleElement != null ? titleElement.text() : "No Title";

                // Skip this product if no title is found
                if (titleElement == null || titleElement.text().trim().isEmpty()) {
                    continue;
                }


                Element priceElement = container.selectFirst(".a-price-whole");
                String price = priceElement != null ? priceElement.text() : "Price Not mentioned";

                Element imgElement = container.selectFirst("img.s-image");
                String imageUrl = imgElement != null ? imgElement.attr("src") : "No Image";

                Element linkElement = container.selectFirst("a.a-link-normal.s-line-clamp-2.s-link-style.a-text-normal");
                // Skip this product if no title is found
                if (linkElement == null || linkElement.text().trim().isEmpty()) {
                    continue;
                }
                String productUrl;
                if (linkElement != null && !linkElement.attr("href").isEmpty()) {
                    productUrl = "https://www.amazon.in" + linkElement.attr("href");
                } else {
                    productUrl = "No URL";
                }

                Element ratingElement = container.selectFirst(".a-icon-alt");
                String rating = ratingElement != null ? ratingElement.text() : "No rating avaible";

                Element sponsoredElement = container.selectFirst("span.a-color-secondary");
                boolean isSponsored = sponsoredElement != null && sponsoredElement.text().equalsIgnoreCase("Sponsored");

                Product product = new Product();
                product.setTitle(title);
                product.setPrice(price);
                product.setImageUrl(imageUrl);
                product.setProductUrl(productUrl);
                product.setRating(rating);
                product.setIsSponsored(isSponsored);
                products.add(product);
            }

            // Print all products
            for (Product p : products) {
                System.out.println(p);
            }
        }
    }

    public List<Product> getProduct(String InputQuery,int pageNumber) throws IOException {
        String encodedProduct = null;
        try {
            encodedProduct = URLEncoder.encode(InputQuery, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        String url = "https://www.amazon.in/s?k=" + encodedProduct + "&page=" + Integer.toString(pageNumber);

        Document doc = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
                .timeout(10000)
                .get();

        Elements productContainers = doc.select("div[data-component-type='s-search-result']");
        List<Product> products = new ArrayList<>();

        for(Element container : productContainers){
            Element titleElement = container.selectFirst("h2.a-size-mini a span");
            if (titleElement == null) {
                titleElement = container.selectFirst("h2 a span");
            }
            String title = titleElement != null ? titleElement.text() : "No Title";

            // Skip this product if no title is found
            if (titleElement == null || titleElement.text().trim().isEmpty()) {
                continue;
            }

            Element priceElement = container.selectFirst(".a-price-whole");
            String price = priceElement != null ? priceElement.text() : "Price Not mentioned";

            Element imgElement = container.selectFirst("img.s-image");
            String imageUrl = imgElement != null ? imgElement.attr("src") : "No Image";

            Element linkElement = container.selectFirst("h2 a");
            // Skip this product if no link is found
            if (linkElement == null || linkElement.attr("href").trim().isEmpty()) {
                continue;
            }
            String productUrl = "https://www.amazon.in" + linkElement.attr("href");

            Element ratingElement = container.selectFirst(".a-icon-alt");
            String rating = ratingElement != null ? ratingElement.text() : "No rating available";

            Element sponsoredElement = container.selectFirst("span.a-color-secondary");
            boolean isSponsored = sponsoredElement != null && sponsoredElement.text().equalsIgnoreCase("Sponsored");

            Product product = new Product();
            product.setTitle(title);
            product.setPrice(price);
            product.setImageUrl(imageUrl);
            product.setProductUrl(productUrl);
            product.setRating(rating);
            product.setIsSponsored(isSponsored);
            products.add(product);
        }
        return products;
    }
}
