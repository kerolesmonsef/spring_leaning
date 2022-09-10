package com.example.qgame.services;

import com.example.qgame.Models.Option;
import com.example.qgame.Models.Product;
import com.example.qgame.helpers.dto.OptionValueDTO;
import com.example.qgame.helpers.paginations.Pagination;
import com.example.qgame.helpers.paginations.PaginationMaker;
import com.example.qgame.repositories.ProductRepository;
import com.example.qgame.requests.admin.AdminProductRequest;
import javassist.runtime.Desc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    @Autowired
    private OptionService optionService;

    @Autowired
    PaginationMaker<Product> paginationMaker;

    public Pagination<Product> getPageable() {
        return paginationMaker.makeFromJpaRepository(repository, "/admin/products");
    }


    public Product store(AdminProductRequest request) {
        Product product = request.toProduct();
        syncImages(product, request.getImages());

        List<OptionValueDTO> optionValues = request.getOptionValues();

        if (optionValues != null) {
            List<String> optionTitles = optionValues.stream().map(OptionValueDTO::getOptionName).toList();
            List<Option> options = optionService.findOrCreate(optionTitles);
            optionValues.stream().forEach((o) -> {
                Option option = options.stream().filter((ol) -> ol.getTitle().equals(o.getOptionName())).toList().get(0);
                o.setOption(option);
            });
        }


        return product;
    }

    public void syncImages(Product product, List<MultipartFile> files) {
        if (files == null || product == null) return;

        if (product.getId() == null){

        }

    }
}
