package Product;

    public class Product {
        private int productId;
        private String name;
        private String description;
        private double price;
        private int stockQuantity;

        public Product(int productId, String name, String description, double price, int stockQuantity) {
            this.productId = productId;
            this.name = name;
            this.description = description;
            this.price = price;
            this.stockQuantity = stockQuantity;
        }

        public void introduce() {
            System.out.printf(
                    "Produkt #%d: %s — %s (%.2f SEK), Lager: %d%n",
                    productId, name, description, price, stockQuantity);
        }

        public int getProductId() {
            return productId;
        }

        public void setProductId(int id) {
            this.productId = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String d){
            this.description = d;
        }
        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getStockQuantity() {
            return stockQuantity;
        }

        public void setStockQuantity(int sq) {
            this.stockQuantity = sq;
        }
    }

