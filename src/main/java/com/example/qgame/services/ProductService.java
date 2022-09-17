package com.example.qgame.services;

import com.example.qgame.Models.Option;
import com.example.qgame.Models.Product;
import com.example.qgame.Models.ProductOptionValue;
import com.example.qgame.helpers.dto.OptionValueDTO;
import com.example.qgame.helpers.entityembadable.FilesList;
import com.example.qgame.helpers.filters.products.*;
import com.example.qgame.helpers.paginations.Pagination;
import com.example.qgame.helpers.paginations.PaginationMaker;
import com.example.qgame.helpers.services.files.AssetFileManager;
import com.example.qgame.helpers.services.files.FileInfo;
import com.example.qgame.repositories.ProductOptionValueRepository;
import com.example.qgame.repositories.ProductRepository;
import com.example.qgame.requests.admin.AdminProductRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private HttpServletRequest servletRequest;

    @Autowired
    private ProductOptionValueRepository productOptionValueRepository;

    @Autowired
    private OptionService optionService;

    @Autowired
    PaginationMaker<Product> paginationMaker;

    @Autowired
    FilterQueryBuilder filterQueryBuilder;

    public Pagination<Product> getPageable() {
        return paginationMaker.makeFromJpaRepository(productRepository, "/admin/products");
    }

    @Transactional
    public Product store(AdminProductRequest request) {
        Product product = request.toProduct();

        syncImages(product, request.getImages());
        syncOptionValue(product, request);

        return productRepository.save(product);
    }

    @Transactional
    public Product update(Product product, AdminProductRequest request) {

        syncImages(product, request.getImages());
        syncOptionValue(product, request);


        product
                .setDescription(request.getDescription())
                .setTitle(request.getTitle())
                .setQuantity(request.getQuantity())
                .setCategory(request.getCategory())
                .setSlug(request.getSlug())
                .setBuyPrice(request.getBuyPrice())
                .setPrice(request.getPrice())
                .setDiscount_percentage(request.getDiscount_percentage());



        return productRepository.save(product);
    }

    public void syncImages(Product product, List<MultipartFile> files) {
        if (files == null || product == null) return;

        FilesList oldFiles = product.getNativeImages();
        FilesList newFiles = new FilesList();
        AssetFileManager fileUploader = new AssetFileManager().setFilePath("/images/products/");
        for (MultipartFile file : files) {
            FileInfo fileInfo = fileUploader.setFile(file).upload();
            newFiles.add(fileInfo.getName());
        }


        product.setImages(newFiles);

        // remove files
        if (oldFiles == null) return;

        for (String fileName : oldFiles.getNativeFiles()) {
            fileUploader.remove(fileName);
        }

    }

    public void syncOptionValue(Product product, AdminProductRequest request) {
        List<OptionValueDTO> optionValueDTOS = request.getOptionValues();

        if (optionValueDTOS != null) {
            List<String> optionTitles = optionValueDTOS.stream().map(OptionValueDTO::getOptionName).toList();
            List<Option> options = optionService.findOrCreate(optionTitles);
            optionValueDTOS.forEach((o) -> {
                Option option = options.stream().filter((ol) -> ol.getTitle().equals(o.getOptionName())).toList().get(0);
                o.setOption(option);
            });
        }

        // delete old product option values
        if (product.getId() != null) {
            productOptionValueRepository.deleteByProduct(product.getId());
        }

        // insert new option values
        if (optionValueDTOS != null) {
            List<ProductOptionValue> productOptionValues = optionValueDTOS.stream().map((optionValueDTO) -> {
                return new ProductOptionValue()
                        .setOption(optionValueDTO.getOption())
                        .setValue(optionValueDTO.getValue())
                        .setProduct(product);
            }).toList();

            productOptionValueRepository.saveAll(productOptionValues);
        }
    }

    @Transactional
    public Object filter(List<Map<String, Object>> properties) {
        FilterOptionCollection optionCollection = new FilterOptionCollection(properties);
        FilterQueryBuilderResult productFilterResult = filterQueryBuilder.buildProductQuery(optionCollection);

        FilterOptionFilter filterOptionFilter = new FilterOptionFilter(productFilterResult);

        return filterOptionFilter.getOptions();


//        return entityManager.createQuery(productFilterResult.getCriteria()).getResultList();
    }
}
