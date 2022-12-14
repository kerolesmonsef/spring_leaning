package com.example.qgame.helpers.database.seeders.impls;

import com.example.qgame.Models.Option;
import com.example.qgame.Models.Product;
import com.example.qgame.Models.ProductOptionValue;
import com.example.qgame.helpers.database.seeders.ISeeder;
import com.example.qgame.repositories.OptionRepository;
import com.example.qgame.repositories.ProductOptionValueRepository;
import com.example.qgame.repositories.ProductRepository;

import java.util.*;

public class ProductOptionValueSeeder extends ISeeder {

    private final ProductOptionValueRepository productOptionValueRepository;
    private final OptionRepository optionRepository;
    private final ProductRepository productRepository;

    public ProductOptionValueSeeder(ProductOptionValueRepository productOptionValueRepository, OptionRepository optionRepository, ProductRepository productRepository) {
        this.productOptionValueRepository = productOptionValueRepository;
        this.optionRepository = optionRepository;
        this.productRepository = productRepository;
    }


    @Override
    protected Collection data() {
        List<ProductOptionValue> data = new ArrayList<>();
        Map<String, List<String>> dataMap = values();

        List<Product> products = productRepository.findAll();
        List<Option> options = optionRepository.findAll();


        for (Product product : products) {
            for (Option option : options) {
                for (String value : randomNElement(dataMap.get(option.getTitle()), faker().number().numberBetween(2, 3))) {
                    ProductOptionValue productOptionValue = new ProductOptionValue()
                            .setOption(option)
                            .setProduct(product)
                            .setValue(value);
                    data.add(productOptionValue);
                }
            }
        }

        return data;
    }


    @Override
    public void seed() {
        productOptionValueRepository.saveAll(data());
    }


//    ---------------------------------------------------

    private Map<String, List<String>> values() {

        List<String> colors = new ArrayList<>();
        List<String> materials = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            colors.add(faker().color().name());
            materials.add(faker().cat().name());
        }

        List<String> sizes = Arrays.asList("x", "xx", "xxx", "large", "medium", "small", "big", "huge");
        List<String> weights = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8");
        List<String> heights = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8");


        return Map.ofEntries(
                Map.entry("color", colors),
                Map.entry("size", sizes),
                Map.entry("weight", weights),
                Map.entry("height", heights),
                Map.entry("material", materials)
        );
    }

    private List<String> randomNElement(List<String> list, int n) {
        n = n < list.size() ? n : list.size();
        List<String> copy = new ArrayList<>(list);
        Collections.shuffle(copy);
        return copy.subList(0, n);
    }
}
