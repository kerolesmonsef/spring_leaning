package com.example.qgame.helpers;

import com.example.qgame.QGameApplication;
import com.example.qgame.requests.admin.AdminCreateUpdateBlogRequest;
import org.hibernate.query.criteria.internal.compile.CriteriaQueryTypeQueryAdapter;
import org.springframework.context.ApplicationContext;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.text.Normalizer;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class Helper {

    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

    public static String limit(String text, int limit) {
        if (text.length() > limit) {
            return text.substring(0, limit) + " ...";
        }
        return text;
    }

    public static <T> List<List<T>> getBatches(List<T> list, int batchSize) {
        return IntStream.iterate(0, i -> i < list.size(), i -> i + batchSize)
                .mapToObj(i -> list.subList(i, Math.min(i + batchSize, list.size())))
                .toList();
    }


    public static String base_url() {
        return ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();

    }


    public static String slug(String input) {
        String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
        String normalized = Normalizer.normalize(nowhitespace, Normalizer.Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");


        return slug.toLowerCase(Locale.ENGLISH);
    }

    public static String NWords(String text, int n) {
        String[] words = text.split(" ");
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < n && i < words.length; i++) {
            result.append(words[i]).append(" ");
        }
        return result.toString();
    }


    public static void appendFlashAttribute(String requestName, Object requestObject, RedirectAttributes attributes, BindingResult binding) {
        attributes
                .addFlashAttribute("org.springframework.validation.BindingResult." + requestName, binding)
                .addFlashAttribute(requestName, requestObject);

    }

    public static void appendToModelIfNotExist(String requestName, Class<?> clazz, Model model) {

        if (!model.containsAttribute(requestName)) {
            model.addAttribute(requestName, QGameApplication.getContext().getBean(clazz));
        }
    }

    public static ModelAndView redirectBack(HttpServletRequest request) {
        String backUrl = request.getHeader("Referer");

        return new ModelAndView("redirect:" + backUrl);
    }

    public static String getQueryString(Query query) {
        try {
            return ((CriteriaQueryTypeQueryAdapter) query).getQueryString();
        } catch (Exception e) {

        }
        return null;
    }
}
