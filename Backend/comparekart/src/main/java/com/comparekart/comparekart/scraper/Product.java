package com.comparekart.comparekart.scraper;

//@Data
////@ToString
//@AllArgsConstructor
//@NoArgsConstructor
//public class Product {
//    private String title;
//    private String price;
//    private String imageUrl;
//    private String productUrl;
//    private String rating;
//    private Boolean isSponsored;
//}
// Product.java
public class Product {
    private String title;
    private String price;
    private String imageUrl;
    private String productUrl;
    private String rating;
    private Boolean isSponsored;


    // Default constructor
    public Product() {}

    // Parameterized constructor
    public Product(String title, String price, String imageUrl, String productUrl, String rating, Boolean isSponsored) {
        this.title = title;
        this.price = price;
        this.imageUrl = imageUrl;
        this.productUrl = productUrl;
        this.rating = rating;
        this.isSponsored = isSponsored;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public String getRating() {
        return rating;
    }

    public Boolean getIsSponsored() {
        return isSponsored;
    }

    // Setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setIsSponsored(Boolean isSponsored) {
        this.isSponsored = isSponsored;
    }

    // toString method for easy printing
    @Override
    public String toString() {
        return "Product Details:\n" +
                "Title: " + title + "\n" +
                "Price: " + price + "\n" +
                "Rating: " + rating + "\n" +
                "Sponsored: " + (isSponsored ? "Yes" : "No") + "\n" +
                "Image URL: " + imageUrl + "\n" +
                "Product URL: " + productUrl;
    }

    // equals and hashCode methods (optional but good practice)
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Product product = (Product) obj;

        if (title != null ? !title.equals(product.title) : product.title != null) return false;
        if (price != null ? !price.equals(product.price) : product.price != null) return false;
        if (imageUrl != null ? !imageUrl.equals(product.imageUrl) : product.imageUrl != null) return false;
        if (productUrl != null ? !productUrl.equals(product.productUrl) : product.productUrl != null) return false;
        if (rating != null ? !rating.equals(product.rating) : product.rating != null) return false;
        return isSponsored != null ? isSponsored.equals(product.isSponsored) : product.isSponsored == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (imageUrl != null ? imageUrl.hashCode() : 0);
        result = 31 * result + (productUrl != null ? productUrl.hashCode() : 0);
        result = 31 * result + (rating != null ? rating.hashCode() : 0);
        result = 31 * result + (isSponsored != null ? isSponsored.hashCode() : 0);
        return result;
    }
}