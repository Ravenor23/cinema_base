package com.kata.cinema.base.webapp.controllers.unauthorized;

import com.kata.cinema.base.models.dto.response.PageDto;
import com.kata.cinema.base.models.dto.response.ReviewResponseDto;
import com.kata.cinema.base.models.enums.ReviewSortType;
import com.kata.cinema.base.models.enums.TypeReview;
import com.kata.cinema.base.service.ReviewPaginationDtoService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class MovieRestController {

  private final ReviewPaginationDtoService reviewPaginationDtoService;

  @Autowired
  public MovieRestController(ReviewPaginationDtoService reviewPaginationDtoService) {
    this.reviewPaginationDtoService = reviewPaginationDtoService;
  }

  @GetMapping("/api/movies/{id}/reviews/page/{pageNumber}")
  public PageDto<ReviewResponseDto> getPageReview(@PathVariable("id") Long movieId, @PathVariable("pageNumber") Integer pageNumber,
      @RequestParam("itemsOnPage") Integer itemsOnPage,
      @RequestParam(name = "typeReview", required = false) TypeReview typeReview,
      @RequestParam(name = "reviewSortType", defaultValue = "DATE_ASC") ReviewSortType reviewSortType){
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("movieId", movieId);
    parameters.put("typeReview", typeReview);
    parameters.put("reviewSortType", reviewSortType);
    PageDto<ReviewResponseDto> pageDto = reviewPaginationDtoService.getPageDtoWithParameters(pageNumber, itemsOnPage, parameters);
    return pageDto;
  }
}
