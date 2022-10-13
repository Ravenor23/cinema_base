package com.kata.cinema.base.webapp.controllers.unauthorized;

import com.kata.cinema.base.models.dto.response.PageDto;
import com.kata.cinema.base.models.dto.response.ReviewResponseDto;
import com.kata.cinema.base.models.enums.ReviewSortType;
import com.kata.cinema.base.models.enums.TypeReview;
import com.kata.cinema.base.service.PaginationDtoService;
import com.kata.cinema.base.service.dto.impl.ReviewService;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class MovieRestController {

  private final PaginationDtoService<ReviewResponseDto> paginationDtoService;
  private final ReviewService reviewService;

  @Autowired
  public MovieRestController(PaginationDtoService<ReviewResponseDto> paginationDtoService,
      ReviewService reviewService) {
    this.paginationDtoService = paginationDtoService;
    this.reviewService = reviewService;
  }

  @GetMapping("/api/movies/{id}/reviews/page/{pageNumber}")
  public PageDto<ReviewResponseDto> getPageReview(@PathVariable("id") Long id, @PathVariable("pageNumber") Integer pageNumber,
      @RequestParam("itemsOnPage") Integer itemsOnPage,
      @RequestParam(name = "typeReview", required = false) TypeReview typeReview,
      @RequestParam(name = "reviewSortType", defaultValue = "DATE_ASC") ReviewSortType reviewSortType){
    List<ReviewResponseDto> reviews = reviewService.getReviews(id);
    HashMap<String, Object> parameters = new HashMap<>();
    parameters.put("typeReview", typeReview);
    parameters.put("reviewSortType", reviewSortType);
    PageDto<ReviewResponseDto> pageDto = paginationDtoService.getPageDtoWithParameters(pageNumber, itemsOnPage, parameters);
    pageDto.setEntities(reviews);
    return pageDto;
  }

}
