package com.comparekart.comparekart.service;

import com.comparekart.comparekart.scraper.AmazonScraper;
import com.comparekart.comparekart.scraper.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@Component
public class ProductService {

    @Autowired
    private AmazonScraper amazonScraper;

    public List<Product> SearchProductFromAmazon(
            String InputQuery,
            int PageNumber
    ) throws IOException {
        return amazonScraper.getProduct(
                InputQuery,
                PageNumber
        );
    }
}
